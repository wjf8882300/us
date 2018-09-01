package com.tonggu.provider.cs.cms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "us_t_user_login")
public class UserLogin {
    @Id
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名称
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private Long createUser;

    /**
     * 更新时间
     */
    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * 更新人
     */
    @Column(name = "last_update_user")
    private Long lastUpdateUser;

    /**
     * 是否删除
            0-未删除/1-已删除
     */
    @Column(name = "is_delete")
    private String isDelete;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String memo;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名称
     *
     * @return open_id - 用户名称
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置用户名称
     *
     * @param openId 用户名称
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取更新时间
     *
     * @return last_update_date - 更新时间
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * 设置更新时间
     *
     * @param lastUpdateDate 更新时间
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * 获取更新人
     *
     * @return last_update_user - 更新人
     */
    public Long getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * 设置更新人
     *
     * @param lastUpdateUser 更新人
     */
    public void setLastUpdateUser(Long lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * 获取是否删除
            0-未删除/1-已删除
     *
     * @return is_delete - 是否删除
            0-未删除/1-已删除
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
            0-未删除/1-已删除
     *
     * @param isDelete 是否删除
            0-未删除/1-已删除
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取备注
     *
     * @return memo - 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注
     *
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
}