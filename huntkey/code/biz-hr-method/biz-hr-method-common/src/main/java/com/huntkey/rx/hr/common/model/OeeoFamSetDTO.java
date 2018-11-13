package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by weijian on 2017/11/18.
 */
public class OeeoFamSetDTO {
  String id;
  String pid;
  String classname;
  @JSONField(name="oeeo_fam_addr")
  String oeeoFamAddr;//家庭住址
  @JSONField(name="oeeo_fam_comp")
  String oeeoFamComp;//工作单位
  @JSONField(name="oeeo_fam_name")
  String oeeoFamName;//姓名
  @JSONField(name="oeeo_fam_post")
  String oeeoFamPost;//职位
  @JSONField(name="oeeo_fam_rela")
  String oeeoFamRela;//关系
  @JSONField(name="oeeo_fam_tel")
  String oeeoFamTel;//联系电话
  @JSONField(name="oeeo_fam_seq")
  String oeeoFamSeq;//排序

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getClassname() {
    return classname;
  }

  public void setClassname(String classname) {
    this.classname = classname;
  }

  public String getOeeoFamAddr() {
    return oeeoFamAddr;
  }

  public void setOeeoFamAddr(String oeeoFamAddr) {
    this.oeeoFamAddr = oeeoFamAddr;
  }

  public String getOeeoFamComp() {
    return oeeoFamComp;
  }

  public void setOeeoFamComp(String oeeoFamComp) {
    this.oeeoFamComp = oeeoFamComp;
  }

  public String getOeeoFamName() {
    return oeeoFamName;
  }

  public void setOeeoFamName(String oeeoFamName) {
    this.oeeoFamName = oeeoFamName;
  }

  public String getOeeoFamPost() {
    return oeeoFamPost;
  }

  public void setOeeoFamPost(String oeeoFamPost) {
    this.oeeoFamPost = oeeoFamPost;
  }

  public String getOeeoFamRela() {
    return oeeoFamRela;
  }

  public void setOeeoFamRela(String oeeoFamRela) {
    this.oeeoFamRela = oeeoFamRela;
  }

  public String getOeeoFamTel() {
    return oeeoFamTel;
  }

  public void setOeeoFamTel(String oeeoFamTel) {
    this.oeeoFamTel = oeeoFamTel;
  }

  public String getOeeoFamSeq() {
    return oeeoFamSeq;
  }

  public void setOeeoFamSeq(String oeeoFamSeq) {
    this.oeeoFamSeq = oeeoFamSeq;
  }
}
