// import ctx from '../static/config/env-config.json'
// import axios from 'axios'

const _userInfo = {
  id: '10000',
  name: '张三'
}

export const userInfoService = {
  /**
   * @return {系统用户信息}
   */
  getUserInfo () {
    return new Promise((resolve, reject) => {
      resolve(_userInfo)
    })
  }
}
