import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const basicInfoService = {
  // 查询数据库类型下拉框数据
  getDBTypes () {
    return axios.get(config.baseUrl.modeler + 'codes/edm_db_type')
  },
  // 查询表单数据
  getBasicInfo (classId) {
    return axios.get(config.baseUrl.modeler + 'classes/' + classId)
  },
  // 查询表格数据
  queryAttachment (classId) {
    return axios.get(config.baseUrl.modeler + 'classes/' + classId + '/attachments')
  },
  // 查询附件类型
  queryType () {
    return axios.get(config.baseUrl.modeler + 'codes/edm_attachment_type')
  },
  // 校验附件名称
  checkDataName (obj) {
    return axios.get(config.baseUrl.modeler + 'attachments/edmaName', obj)
  },
  // 新增附件
  saveAttachment (obj) {
    return axios.post(config.baseUrl.modeler + 'attachments/', obj)
  },
  // 更新附件信息
  updataAttachement (obj) {
    return axios.put(config.baseUrl.modeler + 'attachments/', obj)
  },
  // 删除附件
  deleteInfo (ids) {
    return axios.delete(config.baseUrl.modeler + 'attachments/batch/' + ids.join(','))
  },
  // 附件移动
  moveAtta (ids) {
    return axios.put(config.baseUrl.modeler + 'attachments/move', ids)
  },
  // 查询呈现属性
  queryViewProperty (classId) {
    return axios.get(config.baseUrl.modeler + 'classes/' + classId + '/character/properties')
  },
  // 保存呈现
  saveClassFormats (formats) {
    console.log(JSON.stringify(formats))
    return axios.post(config.baseUrl.modeler + 'classFormats/insertBatch', formats)
  },
  // 获取呈现格式
  getClassFormats (edmcId) {
    return axios.get(config.baseUrl.modeler + 'classFormats', {
      params: {
        edmcId
      }
    })
  }
}
