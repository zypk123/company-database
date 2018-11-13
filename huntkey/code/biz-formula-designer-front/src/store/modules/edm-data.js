import { EDM_DATA_TYPES } from '@/types'
import {formulaService} from '@/api'
// import Vue from 'vue'
const state = {
  emdPropsMap: {},
  edmClassesTree: []
}

// getters
const getters = {
  // [EDM_DATA_TYPES.FET]: state => state.showMenu
}

// actions
const actions = {
  [EDM_DATA_TYPES.ACTION_FETCH_PROPS] ({state, commit}, {edmcId, edmcNameEn}) {
    return new Promise((resolve, reject) => {
      if (!edmcId && !edmcNameEn) {
        reject({message: '类ID和类英文名不能同时为空'})
      }
      if (edmcId) {
        if (state.emdPropsMap[edmcId]) {
          resolve(state.emdPropsMap[edmcId])
        } else {
          formulaService.queryEdmClassCascadePropsByClassID({
            edmcId: edmcId
          },
          (resData) => {
            commit(EDM_DATA_TYPES.MUTATIONS_ADD_PROPS, {
              edmcId: edmcId,
              props: resData
            })
            resolve(resData)
          },
          (error) => {
            reject(error)
          })
        }
      } else { // end of if (edmcId)
        if (state.emdPropsMap[edmcNameEn]) {
          resolve(state.emdPropsMap[edmcNameEn])
        } else {
          formulaService.queryEdmClassCascadePropsByEdmcNameEn({
            edmcNameEn: edmcNameEn
          },
          (resData) => {
            commit(EDM_DATA_TYPES.MUTATIONS_ADD_PROPS, {
              edmcId: edmcNameEn, // edmcNameEn也可做唯一标识, 这里就不区分了.
              props: resData
            })
            resolve(resData)
          },
          (error) => {
            reject(error)
          })
        }
      } // end of else [if (edmcNameEn)]
    })
  },
  [EDM_DATA_TYPES.ACTION_FETCH_CLASSES_TREE] ({state, commit}) {
    return new Promise((resolve, reject) => {
      if (state.edmClassesTree.length > 0) {
        resolve(state.edmClassesTree)
      } else {
        formulaService.queryEdmClassesTree(
          {}, // end of formulaService.queryEdmClassesTree
          (resData) => {
            commit(EDM_DATA_TYPES.MUTATIONS_CACHE_CLASSES_TREE, {
              treeData: resData
            })
            resolve(resData)
          }, // end of  (resData) => {
          (error) => {
            console.log(error)
            reject(error)
          }
        ) // end of formulaService.queryEdmClassesTree(
      }
    }) // end of new Promise((resolve, reject) => {
  }
}

// mutations
const mutations = {
  [EDM_DATA_TYPES.MUTATIONS_ADD_PROPS] (state, {edmcId, props}) {
    // Vue.set(state.emdPropsMap, edmcId, props)
    if (props && props.length > 0) {
      state.emdPropsMap[edmcId] = props
    }
  },
  [EDM_DATA_TYPES.MUTATIONS_CACHE_CLASSES_TREE] (state, {treeData}) {
    // Vue.set(state.emdPropsMap, edmcId, props)
    if (treeData && treeData.length > 0) {
      state.edmClassesTree = treeData
    }
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
