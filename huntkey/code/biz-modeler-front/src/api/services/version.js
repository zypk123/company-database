import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const versionService = {
  // 获得版本信息
  queryVersion ({pageNum, pageSize, edmdVer, edmdUpdateDesc}) {
    pageNum = pageNum && pageNum > 0 ? pageNum : 1
    pageSize = pageSize && pageSize > 0 ? pageSize : config.page.defaultPageSize
    if (!edmdVer) {
      edmdVer = null
    }
    if (!edmdUpdateDesc) {
      edmdUpdateDesc = null
    }
    return axios.get(config.baseUrl.modeler + 'modelers', {
      params: {
        pageNum, pageSize, edmdVer, edmdUpdateDesc
      }
    })
  },
  // 根据ID查询版本
  getVersionById (id) {
    return axios.get(config.baseUrl.modeler + 'modelers/' + id)
  },
  // 获得状态
  getVersionStatus () {
    return axios.get(config.baseUrl.modeler + 'modelers/status')
  },
  // 检查版本号唯一性
  checkVersion (edmdVer) {
    return axios.get(config.baseUrl.modeler + 'modelers/edmdVer', {
      params: {
        edmdVer
      }
    })
  },
  // 保存新版本
  saveVersion ({edmdVer, edmdStatus, edmdUpdateDesc}) {
    return axios.post(config.baseUrl.modeler + 'modelers', {
      edmdVer, edmdStatus, edmdUpdateDesc
    })
  },
  // 更新版本
  updateVersion ({id, edmdVer, edmdStatus, edmdUpdateDesc}) {
    return axios.put(config.baseUrl.modeler + 'modelers', {
      id, edmdVer, edmdStatus, edmdUpdateDesc
    })
  },
  // 删除版本
  removeVersion (id) {
    return axios.delete(config.baseUrl.modeler + 'modelers/' + id)
  },
  // 导出版本
  exportVersion (id) {
    return axios.get(config.baseUrl.upload + 'modelers/export/', {
      params: {
        id
      }
    })
  },
  // 获取最大版本号
  getMaxVersion () {
    return axios.get(config.baseUrl.modeler + 'modelers/version/')
  }
}
