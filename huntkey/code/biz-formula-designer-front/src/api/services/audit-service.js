import axios from 'axios'
import config from '@/assets/config/env-config.json'

export const auditService = {
  getMethodList (classId) {
    // http://10.3.99.36:4772/auditRoles/getmode/{classId}
    return axios.get(config.baseUrl.formula + `/auditRoles/getmode/${classId}`)
  },
  getRolePersonList (postParams) {
    console.log(JSON.stringify(postParams))
    return axios.post(config.baseUrl.formula + '/auditRoles/auditInterface', postParams)
  }
}
