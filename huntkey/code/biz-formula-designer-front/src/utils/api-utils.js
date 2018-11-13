import axios from 'axios'
import { Message } from 'element-ui'
// import AppUtils from './app-utils'
// import Utils from './utils'
/**
 * apiConfig: {
 *  cb:  成功回调函数
 *  ecb: 错误回调函数
 *  fin: finally回调函数
 *  handleOwnErrMsg: 自己处理错误消息
 *}
 *  为什么不直接返回axios 而要 返回一个封装好的promise.
 *  如果直接返回axios, 在后续只能使用.then中继续返回promise, 在每个使用then的地方得要多定义一次, 不如统一封装好.
 *
 */
const ApiUtils = {
  mockDataByData (data = {}, timeconsume = 500) {
    return new Promise(
      (resolve, reject) => {
        setTimeout(() => {
          resolve(data)
        }, timeconsume)
      }
    )
  },
  mockDataError (errorMsg, timeconsume = 500) {
    return new Promise(
      (resolve, reject) => {
        setTimeout(() => {
          reject(errorMsg)
        }, timeconsume)
      }
    )
  },
  get (url, apiConfig = {}, axiosConfig = {}) {
    return new Promise((resolve, reject) => {
      axios.get(url, axiosConfig)
      .then((resData) => {
        return this.handelResSuccess(resData, apiConfig)
      }).then((resData) => {
        return resolve(resData)
      })
      .catch((error) => {
        return this.handelResError(reject, error, apiConfig)
        // console.info('get....catch')
      })
    })
  },  // end of get
  delete (url, apiConfig = {}, axiosConfig = {}) {
    return new Promise((resolve, reject) => {
      axios.delete(url, axiosConfig)
      .then((resData) => {
        return this.handelResSuccess(resData, apiConfig)
      }).then((resData) => {
        return resolve(resData)
      })
      .catch((error) => {
        return this.handelResError(reject, error, apiConfig)
        // console.info('delete....catch')
      })
    })
  },  // end of get
  post (url, data, apiConfig = {}, axiosConfig = {}) {
    return new Promise((resolve, reject) => {
      axios.post(url, data, axiosConfig)
      .then((resData) => {
        // console.info('post....then')
        return this.handelResSuccess(resData, apiConfig)
      }).then((resData) => {
        return resolve(resData)
      })
      .catch((error) => {
        return this.handelResError(reject, error, apiConfig)
      })
    })
  },  // end of post
  put (url, data, apiConfig = {}, axiosConfig = {}) {
    return new Promise((resolve, reject) => {
      axios.put(url, data, axiosConfig)
      .then((resData) => {
        // console.info('put....then')
        return this.handelResSuccess(resData, apiConfig)
      }).then((resData) => {
        return resolve(resData)
      })
      .catch((error) => {
        // console.info('put....catch')
        return this.handelResError(reject, error, apiConfig)
      })
    })
  },  // end of post
  handelResSuccess (resData, apiConfig) {
    return new Promise((resolve, reject) => {
      if (apiConfig.cb && typeof apiConfig.cb === 'function') {
        try {
          apiConfig.cb(resData)
        } catch (e) {
          reject(e)
        }
      }
      if (apiConfig.fin && typeof apiConfig.fin === 'function') {
        apiConfig.fin()
      }
      resolve(resData)
    })
  },
  handelResError (reject, error, apiConfig) {
    if (apiConfig.ecb && typeof apiConfig.ecb === 'function') {
      if (error.message) {
        if (!apiConfig.handleOwnErrMsg) {
          // console.info('post....then')
          Message.error(error.message)
        }
        console.info('apiConfig.handleOwnErrMsg')
        console.info(apiConfig.handleOwnErrMsg)
      }
      apiConfig.ecb(error)
    }
    if (apiConfig.fin && typeof apiConfig.fin === 'function') {
      apiConfig.fin()
    }
    reject(error)
  }
}
export default ApiUtils
