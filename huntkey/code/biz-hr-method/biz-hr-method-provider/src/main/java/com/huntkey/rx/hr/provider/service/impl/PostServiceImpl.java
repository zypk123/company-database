package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.DeptPostSetOderConstants;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.common.constants.WFHandlerTypeConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.CurrentSessionEntity;
import com.huntkey.rx.hr.common.model.OrderConstants;
import com.huntkey.rx.hr.common.model.OrderPostDTO;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.dao.PostDao;
import com.huntkey.rx.hr.provider.service.*;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant.PID;

/**
 * @author zhanggj
 * @createTime 2017/12/12
 * @desc
 */
@Service
public class PostServiceImpl extends BaseService implements PostService {
    @Autowired
    OrmService ormService;
    @Autowired
    PostDao postDao;
    @Autowired
    EmployeeService employee;
    @Autowired
    BizFormService bizFormService;
    @Autowired
    PostDeleteService postDeleteService;
    @Autowired
    DeptTreeService deptTreeService;

    @Autowired
    BaseService baseService;

    /**
     * 查询该部门（或所有子部门）下的岗位信息，默认查询第一层和第二层的岗位信息,以树形嵌套JSON返回。
     *
     * @param deptId  部门ID
     * @param subFlag 是否含下级部门 可为空
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JSONArray queryPostsByDeptId(String deptId, String subFlag, int level) throws Exception {
        JSONArray postListResult = new JSONArray();
        //当前部门及其子部门下的所有岗位
        List<JobpositionEntity> postListAll = this.getPostsListByDeptId(deptId, subFlag);
        if (postListAll != null && postListAll.size() > 0) {
            List<String> postAllIds = postListAll.stream().map(i -> i.getId()).collect(Collectors.toList());
            for (JobpositionEntity jobPost : postListAll) {
                String ppostId = jobPost.getRpos_ppost();
                if (StringUtil.isNullOrEmpty(ppostId) || !(postAllIds.contains(ppostId))) {
                    JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(jobPost));
                    //查询对象链接字段的name值
                    queryPostFiledValue(jsonObject);
                    queryPostsJsonByParId(jsonObject, postListAll, level - 1);
                    postListResult.add(jsonObject);
                }
            }
        }
        return postListResult;
    }

    /**
     * 递归查找当前部门岗位的所有子岗位，子岗位也要在本部门下（包括子部门）,以树形嵌套JSON返回。
     *
     * @param parPostObj  父岗位JsonObject
     * @param postListAll 当前部门下的所有岗位信息
     * @param level       查找层级
     * @throws Exception
     */
    private void queryPostsJsonByParId(JSONObject parPostObj, List<JobpositionEntity> postListAll, int level) throws Exception {
        //查找当前岗位的下级岗位
        if (postListAll != null && postListAll.size() > 0 && parPostObj != null && level > 0) {
            JSONArray subPostList = new JSONArray();
            for (JobpositionEntity jobPost : postListAll) {
                String ppostId = jobPost.getRpos_ppost();
                if (!(StringUtil.isNullOrEmpty(ppostId)) && ppostId.equals(parPostObj.getString(NodeConstant.ID))) {
                    JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(jobPost));
                    queryPostFiledValue(jsonObject);
                    subPostList.add(jsonObject);
                    queryPostsJsonByParId(jsonObject, postListAll, --level);
                }
            }
            parPostObj.put("sub_posts", subPostList);
        }
    }


    /**
     * 查询该部门下，当前岗位的子岗位,子岗位必须在该部门（或在该部门下的子部门）
     *
     * @param deptId    部门ID
     * @param parPostId 汇报岗位ID
     * @param subFlag   是否包含子部门
     * @param isSelf    是否包含当前岗位
     * @param level     查询层级数
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JSONArray queryPostsByParPostId(String deptId, String parPostId, String subFlag, String isSelf, int level) throws Exception {
        List<JobpositionEntity> postsList = new ArrayList<JobpositionEntity>();
        if (isSelf.equals("true")) {
            JobpositionEntity parPostEntity = ormService.load(JobpositionEntity.class, parPostId);
            if (parPostEntity != null) {
                postsList.add(parPostEntity);
            }
            --level;
        }
        //当前部门及其子部门下的所有岗位
        List<JobpositionEntity> postListAll = this.getPostsListByDeptId(deptId, subFlag);
        if (postListAll != null && postListAll.size() > 0) {
            queryPostsByParId(postsList, postListAll, parPostId, level);
        }
        //查询对象链接字段的name值
        JSONArray jsonArrayResult = JSONArray.parseArray(JSON.toJSONString(postsList));
        queryPostsFiledValue(jsonArrayResult, "post");
        return jsonArrayResult;
    }

    //查询当前岗位的上级岗位树
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JSONArray queryParPostsTreeById(String postId, String isSelf, int level) throws Exception {
        List<JobpositionEntity> postsListResult = new ArrayList<JobpositionEntity>();
        JobpositionEntity parPost = ormService.load(JobpositionEntity.class, postId);
        if (parPost != null) {
            if (isSelf.equals("true")) {
                postsListResult.add(parPost);
                --level;
            }
            queryParPostsById(postsListResult, parPost.getRpos_ppost(), level);
        }
        //查询对象链接字段的name值
        JSONArray jsonArrayResult = JSONArray.parseArray(JSON.toJSONString(postsListResult));
        queryPostsFiledValue(jsonArrayResult, "post");
        return jsonArrayResult;
    }

    //根据岗位ID查询岗位信息
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JSONArray queryPostsByIds(String[] ids) throws Exception {
        List<JobpositionEntity> posts = postDao.queryPostsById(ids);
        JSONArray postsArray = JSONArray.parseArray(JSON.toJSONString(posts));
        queryPostsFiledValue(postsArray, "post");
        return postsArray;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JSONArray getPostsByDeptId(String deptId, String subFlag) throws Exception {
        //当前部门及其子部门下的所有岗位
        List<JobpositionEntity> postsList = this.getPostsListByDeptId(deptId, subFlag);
        JSONArray jsonArrayResult = JSONArray.parseArray(JSON.toJSONString(postsList));
        queryPostsFiledValue(jsonArrayResult, "post");
        return jsonArrayResult;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JSONArray getPostsEmpByDeptId(String deptId, String subFlag) throws Exception {
        JSONArray jobPositions = getPostsByDeptId(deptId, subFlag);
        JSONArray resultArr = new JSONArray();
        if (jobPositions != null && jobPositions.size() > 0) {
            for (int i = 0; i < jobPositions.size(); i++) {
                JSONObject jsonObject = jobPositions.getJSONObject(i);
                if (StringUtils.isNotBlank(jsonObject.getString(JobpositionProperty.RPOS_EMP))) {
                    queryPostFiledValue(jsonObject);
                    resultArr.add(jsonObject);
                }
            }
            return resultArr;
        }
        return resultArr;
    }

    //根据岗位ID,取岗位的汇报岗位的任职人的姓名和工号
    @Override
    public JSONArray queryParPostsEmpByIds(String[] ids) throws Exception {
        JSONArray jsonArrayResult = new JSONArray();
        List<JobpositionEntity> jobPositions = postDao.queryPostsById(ids);
        for (int i = 0; i < jobPositions.size(); i++) {
            JobpositionEntity post = jobPositions.get(0);
            List<JobpositionEntity> parPosts = postDao.queryPostsById(new String[]{post.getRpos_ppost()});
            if (parPosts != null && parPosts.size() > 0) {
                JobpositionEntity parPost = parPosts.get(0);
                JSONObject jsonPost = JSON.parseObject(JSON.toJSONString(parPost));
                jsonPost.put("subId", post.getId());
                jsonArrayResult.add(jsonPost);
            }
        }
        queryPostsFiledValue(jsonArrayResult, "post");
        return jsonArrayResult;
    }

    /**
     * 查询岗位树,如果岗位ID为空，就查询所有岗位
     *
     * @param parPostId 岗位ID
     * @param isSelf    是否包含父节点，默认包含
     * @param level     查询层级数
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JSONArray queryPostsTreeByParPostId(String parPostId, String isSelf, int level) throws Exception {
        List<JobpositionEntity> postsListResult = new ArrayList<JobpositionEntity>();
        if (StringUtils.isBlank(parPostId)) {
            List<JobpositionEntity> postListAll = postDao.queryAllPosts();
            if (postListAll != null && postListAll.size() > 0) {
                List<String> postAllIds = postListAll.stream().map(i -> i.getId()).collect(Collectors.toList());
                for (JobpositionEntity jobPost : postListAll) {
                    String ppostId = jobPost.getRpos_ppost();
                    if (StringUtil.isNullOrEmpty(ppostId) || !(postAllIds.contains(ppostId))) {
                        postsListResult.add(jobPost);
                        this.querySubPostByParPostId(postsListResult, jobPost.getId(), --level);
                    }
                }
            }
        } else {
            JobpositionEntity parPost = ormService.load(JobpositionEntity.class, parPostId);
            if (parPost != null) {
                if (isSelf.equals("true")) {
                    postsListResult.add(parPost);
                    --level;
                }
                this.querySubPostByParPostId(postsListResult, parPostId, level);
            }
        }
        //查询对象链接字段的name值
        JSONArray jsonArrayResult = JSONArray.parseArray(JSON.toJSONString(postsListResult));
        queryPostsFiledValue(jsonArrayResult, "post");
        return jsonArrayResult;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JSONObject queryPostSetOderById(String orderId) throws Exception {
        DeptpostsetorderEntity deptpostsetorderEntity = ormService.load(DeptpostsetorderEntity.class, orderId);
        JSONObject orderJson = null;
        if (deptpostsetorderEntity != null) {
            orderJson = JSON.parseObject(JSON.toJSONString(deptpostsetorderEntity));
            queryOrderFiledValue(orderJson);
        }
        return orderJson;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JSONArray queryOrderPostsSetByOrderId(String orderId) throws Exception {
        List<OdpsOdpsDpostSetaEntity> postsList = postDao.queryOrderPostsByPid(orderId);
        JSONArray postsArray = JSONArray.parseArray(JSON.toJSONString(postsList));
        queryPostsFiledValue(postsArray, "order");
        return postsArray;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public String savePost(JobpositionEntity jobpositionEntity) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        jobpositionEntity.setRpos_beg(new Date());
        jobpositionEntity.setRpos_end(DateUtil.parseFormatDate("9999-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        jobpositionEntity.setCretime(new Date());//创建时间
        jobpositionEntity.setCreuser(sessionEntity.getEmployeeId());//创建人
        jobpositionEntity.setEdmd_ente(sessionEntity.getEnterpriseId());//企业对象
        String newPostId = ormService.insert(jobpositionEntity).toString();
        jobpositionEntity.setId(newPostId);
        //保存岗位变更记录集
        savePostsChagSetToJobpost(jobpositionEntity);
        return newPostId;
    }

    private void querySubPostByParPostId(List<JobpositionEntity> postsListResult, String parPostId, int level) throws Exception {
        if (StringUtils.isNotBlank(parPostId) && level > 0) {
            List<JobpositionEntity> postList = postDao.queryPostsByColId(JobpositionProperty.RPOS_PPOST, parPostId);
            if (postList != null && postList.size() > 0) {
                for (int i = 0; i < postList.size(); i++) {
                    JobpositionEntity post = postList.get(i);
                    postsListResult.add(post);
                    querySubPostByParPostId(postsListResult, post.getId(), --level);
                }
            }
        } else {
            return;
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public String deletePost(String postId) throws Exception {
        //根据岗位编码查询岗位
        List<JobpositionEntity> postList = postDao.queryPostsByColId(NodeConstant.ID, postId);
        JobpositionEntity postEntity = null;
        OrmParam ormParam = null;

        if (postList != null && postList.size() > 0) {
            postEntity = postList.get(0);
        } else {
            return "根据岗位ID'" + postId + "'查询岗位失败";
        }
        //1.根据岗位ID更新岗位的失效日期当天
        postEntity.setRpos_end(new Date());
        ormService.updateSelective(postEntity);
        postDao.updatePostsChagSetByPid(postId);
        //2.员工任岗经历最新一条设置为当天
        //查询员工类
        JSONObject empJson = employee.queryEmployeeById(postEntity.getRpos_emp(), false, false);
        //根据ID查询员工的任岗经历 根据生效日期降序排序
        if (empJson != null) {
            //将员工状态 和岗位置空
            EmployeeEntity emp = JSONObject.parseObject(JSONObject.toJSONString(empJson), EmployeeEntity.class);
            emp.setRemp_status("");//员工待岗
            emp.setRemp_post("");//岗位空
            emp.setRemp_pgrade("");//岗级空
            ormService.updateSelective(emp);

            ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(PID, emp.getId())));
            ormParam.setOrderExp(SQLSortEnum.DESC, RempRempPostSetaProperty.REMP_POST_BEG);
            List<RempRempPostSetaEntity> listPostSet = ormService.selectBeanList(RempRempPostSetaEntity.class, ormParam);
            if (listPostSet != null && listPostSet.size() > 0) {
                RempRempPostSetaEntity postSet = listPostSet.get(0);
                postSet.setRemp_post_end(new Date());
                ormService.updateSelective(postSet);
            }
        }
        return "注销岗位成功";
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public JSONObject saveAddOrder(String data) throws Exception {
        return saveOrder(data, DeptPostSetOderConstants.ODPS_TYPE_0);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public JSONObject saveEditOrder(String data) throws Exception {
        return saveOrder(data, DeptPostSetOderConstants.ODPS_TYPE_1);
    }

    //部门岗位新增单据提交方法
    @Override
    public Result submitPostAddOrder(String data) throws Exception {
        //返回单据信息
        JSONObject orderJson = this.saveAddOrder(data);
        return submitFlow(orderJson);
    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result submitFlow(JSONObject orderJson) throws Exception {
        bizFormService.submitWorkFlow(orderJson.getString(OrderProperty.ORDE_RODE_OBJ), orderJson.getString(NodeConstant.ID));
        this.updateOrderStatus(orderJson.getString(NodeConstant.ID), OrderConstants.ORDE_STATUS_2);
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(orderJson);
        return result;
    }


    @Override
    public Result submitPostEditOrder(String data) throws Exception {
        //返回单据信息
        JSONObject orderJson = this.saveEditOrder(data);
        return submitFlow(orderJson);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public List<String> passAddOrder(String orderId) throws Exception {
        List<String> resultList = new ArrayList<>();
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        //查询新增的岗位树的根节点
        OdpsOdpsDpostSetaEntity rootPost = queryRootPostByOrderId(orderId);
        if (rootPost != null) {
            //保存岗位设置单中的新增岗位至岗位表中
            saveAddPostsToJobpost(resultList, rootPost.getId(), rootPost.getOdps_lvl(), sessionEntity);
        }
        this.updateOrderStatus(orderId, OrderConstants.ORDE_STATUS_5);
        return resultList;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public List<String> passEditOrder(String orderId) throws Exception {
        List<String> resultList = new ArrayList<>();
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        List<OdpsOdpsDpostSetaEntity> orderPosts = postDao.queryOrderPostsByPid(orderId);
        saveEditPostsToJobpost(resultList, orderPosts, sessionEntity);
        this.updateOrderStatus(orderId, OrderConstants.ORDE_STATUS_5);
        return resultList;
    }

    @Override
    public Result pass(String orderInstanceId, String handlerType) throws Exception {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<String> resultdatas = new ArrayList<>();
        JSONObject orderJson = this.queryPostSetOderById(orderInstanceId);
        String orderType = orderJson.getString(DeptpostsetorderProperty.ODPS_TYPE);
        if (StringUtils.isNotBlank(orderType)) {
            switch (handlerType) {
                case WFHandlerTypeConstants.PASS: {
                    //TODO 单据状态改为 完成；将单据数据写入资源表
                    if ("0".equals(orderType)) {
                        resultdatas = this.passAddOrder(orderInstanceId);
                        result.setData(resultdatas);
                    }
                    if ("1".equals(orderType)) {
                        resultdatas = this.passEditOrder(orderInstanceId);
                        result.setData(resultdatas);
                    }
                    if ("3".equals(orderType)) {
                        result = postDeleteService.pass(orderInstanceId, handlerType);
                    }
                    break;
                }
                case WFHandlerTypeConstants.REVOKE: {
                    //TODO 单据状态改为 待提
                    this.updateOrderStatus(orderInstanceId, DeptPostSetOderConstants.ORDER_STATUS_1);
                    break;
                }
                case WFHandlerTypeConstants.RETURN_BACK: {
                    //TODO 单据状态改为 退回
                    this.updateOrderStatus(orderInstanceId, DeptPostSetOderConstants.ORDER_STATUS_6);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return result;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int updateOrderStatus(String orderId, String status) throws Exception {
        DeptpostsetorderEntity order = new DeptpostsetorderEntity();
        order.setId(orderId);
        order.setOrde_status(status);
        int resultNum = ormService.updateSelective(order);
        return resultNum;
    }

    /**
     * 保存岗位树(可递归保存多级)，新增单据和修改单据方法共用
     *
     * @param data    树形嵌套的岗位JSON信息
     * @param optType 操作类型，“C”新增 “M”修改
     * @return 单据信息的JSONObject
     */
    private JSONObject saveOrder(String data, String optType) throws Exception {
        //返回保存后的岗位新增单据信息
        JSONObject resultOrderObj = new JSONObject();
        List<String> ids_result = new ArrayList<>();

        //获取单据信息
        JSONObject orderJson = JSONObject.parseObject(data);
        //获取单据的岗位嵌套结构的数据集
        JSONArray firstPosts = JSONArray.parseArray(orderJson.getString("postdatas"));
        if (orderJson != null && firstPosts != null && firstPosts.size() > 0) {
            String orderId = orderJson.getString(NodeConstant.ID);
            //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
            CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
            //岗位新增单据信息
            DeptpostsetorderEntity deptpostsetorder = new DeptpostsetorderEntity();
            deptpostsetorder.setOdps_type(optType);
            deptpostsetorder.setOdps_beg(new Date());
            deptpostsetorder.setOdps_remark(orderJson.getString(DeptpostsetorderProperty.ODPS_REMARK));
            deptpostsetorder.setOrde_status(DeptPostSetOderConstants.ORDER_STATUS_1);
            deptpostsetorder.setOdps_dept_beg(orderJson.getString(DeptpostsetorderProperty.ODPS_DEPT_BEG));
            deptpostsetorder.setOrde_dept(orderJson.getString(OrderProperty.ORDE_DEPT));
            deptpostsetorder.setEdmd_ente(sessionEntity.getEnterpriseId());//企业对象
            deptpostsetorder.setOrde_rode_obj(orderJson.getString(OrderProperty.ORDE_RODE_OBJ));//单据定义对象
            deptpostsetorder.setOrde_adduser(orderJson.getString(OrderProperty.ORDE_ADDUSER));//制单人
            deptpostsetorder.setOrde_duty(orderJson.getString(OrderProperty.ORDE_DUTY));//制单岗位
            deptpostsetorder.setOrde_date(new Date());//制单时间

            //如果传入的岗位设置单的单据ID有值，就说明是单据的再次保存或提交单据的保存。
            //在此情况下，单据信息只是修改，单据的属性集会先删除，后保存。
            if (StringUtils.isNotBlank(orderId)) {
                postDao.deleteOrderPostsByOrderId(orderId);
                deptpostsetorder.setId(orderJson.getString(NodeConstant.ID));
                deptpostsetorder.setOrde_nbr(orderJson.getString(OrderProperty.ORDE_NBR));
                deptpostsetorder.setModtime(new Date());//修改时间
                deptpostsetorder.setModuser(sessionEntity.getEmployeeId());//修改人
                //新增岗位单据信息修改
                int i = ormService.updateSelective(deptpostsetorder);
            } else {//如果单据信息的ID为空，说明是单据第一次保存
                deptpostsetorder.setCretime(new Date());//创建时间
                deptpostsetorder.setCreuser(sessionEntity.getEmployeeId());//创建人
                //生成单据号
                String ordeTpyeStr = "";
                if (optType.equals(DeptPostSetOderConstants.ODPS_TYPE_0)) {
                    ordeTpyeStr = NumberConstants.PREFIX_DEPT_POST_SET_ORDER_ADD;
                } else {
                    ordeTpyeStr = NumberConstants.PREFIX_DEPT_POST_SET_ORDER_MODIFY;
                }
                deptpostsetorder.setOrde_nbr(getOrderNbr(ordeTpyeStr));
                orderId = ormService.insert(deptpostsetorder).toString();
                deptpostsetorder.setId(orderId);
            }
            if (StringUtils.isNotBlank(orderId)) {
                //递归保存岗位属性集信息
                String isRoot = "";
                if (optType.equals(DeptPostSetOderConstants.ODPS_TYPE_0)) {
                    isRoot = "true";
                } else if (optType.equals(DeptPostSetOderConstants.ODPS_TYPE_1)) {
                    isRoot = "false";
                }
                addOrderPosts(ids_result, deptpostsetorder, firstPosts, "", isRoot);
                resultOrderObj = this.queryPostSetOderById(orderId);
            }
        } else {
            throw new ApplicationException(Result.RECODE_ERROR, "部门岗位设置单保存错误,无岗位数据");
        }
        return resultOrderObj;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    //加载岗位新增单据
    @Override
    public JSONArray loadAddOrder(String orderId) throws Exception {
        List<OdpsOdpsDpostSetaEntity> postsList = postDao.queryOrderPostsByPid(orderId);
        JSONArray orderPostsArray = JSONArray.parseArray(JSON.toJSONString(postsList));
        queryPostsFiledValue(orderPostsArray, "order");
        return orderPostsArray;
    }


    /**
     * 保存单据中岗位属性集信息
     *
     * @param ids       返回保存成功记录的ID
     * @param postOrder 单据信息
     * @param postDatas 岗位子数据集
     * @param parId     岗位的汇报岗位ID
     * @param isRoot    第一次调用标识为根节点
     */
    private void addOrderPosts(List<String> ids, DeptpostsetorderEntity postOrder, JSONArray postDatas, String parId, String isRoot) throws Exception {
        if (postDatas == null || postDatas.size() == 0) {
            return;
        }
        for (int i = 0; i < postDatas.size(); i++) {
            JSONObject postData = postDatas.getJSONObject(i);
            OdpsOdpsDpostSetaEntity orderPost = JSONObject.parseObject(JSONObject.toJSONString(postData), OdpsOdpsDpostSetaEntity.class);

            String odpsflag = orderPost.getOdps_flag();
            //判断是否是新增岗位
            if (odpsflag != null && odpsflag.equals(DeptPostSetOderConstants.ODPS_FLAG_1) && DeptPostSetOderConstants.ODPS_TYPE_0.equals(postOrder.getOdps_type())) {
                //如果是新增岗位，系统生成岗位编码
                String postCode = baseService.getQuartersCode();
                orderPost.setOdps_post(postCode);
            }
            orderPost.setPid(postOrder.getId());
            //odps_lvl字段存储非新增岗位的ID，用于后续读取。
            if (StringUtils.isBlank(orderPost.getOdps_lvl())) {
                orderPost.setOdps_lvl(orderPost.getId());
            }
            //保存该单据的根节点,根节点的上级岗位存储原来的值
            if (isRoot.equals("true")) {
                orderPost.setOdps_flag(DeptPostSetOderConstants.ODPS_FLAG_3);
            } else if (DeptPostSetOderConstants.ODPS_TYPE_0.equals(postOrder.getOdps_type())) {
                orderPost.setOdps_ppost(parId);
            }
            //存储岗位设置单的岗位信息
            orderPost.setId("");//新增记录前，把ID设置为空，会重新生成ID
            EdmUtil.setPropertyBaseEntitySysColumns(DeptpostsetorderEntity.class, postOrder, orderPost, SQLCurdEnum.INSERT);
            String orderPostId = ormService.insert(orderPost).toString();
            if (orderPostId == null) {
                throw new ApplicationException(Result.RECODE_ERROR, "岗位设置单岗位信息保存错误");
            }
            ids.add(orderPostId);
            JSONArray subposts = JSONArray.parseArray(postData.getString("subdatas"));
            //递归调用保存方法，保存下级岗位信息。
            addOrderPosts(ids, postOrder, subposts, orderPostId, "false");
        }
    }

    private List<JobpositionEntity> getPostsListByDeptId(String deptId, String subFlag) throws Exception {
        List<String> deptIds = new ArrayList<String>();
        if (StringUtils.isBlank(subFlag) || subFlag.equals("0")) {//不“含下级”
            deptIds.add(deptId);
        } else if (subFlag.equals("1")) {//含下级部门
            deptIds = queryDeptIdsByParId(deptId);
        }
        //当前部门及其子部门下的所有岗位
        List<JobpositionEntity> postsList = postDao.queryPostsByDeptIds(deptIds.toArray(new String[deptIds.size()]));
        return postsList;
    }

    //递归查找岗位的所有上级岗位
    private void queryParPostsById(List<JobpositionEntity> postsListResult, String postId, int level) throws Exception {
        if (StringUtils.isNotBlank(postId) && level > 0) {
            List<JobpositionEntity> postList = postDao.queryPostsByColId(NodeConstant.ID, postId);
            if (postList != null && postList.size() > 0) {
                for (int i = 0; i < postList.size(); i++) {
                    JobpositionEntity post = postList.get(i);
                    postsListResult.add(post);
                    queryParPostsById(postsListResult, post.getRpos_ppost(), --level);
                }
            }
        } else {
            return;
        }
    }

    //递归查找当前部门岗位的所有子岗位，子岗位也要在本部门下（包括子部门）,返回列表。
    private void queryPostsByParId(List<JobpositionEntity> jobPosts, List<JobpositionEntity> postListAll, String parPostId, int level) throws Exception {
        //查找当前岗位的下级岗位
        if (postListAll != null && postListAll.size() > 0 && parPostId != null && level > 0) {
            for (JobpositionEntity jobPost : postListAll) {
                String ppostId = jobPost.getRpos_ppost();
                if (!(StringUtil.isNullOrEmpty(ppostId)) && ppostId.equals(parPostId)) {
                    jobPosts.add(jobPost);
                    queryPostsByParId(jobPosts, postListAll, jobPost.getId(), --level);
                }
            }
        }
    }

    /**
     * 根据当前部门ID查找所有子部门的ID（包括当前部门ID）
     *
     * @param deptId
     * @return
     */
    private List<String> queryDeptIdsByParId(String deptId) throws Exception {
        List<String> deptIds = new ArrayList<String>();
        if (StringUtils.isBlank(deptId)) {
            return null;
        } else {
            deptIds.add(deptId);
            querySubDeptIdsByParId(deptIds, deptId);
            //调用部门类的查询下级部门的方法
            //deptTreeService.getDeptLowerIdList(deptIds,deptId,new Date(), Integer.MAX_VALUE);
        }
        return deptIds;
    }

    //递归查找部门的所有下级部门
    private void querySubDeptIdsByParId(List<String> deptIds, String parDeptId) throws Exception {
        if (StringUtils.isBlank(parDeptId)) {
            return;
        } else {
            List<DepttreeEntity> depts = postDao.queryRecordById(DepttreeEntity.class, DepttreeProperty.MDEP_PAR, parDeptId);
            if (depts != null && depts.size() > 0) {
                for (DepttreeEntity dept : depts) {
                    deptIds.add(dept.getId());
                    querySubDeptIdsByParId(deptIds, dept.getId());
                }
            }
        }
    }

    /**
     * 查询新增的岗位树的根节点
     *
     * @param orderId 单据ID
     * @return
     */
    private OdpsOdpsDpostSetaEntity queryRootPostByOrderId(String orderId) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, orderId),
                ormParam.getEqualXML(OdpsOdpsDpostSetaProperty.ODPS_FLAG, DeptPostSetOderConstants.ODPS_FLAG_3));
        ormParam.setWhereExp(whereCondition)
                .addOrderExpElement(SQLSortEnum.ASC, OdpsOdpsDpostSetaProperty.ODPS_PGRADE)
                .addOrderExpElement(SQLSortEnum.ASC, OdpsOdpsDpostSetaProperty.ODPS_POST);
        List<OdpsOdpsDpostSetaEntity> posts = ormService.selectBeanList(OdpsOdpsDpostSetaEntity.class, ormParam);
        return posts.get(0);
    }


    //保存新增的岗位至目标表,返回List<String>
    private void saveAddPostsToJobpost(List<String> resultList, String orderPostId, String parId, CurrentSessionEntity sessionEntity) throws Exception {
        List<OdpsOdpsDpostSetaEntity> subPosts = queryOrderSubPosts(orderPostId);
        if (subPosts != null && subPosts.size() > 0) {
            for (OdpsOdpsDpostSetaEntity post : subPosts) {
                //如果是新增岗位，先保存该岗位信息，得到ID，然后利用ID再保存子岗位。
                if (post.getOdps_flag().equals("1")) {
                    JobpositionEntity newPost = new JobpositionEntity();
                    newPost.setRpos_name(post.getOdps_name());
                    newPost.setRpos_dept(post.getOdps_dept());
                    newPost.setRpos_rpof(post.getOdps_rpof());
                    newPost.setRpos_grade(post.getOdps_pgrade());
                    newPost.setRpos_code(post.getOdps_post());
                    newPost.setRpos_ppost(parId);
                    newPost.setRpos_duty(post.getOdps_duty());
                    newPost.setRpos_qual(post.getOdps_qual());
                    newPost.setRpos_beg(new Date());
                    newPost.setRpos_end(DateUtil.parseFormatDate("9999-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss"));
                    newPost.setCretime(new Date());//创建时间
                    newPost.setCreuser(sessionEntity.getEmployeeId());//创建人
                    newPost.setEdmd_ente(sessionEntity.getEnterpriseId());//企业对象
                    String newPostId = ormService.insert(newPost).toString();
                    newPost.setId(newPostId);
                    //保存岗位变更记录集
                    savePostsChagSetToJobpost(newPost);
                    resultList.add(newPostId);
                    //递归保存岗位信息
                    saveAddPostsToJobpost(resultList, post.getId(), newPostId, sessionEntity);
                } else {//如果不是新增岗位，递归调用保存方法，保存子岗位
                    saveAddPostsToJobpost(resultList, post.getId(), post.getOdps_lvl(), sessionEntity);
                }
            }
        } else {
            return;
        }
    }

    //保存修改的岗位至目标表
    private void saveEditPostsToJobpost(List<String> resultList, List<OdpsOdpsDpostSetaEntity> orderPosts, CurrentSessionEntity sessionEntity) throws Exception {

        if (orderPosts != null && orderPosts.size() > 0) {
            for (OdpsOdpsDpostSetaEntity post : orderPosts) {
                JobpositionEntity newPost = new JobpositionEntity();
                String postId = post.getOdps_lvl();
                newPost.setId(postId);
                newPost.setRpos_name(post.getOdps_name());
                newPost.setRpos_dept(post.getOdps_dept());
                newPost.setRpos_rpof(post.getOdps_rpof());
                newPost.setRpos_grade(post.getOdps_pgrade());
                newPost.setRpos_code(post.getOdps_post());
                newPost.setRpos_ppost(post.getOdps_ppost());
                newPost.setRpos_duty(post.getOdps_duty());
                newPost.setRpos_qual(post.getOdps_qual());
                newPost.setModtime(new Date());//修改时间
                newPost.setModuser(sessionEntity.getEmployeeId());//修改人
                newPost.setEdmd_ente(sessionEntity.getEnterpriseId());//企业对象
                ormService.updateSelective(newPost);
                savePostsChagSetToJobpost(newPost);
                resultList.add(postId);
            }
        }
    }

    private List<OdpsOdpsDpostSetaEntity> queryOrderSubPosts(String ppost) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = ormParam.getEqualXML(OdpsOdpsDpostSetaProperty.ODPS_PPOST, ppost);
        ormParam.setWhereExp(whereCondition)
                .addOrderExpElement(SQLSortEnum.ASC, OdpsOdpsDpostSetaProperty.ODPS_PGRADE)
                .addOrderExpElement(SQLSortEnum.ASC, OdpsOdpsDpostSetaProperty.ODPS_POST);
        List<OdpsOdpsDpostSetaEntity> posts = ormService.selectBeanList(OdpsOdpsDpostSetaEntity.class, ormParam);
        return posts;
    }

    /**
     * 查询岗位信息中字段的Value值
     *
     * @param postsList
     * @param className post:表示查询岗位表中的字段信息  order:表示查询单据属性集岗位表中字段信息
     */
    private void queryPostsFiledValue(JSONArray postsList, String className) throws Exception {
        if (postsList != null && postsList.size() > 0) {
            for (int i = 0; i < postsList.size(); i++) {
                JSONObject jsonObject = postsList.getJSONObject(i);
                if (className.equals("post")) {
                    queryPostFiledValue(jsonObject);
                } else if (className.equals("order")) {
                    queryOrderPostFiledValue(jsonObject);
                } else {
                }
            }
        }
    }

    //查询岗位表中对象链接字段的Value
    private void queryPostFiledValue(JSONObject postObj) throws Exception {
        String empId = postObj.getString(JobpositionProperty.RPOS_EMP);
        String positionId = postObj.getString(JobpositionProperty.RPOS_RPOF);
        String deptId = postObj.getString(JobpositionProperty.RPOS_DEPT);
        String parPostId = postObj.getString(JobpositionProperty.RPOS_PPOST);
        postObj.put(JobpositionProperty.RPOS_EMP + "_name", "");
        postObj.put(JobpositionProperty.RPOS_EMP + "_empno", "");
        postObj.put(JobpositionProperty.RPOS_RPOF + "_name", "");
        postObj.put(JobpositionProperty.RPOS_RPOF + "_type", "");
        postObj.put(JobpositionProperty.RPOS_DEPT + "_name", "");
        postObj.put(JobpositionProperty.RPOS_DEPT + "_deptno", "");
        postObj.put(JobpositionProperty.RPOS_PPOST + "_name", "");
        if (StringUtils.isNotBlank(empId)) {
            List<EmployeeEntity> emps = postDao.queryRecordById(EmployeeEntity.class, NodeConstant.ID, empId);
            if (emps != null && emps.size() > 0) {
                EmployeeEntity emp = emps.get(0);
                postObj.put(JobpositionProperty.RPOS_EMP + "_name", emp.getRemp_name());
                postObj.put(JobpositionProperty.RPOS_EMP + "_empno", emp.getRemp_no());
            }
        } else {
            postObj.put(JobpositionProperty.RPOS_EMP, "");
        }
        if (StringUtils.isNotBlank(positionId)) {
            List<PositiondefinitionEntity> positions = postDao.queryRecordById(PositiondefinitionEntity.class, NodeConstant.ID, positionId);
            if (positions != null && positions.size() > 0) {
                PositiondefinitionEntity position = positions.get(0);
                postObj.put(JobpositionProperty.RPOS_RPOF + "_name", position.getRpof_name());
                postObj.put(JobpositionProperty.RPOS_RPOF + "_type", position.getRpof_type());
            }
        } else {
            postObj.put(JobpositionProperty.RPOS_RPOF, "");
        }
        if (StringUtils.isNotBlank(deptId)) {
            List<DepttreeEntity> depts = postDao.queryRecordById(DepttreeEntity.class, NodeConstant.ID, deptId);
            if (depts != null && depts.size() > 0) {
                DepttreeEntity dept = depts.get(0);
                postObj.put(JobpositionProperty.RPOS_DEPT + "_name", dept.getMdep_name());
                postObj.put(JobpositionProperty.RPOS_DEPT + "_deptno", dept.getMdep_code());
            }
        } else {
            postObj.put(JobpositionProperty.RPOS_DEPT, "");
        }
        if (StringUtils.isNotBlank(parPostId)) {
            List<JobpositionEntity> parPosts = postDao.queryRecordById(JobpositionEntity.class, NodeConstant.ID, parPostId);
            if (parPosts != null && parPosts.size() > 0) {
                JobpositionEntity parPost = parPosts.get(0);
                postObj.put(JobpositionProperty.RPOS_PPOST + "_name", parPost.getRpos_name());
            }
        } else {
            postObj.put(JobpositionProperty.RPOS_PPOST, "");
        }

    }


    //查询单据属性集岗位表中对象链接字段的value
    private void queryOrderPostFiledValue(JSONObject postObj) throws Exception {
        String oldParPostId = postObj.getString(OdpsOdpsDpostSetaProperty.ODPS_PPOST_OLD);
        String positionId = postObj.getString(OdpsOdpsDpostSetaProperty.ODPS_RPOF);
        String deptId = postObj.getString(OdpsOdpsDpostSetaProperty.ODPS_DEPT);
        String parPostId = postObj.getString(OdpsOdpsDpostSetaProperty.ODPS_PPOST);
        postObj.put(OdpsOdpsDpostSetaProperty.ODPS_PPOST_OLD + "_name", "");
        postObj.put(OdpsOdpsDpostSetaProperty.ODPS_RPOF + "_name", "");
        postObj.put(OdpsOdpsDpostSetaProperty.ODPS_RPOF + "_type", "");
        postObj.put(OdpsOdpsDpostSetaProperty.ODPS_DEPT + "_name", "");
        postObj.put(OdpsOdpsDpostSetaProperty.ODPS_DEPT + "_deptno", "");
        postObj.put(OdpsOdpsDpostSetaProperty.ODPS_PPOST + "_name", "");
        if (StringUtils.isNotBlank(oldParPostId)) {
            List<JobpositionEntity> oldParPosts = postDao.queryRecordById(JobpositionEntity.class, NodeConstant.ID, oldParPostId);
            if (oldParPosts != null && oldParPosts.size() > 0) {
                JobpositionEntity oldParPost = oldParPosts.get(0);
                postObj.put(OdpsOdpsDpostSetaProperty.ODPS_PPOST_OLD + "_name", oldParPost.getRpos_name());
            }
        } else {
            postObj.put(OdpsOdpsDpostSetaProperty.ODPS_PPOST_OLD, "");
        }
        if (StringUtils.isNotBlank(positionId)) {
            List<PositiondefinitionEntity> positions = postDao.queryRecordById(PositiondefinitionEntity.class, NodeConstant.ID, positionId);
            if (positions != null) {
                PositiondefinitionEntity position = positions.get(0);
                postObj.put(OdpsOdpsDpostSetaProperty.ODPS_RPOF + "_name", position.getRpof_name());
                postObj.put(OdpsOdpsDpostSetaProperty.ODPS_RPOF + "_type", position.getRpof_type());

            }
        } else {
            postObj.put(OdpsOdpsDpostSetaProperty.ODPS_RPOF, "");
        }
        if (StringUtils.isNotBlank(deptId)) {
            List<DepttreeEntity> depts = postDao.queryRecordById(DepttreeEntity.class, NodeConstant.ID, deptId);
            if (depts != null && depts.size() > 0) {
                DepttreeEntity dept = depts.get(0);
                postObj.put(OdpsOdpsDpostSetaProperty.ODPS_DEPT + "_name", dept.getMdep_name());
                postObj.put(OdpsOdpsDpostSetaProperty.ODPS_DEPT + "_deptno", dept.getMdep_code());
            }
        } else {
            postObj.put(OdpsOdpsDpostSetaProperty.ODPS_DEPT, "");
        }
        if (StringUtils.isNotBlank(parPostId)) {
            List<JobpositionEntity> parPosts = postDao.queryRecordById(JobpositionEntity.class, NodeConstant.ID, parPostId);
            if (parPosts != null && parPosts.size() > 0) {
                JobpositionEntity parPost = parPosts.get(0);
                postObj.put(OdpsOdpsDpostSetaProperty.ODPS_PPOST + "_name", parPost.getRpos_name());
            }
        } else {
            postObj.put(OdpsOdpsDpostSetaProperty.ODPS_PPOST, "");
        }
    }


    //查询单据表中对象链接字段的value
    private void queryOrderFiledValue(JSONObject ordeObj) throws Exception {
        String orde_dept = ordeObj.getString(OrderProperty.ORDE_DEPT);
        String odps_dept_beg = ordeObj.getString(DeptpostsetorderProperty.ODPS_DEPT_BEG);
        String orde_duty = ordeObj.getString(OrderProperty.ORDE_DUTY);
        String orde_adduser = ordeObj.getString(OrderProperty.ORDE_ADDUSER);
        ordeObj.put(OrderProperty.ORDE_DEPT + "_name", "");
        ordeObj.put(OrderProperty.ORDE_DEPT + "_deptno", "");
        ordeObj.put(DeptpostsetorderProperty.ODPS_DEPT_BEG + "_name", "");
        ordeObj.put(DeptpostsetorderProperty.ODPS_DEPT_BEG + "_deptno", "");
        ordeObj.put(OrderProperty.ORDE_DUTY + "_name", "");
        ordeObj.put(OrderProperty.ORDE_ADDUSER + "_name", "");
        ordeObj.put(OrderProperty.ORDE_ADDUSER + "_empno", "");
        if (StringUtils.isNotBlank(orde_dept)) {
            List<DepttreeEntity> depts = postDao.queryRecordById(DepttreeEntity.class, NodeConstant.ID, orde_dept);
            if (depts != null && depts.size() > 0) {
                DepttreeEntity dept = depts.get(0);
                ordeObj.put(OrderProperty.ORDE_DEPT + "_name", dept.getMdep_name());
                ordeObj.put(OrderProperty.ORDE_DEPT + "_deptno", dept.getMdep_code());
            }
        } else {
            ordeObj.put(OrderProperty.ORDE_DEPT, "");
        }
        if (StringUtils.isNotBlank(odps_dept_beg)) {
            List<DepttreeEntity> depts = postDao.queryRecordById(DepttreeEntity.class, NodeConstant.ID, odps_dept_beg);
            if (depts != null && depts.size() > 0) {
                DepttreeEntity dept = depts.get(0);
                ordeObj.put(DeptpostsetorderProperty.ODPS_DEPT_BEG + "_name", dept.getMdep_name());
                ordeObj.put(DeptpostsetorderProperty.ODPS_DEPT_BEG + "_deptno", dept.getMdep_code());
            }
        } else {
            ordeObj.put(DeptpostsetorderProperty.ODPS_DEPT_BEG, "");
        }
        if (StringUtils.isNotBlank(orde_duty)) {
            List<JobpositionEntity> posts = postDao.queryRecordById(JobpositionEntity.class, NodeConstant.ID, orde_duty);
            if (posts != null && posts.size() > 0) {
                JobpositionEntity post = posts.get(0);
                ordeObj.put(OrderProperty.ORDE_DUTY + "_name", post.getRpos_name());
            }
        } else {
            ordeObj.put(OrderProperty.ORDE_DUTY, "");
        }
        if (StringUtils.isNotBlank(orde_adduser)) {
            List<EmployeeEntity> emps = postDao.queryRecordById(EmployeeEntity.class, NodeConstant.ID, orde_adduser);
            if (emps != null && emps.size() > 0) {
                EmployeeEntity emp = emps.get(0);
                ordeObj.put(OrderProperty.ORDE_ADDUSER + "_name", emp.getRemp_name());
                ordeObj.put(OrderProperty.ORDE_ADDUSER + "_empno", emp.getRemp_no());
            }
        } else {
            ordeObj.put(OrderProperty.ORDE_ADDUSER, "");
        }
    }

    //保存岗位至目标表的变更历史表中
    private String savePostsChagSetToJobpost(JobpositionEntity jobpositionEntity) throws Exception {
        String postChagId = null;
        postDao.updatePostsChagSetByPid(jobpositionEntity.getId());
        if (jobpositionEntity != null) {
            RposRposChagSetaEntity rposRposChagSetaEntity = new RposRposChagSetaEntity();
            rposRposChagSetaEntity.setPid(jobpositionEntity.getId());
            rposRposChagSetaEntity.setRpos_name_his(jobpositionEntity.getRpos_name());
            rposRposChagSetaEntity.setRpos_post_his(jobpositionEntity.getRpos_rpof());
            rposRposChagSetaEntity.setRpos_prep_his(jobpositionEntity.getRpos_ppost());
            rposRposChagSetaEntity.setRpos_grade_his(jobpositionEntity.getRpos_grade());
            rposRposChagSetaEntity.setRpos_duty_his(jobpositionEntity.getRpos_duty());
            rposRposChagSetaEntity.setRpos_qual_his(jobpositionEntity.getRpos_qual());
            rposRposChagSetaEntity.setRpos_beg_his(new Date());
            rposRposChagSetaEntity.setRpos_end_his(DateUtil.parseFormatDate("9999-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss"));
            EdmUtil.setPropertyBaseEntitySysColumns(JobpositionEntity.class, jobpositionEntity, rposRposChagSetaEntity, SQLCurdEnum.INSERT);
            postChagId = ormService.insert(rposRposChagSetaEntity).toString();
        }
        return postChagId;
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<String> isExistCheckPostOrder(String data) throws Exception {
        String[] postids = new String[]{};
        String[] newPostids = new String[]{};
        //提交校验的返回信息
        String submitCheckMes = "";
        JSONObject paramJson = JSONObject.parseObject(data);
        String postidsParam = paramJson.getString("postIds");
        if (postidsParam != null) {
            postids = postidsParam.split(",");
        }
        String newPostidsParam = paramJson.getString("newPostIds");//如果是修改操作且是提交操作，需传入新的汇报岗位ID
        if (newPostidsParam != null) {
            newPostids = newPostidsParam.split(",");
        }
        String orderType = paramJson.getString("orderType");//"C"新增岗位，“M”修改岗位
        String optType = paramJson.getString("optType");//"S"非提交操作，“T”提交操作
        List<String> postOrderResult = new ArrayList<>();
        List<OrderPostDTO> OrderPostsResult = new ArrayList<>();
        if (postids != null && postids.length > 0) {
            List<DeptpostsetorderEntity> allPostCheckOrders = queryAllPostCheckOrders();
            if (orderType.equals("C")) {//如果是岗位新增，S操作传入的是列表选择的岗位ID，T操作传入的是新增岗位的汇报岗位ID
                OrderPostsResult = queryCheckOrderByPosts(postids, allPostCheckOrders);
                if (OrderPostsResult != null && OrderPostsResult.size() > 0) {
                    postOrderResult.add(isExistsResultTip(OrderPostsResult.get(0), "1"));
                    return postOrderResult;
                } else {
                    OrderPostsResult = queryParPostDelCheckOrder(postids, allPostCheckOrders);
                    if (OrderPostsResult != null && OrderPostsResult.size() > 0) {
                        postOrderResult.add(isExistsResultTip(OrderPostsResult.get(0), "2"));
                        return postOrderResult;
                    }
                }
                if (optType.equals("T")) {
                    //如果是岗位提交 新增只需要校验上级岗位是否存在
                    submitCheckMes = checkPostExists(postids, 1);
                    if (!StringUtil.isNullOrEmpty(submitCheckMes)) {
                        postOrderResult.add(submitCheckMes);
                        return postOrderResult;
                    }
                }
            } else if (orderType.equals("M")) {//如果是岗位修改
                OrderPostsResult = queryCheckOrderByPosts(postids, allPostCheckOrders);
                if (OrderPostsResult != null && OrderPostsResult.size() > 0) {
                    postOrderResult.add(isExistsResultTip(OrderPostsResult.get(0), "1"));
                    return postOrderResult;
                } else {
                    OrderPostsResult = queryParPostDelCheckOrder(postids, allPostCheckOrders);
                    if (OrderPostsResult != null && OrderPostsResult.size() > 0) {
                        postOrderResult.add(isExistsResultTip(OrderPostsResult.get(0), "2"));
                        return postOrderResult;
                    }
                    if (optType.equals("T")) {//如果是岗位提交
                        if (postids != null && postids.length > 0) {
                            //校验当前岗位是否被删除
                            submitCheckMes = checkPostExists(postids, 0);
                            if (!StringUtil.isNullOrEmpty(submitCheckMes)) {
                                postOrderResult.add(submitCheckMes);
                                return postOrderResult;
                            }
                            OrderPostsResult = queryCheckOrderByPosts(newPostids, allPostCheckOrders);
                            if (OrderPostsResult != null && OrderPostsResult.size() > 0) {
                                postOrderResult.add(isExistsResultTip(OrderPostsResult.get(0), "3"));
                                return postOrderResult;
                            } else {
                                OrderPostsResult = queryParPostDelCheckOrder(newPostids, allPostCheckOrders);
                                if (OrderPostsResult != null && OrderPostsResult.size() > 0) {
                                    postOrderResult.add(isExistsResultTip(OrderPostsResult.get(0), "4"));
                                    return postOrderResult;
                                }
                            }
                            //校验当前岗位汇报岗位是否被删除
                            submitCheckMes = checkPostExists(newPostids, 0);
                            if (!StringUtil.isNullOrEmpty(submitCheckMes)) {
                                postOrderResult.add(submitCheckMes);
                                return postOrderResult;
                            }
                        }
                    }
                }
            } else {
                postOrderResult.add("校验类型参数传入错误");
                return postOrderResult;
            }
        }
        return postOrderResult;
    }

    //校验结果的返回提示
    private String isExistsResultTip(OrderPostDTO orderPostDTO, String tipType) {
        //如果是校验上级注销
        String resultStr = "";
        if (tipType.equals("1")) {
            resultStr = orderPostDTO.getPostName()
                    + "/" + orderPostDTO.getPostNbr()
                    + "存在待审单据，单号：" + orderPostDTO.getOrderNbr();
        }
        if (tipType.equals("2")) {
            resultStr = "上级岗位【" + orderPostDTO.getPostName()
                    + "/" + orderPostDTO.getPostNbr()
                    + "】存在下级岗位一同注销的待审单,单号：" + orderPostDTO.getOrderNbr();
        }
        if (tipType.equals("3")) {
            resultStr = "当前岗位的汇报岗位【" + orderPostDTO.getPostName()
                    + "/" + orderPostDTO.getPostNbr()
                    + "】存在待审单据，单号：" + orderPostDTO.getOrderNbr();
        }
        if (tipType.equals("4")) {
            resultStr = "当前岗位的汇报岗位的上级岗位【" + orderPostDTO.getPostName()
                    + "/" + orderPostDTO.getPostNbr()
                    + "】存在下级岗位一同注销的待审单,单号：" + orderPostDTO.getOrderNbr();
        }
        return resultStr;
    }

    //判断岗位岗位是否删除
    private String checkPostExists(String[] listPost, int checkSup) {
        String retMes = "";
        if (listPost != null && listPost.length > 0) {
            for (String postId : listPost) {
                try {
                    List<JobpositionEntity> list = postDao.queryPostsByColId(EdmSysColumn.ID, postId);
                    if (list != null && list.size() > 0) {
                        if (checkSup == 1) {
                            //校验汇报上级是否存在
                            retMes = checkPostExists(new String[]{list.get(0).getRpos_ppost()}, 0);
                        }
                    } else {
                        JobpositionEntity entity = ormService.load(JobpositionEntity.class, postId);
                        retMes = "岗位:【" + entity.getRpos_name() + "】不存在,该单据不能提交！";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return retMes;
    }

    //判断所传的POST，是否有"修改"、“注销”的待审单据
    private List<OrderPostDTO> queryCheckOrderByPosts(String[] postids, List<DeptpostsetorderEntity> allPostCheckOrders) throws Exception {
        List<JobpositionEntity> postsArr = postDao.queryPostsById(postids);
        List<OrderPostDTO> resultPostCheckOrder = new ArrayList<>();
        if (postsArr != null && postsArr.size() > 0) {
            if (allPostCheckOrders != null && allPostCheckOrders.size() > 0) {
                resultPostCheckOrder = isExistCheckOrderByPostCodes(null, postsArr, allPostCheckOrders);
                if (resultPostCheckOrder != null && resultPostCheckOrder.size() > 0) {
                    return resultPostCheckOrder;
                }
            }
        }
        return resultPostCheckOrder;
    }

    //判断岗位的所有父岗位有无“注销”且含下级的待审单据
    private List<OrderPostDTO> queryParPostDelCheckOrder(String[] postids, List<DeptpostsetorderEntity> allPostCheckOrders) throws Exception {
        List<OrderPostDTO> resultPostCheckOrder = new ArrayList<>();
        List<JobpositionEntity> postsArr = postDao.queryPostsById(postids);
        if (postsArr != null && postsArr.size() > 0) {
            //查询所有类型为“注销”的待审单据，同时查找该单据下所有选项=含下级的岗位
            List<DeptpostsetorderEntity> changePostOrders = queryAllDelPostCheckOrders(allPostCheckOrders);
            if (changePostOrders != null && changePostOrders.size() > 0) {
                for (int i = 0; i < postsArr.size(); i++) {
                    //查询岗位的上级所有岗位
                    JSONArray parPosts = queryParPostsTreeById(postsArr.get(i).getId(), "false", Integer.MAX_VALUE);
                    if (parPosts != null && parPosts.size() > 0) {
                        //判断上级所有岗位存不存在“注销”且选项=含下级的单据
                        resultPostCheckOrder = isExistCheckOrderByPostCodes(postsArr.get(i), JSONObject.parseArray(parPosts.toJSONString(), JobpositionEntity.class), changePostOrders);
                        return resultPostCheckOrder;
                    }
                }
            }
        }
        return resultPostCheckOrder;
    }


    //查询所有待审单据
    private List<DeptpostsetorderEntity> queryAllPostCheckOrders() throws Exception {
        List<DeptpostsetorderEntity> changePostOrders = new ArrayList<>();
        String[] orderTypes = new String[]{DeptPostSetOderConstants.ODPS_TYPE_0, DeptPostSetOderConstants.ODPS_TYPE_1, DeptPostSetOderConstants.ODPS_TYPE_3};
        String[] orderStatus = new String[]{DeptPostSetOderConstants.ORDER_STATUS_2, DeptPostSetOderConstants.ORDER_STATUS_3, DeptPostSetOderConstants.ORDER_STATUS_4};
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getInXML(DeptpostsetorderProperty.ODPS_TYPE, orderTypes),
                ormParam.getInXML(OrderProperty.ORDE_STATUS, orderStatus));
        ormParam.setWhereExp(whereCondition).addOrderExpElement(SQLSortEnum.ASC, DeptpostsetorderProperty.ODPS_BEG);
        changePostOrders = ormService.selectBeanList(DeptpostsetorderEntity.class, ormParam);
        if (changePostOrders != null && changePostOrders.size() > 0) {
            for (DeptpostsetorderEntity order : changePostOrders) {
                List<OdpsOdpsDpostSetaEntity> orderPosts = ormService.getAttrubuteSetByPID(order.getId(), OdpsOdpsDpostSetaEntity.class, DeptpostsetorderEntity.class);
                if (orderPosts != null && orderPosts.size() > 0) {
                    if (order.getOdps_type().equals(DeptPostSetOderConstants.ODPS_TYPE_1)) {//如果是岗位新增单据，查询新增岗位的汇报岗位
                        List<String> pposts = new ArrayList<>();
                        List<OdpsOdpsDpostSetaEntity> filterOrderPosts = new ArrayList<>();
                        for (OdpsOdpsDpostSetaEntity post : orderPosts) {
                            if (post.getOdps_flag() != null && post.getOdps_flag().equals(DeptPostSetOderConstants.ODPS_FLAG_1)) {
                                pposts.add(post.getOdps_ppost());
                            }
                        }
                        for (OdpsOdpsDpostSetaEntity post : orderPosts) {
                            if (post.getOdps_flag() != null && !(post.getOdps_flag().equals(DeptPostSetOderConstants.ODPS_FLAG_1)) && pposts.contains(post.getId())) {
                                filterOrderPosts.add(post);
                            }
                        }
                        order.setOdps_dpost_set(filterOrderPosts);
                    }
                    order.setOdps_dpost_set(orderPosts);
                }
            }
        }
        return changePostOrders;
    }


    //查询所有类型为“注销”的待审单据，同时查找该单据下所有选项=含下级的岗位
    private List<DeptpostsetorderEntity> queryAllDelPostCheckOrders(List<DeptpostsetorderEntity> allPostCheckOrders) throws Exception {
        List<DeptpostsetorderEntity> changePostOrders = new ArrayList<>();
        if (allPostCheckOrders != null && allPostCheckOrders.size() > 0) {
            for (DeptpostsetorderEntity order : allPostCheckOrders) {
                if (order.getOdps_type() != null && order.getOdps_type().equals(DeptPostSetOderConstants.ODPS_TYPE_3)) {
                    List<OdpsOdpsDpostSetaEntity> orderPosts = order.getOdps_dpost_set();
                    List<OdpsOdpsDpostSetaEntity> changeOrderPostsSet = new ArrayList<>();
                    if (orderPosts != null && orderPosts.size() > 0) {
                        for (OdpsOdpsDpostSetaEntity post : orderPosts) {
                            if (post.getOdps_sub() != null && post.getOdps_sub().equals(DeptPostSetOderConstants.ODPS_SUB_1)) {
                                changeOrderPostsSet.add(post);
                            }
                        }
                        if (changeOrderPostsSet != null && changeOrderPostsSet.size() > 0) {
                            order.setOdps_dpost_set(changeOrderPostsSet);
                            changePostOrders.add(order);
                        }

                    }
                }
            }
        }
        return changePostOrders;
    }

    private List<OrderPostDTO> isExistCheckOrderByPostCodes(JobpositionEntity selectPost, List<JobpositionEntity> posts,
                                                            List<DeptpostsetorderEntity> changePostOrders) {
        List<OrderPostDTO> resultPostCheckOrder = new ArrayList<>();
        if (posts != null && posts.size() > 0 && changePostOrders != null && changePostOrders.size() > 0) {
            for (int i = 0; i < posts.size(); i++) {
                for (DeptpostsetorderEntity order : changePostOrders) {
                    List<OdpsOdpsDpostSetaEntity> orderPosts = order.getOdps_dpost_set();
                    List<String> orderPostsCode = null;
                    if (orderPosts != null && orderPosts.size() > 0) {
                        orderPostsCode = order.getOdps_dpost_set().stream().map(post -> post.getOdps_lvl()).collect(Collectors.toList());
                    }
                    if (orderPostsCode != null && orderPostsCode.size() > 0 && orderPostsCode.contains(posts.get(i).getId())) {
                        OrderPostDTO orderPostDTO = new OrderPostDTO();
                        orderPostDTO.setOrderId(order.getId());
                        orderPostDTO.setOrderNbr(order.getOrde_nbr());
                        orderPostDTO.setPostId(posts.get(i).getId());
                        orderPostDTO.setPostName(posts.get(i).getRpos_name());
                        orderPostDTO.setPostNbr(posts.get(i).getRpos_code());
                        resultPostCheckOrder.add(orderPostDTO);
                        break;
                    }
                }
            }
        }
        return resultPostCheckOrder;
    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public Result audit(JSONObject auditSet) {
        Result result = new Result();
        String auditKey = auditSet.getString(DeptPostSetOderConstants.PARAM_AUDITKEY);
        String formState = auditSet.getString(DeptPostSetOderConstants.PARAM_FORMSTATE);
        String actInstanceId = auditSet.getString(DeptPostSetOderConstants.PARAM_ACT_INSTANCE_ID);
        String opinion = NullUtils.valueOf(auditSet.getString(DeptPostSetOderConstants.PARAM_OPINION));
        if (StringUtil.isNullOrEmpty(auditKey)) {
            result.setErrMsg("请传入参数" + DeptPostSetOderConstants.PARAM_AUDITKEY);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        } else if (StringUtil.isNullOrEmpty(formState)) {
            result.setErrMsg("请传入参数" + DeptPostSetOderConstants.PARAM_FORMSTATE);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        } else if (StringUtil.isNullOrEmpty(actInstanceId)) {
            result.setErrMsg("请传入参数" + DeptPostSetOderConstants.PARAM_ACT_INSTANCE_ID);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        }
        // 调用流程
        bizFormService.audit(actInstanceId, opinion, auditKey);
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public JobpositionEntity queryPostById(String id) throws Exception {
        return ormService.load(JobpositionEntity.class, id);
    }

    /**
     * 根据岗位编码，查询该岗位有没有岗位注销的待审单
     *
     * @param postId
     * @param ifParent “false”标识只查询本岗位，“true”标识包含岗位的所有上级岗位有没有注销且含下级
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Map<String, Object>> isExistDelCheckOrderByPostId(String postId, String ifParent) throws Exception {
        Boolean ifParentFlag = Boolean.valueOf(ifParent);
        JobpositionEntity jobpositionEntity = ormService.load(JobpositionEntity.class, postId);
        String postCode = "";
        List<Map<String, Object>> orders = null;
        if (jobpositionEntity != null) {
            postCode = jobpositionEntity.getRpos_code();

        } else {
            return orders;
        }
        orders = getDelOrdersByPostCode(postCode, Boolean.valueOf("false"));
        if (orders != null && orders.size() > 0) {
            return orders;
        }
        if (ifParentFlag) {
            orders = getParDelOrdersByPostCode(jobpositionEntity.getRpos_ppost());
        }
        return orders;
    }


    //查询该岗位有没有岗位注销的待审单
    //ifParent “false”标识只查询本岗位，“true”标识包含岗位的所有上级岗位有没有注销且含下级
    private List<Map<String, Object>> getDelOrdersByPostCode(String postCode, boolean ifParent) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT a.orde_nbr,b.odps_post  FROM deptpostsetorder as a");
        sb.append(" LEFT JOIN odps_odps_dpost_seta  as b on b.pid = a.id");
        sb.append(" WHERE b.odps_post = '" + postCode + "'");
        if (ifParent) {
            sb.append(" and b.odps_sub = '1'");
            sb.append(" and a.odps_type = '3'");
        } else {
            sb.append(" and a.odps_type in ('1','3')");
        }
        sb.append(" and orde_status in ('2','3','4')");
        sb.append(" and a.is_del = 0 and b.is_del = 0");
        List<Map<String, Object>> rs = ormService.getDataBySql(sb.toString());
        return rs;
    }

    //递归查询岗位的所有上级岗位有没有注销且含下级
    private List<Map<String, Object>> getParDelOrdersByPostCode(String postId) throws Exception {
        List<Map<String, Object>> order = null;
        JobpositionEntity parPosts = ormService.load(JobpositionEntity.class, postId);
        if (parPosts != null && StringUtils.isNotBlank(parPosts.getRpos_code()) && StringUtils.isNotBlank(parPosts.getRpos_ppost())) {
            order = getDelOrdersByPostCode(parPosts.getRpos_code(), true);
            if (order != null && order.size() > 0) {
                return order;
            } else {
                getParDelOrdersByPostCode(parPosts.getRpos_ppost());
            }
        }
        return order;
    }


    @Override
    public JSONObject savePostAddOrderForOozie(String data) throws Exception {
        String optType = DeptPostSetOderConstants.ODPS_TYPE_0;
        //返回保存后的岗位新增单据信息
        JSONObject resultOrderObj = new JSONObject();
        List<String> ids_result = new ArrayList<>();

        //获取单据信息
        JSONObject orderJson = JSONObject.parseObject(data);
        //获取单据的岗位嵌套结构的数据集
        JSONArray firstPosts = JSONArray.parseArray(orderJson.getString("postdatas"));
        if (orderJson != null && firstPosts != null && firstPosts.size() > 0) {
            String orderId = orderJson.getString(NodeConstant.ID);
            //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
            //岗位新增单据信息
            DeptpostsetorderEntity deptpostsetorder = new DeptpostsetorderEntity();
            deptpostsetorder.setOdps_type(optType);
            deptpostsetorder.setOdps_beg(new Date());
            deptpostsetorder.setOdps_remark(orderJson.getString(DeptpostsetorderProperty.ODPS_REMARK));
            deptpostsetorder.setOrde_status(DeptPostSetOderConstants.ORDER_STATUS_1);
            deptpostsetorder.setOdps_dept_beg(orderJson.getString(DeptpostsetorderProperty.ODPS_DEPT_BEG));
            deptpostsetorder.setOrde_dept(orderJson.getString(OrderProperty.ORDE_DEPT));
            deptpostsetorder.setEdmd_ente("d36f1d0129a34e4e96c9feaa06d4e48c");//企业对象
            deptpostsetorder.setOrde_rode_obj(orderJson.getString(OrderProperty.ORDE_RODE_OBJ));//单据定义对象
            deptpostsetorder.setOrde_adduser(orderJson.getString(OrderProperty.ORDE_ADDUSER));//制单人
            deptpostsetorder.setOrde_duty(orderJson.getString(OrderProperty.ORDE_DUTY));//制单岗位
            deptpostsetorder.setOrde_date(new Date());//制单时间

            //如果传入的岗位设置单的单据ID有值，就说明是单据的再次保存或提交单据的保存。
            //在此情况下，单据信息只是修改，单据的属性集会先删除，后保存。
            if (StringUtils.isNotBlank(orderId)) {
                postDao.deleteOrderPostsByOrderId(orderId);
                deptpostsetorder.setId(orderJson.getString(NodeConstant.ID));
                deptpostsetorder.setOrde_nbr(orderJson.getString(OrderProperty.ORDE_NBR));
                deptpostsetorder.setModtime(new Date());//修改时间
                deptpostsetorder.setModuser("c40301f7d5a211e7bbd9005056b7c0c3");//修改人
                //新增岗位单据信息修改
                int i = ormService.updateSelective(deptpostsetorder);
            } else {//如果单据信息的ID为空，说明是单据第一次保存
                deptpostsetorder.setCretime(new Date());//创建时间
                deptpostsetorder.setCreuser("c40301f7d5a211e7bbd9005056b7c0c3");//创建人
                //生成单据号
                String ordeTpyeStr = "";
                if (optType.equals(DeptPostSetOderConstants.ODPS_TYPE_0)) {
                    ordeTpyeStr = NumberConstants.PREFIX_DEPT_POST_SET_ORDER_ADD;
                } else {
                    ordeTpyeStr = NumberConstants.PREFIX_DEPT_POST_SET_ORDER_MODIFY;
                }
                deptpostsetorder.setOrde_nbr(getOrderNbr(ordeTpyeStr));
                orderId = ormService.insert(deptpostsetorder).toString();
                deptpostsetorder.setId(orderId);
            }
            if (StringUtils.isNotBlank(orderId)) {
                //递归保存岗位属性集信息
                String isRoot = "";
                if (optType.equals(DeptPostSetOderConstants.ODPS_TYPE_0)) {
                    isRoot = "true";
                } else if (optType.equals(DeptPostSetOderConstants.ODPS_TYPE_1)) {
                    isRoot = "false";
                }
                addOrderPosts(ids_result, deptpostsetorder, firstPosts, "", isRoot);
                resultOrderObj = this.queryPostSetOderById(orderId);
            }
        } else {
            throw new ApplicationException(Result.RECODE_ERROR, "部门岗位设置单保存错误,无岗位数据");
        }
        return resultOrderObj;
    }

    @Override
    public List<String> passAddOrderForOozie(String odpsRemark, String odpsType, String ordeStatus, String odpsName) throws Exception {
        List<String> resultList = new ArrayList<>();

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT x.* FROM deptpostsetorder x join odps_odps_dpost_seta y on x.id=y.pid WHERE ");
        sb.append(" x.odps_remark LIKE '%" + odpsRemark + "%'");
        sb.append(" and x.odps_type='" + odpsType + "'");
        sb.append(" and x.orde_status='" + ordeStatus + "'");
        sb.append(" and y.odps_name like '%" + odpsName + "%'");
        List<Map<String, Object>> rs = ormService.getDataBySql(sb.toString());
        String orderId = (String) rs.get(0).get("id");
        //查询新增的岗位树的根节点
        OdpsOdpsDpostSetaEntity rootPost = queryRootPostByOrderId(orderId);
        if (rootPost != null) {
            //保存岗位设置单中的新增岗位至岗位表中
            saveAddPostsToJobpostForOozie(resultList, rootPost.getId(), rootPost.getOdps_lvl());
        }
        this.updateOrderStatus(orderId, "5");
        return resultList;
    }


    private void saveAddPostsToJobpostForOozie(List<String> resultList, String orderPostId, String parId) throws Exception {
        List<OdpsOdpsDpostSetaEntity> subPosts = queryOrderSubPosts(orderPostId);
        if (subPosts != null && subPosts.size() > 0) {
            for (OdpsOdpsDpostSetaEntity post : subPosts) {
                //如果是新增岗位，先保存该岗位信息，得到ID，然后利用ID再保存子岗位。
                if (post.getOdps_flag().equals("1")) {
                    JobpositionEntity newPost = new JobpositionEntity();
                    newPost.setRpos_name(post.getOdps_name());
                    newPost.setRpos_dept(post.getOdps_dept());
                    newPost.setRpos_rpof(post.getOdps_rpof());
                    newPost.setRpos_grade(post.getOdps_pgrade());
                    newPost.setRpos_code(post.getOdps_post());
                    newPost.setRpos_ppost(parId);
                    newPost.setRpos_duty(post.getOdps_duty());
                    newPost.setRpos_qual(post.getOdps_qual());
                    newPost.setRpos_beg(new Date());
                    newPost.setRpos_end(DateUtil.parseFormatDate("9999-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss"));
                    newPost.setCretime(new Date());//创建时间
                    newPost.setCreuser("c40301f7d5a211e7bbd9005056b7c0c3");//创建人
                    newPost.setEdmd_ente("d36f1d0129a34e4e96c9feaa06d4e48c");//企业对象
                    String newPostId = ormService.insert(newPost).toString();
                    newPost.setId(newPostId);
                    resultList.add(newPostId);
                    //递归保存岗位信息
                    saveAddPostsToJobpostForOozie(resultList, post.getId(), newPostId);
                } else {//如果不是新增岗位，递归调用保存方法，保存子岗位
                    saveAddPostsToJobpostForOozie(resultList, post.getId(), post.getOdps_lvl());
                }
            }
        } else {
            return;
        }
    }
}
