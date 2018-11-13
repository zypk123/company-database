// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import store from './store'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import './assets/fonts/iconfont.css'
import './assets/themes/default/css/main.css'
import './assets/themes/default/css/app.css'

import Utils from './utils/utils'
import StorageUtils from './utils/storage-utils'
import ApiUtils from './utils/api-utils'
import AppUtils from './utils/app-utils'
import WebUtils from './utils/web-utils'
import config from './assets/config/env-config.json'
import { APP_TYPES } from '@/types'

Vue.use(ElementUI)
Vue.config.productionTip = false
const debug = process.env.NODE_ENV !== 'production'

let CONST = {
  debug: debug
}

Vue.prototype.CONST = CONST
Vue.prototype.Utils = Utils
Vue.prototype.StorageUtils = StorageUtils
Vue.prototype.ApiUtils = ApiUtils
Vue.prototype.AppUtils = AppUtils
Vue.prototype.WebUtils = WebUtils
Vue.prototype.CONFIG = config
Vue.filter('datetimeFormat', function (datetime) {
  if (datetime === undefined) {
    return ''
  }
  return Utils.formatDate(datetime, 'YYYY-MM-DD hh:mm:ss')
})
// window窗口变化，记录窗口大小
window.onresize = () => {
  store.commit(APP_TYPES.WINDOW_MUTATION_RESIZE)
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
