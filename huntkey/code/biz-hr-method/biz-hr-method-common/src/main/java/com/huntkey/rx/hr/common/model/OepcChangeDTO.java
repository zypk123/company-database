package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class OepcChangeDTO {
    @JSONField(name="id")
    String id;

    @JSONField(name="oepc_chang_set")
    List<OepcChangSetDTO> oepcChangSet;
    @JSONField(name = "list")
    ArrayList list;

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OepcChangSetDTO> getOepcChangSet() {
        return oepcChangSet;
    }

    public void setOepcChangSet(List<OepcChangSetDTO> oepcChangSet) {
        this.oepcChangSet= oepcChangSet;
    }
}
