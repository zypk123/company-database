import {Message, Notification} from 'element-ui'
import store from '@/store'

const AppUtils = {
  test: function () {
    // console.info('AppUtils.test')
  },
  showMsg: function (msg) {
    Message({
      message: msg,
      type: 'info'
    })
  },
  showSuccess: function (msg) {
    Message({
      message: msg,
      type: 'success'
    })
  },
  showWarning: function (msg) {
    Message({
      message: msg,
      type: 'warning'
    })
  },
  showError: function (msg) {
    Message({
      message: msg,
      type: 'error'
    })
  },
  showErrorMsg: function (error) {
    if (error && error.message) {
      this.showError(error.message)
    }
  },
  calcHeight (otherHeight = 100, minHeight = 200, maxHeight = 400) {
    let height = store.state.app.windowHeight - otherHeight
    return minHeight > height ? minHeight : height
  },
  showNWarning: function (msg, options = {}) {
    let duration = options.duration || 0
    Notification({
      type: 'warning',
      message: msg,
      duration: duration // 不会自动关闭
    })
  },
  redirect2Login: function (msg) {
    setTimeout(() => {
      window.router.replace('/login')
    }, 1500)
  }
}
export default AppUtils
