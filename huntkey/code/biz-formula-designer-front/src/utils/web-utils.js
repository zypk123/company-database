import Utils from './utils'
/**
 * WebUtils 工具类
 * 提供兼容性的Web API 操作
 * @see https://developer.mozilla.org/zh-CN/docs/Web/API
 * @author karma
 * @version 1.0
 */
const WebUtils = {
  isInIframe: () => {
    if (window.frameElement && window.frameElement.tagName === 'IFRAME') {
      return true
    }
    return false
  },
  // 操作DOM.Node begin */
  hasClass: (node, className) => {
    // console.info(node.classList)
    if (node.classList) { // IE^10
      if (node.classList.contains(className)) {
        return true
      }
      return false
    } else if (node.className === className ||
      Utils.startsWith(node.className, `${className} `) ||
      Utils.endsWith(node.className, ` ${className}`) ||
      node.className.indexOf(` ${className}`) > -1) {
      return true
    }
    return false
  },
  // 操作DOM.Node end */
  /* 操作DOM.Range对象 begin */
  // 获取选中的区域(单选)
  getSelectedRange: (range) => {
    let s = window.getSelection()
    if (s.rangeCount > 0) {
      return s.getRangeAt(0)
    }
    return null
  },
  // 选中区域
  selectRange: (range) => {
    let s = window.getSelection()
    if (s.rangeCount > 0) s.removeAllRanges()
    s.addRange(range)
  },
  // 选中Node
  selectNode: (node) => {
    let s = window.getSelection()
    if (s.rangeCount > 0) s.removeAllRanges()
    let range = document.createRange()
    range.selectNode(node)
    s.addRange(range)
    console.info(range)
  }
  /* 操作Range对象 end */
}
export default WebUtils
