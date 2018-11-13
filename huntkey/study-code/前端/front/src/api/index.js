import axios from 'axios'
import Utils from '../utils/utils'
import { Message } from 'element-ui'
// 引入其他的service
export * from './services/user-service'

// 以下为axios全局设置
// axios.defaults.withCredentials = true
axios.defaults.timeout = 1000 * 25
// 配置axios 拦截器

// 添加请求拦截器
axios.interceptors.request.use((config) => {
  // 在发送请求之前做某事
  return config
}, (error) => {
  // 请求错误时做些事
  return Promise.reject(error)
})

// 添加响应拦截器
axios.interceptors.response.use((response) => {
  // console.info('axios.interceptors.response.use -> response')
  // console.info(response.config.url)
  // console.info(response)
  // console.info(JSON.stringify(response.data))

  // 对响应数据做些事
  const result = response.data
  let errMsg = '接口返回的数据格式错误'
  if (!result || !(Utils.isInteger(result.retCode))) {
    Message.error(errMsg)
    return Promise.reject({message: errMsg})
  }

  errMsg = '调用接口时发生错误'
  if (result.retCode === 1) { // 成功
    return result.data
  } else if (result.retCode >= 4000 && result.retCode <= 4999) {
    // 根据错误码自己处理错误信息
    return result
  } else if (result.retCode === -1) { // 未登录
    // window.location.href = result.data
    return Promise.reject({message: '用户尚未登录'})
  } else {
    // retCode=0 失败, -2校验错误 其他等等
    errMsg = result.errMsg
    console.log(`数据异常[${result.retCode}]！` + response.config.url)
    return Promise.reject({message: errMsg})
  }
}, (error) => {
  // 请求错误时提示
  // FIXME 这里有些问题...
  // Message.error('网络异常.' + error.message)
  console.log('网络异常.' + error.message)
  return Promise.reject(error)
})
