package com.huntkey.rx.sceo.common.service.common.enums;

/**
 * Created by zhaomj on 2017/4/28.
 */
public enum  EmailBodyFormat {

    HTML("HTML","html"),TEXT("TEXT","text");

    private String text;
    private String value;

    private EmailBodyFormat(String text, String value){
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
