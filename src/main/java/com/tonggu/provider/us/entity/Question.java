package com.tonggu.provider.us.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "us_t_question")
public class Question {
    @Id
    private Long id;

    /**
     * 题目类别:0-学生题/1-支部题/2-指导员题
     */
    @Column(name = "question_group")
    private String questionGroup;

    /**
     * 题目类型:0-选择题/1-填空题
     */
    @Column(name = "question_type")
    private String questionType;

    /**
     * 题目内容
     */
    @Column(name = "question_content")
    private String questionContent;
    
    /**
     * 题目分值
     */
    @Column(name = "question_score")
    private Integer questionScore;
    
    /**
     * 题目说明
     */
    @Column(name = "question_desc")
    private String questionDesc;

    /**
     * 排序
     */
    @Column(name = "question_sort")
    private Integer questionSort;

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
    
    public Question() {
    	super();
    }

    public Question(Long id,String questionGroup,String questionContent,Integer questionSort) {
    	this.id = id;
    	this.questionGroup = questionGroup;
    	this.questionContent=questionContent;
    	this.questionSort=questionSort;
    }
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
     * 获取题目类别:0-学生题/1-支部题/2-指导员题
     *
     * @return question_group - 题目类别:0-学生题/1-支部题/2-指导员题
     */
    public String getQuestionGroup() {
        return questionGroup;
    }

    /**
     * 设置题目类别:0-学生题/1-支部题/2-指导员题
     *
     * @param questionGroup 题目类别:0-学生题/1-支部题/2-指导员题
     */
    public void setQuestionGroup(String questionGroup) {
        this.questionGroup = questionGroup;
    }

    /**
     * 获取题目类型:0-选择题/1-填空题
     *
     * @return question_type - 题目类型:0-选择题/1-填空题
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * 设置题目类型:0-选择题/1-填空题
     *
     * @param questionType 题目类型:0-选择题/1-填空题
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    /**
     * 获取题目内容
     *
     * @return question_content - 题目内容
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * 设置题目内容
     *
     * @param questionContent 题目内容
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    /**
     * 获取排序
     *
     * @return question_sort - 排序
     */
    public Integer getQuestionSort() {
        return questionSort;
    }

    /**
     * 设置排序
     *
     * @param questionSort 排序
     */
    public void setQuestionSort(Integer questionSort) {
        this.questionSort = questionSort;
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

	public Integer getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(Integer questionScore) {
		this.questionScore = questionScore;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
}