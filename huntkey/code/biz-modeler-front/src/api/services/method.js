import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const functionService = {
  // 程序类型
  getProgramType () {
    return axios.get(config.baseUrl.modeler + 'codes/edm_program_type')
  },
  // 查询方法输入参数
  getArgType () {
    return axios.get(config.baseUrl.modeler + 'codes/edm_para_type')
  },
  // 方法管理表单
  queryMethods ({pageNum, pageSize, edmmType, edmmProgramType, edmmName, edmmStatus, edmmClasses}) {
    pageNum = pageNum && pageNum > 0 ? pageNum : 1
    pageSize = pageSize && pageSize > 0 ? pageSize : config.page.defaultPageSize
    if (!edmmType) {
      edmmType = null
    }
    if (!edmmProgramType) {
      edmmProgramType = null
    }
    if (!edmmName) {
      edmmName = null
    }
    if (!edmmStatus) {
      edmmStatus = null
    }
    if (!edmmClasses) {
      edmmClasses = null
    }
    return axios.get(config.baseUrl.modeler + 'methods', {
      params: {
        pageNum, pageSize, edmmType, edmmProgramType, edmmName, edmmStatus, edmmClasses
      }
    })
  },
  // 查询方法输入参数
  queryMethodIn (id) {
    return axios.get(config.baseUrl.modeler + 'methods/' + id)
  },
  // 根据ID查询方法
  queryMethod (id) {
    return axios.get(config.baseUrl.modeler + 'methods/' + id)
  },
  // 删除方法
  deleteMethod (id) {
    return axios.delete(config.baseUrl.modeler + 'methods/' + id)
  },
  // 检查方法名唯一
  checkMethodName (name) {
    return axios.get(config.baseUrl.modeler + 'methods/edmmName?edmmName=' + name)
  },
  // 校验参数名唯一
  checkArg (obj) {
    console.log(JSON.stringify(obj))
    return axios.get(config.baseUrl.modeler + 'methodArgs/edmaName?edmaName=' + obj.edmaName + '&edmaEdmmId=' + obj.edmaEdmmId)
  },
  // 查询输入参数
  queryInput (id) {
    return axios.get(config.baseUrl.modeler + 'methodArgs/inputArgs/' + id)
  },
  // 查询返回参数
  queryReturn (id) {
    return axios.get(config.baseUrl.modeler + 'methodArgs/retArgs/' + id)
  },
  // 保存方法
  save (obj) {
    console.log(JSON.stringify(obj))
    return axios.put(config.baseUrl.modeler + 'methods/', obj)
  },
  // 新增方法
  insert (obj) {
    console.log(JSON.stringify(obj))
    return axios.post(config.baseUrl.modeler + 'methods/', obj)
  },
  // 更新参数
  saveArg (type, obj) {
    return axios.post(config.baseUrl.modeler + 'methodArgs/' + type + 'Arg', obj)
  },
  // 添加参数
  insertArg (obj) {
    return axios.put(config.baseUrl.modeler + 'methodArgs/', obj)
  },
  // 删除输入、输出参数
  deleteRow (id) {
    return axios.delete(config.baseUrl.modeler + 'methodArgs/' + id)
  },
  //  上移参数
  changeArg (aid, bid) {
    return axios.put(config.baseUrl.modeler + 'methodArgs/' + aid + '/' + bid)
  },
  // 获取方法种类
  getTypes () {
    return axios.get(config.baseUrl.modeler + 'methodtypes')
  },
  // 加载全部方法树
  getTypesTree () {
    return axios.get(config.baseUrl.modeler + 'methods/methodTypeTree')
  },
  // 获取方法
  getType (id) {
    return axios.get(config.baseUrl.modeler + 'methodtypes/' + id)
  },
  // 分类编码唯一性
  checkUnique (code) {
    return axios.get(config.baseUrl.modeler + 'methodtypes/edmtCode?edmtCode=' + code)
  },
  // 分类名唯一性
  checkName (name) {
    return axios.get(config.baseUrl.modeler + 'methodtypes/edmtName?edmtName=' + name)
  },
  // 更新分类
  updateTypes (obj) {
    return axios.put(config.baseUrl.modeler + 'methodtypes', obj)
  },
  // 添加分类
  putTypes (obj) {
    return axios.post(config.baseUrl.modeler + 'methodtypes', obj)
  },
  // 删除分类
  deleteTypes (id) {
    return axios.delete(config.baseUrl.modeler + 'methodtypes/' + id)
  },
  // 移动方法
  moveMethod (id, moveId) {
    return axios.put(config.baseUrl.modeler + 'methodtypes/moveMethod/' + id + '/' + moveId)
  },
  // 为类增加方法
  classAddMethod (obj) {
    console.log(JSON.stringify(obj))
    return axios.post(config.baseUrl.modeler + 'classes/methods', obj)
  }
}
