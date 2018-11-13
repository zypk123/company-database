import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'
import { APP_TYPES } from '@/types'
import appRoutes from './router-app'
import utils from '../utils/utils'
Vue.use(Router)

const debug = process.env.NODE_ENV !== 'production'
let tempRoutes = utils.concat(
  appRoutes
)
if (debug) {
  tempRoutes = utils.concat(
    tempRoutes
  )
}
const routes = tempRoutes

const router = new Router({ // 创建路由对象
  routes: routes
})

router.beforeEach((to, from, next) => {
  // console.log(to, from, next)
  store.commit(APP_TYPES.APP_SET_SHOW_MENU, false)
  if (to.matched.length > 0) {
    if (to.meta && to.meta.showMenu) {
      store.commit(APP_TYPES.APP_SET_SHOW_MENU, true)
    }
    next()
  } else {
    // AppUtils.showWarning('错误的路径')
    // 错误路径
    router.replace('/')
  }
})

export default router
