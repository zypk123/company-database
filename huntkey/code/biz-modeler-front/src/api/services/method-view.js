import store from '@/store'
const config = store.state.windowStore.config
import axios from 'axios'

export const methodViewService = {
  // 查询模型方法列表
  getMethods (id) {
    return axios.get(config.baseUrl.modeler + 'classes/' + id + '/methods')
    /* return new Promise((resolve, reject) => {
      axios.get(config.baseUrl.modeler + 'classes/' + id + '/methods').then(data => {
        if (data && data.length !== 0) {
          setAuto(data)
        }
        resolve(data)
        function setAuto (data) {
          for (let obj of data) {
            if (obj.isPlatformProgram === 0) {
              obj.isPlatformProgram = false
            } else if (obj.isPlatformProgram === 1) {
              obj.isPlatformProgram = true
            }
            if (obj.isCover === 0) {
              obj.isCover = false
            } else if (obj.isCover === 1) {
              obj.isCover = true
            }
          }
        }
      }).catch(err => {
        reject(err)
      })
    }) */
  },
  // 查询模型方法列表
  getParentMethods (id) {
    return axios.get(config.baseUrl.modeler + 'classes/' + id + '/father/methods')
   /* return new Promise((resolve, reject) => {
     axios.get(config.baseUrl.modeler + 'classes/' + id + '/father/methods').then(data => {
     if (data && data.length !== 0) {
     setAuto(data)
     }
     resolve(data)
     function setAuto (data) {
     for (let obj of data) {
     if (obj.isPlatformProgram === 0) {
     obj.isPlatformProgram = false
     } else if (obj.isPlatformProgram === 1) {
     obj.isPlatformProgram = true
     }
     if (obj.isCover === 0) {
     obj.isCover = false
     } else if (obj.isCover === 1) {
     obj.isCover = true
     }
     }
     }
     }).catch(err => {
     reject(err)
     })
     }) */
  },
  // 删除类方法
  deleteMethod (classId, methodId) {
    return axios.delete(config.baseUrl.modeler + 'classes/' + classId + '/' + methodId)
  },
  // 批量删除类方法
  deleteMethods (classId, methodIds) {
    return axios.delete(config.baseUrl.modeler + 'classes/batch/' + classId + '/' + methodIds)
  },
  // 修改方法
  updateMedthod (obj) {
    console.log(JSON.stringify(obj))
    return axios.put(config.baseUrl.modeler + 'methods/baseInfo', obj)
  },
  // 方法排序
  moveMethod (ids) {
   // console.log(JSON.stringify(ids))
    return axios.put(config.baseUrl.modeler + 'methods/move', ids)
  }
}
