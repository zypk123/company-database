import { classTypes } from '@/types'
const state = {
  // 当前版本类树数据
  currentClassTreeData: null,
  // 当前编辑的类
  currentClass: null
}

const getters = {
  // 获得树数据
  [classTypes.GET_CURRENT_CLASS_TREE_DATA]: state => state.currentClassTreeData
}

const mutations = {
  // 当前的树数据赋值
  [classTypes.SET_CURRENT_CLASS_TREE_DATA] (state, treeData) {
    state.currentClassTreeData = treeData
  },
  [classTypes.SET_CURRENT_CLASS] (state, currentClass) {
    state.currentClass = currentClass
  },
  // 父节点添加子节点
  [classTypes.ADD_CHILD_OF_PARENT] (state, { child, parentId }) {
    const parentNode = getDataOfId(state.currentClassTreeData, parentId)
    if (parentNode) {
      parentNode.children = parentNode.children ? parentNode.children : []
      parentNode.children.push(child)
    }
  },
  // 上移节点
  [classTypes.UP] (state, classInfo) {
    // 查找兄弟节点
    let classes = getBrotherOfId(state.currentClassTreeData, classInfo.id)
    const index = classes.indexOf(classInfo)
    classes.splice(index, 1)
    classes.splice(index - 1, 0, classInfo)
  },
  // 上移节点
  [classTypes.DOWN] (state, classInfo) {
    // 查找兄弟节点
    let classes = getBrotherOfId(state.currentClassTreeData, classInfo.id)
    const index = classes.indexOf(classInfo)
    classes.splice(index, 1)
    classes.splice(index + 1, 0, classInfo)
  }
}

// 递归查找节点
function getDataOfId (list, id) {
  for (var index in list) {
    const item = list[index]
    if (item.id === id) {
      return item
    } else {
      if (item.children && item.children.length > 0) {
        var result = getDataOfId(item.children, id)
        if (result) {
          return result
        } else {
          continue
        }
      }
    }
  }
}
// 递归查找兄弟节点
function getBrotherOfId (list, id) {
  for (var index in list) {
    const item = list[index]
    if (item.id === id) {
      return list
    } else {
      if (item.children && item.children.length > 0) {
        var result = getBrotherOfId(item.children, id)
        if (result) {
          return result
        } else {
          continue
        }
      }
    }
  }
}

export default {
  state,
  getters,
  mutations
}
