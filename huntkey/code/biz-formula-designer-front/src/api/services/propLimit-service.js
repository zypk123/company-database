import * as EnvConfig from '../../assets/config/env-config'
import ApiUtils from '../../utils/api-utils'

export const propLimitService = {
  // 获取函数列表
  queryLimitAndConditions ({prprId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/propertyLimit/queryLimitAndConditions/${prprId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  queryVariableData (queryParams, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + '/variantMgr/queryVariants', {cb: cb, ecb: ecb}, {params: queryParams})
  },
  save (postParams, cb, ecb) {
    // return ApiUtils.post('http://10.3.99.20:4771/propertyLimit/saveOrUpdateLimitAndConditions', postParams, {cb: cb, ecb: ecb})
    return ApiUtils.post(EnvConfig.baseUrl.formula + '/propertyLimit/saveOrUpdateLimitAndConditions', postParams, {cb: cb, ecb: ecb})
  },
  deletePropLimit ({prprId}, cb, ecb) {
    return ApiUtils.delete(EnvConfig.baseUrl.formula + `/propertyLimit/deletePropLimit/${prprId}`, {cb: cb, ecb: ecb})
  },
  getValueLimitById ({propertyId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/propertyLimit/getValueLimitById/${propertyId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  insertPropLimit (postParams, cb, ecb) {
    return ApiUtils.post(EnvConfig.baseUrl.formula + '/propertyLimit/savePropLimit', postParams, {cb: cb, ecb: ecb})
  },
  updateValueLimit (queryParams, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + '/propertyLimit/updateValueLimit', {cb: cb, ecb: ecb}, {params: queryParams})
  },
  queryBaseInfoOfProp ({propertyId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/propertyLimit/queryBaseInfoOfProp/${propertyId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  queryPropLimitById ({prprId}, cb, ecb) {
    let url = EnvConfig.baseUrl.formula + `/propertyLimit/getPropLimitById/${prprId}`
    return ApiUtils.get(url, {cb: cb, ecb: ecb})
  },
  queryRecord (queryParams, cb, ecb) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + '/recordMapping/queryRecord', {cb: cb, ecb: ecb}, {params: queryParams})
  },
  addRecordMapping (postParams, cb, ecb) {
    return ApiUtils.post(EnvConfig.baseUrl.formula + '/recordMapping/addRecordMapping', postParams, {cb: cb, ecb: ecb})
  },
  updateRecordMapping (postParams, cb, ecb) {
    return ApiUtils.put(EnvConfig.baseUrl.formula + '/recordMapping/updateRecordMapping', postParams, {cb: cb, ecb: ecb})
  }
}
