import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const propertyService = {
  // 根据classID查找属性
  getPropertiesByClassId (classId) {
    return axios.get(config.baseUrl.modeler + 'classes/' + classId + '/properties')
  },
  // 根据classId查找可关联的属性
  getPropertyConnectAble (classId) {
    return axios.get(config.baseUrl.modeler + 'classes/' + classId + '/choose/properties')
  },
  // 根据属性查找属性集
  getAssembleByPropertyId (propertyId) {
    return axios.get(config.baseUrl.modeler + 'properties/' + propertyId + '/properties')
  },
  // 查找父属性
  getParentProperties (classId) {
    return axios.get(config.baseUrl.modeler + 'classes/' + classId + '/fatherProperties')
  },
  // 添加属性
  createProperty (propertyData) {
    return axios.post(config.baseUrl.modeler + 'properties', propertyData)
  },
  // 更新属性
  updateProperty (propertyData) {
    return axios.put(config.baseUrl.modeler + 'properties', propertyData)
  },
  // 删除属性
  removeProperties (ids) {
    return axios.delete(config.baseUrl.modeler + 'properties/batch/' + ids.join(','))
  },
  // 更改是否索引，是否可见
  updatePropertyPatch (propertyData) {
    return axios.put(config.baseUrl.modeler + 'properties/update', propertyData)
  },
  // 属性名称验证唯一性
  validatePropertyName (edmpEdmcId, edmpParentId, edmpName) {
    return axios.get(config.baseUrl.modeler + 'properties/edmpName', {
      params: {
        edmpEdmcId, edmpName, edmpParentId
      }
    })
  },
  // 属性编码唯一性验证
  varlidatePropertyCode (edmpEdmcId, edmpParentId, edmpCode) {
    return axios.get(config.baseUrl.modeler + 'properties/edmpCode', {
      params: {
        edmpEdmcId, edmpParentId, edmpCode
      }
    })
  },
  // 属性排序
  moveProperty (ids) {
    return axios.put(config.baseUrl.modeler + 'properties/move', ids)
  },
  // 关联属性唯一性验证
  validateLinkProperty (edmpId, linkedId) {
    return axios.get(config.baseUrl.modeler + 'links/edmlEdmpLinkId', {
      params: {
        edmpId, linkedId
      }
    })
  },
  // 添加关联属性
  savePropertyLink (linkProperty) {
    return axios.post(config.baseUrl.modeler + 'links', linkProperty)
  },
  // 修改关联属性
  updatePropertyLink (linkProperty) {
    return axios.put(config.baseUrl.modeler + 'links', linkProperty)
  },
  // 删除关联属性
  deletePropertyLink (ids) {
    return axios.delete(config.baseUrl.modeler + 'links/batch/' + ids.join(','))
  },
  // 获得关联属性
  getPropertyLinks (propertyId) {
    return axios.get(config.baseUrl.modeler + 'properties/' + propertyId + '/links')
  },
  // 获得联动属性
  getPropertyConnect (propertyId) {
    return axios.get(config.baseUrl.modeler + 'properties/' + propertyId + '/connects')
  },
  // 获取联动设置
  getConnectSetting (propertyId) {
    return axios.get(config.baseUrl.modeler + 'connects/' + propertyId)
  },
  // 保存联动属性
  savePropertyConnect (connectData) {
    return axios.post(config.baseUrl.modeler + 'connects', connectData)
  },
  // 更新联动属性
  updatePropertyConnect (connectData) {
    return axios.put(config.baseUrl.modeler + 'connects', connectData)
  },
  // 删除联动属性
  deletePropertyConnect (id) {
    return axios.delete(config.baseUrl.modeler + 'connects/' + id)
  },
  // 保存卷积
  saveConvolute (convoluteData) {
    return axios.post(config.baseUrl.modeler + 'convolutes', convoluteData)
  },
  // 修改卷积
  updateConvolute (convoluteData) {
    return axios.put(config.baseUrl.modeler + 'convolutes', convoluteData)
  },
  // 获得卷积
  getPropertyConvolute (propertyId) {
    return axios.get(config.baseUrl.modeler + 'properties/' + propertyId + '/convolute')
  },
  // 删除卷积属性
  deletePropertyConvolute (id) {
    return axios.delete(config.baseUrl.modeler + 'convolutes/' + id)
  },
  // 查询类下面整型和浮点型的属性
  getUnitProperties (edmcId, edmpId) {
    return axios.get(config.baseUrl.modeler + 'classes/edmUnits/properties', {
      params: {
        edmcId, edmpId
      }
    })
  },
  // 获得单位属性
  getPropertyUnits (propertyId) {
    return axios.get(config.baseUrl.modeler + 'properties/' + propertyId + '/units')
  },
  // 新增单位属性
  createUnit (unitData) {
    return axios.post(config.baseUrl.modeler + 'units', unitData)
  },
  // 批量删除单位属性
  deleteUnits (ids) {
    return axios.delete(config.baseUrl.modeler + 'units/batch/' + ids.join(','))
  },
  // 新增关联条件
  createCondition ({ edcoCond, edcoEdmpId }) {
    return axios.post(config.baseUrl.modeler + 'conds', {
      edcoCond, edcoEdmpId
    })
  },
  // 更新触发条件
  updateCondition ({ edcoCond, edcoEdmpId, id }) {
    return axios.put(config.baseUrl.modeler + 'conds', {
      edcoCond, edcoEdmpId
    })
  },
  // 查询关联条件
  queryCondition (propertyId) {
    return axios.get(config.baseUrl.modeler + 'properties/' + propertyId + '/conds')
  }
}
