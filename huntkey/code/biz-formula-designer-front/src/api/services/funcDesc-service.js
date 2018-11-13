import axios from 'axios'
import config from '@/assets/config/env-config.json'

const funcDescService = {
  queryFuncDescLanguage ({pageNum, pageSize, varName, varStatus, sortName, sortOrder}) {
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
  updateFuncDescLanguage () {
  },
  deleteFuncDescLanguage () {
  },
  addFuncDescLanguage () {
  },
  getFuncDescLanguage () {
  },
  initFuncDescLanguageId () {
  }
}
export default funcDescService
