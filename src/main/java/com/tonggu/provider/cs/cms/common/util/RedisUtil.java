package com.tonggu.provider.cs.cms.common.util;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Preconditions;
import com.tonggu.provider.cs.cms.common.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Admin on 2018/4/8.
 * @author Admin
 */
@Slf4j
@Component
public class RedisUtil{
	
	@Resource(name="stringRedisTemplate")
    private StringRedisTemplate rt;
    
    /** 15天超时 */
    public static int expireMsecsToday = 60 * 60 * 24 * 15;
    
    /** 默认过期时间 */
	private static final int DEFAULT_EXPIRE = 30;
	
	/** 默认过期时间单位 */
	private static final TimeUnit DEFAULT_EXPIRE_UNIT = TimeUnit.SECONDS;
	
	/** 默认最大重试次数 */
	private static final int DEFAULT_RETRY = 3;
	
	/** 默认等待时间 */
	private static final int DEFAULT_WAIT = 1;
	
	/** 默认等待时间单位 */
	private static final TimeUnit DEFAULT_WAIT_UNIT = TimeUnit.SECONDS;

    
    public String getKey(String key) {
        String value = null;
        ValueOperations<String, String> ops = rt.opsForValue();
        if (rt.hasKey(key)) {
            value = ops.get(key);
        }
        log.info("getKey. [OK] key={}", key);
        return value;
    }

    
    public void deleteKey(String key) {
        rt.delete(key);
        log.info("deleteKey. [OK] key={}", key);

    }

    public void setKey(String key, String value) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");

