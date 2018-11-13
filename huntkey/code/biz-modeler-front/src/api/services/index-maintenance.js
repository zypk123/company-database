import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const indexService = {
  // 查找索引
  getIndexByClassId (classId) {
    return axios.get(`${config.baseUrl.modeler}classes/${classId}/indexes`)
  },
  // 查找父类索引
  getParentIndexes (classId) {
    return axios.get(`${config.baseUrl.modeler}classes/${classId}/parentIndexes`)
  },
  // 查找类或属性集下的非属性集属性
  getNonAttributeIndex (id, assembleId) {
    // return axios.get(`${config.baseUrl.modeler}classes/${id}/propertys/assemble`, assembleId)
    return new Promise((resolve, reject) => {
      axios.get(`${config.baseUrl.modeler}classes/${id}/propertys/assemble`, {
        params: {
          assembleId
        }
      }).then(data => {
        resolve(data)
        for (let index in data) {
          const indexData = data[index]
          indexData.viewName = indexData.edmpCode + ' ' + indexData.edmpName
        }
      }).catch(err => {
        reject(err)
      })
    })
  },
  // 查找类下所有属性集属性
  getAttributeIndex (id) {
    return axios.get(`${config.baseUrl.modeler}classes/${id}/assemble`)
  },
  // 批量删除索引
  deleteIndexes (ids) {
    return axios.delete(`${config.baseUrl.modeler}indexes/batch/${ids}`)
  },
  // 新增索引
  createIndex (indexData) {
    return axios.post(`${config.baseUrl.modeler}indexes`, indexData)
  },
  // 修改索引
  updateIndex (indexData) {
    return axios.put(`${config.baseUrl.modeler}indexes`, indexData)
  },
  // 名称校验
  validateIndexName (id, indexName) {
    return axios.get(`${config.baseUrl.modeler}classes/${id}/indexName/validator`, {
      params: {
        indexName
      }
    })
  }
}
