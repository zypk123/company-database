import {APP_TYPES} from '@/types'

const state = {
  showMenu: false,
  windowHeight: window.innerHeight,
  windowWidth: window.innerWidth,
  currentUserId: 'fakeUserId'
}

// getters
const getters = {
  [APP_TYPES.APP_GET_SHOW_MENU]: state => state.showMenu,
  [APP_TYPES.WINDOW_GET_SIZE]: state => {
    return {
      width: state.windowWidth,
      height: state.windowHeight
    }
  },
  [APP_TYPES.APP_GET_CURRENT_USER_ID]: state => state.currentUserId
}

// actions
const actions = {

}

// mutations
const mutations = {
  [APP_TYPES.APP_SET_SHOW_MENU] (state, showMenu) {
    state.showMenu = showMenu
  },
  [APP_TYPES.WINDOW_MUTATION_RESIZE] (state) {
    state.windowHeight = window.innerHeight
    state.windowWidth = window.innerWidth
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
