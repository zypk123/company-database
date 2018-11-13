import ApiUtils from '../../utils/api-utils'
import * as EnvConfig from '../../assets/config/env-config'

export const classifyService = {
  /**
   * 加载函数分类列表classifyService.queryClassify
   */
  queryClassify () {
    return ApiUtils.get(EnvConfig.baseUrl.formula + '/functionClassify/' + 'getFtmFunctionClassifyList')
  },
  /**
   * 删除函数分类
   */
  removeClassify (id) {
    return ApiUtils.delete(EnvConfig.baseUrl.formula + '/functionClassify/' + 'deleteFunctionClassify/' + id)
  },
  /**
   * 新增函数分类
   */
  saveClassify (postParams, cb, ecb) {
    return ApiUtils.post(EnvConfig.baseUrl.formula + '/functionClassify/' + 'createFunctionClassify', postParams, {cb: cb, ecb: ecb})
  },
  /**
   * 修改函数分类
   */
  updateClassify (postParams, cb, ecb) {
    return ApiUtils.put(EnvConfig.baseUrl.formula + '/functionClassify/' + 'updateFunctionClassify', postParams, {cb: cb, ecb: ecb})
  },
  /**
   * 判断是否有子类
   */
  ishaveChildren (id) {
    return ApiUtils.get(EnvConfig.baseUrl.formula + '/defineFunction/' + 'getFunctionDefinitionByClassifyId/' + id)
  },
  /**
   * 加载函数分类名称(有效状态)
   */
  queryClassifyNameList () {
    return ApiUtils.get(EnvConfig.baseUrl.formula + '/functionClassify/' + 'getClassifyNameList')
  }
}
