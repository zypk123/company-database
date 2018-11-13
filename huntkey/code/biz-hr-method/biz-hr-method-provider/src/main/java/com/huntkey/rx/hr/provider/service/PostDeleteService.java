package com.huntkey.rx.hr.provider.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.DeptpostsetorderEntity;
/**
 * 部门岗位注销
 * @author fangkun
 */
public interface PostDeleteService {
    /**
     * 部门岗位注销单据加载方法
     * @param orderId 单据ID
     * @return
     */
    JSONObject loadDpDeleteOrder(String orderId);
    /**
     * 部门岗位注销岗位加载方法
     * @param postIds 最高级岗位id集合
     * @return
     */
    JSONArray loadDpDelete(String[] postIds);
    /***
     * 部门岗位注销单据保存方法（含校验）
     * @param saveParams 保存数据
     * @return
     */
    JSONObject saveDpDeleteOrder(DeptpostsetorderEntity postDeleteSetOrder);
    /***
     * 部门岗位注销单据提交方法（含校验）
     * @param orderId 单据ID
     * @return
     */
    JSONObject submitDpDeleteOrder(DeptpostsetorderEntity deptPostDeleteOrder);
    /***
     * 部门岗位注销单据批准通过方法
     * @param orderId 单据ID
     * @return
     */
    Result pass(String orderInstanceId,String handlerType);
    /***
     * 判断是否有岗位人员调整 的单据
     * @param postNo 岗位编码
     * @param status 单据状态
     * @return
     */
    Map<String, String> getPostStatus(String postNo,String[] status);
    /***
     * 
     * @param deptId 部门ID
     * @param postId 岗位ID
     * @param empId 人员ID
     * @param subPost 是否包含下级部门
     * @return
     */
    Map<String, String> postEmpIsMajor(String deptId,String postId,String empId,String date);
    /***
     * 校验下级岗位是否是部门的责任岗位
     * @param postId 岗位ID
     * @param checkType 1 校验主责  2 校验是否有待审单  3 校验岗位是否删除
     * @return
     */
    Map<String, String> checkSubPost(String postNo,int checkType);
    /****
     * 下级岗位是否存在待审单
     * 返回第一个校验待审单据的单据号、单据类型、单据状态
     * @param postId
     * @return
     */
    Map<String, String> subPostContainOrder(String postNo);
    
    Map<String, String> curPostContainOrder(DeptpostsetorderEntity deptPostDelete,
			Boolean checkSub);
    
    
}
