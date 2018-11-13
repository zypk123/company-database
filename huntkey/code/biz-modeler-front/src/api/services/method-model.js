import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const methodModelService = {
  // 查询模型版本
  getModelType () {
    return axios.get(config.baseUrl.modeler + 'modelers/versions')
  },
  // 查询列表
  queryData (obj) {
    if (!obj.type) {
      obj.type = ''
    }
    if (!obj.name) {
      obj.name = ''
    }
    if (!obj.version) {
      obj.version = ''
    }
    if (!obj.className) {
      obj.className = ''
    }
   // console.log(JSON.stringify(obj))
    return axios.get(config.baseUrl.modeler + 'methods/classes?type=' + obj.type + '&name=' + obj.name + '&version=' + obj.version + '&className=' + obj.className)
  },
  //  查询卷积公式
  getConvolution () {
    return axios.get(config.baseUrl.modeler + 'codes/edm_convolute_formula')
  },
  // 查询模型版本
  getVersions () {
    return axios.get(config.baseUrl.modeler + 'modelers/versions')
  },
  // 查询卷积列表数据
  getProperties (obj) {
   // console.log(JSON.stringify(obj))
    return axios.get(config.baseUrl.modeler + 'convolutes/properties?edcoConvoluteFormula=' + obj.edcoConvoluteFormula + '&edmdVer=' + obj.edmdVer + '&edmcName=' + obj.edmcName + '&edmpName=' + obj.edmpName)
  }
}
