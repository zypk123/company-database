import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const constService = {
  // 查询常量列表
  getConst (classId) {
    return axios.get(config.baseUrl.modeler + 'classes/' + classId + '/consts')
  },
  // 查询父类常量列表
  getParentConst (classId) {
    return axios.get(config.baseUrl.modeler + 'classes/' + classId + '/fatherConsts')
  },
  // 自动生成类下的属性编码
  getEdmpCode (classId) {
    return axios.get(config.baseUrl.modeler + 'properties/getEdmpCode?classId=' + classId)
  },
  // 查询类名
  getClass (classId) {
    return axios.get(config.baseUrl.modeler + 'classes/' + classId)
  },
  // 属性名校验
  checkDataName (obj) {
    return axios.get(config.baseUrl.modeler + 'properties/edmpName', obj)
  },
  // 查询属性类别下拉框
  getEdmpValueType () {
    return axios.get(config.baseUrl.modeler + 'codes/' + 'edm_field_type')
  },
  // 查询数据类型下拉框
  getDataType (codeValue) {
    return axios.get(config.baseUrl.modeler + 'codes/dataType/' + codeValue)
  },
  // 保存常量
  saveConst (obj) {
    console.log(JSON.stringify(obj))
    return axios.post(config.baseUrl.modeler + 'properties/consts', obj)
  },
  // 更新常量
  updateConst (obj) {
    return axios.put(config.baseUrl.modeler + 'properties', obj)
  },
  // 删除常量
  deleteConst (id) {
    return axios.delete(config.baseUrl.modeler + 'properties/' + id)
  },
  // 常量上移下移
  moveConst (ids) {
   // console.log(JSON.stringify(ids))
    return axios.put(config.baseUrl.modeler + 'properties/move', ids)
  }
}
