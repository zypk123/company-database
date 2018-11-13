// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import router from './router'
import store from './store'
import utils from './utils'
import './assets/themes/default/css/main.css'
import './assets/fonts/icon.css'
import { windowTypes } from './types'
import axios from 'axios'

Vue.config.productionTip = false

Vue.prototype.UTILS = utils

Vue.use(ElementUI)

// window窗口变化，记录窗口大小
window.onresize = () => {
  store.commit(windowTypes.REFRESH)
}

// 注册页面接收函数
window.onmessage = function (message) {
  // console.log(message)
  const callback = store.state.windowStore.eventBus[message.data.eventType]
  callback(message.data)
}
// 获取全局配置文件，初始化vue组件
axios.create().get('/static/env-config.json?_=' + new Date().getTime()).then(data => {
  Vue.prototype.CONFIG = data.data
  store.commit('initConfig', data.data)
  /* eslint-disable no-new */
  new Vue({
    el: '#app',
    router,
    store,
    template: '<App/>',
    render: h => h(App),
    components: { App }
  })
})

