import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const classService = {
  /**
   * 获得行业树
   */
  getIndustries () {
    return axios.get(config.baseUrl.modeler + 'codes/industries')
  },
  /**
   * 根据id获取类树
   * @param  {[type]} id
   */
  getClassTreeById (id) {
    return new Promise((resolve, reject) => {
      axios.get(config.baseUrl.modeler + 'modelers/' + id + '/classes').then(data => {
        // 中英文名合并
        setNode(data)
        resolve(data)
        // 递归设置值
        function setNode (nodes) {
          for (var index in nodes) {
            const node = nodes[index]
            node.viewName = node.edmcName + ' ' + node.edmcNameEn
            if (node.children && node.children.length > 0) {
              setNode(node.children)
            }
          }
        }
      }).catch(err => {
        reject(err)
      })
    })
  },
  /**
   * 创建类
   */
  createClass (newClass) {
    return axios.post(config.baseUrl.modeler + 'classes', newClass)
  },
  /**
   * 更新类
   */
  updateClass (newClass) {
    return axios.put(config.baseUrl.modeler + 'classes', newClass)
  },
  /**
   * 类复制
   */
  copyClass (newClass) {
    return axios.post(config.baseUrl.modeler + 'classes/copy', newClass)
  },
  /**
   * 删除类
   */
  removeClass (id) {
    return axios.delete(config.baseUrl.modeler + 'classes/' + id)
  },
  /**
   * 远程验证类编码唯一性
   */
  validateEdmcCode (edmcEdmdId, edmcCode) {
    return axios.get(config.baseUrl.modeler + 'classes/edmcCode', {
      params: {
        edmcEdmdId, edmcCode
      }
    })
  },
  /**
   * 远程验证类名称唯一性
   */
  validateEdmcName (edmcEdmdId, edmcName) {
    return axios.get(config.baseUrl.modeler + 'classes/edmcName', {
      params: {
        edmcEdmdId, edmcName
      }
    })
  },
  /**
   * 远程验证类名称唯一性
   */
  validateEdmcShortName (edmcEdmdId, edmcShortName) {
    return axios.get(config.baseUrl.modeler + 'classes/edmcShortName', {
      params: {
        edmcEdmdId, edmcShortName
      }
    })
  },
  /**
   * 远程验证类英文名称唯一性
   */
  validateEdmcNameEn (edmcEdmdId, edmcNameEn) {
    return axios.get(config.baseUrl.modeler + 'classes/edmcNameEn', {
      params: {
        edmcEdmdId, edmcNameEn
      }
    })
  },
  /**
   * 类上移下移
   */
  moveClass (ids) {
    return axios.put(config.baseUrl.modeler + 'classes/move', ids)
  },
  /**
   * 获取类对象
   */
  getClassObject (queryData) {
    return new Promise((resolve, reject) => {
      axios.get(config.baseUrl.modeler + 'classFormats/getAppearAndEnumObject', {
        params: queryData
      }).then(data => {
        for (let index in data.list) {
          var item = data.list[index]
          for (let key in item) {
            item.id = key
            item.name = item[key]
          }
        }
        resolve(data)
      }).catch(err => {
        reject(err)
      })
    })
  },
  /**
   * 判断类是否配置了特征值
   */
  hasCharacter (classId) {
    return axios.get(`${config.baseUrl.modeler}/classes/${classId}/set/formats`)
  }
}
