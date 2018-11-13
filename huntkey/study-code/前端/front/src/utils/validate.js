const patterns = {
  // 账号
  account: /^[A-Za-z0-9_]*$/,
  // ip
  ip: /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
  // 邮箱
  email: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
  // 日期
  date: /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/,
  // 日期
  datetime: /^(\d{4})-(\d{1,2})-(\d{1,2})\s{1}(\d{2}):(\d{2}):(\d{2})$/,
  // 手机号
  phone: /^1[3|4|5|7|8][0-9]{9}$/,
  // 版本号
  version: /^[Vv]\d+(.\d+){0,2}$/,
  // 中文 不能包含空格
  zh: /^[\u4e00-\u9fa5]*$/,
  // 英文, 中间可以包含空格
  en: /^[a-zA-Z\s]*/,
  // 中文&英文
  zh_en: /^[a-zA-Z\s\u4e00-\u9fa5]*$/,
  // 数字
  number: /^(-?\d+)(\.\d+)?$/,
  // 整数
  integer: /^-?[0-9]+$/,
  // 逻辑值(字符串类)
  logic_str: /^true|false$/,
  // 名称(字母开头,英文和数字,不能包含空格)
  name_en: /^[a-zA-Z]+[a-zA-Z0-9]*$/,
  // 名称(大写字母开头,字母和数字)
  func_class_name: /^[A-Z]+[a-zA-Z0-9]*$/,
  // 函数名称(大写字母开头,大写字母和数字)
  func_name: /^[A-Z]+[A-Z0-9]*$/
}

export default (str, pat) => {
  const thePat = patterns[pat]
  return thePat.test(str)
}
