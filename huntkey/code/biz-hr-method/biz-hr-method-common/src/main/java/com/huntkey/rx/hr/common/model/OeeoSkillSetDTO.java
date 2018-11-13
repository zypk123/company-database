package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by weijian on 2017/11/18.
 */
public class OeeoSkillSetDTO {
  String id;
  String pid;
  String classname;
  @JSONField(name="oeeo_mast_level")
  String oeeoMastLevel;//掌握水平
  @JSONField(name="oeeo_skill_field")
  String oeeoSkillField;//技能领域
  @JSONField(name="oeeo_skill_pro")
  String oeeoSkillPro;//专业技能
  @JSONField(name="oeeo_util_mon")
  String oeeoUtilMon;//应用时间
  @JSONField(name="oeeo_skill_seq")
  String oeeoSkillSeq;//排序
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

  public String getOeeoMastLevel() {
    return oeeoMastLevel;
  }

  public void setOeeoMastLevel(String oeeoMastLevel) {
    this.oeeoMastLevel = oeeoMastLevel;
  }

  public String getOeeoSkillField() {
    return oeeoSkillField;
  }

  public void setOeeoSkillField(String oeeoSkillField) {
    this.oeeoSkillField = oeeoSkillField;
  }

  public String getOeeoSkillPro() {
    return oeeoSkillPro;
  }

  public void setOeeoSkillPro(String oeeoSkillPro) {
    this.oeeoSkillPro = oeeoSkillPro;
  }

  public String getOeeoUtilMon() {
    return oeeoUtilMon;
  }

  public void setOeeoUtilMon(String oeeoUtilMon) {
    this.oeeoUtilMon = oeeoUtilMon;
  }

  public String getOeeoSkillSeq() {
    return oeeoSkillSeq;
  }

  public void setOeeoSkillSeq(String oeeoSkillSeq) {
    this.oeeoSkillSeq = oeeoSkillSeq;
  }
}
