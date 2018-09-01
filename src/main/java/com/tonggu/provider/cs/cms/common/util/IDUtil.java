package com.tonggu.provider.cs.cms.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @desc ID生成工具类
 * @author quzhimou
 * @since 2018年1月4日
 */
public class IDUtil {

	private static final DateTimeFormatter ORDER_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	private static final IDGenerator GENERATOR = new IDGenerator(2, Long.parseLong(IpUtil.getLocalAddress().replace(".", "")) % 32);
	
	private static final class IDGenerator{
		
		private final long twepoch = 1526067598228L;
		private final long workerIdBits = 5L;
		private final long datacenterIdBits = 5L;
		private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
		private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
		private final long sequenceBits = 12L;
		private final long workerIdShift = sequenceBits;
		private final long datacenterIdShift = sequenceBits + workerIdBits;
		private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
		private final long sequenceMask = -1L ^ (-1L << sequenceBits);
	
		private long workerId;
		private long datacenterId;
		private long sequence = 0L;
		private long lastTimestamp = -1L;

		public IDGenerator(long workerId, long datacenterId) {
			if (workerId > maxWorkerId || workerId < 0) {
				throw new IllegalArgumentException(
						String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
			}
			if (datacenterId > maxDatacenterId || datacenterId < 0) {
				throw new IllegalArgumentException(
						String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
			}
			this.workerId = workerId;
			this.datacenterId = datacenterId;
		}
	
		public synchronized long nextId() {
			long timestamp = timeGen();
			if (timestamp < lastTimestamp) {
				throw new RuntimeException(String.format(
						"Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
			}
			if (lastTimestamp == timestamp) {
				sequence = (sequence + 1) & sequenceMask;
				if (sequence == 0) {
					timestamp = tilNextMillis(lastTimestamp);
				}
			} else {
				sequence = 0L;
			}
	
			lastTimestamp = timestamp;
	
			return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
					| (workerId << workerIdShift) | sequence;
		}
	
		protected long tilNextMillis(long lastTimestamp) {
			long timestamp = timeGen();
			while (timestamp <= lastTimestamp) {
				timestamp = timeGen();
			}
			return timestamp;
		}
	
		protected long timeGen() {
			return System.currentTimeMillis();
		}
	}
	
	/** 生成long型ID */
	public static long nextId(){
		return GENERATOR.nextId();
	}
	
	/** 生成String型ID */
	public static String nextStrId(){
		return String.valueOf(nextId());
	}
	
	/** 生成UUID */
	public static String uuId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/** 生成订单ID */
	public static String nextOrderId() {
		return "YCD" + LocalDate.now().format(ORDER_FORMAT) + nextId();
	}
	
	/** 生成批次号 */
	public static String nextBatchId() {
		return "YCDBATCH" + LocalDate.now().format(ORDER_FORMAT) + nextId();
	}
}