import ApiUtils from '../../utils/api-utils'
import * as EnvConfig from '../../assets/config/env-config'

export const userService = {
  // 获取用户列表
  // GET请求 传入查询参数
  queryUser ({userName, sortName, sortOrder, pageNum, pageSize}) {
    pageNum = pageNum && pageNum > 0 ? pageNum : 1
    pageSize = pageSize && pageSize > 0 ? pageSize : EnvConfig.page.defaultPageSize
    if (!userName) {
      userName = ''
    }
    if (!sortName) {
      sortName = null
    }
    if (!sortOrder) {
      sortOrder = null
    }
    return ApiUtils.get(EnvConfig.baseUrl.userManage + '/user/getUserList', {}, {params: {
      userName, sortName, sortOrder, pageNum, pageSize
    }})
  },
  // 删除用户
  // DELETE请求，传入待删除的用户ID
  removeUser (id) {
    return ApiUtils.delete(EnvConfig.baseUrl.userManage + '/user/delUser/' + id)
  },
  // 保存用户
  // POST请求 参数为保存的对象
  saveUser (user) {
    let apiConfig = {
      handleOwnErrMsg: true
    }
    return ApiUtils.post(EnvConfig.baseUrl.userManage + '/user/addUser', user, apiConfig)
  },
  // 修改用户
  // PUT请求, 参数为保存的对象
  updateUser (user) {
    let apiConfig = {
      handleOwnErrMsg: true
    }
    return ApiUtils.put(EnvConfig.baseUrl.userManage + '/user/updateUser', user, apiConfig)
  }
}
