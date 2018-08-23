package com.runxsports.provider.cs.cms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "us_t_user")
public class User {
    @Id
    private Long id;

    /**
     * 用户类型:0-学生/1-支部书记/2-辅导员
     */
    @Column(name = "user_type")
    private String userType;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 班级
     */
    @Column(name = "class_name")
    private String className;

    /**
     * 学号
     */
    @Column(name = "user_no")
    private String userNo;

    /**
     * 所在支部
     */
    @Column(name = "team_name")
    private String teamName;

    /**
     * 支部书记
     */
    @Column(name = "team_leader")
    private String teamLeader;

    /**
     * 辅导员
     */
    private String teacher;

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
     * 获取用户类型:0-学生/1-支部书记/2-辅导员
     *
     * @return user_type - 用户类型:0-学生/1-支部书记/2-辅导员
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户类型:0-学生/1-支部书记/2-辅导员
     *
     * @param userType 用户类型:0-学生/1-支部书记/2-辅导员
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取班级
     *
     * @return class_name - 班级
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置班级
     *
     * @param className 班级
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取学号
     *
     * @return user_no - 学号
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * 设置学号
     *
     * @param userNo 学号
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * 获取所在支部
     *
     * @return team_name - 所在支部
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 设置所在支部
     *
     * @param teamName 所在支部
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * 获取支部书记
     *
     * @return team_leader - 支部书记
     */
    public String getTeamLeader() {
        return teamLeader;
    }

    /**
     * 设置支部书记
     *
     * @param teamLeader 支部书记
     */
    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    /**
     * 获取辅导员
     *
     * @return teacher - 辅导员
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * 设置辅导员
     *
     * @param teacher 辅导员
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
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