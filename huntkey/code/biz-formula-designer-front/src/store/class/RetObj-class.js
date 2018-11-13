class RetObj {
  /*
   参数实体
   参数名/值 paraName
   参数类型 paraType
   参数位置 paraPosition  二元运算符 左侧参数 LEFT 右侧参数 RIGHT
  */
  constructor (retMsg, retCode, expectObj) {
    this.retMsg = retMsg
    this.retCode = retCode
    this.expectObj = expectObj
  }
}
export {RetObj}
