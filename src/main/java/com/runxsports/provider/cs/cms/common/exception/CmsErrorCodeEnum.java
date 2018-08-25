package com.runxsports.provider.cs.cms.common.exception;

import com.runxsports.provider.cs.cms.common.constant.enumerate.BaseCallbackEnum;

/** 
 * 业务异常枚举
 *
 * @author wangjf
 * @since 2018/7/27 11:11
 **/
public enum CmsErrorCodeEnum implements BaseCallbackEnum {
    /** */
//    CMS9083001(9083001, "查询异常"),
    CMS9083002(9083002, "参数异常"),
    CMS9083003(9083003, "更新失败"),
    CMS9083004(9083004, "新增失败"),
    CMS9083005(9083005, "删除失败"),
    CMS9083006(9083006, "查询失败"),
    CMS9083007(9083007, "导入失败，发送异常"),
    CMS9083008(9083008, "导入失败，excel为空或者格式有误"),
    CMS9083009(9083009, "导入失败，上传文件为空"),
    CMS9083010(9083010, "请选择导出成绩类型"),
    ;

    /**
     * 错误码
     */
    private Integer value;

    /**
     * 说明
     */
    private String intro;

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    private CmsErrorCodeEnum(Integer value, String intro) {
        this.value = value;
        this.intro = intro;
    }
}

