import { windowTypes } from '@/types'
import _ from 'lodash'
const state = {
  windowHeight: window.innerHeight,
  windowWidth: window.innerWidth,
  eventBus: {
  },
  config: {
  }
}

const getters = {
  [windowTypes.WINDOW_SIZE]: state => {
    return {
      width: state.windowWidth,
      height: state.windowHeight
    }
  },
  getConfig: state => state.config
}

const mutations = {
  [windowTypes.REFRESH] (state) {
    state.windowHeight = window.innerHeight
    state.windowWidth = window.innerWidth
  },
  [windowTypes.ADD_EVENT] (state, {type, callback}) {
    state.eventBus[type] = callback
  },
  initConfig (state, config) {
    _.assign(state.config, config)
  }
}

export default {
  state,
  getters,
  mutations
}
