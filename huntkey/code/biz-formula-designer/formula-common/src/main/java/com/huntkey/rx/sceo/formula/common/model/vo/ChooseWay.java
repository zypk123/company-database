package com.huntkey.rx.sceo.formula.common.model.vo;

/**
 * @author  zhouyou on 2017/11/2.
 */
public class ChooseWay {
    private String id;
    private String content;
    private String code;
    private String mark;
    private String classId;

    public ChooseWay() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "ChooseWay{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", code='" + code + '\'' +
                ", mark='" + mark + '\'' +
                ", classId='"+classId+'\''+
                '}';
    }
}
