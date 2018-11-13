import validate from './validate'
import store from '@/store'

export default {
  validate,
  /**
   * 对象复制数据，不会增加目标对象字段数量
   * @return {[type]} [description]
   */
  setDataFromOther (target, src) {
    for (let index in target) {
      target[index] = src[index]
    }
  },
  // 根据字典code和value获得字典名称
  getDictName (code, value) {
    const dicts = store.state.dictStore.dicts[code]
    const dict = dicts.find(item => item.value === value)
    if (dict) {
      return dict.label
    } else {
      return ''
    }
  },
  // 根据classId查找class
  getClassById (id) {
    const classTree = store.state.classTreeStore.currentClassTreeData
    return findClass(id, classTree)
    /**
     * 递归查找子类
     * @param  {string} id   需要查找的ID
     * @param  {[type]} list 查找范围
     */
    function findClass (id, list) {
      for (let index in list) {
        const item = list[index]
        if (item.id === id) {
          return item
        } else {
          if (item.children) {
            const result = findClass(id, item.children)
            if (result) {
              return result
            }
          }
        }
      }
    }
  }
}
