package com.runxsports.provider.cs.cms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "com_t_param")
public class Param {
    @Id
    private Long id;

    /**
     * 参数类型
     */
    @Column(name = "parameter_type")
    private String parameterType;

    /**
     * 参数名
     */
    @Column(name = "parameter_name")
    private String parameterName;

    /**
     * 参数值
     */
    @Column(name = "parameter_value")
    private String parameterValue;

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
     * 获取参数类型
     *
     * @return parameter_type - 参数类型
     */
    public String getParameterType() {
        return parameterType;
    }

    /**
     * 设置参数类型
     *
     * @param parameterType 参数类型
     */
    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    /**
     * 获取参数名
     *
     * @return parameter_name - 参数名
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * 设置参数名
     *
     * @param parameterName 参数名
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * 获取参数值
     *
     * @return parameter_value - 参数值
     */
    public String getParameterValue() {
        return parameterValue;
    }

    /**
     * 设置参数值
     *
     * @param parameterValue 参数值
     */
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
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