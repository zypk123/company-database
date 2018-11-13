import Vue from 'vue'
import Router from 'vue-router'
import _ from 'lodash'
import versionRouter from './router-modules/version-router.js'
import classRouter from './router-modules/class-router.js'
import methodRouter from './router-modules/method-router.js'

Vue.use(Router)

const rootRouter = [{
  path: '/',
  name: 'root',
  redirect: '/version-list'
}]

var route = _.concat(rootRouter, versionRouter, classRouter, methodRouter)

export default new Router({
  routes: route
})
