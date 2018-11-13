import ApiUtils from '../../utils/api-utils'
import * as EnvConfig from '../../assets/config/env-config'
// import AppUtils from '../utils/app-utils'
// import Utils from '../utils/utils'
import axios from 'axios'

/*
 * cb -> callback
 * ecb -> errorCallback
 * 模拟数据: return ApiUtils.mockDataByData(allFuncsData)
 */
export const formulaService = {
  initFormulaInfo ({propId, varId}, cb, ecb, fin) { //
    let url = EnvConfig.baseUrl.formula + `/formula/initFormula`
    return ApiUtils.post(url, {propId, varId}, {cb: cb, ecb: ecb, fin: fin})
  },
  queryVarInfo4InitFormulaEditorByVarID ({varId, userId}, cb, ecb, fin) {
    let url = EnvConfig.baseUrl.formula + `/formula/getFormulaElement/${varId}/${userId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb, fin: fin})
  },
  queryFuncsInfo ({userId}, cb, ecb, fin) {
    let url = EnvConfig.baseUrl.formula + `/formula/getFormulaFunction/${userId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb, fin: fin})
  },
  queryEdmClassesByClassID ({edmcId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/modelerScanner/classes/relateClasses/${edmcId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  queryLinkEdmClassesByClassID ({edmcId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/modelerScanner/classes/${edmcId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  //  根据属性ID获取属性信息
  queryEdmPropInfoByPropID ({edmpId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/modelerScanner/getPropById/${edmpId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  // 查询类ID查询类的相关类列表
  queryRelEdmClassesByFormulaID ({formulaId}, cb, ecb) {
    // http://192.168.113.130:2001/relatedClasses/3c30efcc97af436e90f93c3c93ba9b1f
    let url = EnvConfig.baseUrl.formula + `/relatedClasses/${formulaId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  queryEdmClassCascadePropsByClassID ({edmcId}, cb, ecb, fin) {
    // 根据edmcID 级联查找属性
    let url = EnvConfig.baseUrl.formula + `/modelerScanner/classes/${edmcId}/1/properties`
    return ApiUtils.get(url, {cb: cb, ecb: ecb, fin: fin})
  },
  queryEdmClassCascadePropsByEdmcNameEn ({edmcNameEn}, cb, ecb, fin) {
    // 根据edmcID 级联查找属性
    let url = EnvConfig.baseUrl.formula + `/modelerScanner/classes/${edmcNameEn}/2/properties`
    return ApiUtils.get(url, {cb: cb, ecb: ecb, fin: fin})
  },
  queryEdmClassPropsByClassID ({edmcId}, cb, ecb, fin) {
    // 根据edmcID 查找属性直接属性
    let url = EnvConfig.baseUrl.formula + `/modelerScanner/classes/${edmcId}/1/properties`
    return ApiUtils.get(url, {cb: cb, ecb: ecb, fin: fin})
  },
  queryEdmClassCndsByClassID ({clssId}, cb, ecb, fin) {
    // 根据edmcID 查找属性直接属性
    let url = EnvConfig.baseUrl.formula + `/relatedClasses/queryCondisionsByClssId/${clssId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb, fin: fin})
  },
  querySysVars (queryParams, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/variantMgr/loadSystemVariants`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  }, // end of querySysVars
  queryLoacalVars (queryParams, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/variantMgr/loadLocalVariantsWithFormula`
    return ApiUtils.get(url, {cb: cb, ecb: ecb}, {params: queryParams})
  },
  queryEdmClassesTree (queryParams, cb, ecb) {
    let url = EnvConfig.baseUrl.modeler + `/modelers/${EnvConfig.moderVersion}/classes`
    return ApiUtils.get(url, {cb: cb, ecb: ecb}, {params: queryParams})
  },
  queryAllFuncs (queryParams, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + '/functionDescriber/loadAllFunctions'
    return axios.get(url, {cb: cb, ecb: ecb}, {params: queryParams})
  },
  /* query frequently used functions */
  // queryFUFuncs (queryParams) {
  //   let url = EnvConfig.baseUrl.formula + '/behaivorTracker/loadCommonlyFormulas/123456788'
  //   return ApiUtils.get(url, {params: queryParams})
  // },
  // 统计常用函数使用频率
  statFreqUsedFunc ({userId, funcName}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/behaivorTracker/updateCommonlyFormulas/${userId}/${funcName}`
    return ApiUtils.post(url, {}, {cb: cb, ecb: ecb})
  },
  checkFormulaStrValid (queryParams) {
    return ApiUtils.mockDataByData()
  },
  checkFormulaValid (postParams, cb, ecb, fin) {
    let url = EnvConfig.baseUrl.formula + '/formula/validate'
    let apiConfig = {
      cb: cb,
      ecb: ecb,
      fin: fin,
      handleOwnErrMsg: true
    }
    return ApiUtils.post(url, postParams, apiConfig)
    // return ApiUtils.mockDataByData()
  },
  updateFormulaInfo (postParams, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + '/formula/updateFormula'
    return ApiUtils.post(url, postParams, {cb: cb, ecb: ecb})
  },
  updatePPIFormulaInfo (postParams, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + '/formula/updatePPIFormula'
    return ApiUtils.post(url, postParams, {cb: cb, ecb: ecb})
  },
  delFormulaInfo ({varId}, cb, ecb) {
    // FIXME 这里可不可以和下面的deleteVariant使用同一个接口 2017-08-24
    let url = EnvConfig.baseUrl.formula + `/variantMgr/removeVariant/${varId}`
    return ApiUtils.delete(url, {cb: cb, ecb: ecb})
  },
  getNewRelClassId ({formulaId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/relatedClasses/initRelateClasses/${formulaId}`
    return ApiUtils.post(url, {}, {cb: cb, ecb: ecb})
  },
  delRelClass ({clssId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/relatedClasses/deleteClassRelated/${clssId}`
    return ApiUtils.delete(url, {}, {cb: cb, ecb: ecb})
  },
  saveOrUpdateClassRelated (postParams, cb, ecb) {
    // console.log('json', JSON.stringify(postParams))
    let url = EnvConfig.baseUrl.formula + '/relatedClasses/saveOrUpdateClassRelatedAndConditions'
    return ApiUtils.post(url, postParams, {cb: cb, ecb: ecb})
  },
  initLocalVariant ({parentFormulaId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/variantMgr/initLocalVariant/${parentFormulaId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  updateVariant (varInfo, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/variantMgr/updateVariant`
    return ApiUtils.put(url, varInfo, {cb: cb, ecb: ecb})
  },
  deleteVariant ({varId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/variantMgr/removeVariant/${varId}`
    return ApiUtils.delete(url, {cb: cb, ecb: ecb})
  },
  getVarInfoByVarId ({varId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/variantMgr/getSystemVariantsById/${varId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  getConstInfoByEnumId (id) {
    return axios.post(EnvConfig.baseUrl.enum + `/dateSharing/search?id=${id}`)
  },
  getConstInfoByObjectId (id) {
    return axios.get(EnvConfig.baseUrl.enum + `/dateSharing/findClassById/${id}`)
  },
  // 通过类Id查询类的基本信息 不包含关联类
  getClassInfoByClassId (id) {
    return axios.get(EnvConfig.baseUrl.formula + `/modelerScanner/getClassById/${id}`)
  },
  queryRelClassesByClassId (params) {
    return axios.get(EnvConfig.baseUrl.formula + `/relatedClasses/loadRelatedClassesByClssClassIdAndType/${params.edmcId}/${params.type}`)
  },
  saveRelatedClass (postParams) {
    // console.log(JSON.stringify(postParams))
    return axios.post(EnvConfig.baseUrl.formula + `/relatedClasses/saveOrUpdateClassRelatedAndConditions`, postParams)
    // return axios.post(`http://10.3.99.67:4772/relatedClasses/saveOrUpdateClassRelatedAndConditions`, postParams)
  },
  getNewRelClass (clssLinkClassId) {
    return axios.post(EnvConfig.baseUrl.formula + `/relatedClasses/initRelatedClassesByClssLinkClassId/${clssLinkClassId}`)
  },
  initPPIFormulaInfo ({varId, ppiId}) {
    return axios.post(EnvConfig.baseUrl.formula + `/formula/initPPIFormula`, {varId, ppiId})
  }
}
