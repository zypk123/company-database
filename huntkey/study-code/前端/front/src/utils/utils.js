import moment from 'moment'
moment.locale('zh-cn')
import _ from 'lodash'
import validate from './validate'

/**
 * Utils工具类, 提供通用的Array, Collection, Date, Function, Lang, Math,
 * Number, Object, Seq, String, 及其他工具方法
 * base on moment.js^2.18.1, lodash.js^4.17.4
 */
const Utils = {
  validate,
  test: function () {
    console.info('utils.test')
    // console.log(moment.locale())
  },
  // ======Array 工具方法 begin======
  // Creates an array with all falsey values removed. The values false, null, 0, "", undefined, and NaN are falsey.
  compact: function (array) {
    return _.compact.apply(this, arguments)
  },
  concat: function () {
    return _.concat.apply(this, arguments)
  },
  findIndex: function (array, predicate, fromIndex) {
    return _.findIndex.apply(this, arguments)
  },
  findLastIndex: function (array, predicate, fromIndex) {
    return _.findLastIndex.apply(this, arguments)
  },
  indexOf: function (array, value, fromIndex) {
    return _.indexOf.apply(this, arguments)
  },
  /**
   * Removes all elements from array that predicate returns truthy for
   * and returns an array of the removed elements.
   * The predicate is invoked with three arguments: (value, index, array).
   */
  remove: function (arr, predicate) {
    return _.remove.apply(this, arguments)
  },
  removeByIndex: function (arr, index) {
    arr[index] = null
    return this.compact(arr)
  },
  // ======Array 工具方法 end========

  // ======Collection 工具方法 begin======
  /**
   * Iterates over elements of collection, returning the first element predicate returns truthy for.
   * The predicate is invoked with three arguments: (value, index|key, collection).
   * Arguments
   *  collection (Array|Object): The collection to inspect.
   *  [predicate=_.identity] (Function): The function invoked per iteration.
   *  [fromIndex=0] (number): The index to search from.
   * Returns
   *  (*): Returns the matched element, else undefined.
   */
  find: function (collection, predicate, fromIndex) {
    return _.find.apply(this, arguments)
  },
  // 根据属性查找对象, 返回第一个结果
  findObjByProp: function (collection, prop1, val1) {
    let obj = _.find(collection, (o) => {
      return o[prop1] === val1
    })
    return obj || null
  },
  // 根据属性查找属性, 返回第一个结果
  findPropByProp: function (collection, prop1, val1, prop2, defVal = '') {
    let obj = _.find(collection, (o) => {
      return o[prop1] === val1
    })
    if (obj) {
      return obj[prop2] || defVal
    }
    return defVal
  },
  take: function (array, takeCnt) {
    return _.take.apply(this, arguments)
  },
  takeRight: function (array, takeCnt) {
    return _.takeRight.apply(this, arguments)
  },
  // ======Collection 工具方法 end========
  // ======Date 工具方法 begin======
  curDateTime: function () {
    return moment().format('YYYY-MM-DD HH:mm:ss')
  },
  curDate: function () {
    return moment().format('YYYY-MM-DD')
  },
  curTime: function () {
    return moment().format('HH:mm:ss')
  },
  formatDate: function (date, formatter = 'YYYY-MM-DD') {
    return moment(date).format('YYYY-MM-DD')
  },
  // ======Date 工具方法 end========
  // ======Function 工具方法 begin======
  throttle: function (func, wait = 0, options = {}) {
    console.info('..throttle...')
    return _.throttle.apply(this, arguments)
    // _.throttle(func, wait, options)
  },
  // ======Function 工具方法 end========
  // ======Lang 工具方法 begin======
  /**
   * Casts value as an array if it's not one.
   */
  castArray: function (value) {
    return _.castArray.apply(this, arguments)
  },
  clone: function (value) {
    return _.clone.apply(this, arguments)
  },
  cloneDeep: function (value) {
    return _.cloneDeep.apply(this, arguments)
  },
  cloneDeepWith: function (value, customizer) {
    return _.cloneDeepWith.apply(this, arguments)
  },
  clonePropsWhenExist: function (target, src) {
    // 从src往target中克隆值
    for (let index in src) {
      if (target[index] !== undefined) {
        target[index] = this.cloneDeep(src[index])
      }
    }
    return target
  },
  cloneWith: function (value, customizer) {
    return _.cloneWith.apply(this, arguments)
  },
  isArray: function (value) {
    return _.isArray.apply(this, arguments)
  },
  isEmpty: function (value) {
    return _.isEmpty.apply(this, arguments)
  },
  isObject: function (value) {
    // Checks if value is the language type of Object. (e.g. arrays, functions, objects, regexes, new Number(0), and new String(''))
    return _.isObject.apply(this, arguments)
  },
  slice: function (array, start, end) {
    return _.slice.apply(this, arguments)
  },
  // ======Lang 工具方法 end========
  isInteger: function (value) {
    return _.isInteger.apply(this, arguments)
  },
  // ======Math 工具方法 begin======
  // ======Math 工具方法 end========
  // ======Number 工具方法 begin======
  // ======Number 工具方法 end========

  // ======Object 工具方法 begin======
/**
 * Assigns own enumerable string keyed properties of source objects to the destination object.
 * Source objects are applied from left to right.
 * Subsequent sources overwrite property assignments of previous sources.
 */
  assign: function (obj, iteratee) {
    return _.assign.apply(this, arguments)
  },
 /**
   *
   *  _.forIn(new Foo, function(value, key) {
   *   console.log(key);
   * });
   */
  forIn: function (obj, iteratee) {
    return _.forIn.apply(this, arguments)
  },
  /**
   * Creates an object composed of the picked object properties.
   * Arguments
   *    object (Object): The object to iterate over.
   *   [paths] (...(string|string[])): The property paths to pick.
   * Returns
   *   (Object): Returns the new object.
   *
   *   var object = { 'a': 1, 'b': '2', 'c': 3 };
   *   Utils.pick(object, ['a', 'c']);
   *   // => { 'a': 1, 'c': 3 }
   */
  pick: function (obj, porpArr) {
    return _.pick.apply(this, arguments)
  },
  // ======Object 工具方法 end========
  // ======Seq 工具方法 begin======
  // ======Seq 工具方法 end========
  // ======String 工具方法 begin======
  endsWith: function (str, target, position) {
    return _.endsWith.apply(this, arguments)
  },
  startsWith: function (str, target, position) {
    return _.startsWith.apply(this, arguments)
  }
  // ======String 工具方法 end========
}
export default Utils
