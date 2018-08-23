package com.runxsports.provider.cs.cms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "us_t_question_item")
public class QuestionItem {
    @Id
    private Long id;

    /**
     * 题库ID
     */
    @Column(name = "question_id")
    private Long questionId;

    /**
     * 序号
     */
    @Column(name = "item_no")
    private String itemNo;

    /**
     * 内容
     */
    @Column(name = "item_content")
    private String itemContent;

    /**
     * 排序
     */
    @Column(name = "item_sort")
    private Integer itemSort;

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
     * 获取题库ID
     *
     * @return question_id - 题库ID
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * 设置题库ID
     *
     * @param questionId 题库ID
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取序号
     *
     * @return item_no - 序号
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * 设置序号
     *
     * @param itemNo 序号
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * 获取内容
     *
     * @return item_content - 内容
     */
    public String getItemContent() {
        return itemContent;
    }

    /**
     * 设置内容
     *
     * @param itemContent 内容
     */
    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    /**
     * 获取排序
     *
     * @return item_sort - 排序
     */
    public Integer getItemSort() {
        return itemSort;
    }

    /**
     * 设置排序
     *
     * @param itemSort 排序
     */
    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
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
     *
     * @return is_delete - 是否删除
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
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