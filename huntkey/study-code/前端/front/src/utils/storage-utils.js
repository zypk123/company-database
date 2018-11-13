import Lockr from 'lockr'

const StorageUtils = {

  test: function () {
    // console.info('StorageUtils.test')
    // console.info(window.localStorage)
    // console.info(this.getAllLS())
  },
  // 设置localStorage的key的前缀
  setLSPrefix: function (prefix) {
    Lockr.prefix = prefix
  },
  // 设置localStorage的值
  setLS: function (key, value) {
    Lockr.set(key, value)
  },
  // 获取localStorage的值
  getLS: function (key) {
    return Lockr.get(key)
  },
  // Remove a key from localStorage entirely.
  rmLS: function (key) {
    return Lockr.rm(key)
  },
  // Adds a unique value to a particular set under a hash key.
  saddLS: function (key, value) {
    return Lockr.sadd(key, value)
  },
  // Returns the values of a particular set under a hash key.
  smembersLS: function (key) {
    return Lockr.smembers(key)
  },
  // Removes a value from a particular set under a hash key.
  sremLS: function (key) {
    return Lockr.srem(key)
  },
  // Returns all saved values & objects, in an Array
  // 2017-05-02 getAll返回的结果和文档的不一致, 是否是lockr版本的问题?
  getAllLS: function (bIncludeKeys) {
    return Lockr.getAll(bIncludeKeys)
  },
  flushLS: function () {
    return Lockr.flush()
  }
}
export default StorageUtils
