package com.huntkey.rx.sceo.formula.provider.audit.service.impl;

import com.alibaba.fastjson.JSON;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.model.AuditRoles;
import com.huntkey.rx.sceo.formula.common.model.vo.*;
import com.huntkey.rx.sceo.formula.provider.audit.dao.AuditRolesMapper;
import com.huntkey.rx.sceo.formula.provider.audit.feign.EdmInterface;
import com.huntkey.rx.sceo.formula.provider.audit.feign.EdmModelerClientInterface;
import com.huntkey.rx.sceo.formula.provider.audit.service.AuditRolesSetService;
import com.huntkey.rx.sceo.formula.provider.engine.feign.DataSharingService;
import com.huntkey.rx.sceo.formula.provider.utils.ORMUtils;
import com.huntkey.rx.sceo.formula.provider.utils.RestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhouyou on 2017/10/28.
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class AuditRolesSetServiceImpl<M extends AuditRoles> extends BaseService<M> implements AuditRolesSetService {

    private static Logger log = LoggerFactory.getLogger(AuditRolesSetServiceImpl.class);

    /**
     * 设置日期格式
     */
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final String ORM_URL = "http://SERVICECENTER-PROVIDER/servicecenter/find";

    @Autowired
    private EdmModelerClientInterface edmModelerClientInterfaceAutowired;

    private static EdmModelerClientInterface edmModeler;

    @Autowired
    DataSharingService dataSharingService;


    @Autowired
    private EdmInterface edmInterfaceAutowired;

    @Autowired
    private static EdmInterface edmInterface;

    @Autowired
    private AuditRolesMapper auditRolesMapper;

    @Autowired
    AuditRolesSetServiceImpl auditRolesSetServiceImpl;

    @PostConstruct
    public void init() {

        edmInterface = edmInterfaceAutowired;
        edmModeler = edmModelerClientInterfaceAutowired;

    }

    /**
     * @return 获取方式列表
     * @desc 获取审核方式列表
     */
    @Override
    public List getmode(String classId) {
        Result result;
        List<ChooseWay> modelist = new ArrayList<ChooseWay>();
        ChooseWay choose1 = new ChooseWay();
        choose1.setId("521cf89a936f4c2380fd44dfed5d58f6");
        choose1.setContent("指定岗位");
        choose1.setMark("1");
        choose1.setClassId("");
        ChooseWay choose2 = new ChooseWay();
        choose2.setId("5bf03bcc690c4e78941a4809995f2684");
        choose2.setContent("指定部门");
        choose2.setMark("2");
        choose2.setClassId("");
        modelist.add(choose1);
        modelist.add(choose2);
        result = edmModeler.getPsClass(classId);
        List<Map<String, String>> datas = (List<Map<String, String>>) result.getData();
        if (datas == null) {
            return modelist;
        }
        for (Map<String, String> data : datas) {

            if ("2d6a2e40abcb11e78bba005056bc4879".equals(data.get("edmpDataType"))) {
                ChooseWay choosePost = new ChooseWay();
                String edmpParentId = data.get("edmpParentId");
                String edmpName = data.get("edmpName");
                String edmpCode = data.get("edmpCode");
                Map<String, String> map = getParentProp(edmpName, edmpCode, edmpParentId);
                choosePost.setId(data.get("id"));
                //岗位
                choosePost.setContent(map.get("edmpName"));
                //编码
                choosePost.setCode(map.get("edmpCode"));
                //标记
                choosePost.setMark("3");
                choosePost.setClassId(data.get("edmpEdmcId"));
                modelist.add(choosePost);
            }
            //如果数据类型是部门
            if ("2d6c2beeabcb11e78bba005056bc4879".equals(data.get("edmpDataType"))) {
                ChooseWay chooseDept = new ChooseWay();
                String edmpParentId = data.get("edmpParentId");
                String edmpName = data.get("edmpName");
                String edmpCode = data.get("edmpCode");
                Map<String, String> map = getParentProp(edmpName, edmpCode, edmpParentId);
                chooseDept.setId(data.get("id"));
                //部门
                chooseDept.setContent(map.get("edmpName"));
                //编码
                chooseDept.setCode(map.get("edmpCode"));
                //标记
                chooseDept.setMark("4");
                chooseDept.setClassId(data.get("edmpEdmcId"));
                modelist.add(chooseDept);
            }
        }
        return modelist;

    }


    /**
     * 递归查询属性集名称和code
     *
     * @param edmpName
     * @param edmpCode
     * @param edmpParentId
     * @return
     */
    private Map<String, String> getParentProp(String edmpName, String edmpCode, String edmpParentId) {
        Map map = new HashMap();
        if (!StringUtil.isNullOrEmpty(edmpParentId)) {
            Result propResult;
            propResult = edmModeler.getEdmProp(edmpParentId);
            Map<String, String> propDatas = (Map<String, String>) propResult.getData();
            edmpName = propDatas.get("edmpName") + "/" + edmpName;
            edmpCode = propDatas.get("edmpCode") + "." + edmpCode;
            if (!StringUtil.isNullOrEmpty(propDatas.get("edmpParentId"))) {
                getParentProp(edmpName, edmpCode, propDatas.get("edmpParentId"));
            } else {
                map.put("edmpName", edmpName);
                map.put("edmpCode", edmpCode);
            }
        } else {
            map.put("edmpName", edmpName);
            map.put("edmpCode", edmpCode);
        }
        return map;
    }


    /**
     * @return 汇报岗位集合
     * @desc 根据选定指定岗位，获取汇报岗位集合
     */
    @Override
    public List<Jobposition> getRootReportPosition() {
        //查询汇报岗位字段[rpos_ppost]
        List<Jobposition> list1 = new ArrayList<>();
        //======
        ArrayList<Condition> list = new ArrayList<>();
        //======
        String scelect1 = "rpos_ppost";
        String scelect2 = "id";
        String scelect3 = "rpos_name";
        String select71 = "rpos_dept";
        ArrayList<String> colum = new ArrayList<String>();
        colum.add(scelect1);
        colum.add(scelect2);
        colum.add(scelect3);
        colum.add(select71);
        //=====
        Condition condition1 = new Condition();
        condition1.setAttr("rpos_beg");
        condition1.setOperator("<");
        condition1.setValue(df.format(new Date()));
        Condition condition2 = new Condition();
        condition2.setAttr("rpos_end");
        condition2.setOperator(">");
        condition2.setValue(df.format(new Date()));
        list.add(condition1);
        list.add(condition2);
        Condition condition21 = new Condition();
        condition21.setAttr("rpos_ppost");
        condition21.setOperator("is");
        condition21.setValue("null");
        list.add(condition21);
        //=====
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        searchObject.setColumns(colum);
        searchObject.setConditions(list);
        paramObject.setEdmName("jobposition");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        //返回的是一个result
        Result resources = edmInterface.getResources(s);

        //解析result中的值
        List<Jobposition> positionList = new ArrayList<Jobposition>();

        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        if (data.size() == 0 || data == null) {
            return list1;
        }
        List<Map<String, String>> datasets = data.get("dataset");

        //查询两次第二次失效时间在开始与null之间的
        ArrayList<Condition> list3 = new ArrayList<>();
        //======
        String scelect4 = "rpos_ppost";
        String scelect5 = "id";
        String scelect6 = "rpos_name";
        String select7 = "rpos_dept";
        ArrayList<String> colum1 = new ArrayList<String>();
        colum1.add(scelect4);
        colum1.add(scelect5);
        colum1.add(scelect6);
        colum1.add(select7);
        //=====
        Condition condition3 = new Condition();
        condition3.setAttr("rpos_beg");
        condition3.setOperator("<");
        condition3.setValue(df.format(new Date()));
        Condition condition4 = new Condition();
        condition4.setAttr("rpos_end");
        condition4.setOperator("is");
        condition4.setValue("null");
        Condition condition41 = new Condition();
        condition41.setAttr("rpos_ppost");
        condition41.setOperator("is");
        condition41.setValue("null");
        list3.add(condition41);
        list3.add(condition3);
        list3.add(condition4);
        //=====
        ParamObject paramObject1 = new ParamObject();
        SearchObject searchObject1 = new SearchObject();
        searchObject1.setColumns(colum1);
        searchObject1.setConditions(list3);
        paramObject1.setEdmName("jobposition");
        paramObject1.setSearch(searchObject1);
        String s1 = JSON.toJSONString(paramObject1);
        //返回的是一个result
        Result resources1 = edmInterface.getResources(s1);

        Map<String, List<Map<String, String>>> data1 = (Map<String, List<Map<String, String>>>) resources1.getData();
        if (data1.size() == 0 || data1 == null) {
            return list1;
        }
        List<Map<String, String>> datasets1 = data1.get("dataset");

        datasets.addAll(datasets1);
        List<String> dept = getDept();
        //第二次查询在有效时间范围内 生效时间与null之间
        for (Map<String, String> dataset : datasets) {
            if (dept.contains(dataset.get("rpos_dept"))) {
                Jobposition j = new Jobposition();
                j.setJobId(dataset.get("id"));
                j.setRposPpost(null);
                j.setRposName(dataset.get("rpos_name"));
                positionList.add(j);
            }
        }
        //返回资源列表

        List<Jobposition> ppost = getChildPpost();
        Iterator<Jobposition> iterator = ppost.iterator();
        while (iterator.hasNext()) {
            Jobposition next = iterator.next();
            if (next.getJobId().equals(next.getRposPpost())) {
                throw new RuntimeException("岗位:" + next.getJobId() + ";存在异常，汇报岗位为本岗位。");
            }
        }
        getPositionTree(positionList, ppost);
        return positionList;
    }

    private List<String> getDept() {
        ArrayList<Condition> list2 = new ArrayList<>();
        String scelect2 = "pid";
        ArrayList<String> colum = new ArrayList<String>();
        colum.add(scelect2);
        Condition condition1 = new Condition();
        condition1.setAttr("mdep_beg_his");
        condition1.setOperator("<");
        condition1.setValue(df.format(new Date()));
        Condition condition2 = new Condition();
        condition2.setAttr("mdep_end_his");
        condition2.setOperator(">");
        condition2.setValue(df.format(new Date()));
        list2.add(condition1);
        list2.add(condition2);
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        searchObject.setColumns(colum);
        searchObject.setConditions(list2);
        paramObject.setEdmName("depttree.mdep_chag_set");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        //返回的是一个result
        Result resources = edmInterface.getResources(s);
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        List<Map<String, String>> datasets = new ArrayList<>();
        if (data != null && data.size() != 0) {
            datasets = data.get("dataset");
        }
        ArrayList<Condition> list3 = new ArrayList<>();
        String scelect5 = "pid";
        ArrayList<String> colum1 = new ArrayList<String>();
        colum1.add(scelect5);
        Condition condition3 = new Condition();
        condition3.setAttr("mdep_beg_his");
        condition3.setOperator("<");
        condition3.setValue(df.format(new Date()));
        Condition condition4 = new Condition();
        condition4.setAttr("mdep_end_his");
        condition4.setOperator("is");
        condition4.setValue("null");
        list3.add(condition3);
        list3.add(condition4);
        ParamObject paramObject1 = new ParamObject();
        SearchObject searchObject1 = new SearchObject();
        searchObject1.setColumns(colum1);
        searchObject1.setConditions(list3);
        paramObject1.setEdmName("depttree.mdep_chag_set");
        paramObject1.setSearch(searchObject1);
        String s1 = JSON.toJSONString(paramObject1);
        //返回的是一个result
        Result resources1 = edmInterface.getResources(s1);
        Map<String, List<Map<String, String>>> data2 = (Map<String, List<Map<String, String>>>) resources1.getData();
        List<Map<String, String>> datasets2 = new ArrayList<>();
        if (data2 != null && data2.size() != 0) {
            datasets2 = data2.get("dataset");
        }
        datasets.addAll(datasets2);
        ArrayList<String> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(datasets)) {
            return list;
        }
        for (Map<String, String> dataset : datasets) {
            list.add(dataset.get("pid"));
        }
        return list;
    }

    private List<Jobposition> getChildPpost() {

        List<Jobposition> list1 = new ArrayList<>();
        //======
        ArrayList<Condition> list2 = new ArrayList<>();
        //======
        String scelect1 = "rpos_ppost";
        String scelect2 = "id";
        String scelect3 = "rpos_name";
        String select7 = "rpos_dept";
        ArrayList<String> colum = new ArrayList<String>();
        colum.add(scelect1);
        colum.add(scelect2);
        colum.add(scelect3);
        colum.add(select7);
        //=====
        Condition condition1 = new Condition();
        condition1.setAttr("rpos_beg");
        condition1.setOperator("<");
        condition1.setValue(df.format(new Date()));
        Condition condition2 = new Condition();
        condition2.setAttr("rpos_end");
        condition2.setOperator(">");
        condition2.setValue(df.format(new Date()));
        list2.add(condition1);
        list2.add(condition2);
        Condition condition21 = new Condition();
        condition21.setAttr("rpos_ppost");
        condition21.setOperator("!is");
        condition21.setValue("null");
        list2.add(condition21);
        //=====
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        searchObject.setColumns(colum);
        searchObject.setConditions(list2);
        paramObject.setEdmName("jobposition");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        //返回的是一个result
        Result resources = edmInterface.getResources(s);

        //解析result中的值
        List<Jobposition> positionList = new ArrayList<Jobposition>();

        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        if (data.size() == 0 || data == null) {
            return list1;
        }
        List<Map<String, String>> datasets = data.get("dataset");

        //查询两次第二次失效时间在开始与null之间的
        ArrayList<Condition> list3 = new ArrayList<>();
        //======
        String scelect4 = "rpos_ppost";
        String scelect5 = "id";
        String scelect6 = "rpos_name";
        String select71 = "rpos_dept";
        ArrayList<String> colum1 = new ArrayList<String>();
        colum1.add(scelect4);
        colum1.add(scelect5);
        colum1.add(scelect6);
        colum1.add(select71);
        //=====
        Condition condition3 = new Condition();
        condition3.setAttr("rpos_beg");
        condition3.setOperator("<");
        condition3.setValue(df.format(new Date()));
        Condition condition4 = new Condition();
        condition4.setAttr("rpos_end");
        condition4.setOperator("is");
        condition4.setValue("null");
        Condition condition41 = new Condition();
        condition41.setAttr("rpos_ppost");
        condition41.setOperator("!is");
        condition41.setValue("null");
        list3.add(condition41);
        list3.add(condition3);
        list3.add(condition4);
        //=====
        ParamObject paramObject1 = new ParamObject();
        SearchObject searchObject1 = new SearchObject();
        searchObject1.setColumns(colum1);
        searchObject1.setConditions(list3);
        paramObject1.setEdmName("jobposition");
        paramObject1.setSearch(searchObject1);
        String s1 = JSON.toJSONString(paramObject1);
        //返回的是一个result
        Result resources1 = edmInterface.getResources(s1);

        Map<String, List<Map<String, String>>> data1 = (Map<String, List<Map<String, String>>>) resources1.getData();
        if (data1.size() == 0 || data1 == null) {
            return list1;
        }
        List<Map<String, String>> datasets1 = data1.get("dataset");

        datasets.addAll(datasets1);

        List<String> dept = getDept();
        //第二次查询在有效时间范围内 生效时间与null之间
        for (Map<String, String> dataset : datasets) {
            if (dept.contains(dataset.get("rpos_dept"))) {
                Jobposition j = new Jobposition();
                j.setJobId(dataset.get("id"));
                j.setRposPpost(dataset.get("rpos_ppost"));
                j.setRposName(dataset.get("rpos_name"));
                positionList.add(j);
            }
        }
        //返回资源列表
        return positionList;
    }


    /**
     * @param list
     * @return
     * @Desc 根据返回的汇报岗list 拼接成岗位树结构
     */
    private void getPositionTree(List<Jobposition> list, List<Jobposition> ppost) {
        if (null != list && list.size() > 0) {
            for (Jobposition jobposition : list) {
                //获取所有有效的子节点
                ArrayList<Jobposition> list1 = new ArrayList<>();
                for (Jobposition jobposition1 : ppost) {
                    if (jobposition.getJobId().equals(jobposition1.getRposPpost())) {
                        list1.add(jobposition1);
                    }
                }
                if (CollectionUtils.isEmpty(list1)) {
                    jobposition.setChildPosition(null);
                    continue;
                }
                jobposition.setChildPosition(list1);
                getPositionTree(list1, ppost);

            }

        }
    }

    /**
     * @param jobGrad   岗位级别
     * @param operator1 操作符1
     * @param value1    值1
     * @param deptGrad  部门级别
     * @param operator2 操作符2
     * @param value2    值2
     * @return 通过岗位级别条件 获取到对应的所属部门
     * @DESC 根据传入条件操作符以及内容 获取到所属部门id
     */
    @Override
    public List getRposdept(String jobGrad, String operator1, String value1,
                            String deptGrad, String operator2, String value2) {
        jobGrad = "rpos_grade";

        //查询所属部门id
        String rpos_dept = "rpos_dept";
        //汇报岗位
        ArrayList<String> colum = new ArrayList<String>();
        //查询汇报岗位value值
        colum.add(rpos_dept);
        //汇报岗位
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        Condition condition1 = new Condition();
        //条件根据岗级字段
        condition1.setAttr(jobGrad);
        condition1.setOperator(operator1);
        condition1.setValue(value1);
        ArrayList<Condition> list = new ArrayList<Condition>();
        list.add(condition1);
        searchObject.setColumns(colum);
        searchObject.setConditions(list);
        paramObject.setEdmName("jobposition");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        Result resources = edmInterface.getResources(s);
        //解析result中的值
        List<String> rposPpostList = new ArrayList<String>();
        //满足岗位<03 并且 部门>05的id  获取到对应的id信息
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        List<Map<String, String>> datasets = new ArrayList<>();
        if (null != data) {
            datasets = data.get("dataset");
        }
        for (Map<String, String> dataset : datasets) {
            //根据岗位级别 >03  获取到所属部门List  根据所属部门List查找到对应的部门 按照部门级别
            String rpos_dept_id = getSatisfy_Dept_Id(dataset.get("rpos_dept"), deptGrad, operator2, value2);
            //=================================如果返回为空做处理
            if (getSatisfyRposPpost(rpos_dept_id) == null) {
                continue;
            }
            rposPpostList.add(getSatisfyRposPpost(rpos_dept_id));
        }

        return rposPpostList;
    }

    /**
     * @param rpos_dept [所属部门编号集合] 关联到具体的部门
     * @param deptGrad  [部门级别]
     * @param operator2 [操作符号]
     * @param value2    [条件值]
     * @return 获取部门级别条件限制的部门编号
     */
    private String getSatisfy_Dept_Id(String rpos_dept, String deptGrad, String operator2, String value2) {
        String scelect = "id";
        ArrayList<String> colum = new ArrayList<String>();
        colum.add(scelect);
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        Condition condition1 = new Condition();
        condition1.setAttr("id");
        condition1.setOperator("=");
        condition1.setValue(rpos_dept);
        Condition condition2 = new Condition();
        condition2.setAttr(deptGrad);
        condition2.setOperator(operator2);
        condition2.setValue(value2);
        ArrayList<Condition> list = new ArrayList<Condition>();
        list.add(condition1);
        list.add(condition2);
        searchObject.setColumns(colum);
        searchObject.setConditions(list);
        paramObject.setEdmName("depttree");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        Result resources = edmInterface.getResources(s);
        //解析result中的值 满足部门级别条件限制的id解析出来
        List<String> satisfyIdList = new ArrayList<String>();
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        if (data == null) {
            return null;
        }
        List<Map<String, String>> datasets = data.get("dataset");
        for (Map<String, String> dataset : datasets) {

            satisfyIdList.add(dataset.get("id"));
        }
        if (satisfyIdList.size() == 0) {
            return null;
        }
        return satisfyIdList.get(0);
    }

    /**
     * @param rposDeptId [满足条件所属部门id]
     * @return 满足条件的汇报岗位
     */
    private String getSatisfyRposPpost(String rposDeptId) {
        String scelect = "rpos_ppost";
        ArrayList<String> colum = new ArrayList<String>();
        colum.add(scelect);
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        Condition condition = new Condition();
        condition.setAttr("rpos_dept");
        condition.setOperator("=");
        condition.setValue(rposDeptId);
        ArrayList<Condition> list = new ArrayList<Condition>();
        list.add(condition);
        searchObject.setColumns(colum);
        searchObject.setConditions(list);
        paramObject.setEdmName("jobposition");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        Result resources = edmInterface.getResources(s);
        //解析result中的值 满足部门级别条件限制的id解析出来
        List<String> rposDeptList = new ArrayList<String>();
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        if (data == null) {
            return null;
        }
        List<Map<String, String>> datasets = data.get("dataset");
        for (Map<String, String> dataset : datasets) {
            rposDeptList.add(dataset.get("rpos_ppost"));
        }
        if (rposDeptList.size() == 0 || rposDeptList == null) {
            return null;
        }
        return rposDeptList.get(0);
    }


    /**
     * @param rposDeptId [满足条件所属部门id]
     * @return 满足条件的部门树结构
     */
    public String getSatisfyDeptTree(String rposDeptId) {
        //查询满足条件的部门树字段 需要的字段   mdep_lvl_code
        String scelect = "rpos_ppost";
        ArrayList<String> colum = new ArrayList<String>();
        colum.add(scelect);
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        Condition condition = new Condition();
        //根据哪个字段 去查 id
        condition.setAttr("id");
        condition.setOperator("=");
        condition.setValue(rposDeptId);
        ArrayList<Condition> list = new ArrayList<Condition>();
        list.add(condition);
        searchObject.setColumns(colum);
        searchObject.setConditions(list);
        paramObject.setEdmName("depttree");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        Result resources = edmInterface.getResources(s);
        //解析result中的值 满足部门级别条件限制的id解析出来
        List<String> rposDeptList = new ArrayList<String>();
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        if (data == null) {
            return null;
        }
        List<Map<String, String>> datasets = data.get("dataset");
        for (Map<String, String> dataset : datasets) {
            rposDeptList.add(dataset.get("rpos_ppost"));
        }
        if (rposDeptList.size() == 0 || rposDeptList == null) {
            return null;
        }
        return rposDeptList.get(0);
    }


    @Override
    public List<DeptTree> getOnlyDeptTree() {

        ArrayList<String> colum = new ArrayList<String>();
        ArrayList<Condition> list = new ArrayList<>();
        Condition condition1 = new Condition();
        condition1.setAttr("mdep_beg_his");
        condition1.setOperator("<");
        condition1.setValue(df.format(new Date()));
        Condition condition2 = new Condition();
        condition2.setAttr("mdep_end_his");
        condition2.setOperator(">");
        condition2.setValue(df.format(new Date()));
        Condition condition3 = new Condition();
        condition3.setAttr("classname");
        condition3.setOperator("=");
        condition3.setValue("depttree");
        list.add(condition1);
        list.add(condition2);
        list.add(condition3);
        String scelect1 = "mdep_lvl_his";
        colum.add(scelect1);
        String scelect2 = "mdep_name_his";
        colum.add(scelect2);
        String scelect3 = "pid";
        colum.add(scelect3);
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        searchObject.setColumns(colum);
        searchObject.setConditions(list);
        paramObject.setEdmName("depttree.mdep_chag_set");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        Result resources = edmInterface.getResources(s);
        log.info(s.toString() + "拼接条件4");
        //解析result中的值 满足部门级别条件限制的id解析出来
        List<DeptTree> deptTreeList = new ArrayList<DeptTree>();
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        if (data == null) {
            return null;
        }
        List<Map<String, String>> datasets = data.get("dataset");


        //第二次查询在有效时间范围内 生效时间与null之间

        ArrayList<String> colum1 = new ArrayList<String>();
        ArrayList<Condition> list2 = new ArrayList<>();
        Condition condition4 = new Condition();
        condition4.setAttr("mdep_beg_his");
        condition4.setOperator("<");
        condition4.setValue(df.format(new Date()));
        Condition condition5 = new Condition();
        condition5.setAttr("mdep_end_his");
        condition5.setOperator("is");
        condition5.setValue("null");
        Condition condition6 = new Condition();
        condition6.setAttr("classname");
        condition6.setOperator("=");
        condition6.setValue("depttree");
        list2.add(condition4);
        list2.add(condition5);
        list2.add(condition6);
        String scelect4 = "mdep_lvl_his";
        colum1.add(scelect4);
        String scelect5 = "mdep_name_his";
        colum1.add(scelect5);
        String scelect6 = "pid";
        colum1.add(scelect6);
        ParamObject paramObject1 = new ParamObject();
        SearchObject searchObject1 = new SearchObject();
        searchObject1.setColumns(colum1);
        searchObject1.setConditions(list2);
        paramObject1.setEdmName("depttree.mdep_chag_set");
        paramObject1.setSearch(searchObject1);
        String s1 = JSON.toJSONString(paramObject1);
        Result resources1 = edmInterface.getResources(s1);

        //解析result中的值 满足部门级别条件限制的id解析出来
        Map<String, List<Map<String, String>>> data1 = (Map<String, List<Map<String, String>>>) resources1.getData();
        if (data1 == null) {
            return null;
        }
        List<Map<String, String>> datasets1 = data1.get("dataset");

        datasets.addAll(datasets1);

        //第二次查询在有效时间范围内 生效时间与null之间
        for (Map<String, String> dataset : datasets) {
            DeptTree deptTreeTemp = new DeptTree();
            deptTreeTemp.setMoniLvlCode(dataset.get("mdep_lvl_his"));
            deptTreeTemp.setMoniNodeName(dataset.get("mdep_name_his"));
            deptTreeTemp.setDeptId(dataset.get("pid"));
            deptTreeList.add(deptTreeTemp);
        }

        if (deptTreeList.size() == 0) {
            return null;
        }

        //构造部门树
        List<Integer> indexs = new ArrayList<Integer>();
        for (DeptTree deptTree : deptTreeList) {
            if (deptTree == null || deptTree.getMoniLvlCode() == null) {
                continue;
            }
            List<DeptTree> tempList = new ArrayList<DeptTree>();

            for (DeptTree tree : deptTreeList) {
                if (tree == null || tree.getMoniLvlCode() == null) {
                    continue;
                }
                String code = tree.getMoniLvlCode();
                if (code.length() <= 4) {
                    continue;
                }
                if (deptTree.getMoniLvlCode().equals(code.substring(0, code.length() - 4))) {
                    indexs.add(deptTreeList.indexOf(tree));
                    tempList.add(tree);
                }
            }
            if (tempList == null || tempList.size() == 0) {
                deptTree.setChildDept(null);
            } else {
                deptTree.setChildDept(tempList);
            }
        }
        //修改前取出部门树 若根节点无效 查不出具体的部门树
        List<DeptTree> list1 = new ArrayList<DeptTree>();
        for (DeptTree deptTree : deptTreeList) {
            //对于长度大于1位的树过滤 取得部门树结果
            if (deptTree == null || deptTree.getMoniLvlCode().length() > 4) {
                continue;
            }
            list1.add(deptTree);
        }
        return list1;
    }


    @Override
    public List getDeptAllPeople(String deptId) {
        String scelect = "staf002";
        ArrayList<String> colum = new ArrayList<String>();
        colum.add(scelect);
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        Condition condition = new Condition();
        //员工表中的部门编号
        condition.setAttr("部门字段");
        condition.setOperator("=");
        condition.setValue(deptId);
        ArrayList<Condition> list = new ArrayList<Condition>();
        list.add(condition);
        searchObject.setColumns(colum);
        searchObject.setConditions(list);
        paramObject.setEdmName("staff");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        Result resources = edmInterface.getResources(s);
        List<String> staffNameList = new ArrayList<String>();
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        if (data == null) {
            return null;
        }
        List<Map<String, String>> datasets = data.get("dataset");
        for (Map<String, String> dataset : datasets) {
            staffNameList.add(dataset.get("staf002"));
        }
        if (staffNameList.size() == 0 || staffNameList == null) {
            return null;
        }
        return staffNameList;
    }

    @Override
    public String getDeptMatter(String deptId) {
        //查询主责岗位
        String scelect = "staf121";
        ArrayList<String> colum = new ArrayList<String>();
        colum.add(scelect);
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        Condition condition = new Condition();
        //员工表中的部门编号
        condition.setAttr("id");
        condition.setOperator("=");
        condition.setValue(deptId);
        ArrayList<Condition> list = new ArrayList<Condition>();
        list.add(condition);
        searchObject.setColumns(colum);
        searchObject.setConditions(list);
        paramObject.setEdmName("depttree");
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        Result resources = edmInterface.getResources(s);
        List<String> matterList = new ArrayList<String>();
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        if (data == null) {
            return null;
        }
        List<Map<String, String>> datasets = data.get("dataset");
        for (Map<String, String> dataset : datasets) {
            matterList.add(dataset.get("staf121"));
        }
        if (matterList.size() == 0 || matterList == null) {
            return null;
        }
        return matterList.get(0);
    }

    //流浪所写的部门内容//


    @Override
    public Boolean isEffectiveDept(String deptId) {
        List<ConditionsVo> conditionsDeptClass = new ArrayList<ConditionsVo>();
        String[] columns = {"id"};
        ConditionsVo condDept1 = new ConditionsVo();
        ConditionsVo condDept2 = new ConditionsVo();
        ConditionsVo condDept3 = new ConditionsVo();
        condDept1.setAttr("pid");
        condDept1.setOperator("=");
        condDept1.setValue(deptId);
        condDept2.setAttr("mdep_beg_his");
        condDept2.setOperator("<");
        condDept2.setValue(df.format(new Date()));
        condDept3.setAttr("mdep_end_his");
        condDept3.setOperator(">");
        condDept3.setValue(df.format(new Date()));
        conditionsDeptClass.add(condDept1);
        conditionsDeptClass.add(condDept2);
        conditionsDeptClass.add(condDept3);
        EsHbaseCriteriaVo queryDeptClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns, conditionsDeptClass, null);
        String restDeptClass = RestUtils.doPost(ORM_URL, queryDeptClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restDeptClass);
        List<ConditionsVo> conditionsDeptClass2 = new ArrayList<ConditionsVo>();
        String[] columns2 = {"id"};
        ConditionsVo condDept4 = new ConditionsVo();
        ConditionsVo condDept5 = new ConditionsVo();
        ConditionsVo condDept6 = new ConditionsVo();
        condDept4.setAttr("pid");
        condDept4.setOperator("=");
        condDept4.setValue(deptId);
        condDept5.setAttr("mdep_beg_his");
        condDept5.setOperator("<");
        condDept5.setValue(df.format(new Date()));
        condDept6.setAttr("mdep_end_his");
        condDept6.setOperator("is");
        condDept6.setValue("null");
        conditionsDeptClass2.add(condDept4);
        conditionsDeptClass2.add(condDept5);
        conditionsDeptClass2.add(condDept6);
        EsHbaseCriteriaVo queryDeptClass2 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns2, conditionsDeptClass2, null);
        String restDeptClass2 = RestUtils.doPost(ORM_URL, queryDeptClass2);
        List<Map<String, Object>> dataSet2 = ORMUtils.getDataSet(restDeptClass2);
        dataSet.addAll(dataSet2);
        if (dataSet.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Result getSuper(String id, String level, String pGrade, String poperater, String dGrade, String doperater) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        boolean cher = true;
        String deptId = id;
        if (!isEffectiveDept(deptId)) {
            result.setErrMsg("当前部门无效,id: " + deptId);
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        List superior;
        List directorSet;
        //获取当前部门等级1,2,
        String office = office(id);
        int i = office.lastIndexOf(',');
        if ("上级主责岗位".equals(level)) {
            int i1 = office.lastIndexOf(',', i - 1);
            if (i1 == -1) {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("上级部门不存在");
                return result;
            }
            office = office.substring(0, i1 + 1);
            deptId = getId(office);
            if (!isEffectiveDept(deptId)) {
                result.setErrMsg("部门无效,id: " + deptId);
                result.setRetCode(Result.RECODE_ERROR);
                return result;
            }
        } else if ("上上级主责岗位".equals(level)) {
            int i1 = office.lastIndexOf(',', i - 1);
            if (i1 == -1) {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("上级部门不存在");
                return result;
            }
            office = office.substring(0, i1 + 1);
            deptId = getId(office);
            if (!isEffectiveDept(deptId)) {
                result.setErrMsg("部门无效,id: " + deptId);
                result.setRetCode(Result.RECODE_ERROR);
                return result;
            }
            int i2 = office.lastIndexOf(',', i1 - 1);
            if (i2 == -1) {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("上上级部门不存在");
                return result;
            }
            office = office.substring(0, i2 + 1);
            deptId = getId(office);
            if (!isEffectiveDept(deptId)) {
                result.setErrMsg("部门无效,id: " + deptId);
                result.setRetCode(Result.RECODE_ERROR);
                return result;
            }
        } else {
            office = office.substring(0, i + 1);
            deptId = getId(office);
        }
        Set<Object> objects = new HashSet<>();
        if (inTheLevel(deptId, dGrade, doperater)) {
            //主管人岗位
            superior = getsupreior(deptId, pGrade, doperater, poperater, dGrade);
            //获取主管人岗位集合
            directorSet = getDirectorSet(deptId, poperater, doperater, dGrade, pGrade);
            objects.addAll(superior);
            objects.addAll(directorSet);
            if (objects.size() != 0) {
                result.setData(objects);
                return result;
            }
        }
        while (cher) {
            int i3 = office.lastIndexOf(',');
            int i4 = office.lastIndexOf(',', i3 - 1);
            if (i4 == -1) {
                result.setErrMsg("上级部门不存在");
                result.setRetCode(Result.RECODE_ERROR);
                break;
            }
            //获得上级主管人的层级
            office = office.substring(0, i4 + 1);
            //   主管人id
            deptId = getId(office);
            if (isEffectiveDept(deptId)) {
                if (inTheLevel(deptId, dGrade, doperater)) {
                    superior = getsupreior(deptId, pGrade, doperater, poperater, dGrade);
                    //获取主管人岗位集合
                    directorSet = getDirectorSet(deptId, poperater, doperater, dGrade, pGrade);
                    objects.addAll(superior);
                    objects.addAll(directorSet);
                }
            } else {
                result.setErrMsg("部门无效,id: " + deptId);
                result.setRetCode(Result.RECODE_ERROR);
                break;
            }
            if (objects.size() != 0) {
                result.setData(objects);
                break;
            }
        }
        return result;
    }

    @Override
    public String office(String id) {
        List<ConditionsVo> conditionsClass = new ArrayList<ConditionsVo>();
        ConditionsVo condClass1 = new ConditionsVo();
        ConditionsVo condClass2 = new ConditionsVo();
        ConditionsVo condClass3 = new ConditionsVo();
        String[] columns = {"mdep_lvl_his"};
        condClass1.setAttr("pid");
        condClass1.setOperator("=");
        condClass1.setValue(id);
        condClass2.setAttr("mdep_beg_his");
        condClass2.setOperator("<");
        condClass2.setValue(df.format(new Date()));
        condClass3.setAttr("mdep_end_his");
        condClass3.setOperator(">");
        condClass3.setValue(df.format(new Date()));
        conditionsClass.add(condClass1);
        conditionsClass.add(condClass2);
        conditionsClass.add(condClass3);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns, conditionsClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        List<ConditionsVo> conditionsIsNull = new ArrayList<ConditionsVo>();
        ConditionsVo condIsNull = new ConditionsVo();
        ConditionsVo condIsNull2 = new ConditionsVo();
        ConditionsVo condIsNull3 = new ConditionsVo();
        String[] columns1 = {"mdep_lvl_his"};
        condIsNull.setAttr("pid");
        condIsNull.setOperator("=");
        condIsNull.setValue(id);
        condIsNull2.setAttr("mdep_beg_his");
        condIsNull2.setOperator("<");
        condIsNull2.setValue(df.format(new Date()));
        condIsNull3.setAttr("mdep_end_his");
        condIsNull3.setOperator("is");
        condIsNull3.setValue("null");
        conditionsIsNull.add(condIsNull);
        conditionsIsNull.add(condIsNull2);
        conditionsIsNull.add(condIsNull3);
        EsHbaseCriteriaVo queryIsNull = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns1, conditionsIsNull, null);
        String restIsNull = RestUtils.doPost(ORM_URL, queryIsNull);
        List<Map<String, Object>> dataIsNull = ORMUtils.getDataSet(restIsNull);
        if (dataIsNull.size() != 0) {
            dataSet.addAll(dataIsNull);
        }
        Object mdepNumLvl = null;
        if (dataSet.size() != 0) {
            for (Map<String, Object> map : dataSet) {
                mdepNumLvl = map.get("mdep_lvl_his");
            }
        }
        return String.valueOf(mdepNumLvl);
    }

    @Override
    public List getDepartmentSuperior(String department, String level, String pGrade, String doperater, String poperater, String dGrade) {
        List<Object> list = new ArrayList();
        String designatedpost = office(department);
        int i = designatedpost.lastIndexOf(',');
        String superiorlevel = null;
        if ("上级主责岗位".equals(level)) {
            int i1 = designatedpost.lastIndexOf(',', i - 1);
            if (i1 == -1) {
                return list;
            }
            superiorlevel = designatedpost.substring(0, i1 + 1);
        } else if ("上上级主责岗位".equals(level)) {
            int i1 = designatedpost.lastIndexOf(',', i - 1);
            if (i1 == -1) {
                return list;
            }
            int i2 = designatedpost.lastIndexOf(',', i1 - 1);
            if (i2 == -1) {
                return list;
            }
            superiorlevel = designatedpost.substring(0, i2 + 1);
        } else {
            superiorlevel = designatedpost.substring(0, i + 1);
        }
        List<ConditionsVo> conditionsSetClass = new ArrayList();
        ConditionsVo conSetClass1 = new ConditionsVo();
        ConditionsVo conSetClass2 = new ConditionsVo();
        ConditionsVo conSetClass3 = new ConditionsVo();
        String[] columnsSet = {"pid"};
        conSetClass1.setAttr("mdep_lvl_his");
        conSetClass1.setOperator("=");
        conSetClass1.setValue(superiorlevel);
        conSetClass2.setAttr("mdep_beg_his");
        conSetClass2.setOperator("<");
        conSetClass2.setValue(df.format(new Date()));
        conSetClass3.setAttr("mdep_end_his");
        conSetClass3.setOperator(">");
        conSetClass3.setValue(df.format(new Date()));
        conditionsSetClass.add(conSetClass1);
        conditionsSetClass.add(conSetClass2);
        conditionsSetClass.add(conSetClass3);
        if (!StringUtil.isNullOrEmpty(doperater)) {
            ConditionsVo condSetClass2 = new ConditionsVo();
            condSetClass2.setAttr("mdep_grade_his");
            condSetClass2.setOperator(doperater);
            condSetClass2.setValue(dGrade);
            conditionsSetClass.add(condSetClass2);
        }
        EsHbaseCriteriaVo querySetClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsSet, conditionsSetClass, null);
        String restSetClass = RestUtils.doPost(ORM_URL, querySetClass);
        List<Map<String, Object>> dataSetset = ORMUtils.getDataSet(restSetClass);
        List<ConditionsVo> conditionsIsNull = new ArrayList<ConditionsVo>();
        ConditionsVo condIsNull = new ConditionsVo();
        ConditionsVo condIsNull2 = new ConditionsVo();
        ConditionsVo condIsNull3 = new ConditionsVo();
        String[] columnsIsNull = {"pid"};
        condIsNull.setAttr("mdep_lvl_his");
        condIsNull.setOperator("=");
        condIsNull.setValue(superiorlevel);
        condIsNull2.setAttr("mdep_beg_his");
        condIsNull2.setOperator("<");
        condIsNull2.setValue(df.format(new Date()));
        condIsNull3.setAttr("mdep_end_his");
        condIsNull3.setOperator("is");
        condIsNull3.setValue("null");
        if (!StringUtil.isNullOrEmpty(doperater)) {
            ConditionsVo condSetIsNull = new ConditionsVo();
            condSetIsNull.setAttr("mdep_grade_his");
            condSetIsNull.setOperator(doperater);
            condSetIsNull.setValue(dGrade);
            conditionsIsNull.add(condSetIsNull);
        }
        conditionsIsNull.add(condIsNull);
        conditionsIsNull.add(condIsNull2);
        conditionsIsNull.add(condIsNull3);
        EsHbaseCriteriaVo queryIsNull = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsIsNull, conditionsIsNull, null);
        String restIsNull = RestUtils.doPost(ORM_URL, queryIsNull);
        List<Map<String, Object>> dataIsNull = ORMUtils.getDataSet(restIsNull);
        if (dataIsNull.size() != 0) {
            dataSetset.addAll(dataIsNull);
        }
        String pid = null;
        if (dataSetset.size() != 0) {
            Map<String, Object> objectMap = dataSetset.get(0);
            pid = (String) objectMap.get("pid");
        }
        List<Object> listpost = new ArrayList();
        if (pid == null) {
            return listpost;
        }
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass1 = new ConditionsVo();
        String[] columns = {"mdep_leader_post"};
        condPrplClass1.setAttr("id");
        condPrplClass1.setOperator("=");
        condPrplClass1.setValue(pid);
        conditionsPrplClass.add(condPrplClass1);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("depttree", columns, conditionsPrplClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        if (dataSet == null || dataSet.size() == 0) {
            return listpost;
        }
        //负责人集合
        List supreior = new ArrayList();
        for (Map<String, Object> map : dataSet) {
            if (!StringUtil.isNullOrEmpty(map.get("mdep_leader_post"))) {
                supreior.add(map.get("mdep_leader_post"));
            }
        }
        if (supreior.size() != 0) {
            for (Object o : supreior) {
                List<ConditionsVo> conditionsPrplClassPost = new ArrayList<ConditionsVo>();
                String[] columns1 = {"id", "rpos_dept"};
                ConditionsVo pcondPrplClass1 = new ConditionsVo();
                ConditionsVo pcondPrplClass3 = new ConditionsVo();
                ConditionsVo pcondPrplClass4 = new ConditionsVo();
                pcondPrplClass1.setAttr("id");
                pcondPrplClass1.setOperator("=");
                pcondPrplClass1.setValue(String.valueOf(o));
                if (!StringUtil.isNullOrEmpty(poperater)) {
                    ConditionsVo pcondPrplClass2 = new ConditionsVo();
                    pcondPrplClass2.setAttr("rpos_grade");
                    pcondPrplClass2.setOperator(poperater);
                    pcondPrplClass2.setValue(pGrade);
                    conditionsPrplClassPost.add(pcondPrplClass2);
                }
                ConditionsVo pcondClassbeg = new ConditionsVo();
                ConditionsVo pcondClassend = new ConditionsVo();
                pcondClassbeg.setAttr("rpos_beg");
                pcondClassbeg.setOperator("<");
                pcondClassbeg.setValue(df.format(new Date()));
                pcondClassend.setAttr("rpos_end");
                pcondClassend.setOperator(">");
                pcondClassend.setValue(df.format(new Date()));
                pcondPrplClass3.setAttr("rpos_emp");
                pcondPrplClass3.setOperator("!is");
                pcondPrplClass3.setValue("null");
                pcondPrplClass4.setAttr("rpos_emp");
                pcondPrplClass4.setOperator("!=");
                pcondPrplClass4.setValue("");
                conditionsPrplClassPost.add(pcondPrplClass1);
                conditionsPrplClassPost.add(pcondPrplClass3);
                conditionsPrplClassPost.add(pcondPrplClass4);
                conditionsPrplClassPost.add(pcondClassbeg);
                conditionsPrplClassPost.add(pcondClassend);
                EsHbaseCriteriaVo queryPrplClasspost = ORMUtils.getEsHbaseCriteria("jobposition", columns1, conditionsPrplClassPost, null);
                String restPrplClasspost = RestUtils.doPost(ORM_URL, queryPrplClasspost);
                List<Map<String, Object>> dataSetpost = ORMUtils.getDataSet(restPrplClasspost);

                List<ConditionsVo> conditionsPrplClassPost1 = new ArrayList<ConditionsVo>();
                String[] columns2 = {"id", "rpos_dept"};
                ConditionsVo pcondPrplClass12 = new ConditionsVo();
                ConditionsVo pcondPrplClass32 = new ConditionsVo();
                ConditionsVo pcondPrplClass42 = new ConditionsVo();
                pcondPrplClass12.setAttr("id");
                pcondPrplClass12.setOperator("=");
                pcondPrplClass12.setValue(String.valueOf(o));
                if (!StringUtil.isNullOrEmpty(poperater)) {
                    ConditionsVo pcondPrplClass2 = new ConditionsVo();
                    pcondPrplClass2.setAttr("rpos_grade");
                    pcondPrplClass2.setOperator(poperater);
                    pcondPrplClass2.setValue(pGrade);
                    conditionsPrplClassPost1.add(pcondPrplClass2);
                }
                ConditionsVo pcondClassbeg1 = new ConditionsVo();
                ConditionsVo pcondClassend2 = new ConditionsVo();
                pcondClassbeg1.setAttr("rpos_beg");
                pcondClassbeg1.setOperator("<");
                pcondClassbeg1.setValue(df.format(new Date()));
                pcondClassend2.setAttr("rpos_end");
                pcondClassend2.setOperator("is");
                pcondClassend2.setValue("null");
                pcondPrplClass32.setAttr("rpos_emp");
                pcondPrplClass32.setOperator("!is");
                pcondPrplClass32.setValue("null");
                pcondPrplClass42.setAttr("rpos_emp");
                pcondPrplClass42.setOperator("!=");
                pcondPrplClass42.setValue("");
                conditionsPrplClassPost1.add(pcondPrplClass12);
                conditionsPrplClassPost1.add(pcondPrplClass32);
                conditionsPrplClassPost1.add(pcondPrplClass42);
                conditionsPrplClassPost1.add(pcondClassbeg1);
                conditionsPrplClassPost1.add(pcondClassend2);

                EsHbaseCriteriaVo queryPrplClasspost2 = ORMUtils.getEsHbaseCriteria("jobposition", columns2, conditionsPrplClassPost1, null);
                String restPrplClasspost2 = RestUtils.doPost(ORM_URL, queryPrplClasspost2);
                List<Map<String, Object>> dataSetpost2 = ORMUtils.getDataSet(restPrplClasspost2);
                if (null != dataSetpost) {
                    dataSetpost.addAll(dataSetpost2);
                }
                if (dataSetpost == null || dataSetpost.size() == 0) {
                    return listpost;
                }
                Map<String, Object> map = dataSetpost.get(0);
                String rposDept = (String) map.get("rpos_dept");
                List<ConditionsVo> conditionsdept = new ArrayList();
                ConditionsVo condeptClass1 = new ConditionsVo();
                ConditionsVo condeptClass2 = new ConditionsVo();
                ConditionsVo condeptClass3 = new ConditionsVo();
                String[] columnsdept = {"pid"};
                condeptClass1.setAttr("pid");
                condeptClass1.setOperator("=");
                condeptClass1.setValue(rposDept);
                condeptClass2.setAttr("mdep_beg_his");
                condeptClass2.setOperator("<");
                condeptClass2.setValue(df.format(new Date()));
                condeptClass3.setAttr("mdep_end_his");
                condeptClass3.setOperator(">");
                condeptClass3.setValue(df.format(new Date()));
                conditionsdept.add(condeptClass1);
                conditionsdept.add(condeptClass2);
                conditionsdept.add(condeptClass3);
                EsHbaseCriteriaVo querydeptClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsdept, conditionsdept, null);
                String restdeptClass = RestUtils.doPost(ORM_URL, querydeptClass);
                List<Map<String, Object>> datadeptset = ORMUtils.getDataSet(restdeptClass);

                List<ConditionsVo> conditionsdeptNull = new ArrayList<ConditionsVo>();
                ConditionsVo conddeptNull = new ConditionsVo();
                ConditionsVo conddeptNull2 = new ConditionsVo();
                ConditionsVo conddeptNull3 = new ConditionsVo();
                String[] columnsdeptNull = {"pid"};
                conddeptNull.setAttr("pid");
                conddeptNull.setOperator("=");
                conddeptNull.setValue(rposDept);
                conddeptNull2.setAttr("mdep_beg_his");
                conddeptNull2.setOperator("<");
                conddeptNull2.setValue(df.format(new Date()));
                conddeptNull3.setAttr("mdep_end_his");
                conddeptNull3.setOperator("is");
                conddeptNull3.setValue("null");
                conditionsdeptNull.add(conddeptNull);
                conditionsdeptNull.add(conddeptNull2);
                conditionsdeptNull.add(conddeptNull3);
                EsHbaseCriteriaVo querydeptNull = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsdeptNull, conditionsdeptNull, null);
                String restdeptNull = RestUtils.doPost(ORM_URL, querydeptNull);
                List<Map<String, Object>> datadeptNull = ORMUtils.getDataSet(restdeptNull);
                datadeptset.addAll(datadeptNull);
                if (datadeptset != null && datadeptset.size() > 0) {
                    listpost.add(o);
                }
            }
        }

        return listpost;
    }


    @Override
    public List<Object> getDirectorSet(String id, String poperater, String doperater, String dGrade, String pGrade) {
        final String ORM_URL = "http://SERVICECENTER-PROVIDER/servicecenter/find";
        List<ConditionsVo> conditionsPrplClassPost = new ArrayList<ConditionsVo>();
        String[] columns1 = {"mdep_chgr_post"};
        ConditionsVo pcondPrplClass1 = new ConditionsVo();
        ConditionsVo pcondPrplClass2 = new ConditionsVo();
        ConditionsVo pcondPrplClass3 = new ConditionsVo();
        ConditionsVo pcondPrplClass6 = new ConditionsVo();
        pcondPrplClass1.setAttr("pid");
        pcondPrplClass1.setOperator("=");
        pcondPrplClass1.setValue(id);
        pcondPrplClass6.setAttr("classname");
        pcondPrplClass6.setOperator("=");
        pcondPrplClass6.setValue("depttree");
        pcondPrplClass2.setAttr("mdep_chgr_beg");
        pcondPrplClass2.setOperator("<");
        pcondPrplClass2.setValue(df.format(new Date()));
        pcondPrplClass3.setAttr("mdep_chgr_end");
        pcondPrplClass3.setOperator(">");
        pcondPrplClass3.setValue(df.format(new Date()));
        conditionsPrplClassPost.add(pcondPrplClass1);
        conditionsPrplClassPost.add(pcondPrplClass2);
        conditionsPrplClassPost.add(pcondPrplClass3);
        conditionsPrplClassPost.add(pcondPrplClass6);
        EsHbaseCriteriaVo queryPrplClasspost = ORMUtils.getEsHbaseCriteria("depttree.mdep_chgr_set", columns1, conditionsPrplClassPost, null);
        String restPrplClasspost = RestUtils.doPost(ORM_URL, queryPrplClasspost);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClasspost);

        List<ConditionsVo> conditionPost2 = new ArrayList<ConditionsVo>();
        String[] columnspost1 = {"mdep_chgr_post"};
        ConditionsVo pcondpost1 = new ConditionsVo();
        ConditionsVo pcondpost2 = new ConditionsVo();
        ConditionsVo pcondpost3 = new ConditionsVo();
        ConditionsVo pcondpost6 = new ConditionsVo();
        pcondpost1.setAttr("pid");
        pcondpost1.setOperator("=");
        pcondpost1.setValue(id);
        pcondpost6.setAttr("classname");
        pcondpost6.setOperator("=");
        pcondpost6.setValue("depttree");
        pcondpost2.setAttr("mdep_chgr_beg");
        pcondpost2.setOperator("<");
        pcondpost2.setValue(df.format(new Date()));
        pcondpost3.setAttr("mdep_chgr_end");
        pcondpost3.setOperator("is");
        pcondpost3.setValue("null");
        conditionPost2.add(pcondpost1);
        conditionPost2.add(pcondpost2);
        conditionPost2.add(pcondpost3);
        conditionPost2.add(pcondpost6);
        EsHbaseCriteriaVo queryClasspost1 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chgr_set", columnspost1, conditionPost2, null);
        String restpost2 = RestUtils.doPost(ORM_URL, queryClasspost1);
        List<Map<String, Object>> dataSet2 = ORMUtils.getDataSet(restpost2);
        dataSet.addAll(dataSet2);
        ArrayList<Object> list = new ArrayList<Object>();
        ArrayList<Object> list1 = new ArrayList<Object>();
        if (dataSet == null || dataSet.size() == 0) {
            return list1;
        }
        for (Map<String, Object> map : dataSet) {
            if (!StringUtil.isNullOrEmpty(map.get("mdep_chgr_post"))) {
                list.add(map.get("mdep_chgr_post"));
            }    //返回岗位对象
        }
        for (Object o : list) {
            List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
            String[] columns = {"id", "rpos_dept"};
            ConditionsVo pcondPrplClass4 = new ConditionsVo();
            ConditionsVo pcondPrplClass7 = new ConditionsVo();
            ConditionsVo pcondPrplClass8 = new ConditionsVo();
            ConditionsVo pcondPrplClass9 = new ConditionsVo();
            ConditionsVo pcondPrplClass10 = new ConditionsVo();

            pcondPrplClass4.setAttr("id");
            pcondPrplClass4.setOperator("=");
            pcondPrplClass4.setValue(String.valueOf(o));
            if (!StringUtil.isNullOrEmpty(poperater)) {
                ConditionsVo pcondPrplClass5 = new ConditionsVo();
                pcondPrplClass5.setAttr("rpos_grade");
                pcondPrplClass5.setOperator(poperater);
                pcondPrplClass5.setValue(pGrade);
                conditionsPrplClass.add(pcondPrplClass5);
            }
            pcondPrplClass9.setAttr("rpos_beg");
            pcondPrplClass9.setOperator("<");
            pcondPrplClass9.setValue(df.format(new Date()));
            pcondPrplClass10.setAttr("rpos_end");
            pcondPrplClass10.setOperator(">");
            pcondPrplClass10.setValue(df.format(new Date()));
            pcondPrplClass7.setAttr("rpos_emp");
            pcondPrplClass7.setOperator("!is");
            pcondPrplClass7.setValue("null");
            pcondPrplClass8.setAttr("rpos_emp");
            pcondPrplClass8.setOperator("!=");
            pcondPrplClass8.setValue("");
            conditionsPrplClass.add(pcondPrplClass4);
            conditionsPrplClass.add(pcondPrplClass7);
            conditionsPrplClass.add(pcondPrplClass8);
            conditionsPrplClass.add(pcondPrplClass9);
            conditionsPrplClass.add(pcondPrplClass10);
            EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("jobposition", columns, conditionsPrplClass, null);
            String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
            List<Map<String, Object>> dataSetpost = ORMUtils.getDataSet(restPrplClass);
            //对岗位的失效时间为null的也进行筛选
            List<ConditionsVo> conditionsPrplClassPost1 = new ArrayList<ConditionsVo>();
            String[] columns22 = {"id", "rpos_dept"};
            ConditionsVo pcondPrplClass12 = new ConditionsVo();
            ConditionsVo pcondPrplClass32 = new ConditionsVo();
            ConditionsVo pcondPrplClass42 = new ConditionsVo();
            pcondPrplClass12.setAttr("id");
            pcondPrplClass12.setOperator("=");
            pcondPrplClass12.setValue(String.valueOf(o));
            if (!StringUtil.isNullOrEmpty(poperater)) {
                ConditionsVo pcondPrplClass22 = new ConditionsVo();
                pcondPrplClass22.setAttr("rpos_grade");
                pcondPrplClass22.setOperator(poperater);
                pcondPrplClass22.setValue(pGrade);
                conditionsPrplClassPost1.add(pcondPrplClass22);
            }
            ConditionsVo pcondClassbeg1 = new ConditionsVo();
            ConditionsVo pcondClassend2 = new ConditionsVo();
            pcondClassbeg1.setAttr("rpos_beg");
            pcondClassbeg1.setOperator("<");
            pcondClassbeg1.setValue(df.format(new Date()));
            pcondClassend2.setAttr("rpos_end");
            pcondClassend2.setOperator("is");
            pcondClassend2.setValue("null");
            pcondPrplClass32.setAttr("rpos_emp");
            pcondPrplClass32.setOperator("!is");
            pcondPrplClass32.setValue("null");
            pcondPrplClass42.setAttr("rpos_emp");
            pcondPrplClass42.setOperator("!=");
            pcondPrplClass42.setValue("");
            conditionsPrplClassPost1.add(pcondPrplClass12);
            conditionsPrplClassPost1.add(pcondPrplClass32);
            conditionsPrplClassPost1.add(pcondPrplClass42);
            conditionsPrplClassPost1.add(pcondClassbeg1);
            conditionsPrplClassPost1.add(pcondClassend2);
            EsHbaseCriteriaVo queryPrplClasspost2 = ORMUtils.getEsHbaseCriteria("jobposition", columns22, conditionsPrplClassPost1, null);
            String restPrplClasspost2 = RestUtils.doPost(ORM_URL, queryPrplClasspost2);
            List<Map<String, Object>> dataSetpost2 = ORMUtils.getDataSet(restPrplClasspost2);
            if (null != dataSetpost) {
                dataSetpost.addAll(dataSetpost2);
            }
            if (dataSetpost == null || dataSetpost.size() == 0) {
                continue;
            }
            for (Map<String, Object> stringObjectMap : dataSetpost) {
                Object id1 = stringObjectMap.get("rpos_dept");
                List<ConditionsVo> conditionsClassPost = new ArrayList<ConditionsVo>();
                String[] columns2 = {"pid"};
                ConditionsVo pcondClass1 = new ConditionsVo();
                ConditionsVo pcondClass3 = new ConditionsVo();
                ConditionsVo pcondClass4 = new ConditionsVo();
                pcondClass3.setAttr("mdep_beg_his");
                pcondClass3.setOperator("<");
                pcondClass3.setValue(df.format(new Date()));
                pcondClass4.setAttr("mdep_end_his");
                pcondClass4.setOperator(">");
                pcondClass4.setValue(df.format(new Date()));
                conditionsClassPost.add(pcondClass3);
                conditionsClassPost.add(pcondClass4);
                pcondClass1.setAttr("pid");
                pcondClass1.setOperator("=");
                pcondClass1.setValue(String.valueOf(id1));
                conditionsClassPost.add(pcondClass1);
                EsHbaseCriteriaVo queryClasspost = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns2, conditionsClassPost, null);
                String restClasspost = RestUtils.doPost(ORM_URL, queryClasspost);
                List<Map<String, Object>> dataSetpost1 = ORMUtils.getDataSet(restClasspost);
                List<ConditionsVo> conditionsIsNull = new ArrayList<ConditionsVo>();
                ConditionsVo condIsNull = new ConditionsVo();
                ConditionsVo condIsNull2 = new ConditionsVo();
                ConditionsVo condIsNull3 = new ConditionsVo();
                String[] columnsIsNull = {"mdep_lvl_his"};
                condIsNull.setAttr("pid");
                condIsNull.setOperator("=");
                condIsNull.setValue(String.valueOf(id1));
                condIsNull2.setAttr("mdep_beg_his");
                condIsNull2.setOperator("<");
                condIsNull2.setValue(df.format(new Date()));
                condIsNull3.setAttr("mdep_end_his");
                condIsNull3.setOperator("is");
                condIsNull3.setValue("null");
                conditionsIsNull.add(condIsNull);
                conditionsIsNull.add(condIsNull2);
                conditionsIsNull.add(condIsNull3);
                EsHbaseCriteriaVo queryIsNull = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsIsNull, conditionsIsNull, null);
                String restIsNull = RestUtils.doPost(ORM_URL, queryIsNull);
                List<Map<String, Object>> dataIsNull = ORMUtils.getDataSet(restIsNull);
                if (dataIsNull.size() != 0) {
                    dataSetpost1.addAll(dataIsNull);
                }
                if (dataSetpost1 == null) {
                    return list1;
                }
                if (dataSetpost1.size() != 0) {
                    list1.add((String) o);
                }
            }
        }
        return list1;
    }


    private List getsupreior(String department, String pGrade, String doperater, String poperater, String dGrade) {
        final String ORM_URL = "http://SERVICECENTER-PROVIDER/servicecenter/find";
        String designatedpost = office(department);
        List<ConditionsVo> conditionsSetClass = new ArrayList();
        ConditionsVo conSetClass1 = new ConditionsVo();
        ConditionsVo conSetClass2 = new ConditionsVo();
        ConditionsVo conSetClass3 = new ConditionsVo();
        String[] columnsSet = {"pid"};
        conSetClass1.setAttr("mdep_lvl_his");
        conSetClass1.setOperator("=");
        conSetClass1.setValue(designatedpost);
        conSetClass2.setAttr("mdep_beg_his");
        conSetClass2.setOperator("<");
        conSetClass2.setValue(df.format(new Date()));
        conSetClass3.setAttr("mdep_end_his");
        conSetClass3.setOperator(">");
        conSetClass3.setValue(df.format(new Date()));
        conditionsSetClass.add(conSetClass1);
        conditionsSetClass.add(conSetClass2);
        conditionsSetClass.add(conSetClass3);
        if (!StringUtil.isNullOrEmpty(doperater)) {
            ConditionsVo condSetClass2 = new ConditionsVo();
            condSetClass2.setAttr("mdep_grade_his");
            condSetClass2.setOperator(doperater);
            condSetClass2.setValue(dGrade);
            conditionsSetClass.add(condSetClass2);
        }
        EsHbaseCriteriaVo querySetClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsSet, conditionsSetClass, null);
        String restSetClass = RestUtils.doPost(ORM_URL, querySetClass);
        List<Map<String, Object>> dataSetset = ORMUtils.getDataSet(restSetClass);
        List<ConditionsVo> conditionsIsNull = new ArrayList<ConditionsVo>();
        ConditionsVo condIsNull = new ConditionsVo();
        ConditionsVo condIsNull2 = new ConditionsVo();
        ConditionsVo condIsNull3 = new ConditionsVo();
        String[] columnsIsNull = {"pid"};
        condIsNull.setAttr("mdep_lvl_his");
        condIsNull.setOperator("=");
        condIsNull.setValue(designatedpost);
        condIsNull2.setAttr("mdep_beg_his");
        condIsNull2.setOperator("<");
        condIsNull2.setValue(df.format(new Date()));
        condIsNull3.setAttr("mdep_end_his");
        condIsNull3.setOperator("is");
        condIsNull3.setValue("null");
        if (!StringUtil.isNullOrEmpty(doperater)) {
            ConditionsVo condSetIsNull = new ConditionsVo();
            condSetIsNull.setAttr("mdep_grade_his");
            condSetIsNull.setOperator(doperater);
            condSetIsNull.setValue(dGrade);
            conditionsIsNull.add(condSetIsNull);
        }
        conditionsIsNull.add(condIsNull);
        conditionsIsNull.add(condIsNull2);
        conditionsIsNull.add(condIsNull3);
        EsHbaseCriteriaVo queryIsNull = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsIsNull, conditionsIsNull, null);
        String restIsNull = RestUtils.doPost(ORM_URL, queryIsNull);
        List<Map<String, Object>> dataIsNull = ORMUtils.getDataSet(restIsNull);
        if (dataIsNull.size() != 0) {
            dataSetset.addAll(dataIsNull);
        }
        String pid = null;
        if (dataSetset.size() != 0) {
            Map<String, Object> objectMap = dataSetset.get(0);
            pid = (String) objectMap.get("pid");
        }
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass1 = new ConditionsVo();
        String[] columns = {"mdep_leader_post"};
        condPrplClass1.setAttr("id");
        condPrplClass1.setOperator("=");
        condPrplClass1.setValue(pid);
        conditionsPrplClass.add(condPrplClass1);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("depttree", columns, conditionsPrplClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        //负责人岗位集合
        List supreior = new ArrayList();
        List<Object> listPost = new ArrayList();
        if (dataSet == null || dataSet.size() == 0) {
            return listPost;
        }
        for (Map<String, Object> map : dataSet) {
            if (!StringUtil.isNullOrEmpty(map.get("mdep_leader_post"))) {
                supreior.add(map.get("mdep_leader_post"));
            }
        }
        for (Object o : supreior) {
            List<ConditionsVo> conditionsPrplClassPost = new ArrayList<ConditionsVo>();
            String[] columns1 = {"id", "rpos_dept"};
            ConditionsVo pcondPrplClass1 = new ConditionsVo();
            ConditionsVo pcondPrplClass3 = new ConditionsVo();
            ConditionsVo pcondPrplClass4 = new ConditionsVo();
            ConditionsVo pcondClassbeg = new ConditionsVo();
            ConditionsVo pcondClassend = new ConditionsVo();
            pcondPrplClass1.setAttr("id");
            pcondPrplClass1.setOperator("=");
            pcondPrplClass1.setValue(String.valueOf(o));
            if (!StringUtil.isNullOrEmpty(poperater)) {
                ConditionsVo pcondPrplClass2 = new ConditionsVo();
                pcondPrplClass2.setAttr("rpos_grade");
                pcondPrplClass2.setOperator(poperater);
                pcondPrplClass2.setValue(pGrade);
                conditionsPrplClassPost.add(pcondPrplClass2);

            }
            pcondClassbeg.setAttr("rpos_beg");
            pcondClassbeg.setOperator("<");
            pcondClassbeg.setValue(df.format(new Date()));
            pcondClassend.setAttr("rpos_end");
            pcondClassend.setOperator(">");
            pcondClassend.setValue(df.format(new Date()));
            pcondPrplClass3.setAttr("rpos_emp");
            pcondPrplClass3.setOperator("!is");
            pcondPrplClass3.setValue("null");
            pcondPrplClass4.setAttr("rpos_emp");
            pcondPrplClass4.setOperator("!=");
            pcondPrplClass4.setValue("");
            conditionsPrplClassPost.add(pcondPrplClass1);
            conditionsPrplClassPost.add(pcondPrplClass4);
            conditionsPrplClassPost.add(pcondPrplClass3);
            conditionsPrplClassPost.add(pcondClassbeg);
            conditionsPrplClassPost.add(pcondClassend);
            EsHbaseCriteriaVo queryPrplClasspost = ORMUtils.getEsHbaseCriteria("jobposition", columns1, conditionsPrplClassPost, null);
            String restPrplClasspost = RestUtils.doPost(ORM_URL, queryPrplClasspost);
            List<Map<String, Object>> dataSetpost = ORMUtils.getDataSet(restPrplClasspost);
            List<ConditionsVo> conditionsPrplClassPost1 = new ArrayList<ConditionsVo>();
            String[] columns11 = {"id", "rpos_dept"};
            ConditionsVo pcondPrplClass11 = new ConditionsVo();
            ConditionsVo pcondPrplClass31 = new ConditionsVo();
            ConditionsVo pcondPrplClass41 = new ConditionsVo();
            ConditionsVo pcondClassbeg1 = new ConditionsVo();
            ConditionsVo pcondClassend1 = new ConditionsVo();
            pcondPrplClass11.setAttr("id");
            pcondPrplClass11.setOperator("=");
            pcondPrplClass11.setValue(String.valueOf(o));
            if (!StringUtil.isNullOrEmpty(poperater)) {
                ConditionsVo pcondPrplClass21 = new ConditionsVo();
                pcondPrplClass21.setAttr("rpos_grade");
                pcondPrplClass21.setOperator(poperater);
                pcondPrplClass21.setValue(pGrade);
                conditionsPrplClassPost1.add(pcondPrplClass21);
            }
            pcondClassbeg1.setAttr("rpos_beg");
            pcondClassbeg1.setOperator("<");
            pcondClassbeg1.setValue(df.format(new Date()));
            pcondClassend1.setAttr("rpos_end");
            pcondClassend1.setOperator("is");
            pcondClassend1.setValue("null");
            pcondPrplClass31.setAttr("rpos_emp");
            pcondPrplClass31.setOperator("!is");
            pcondPrplClass31.setValue("null");
            pcondPrplClass41.setAttr("rpos_emp");
            pcondPrplClass41.setOperator("!=");
            pcondPrplClass41.setValue("");
            conditionsPrplClassPost1.add(pcondPrplClass11);
            conditionsPrplClassPost1.add(pcondPrplClass41);
            conditionsPrplClassPost1.add(pcondPrplClass31);
            conditionsPrplClassPost1.add(pcondClassbeg1);
            conditionsPrplClassPost1.add(pcondClassend1);
            EsHbaseCriteriaVo queryPrplClasspost1 = ORMUtils.getEsHbaseCriteria("jobposition", columns11, conditionsPrplClassPost1, null);
            String restPrplClasspost1 = RestUtils.doPost(ORM_URL, queryPrplClasspost1);
            List<Map<String, Object>> dataSetpost1 = ORMUtils.getDataSet(restPrplClasspost1);
            if (null != dataSetpost1) {
                dataSetpost1.addAll(dataSetpost);
            }
            if (null == dataSetpost1 || dataSetpost1.size() == 0) {
                return listPost;
            }
            Map<String, Object> map = dataSetpost1.get(0);
            String rposDept = (String) map.get("rpos_dept");
            List<ConditionsVo> conditionsdept = new ArrayList();
            ConditionsVo condeptClass1 = new ConditionsVo();
            ConditionsVo condeptClass2 = new ConditionsVo();
            ConditionsVo condeptClass3 = new ConditionsVo();
            String[] columnsdept = {"pid"};
            condeptClass1.setAttr("pid");
            condeptClass1.setOperator("=");
            condeptClass1.setValue(rposDept);
            condeptClass2.setAttr("mdep_beg_his");
            condeptClass2.setOperator("<");
            condeptClass2.setValue(df.format(new Date()));
            condeptClass3.setAttr("mdep_end_his");
            condeptClass3.setOperator(">");
            condeptClass3.setValue(df.format(new Date()));
            conditionsdept.add(condeptClass1);
            conditionsdept.add(condeptClass2);
            conditionsdept.add(condeptClass3);
            EsHbaseCriteriaVo querydeptClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsdept, conditionsdept, null);
            String restdeptClass = RestUtils.doPost(ORM_URL, querydeptClass);
            List<Map<String, Object>> datadeptset = ORMUtils.getDataSet(restdeptClass);

            List<ConditionsVo> conditionsdeptNull = new ArrayList<ConditionsVo>();
            ConditionsVo conddeptNull = new ConditionsVo();
            ConditionsVo conddeptNull2 = new ConditionsVo();
            ConditionsVo conddeptNull3 = new ConditionsVo();
            String[] columnsdeptNull = {"pid"};
            conddeptNull.setAttr("pid");
            conddeptNull.setOperator("=");
            conddeptNull.setValue(rposDept);
            conddeptNull2.setAttr("mdep_beg_his");
            conddeptNull2.setOperator("<");
            conddeptNull2.setValue(df.format(new Date()));
            conddeptNull3.setAttr("mdep_end_his");
            conddeptNull3.setOperator("is");
            conddeptNull3.setValue("null");
            conditionsdeptNull.add(conddeptNull);
            conditionsdeptNull.add(conddeptNull2);
            conditionsdeptNull.add(conddeptNull3);
            EsHbaseCriteriaVo querydeptNull = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsdeptNull, conditionsdeptNull, null);
            String restdeptNull = RestUtils.doPost(ORM_URL, querydeptNull);
            List<Map<String, Object>> datadeptNull = ORMUtils.getDataSet(restdeptNull);
            datadeptset.addAll(datadeptNull);
            if (datadeptset != null && datadeptset.size() > 0) {
                listPost.add(o);
            }
        }
        return listPost;
    }

    private String getId(String level) {
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass1 = new ConditionsVo();
        String[] columns = {"pid"};
        condPrplClass1.setAttr("mdep_lvl_his");
        condPrplClass1.setOperator("=");
        condPrplClass1.setValue(level);
        conditionsPrplClass.add(condPrplClass1);
        ConditionsVo condPrplClass2 = new ConditionsVo();
        condPrplClass2.setAttr("mdep_beg_his");
        condPrplClass2.setOperator("<");
        condPrplClass2.setValue(df.format(new Date()));
        conditionsPrplClass.add(condPrplClass2);
        ConditionsVo condPrplClass3 = new ConditionsVo();
        condPrplClass3.setAttr("mdep_end_his");
        condPrplClass3.setOperator(">");
        condPrplClass3.setValue(df.format(new Date()));
        conditionsPrplClass.add(condPrplClass3);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns, conditionsPrplClass, null);
        String restPrplClasspost = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> pidList = ORMUtils.getDataSet(restPrplClasspost);
        List<ConditionsVo> conditionsIsNull = new ArrayList<ConditionsVo>();
        ConditionsVo condIsNull = new ConditionsVo();
        ConditionsVo condIsNull2 = new ConditionsVo();
        ConditionsVo condIsNull3 = new ConditionsVo();
        String[] columnsIsNull = {"pid"};
        condIsNull.setAttr("mdep_lvl_his");
        condIsNull.setOperator("=");
        condIsNull.setValue(level);
        condIsNull2.setAttr("mdep_beg_his");
        condIsNull2.setOperator("<");
        condIsNull2.setValue(df.format(new Date()));
        condIsNull3.setAttr("mdep_end_his");
        condIsNull3.setOperator("is");
        condIsNull3.setValue("null");
        conditionsIsNull.add(condIsNull);
        conditionsIsNull.add(condIsNull2);
        conditionsIsNull.add(condIsNull3);
        EsHbaseCriteriaVo queryIsNull = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsIsNull, conditionsIsNull, null);
        String restIsNull = RestUtils.doPost(ORM_URL, queryIsNull);
        List<Map<String, Object>> dataIsNull = ORMUtils.getDataSet(restIsNull);
        if (dataIsNull.size() != 0) {
            pidList.addAll(dataIsNull);
        }
        if (pidList.size() != 0) {
            Map<String, Object> map = pidList.get(0);
            Object pid = map.get("pid");
            return String.valueOf(pid);
        }
        return null;
    }


    //根据岗位id 与level查询上级岗位
    @Override
    public Result getPostSuperior(String postId, String level, String pGrade, String poperater, String dGrade, String doperater) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        if (!isEffectivePost(postId)) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("岗位：" + postId + "无效，" + " 或所属部门无效");
            return result;
        }
        List<String> list = new ArrayList();
        if (!isEffectivePost(postId)) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("岗位：" + postId + "无效，" + " 或所属部门无效");
            return result;
        }
        if ("上上级岗位".equals(level)) {
            postId = getPpost(postId);
            if (postId == null || "".equals(postId)) {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("上上级岗位不存在");
                return result;
            }
            if (!isEffectivePost(postId)) {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("岗位：" + postId + "无效，" + " 或所属部门无效");
                return result;
            }
            while (true) {
                postId = getPpost(postId);
                if (postId == null || "".equals(postId)) {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("岗位：" + postId + ",不存在上级");
                    break;
                }
                if (!isEffectivePost(postId)) {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("岗位：" + postId + "无效，" + " 或所属部门无效");
                    return result;
                }
                list = designatedpost1(postId, pGrade, poperater, dGrade, doperater);
                if (list.size() != 0) {
                    break;
                }
            }
        } else if ("上级岗位".equals(level)) {
            while (true) {
                postId = getPpost(postId);
                if (postId == null || "".equals(postId)) {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("岗位：" + postId + ",不存在上级");
                    break;
                }
                if (!isEffectivePost(postId)) {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("岗位：" + postId + "无效，" + " 或所属部门无效");
                    return result;
                }
                list = designatedpost1(postId, pGrade, poperater, dGrade, doperater);
                if (list.size() != 0) {
                    break;
                }
            }
        } else if ("当前岗位".equals(level)) {
            while (true) {
                list = designatedpost1(postId, pGrade, poperater, dGrade, doperater);
                if (list.size() != 0) {
                    break;
                }
                postId = getPpost(postId);

                if (postId == null || "".equals(postId)) {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("岗位：" + postId + ",不存在上级");
                    break;
                }
                if (!isEffectivePost(postId)) {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("岗位：" + postId + "无效，" + " 或所属部门无效");
                    return result;
                }
            }
        }
        result.setData(list);
        return result;
    }


    /**
     * 无条件获取上级岗位
     *
     * @param id
     * @return
     */
    private String getPpost(String id) {
        Object psotId = null;
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass1 = new ConditionsVo();
        String[] columns = {"rpos_ppost"};
        condPrplClass1.setAttr("id");
        condPrplClass1.setOperator("=");
        condPrplClass1.setValue(id);
        conditionsPrplClass.add(condPrplClass1);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("jobposition", columns, conditionsPrplClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        if (dataSet == null || dataSet.size() == 0) {
            return null;
        }
        for (Map<String, Object> map : dataSet) {
            psotId = map.get("rpos_ppost");
        }
        return String.valueOf(psotId);
    }

    @Override
    public Boolean isEffectivePost(String postId) {
        final String ORM_URL = "http://SERVICECENTER-PROVIDER/servicecenter/find";
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass1 = new ConditionsVo();
        ConditionsVo condPrplClass2 = new ConditionsVo();
        ConditionsVo condPrplClass5 = new ConditionsVo();
        String[] columns = {"rpos_dept"};
        condPrplClass1.setAttr("id");
        condPrplClass1.setOperator("=");
        condPrplClass1.setValue(postId);
        condPrplClass2.setAttr("rpos_beg");
        condPrplClass2.setOperator("<");
        condPrplClass2.setValue(df.format(new Date()));
        condPrplClass5.setAttr("rpos_end");
        condPrplClass5.setOperator(">");
        condPrplClass5.setValue(df.format(new Date()));
        conditionsPrplClass.add(condPrplClass1);
        conditionsPrplClass.add(condPrplClass2);
        conditionsPrplClass.add(condPrplClass5);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("jobposition", columns, conditionsPrplClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        List<ConditionsVo> conditionsPrplClass1 = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass11 = new ConditionsVo();
        ConditionsVo condPrplClass22 = new ConditionsVo();
        ConditionsVo condPrplClass52 = new ConditionsVo();
        String[] columns2 = {"rpos_dept"};
        condPrplClass11.setAttr("id");
        condPrplClass11.setOperator("=");
        condPrplClass11.setValue(postId);
        condPrplClass22.setAttr("rpos_beg");
        condPrplClass22.setOperator("<");
        condPrplClass22.setValue(df.format(new Date()));
        condPrplClass52.setAttr("rpos_end");
        condPrplClass52.setOperator("is");
        condPrplClass52.setValue("null");
        conditionsPrplClass1.add(condPrplClass11);
        conditionsPrplClass1.add(condPrplClass22);
        conditionsPrplClass1.add(condPrplClass52);
        EsHbaseCriteriaVo queryPrplClass1 = ORMUtils.getEsHbaseCriteria("jobposition", columns2, conditionsPrplClass1, null);
        String restPrplClass2 = RestUtils.doPost(ORM_URL, queryPrplClass1);
        List<Map<String, Object>> dataSet2 = ORMUtils.getDataSet(restPrplClass2);
        dataSet2.addAll(dataSet);
        if (dataSet2.isEmpty()) {
            return false;
        }
        if (dataSet2.size() != 0) {
            Map<String, Object> map = dataSet2.get(0);
            Object rposDept = map.get("rpos_dept");
            List<ConditionsVo> conditionsDeptClass = new ArrayList<ConditionsVo>();
            ConditionsVo condPrplClass3 = new ConditionsVo();
            String[] columns3 = {"id"};
            condPrplClass3.setAttr("pid");
            condPrplClass3.setOperator("=");
            condPrplClass3.setValue(String.valueOf(rposDept));
            conditionsDeptClass.add(condPrplClass3);
            ConditionsVo condPrplClass4 = new ConditionsVo();
            condPrplClass4.setAttr("mdep_beg_his");
            condPrplClass4.setOperator("<");
            condPrplClass4.setValue(df.format(new Date()));
            conditionsDeptClass.add(condPrplClass4);
            ConditionsVo condPrplClass6 = new ConditionsVo();
            condPrplClass6.setAttr("mdep_end_his");
            condPrplClass6.setOperator(">");
            condPrplClass6.setValue(df.format(new Date()));
            conditionsDeptClass.add(condPrplClass6);
            EsHbaseCriteriaVo queryDeptClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns3, conditionsDeptClass, null);
            String restDeptClass = RestUtils.doPost(ORM_URL, queryDeptClass);
            List<Map<String, Object>> dataDeptSet = ORMUtils.getDataSet(restDeptClass);
            List<ConditionsVo> conditionsIsNull = new ArrayList<ConditionsVo>();
            ConditionsVo condIsNull = new ConditionsVo();
            ConditionsVo condIsNull2 = new ConditionsVo();
            ConditionsVo condIsNull3 = new ConditionsVo();
            String[] columnsIsNull = {"id"};
            condIsNull.setAttr("pid");
            condIsNull.setOperator("=");
            condIsNull.setValue(String.valueOf(rposDept));
            condIsNull2.setAttr("mdep_beg_his");
            condIsNull2.setOperator("<");
            condIsNull2.setValue(df.format(new Date()));
            condIsNull3.setAttr("mdep_end_his");
            condIsNull3.setOperator("is");
            condIsNull3.setValue("null");
            conditionsIsNull.add(condIsNull);
            conditionsIsNull.add(condIsNull2);
            conditionsIsNull.add(condIsNull3);
            EsHbaseCriteriaVo queryIsNull = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsIsNull, conditionsIsNull, null);
            String restIsNull = RestUtils.doPost(ORM_URL, queryIsNull);
            List<Map<String, Object>> dataIsNull = ORMUtils.getDataSet(restIsNull);
            dataDeptSet.addAll(dataIsNull);
            if (dataDeptSet.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    @Override
    public Boolean inTheLevel(String id, String dGrade, String doperater) {
        List<ConditionsVo> conditionsClass = new ArrayList<ConditionsVo>();
        ConditionsVo condClass1 = new ConditionsVo();
        ConditionsVo condClass2 = new ConditionsVo();
        ConditionsVo condClass3 = new ConditionsVo();
        String[] columns = {"id"};
        condClass1.setAttr("pid");
        condClass1.setOperator("=");
        condClass1.setValue(id);
        condClass2.setAttr("mdep_beg_his");
        condClass2.setOperator("<");
        condClass2.setValue(df.format(new Date()));
        condClass3.setAttr("mdep_end_his");
        condClass3.setOperator(">");
        condClass3.setValue(df.format(new Date()));
        if (!StringUtil.isNullOrEmpty(dGrade) && !StringUtil.isNullOrEmpty(doperater)) {
            ConditionsVo condClass4 = new ConditionsVo();
            condClass4.setAttr("mdep_grade_his");
            condClass4.setOperator(doperater);
            condClass4.setValue(dGrade);
            conditionsClass.add(condClass4);
        }
        conditionsClass.add(condClass1);
        conditionsClass.add(condClass2);
        conditionsClass.add(condClass3);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns, conditionsClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        List<ConditionsVo> conditionsClass2 = new ArrayList<ConditionsVo>();
        ConditionsVo condClass11 = new ConditionsVo();
        ConditionsVo condClass21 = new ConditionsVo();
        ConditionsVo condClass31 = new ConditionsVo();
        condClass11.setAttr("pid");
        condClass11.setOperator("=");
        condClass11.setValue(id);
        condClass21.setAttr("mdep_beg_his");
        condClass21.setOperator("<");
        condClass21.setValue(df.format(new Date()));
        condClass31.setAttr("mdep_end_his");
        condClass31.setOperator("is");
        condClass31.setValue("null");
        if (!StringUtil.isNullOrEmpty(dGrade) && !StringUtil.isNullOrEmpty(doperater)) {
            ConditionsVo condClass4 = new ConditionsVo();
            condClass4.setAttr("mdep_grade_his");
            condClass4.setOperator(doperater);
            condClass4.setValue(dGrade);
            conditionsClass2.add(condClass4);
        }
        conditionsClass2.add(condClass11);
        conditionsClass2.add(condClass21);
        conditionsClass2.add(condClass31);
        EsHbaseCriteriaVo queryPrplClass1 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns, conditionsClass2, null);
        String restPrplClass1 = RestUtils.doPost(ORM_URL, queryPrplClass1);
        List<Map<String, Object>> dataSet1 = ORMUtils.getDataSet(restPrplClass1);
        dataSet.addAll(dataSet1);
        if (CollectionUtils.isNotEmpty(dataSet)) {
            return true;
        }
        return false;
    }


    /**
     * 本级岗位也需要经过判断
     *
     * @param postId
     * @param pGrade
     * @param poperater
     * @param dGrade
     * @param doperater
     * @return
     */
    private List<String> designatedpost1(String postId, String pGrade, String poperater, String dGrade, String doperater) {
        List<String> list = new ArrayList<>();
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass1 = new ConditionsVo();
        ConditionsVo condPrplClass2 = new ConditionsVo();
        ConditionsVo condPrplClass5 = new ConditionsVo();
        ConditionsVo condPrplClass = new ConditionsVo();
        String[] columns = {"id", "rpos_dept"};
        condPrplClass1.setAttr("id");
        condPrplClass1.setOperator("=");
        condPrplClass1.setValue(postId);
        if (!StringUtil.isNullOrEmpty(poperater)) {
            //判断岗级
            condPrplClass2.setAttr("rpos_grade");
            condPrplClass2.setOperator(poperater);
            condPrplClass2.setValue(pGrade);
            conditionsPrplClass.add(condPrplClass2);
        }
        ConditionsVo pcondClassbeg = new ConditionsVo();
        ConditionsVo pcondClassend = new ConditionsVo();
        pcondClassbeg.setAttr("rpos_beg");
        pcondClassbeg.setOperator("<");
        pcondClassbeg.setValue(df.format(new Date()));
        pcondClassend.setAttr("rpos_end");
        pcondClassend.setOperator(">");
        pcondClassend.setValue(df.format(new Date()));
        condPrplClass5.setAttr("rpos_emp");
        condPrplClass5.setOperator("!is");
        condPrplClass5.setValue("null");
        condPrplClass.setAttr("rpos_emp");
        condPrplClass.setOperator("!=");
        condPrplClass.setValue("");
        conditionsPrplClass.add(condPrplClass1);
        conditionsPrplClass.add(condPrplClass5);
        conditionsPrplClass.add(condPrplClass);
        conditionsPrplClass.add(pcondClassbeg);
        conditionsPrplClass.add(pcondClassend);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("jobposition", columns, conditionsPrplClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        List<ConditionsVo> conditionsPrplClass1 = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass11 = new ConditionsVo();
        ConditionsVo condPrplClass21 = new ConditionsVo();
        ConditionsVo condPrplClass51 = new ConditionsVo();
        ConditionsVo condPrplClasss = new ConditionsVo();
        String[] columns11 = {"id", "rpos_dept"};
        condPrplClass11.setAttr("id");
        condPrplClass11.setOperator("=");
        condPrplClass11.setValue(postId);
        if (!StringUtil.isNullOrEmpty(poperater)) {
            //判断岗级
            condPrplClass21.setAttr("rpos_grade");
            condPrplClass21.setOperator(poperater);
            condPrplClass21.setValue(pGrade);
            conditionsPrplClass1.add(condPrplClass21);
        }
        ConditionsVo pcondClassbeg1 = new ConditionsVo();
        ConditionsVo pcondClassend1 = new ConditionsVo();
        pcondClassbeg1.setAttr("rpos_beg");
        pcondClassbeg1.setOperator("<");
        pcondClassbeg1.setValue(df.format(new Date()));
        pcondClassend1.setAttr("rpos_end");
        pcondClassend1.setOperator("is");
        pcondClassend1.setValue("null");
        condPrplClass51.setAttr("rpos_emp");
        condPrplClass51.setOperator("!is");
        condPrplClass51.setValue("null");
        condPrplClasss.setAttr("rpos_emp");
        condPrplClasss.setOperator("!=");
        condPrplClasss.setValue("");
        conditionsPrplClass1.add(condPrplClass11);
        conditionsPrplClass1.add(condPrplClass51);
        conditionsPrplClass1.add(condPrplClasss);
        conditionsPrplClass1.add(pcondClassbeg1);
        conditionsPrplClass1.add(pcondClassend1);
        EsHbaseCriteriaVo queryPrplClass1 = ORMUtils.getEsHbaseCriteria("jobposition", columns11, conditionsPrplClass1, null);
        String restPrplClass1 = RestUtils.doPost(ORM_URL, queryPrplClass1);
        List<Map<String, Object>> dataSet1 = ORMUtils.getDataSet(restPrplClass1);
        if (null != dataSet) {
            dataSet.addAll(dataSet1);
        }
        if (null == dataSet || dataSet.size() == 0) {
            return list;
        }
        Set set = new HashSet();
        for (Map<String, Object> map : dataSet) {
            Object rposDept = map.get("rpos_dept");
            List<ConditionsVo> conditionsDeptClass = new ArrayList<ConditionsVo>();
            ConditionsVo condPrplClass3 = new ConditionsVo();
            String[] columns2 = {"id"};
            condPrplClass3.setAttr("pid");
            condPrplClass3.setOperator("=");
            condPrplClass3.setValue(String.valueOf(rposDept));
            conditionsDeptClass.add(condPrplClass3);
            if (!StringUtil.isNullOrEmpty(doperater)) {
                ConditionsVo condPrplClass4 = new ConditionsVo();
                condPrplClass4.setAttr("mdep_grade_his");
                condPrplClass4.setOperator(doperater);
                condPrplClass4.setValue(dGrade);
                conditionsDeptClass.add(condPrplClass4);
            }
            ConditionsVo condPrplClass4 = new ConditionsVo();
            condPrplClass4.setAttr("mdep_beg_his");
            condPrplClass4.setOperator("<");
            condPrplClass4.setValue(df.format(new Date()));
            conditionsDeptClass.add(condPrplClass4);
            ConditionsVo condPrplClass6 = new ConditionsVo();
            condPrplClass6.setAttr("mdep_end_his");
            condPrplClass6.setOperator(">");
            condPrplClass6.setValue(df.format(new Date()));
            conditionsDeptClass.add(condPrplClass6);
            EsHbaseCriteriaVo queryDeptClass = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns2, conditionsDeptClass, null);
            String restDeptClass = RestUtils.doPost(ORM_URL, queryDeptClass);
            List<Map<String, Object>> dataDeptSet = ORMUtils.getDataSet(restDeptClass);

            List<ConditionsVo> conditionsIsNull = new ArrayList<ConditionsVo>();
            ConditionsVo condIsNull = new ConditionsVo();
            ConditionsVo condIsNull2 = new ConditionsVo();
            ConditionsVo condIsNull3 = new ConditionsVo();
            String[] columnsIsNull = {"mdep_lvl_his"};
            condIsNull.setAttr("pid");
            condIsNull.setOperator("=");
            condIsNull.setValue(String.valueOf(rposDept));
            condIsNull2.setAttr("mdep_beg_his");
            condIsNull2.setOperator("<");
            condIsNull2.setValue(df.format(new Date()));
            condIsNull3.setAttr("mdep_end_his");
            condIsNull3.setOperator("is");
            condIsNull3.setValue("null");
            if (!StringUtil.isNullOrEmpty(doperater)) {
                ConditionsVo condSetIsNull = new ConditionsVo();
                condSetIsNull.setAttr("mdep_grade_his");
                condSetIsNull.setOperator(doperater);
                condSetIsNull.setValue(dGrade);
                conditionsIsNull.add(condSetIsNull);
            }
            conditionsIsNull.add(condIsNull);
            conditionsIsNull.add(condIsNull2);
            conditionsIsNull.add(condIsNull3);
            EsHbaseCriteriaVo queryIsNull = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columnsIsNull, conditionsIsNull, null);
            String restIsNull = RestUtils.doPost(ORM_URL, queryIsNull);
            List<Map<String, Object>> dataIsNull = ORMUtils.getDataSet(restIsNull);
            if (dataIsNull.size() != 0) {
                dataDeptSet.addAll(dataIsNull);
            }
            if (dataDeptSet == null) {
                dataDeptSet = new ArrayList<>();
            }
            if (dataDeptSet.size() != 0) {
                //当前岗位id
                String id = String.valueOf(map.get("id"));
                list.add(id);
                List newList = new ArrayList();
                for (String cd : list) {
                    if (set.add(cd)) {
                        newList.add(cd);
                    }
                }
                return newList;
            }
        }
        return list;
    }


//    /**
//     * @param pGrade
//     * @param poperater
//     * @param dGrade
//     * @param doperater
//     * @return
//     * @Desc 带条件语句的查询 汇报岗位集合
//     */
//    @Override
//    public List<Jobposition> getPostList(String pGrade, String poperater, String dGrade, String doperater) {
//        List<Jobposition> postList = new ArrayList<Jobposition>();
//        Object rposPpost;
//        Object id;
//        Object rposName;
//        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
//        ConditionsVo condPrplClass2 = new ConditionsVo();
//        String[] columns = {"id", "rpos_ppost", "rpos_dept", "rpos_name"};
//        condPrplClass2.setAttr("rpos_grade");
//        condPrplClass2.setOperator(poperater);
//        condPrplClass2.setValue(pGrade);
//        conditionsPrplClass.add(condPrplClass2);
//        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("jobposition", columns, conditionsPrplClass, null);
//        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
//        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
//        if (dataSet.size() == 0 || dataSet == null) {
//            return postList;
//        } else {
//            for (Map<String, Object> map : dataSet) {
//                rposPpost = map.get("rpos_ppost");
//                id = map.get("id");
//                rposName = map.get("rpos_name");
//                Object rposDept = map.get("rpos_dept");
//                List<ConditionsVo> conditionsDeptClass = new ArrayList<ConditionsVo>();
//                ConditionsVo condPrplClass3 = new ConditionsVo();
//                ConditionsVo condPrplClass4 = new ConditionsVo();
//                String[] columns2 = {"id"};
//                condPrplClass3.setAttr("id");
//                condPrplClass3.setOperator("=");
//                condPrplClass3.setValue(String.valueOf(rposDept));
//                condPrplClass4.setAttr("mdep_grade");
//                condPrplClass4.setOperator(doperater);
//                condPrplClass4.setValue(dGrade);
//                conditionsDeptClass.add(condPrplClass3);
//                conditionsDeptClass.add(condPrplClass4);
//                EsHbaseCriteriaVo queryDeptClass = ORMUtils.getEsHbaseCriteria("depttree", columns2, conditionsDeptClass, null);
//                String restDeptClass = RestUtils.doPost(ORM_URL, queryDeptClass);
//                List<Map<String, Object>> dataDeptSet = ORMUtils.getDataSet(restDeptClass);
//                /*if(dataDeptSet == null||dataDeptSet.size()==0){
//                    return postList;
//                }*/
//                if (dataDeptSet.size() != 0) {
//                    Jobposition j = new Jobposition();
//                    j.setJobId(String.valueOf(id));
//                    j.setRposPpost(String.valueOf(rposPpost));
//                    j.setRposName(String.valueOf(rposName));
//                    postList.add(j);
//                }
//            }
//        }
//        return getPositionTree(postList);
//    }


    @Override
    public List<DeptTree> getDeptTree(String pGrade, String poperater, String dGrade, String doperater) {
        Object moniLvlCode;
        Object moniNodeName;
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass2 = new ConditionsVo();
        String[] columns = {"id", "mdep_lvl_code", "mdep_lname"};
        condPrplClass2.setAttr("mdep_grade");
        condPrplClass2.setOperator(doperater);
        condPrplClass2.setValue(dGrade);
        conditionsPrplClass.add(condPrplClass2);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("depttree", columns, conditionsPrplClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        List<DeptTree> deptTreeList = new ArrayList<DeptTree>();
        for (Map<String, Object> map : dataSet) {
            moniLvlCode = map.get("mdep_lvl_code");
            moniNodeName = map.get("mdep_lname");
            Object deptId = map.get("id");

            //先进行无条件判断 根据部门id查找 查找一个岗位对应多少个岗位
            List<ConditionsVo> conditionsDeptClass1 = new ArrayList<ConditionsVo>();
            ConditionsVo condPrplClass3 = new ConditionsVo();
            String[] columns2 = {"id"};
            condPrplClass3.setAttr("rpos_dept");
            condPrplClass3.setOperator("=");
            condPrplClass3.setValue(String.valueOf(deptId));
            conditionsDeptClass1.add(condPrplClass3);
            EsHbaseCriteriaVo queryDeptClass = ORMUtils.getEsHbaseCriteria("jobposition", columns2, conditionsDeptClass1, null);
            String restDeptClass = RestUtils.doPost(ORM_URL, queryDeptClass);
            List<Map<String, Object>> dataDeptSet = ORMUtils.getDataSet(restDeptClass);


            //在进行有条件判断 根据部门id查找  查找一个岗位对应多少个满足条件的岗位
            List<ConditionsVo> conditionsDeptClass = new ArrayList<ConditionsVo>();
            ConditionsVo condPrplClass4 = new ConditionsVo();
            ConditionsVo condPrplClass5 = new ConditionsVo();
//          String[] columns3 = {"rpos_dept"};
            String[] columns3 = {"id"};
            condPrplClass4.setAttr("rpos_dept");
            condPrplClass4.setOperator("=");
            condPrplClass4.setValue(String.valueOf(deptId));
            condPrplClass5.setAttr("rpos_grade");
            condPrplClass5.setOperator(poperater);
            condPrplClass5.setValue(pGrade);
            conditionsDeptClass.add(condPrplClass4);
            conditionsDeptClass.add(condPrplClass5);
            EsHbaseCriteriaVo queryDeptClass1 = ORMUtils.getEsHbaseCriteria("jobposition", columns3, conditionsDeptClass, null);
            String restDeptClass1 = RestUtils.doPost(ORM_URL, queryDeptClass1);
            List<Map<String, Object>> dataDeptSet1 = ORMUtils.getDataSet(restDeptClass1);

            //如果有条件的和无条件的返回结果size不相等 说明有的不能满足所有的条件限制 这个部门id不能用来拼接部门树

            if (dataDeptSet1.size() != 0 && dataDeptSet1.size() == dataDeptSet.size()) {
                DeptTree deptTreeTemp = new DeptTree();
                deptTreeTemp.setMoniLvlCode(String.valueOf(map.get("mdep_lvl_code")));
                deptTreeTemp.setMoniNodeName(String.valueOf(map.get("mdep_lname")));
                deptTreeTemp.setDeptId(String.valueOf(map.get("id")));
                deptTreeList.add(deptTreeTemp);
            }
        }


        if (deptTreeList.size() == 0) {
            //前端以数组的形式处理 返回默认为空数组 如果返回为null 报无法遍历foreach错误
//            return null;
            return deptTreeList;
        }
        //构造部门树
        List<Integer> indexs = new ArrayList<Integer>();
        for (DeptTree deptTree : deptTreeList) {
            if (deptTree == null || deptTree.getMoniLvlCode() == null) {
                continue;
            }
            List<DeptTree> tempList = new ArrayList<DeptTree>();
            for (DeptTree tree : deptTreeList) {
                if (tree == null || tree.getMoniLvlCode() == null) {
                    continue;
                }
                String code = tree.getMoniLvlCode();
                if (code.length() <= 4) {
                    continue;
                }
                if (deptTree.getMoniLvlCode().equals(code.substring(0, code.length() - 4))) {
                    indexs.add(deptTreeList.indexOf(tree));
                    tempList.add(tree);
                }
            }
            if (tempList == null || tempList.size() == 0) {
                deptTree.setChildDept(null);
            } else {
                deptTree.setChildDept(tempList);
            }
        }
        //取出部门树
        List<DeptTree> dTreelist = new ArrayList<DeptTree>();
        for (DeptTree deptTree : deptTreeList) {
            //对于长度大于1位的树过滤 取得部门树结果
            if (deptTree == null) {
                continue;
            }
            Integer i = deptTreeList.indexOf(deptTree);
            Boolean foundFlag = false;
            for (Integer index : indexs) {
                if (index.equals(i)) {
                    foundFlag = true;
                    break;
                }
            }
            if (!foundFlag) {
                dTreelist.add(deptTree);
            }
        }
        return dTreelist;

    }

    /**
     * 获取当前部门下的所有岗位
     *
     * @param str
     * @return 返回岗位的id
     */
    @Override
    public List<String> getCurrentDeptUnderJobposition(String str) {
        if (StringUtil.isNullOrEmpty(str)) {
            return null;
        }
        //主责岗位为空则获取负责人集合  负责人岗位字段是个列表
        int len = str.length();
        //部门ID
        String deptId = str.substring(0, len - 2);
        //01代表所有人，02代表主管人
        String no = str.substring(len - 2, len);
        List<ConditionsVo> conditionsClass = new ArrayList<ConditionsVo>();
        ConditionsVo condClass1 = new ConditionsVo();

        String[] columns = {"id,mdep_lvl_code,mdep_leader_post"};
        condClass1.setAttr("id");
        condClass1.setOperator("=");
        condClass1.setValue(deptId);
        conditionsClass.add(condClass1);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("depttree", columns, conditionsClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        if (CollectionUtils.isEmpty(dataSet)) {
            return null;
        }
        Map<String, Object> map = dataSet.get(0);
        String level = map.get("mdep_lvl_code").toString();

        //根据当前部门获取下级部门
        List<ConditionsVo> conditionsClass1 = new ArrayList<ConditionsVo>();
        ConditionsVo condClass2 = new ConditionsVo();
        condClass2.setAttr("mdep_lvl_code");
        condClass2.setOperator("rlike");
        condClass2.setValue(level);
        conditionsClass1.add(condClass2);
        EsHbaseCriteriaVo queryPrplClass1 = ORMUtils.getEsHbaseCriteria("depttree", columns, conditionsClass1, null);
        String restPrplClass1 = RestUtils.doPost(ORM_URL, queryPrplClass1);
        List<Map<String, Object>> dataSet1 = ORMUtils.getDataSet(restPrplClass1);
        if (CollectionUtils.isEmpty(dataSet1)) {
            return null;
        }

        List<String> newList = new ArrayList<String>();
        for (Map<String, Object> data : dataSet1) {
            //所有人
            if ("01".equals(no)) {

                //通过部门ID寻找对应岗位数据
                String[] columns1 = {"id"};
                List<ConditionsVo> conditionsClass3 = new ArrayList<ConditionsVo>();
                ConditionsVo condClass3 = new ConditionsVo();
                condClass3.setAttr("rpos_dept");
                condClass3.setOperator("=");
                condClass3.setValue(data.get("id").toString());
                conditionsClass3.add(condClass3);
                EsHbaseCriteriaVo queryPrplClass2 = ORMUtils.getEsHbaseCriteria("jobposition", columns1, conditionsClass3, null);
                String restPrplClass2 = RestUtils.doPost(ORM_URL, queryPrplClass2);
                List<Map<String, Object>> dataSet2 = ORMUtils.getDataSet(restPrplClass2);
                if (CollectionUtils.isEmpty(dataSet2)) {
                    continue;
                } else {
                    for (Map<String, Object> data1 : dataSet2) {
                        Object id = data1.get("id");
                        if (id != null) {
                            newList.add(id.toString());
                        }
                    }

                }
            } else if ("02".equals(no)) {
                //主管人
                //如果为空
                Object mdepLeaderPost = data.get("mdep_leader_post");
                if (mdepLeaderPost == null) {
                    //找属性集
                    String[] columns1 = {"mdep_chgr_post"};
                    List<ConditionsVo> conditionsClass3 = new ArrayList<ConditionsVo>();
                    ConditionsVo condClass3 = new ConditionsVo();
                    condClass3.setAttr("pid");
                    condClass3.setOperator("=");
                    condClass3.setValue(data.get("id").toString());
                    conditionsClass3.add(condClass3);
                    ConditionsVo condClass4 = new ConditionsVo();
                    condClass4.setAttr("classname");
                    condClass4.setOperator("=");
                    condClass4.setValue("depttree");
                    conditionsClass3.add(condClass4);
                    EsHbaseCriteriaVo queryPrplClass2 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chgr_set", columns1, conditionsClass3, null);
                    String restPrplClass2 = RestUtils.doPost(ORM_URL, queryPrplClass2);
                    List<Map<String, Object>> dataSet2 = ORMUtils.getDataSet(restPrplClass2);
                    if (CollectionUtils.isEmpty(dataSet2)) {
                        continue;
                    } else {
                        for (Map<String, Object> data1 : dataSet2) {
                            Object mdepChgrPost = data1.get("mdep_chgr_post");
                            if (mdepChgrPost != null) {
                                newList.add(mdepChgrPost.toString());
                            }
                        }
                    }
                } else {
                    newList.add(mdepLeaderPost.toString());
                }
            }
        }
        return newList;
    }


    @Override
    public Set<String> getCurrentDeptUnderJobposition2(String nodeDeptId, String allorOne, String pGrade, String poperater, String dGrade, String doperater) {
//        List<String> newList = new ArrayList<String>();
        //字符串数组去重复
//        List<String> resultList = new ArrayList<>();
        HashSet objects = new HashSet<>();
        //过滤出有效时间范围内的岗位id

        if (StringUtil.isNullOrEmpty(nodeDeptId)) {
            return null;
        }
        if (StringUtil.isNullOrEmpty(allorOne)) {
            return null;
        }
        String str = nodeDeptId.concat(allorOne);
        //主责岗位为空则获取负责人集合  负责人岗位字段是个列表
        int len = str.length();
        //部门ID
        String deptId = str.substring(0, len - 2);
        //01代表所有人，02代表主管人
        String no = str.substring(len - 2, len);
        //主责岗位
        //找到有效部门的levelcode
        String[] columns = {"pid,mdep_lvl_his"};
        List<ConditionsVo> conditionsClass = new ArrayList<ConditionsVo>();
        ConditionsVo condClass = new ConditionsVo();
        condClass.setAttr("pid");
        condClass.setOperator("=");
        condClass.setValue(deptId);
        //======所有部门必须满足在有效范围时间范围======//
        ConditionsVo condClass1 = new ConditionsVo();
        condClass1.setAttr("mdep_beg_his");
        condClass1.setOperator("<");
        condClass1.setValue(df.format(new Date()));
        ConditionsVo condClass2 = new ConditionsVo();
        condClass2.setAttr("mdep_end_his");
        condClass2.setOperator(">");
        condClass2.setValue(df.format(new Date()));
        ConditionsVo condClass3 = new ConditionsVo();
        condClass3.setAttr("classname");
        condClass3.setOperator("=");
        condClass3.setValue("depttree");
        //==========================================//
        conditionsClass.add(condClass);
        conditionsClass.add(condClass1);
        conditionsClass.add(condClass2);
        conditionsClass.add(condClass3);
        EsHbaseCriteriaVo queryPrplClass2 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns, conditionsClass, null);
        String restPrplClass2 = RestUtils.doPost(ORM_URL, queryPrplClass2);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass2);

        //======二次查询是否在beg与null之间
        List<ConditionsVo> conditionsClass4 = new ArrayList<ConditionsVo>();
        ConditionsVo condClass4 = new ConditionsVo();
        condClass4.setAttr("pid");
        condClass4.setOperator("=");
        condClass4.setValue(deptId);
        //======所有部门必须满足在有效范围时间范围======//
        ConditionsVo condClass5 = new ConditionsVo();
        condClass5.setAttr("mdep_beg_his");
        condClass5.setOperator("<");
        condClass5.setValue(df.format(new Date()));
        ConditionsVo condClass6 = new ConditionsVo();
        condClass6.setAttr("mdep_end_his");
        condClass6.setOperator("is");
        condClass6.setValue("null");
        ConditionsVo condClass7 = new ConditionsVo();
        condClass7.setAttr("classname");
        condClass7.setOperator("=");
        condClass7.setValue("depttree");
        //==========================================//
        conditionsClass4.add(condClass4);
        conditionsClass4.add(condClass5);
        conditionsClass4.add(condClass6);
        conditionsClass4.add(condClass7);
        EsHbaseCriteriaVo queryPrplClass3 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns, conditionsClass4, null);
        String restPrplClass3 = RestUtils.doPost(ORM_URL, queryPrplClass3);
        List<Map<String, Object>> dataSet1 = ORMUtils.getDataSet(restPrplClass3);
        dataSet.addAll(dataSet1);
        if (CollectionUtils.isEmpty(dataSet)) {
            return null;
        }
        Map<String, Object> map = dataSet.get(0);
        String level = map.get("mdep_lvl_his").toString();
        String pid = map.get("pid").toString();
        if ("02".equals(no)) {
            if (inTheLevel(pid, dGrade, doperater)) {
                List getsupreior = getsupreior(pid, pGrade, doperater, poperater, dGrade);
                List<Object> directorSet = getDirectorSet(pid, poperater, doperater, dGrade, pGrade);
                objects.addAll(getsupreior);
                objects.addAll(directorSet);
                if (CollectionUtils.isEmpty(objects)) {
                    return null;
                }
                return objects;
            } else {
                return null;
            }
        }
        //根据当前部门获取下级部门  并且判断下级部门是否有效2017-11-29
        List<ConditionsVo> conditionsClass1 = new ArrayList<ConditionsVo>();
        String[] columns4 = {"pid,mdep_lvl_his"};
        ConditionsVo condDept2 = new ConditionsVo();
        condDept2.setAttr("mdep_lvl_his");
        condDept2.setOperator("rlike");
        condDept2.setValue(level);
        conditionsClass1.add(condDept2);
        //=====添加子部门条件限制 只取有效的部门
        ConditionsVo condClass10 = new ConditionsVo();
        condClass10.setAttr("classname");
        condClass10.setOperator("=");
        condClass10.setValue("depttree");
        conditionsClass1.add(condClass10);
        ConditionsVo condClass11 = new ConditionsVo();
        condClass11.setAttr("mdep_beg_his");
        condClass11.setOperator("<");
        condClass11.setValue(df.format(new Date()));
        conditionsClass1.add(condClass11);
        ConditionsVo condClass12 = new ConditionsVo();
        condClass12.setAttr("mdep_end_his");
        condClass12.setOperator(">");
        condClass12.setValue(df.format(new Date()));
        conditionsClass1.add(condClass12);
        if (!StringUtil.isNullOrEmpty(doperater) && !StringUtil.isNullOrEmpty(dGrade)) {
            ConditionsVo condDGClass = new ConditionsVo();
            condDGClass.setAttr("mdep_grade_his");
            condDGClass.setOperator(doperater);
            condDGClass.setValue(dGrade);
            conditionsClass1.add(condDGClass);
        }
        EsHbaseCriteriaVo queryPrplClass1 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns4, conditionsClass1, null);
        String restPrplClass1 = RestUtils.doPost(ORM_URL, queryPrplClass1);
        List<Map<String, Object>> dataDSet1 = ORMUtils.getDataSet(restPrplClass1);

        //二次查询 满足start与null之间 2017-11-29
        List<ConditionsVo> conditionsClass21 = new ArrayList<ConditionsVo>();
        ConditionsVo condClass13 = new ConditionsVo();
        condClass13.setAttr("mdep_lvl_his");
        condClass13.setOperator("rlike");
        condClass13.setValue(level);
        conditionsClass21.add(condClass13);
        //=====添加子部门条件限制 只取有效的部门
        ConditionsVo condClass14 = new ConditionsVo();
        condClass14.setAttr("classname");
        condClass14.setOperator("=");
        condClass14.setValue("depttree");
        conditionsClass21.add(condClass14);
        ConditionsVo condClass15 = new ConditionsVo();
        condClass15.setAttr("mdep_beg_his");
        condClass15.setOperator("<");
        condClass15.setValue(df.format(new Date()));
        conditionsClass21.add(condClass15);
        ConditionsVo condClass16 = new ConditionsVo();
        condClass16.setAttr("mdep_end_his");
        condClass16.setOperator("is");
        condClass16.setValue("null");
        conditionsClass21.add(condClass16);
        //=====
        if (!StringUtil.isNullOrEmpty(doperater) && !StringUtil.isNullOrEmpty(dGrade)) {
            ConditionsVo condDGClass1 = new ConditionsVo();
            condDGClass1.setAttr("mdep_grade_his");
            condDGClass1.setOperator(doperater);
            condDGClass1.setValue(dGrade);
            conditionsClass21.add(condDGClass1);
        }
        EsHbaseCriteriaVo queryPrplClass21 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns4, conditionsClass21, null);
        String restPrplClass21 = RestUtils.doPost(ORM_URL, queryPrplClass21);
        List<Map<String, Object>> dataSet21 = ORMUtils.getDataSet(restPrplClass21);

        //二次查询 满足strat与null之间
        dataDSet1.addAll(dataSet21);
        for (Map<String, Object> data : dataDSet1) {
            //所有人
            if ("01".equals(no)) {

                //通过部门ID寻找对应岗位数据
                String[] columns1 = {"id"};
                List<ConditionsVo> conditionsClass3 = new ArrayList<ConditionsVo>();
                ConditionsVo condCDlass3 = new ConditionsVo();
                condCDlass3.setAttr("rpos_dept");
                condCDlass3.setOperator("=");
                condCDlass3.setValue(data.get("pid").toString());
                conditionsClass3.add(condCDlass3);
                //======所有岗位必须满足在有效范围时间范围======//
                ConditionsVo condCDlass4 = new ConditionsVo();
                condCDlass4.setAttr("rpos_beg");
                condCDlass4.setOperator("<");
                condCDlass4.setValue(df.format(new Date()));
                conditionsClass3.add(condCDlass4);

                ConditionsVo condCDlass5 = new ConditionsVo();
                condCDlass5.setAttr("rpos_end");
                condCDlass5.setOperator(">");
                condCDlass5.setValue(df.format(new Date()));
                conditionsClass3.add(condCDlass5);

                if (!StringUtil.isNullOrEmpty(poperater) && !StringUtil.isNullOrEmpty(pGrade)) {
                    ConditionsVo condPGlass = new ConditionsVo();
                    condPGlass.setAttr("rpos_grade");
                    condPGlass.setOperator(poperater);
                    condPGlass.setValue(pGrade);
                    conditionsClass3.add(condPGlass);
                }
                EsHbaseCriteriaVo queryPDClass2 = ORMUtils.getEsHbaseCriteria("jobposition", columns1, conditionsClass3, null);
                String restPDClass2 = RestUtils.doPost(ORM_URL, queryPDClass2);
                List<Map<String, Object>> dataSet2 = ORMUtils.getDataSet(restPDClass2);

                //二次查询失效时间为null开始
                String[] columns2 = {"id"};
                List<ConditionsVo> conditionsJClass4 = new ArrayList<ConditionsVo>();
                ConditionsVo condJClass6 = new ConditionsVo();
                condJClass6.setAttr("rpos_dept");
                condJClass6.setOperator("=");
                condJClass6.setValue(data.get("pid").toString());
                //======所有岗位必须满足在有效范围时间范围======//
                ConditionsVo condJClass7 = new ConditionsVo();
                condJClass7.setAttr("rpos_beg");
                condJClass7.setOperator("<");
                condJClass7.setValue(df.format(new Date()));

                ConditionsVo condClass8 = new ConditionsVo();
                condClass8.setAttr("rpos_end");
                condClass8.setOperator("is");
                condClass8.setValue("null");
                //==========================================//
                if (!StringUtil.isNullOrEmpty(poperater) && !StringUtil.isNullOrEmpty(pGrade)) {
                    ConditionsVo condPGlass = new ConditionsVo();
                    condPGlass.setAttr("rpos_grade");
                    condPGlass.setOperator(poperater);
                    condPGlass.setValue(pGrade);
                    conditionsClass3.add(condPGlass);
                }
                conditionsJClass4.add(condJClass6);
                conditionsJClass4.add(condJClass7);
                conditionsJClass4.add(condClass8);
                EsHbaseCriteriaVo queryPJClass3 = ORMUtils.getEsHbaseCriteria("jobposition", columns2, conditionsJClass4, null);
                String restPJClass3 = RestUtils.doPost(ORM_URL, queryPJClass3);
                List<Map<String, Object>> dataSet3 = ORMUtils.getDataSet(restPJClass3);
                //二次查询失效时间为null结束
                dataSet2.addAll(dataSet3);
                if (CollectionUtils.isEmpty(dataSet2)) {
                    continue;
                } else {
                    for (Map<String, Object> data1 : dataSet2) {
                        String id = (String) data1.get("id");
                        if (!StringUtil.isNullOrEmpty(id)) {
                            if (rposEmpIsNull(id)) {
                                objects.add(id);
                            }
                        }
                    }
                }
            }
        }

        return objects;
    }

    @Override
    public Boolean rposEmpIsNull(String id) {
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass1 = new ConditionsVo();
        ConditionsVo condPrplClass8 = new ConditionsVo();
        ConditionsVo condPrplClass9 = new ConditionsVo();
        String[] columns = {"id"};
        condPrplClass1.setAttr("id");
        condPrplClass1.setOperator("=");
        condPrplClass1.setValue(id);
        condPrplClass8.setAttr("rpos_emp");
        condPrplClass8.setOperator("!is");
        condPrplClass8.setValue("null");
        condPrplClass9.setAttr("rpos_emp");
        condPrplClass9.setOperator("!=");
        condPrplClass9.setValue("");
        conditionsPrplClass.add(condPrplClass1);
        conditionsPrplClass.add(condPrplClass8);
        conditionsPrplClass.add(condPrplClass9);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("jobposition", columns, conditionsPrplClass, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        if (null != dataSet && dataSet.size() != 0) {
            return true;
        }
        return false;
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveAuditRoles(AuditRoles auditRoles) {
        auditRolesSetServiceImpl.saveSetting(auditRoles);
        return auditRolesMapper.insertSelective(auditRoles);
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateAuditRoles(AuditRoles auditRoles) {
        auditRolesSetServiceImpl.updateSetting(auditRoles);
        return auditRolesMapper.updateByPrimaryKeySelective(auditRoles);
    }

    @Override
    public AuditRoles selectByPrimaryKey(String auditId) {
        return auditRolesMapper.selectByPrimaryKey(auditId);
    }

    @Override
    /**
     * 根据流程单据类id查询审核角色设置信息
     * @param classId
     * @return
     */
    public List<AuditRoles> selectByClassId(String classId) {
        return auditRolesMapper.selectByClassId(classId);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int delAuditRolesInfo(String auditId) {
        auditRolesMapper.updateIsEnaleByAuditId(auditId);
        return 1;
    }

    @Override
    public List<String> getChooseId(String classId, String superClassId, String formId, String chooseCode) {

        Result result;
        Map<String, String> datas;
        if (!StringUtil.isNullOrEmpty(superClassId) && !classId.equals(superClassId) && chooseCode.indexOf(".") != -1) {
            result = edmModeler.getEdmClass(superClassId);
            datas = (Map<String, String>) result.getData();
        } else {
            result = edmModeler.getEdmClass(classId);
            datas = (Map<String, String>) result.getData();
        }


        if (datas == null) {
            return null;
        }
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        ArrayList<String> colum = new ArrayList<String>();
        ArrayList<Condition> list = new ArrayList<Condition>();
        Condition condition1 = new Condition();
        String tableName = "";
        String scelect = "";
        //判断参数是否为属性集
        if (chooseCode.indexOf(".") == -1) {
            tableName = datas.get("edmcNameEn");
            scelect = chooseCode;
            colum.add(scelect);
            condition1.setAttr("id");
            condition1.setOperator("=");
            condition1.setValue(formId);
            list.add(condition1);
        } else {
            tableName = datas.get("edmcNameEn") + "." + chooseCode.substring(0, chooseCode.lastIndexOf("."));
            scelect = chooseCode.substring(chooseCode.lastIndexOf(".") + 1, chooseCode.length());
            colum.add(scelect);
            condition1.setAttr("pid");
            condition1.setOperator("=");
            condition1.setValue(formId);
            list.add(condition1);
        }
        searchObject.setColumns(colum);
        searchObject.setConditions(list);
        paramObject.setEdmName(tableName);
        paramObject.setSearch(searchObject);
        String s = JSON.toJSONString(paramObject);
        Result resources = edmInterface.getResources(s);
        //解析result中的值
        List<String> satisfyIdList = new ArrayList<String>();
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        if (data == null) {
            return null;
        }
        List<Map<String, String>> datasets = data.get("dataset");
        for (Map<String, String> dataset : datasets) {

            satisfyIdList.add(dataset.get(scelect));

        }
        if (satisfyIdList.size() == 0) {
            return null;
        }
        return satisfyIdList;
    }

    /**
     * 根据方式选择对应的岗位
     *
     * @param str
     * @return
     */
    private String getPositionsStr(String str) {
        int length = str.length();
        //岗位ID
        String jobId = str.substring(0, length - 2);
        //01代表所有岗位，02代表当前岗位
        String no = str.substring(length - 2, length);
        if ("01".equals(no)) {
            return getChildPositions(jobId);
        }

        return jobId + ",";
    }

    /**
     * 根据岗位id获取所有的子岗位
     *
     * @param jobId
     * @return
     */
    private String getChildPositions(String jobId) {
        List<Jobposition> list = new ArrayList<Jobposition>();
        List<Jobposition> jobpositions = getChildPositionsByJobId(jobId);
        list.addAll(jobpositions);

        while (true) {
            List<Jobposition> tempList = new ArrayList<Jobposition>();
            for (Jobposition jobposition : jobpositions) {
                List<Jobposition> t = getChildPositionsByJobId(jobposition.getJobId());
                if (t == null) {
                    continue;
                }
                tempList.addAll(t);
            }
            if (tempList == null || tempList.size() <= 0) {
                break;
            }
            jobpositions.clear();
            jobpositions.addAll(tempList);
            list.addAll(tempList);
        }

        String str = jobId + ",";
        for (Jobposition jobposition : list) {
            str = str.concat(jobposition.getJobId() + ",");
        }
        return str;
    }

    /**
     * 根据岗位id获取子岗位
     *
     * @param jobId
     * @return
     */
    private List<Jobposition> getChildPositionsByJobId(String jobId) {
        String scelect1 = "rpos_ppost";
        String scelect2 = "id";
        String scelect3 = "rpos_name";
        ArrayList<String> colum = new ArrayList<String>();
        colum.add(scelect1);
        colum.add(scelect2);
        colum.add(scelect3);
        List<Condition> conditions = new ArrayList<Condition>();
        Condition condition = new Condition();
        condition.setAttr("rpos_ppost");
        condition.setOperator("=");
        condition.setValue(jobId);
        conditions.add(condition);
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        searchObject.setColumns(colum);
        searchObject.setConditions(conditions);
        paramObject.setEdmName("jobposition");
        paramObject.setSearch(searchObject);

        String s = JSON.toJSONString(paramObject);
        //返回的是一个result
        Result resources = edmInterface.getResources(s);

        //解析result中的值
        List<Jobposition> positionList = new ArrayList<Jobposition>();
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) resources.getData();
        List<Map<String, String>> datasets = new ArrayList<>();
        if (null != data) {
            datasets = data.get("dataset");
        }
        for (Map<String, String> dataset : datasets) {
            Jobposition j = new Jobposition();
            j.setJobId(dataset.get("id"));
            j.setRposPpost(dataset.get("rpos_ppost"));
            j.setRposName(dataset.get("rpos_name"));
            positionList.add(j);
        }

        return positionList;
    }

    /**
     * @param postId 岗位id
     * @return 返回岗位id
     * @Desc 判断岗位是否有效
     */
    @Override
    public String getEffectivePost(String postId) {
        String resultId = null;
        String[] columns1 = {"id", "rpos_dept"};
        List<ConditionsVo> conditionsClass = new ArrayList<ConditionsVo>();
        ConditionsVo condClass = new ConditionsVo();
        condClass.setAttr("id");
        condClass.setOperator("=");
        condClass.setValue(postId);
        //======所有岗位必须满足在有效范围时间范围======//
        ConditionsVo condClass1 = new ConditionsVo();
        condClass1.setAttr("rpos_beg");
        condClass1.setOperator("<");
        condClass1.setValue(df.format(new Date()));

        ConditionsVo condClass2 = new ConditionsVo();
        condClass2.setAttr("rpos_end");
        condClass2.setOperator(">");
        condClass2.setValue(df.format(new Date()));
        //==========================================//
        conditionsClass.add(condClass);
        conditionsClass.add(condClass1);
        conditionsClass.add(condClass2);
        EsHbaseCriteriaVo queryPrplClass2 = ORMUtils.getEsHbaseCriteria("jobposition", columns1, conditionsClass, null);
        String restPrplClass2 = RestUtils.doPost(ORM_URL, queryPrplClass2);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass2);


        //========二次查询判断是否在beg与null之间
        String[] columns2 = {"id", "rpos_dept"};
        List<ConditionsVo> conditionsClass1 = new ArrayList<ConditionsVo>();
        ConditionsVo condClass3 = new ConditionsVo();
        condClass3.setAttr("id");
        condClass3.setOperator("=");
        condClass3.setValue(postId);
        //======所有岗位必须满足在有效范围时间范围======//
        ConditionsVo condClass4 = new ConditionsVo();
        condClass4.setAttr("rpos_beg");
        condClass4.setOperator("<");
        condClass4.setValue(df.format(new Date()));

        ConditionsVo condClass5 = new ConditionsVo();
        condClass5.setAttr("rpos_end");
        condClass5.setOperator("is");
        condClass5.setValue("null");
        //==========================================//
        conditionsClass1.add(condClass3);
        conditionsClass1.add(condClass4);
        conditionsClass1.add(condClass5);
        EsHbaseCriteriaVo queryPrplClass3 = ORMUtils.getEsHbaseCriteria("jobposition", columns2, conditionsClass1, null);
        String restPrplClass3 = RestUtils.doPost(ORM_URL, queryPrplClass3);
        List<Map<String, Object>> dataSet1 = ORMUtils.getDataSet(restPrplClass3);
        dataSet.addAll(dataSet1);
        //========二次查询判断是否在beg与null之间


        if (CollectionUtils.isEmpty(dataSet)) {
            return null;
        } else {
            for (Map<String, Object> data : dataSet) {
                String id = (String) data.get("id");
                //=====判断部门是否也有效
                Object rposDept = data.get("rpos_dept");
                String deptId = String.valueOf(rposDept);
                String isEffect = getEffectiveDept(deptId);
                //=====判断部门是否也有效 &&isEffect!=null
                if (id != null && isEffect != null) {
                    if (rposEmpIsNull(id)) {
                        resultId = id;
                    }
                }
            }
        }
        return resultId;
    }


    /**
     * @param deptId 部门id
     * @return 返回部门id
     * @Desc 判断部门是否有效
     */
    @Override
    public String getEffectiveDept(String deptId) {
        String resultId = null;
        String[] columns1 = {"pid"};
        List<ConditionsVo> conditionsClass = new ArrayList<ConditionsVo>();
        ConditionsVo condClass = new ConditionsVo();
        condClass.setAttr("pid");
        condClass.setOperator("=");
        condClass.setValue(deptId);
        //======所有部门必须满足在有效范围时间范围======//
        ConditionsVo condClass1 = new ConditionsVo();
        condClass1.setAttr("mdep_beg_his");
        condClass1.setOperator("<");
        condClass1.setValue(df.format(new Date()));
        ConditionsVo condClass2 = new ConditionsVo();
        condClass2.setAttr("mdep_end_his");
        condClass2.setOperator(">");
        condClass2.setValue(df.format(new Date()));
        ConditionsVo condClass3 = new ConditionsVo();
        condClass3.setAttr("classname");
        condClass3.setOperator("=");
        condClass3.setValue("depttree");
        //==========================================//
        conditionsClass.add(condClass);
        conditionsClass.add(condClass1);
        conditionsClass.add(condClass2);
        conditionsClass.add(condClass3);
        EsHbaseCriteriaVo queryPrplClass2 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns1, conditionsClass, null);
        String restPrplClass2 = RestUtils.doPost(ORM_URL, queryPrplClass2);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass2);

        //======二次查询是否在beg与null之间
        String[] columns2 = {"pid"};
        List<ConditionsVo> conditionsClass4 = new ArrayList<ConditionsVo>();
        ConditionsVo condClass4 = new ConditionsVo();
        condClass4.setAttr("pid");
        condClass4.setOperator("=");
        condClass4.setValue(deptId);
        //======所有部门必须满足在有效范围时间范围======//
        ConditionsVo condClass5 = new ConditionsVo();
        condClass5.setAttr("mdep_beg_his");
        condClass5.setOperator("<");
        condClass5.setValue(df.format(new Date()));
        ConditionsVo condClass6 = new ConditionsVo();
        condClass6.setAttr("mdep_end_his");
        condClass6.setOperator("is");
        condClass6.setValue("null");
        ConditionsVo condClass7 = new ConditionsVo();
        condClass7.setAttr("classname");
        condClass7.setOperator("=");
        condClass7.setValue("depttree");
        //==========================================//
        conditionsClass4.add(condClass4);
        conditionsClass4.add(condClass5);
        conditionsClass4.add(condClass6);
        conditionsClass4.add(condClass7);
        EsHbaseCriteriaVo queryPrplClass3 = ORMUtils.getEsHbaseCriteria("depttree.mdep_chag_set", columns2, conditionsClass4, null);
        String restPrplClass3 = RestUtils.doPost(ORM_URL, queryPrplClass3);
        List<Map<String, Object>> dataSet1 = ORMUtils.getDataSet(restPrplClass3);
        dataSet.addAll(dataSet1);
        //======二次查询是否在beg与null之间
        if (CollectionUtils.isEmpty(dataSet)) {
            return null;
        } else {
            for (Map<String, Object> data : dataSet) {
                Object id = data.get("pid");
                if (id != null) {
                    resultId = id.toString();
                }
            }
        }
        return resultId;
    }


}
