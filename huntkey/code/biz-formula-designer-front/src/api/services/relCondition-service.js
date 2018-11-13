import * as EnvConfig from '../../assets/config/env-config'
import ApiUtils from '../../utils/api-utils'
import axios from 'axios'

export const relConditionService = {
  /**
   * 初始化属性下拉框
   */
  queryBaseInfoOfRelCondition ({propId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/modelerScanner/getPropById/${propId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  /**
   * 查询关联属性及其条件列表
   */
  queryRelConditionAndConditions ({prplId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/relatedConditions/getPropertyRelatedCondition/${prplId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  /**
   * 变量模糊查询
   */
  queryVariableData (queryParams, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + '/variantMgr/queryVariants', {cb: cb, ecb: ecb}, {params: queryParams})
  },
  /**
   * 保存关联条件及其条件列表(触发条件)
   */
  saveTriCon (postParams, cb, ecb) {
    return ApiUtils.post(EnvConfig.baseUrl.formula + '/relatedConditions/saveOrUpdatePropRelatedAndConditionsForTriCon', postParams, {cb: cb, ecb: ecb})
    // return ApiUtils.post('http://192.168.113.64:4772' + '/relatedConditions/saveOrUpdatePropRelatedAndConditionsForTriCon', postParams, {cb: cb, ecb: ecb})
  },
  /**
   * 保存关联条件及其条件列表(方法触发条件)
   */
  saveMethodTriCon (postParams, cb, ecb) {
    return ApiUtils.post(EnvConfig.baseUrl.formula + '/relatedConditions/saveOrUpdatePropRelatedAndConditionsForMethodTriCon', postParams, {cb: cb, ecb: ecb})
    // return ApiUtils.post('http://192.168.113.64:4772' + '/relatedConditions/saveOrUpdatePropRelatedAndConditionsForTriCon', postParams, {cb: cb, ecb: ecb})
  },
  /**
   * 保存关联条件及其条件列表(关联条件)
   */
  saveRelCon (postParams, cb, ecb) {
    return ApiUtils.post(EnvConfig.baseUrl.formula + '/relatedConditions/saveOrUpdatePropRelatedAndConditionsForRelCon', postParams, {cb: cb, ecb: ecb})
    // return ApiUtils.post('http://192.168.113.64:4772' + '/relatedConditions/saveOrUpdatePropRelatedAndConditionsForRelCon', postParams, {cb: cb, ecb: ecb})
  },
  /**
   * 初始化触发条件
   */
  initRelTriggerCond ({propId}, {prplId}, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + `/relatedConditions/initRelTriggerCond/${propId}/${prplId}`, {cb: cb, ecb: ecb})
    // return ApiUtils.get('http://192.168.113.64:4772' + `/relatedConditions/initRelTriggerCond/${propId}`, {cb: cb, ecb: ecb})
  },
  /**
   * 初始化方法触发条件
   */
  initMethodRelTriggerCond ({propId}, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + `/relatedConditions/initMethodRelTriggerCond/${propId}`, {cb: cb, ecb: ecb})
    // return ApiUtils.get('http://192.168.113.64:4772' + `/relatedConditions/initRelTriggerCond/${propId}`, {cb: cb, ecb: ecb})
  },
  /**
   * 初始化关联条件
   */
  initRelCond ({propId}, {propId2}, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + `/relatedConditions/initRelCond/${propId}/${propId2}`, {cb: cb, ecb: ecb})
    // return ApiUtils.get('http://192.168.113.64:4772' + `/relatedConditions/initRelCond/${propId}/${propId2}`, {cb: cb, ecb: ecb})
  },
  /**
   * 根据类ID查找类信息
   */
  getClassById ({clssId}, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + `/modelerScanner/getClassById/${clssId}`, {cb: cb, ecb: ecb})
    // return ApiUtils.get('http://192.168.113.64:4771' + `/modelerScanner/getClassById/${clssId}`, {cb: cb, ecb: ecb})
  },
  delRelCond ({prplId}, cb, ecb) {
    return ApiUtils.delete(EnvConfig.baseUrl.formula + `/relatedConditions/delRelCond/${prplId}`, {cb: cb, ecb: ecb})
    // return ApiUtils.delete('http://192.168.113.64:4771' + `/relatedConditions/delRelCond/${prplId}`, {cb: cb, ecb: ecb})
  },
  delRelTrigger ({prplId}, cb, ecb) {
    return ApiUtils.delete(EnvConfig.baseUrl.formula + `/relatedConditions/delRelTrigger/${prplId}`, {cb: cb, ecb: ecb})
    // return ApiUtils.delete('http://192.168.113.64:4771' + `/relatedConditions/delRelTrigger/${prplId}`, {cb: cb, ecb: ecb})
  },
  /**
   * 根据方法id跟类id查询方法和类名
   */
  getClassAndMethod ({methodId}, {classId}, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + `/modelerScanner/getClassAndMethod/${methodId}/${classId}`, {cb: cb, ecb: ecb})
    // return ApiUtils.get('http://192.168.113.64:4772' + `/relatedConditions/initRelCond/${propId}/${propId2}`, {cb: cb, ecb: ecb})
  },
  /**
   * 获取方法基本信息
   */
  getMethod ({methodId}, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + `/modelerScanner/getMethod/${methodId}`, {cb: cb, ecb: ecb})
    // return ApiUtils.delete('http://192.168.113.64:4771' + `/relatedConditions/delRelTrigger/${prplId}`, {cb: cb, ecb: ecb})
  },
  saveRelCondition (postParams) {
    return axios.post(EnvConfig.baseUrl.formula + `/relatedConditions/saveOrUpdatePropRelatedAndConditionsForClassCon`, postParams)
    // return axios.post(`http://10.3.99.67:4772/relatedConditions/saveOrUpdatePropRelatedAndConditionsForClassCon`, postParams)
  },
  saveAuditCon (postParams) {
    // console.log(JSON.stringify(postParams))
    return axios.post(EnvConfig.baseUrl.formula + `/auditRoles/saveAuditRolesBat`, postParams)
  },
  initAuditCond (alternateField4) {
    return axios.get(EnvConfig.baseUrl.formula + `/auditRoles/queryAuditRolesByClassId/${alternateField4}`)
  },
  delAuditCond (alternateField4) {
    return axios.delete(EnvConfig.baseUrl.formula + `/auditRoles/removeConditionRelated/${alternateField4}`)
  }
}
