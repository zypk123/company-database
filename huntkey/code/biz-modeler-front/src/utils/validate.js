const patterns = {
  // 账号
  account: /^[A-Za-z0-9_]*$/,
  // ip
  ip: /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
  // 邮箱
  email: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
  // 日期
  date: /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/,
  // 手机号
  phone: /^1[3|4|5|7|8][0-9]{9}$/,
  // 版本号
  version: /^[Vv]\d+(.\d+){0,2}$/,
  // 中文 不能包含空格
  zh: /^[\u4e00-\u9fa5]*$/,
  // 小写英文
  en: /^[a-z]*$/,
  // 中文&英文
  zh_en: /^[a-zA-Z\s\u4e00-\u9fa5]*$/,
  // 整型
  int: /^(-)?\d+$/,
  // 浮点型数字
  float: /^(-)?\d+(\.\d+)?$/
}

export default (str, pat) => {
  if (str) {
    const thePat = patterns[pat]
    return thePat.test(str)
  }
  return true
}
