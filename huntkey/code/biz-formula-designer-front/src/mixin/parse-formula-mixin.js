import _ from 'lodash'
import moment from 'moment'
/* eslint-disable no-unused-vars */
export function parseFormula (params) {
  // console.log(params)
  /**
   formulaId:"63bf2ebaaf7c4e2c98ed1538e6d754b1"
   formulaItemsStr:
   "[{"type":"str","val":""},
   {"type":"prop","val":"6f8e394866e211e7b2e4005056bc4879_51525c22677911e7b2e4005056bc4879_dech001","label":"this.委托时限","returnType":"int"},
   {"type":"str","val":""},
   {"type":"str","val":"+"},
   {"type":"str","val":""},
   {"type":"var","val":"xitong01","label":"xitong01","returnType":"int"},
   {"type":"str","val":""},
   {"type":"str","val":"*10"}]
   0:{varType: "prop", varVal: "6f8e394866e211e7b2e4005056bc4879_51525c22677911e7b2e4005056bc4879_dech001", dataValType: "int", dataVal: "4"}
   1:{varType: "var", varVal: "xitong01", dataValType: "int", dataVal: "2"}
   formulaStr:"#prop_6f8e394866e211e7b2e4005056bc4879_51525c22677911e7b2e4005056bc4879_dech001#+#var_xitong01#*10"
  **/
  return new Promise((resolve, reject) => {
    if (!params) {
      reject({message: '错误信息！'})
    }
    let formulaText = ''
    let formulaItems = _.filter(params.formulaItems, function (o) { return o.val !== '' })
    console.log(formulaItems)
    formulaItems.forEach(
      (item, index, arr) => {
        if (item.type === 'var' || item.type === 'prop') {
          params.formulaItemsValues.forEach(
            (itemVal, index, arr) => {
              if (item.val === itemVal.varVal) {
                if (itemVal.dataValType === 'date') {
                  let date = new Date(itemVal.dataVal)
                  formulaText = formulaText + `DATE(${date.getFullYear()},${date.getMonth()},${date.getDate()},${date.getHours()},${date.getMinutes()},${date.getSeconds()})`
                } else {
                  formulaText = formulaText + itemVal.dataVal
                }
              }
            }
          )
        } else {
          // item.val = item.val.replace(/\s/g, '')
          formulaText = formulaText + item.val
        }
      }
    )
    console.log(formulaText)
    /* eslint-disable no-eval */
    console.log(eval(formulaText))
    if (_.isDate(eval(formulaText))) {
      let date = eval(formulaText)
      resolve(moment({year: date.getFullYear(), month: date.getMonth() - 1, day: date.getDate(), hour: date.getHours(), minute: date.getMinutes(), second: date.getSeconds()}).format('YYYY-MM-DD HH:mm:ss'))
    } else {
      resolve(eval(formulaText))
    }
  })
}
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
// 16
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
  if (num >= 0) {
    return text.substr(0, num)
  }
}
function LEN (text) {
  return text.length
}
function LOWER (text) {
  return text.toLowerCase()
}
function MID (text, num1, num2) {
  return text.substr(num1 - 1, num2)
}
function NUMBER (text) {
  return parseInt(text)
}
function REPLACE (text1, num1, num2, text2) {
  return text1.replace(text1.substr(num1 - 1, num2), text2)
}
function REPT (text, num) {
  if (num > 0) {
    return _.repeat(text, num)
  }
}
function RIGHT (text, num) {
  if (num >= 0) {
    let temp = text.length - num
    let str = ''
    for (let i = temp; i < text.length; i++) {
      str = str.concat(text[i])
    }
    return str
  }
}
function SEARCH (text1, text2, num = 1) {
  let x = num - 1
  if (text1.indexOf(text2, x) === -1) {
    return text1.indexOf(text2, x)
  } else {
    return text1.indexOf(text2, x) + 1
  }
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
// 16
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
  return Math.floor((date1 - date2) / (24 * 3600 * 1000))
}
function HOUR (date) {
  return parseInt(date.getHours())
}
function HOURADD (date, num) {
  date.setHours(date.getHours() + num)
  return date
}
function HOURS (date1, date2) {
  return Math.floor((date1 - date2) / (3600 * 1000))
}
function MINUTE (date) {
  return parseInt(date.getMinutes())
}
function MINUTEADD (date, num) {
  date.setMinutes(date.getMinutes() + num)
  return date
}
function MINUTES (date1, date2) {
  return Math.floor((date1 - date2) / (60 * 1000))
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
  return Math.floor((date1 - date2) / (1000))
}
function TIME () {
}
function TIMESTAMP () {
}
function TODATE () {
}
function TODAY () {
  // let date = new Date()
  // return new Date(date.getFullYear(), date.getMonth() + 1, date.getDate())
  return moment(new Date()).format('YYYY-MM-DD')
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
  return Math.floor((date1.getFullYear() - date2.getFullYear()))
}
// 53
