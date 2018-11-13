import FormulaValidate from '../../utils/formula-validate.js'

class FuncStackEle {
  constructor (funcName, parenthesesStack, parasStack, funcParasStack, funcMsg) {
    this.funcName = funcName
    this.parenthesesStack = parenthesesStack
    this.parasStack = parasStack
    this.funcParasStack = funcParasStack
    this.funcMsg = funcMsg
  }
  // 函数状态 -1 不可计算 0 待计算（需要遇到触发符,)） 1 可计算
  getFuncState (defineCustomFuncs) {
    if (this.getParaState(defineCustomFuncs) === '1' && this.getParenthesesState(defineCustomFuncs) === 1) {
      return '1'
    } else {
      return '-1'
    }
  }
  // 参数状态 -1 未完成 1 已完成
  getParaState (defineCustomFuncs) {
    let parasStackLen = this.parasStack.length
    let funcParasStackLen = this.funcParasStack.length
    if (parasStackLen < funcParasStackLen) {
      if (FormulaValidate.isDefineCustomFunc(this.funcName, defineCustomFuncs)) {
        let funcParasStack = this.funcParasStack
        if (funcParasStack[parasStackLen].option === 'Option') {
          return '1'
        } else {
          return '-1'
        }
      } else {
        return '-1'
      }
    } else if (parasStackLen >= funcParasStackLen) {
      return '1'
    }
  }
  // 精确的参数状态 -1 未完成 0 可继续添加参数也可运算 1 已完成
  getParaStateExact (defineCustomFuncs) {
    let parasStackLen = this.parasStack.length
    let funcParasStackLen = this.funcParasStack.length
    if (funcParasStackLen === 0) {
      return '1'
    }
    if (parasStackLen < funcParasStackLen) {
      return '-1'
    } else if (parasStackLen >= funcParasStackLen) {
      if (FormulaValidate.isDefineCustomFunc(this.funcName, defineCustomFuncs)) {
        let funcParasStack = this.funcParasStack
        let paramType = funcParasStack[funcParasStackLen - 1]
        if (paramType.paramOrderType === 'Loop') {
          return '0'
        } else {
          return '1'
        }
      } else {
        return '1'
      }
    }
  }
  // 自定义函数括号状态 -1 未完成：缺少左右括号 0 待完成：左括号存在 1 已完成：左右括号都存在
  getParenthesesState (defineCustomFuncs) {
    if (FormulaValidate.isDefineCustomFunc(this.funcName, defineCustomFuncs) || this.funcName === '(') {
      return this.parenthesesStack.length - 1
    } else {
      return 1
    }
  }
}
export {FuncStackEle}
