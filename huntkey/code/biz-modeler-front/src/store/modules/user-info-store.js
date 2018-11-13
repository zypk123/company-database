import { userInfoTypes } from '@/types'
import { userInfoService } from '@/api'

const state = {
  userInfo: null
}

const getters = {
  [userInfoTypes.GET_CURRENT_USER]: state => state.userInfo
}

const mutations = {
  [userInfoTypes.SET_USER_INFO] (state, {userInfo}) {
    state.userInfo = userInfo
  }
}

const actions = {
  [userInfoTypes.GET_CURRENT_USER] ({ commit }) {
    // 返回promise
    const promise = userInfoService.getSysUserInfo()
    promise.then(userInfo => {
      commit(userInfoTypes.SET_USER_INFO, {userInfo})
    })
    return promise
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
