import ApiUtils from '../../utils/api-utils'
import * as EnvConfig from '../../assets/config/env-config'

export const funcService = {
  // 获取函数列表
  queryFunc ({fundFunCatagoryId, fundFunName, fundState, sortName, sortOrder, pageNum, pageSize}) {
    pageNum = pageNum && pageNum > 0 ? pageNum : 1
    pageSize = pageSize && pageSize > 0 ? pageSize : EnvConfig.page.defaultPageSize
    if (!fundFunCatagoryId) {
      fundFunCatagoryId = ''
    }
    if (!fundFunName) {
      fundFunName = ''
    }
    if (!fundState) {
      fundState = ''
    }
    if (!sortName) {
      sortName = null
    }
    if (!sortOrder) {
      sortOrder = null
    }
    return ApiUtils.get(EnvConfig.baseUrl.formula + '/defineFunction/queryDefineFunction', {}, {params: {
      fundFunCatagoryId, fundFunName, fundState, sortName, sortOrder, pageNum, pageSize
    }})
  },
  removeFunc (id) {
    return ApiUtils.delete(EnvConfig.baseUrl.formula + '/defineFunction/deleteCustomizeFunction/' + id)
  },
  getFuncById (id) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + ' /functionMgr/selectFuncByPrimaryKey/' + id)
  },
  saveFuncInfo (funcInfo) {
    let apiConfig = {
      handleOwnErrMsg: true
    }
    return ApiUtils.post(EnvConfig.baseUrl.formula_provider + '/defineFunction/createCustomizeFunction', funcInfo, apiConfig)
  },
  updateFuncInfo (funcInfo) {
    let apiConfig = {
      handleOwnErrMsg: true
    }
    return ApiUtils.post(EnvConfig.baseUrl.formula_provider + '/defineFunction/updateCustomizeFunction', funcInfo, apiConfig)
  },
  getFuncDesc (fundId) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + '/defineFunction/getFunctionDescriberById/' + fundId)
  },
  checkFuncValid (postParams, cb, ecb, fin) {
    let url = EnvConfig.baseUrl.formula + '/formula/validate'
    let apiConfig = {
      cb: cb,
      ecb: ecb,
      fin: fin,
      handleOwnErrMsg: true
    }
    return ApiUtils.post(url, postParams, apiConfig)
    // return ApiUtils.mockDataByData()
  }
}
