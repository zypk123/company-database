import axios from 'axios'
import config from '@/assets/config/env-config.json'
// import ApiUtils from '../../utils/api-utils'
// import Utils from '../../utils/utils'
// const funManagerData = require('../mock-data/fun-manager-data')

export const varService = {
  querySysVarsPage ({pageNum, pageSize, varName, varStatus, sortName, sortOrder}) {
    pageNum = pageNum && pageNum > 0 ? pageNum : config.baseUrl.defaultPageNumber
    pageSize = pageSize && pageSize > 0 ? pageSize : config.baseUrl.defaultPageSize
    if (!varName) {
      varName = null
    }
    if (!varStatus) {
      varStatus = null
    }
    if (!sortName) {
      sortName = null
    }
    if (!sortOrder) {
      sortOrder = null
    }
    return axios.get(config.baseUrl.formula + '/variantMgr/querySystemVariants', {
      params: {
        pageNum, pageSize, varName, varStatus, sortName, sortOrder
      }
    })
  },
  getSysVar (id) {
    return axios.get(config.baseUrl.formula + '/variantMgr/getSystemVariantsById/' + id)
  },
  updateSysVar ({vrntId, vrntVarType, vrntVarName, vrntState, vrntVarDesc, vrntFormulaId, vrntModifyRemarks, isenable, vrntVarScope}) {
    return axios.put(config.baseUrl.formula + '/variantMgr/updateVariant', {
      vrntId, vrntVarType, vrntVarName, vrntState, vrntVarDesc, vrntFormulaId, vrntModifyRemarks, isenable, vrntVarScope
    })
  },
  saveSysVar ({vrntVarType, vrntVarName, vrntState, vrntVarDesc, vrntFormulaId, vrntModifyRemarks, vrntVarScope}) {
    return axios.post(config.baseUrl.formula + '/variantMgr/saveVariant', {
      vrntVarType, vrntVarName, vrntState, vrntVarDesc, vrntFormulaId, vrntModifyRemarks, vrntVarScope
    })
  },
  removeSysVar (id) {
    return axios.delete(config.baseUrl.formula + '/variantMgr/removeVariant/' + id)
  },
  checkSysVarInUse (id) {
    return axios.get(config.baseUrl.formula + '/variantMgr/checkSysVarInUse/' + id)
  },
  initSystVariant () {
    return axios.get(config.baseUrl.formula + '/variantMgr/initSystVariant')
  }
}
