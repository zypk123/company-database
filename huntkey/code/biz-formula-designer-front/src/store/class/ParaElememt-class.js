class ParaElememt {
  /*
     参数实体
     参数名/值 paraName
     参数类型 paraType
     参数位置 paraPosition  二元运算符 左侧参数 LEFT 右侧参数 RIGHT
  */
  constructor (paraName, paraType, paraPosition) {
    this.paraName = paraName
    this.paraType = paraType
    this.paraPosition = paraPosition
  }
}
export {ParaElememt}
