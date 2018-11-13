  let _ = require('lodash')
  // let moment = require('moment')
  // import moment from 'moment'
  // import _ from 'lodash'
  /* eslint-disable no-unused-vars */
  // 数学函数
  function ABS (param) {
    return Math.abs(param)
  }
  function AVG (...params) {
    let arr = []
    let sum = 0
    for (let val of params) {
      arr.push(val)
    }
    for (let value of arr) {
      sum += value
    }
    return sum / (arr.length)
  }
  function CEILING (num1, num2) {
    return _.ceil(num1, num2)
  }
  function COUNT (...params) {
    let arr = []
    for (let val of params) {
      arr.push(val)
    }
    return arr.length
  }
  function FIXED (num1, num2 = 2) {
    return num1.toFixed(num2)
  }
  function FLOOR (num1, num2) {
    return _.floor(num1, num2)
  }
  function INT (num) {
    return _.floor(num)
  }
  function LARGE () {
  }
  function LOG (num1, num2) {
    return Math.log(num1) / Math.log(num2)
  }
  function MAX (...params) {
    let arr = []
    for (let val of params) {
      arr.push(val)
    }
    return Math.max(...arr)
  }
  function MIN (...params) {
    let arr = []
    for (let val of params) {
      arr.push(val)
    }
    return Math.min(...arr)
  }
  function MOD (x, y) {
    if (y === 0) {
      return
    } else if (y > 0) {
      return Math.abs(x % y)
    } else {
      return -Math.abs(x % y)
    }
  }
  function POWER (x, y) {
    return Math.pow(x, y)
  }
  function PRODUCT (...params) {
    let sum = 1
    for (let val of params) {
      sum *= val
    }
    return sum
  }
  function ROUND (num1, num2) {
    return _.round(num1, num2)
  }
  function SMALL () {
  }
  function SQRT (param) {
    return Math.sqrt(param)
  }
  function SUM (...params) {
    let sum = 0
    for (let val of params) {
      sum += val
    }
    return sum
  }
  function SUMPRODUCT () {
  }
  // 文本函数
  function CONCAT () {
  }
  function CONTAINS (text1, text2) {
    return text1.includes(text2)
  }
  function EXACT (text1, text2) {
    return text1 === text2
  }
  function ISEMPTY (text) {
    return _.isEmpty(text)
  }
  function LEFT (text, num) {
    return text.substr(0, num)
  }
  function LEN (text) {
    return text.length
  }
  function LOWER (text) {
    return text.toLowerCase()
  }
  function MID (text, num1, num2) {
    return text.substr(num1, num2)
  }
  function NUMBER (text) {
    return parseInt(text)
  }
  function REPLACE (text1, num1, num2, text2) {
    return text1.replace(text1.substr(num1, num2), text2)
  }
  function REPT (text, num) {
    return _.repeat(text, num)
  }
  function RIGHT (text, num) {
    let temp = text.length - num
    let str = ''
    for (let i = temp; i < text.length; i++) {
      str = str.concat(text[i])
    }
    return str
  }
  function SEARCH (text1, text2, num = 0) {
    return text1.indexOf(text2, num)
  }
  function SPLIT () {
  }
  function STRING (text) {
    return text.toString()
  }
  function TEXT () {
  }
  function TRIM (text) {
    return text.trim()
  }
  function UPPER (text) {
    return text.toUpperCase()
  }
  function UPPERMONEY () {
  }
  function VALUE (text) {
    return parseFloat(text)
  }
  // 日期函数
  function DATE (year, month, day, hour, minute, second) {
    let date = new Date(year, month, day, hour, minute, second)
    return date
  }
  function DATEDELTA (date, num) {
    date.setDate(date.getDate() + num)
    return date
  }
  function DAY (date) {
    return parseInt(date.getDate())
  }
  function DAYADD (date, num) {
    date.setDate(date.getDate() + num)
    return date
  }
  function DAYS (date1, date2) {
    return Math.floor(Math.abs(date1 - date2) / (24 * 3600 * 1000))
  }
  function HOUR (date) {
    return parseInt(date.getHours())
  }
  function HOURADD (date, num) {
    date.setHours(date.getHours() + num)
    return date
  }
  function HOURS (date1, date2) {
    return Math.floor(Math.abs(date1 - date2) / (3600 * 1000))
  }
  function MINUTE (date) {
    return parseInt(date.getMinutes())
  }
  function MINUTEADD (date, num) {
    date.setMinutes(date.getMinutes() + num)
    return date
  }
  function MINUTES (date1, date2) {
    return Math.floor(Math.abs(date1 - date2) / (60 * 1000))
  }
  function MONTH (date) {
    return parseInt(date.getMonth())
  }
  function MONTHADD (date, num) {
    date.setMonth(date.getMonth() + num)
    return date
  }
  function MONTHS () {
  }
  function NOW () {
    let date = new Date()
    return new Date(date.getFullYear(), date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds())
  }
  function SECOND (date) {
    return parseInt(date.getSeconds())
  }
  function SECONDADD (date, num) {
    date.setSeconds(date.getSeconds() + num)
    return date
  }
  function SECONDS (date1, date2) {
    return Math.floor(Math.abs(date1 - date2) / (1000))
  }
  function TIME () {
  }
  function TIMESTAMP () {
  }
  function TODATE () {
  }
  function TODAY () {
    let date = new Date()
    return new Date(date.getFullYear(), date.getMonth() + 1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds())
  }
  function WEEK () {
  }
  function WEEKNUM () {
  }
  function YEAR (date) {
    return parseInt(date.getFullYear())
  }
  function YEARADD (date, num) {
    date.setFullYear(date.getFullYear() + num)
    return date
  }
  function YEARS (date1, date2) {
    return Math.floor(Math.abs(date1.getFullYear() - date2.getFullYear()))
  }
  /* eslint-disable no-eval */
  // console.log(_.isDate(DATE(2017, 2, 5, 4, 6, 9)))
  let date = eval('YEARS(DATE(2017, 2, 5, 4, 6, 9),DATE(2027, 2, 6, 4, 6, 9))')
  // console.log(_.add(100, 123.456))
  console.log(_.isDate(date))
  // console.log(moment({year: date.getFullYear(), month: date.getMonth() - 1, day: date.getDate(), hour: date.getHours(), minute: date.getMinutes(), second: date.getSeconds()}).format('YYYY-MM-DD HH:mm:ss'))
  Math.formatFloat = function (num1, num2, float) {
    let m = Math.pow(10, float)
    let result1 = num1 * m
    let result2 = num2 * m
    return (result1 + result2) / m
  }
  // console.log(45.6 * 13)
  console.log(1.01 + 1.02)
  console.log(Math.formatFloat(2.03000000000000021, 1.02, 1))
  console.log(Math.formatFloat(1.01, 1.02, 2))
  console.log(Math.formatFloat(1.01, 1.02, 3))
  console.log(Math.formatFloat(1.01, 1.02, 4))
  console.log(Math.formatFloat(1.01, 1.02, 5))
  console.log(Math.formatFloat(1.01, 1.02, 6))
  console.log(Math.formatFloat(1.01, 1.02, 7))
  console.log(Math.formatFloat(1.01, 1.02, 8))
  console.log(new Date('2017-10-23 10:25:36'))
