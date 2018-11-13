class FuncMsg {
  /*
  函数描述信息实体
  函数状态 -1 不可计算 0 待计算（需要遇到触发符,)） 1 可计算
  参数状态 -1 未完成 0 待完成 1 已完成
  自定义函数括号状态 -1 未完成：缺少左右括号 0 待完成：左括号存在 1 已完成：左右括号都存在
  函数返回值类型 funcRetType
  */
  constructor (funcState, paraState, parenthesesState, funcRetType) {
    this.funcState = funcState
    this.paraState = paraState
    this.parenthesesState = parenthesesState
    this.funcRetType = funcRetType
  }
}
export {FuncMsg}