        ValueOperations<String, String> ops = rt.opsForValue();
        ops.set(key, value);
        log.info("setKey. [OK] key={}", key);
    }
    
    public void setKey(String key, String value, long timeout, TimeUnit unit) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        Preconditions.checkArgument(unit != null, "TimeUnit is not null");
        ValueOperations<String, String> ops = rt.opsForValue();
        ops.set(key, value);
        rt.expire(key, timeout, unit);
        log.info("setKey. [OK] , value={}, timeout={}, unit={}", key,  timeout, unit);
    }

    public void zadd(String key,  String value, Long score) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        ZSetOperations<String, String> ops = rt.opsForZSet();
        ops.add(key, value, score);
        log.info("zadd. [OK] key={}, value={} , score={}", key, value, score);
    }

    
    public Set<String> zrangeByScore(String key, Long min, Long max) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        ZSetOperations<String, String> ops = rt.opsForZSet();
        Set<String> sets = ops.rangeByScore(key, min, max);
        log.info("zrangeByScore. [OK] key={}, min={}, max={}", key, min, max);
        return sets;
    }
    
    public Set<String> zrange(String key, Long start, Long end) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        ZSetOperations<String, String> ops = rt.opsForZSet();
        Set<String> sets = ops.range(key, start, end);
        log.info("zrange. [OK] key={}, start={}, end={}", key, start, end);
        return sets;
    }
    
    public Set<ZSetOperations.TypedTuple<String>> zrangeWithScores(String key, Long start, Long end) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        ZSetOperations<String, String> ops = rt.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> sets = ops.rangeWithScores(key, start, end);
        log.info("zrangeWithScores. [OK] key={}, start={}, end={}", key, start, end);
        return sets;
    }
    
    public void zIncrby(String key, String value, Double delta) {
    	Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        ZSetOperations<String, String> ops = rt.opsForZSet();
        ops.incrementScore(key, value, delta);
        log.info("zIncrby. [OK] key={}, value={} , delta={}", key, value, delta);
    }

    public void zremove(String key, Object...objects) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        ZSetOperations<String, String> ops = rt.opsForZSet();
        ops.remove(key, objects);
        log.info("remove zset. [OK] key={}, value={}", key, objects);
    }
    
    public Double zscore(String key, String value) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        ZSetOperations<String, String> ops = rt.opsForZSet();
        Double score = ops.score(key, value);
        log.info("remove zset. [OK] key={}, value={}", key, value);
        return score;
    }

    /**
     * @param key
     * @param liveTime
     * @return
     */
    public Long incr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, rt.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        //初始设置过期时间
        boolean b= (null == increment || increment.longValue() == 0) && liveTime > 0;
        if (b) {
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }

        return increment;
    }

	/*
		===================================新版分布式锁Start======================================= 
		作者：瞿智谋，日期：2016-06-25
	*/
    
	
	public void lock(String key) { lock(key, DEFAULT_EXPIRE, DEFAULT_EXPIRE_UNIT); }
	
	
	public void lock(String key, int expire, TimeUnit expireUnit) { lock(key, expire, expireUnit, DEFAULT_RETRY, DEFAULT_WAIT, DEFAULT_WAIT_UNIT); }

	
	public void lock(String key, int retry) { lock(key, retry, DEFAULT_WAIT, DEFAULT_WAIT_UNIT); }
	
	
	public void lock(String key, int retry, int wait, TimeUnit waitUnit) { lock(key, DEFAULT_EXPIRE, DEFAULT_EXPIRE_UNIT, retry, wait, waitUnit); }
	
	
	public void lock(String key, int expire, TimeUnit expireUnit, int retry, int wait, TimeUnit waiUnit) {
		for (; retry > 0; retry--) {
			long ts = System.currentTimeMillis(); String val = ts + TimeoutUtils.toMillis(expire, expireUnit) + "";
			if (rt.opsForValue().setIfAbsent(key, val)) {
				rt.expire(key, expire, expireUnit); return;
			}
            // [针对死锁]当前时间大于超时时间，则释放原锁并获取锁
			synchronized (key) {
				if (ts > Long.valueOf(rt.opsForValue().get(key))) {
					rt.opsForValue().set(key, val, expire, expireUnit); return;
				}
			}
			try {
				waiUnit.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		throw new BaseException("获取分布式锁失败");
	}

    // ====================================新版分布式锁End========================================

    /**
     * 获取自增值
     *
     * @param key
     * @return
     */
    public long getIncrValue(final String key) {
        return rt.execute(new RedisCallback<Long>() {
            
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = rt.getStringSerializer();
                byte[] rowkey = serializer.serialize(key);
                byte[] rowval = connection.get(rowkey);
                try {
                    String val = serializer.deserialize(rowval);
                    return Long.parseLong(val);
                } catch (Exception e) {
                    return 0L;
                }
            }
        });
    }

    /**
     * 解锁操作
     *
     * @param lockKey
     */
    
    public void unlock(String lockKey) {
        this.deleteKey(lockKey);
        log.info("unlock Key. [OK] key={}", lockKey);

    }


    /**
     * Redis自增
     *
     * @param key
     * @param value
     * @return
     */
    
    public Long increment(final String key, final long value) {
        ValueOperations<String, String> ops = rt.opsForValue();
        return ops.increment(key, value);
    }

    
    public <T> T getObject(final String key, Class<T> clazz) {
        String value = this.getKey(key);
        return JSON.parseObject(value, clazz);
    }
    
    public <T> List<T> getList(final String key, Class<T> clazz) {
        String value = this.getKey(key);
        return JSONArray.parseArray(value, clazz);
    }

    
    public void setObject(final String key, Object object, long timeout, TimeUnit unit) {
        String value = JSON.toJSONString(object);
        this.setKey(key, value, timeout, unit);
    }
    
    public void setObject(final String key, Object object) {
        String value = JSON.toJSONString(object);
        this.setKey(key, value);
    }

	
	public void expire(String key, long timeout, TimeUnit unit) {
		rt.expire(key, timeout, unit);		
	}
	
	
	public void expireAt(String key, long date) {
		rt.expireAt(key, new Date(date));	
	}
	
	public long lpush(String key, String value) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		long len = rt.boundListOps(key).leftPush(value);
		log.info("lpush. [OK] key={}, value={}", key, value);
		return len;
	}
	
	public long lpush(String key, Object object) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		String value = JSON.toJSONString(object);
		long len = rt.boundListOps(key).leftPush(value);
		log.info("lpush. [OK] key={}, object={}", key, value);
		return len;
	}

	public long rpush(String key, String value) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		long len = rt.boundListOps(key).rightPush(value);
		log.info("lpush. [OK] key={}, value={}", key, value);
		return len;
	}
	
	public long rpush(String key, Object object) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		String value = JSON.toJSONString(object);
		long len = rt.boundListOps(key).rightPush(value);
		log.info("lpush. [OK] key={}, value={}", key, value);
		return len;
	}
	
	public String rpop(String key) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		String value = rt.boundListOps(key).rightPop();
		log.info("rpop. [OK] key={}, value={}", key, value);
		return value;
	}
	
	public <T> T rpop(String key, Class<T> clazz) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		String value = rt.boundListOps(key).rightPop();
		log.info("rpop. [OK] key={}, value={}", key, value);
		return JSON.parseObject(value, clazz);
	}
	
	public String brpop(String key, long timeout, TimeUnit unit) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		String value = rt.boundListOps(key).rightPop(timeout, unit);
		log.info("brpop. [OK] key={}, value={}", key, value);
		return value;
	}
	
	public <T> T brpop(String key, Class<T> clazz, long timeout, TimeUnit unit) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		String value = rt.boundListOps(key).rightPop(timeout, unit);
		log.info("brpop. [OK] key={}, value={}", key, value);
		return JSON.parseObject(value, clazz);
	}
	
	public void lrem(String key, String value) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		rt.boundListOps(key).remove(0, value);
		log.info("lrem. [OK] key={}, value={}", key, value);
	}
	
	public List<String> lrange(String key, long start, long end) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		List<String> list = rt.boundListOps(key).range(start, end);
		log.info("lrange. [OK] key={}, start={}, end={}", key, start, end);
		return list;
	}
	
	public long llen(String key) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
		long len = rt.boundListOps(key).size();
		log.info("llen. [OK] key={}", key);
		return len;
	}
	
	public void sendMessage(String topic, Object object) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(topic), "Redis topic is not null");
		String value = JSON.toJSONString(object);
		rt.convertAndSend(topic, value);
		log.info("sendMessage. [OK] topic={}", topic);
	}
}
