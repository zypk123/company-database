// import Utils from '../utils/utils'
/**
 * 所用公式设计器页面公用的方法.
 * 前置条件:
 *  定义了: this.store
 *  定义了: this.store.freqUsedFuncs
 *  定义了: this.store.allFuncs
 *  定义了: this.store.initFuncs (用于公式校验 for 陆礼祥)
 *  定义了: this.store.sysVars
 *  定义了: this.store.localVars (允许为空)
 */
const mixin = {
  methods: {
    // 初始化常用函数, 把后台传过来的函数列表封装成前台要用的格式
    initFreqUsedFuncs (funcsList) {
      let funcs = []
      funcsList.forEach(
        (func, funcIndex, arr) => {
          let aFunc = {
            funName: func.funName,
            returnType: func.funType,
            funDesc: func.funDesc
          }
          funcs.push(aFunc)
        }
      )
      // 常用函数列表两行16个, 有5个写死了, 取最前面的11个就好
      this.store.freqUsedFuncs = this.Utils.take(funcs, 11)
    }, // end of initFreqUsedFuncs
    initAllFuncsInfo (funcsList) {
      this.store.initFuncs = funcsList
      let funcGroups = []
      funcsList.forEach(
        (func, funcIndex, arr) => {
          if (!funcGroups[func.funClassifyOrder]) {
            funcGroups[func.funClassifyOrder] = {
              groupName: func.classifyDesc,
              groupCode: func.funClassify,
              funcs: []
            }
          }
          let aFunc = {
            funName: func.funName,
            returnType: func.funType,
            funDesc: func.funDesc
          }
          funcGroups[func.funClassifyOrder].funcs.push(aFunc)
        }
      )
      funcGroups = this.Utils.compact(funcGroups) // 存在某个索引为空的情况, 去掉
      this.store.allFuncs = funcGroups
    }, // end of initAllFuncsInfo
    funcSearchTextChange (searchText) {
      this.store.searchResult = this.filterFuncs(searchText)
    },
    filterFuncs (searchText, dataLimit = 50) {
      // suggest && commonSearch都会调用该方法
      let items = []
      let targetText = searchText.trim().toLowerCase()  // 去除首尾空格 将大写字母转换成小写
      // console.log(targetText)
      if (targetText.length === 0) {
        return items
      }
      // 遍历函数
      this.store.allFuncs.forEach(
        (funcGroup, funcGroupIndex, arr) => {
          funcGroup.funcs.forEach(
            (func, funcIndex, arr2) => {
              if (func.funName.toLowerCase().indexOf(targetText) !== -1) {
                let item = {
                  itemType: 'func',
                  itemName: func.funName,
                  itemLabel: func.funName,
                  returnType: func.returnType,
                  itemObj: func
                }
                items.push(item)
              }
            }
          )
        }
      )
      if (items.length >= dataLimit) {
        return this.Utils.slice(items, 0, dataLimit)
      }
      return items
    },
    commonSearchTextChange (searchTxt) {
      this.store.searchResult = this.searchTextChange(searchTxt)
    },
    suggestSearchTextChange (searchTxt) {
      this.store.suggestResult = this.searchTextChange(searchTxt)
    },
    searchTextChange (searchText, dataLimit = 50) {
      // suggest && commonSearch都会调用该方法
      let items = []
      let targetText = searchText.trim().toLowerCase()  // 去除首尾空格 将大写字母转换成小写
      // console.log(targetText)
      if (targetText.length === 0) {
        return items
      }
      // 遍历函数, 全局变量, 局部变量
      this.store.allFuncs.forEach(
        (funcGroup, funcGroupIndex, arr) => {
          funcGroup.funcs.forEach(
            (func, funcIndex, arr2) => {
              if (func.funName.toLowerCase().indexOf(targetText) !== -1) {
                let item = {
                  itemType: 'func',
                  itemName: func.funName,
                  itemLabel: func.funName,
                  returnType: func.returnType,
                  itemObj: func
                }
                items.push(item)
              }
            }
          )
        }
      )
      if (items.length >= dataLimit) {
        return this.Utils.slice(items, 0, dataLimit)
      }

      this.store.sysVars.forEach(
        (aVar, index, arr) => {
          let varName = aVar.vrntVarName || ''
          if (varName.toLowerCase().indexOf(targetText) !== -1) {
            let item = {
              itemType: 'var',
              itemName: aVar.vrntVarName,
              itemLabel: aVar.vrntVarName,
              returnType: aVar.vrntVarType,
              itemObj: aVar
            }
            items.push(item)
          }
        }
      )
      if (items.length >= dataLimit) {
        return this.Utils.slice(items, 0, dataLimit)
      }
      if (this.store.localVars) {
        this.store.localVars.forEach(
          (aVar, index, arr) => {
            let varName = aVar.vrntVarName || ''
            if (varName.toLowerCase().indexOf(targetText) !== -1) {
              let item = {
                isVar: true,
                itemType: 'var',
                itemName: aVar.vrntVarName,
                itemLabel: aVar.vrntVarName,
                returnType: aVar.vrntVarType,
                itemObj: aVar
              }
              items.push(item)
            }
          }
        )
      } // end of if (this.store.localVars) {
      if (items.length >= dataLimit) {
        return this.Utils.slice(items, 0, dataLimit)
      }
      return items
    },
    selectRelClass (relClass) {
      if (this.store.currentSelectedClass === relClass.clssId) {
        return
      }
      this.store.currentSelectedClass = relClass.clssId
      this.store.currentSelectedClassName = relClass.clssAliasName
      this.getPropsbyClassId(relClass.clssClassId)
    },
    getPropsbyClassId (classId) {
      // console.info('getPropsbyClassId' + classId)
      this.store.loadingInfo.isLoadingProp = true
      this.store.curEdmClassProps = []
      return new Promise((resolve, reject) => {
        this.fetchEdmps({
          edmcId: classId
        }).then((resData) => {
          // console.log(resData)
          this.store.curEdmClassProps = resData
          setTimeout(() => {
            this.store.loadingInfo.isLoadingProp = false
          }, 0)
          resolve()
        })
      })
    },
    selectProp (prop) {
      this.$refs.editor.insertProp(prop)
    },
    selectSuggest ({item, delSuggestRange}) {
      if (item.itemType === 'func') {
        this.$refs.editor.inserFunc(item.itemObj, delSuggestRange)
      }
      if (item.itemType === 'var') {
        this.$refs.editor.inserVar(item.itemObj, delSuggestRange)
      }
    },
    selectVar (aVar) {
      this.$refs.editor.inserVar(aVar)
    },
    checkFormulaStrValid () {
      this.$refs.editor.checkFormulaStrValid()
    },
    checkPPIFormulaStrValid () {
      this.$refs.editor.checkPPIFormulaStrValid()
    },
    checkObjectPreFormulaStrValid () {
      this.$refs.editor.checkObjectPreFormulaStrValid()
    },
    showFormulaValidatorDlg () {
      this.$refs.editor.showFormulaValidatorDlg()
    }
  } // end of methods
} // end of mixin
export default mixin
