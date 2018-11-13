import {FuncStackEle} from '../store/class/FuncStackEle-class.js'
import {ParaElememt} from '../store/class/ParaElememt-class.js'
import {RetObj} from '../store/class/RetObj-class.js'
import {FuncMsg} from '../store/class/FunMsg-class.js'
import store from '../store'
/*
TODO 1、defineCustomFuncList
 */
const FormulaValidateUtils = {
  testFormulaValidateUtils: function (defineCustomFuncs) {
  // console.info('FuncStackEle.test')
  },
  // 校验公式
  /*
  校验公式
  formulaArrayPara 待校验公式拆分后的数组
  defineCustomFuncs 自定义函数list
  exceptParaType 属性类型
  exceptParaName 属性名称
   */
  ananalyticFormula: function (formulaArrayPara, defineCustomFuncs, exceptParaType, exceptParaName) {
    console.log('待校验公式拆分后的数组', formulaArrayPara)
    // 单引号标注位 -1：无单引号 0：存在左单引号
    var quotesFlag = '-1'
    // 括号标注左右括号个数是否一样
    var bracketFlag = 0
    var funcStack = []
    var strs = ''
    this.preHandleFormulaArray(formulaArrayPara)
    // console.log('ttt', formulaArrayPara)
    for (let [index, elem] of new Map(formulaArrayPara.map((item, i) => [ i, item ]))) {
      // console.log(elem)
      //  elem 四大类型 str var func prop
      let elemType = elem.type
      if (quotesFlag === '0' && elemType !== 'str') {
        return new RetObj('单引号错误', '-1', index)
      }
      if (elemType === 'func') {
        let retDate = this.commitStrs(funcStack, strs, defineCustomFuncs)
        if (retDate.retCode === '1') {
          strs = ''
        } else {
          retDate.expectObj = index
          return retDate
        }
        if (!this.isDefineCustomFunc(elem.val, defineCustomFuncs)) {
          return new RetObj('函数 ' + elem.val + '错误', '-1', index)
        }
        let funcEle = this.createFuncEle(elem.val, defineCustomFuncs)
        let pushFuncStateObj = this.pushFunc2Stack(funcStack, funcEle, defineCustomFuncs)
        if (pushFuncStateObj.retCode !== '1') {
          pushFuncStateObj.expectObj = index
          return pushFuncStateObj
        } else {
          continue
        }
      }
      if (elemType === 'var' || elemType === 'prop') {
        let retDate = this.commitStrs(funcStack, strs, defineCustomFuncs)
        if (retDate.retCode === '1') {
          strs = ''
        } else {
          retDate.expectObj = index
          return retDate
        }
        let val = elem.label
        let returnType = elem.returnType
        let popEle = funcStack.pop()
        if (this.isDefineCustomFunc(popEle.funcName, defineCustomFuncs)) {
          if (popEle.getParenthesesState(defineCustomFuncs) === 0 && popEle.funcParasStack.length > 0 && popEle.parasStack.length > 0) {
            return new RetObj('函数 ' + popEle.funcName + ' 缺失逗号,分隔符错误', '-1', index)
          }
        }
        // 判断属性集是否同时使用
        if (this.IsPropMeanwhileUse(popEle, val, formulaArrayPara, exceptParaType, defineCustomFuncs)) {
          return new RetObj('属性集校验不通过', '-1', index)
        }
        funcStack.push(popEle)
        let pushParaStateObj = this.pushPara2Stack(funcStack, new ParaElememt(val, returnType, ''), defineCustomFuncs)
        if (pushParaStateObj.retCode !== '1') {
          pushParaStateObj.expectObj = index
          return pushParaStateObj
        } else {
          continue
        }
      }
      let val = elem.val
      if (!val || !val.replace(/(^\s*)|(\s*$)/g, '')) {
        continue
      }
      for (let str of val) {
        if (!str || !str.replace(/(^\s*)|(\s*$)/g, '')) {
          continue
        }
        if (str === '(') {
          bracketFlag += 1
        } else if (str === ')') {
          bracketFlag -= 1
        }
        if (quotesFlag === '0') {
          if (str === '\'') {
            let pushParaStateObj = this.pushPara2Stack(funcStack, new ParaElememt(strs, 'Varchar', ''), defineCustomFuncs)
            if (pushParaStateObj.retCode !== '1') {
              pushParaStateObj.expectObj = index
              return pushParaStateObj
            } else {
              strs = ''
              quotesFlag = '-1'
              continue
            }
          } else {
            strs += str
            continue
          }
        } else {
          if (str === '\'') {
            let retDate = this.commitStrs(funcStack, strs, defineCustomFuncs)
            if (retDate.retCode === '1') {
              strs = ''
              quotesFlag = '0'
              continue
            } else {
              retDate.expectObj = index
              return retDate
            }
          }
        }
        if (strs === '') {
          strs += str
          continue
        }
        if (this.isUnDefineCustomFunc(strs + str)) {
          var funcEle = this.createFuncEle(strs + str, defineCustomFuncs)
          let pushFuncStateObj = this.pushFunc2Stack(funcStack, funcEle, defineCustomFuncs)
          if (pushFuncStateObj.retCode !== '1') {
            pushFuncStateObj.expectObj = index
            return pushFuncStateObj
          } else {
            strs = ''
            continue
          }
        }
        if (this.isUnDefineCustomFunc(strs)) {
          var funcEle1 = this.createFuncEle(strs, defineCustomFuncs)
          let pushFuncStateObj = this.pushFunc2Stack(funcStack, funcEle1, defineCustomFuncs)
          if (pushFuncStateObj.retCode === '-1') {
            pushFuncStateObj.expectObj = index
            return pushFuncStateObj
          } else {
            strs = str
            continue
          }
        } else {
          if (this.isUnDefineCustomFunc(str) || str === '=' || str === '&' || str === '|') {
            if (isNaN(strs)) {
              return new RetObj('字符串 ' + strs + '错误', '-1', index)
            } else {
              let paraType = this.getNumExactScope(strs)
              let pushParaStateObj = this.pushPara2Stack(funcStack, new ParaElememt(strs, paraType, ''), defineCustomFuncs)
              if (pushParaStateObj.retCode === '-1') {
                pushParaStateObj.expectObj = index
                return pushParaStateObj
              } else {
                strs = str
                continue
              }
            }
          } else {
            strs += str
            continue
          }
        }
      }
    }
    if (bracketFlag > 0) {
      return new RetObj('存在错误的左括号( 或 缺失对应的右括号)', '-1', '')
    } else if (bracketFlag < 0) {
      return new RetObj('存在错误的右括号) 或 缺失对应的左括号(', '-1', '')
    }
    if (strs !== '') {
      if (this.isUnDefineCustomFunc(strs)) {
        var funcEle2 = this.createFuncEle(strs, defineCustomFuncs)
        let pushFuncStateObj = this.pushFunc2Stack(funcStack, funcEle2, defineCustomFuncs)
        if (pushFuncStateObj.retCode === '-1') {
          pushFuncStateObj.expectObj = ''
          return pushFuncStateObj
        }
      } else {
        if (isNaN(strs)) {
          return new RetObj('字符串 ' + strs + '错误', '-1', '')
        } else {
          let paraType = this.getNumExactScope(strs)
          let pushParaStateObj = this.pushPara2Stack(funcStack, new ParaElememt(strs, paraType, ''), defineCustomFuncs)
          if (pushParaStateObj.retCode === '-1') {
            pushParaStateObj.expectObj = ''
            return pushParaStateObj
          }
        }
      }
    }
    if (quotesFlag === '0') {
      return new RetObj('单引号错误', '-1', '')
    } else {
      if (funcStack.length === 1) {
        console.log('测试', funcStack[0].parasStack[0].paraType)
        if (this.typeCompare([exceptParaType], funcStack[0].parasStack[0].paraType)) {
          return new RetObj('检验通过', '1', '')
        } else {
          return new RetObj('校验不通过,属性 ' + exceptParaName + ' 类型 ' + exceptParaType + ' 与公式返回类型 ' + funcStack[0].parasStack[0].paraType + ' 不一致', '-1', '')
        }
      } else {
        return new RetObj('校验错误', '-1', '')
      }
    }
  },
  // 判断属性集与函数同时使用
  IsPropMeanwhileUse (popEle, val, formulaArrayPara, exceptParaType, defineCustomFuncs) {
    // console.log(val)
    // console.log(formulaArrayPara)
    // console.log(exceptParaType)
    // console.log(defineCustomFuncs)
    let paraRetFun = ['AVG', 'SUM', 'MAX', 'MIN', 'COUNT']
    let numberFunArray = ['LARGE', 'SMALL', 'SUMPRODUCT']
    let paraStackNameCount = (val.split('.')).length - 1
    if (paraStackNameCount !== 1) {
      // if (popEle.funcName === '(') {
      //   return true
      // }
      if (paraRetFun.indexOf(popEle.funcName) < 0) {
        // console.log('数组', formulaArrayPara)
        for (let j in formulaArrayPara) {
          if (formulaArrayPara[j].type === 'prop') {
            let len = formulaArrayPara[j].label.split('.').length
            if (len > 1) {
              if (j - 2 <= 0) {
                return true
              }
              let topLen = formulaArrayPara[j - 2].label.split('.').length
              let val = formulaArrayPara[j - 2].val
              let type = formulaArrayPara[j - 2].type
              // console.log('topLen', topLen)
              // console.log('val', val)
              // console.log('type', type)
              if (type !== 'func' && topLen < 2 && val === '(') {
                return true
              }
            }
          }
          if (formulaArrayPara[j].type === 'func') {
            let funcName = formulaArrayPara[j].val
            if (formulaArrayPara[j].returnType === exceptParaType.toUpperCase() || (formulaArrayPara[j].returnType === 'INT' && exceptParaType.toUpperCase() === 'DECIMAL')) {
              let allowFuncPara = ['INT', 'FIXED', 'LOG', 'PRODUCT', 'SQRT']
              for (let i in defineCustomFuncs) {
                if (funcName === defineCustomFuncs[i].funName) {
                  if (allowFuncPara.indexOf(defineCustomFuncs[i].funName) >= 0) {
                    if (formulaArrayPara[j - 2] && formulaArrayPara[j - 2].type === 'func') {
                      if (paraRetFun.indexOf(formulaArrayPara[j - 2].val) < 0) {
                        return true
                      }
                    } else if (formulaArrayPara[j - 3] && formulaArrayPara[j - 3].type === 'func') {
                      if (paraRetFun.indexOf(formulaArrayPara[j - 3].val) < 0) {
                        return true
                      }
                    } else if (formulaArrayPara[j - 3] && formulaArrayPara[j - 3].type !== undefined) {
                      return true
                    }
                  }
                }
              }
            }
          }
        }
      }
    } else if (paraStackNameCount === 1) {
      let funRetType = popEle.funcMsg.funcRetType
      if (funRetType === exceptParaType.toUpperCase() && numberFunArray.indexOf(popEle.funcName) >= 0) {
        for (let i in formulaArrayPara) {
          if (formulaArrayPara[i].type === 'prop' && formulaArrayPara[i].returnType === funRetType.toLowerCase()) {
            return true
          }
        }
      }
    }
  },
// // 判断属性集与函数同时使用
//   IsPropMeanwhileUse (popEle, val, formulaArrayPara, exceptParaType, defineCustomFuncs) {
//     let paraRetFun = ['AVG', 'SUM', 'MAX', 'MIN', 'COUNT']
//     let numberFunArray = ['LARGE', 'SMALL', 'SUMPRODUCT']
//     let paraStackNameCount = (val.split('.')).length - 1
//     if (paraStackNameCount !== 1) {
//       if (popEle.funcName === '(') {
//         return true
//       }
//       if (paraRetFun.indexOf(popEle.funcName) < 0) {
//         for (let j in formulaArrayPara) {
//           if (formulaArrayPara[j].type === 'func') {
//             let funcName = formulaArrayPara[j].val
//             if (formulaArrayPara[j].returnType === exceptParaType.toUpperCase() || (formulaArrayPara[j].returnType === 'INT' && exceptParaType.toUpperCase() === 'DECIMAL')) {
//               let allowFuncPara = ['INT', 'FIXED', 'LOG', 'PRODUCT', 'SQRT']
//               for (let i in defineCustomFuncs) {
//                 if (funcName === defineCustomFuncs[i].funName) {
//                   if (allowFuncPara.indexOf(defineCustomFuncs[i].funName) >= 0) {
//                     if (formulaArrayPara[j - 2] && formulaArrayPara[j - 2].type === 'func') {
//                       if (paraRetFun.indexOf(formulaArrayPara[j - 2].val) < 0) {
//                         return true
//                       }
//                     } else if (formulaArrayPara[j - 3] && formulaArrayPara[j - 3].type === 'func') {
//                       if (paraRetFun.indexOf(formulaArrayPara[j - 3].val) < 0) {
//                         return true
//                       }
//                     } else {
//                       return true
//                     }
//                   }
//                 }
//               }
//             }
//           }
//         }
//       }
//     } else if (paraStackNameCount === 1) {
//       let funRetType = popEle.funcMsg.funcRetType
//       if (funRetType === exceptParaType.toUpperCase() && numberFunArray.indexOf(popEle.funcName) >= 0) {
//         for (let i in formulaArrayPara) {
//           if (formulaArrayPara[i].type === 'prop' && formulaArrayPara[i].returnType === funRetType.toLowerCase()) {
//             return true
//           }
//         }
//       }
//     }
//   },
  // 预处理公式数组
  preHandleFormulaArray: function (formulaArrayPara) {
    formulaArrayPara.unshift({'type': 'str', 'val': '('})
    formulaArrayPara.push({'type': 'str', 'val': ')'})
  },
  commitStrs: function (funcStack, strs, defineCustomFuncs) {
    if (!strs) {
      return new RetObj('', '1', '')
    }
    console.log(this.isUnDefineCustomFunc(strs))
    if (this.isUnDefineCustomFunc(strs)) {
      var funcEle = this.createFuncEle(strs, defineCustomFuncs)
      let pushFuncStateObj = this.pushFunc2Stack(funcStack, funcEle, defineCustomFuncs)
      return pushFuncStateObj
    } else {
      if (isNaN(strs)) {
        return new RetObj('字符串 ' + strs + '错误', '-1', '')
      } else {
        let paraType = this.getNumExactScope(strs)
        let pushParaStateObj = this.pushPara2Stack(funcStack, new ParaElememt(strs, paraType, ''), defineCustomFuncs)
        return pushParaStateObj
      }
    }
  },
  pushFunc2Stack: function (funcStack, funcEle, defineCustomFuncs) {
    if (funcStack.length === 0) {
      if (this.isBinaryFunc(funcEle.funcName)) {
        if (funcEle.funcName === '-') {
          // 处理负号 增加一个0参数 作为减号函数处理
          funcEle.funcMsg.paraState = '0'
          funcEle.parasStack.push(new ParaElememt('0', 'int', 'LEFT'))
        } else {
          return new RetObj('函数' + funcEle.funcName + '参数个数错误', '-1', '')
        }
      }
      funcStack.push(funcEle)
      return new RetObj('', '1', '')
    } else {
      let preHandleMsg = this.preHandleFuncEle(funcStack, funcEle, defineCustomFuncs)
      if (preHandleMsg.retCode === '-1') {
        return preHandleMsg
      }
      let funcStackState = this.isOkFristFuncStackEle(funcStack, funcEle, defineCustomFuncs)
      if (funcStackState.retCode === '-1') {
        return funcStackState
      }
      if (funcEle.funcName === '' || funcStack.length === 0) {
        return new RetObj('', '1', '')
      }
      var popEle = funcStack.pop()
      if (funcEle.funcName === ')') {
        return new RetObj('函数 ' + popEle.funcName + ' 参数个数或类型错误', '-1', '')
      }
      if (funcEle.funcName === '(') {
        if (this.isDefineCustomFunc(popEle.funcName, defineCustomFuncs) && popEle.funcMsg.parenthesesState === '-1') {
          popEle.parenthesesStack.push('(')
          popEle.funcMsg.parenthesesState = '0'
          funcStack.push(popEle)
          return new RetObj('', '1', '')
        } else {
          funcStack.push(popEle)
          funcStack.push(funcEle)
          return new RetObj('', '1', '')
        }
      }
      if (funcEle.funcName === ',') {
        if (this.isDefineCustomFunc(popEle.funcName, defineCustomFuncs)) {
          if (popEle.parenthesesStack.length === 0) {
            return new RetObj('逗号' + funcEle.funcName + '位置错误，函数 ' + popEle.funcName + '缺失左括号', '-1', '')
          } else if (popEle.getParaStateExact(defineCustomFuncs) === '1') {
            return new RetObj('逗号' + funcEle.funcName + '错误，函数 ' + popEle.funcName + '参数个数已满足', '-1', '')
          } else if (popEle.parasStack.length === 0) {
            return new RetObj('逗号' + funcEle.funcName + '错误，函数 ' + popEle.funcName + ' 中逗号前需要存在参数', '-1', '')
          } else {
            funcStack.push(popEle)
            funcStack.push(funcEle)
            return new RetObj('', '1', '')
          }
        } else {
          return new RetObj('逗号' + funcEle.funcName + '位置错误', '-1', '')
        }
      }
      if (this.isDefineCustomFunc(funcEle.funcName, defineCustomFuncs)) {
        if (popEle.funcName === ',') {
          if (popEle.parasStack.length !== 0) {
            return new RetObj('函数' + funcEle.funcName + '错误', '-1', '')
          }
        } else if (popEle.funcName === '(') {
          if (popEle.parasStack.length !== 0) {
            return new RetObj('函数' + funcEle.funcName + '错误', '-1', '')
          }
        } else if (this.isDefineCustomFunc(popEle.funcName, defineCustomFuncs)) {
          if (popEle.getParenthesesState(defineCustomFuncs) !== 0 || popEle.getParaState(defineCustomFuncs) === '1') {
            return new RetObj('函数' + funcEle.funcName + '错误', '-1', '')
          }
        }
        funcStack.push(popEle)
        funcStack.push(funcEle)
        return new RetObj('', '1', '')
      }
      if (this.isBinaryFunc(funcEle.funcName)) {
        if (funcEle.parasStack.length === 1) {
          funcStack.push(popEle)
          funcStack.push(funcEle)
          return new RetObj('', '1', '')
        } else {
          return new RetObj('函数' + funcEle.funcName + '错误', '-1', '')
        }
      }
      if (funcEle.funcName === '!') {
        if (popEle.funcName === '(' || popEle.funcName === ',') {
          if (popEle.parasStack.length !== 0) {
            return new RetObj('函数' + funcEle.funcName + '错误', '-1', '')
          }
        } else if (this.isDefineCustomFunc(popEle.funcName, defineCustomFuncs)) {
          if (popEle.getParenthesesState(defineCustomFuncs) === -1 || popEle.getParaState(defineCustomFuncs) === '1') {
            return new RetObj('函数' + funcEle.funcName + '错误', '-1', '')
          }
        }
        funcStack.push(popEle)
        funcStack.push(funcEle)
        return new RetObj('', '1', '')
      }
      return new RetObj('', '-1', '')
    }
  },
  preHandleFuncEle: function (funcStack, funcEle, defineCustomFuncs) {
    if (funcStack.length === 0 || funcEle.funcName === '') {
      return new RetObj('', '1', '')
    }
    var popEle = funcStack.pop()
    if (this.isBinaryFunc(funcEle.funcName)) {
      if (this.isBinaryFunc(popEle.funcName)) {
        var funcParaState = popEle.getParaState(defineCustomFuncs)
        if (funcParaState === '1') {
          let paraElememt = popEle.parasStack.pop()
          if (this.typeCompare(['number'], paraElememt.paraType) && paraElememt.paraPosition === 'RIGHT') {
            if (this.isGt(funcEle.funcName, popEle.funcName)) {
              paraElememt.paraPosition = 'LEFT'
              funcEle.parasStack.push(paraElememt)
            } else {
              popEle.parasStack.push(paraElememt)
            }
            funcStack.push(popEle)
            return new RetObj('', '1', '')
          } else {
            return new RetObj('函数' + popEle.funcName + '参数类型错误', '-1', '')
          }
        } else {
          return new RetObj('函数' + popEle.funcName + '参数个数错误', '-1', '')
        }
      } else if (this.isDefineCustomFunc(popEle.funcName, defineCustomFuncs)) {
        if (popEle.parasStack.length !== 0) {
          let paraElememt = popEle.parasStack.pop()
          if (this.typeCompare(['number'], paraElememt.paraType)) {
            paraElememt.paraPosition = 'LEFT'
            funcEle.parasStack.push(paraElememt)
          } else {
            return new RetObj('函数 ' + funcEle.funcName + ' 参数 ' + paraElememt.paraName + ' 类型错误', '-1', '')
          }
        } else if (popEle.parasStack.length === 0) {
          if (funcEle.funcName === '-' && popEle.getParenthesesState(defineCustomFuncs) === 0) {
            funcEle.parasStack.push(new ParaElememt('0', 'int', 'LEFT'))
          } else {
            return new RetObj('函数' + funcEle.funcName + '参数个数错误', '-1', '')
          }
        } else {
          return new RetObj('函数' + funcEle.funcName + '参数错误', '-1', '')
        }
        funcStack.push(popEle)
        return new RetObj('', '1', '')
      } else if (popEle.funcName === '(') {
        if (popEle.parasStack.length === 1) {
          let paraElememt = popEle.parasStack.pop()
          if (this.typeCompare(['number'], paraElememt.paraType)) {
            paraElememt.paraPosition = 'LEFT'
            funcEle.parasStack.push(paraElememt)
          } else {
            return new RetObj('函数 ' + funcEle.funcName + ' 参数 ' + paraElememt.paraName + ' 类型错误', '-1', '')
          }
        } else if (popEle.parasStack.length === 0) {
          if (funcEle.funcName === '-') {
            funcEle.parasStack.push(new ParaElememt('0', 'int', 'LEFT'))
          } else {
            return new RetObj('函数' + funcEle.funcName + '参数个数错误', '-1', '')
          }
        } else {
          return new RetObj('函数' + funcEle.funcName + '参数个数错误', '-1', '')
        }
        funcStack.push(popEle)
        return new RetObj('', '1', '')
      } else if (popEle.funcName === ',') {
        if (popEle.parasStack.length === 1) {
          let paraElememt = popEle.parasStack.pop()
          if (this.typeCompare(['number'], paraElememt.paraType)) {
            paraElememt.paraPosition = 'LEFT'
            funcEle.parasStack.push(paraElememt)
          } else {
            return new RetObj('函数 ' + funcEle.funcName + ' 参数 ' + paraElememt.paraName + ' 类型错误', '-1', '')
          }
        } else {
          if (funcEle.funcName === '-') {
            funcEle.funcMsg.paraState = '0'
            funcEle.parasStack.push(new ParaElememt('0', 'int', 'LEFT'))
          } else {
            return new RetObj('函数' + funcEle.funcName + '参数个数错误', '-1', '')
          }
        }
        funcStack.push(popEle)
        return new RetObj('', '1', '')
      } else if (popEle.funcName === 'NULLPREFUNC') {
        if (popEle.parasStack.length === '1') {
          var paraElememt = popEle.parasStack.pop()
          if (this.typeCompare(['number'], paraElememt.paraType)) {
            paraElememt.paraPosition = 'LEFT'
            funcEle.parasStack.push(paraElememt)
            return new RetObj('', '1', '')
          } else {
            return new RetObj('函数' + funcEle.funcName + '参数 ' + paraElememt.paraName + ' 类型错误', '-1', '')
          }
        } else {
          return new RetObj('函数' + funcEle.funcName + '参数个数错误', '-1', '')
        }
      } else {
        return new RetObj('函数' + funcEle.funcName + '参数个数错误', '-1', '')
      }
    } else if (funcEle.funcName === ')') {
      if (popEle.funcName === '(') {
        if (popEle.getParaState(defineCustomFuncs) === '1') {
          popEle.parenthesesStack.push('(')
          popEle.parenthesesStack.push(')')
          funcStack.push(popEle)
          funcEle.funcName = ''
          return new RetObj('', '1', '')
        } else {
          return new RetObj('空括号(' + funcEle.funcName + '错误', '-1', '')
        }
      } else if (this.isDefineCustomFunc(popEle.funcName, defineCustomFuncs)) {
        if (popEle.parasStack.length === 1) {
          let funcParasStack = popEle.funcParasStack
          let paramType = funcParasStack[0]
          let paraElememt = popEle.parasStack.pop()
          if (this.typeCompare(paramType.types, paraElememt.paraType)) {
            popEle.parasStack.push(paraElememt)
          } else {
            return new RetObj('函数 ' + popEle.funcName + ' 参数 ' + paraElememt.paraName + ' 类型错误', '-1', '')
          }
        }
        if (popEle.getParenthesesState(defineCustomFuncs) === 0 && popEle.getParaState(defineCustomFuncs) === '1') {
          popEle.parenthesesStack.push(')')
          funcStack.push(popEle)
          funcEle.funcName = ''
          return new RetObj('', '1', '')
        } else {
          return new RetObj('函数' + popEle.funcName + '参数个数错误或左括号缺失', '-1', '')
        }
      } else if (popEle.funcName === ',') {
        if (popEle.parasStack.length === 0) {
          return new RetObj('右括号)前存在错误的逗号,', '-1', '')
        }
      } else if (this.isUnDefineCustomFunc(popEle.funcName)) {
        if (popEle.getParaState(defineCustomFuncs) !== '1') {
          return new RetObj('函数' + popEle.funcName + '参数个数错误', '-1', '')
        }
      }
    } else if (funcEle.funcName === ',') {
      if (this.isDefineCustomFunc(popEle.funcName, defineCustomFuncs) && popEle.parasStack.length === 1) {
        let funcParasStack = popEle.funcParasStack
        let paramType = funcParasStack[0]
        let paraElememt = popEle.parasStack.pop()
        if (this.typeCompare(paramType.types, paraElememt.paraType)) {
          popEle.parasStack.push(paraElememt)
        } else {
          return new RetObj('函数 ' + popEle.funcName + '参数 ' + paraElememt.paraName + '类型错误', '-1', '')
        }
      }
    }
    funcStack.push(popEle)
    return new RetObj('', '1', '')
  },
  // 栈顶函数状态是否可计算
  isOkFristFuncStackEle: function (funcStack, funcEle, defineCustomFuncs) {
    if (funcStack.length === 0) {
      return new RetObj('', '1', '')
    }
    var popEle = funcStack.pop()
    if (popEle.getFuncState(defineCustomFuncs) === '1' && (this.isBinaryFunc(popEle.funcName) || this.isDefineCustomFunc(popEle.funcName, defineCustomFuncs) || popEle.funcName === '(' || popEle.funcName === ',')) {
      let retType = this.getFuncCalRetType(popEle, defineCustomFuncs)
      let prePopEle = funcStack.pop()
      if (prePopEle) {
        if (this.isDefineCustomFunc(prePopEle.funcName, defineCustomFuncs) && popEle.funcName !== ',') {
          if (prePopEle.getParenthesesState(defineCustomFuncs) === 0 && prePopEle.funcParasStack.length > 0 && prePopEle.parasStack.length > 0) {
            return new RetObj('函数 ' + prePopEle.funcName + ' 缺失逗号,分隔符错误', '-1', '')
          }
        }
        funcStack.push(prePopEle)
      }
      let pushParaState = this.pushPara2Stack(funcStack, new ParaElememt(this.buildParaName(popEle, defineCustomFuncs), retType, ''), defineCustomFuncs)
      if (pushParaState.retCode === '1') {
        if (funcEle.funcName !== '') {
          let preHandleState = this.preHandleFuncEle(funcStack, funcEle, defineCustomFuncs)
          if (preHandleState.retCode === '1' && funcStack.length > 0) {
            return this.isOkFristFuncStackEle(funcStack, funcEle, defineCustomFuncs)
          } else {
            return preHandleState
          }
        } else {
          return pushParaState
        }
      } else {
        // TODO
        pushParaState.retMsg = this.buildParaName(popEle, defineCustomFuncs) + ' 类型错误'
        return pushParaState
      }
    } else {
      funcStack.push(popEle)
      return new RetObj('', '1', '')
    }
  },
  // 将得到的参数压栈
  pushPara2Stack: function (funcStack, paraElememt, defineCustomFuncs) {
    var funcEle = ''
    if (funcStack.length === 0) {
      funcEle = this.createFuncEle('NULLPREFUNC', defineCustomFuncs)
      funcEle.funcMsg.funcRetType = paraElememt.paraType
      funcEle.parasStack.push(paraElememt)
      funcStack.push(funcEle)
      return new RetObj('', '1', '')
    } else {
      funcEle = funcStack.pop()
      let parasStack = funcEle.parasStack
      let funcParasStack = funcEle.funcParasStack
      let parasStackLen = parasStack.length
      let funcParasStackLen = funcParasStack.length
      if (this.isDefineCustomFunc(funcEle.funcName, defineCustomFuncs)) {
        if (funcEle.getParenthesesState(defineCustomFuncs) === -1) {
          return new RetObj('函数 ' + funcEle.funcName + '缺失左括号', '-1', '')
        }
      }
      if (funcParasStackLen === 0) {
        return new RetObj('函数 ' + funcEle.funcName + ' 参数个数应当为0', '-1', '')
      }
      if (parasStackLen >= funcParasStackLen) {
        let paramType = funcParasStack[funcParasStackLen - 1]
        if (paramType.paramOrderType === 'Loop' && this.typeCompare(paramType.types, paraElememt.paraType)) {
          funcEle.parasStack.push(paraElememt)
          funcStack.push(funcEle)
          return new RetObj('', '1', '')
        } else {
          return new RetObj('参数 ' + paraElememt.paraName + ' 错误', '-1', '')
        }
      } else {
        if (this.isDefineCustomFunc(funcEle.funcName, defineCustomFuncs)) {
          if (funcEle.getParenthesesState(defineCustomFuncs) === 0 && funcEle.parasStack.length === 0) {
            // 临时存储 格式校验有prehandle处理
            funcEle.parasStack.push(paraElememt)
            funcStack.push(funcEle)
            return new RetObj('', '1', '')
          }
        }
        let paramType = funcParasStack[parasStackLen]
        if (this.typeCompare(paramType.types, paraElememt.paraType)) {
          if (this.isBinaryFunc(funcEle.funcName) && funcEle.parasStack.length === 0) {
            paraElememt.paraPosition = 'LEFT'
          } else {
            paraElememt.paraPosition = 'RIGHT'
          }
          funcEle.parasStack.push(paraElememt)
          if (funcEle.funcName === '(' || funcEle.funcName === ',') {
            funcEle.funcMsg.funcRetType = paraElememt.paraType
          }
          if (funcEle.funcName === '!') {
            funcEle = this.pushPara2Stack(funcStack, new ParaElememt('!' + paraElememt.paraName, 'Boolean', ''), defineCustomFuncs)
          } else {
            funcStack.push(funcEle)
          }
          return new RetObj('', '1', '')
        } else {
          return new RetObj('参数 ' + paraElememt.paraName + '错误', '-1', '')
        }
      }
    }
  },
  /*
  类型比较
  types：函数参数对应的类型
  paraType：参数的类型
  return: true|types包含paraType false|types不包含paraType
  decimal int || number decimal int
  text varchar || varchar
  number decimal double float long int
   */
  typeCompare: function (types, paraType) {
    paraType = paraType.toUpperCase()
    types = types.map((type) => type.toUpperCase())
    if (['TEXT', 'VARCHAR'].includes(paraType)) {
      paraType = 'VARCHAR'
    }
    if (types.includes('ANY'.toUpperCase()) || types.includes(paraType)) {
      return true
    }
    let numArray = ['NUMBER', 'DECIMAL', 'INT']
    let paraTypeArraySize = numArray.indexOf(paraType)
    if (paraTypeArraySize === -1) {
      return false
    } else {
      for (let item of types) {
        let itemArraySize = numArray.indexOf(item)
        if (itemArraySize <= paraTypeArraySize && itemArraySize >= 0) {
          return true
        }
      }
    }
    return false
  },
  /*
  构建参数名
   */
  buildParaName: function (funcEle, defineCustomFuncs) {
    var funcName = funcEle.funcName
    if (funcName === '!') {
      return ' !' + funcEle.parasStack[0].paraName + ' '
    } else if (funcName === ',') {
      return ' ' + funcEle.parasStack[0].paraName + ' '
    } else if (this.isDefineCustomFunc(funcName, defineCustomFuncs)) {
      let buildParaName = funcName + '('
      let flag = true
      for (let item of funcEle.parasStack) {
        if (!flag) {
          buildParaName += ' , '
        }
        flag = false
        buildParaName += item.paraName
      }
      return buildParaName + ') '
    } else if (this.isBinaryFunc(funcName)) {
      return funcEle.parasStack[0].paraName + funcName + funcEle.parasStack[1].paraName
    } else if (funcName === '(') {
      return ' (' + funcEle.parasStack[0].paraName + ') '
    } else {
      return ''
    }
  },
  /*
  获取函数结果参数计算后的返回值
  1、加减乘除运算 ：int与int进行计算返回int型，否者返回类型设置为decimal
   */
  getFuncCalRetType: function (funcEle, defineCustomFuncs) {
    let retType = funcEle.funcMsg.funcRetType
    let funcName = funcEle.funcName
    if (this.isMulBinaryFunc(funcName) || this.isAddSubBinaryFunc(funcName)) {
      if (funcEle.parasStack[0].paraType.toUpperCase() === 'int'.toUpperCase() && funcEle.parasStack[1].paraType.toUpperCase() === 'int'.toUpperCase()) {
        retType = 'int'.toUpperCase()
      } else {
        retType = 'decimal'.toUpperCase()
      }
    }
    return retType
  },
  /*
  获取数字的精确的类型 int|demimal
   */
  getNumExactScope: function (strs) {
    let paraType = 'number'.toUpperCase()
    if (Number.isInteger(parseFloat(strs))) {
      paraType = 'int'.toUpperCase()
    } else {
      paraType = 'decimal'.toUpperCase()
    }
    return paraType
  },
  /*
    判断是否为函数
    是: return true 否: return false
    i.一元函数
    ii.二元函数
    iii.自定义函数
    iv.括号函数()
    v.单引号函数''
    vi.逗号函数,
   */
  isFunc: function (funcName, defineCustomFuncs) {
    return this.isDefineCustomFunc(funcName, defineCustomFuncs) || this.isUnDefineCustomFunc(funcName)
  },
  // 是否自定义函数
  isDefineCustomFunc: function (funcName, defineCustomFuncs) {
    for (let item of defineCustomFuncs) {
      if (funcName === item.funName) {
        return true
      }
    }
    return false
  },
  // 除非自定义函数外的合法函数
  isUnDefineCustomFunc: function (funcName) {
    return this.isBinaryFunc(funcName) || funcName === '(' || funcName === ')' || funcName === ',' || funcName === '!'
  },
  // 是否二元函数
  isBinaryFunc: function (funcName) {
    return this.isMulBinaryFunc(funcName) || this.isAddSubBinaryFunc(funcName) || this.isLogicBinaryFunc(funcName) || this.isLogicXORFunc(funcName)
  },
  isMulBinaryFunc: function (funcName) {
    return funcName === '*' || funcName === '/' || funcName === '%'
  },
  isAddSubBinaryFunc: function (funcName) {
    return funcName === '+' || funcName === '-'
  },
  isLogicBinaryFunc: function (funcName) {
    return funcName === '<' || funcName === '>' || funcName === '>=' || funcName === '<=' || funcName === '==' || funcName === '!='
  },
  isLogicXORFunc: function (funcName) {
    return funcName === '&&' || funcName === '||'
  },
  // 获取函数需要的参数
  getFuncParas: function (funcName, defineCustomFuncs) {
    if (funcName === 'NULLPREFUNC') {
      return []
    } else {
      for (let item of defineCustomFuncs) {
        if (funcName === item.funName) {
          return item.paramTypes
        }
      }
      for (let item of store.state.dict.otherFuncList) {
        if (funcName === item.funName) {
          return item.paramTypes
        }
      }
      return []
    }
  },
  // 获取函数返回值类型
  getFuncRetType: function (funcName, defineCustomFuncs) {
    if (funcName === 'NULLPREFUNC') {
      return 'any'
    } else {
      for (let item of defineCustomFuncs) {
        if (funcName === item.funName) {
          return item.funType
        }
      }
      for (let item of store.state.dict.otherFuncList) {
        if (funcName === item.funName) {
          return item.funType
        }
      }
      return ''
    }
  },
  createFuncEle: function (funcName, defineCustomFuncs) {
    let funcParasStack = this.getFuncParas(funcName, defineCustomFuncs)
    let parasStack = []
    var paraState = '-1'
    if (funcParasStack.length === 0) {
      paraState = '1'
    }
    var parenthesesState = '-1'
    if (!this.isDefineCustomFunc(funcName, defineCustomFuncs)) {
      parenthesesState = '1'
    }
    let funcRetType = this.getFuncRetType(funcName, defineCustomFuncs)
    return new FuncStackEle(funcName, [], parasStack, funcParasStack, new FuncMsg('-1', paraState, parenthesesState, funcRetType))
  },
  /*
  比较两个函数优先级
  true: funcName 优先级大于 popEleFunctionName  false: funcName 优先级不大于 popEleFunctionName
  发现只是二元操作符之间的优先级比较，很简单了
  / * % 优先级最高
  + - 次之
  逻辑判断最低
  isMulBinaryFunc(funcName) || isAddSubBinaryFunc(funcName) || isLogicBinaryFunc(funcName) || isLogicXORFunc(funcName)
  */
  isGt: function (funcName, popEleFunctionName) {
    if (this.isMulBinaryFunc(funcName)) {
      return true
    } else if (this.isAddSubBinaryFunc(funcName)) {
      if (this.isMulBinaryFunc(popEleFunctionName) || this.isAddSubBinaryFunc(popEleFunctionName)) {
        return false
      } else {
        return true
      }
    } else if (this.isLogicBinaryFunc(funcName)) {
      if (this.isLogicXORFunc(popEleFunctionName)) {
        return true
      } else {
        return false
      }
    }
    return false
  }
}
export default FormulaValidateUtils
