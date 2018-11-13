import Vuex from 'vuex'
import Vue from 'vue'

import userInfoStore from './modules/user-info-store'
import dictStore from './modules/dict-store'
import windowStore from './modules/window-store'
import classTreeStore from './modules/class-tree-store'

Vue.use(Vuex)

const _opt = {
  modules: {
    userInfoStore,
    dictStore,
    windowStore,
    classTreeStore
  },
  // 严格模式
  strict: process.env.NODE_ENV === 'development'
}

export default new Vuex.Store(_opt)
