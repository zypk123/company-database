package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.hr.common.model.OrderPostDTO;

import java.util.List;
import java.util.Map;


/**
 * @author zhanggj
 * @createTime 2017/12/12
 * @desc
 */
public interface PostService {

    /**
     * 查询该部门（或所有子部门）下，level层的岗位树，默认第一层和第二层的岗位信息
     *
     * @param deptId  部门ID
     * @param subFlag 是否含下级部门 可为空
     * @param level   查询层级数
     * @return
     */
    JSONArray queryPostsByDeptId(String deptId, String subFlag, int level) throws Exception;

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
    JSONArray queryPostsByParPostId(String deptId, String parPostId, String subFlag, String isSelf, int level) throws Exception;

    //查询当前岗位的上级岗位树
    JSONArray queryParPostsTreeById(String postId, String isSelf, int level) throws Exception;

    //根据岗位ID查询岗位信息
    JSONArray queryPostsByIds(String[] ids) throws Exception;

    //查询指定部门岗位清单
    JSONArray getPostsByDeptId(String deptId, String subFlag) throws Exception;

    //统计指定部门岗位在编人数
    JSONArray getPostsEmpByDeptId(String deptId, String subFlag) throws Exception;

    //根据岗位ID,取岗位的汇报岗位的任职人的姓名和工号
    JSONArray queryParPostsEmpByIds(String[] ids) throws Exception;

    //岗位树查询
    JSONArray queryPostsTreeByParPostId(String parPostId, String isSelf, int level) throws Exception;

    //根据ID查询岗位设置单
    JSONObject queryPostSetOderById(String orderId) throws Exception;

    //根据单据ID查询单据的岗位属性集
    JSONArray queryOrderPostsSetByOrderId(String orderId) throws Exception;

    //保存单个岗位信息
    String savePost(JobpositionEntity jobpositionEntity) throws Exception;

    //根据岗位编码注销岗位
    String deletePost(String postId) throws Exception;

    /**
     * 部门岗位新增单据保存方法
     */
    JSONObject saveAddOrder(String data) throws Exception;

    /**
     * 加载岗位新增单据
     *
     * @param orderId
     * @return
     */
    JSONArray loadAddOrder(String orderId) throws Exception;

    //部门岗位修改单据保存方法
    JSONObject saveEditOrder(String data) throws Exception;

    Result submitPostAddOrder(String data) throws Exception;

    Result submitPostEditOrder(String data) throws Exception;

    List<String> passAddOrder(String orderId) throws Exception;

    List<String> passEditOrder(String orderId) throws Exception;

    /**
     * 岗位设置单通过
     *
     * @param orderInstanceId 单据实例ID
     * @param handlerType     处理类型
     * @return
     */
    Result pass(String orderInstanceId, String handlerType) throws Exception;

    int updateOrderStatus(String orderId, String status) throws Exception;

    List<String> isExistCheckPostOrder(String data) throws Exception;

    Result audit(JSONObject auditSet) throws Exception;

    JobpositionEntity queryPostById(String id) throws Exception;

    List<Map<String, Object>> isExistDelCheckOrderByPostId(String postCode, String ifParent) throws Exception;

    JSONObject savePostAddOrderForOozie(String data) throws Exception;

    List<String> passAddOrderForOozie(String odpsRemark, String odpsType, String ordeStatus, String odpsName) throws Exception;

}
