import { dictTypes } from '@/types'
import { dictService } from '@/api'
import Vue from 'vue'

const state = {
  // 所有数据字典
  dicts: {

  },
  // 所有树形数据字典
  trees: {

  }
}

const getters = {
  [dictTypes.GET_ALL_DICTS]: state => state.dicts,
  [dictTypes.GET_ALL_TREES]: state => state.trees
}

const mutations = {
  [dictTypes.ADD_DICT_TO_CACHE] (state, {data}) {
    for (let index in data) {
      Vue.set(state.dicts, index, data[index])
    }
  },
  [dictTypes.ADD_DICT_TREE_TO_CACHE] (state, {code, data}) {
    Vue.set(state.trees, code, data)
  }
}

const actions = {
  // 根据一组code查找下级数据字典，并缓存
  [dictTypes.GET_DICT_BY_CODES] ({state, commit}, codes) {
    return new Promise((resolve, reject) => {
      let codesToGet = []
      for (let index = 0; index < codes.length; index++) {
        if (!state.dicts[codes[index]]) {
          codesToGet.push(codes[index])
        }
      }
      // 没有新的数据字典需要获取
      if (!codesToGet.length) {
        resolve()
      } else {
        dictService.getDictByCodes(codesToGet).then(data => {
          commit(dictTypes.ADD_DICT_TO_CACHE, {data})
          resolve(data)
        }).catch(err => {
          reject(err)
        })
      }
    })
  },
  // 查找树形字典
  [dictTypes.GET_DICT_TREE_BY_CODE] ({state, commit}, code) {
    return new Promise((resolve, reject) => {
      // 判断有没有树形节点
      if (state.trees[code]) {
        resolve(state.trees[code])
      } else {
        // 远端查找
        dictService.getDictTreeByCode(code).then(data => {
          commit(dictTypes.ADD_DICT_TREE_TO_CACHE, {code, data})
          resolve(data)
        }).catch(err => {
          reject(err)
        })
      }
    })
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
