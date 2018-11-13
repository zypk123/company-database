<template>
  <!--  -->
  <div class="formula-editor-comp">
    <el-card class="box-card border-box">
      <div class="formula-editor__wrapper" ref="editorWrapper"
       >
        <!--@paste.stop.prevent 粘贴键禁止-->
        <div class="formula-editor formula-display-div" style="margin-top:6px;" ref="editor"
          :contenteditable="pageConfig.contenteditable"
          v-html="initFormulaInfo.formulaStrHtml"
          @input="formulaEditorInputEvent($event)"
          @keyup="formulaEditorKeyupEvent($event)"
          @keydown="formulaEditorKeydownEvent($event)"
          @click="formulaEditorClickEvent($event)"
          @focus="formulaEditorFocusEvent($event)"
          @blur="formulaEditorBlurEvent($event)"
          @compositionstart="formulaEditorCompositionstart($event)"
          @compositionend="formulaEditorCompositionend($event)"
          @paste.stop.prevent
        >
        </div><!-- /.formula-editor -->
        <!--  -->
        <formula-suggestion
          v-if="pageConfig.suggestionVisible"
          :posTop="pageConfig.suggestionTop"
          :posLeft="pageConfig.suggestionLeft"
          :suggestFocusIndex = "pageConfig.suggestFocusIndex"
          :searchResult="store.suggestResult"
          @selectSuggest="selectSuggest"
          >
        </formula-suggestion>
      </div>
      <!-- /.formula-editor__wrapper  -->

      <!-- 常用函数 begin -->
      <div class="fufb-div">
        <el-row>
          <el-col v-for="(str,index) in fixedFreqUsedStrs" :span="3" :key="index"
            class="fun-btn-col">
            <el-button class="fufb-func-btn" @click="inserStr(str)">{{str}}</el-button>
          </el-col>
          <el-col :span="3" class="fun-btn-col">
            <!--括号需单独处理光标位置-->
            <el-button class="fufb-func-btn" @click="insertBrackets">( )</el-button>
          </el-col>
          <el-col v-for="(func,index) in store.freqUsedFuncs" :span="3" :key="index"
            class="fun-btn-col">
            <div @mouseenter="showFuncDesc(func)">
            <el-button class="fufb-func-btn" @click="inserFunc(func)"
              :title="func.funName">{{func.funName}}
            </el-button>
            </div>
          </el-col>
          <el-col v-for="index in dynPageConfig.emptyfreqUsedFuncsCnt" :span="3" :key="index"
            class="fun-btn-col">
            <el-button class="fufb-blank-btn disabled" disabled>常用函数</el-button>
          </el-col>
        </el-row>
      </div>
      <!-- 常用函数 begin -->

      <el-tabs type="card" class="func-tabs fixh" v-model="dynPageConfig.activeFuncsTab" >
        <el-tab-pane v-for="(funcGroup,index) in store.allFuncs" :key="index"
        :label="funcGroup.groupName" :name="funcGroup.groupCode">
          <el-scrollbar
            tag="div"
            class="h100"
            wrap-class="scroll-wrapper"
            view-class="scroll-div"
            >
            <el-row>
                <el-col v-for="(func,index) in funcGroup.funcs" :span="3" :key="index"
                  class="fun-btn-col" >
                  <div @mouseenter="showFuncDesc(func)">
                  <el-button class="fufb-func-btn" :title="func.funName"
                    @click="inserFunc(func)"
                    >
                    {{func.funName}}
                  </el-button>
                  </div>
                </el-col>
            </el-row>
          </el-scrollbar>
        </el-tab-pane>
      </el-tabs>

      <div class="func-desc-div">
        <span class="formula-label">函数说明:</span>
        <el-scrollbar
          tag="div"
          class="formula-showcase-scroll"
          wrap-class="scroll-wrapper"
          view-class="scroll-div"
          >
          <div class="formula-showcase monospace" v-html="pageConfig.funcDescHtml"></div>
        </el-scrollbar>
      </div>
      <!-- /.func-desc-div -->
    </el-card>

  </div>
</template>
<script>
import appMixin from '@/mixin/app-mixin'
import editorMixin from '@/mixin/editor-mixin'
import { formulaService } from '@/api'
import FormulaSuggestion from '../formula-suggestion/FormulaSuggestion'
import FormulaValidator from '../formula-validator/FormulaValidator'
import FormulaValidateUtils from '@/utils/formula-validate'
import PPIFormulaValidateUtils from '@/utils/ppiFormula-validate'
import ObjectPreFormulaValidateUtils from '@/utils/objectPreFormula-validate'
export default {
  props: ['store'],
  mixins: [appMixin, editorMixin],
  data () {
    return {
      pageConfig: {
        contenteditable: true,
        formulaValidatorVisible: false,
        suggestRange: null,
        suggestionQueryStr: '', // 记录用于查询提示信息的QueryStr
        formluaEditorFocus: false,
        suggestionVisible: false,
        suggestionTop: 0,
        suggestionLeft: 0,
        suggestFocusIndex: 0, // 提示框focus到那个元素上.
        isIME: false,
        funcDescHtml: '' // 函数描述的html内容
      },
      lastSelectedRange: null,
      fixedFreqUsedStrs: [
        '+', '-', '*', '/'
      ],
      editedFormulaInfo: {
        formulaId: '',
        label: '',
        returnType: '',
        formulaStr: '',
        formulaItems: [],
        formulaItemsStr: '',
        formulaStrHtml: ''
      },
      funcDescHtml: '' // 函数描述的html内容
    }
  },
  created () {
    // console.log(this.store)
  },
  mounted () {
  },
  methods: {
    inserStr (str) {
      let newNode = document.createTextNode(str)
      let nodes = []
      nodes.push(newNode)
      nodes.push(document.createTextNode(''))
      this.insertNode2Editor(nodes, 1)
    },
    inserFunc (func, delSuggestRange) {
      if (delSuggestRange && this.pageConfig.suggestRange) {
        this.pageConfig.suggestRange.deleteContents()
        this.pageConfig.suggestionVisible = false
      }
      let newNode = document.createElement('div')
      newNode.setAttribute('class', 'formula-item formula-item-func')
      newNode.setAttribute('data-type', 'func')
      newNode.setAttribute('data-value', func.funName)
      newNode.setAttribute('data-return-type', func.returnType)
      newNode.setAttribute('contenteditable', false)
      newNode.appendChild(document.createTextNode(func.funName))

      let nodes = []
      nodes.push(newNode)
      nodes.push(document.createTextNode('')) // 为了解决光标被div边框阻挡的bug. 此处需加一个空文本节点
      this.insertNode2Editor(nodes)
      this.insertBrackets()
      // 统计常用函数使用频率
      formulaService.statFreqUsedFunc({
        funcName: func.funName,
        userId: this.store.userId
      })
    },
    inserVar (aVar, delSuggestRange) {
      if (delSuggestRange && this.pageConfig.suggestRange) {
        this.pageConfig.suggestRange.deleteContents()
        this.pageConfig.suggestionVisible = false
      }
      let newNode = document.createElement('div')
      newNode.setAttribute('class', 'formula-item formula-item-var')
      newNode.setAttribute('data-type', 'var')
      newNode.setAttribute('data-value', aVar.vrntVarName)
      newNode.setAttribute('data-return-type', aVar.vrntVarType)
      newNode.setAttribute('contenteditable', false)
      newNode.appendChild(document.createTextNode(aVar.vrntVarName))
      let nodes = []
      nodes.push(newNode)
      nodes.push(document.createTextNode('')) // 为了解决光标被div边框阻挡的bug. 此处需加一个空文本节点
      this.insertNode2Editor(nodes, 1)
    },
    insertProp (prop) {
      // 禁止属性集参与运算
      if (prop.edmpValueType === 'assemble') {
        return
      }
      let className = this.store.currentSelectedClassName
      let classId = this.store.currentSelectedClass
      let propValue = `${classId}_${prop.id}_${prop.casEdmpCode}`
      let propLabel = `${className}.${prop.casEdmpName}`

      let newNode = document.createElement('div')
      newNode.setAttribute('class', 'formula-item formula-item-prop')
      newNode.setAttribute('contenteditable', false)
      newNode.setAttribute('data-type', 'prop')
      newNode.setAttribute('data-value', propValue)
      newNode.setAttribute('data-label', propLabel)
      newNode.setAttribute('data-return-type', prop.edmpValueType)
      newNode.appendChild(document.createTextNode(propLabel))
      let nodes = []
      nodes.push(newNode)
      nodes.push(document.createTextNode('')) // 为了解决光标被div边框阻挡的bug. 此处需加一个空文本节点
      this.insertNode2Editor(nodes, 1)
    },
    formulaEditorInputEvent (e) {
      // update
      this.setLastSelectedRange()
      this.showSuggestionQuery(e)
    },
    formulaEditorKeyupEvent (e) {
//      console.log('keyup')
      if (this.pageConfig.isIME) {
        // chrome下 中文输入法导致页面直接崩溃
        e.stopPropagation()
        e.preventDefault()
        return false
      }
      if (this.pageConfig.suggestionVisible) {
        // 如果弹出了建议, 则上下键和回车用来操作弹出提示框.
        if (e.key === 'ArrowUp') {
          this.focusPrevSuggest()
          e.preventDefault()
          e.stopPropagation()
          return
        }
        if (e.key === 'ArrowDown') {
          this.focusNextSuggest()
          e.preventDefault()
          e.stopPropagation()
          return
        }
        if (e.key === 'Enter') {
          this.selectFocusSuggest()
          e.preventDefault()
          e.stopPropagation()
          return
        }
      }
      if (e.key === 'Backspace') {
        // 处理退格导致的光标Bug
        // console.info('fixBackSpaceEvent')
        this.fixBackSpaceEvent(e)
      }
      this.setLastSelectedRange()
    },
    formulaEditorKeydownEvent (e) {
      /** 添加换行功能 **/
//      console.log('keydown', e)
      if (e.key === 'Enter') {
        this.fixEnterEvent(e)
        e.preventDefault()
        e.stopPropagation()
        return
      }
    },
    formulaEditorClickEvent (e) {
      // console.info('formulaEditorClickEvent')
      let el = e.target
      if (this.WebUtils.hasClass(el, 'formula-item')) {
        // 当点击公式项时, 如果点击在前半部分, 则选中该公式项之前, 如果点击后半部分, 则选中该公式项之后.
        let rect = e.target.getBoundingClientRect()
        let mid = (rect.left + rect.right) / 2
        if (e.screenX >= mid) {
          let nextSibling = el.nextSibling
          if (nextSibling && nextSibling.nodeType === Node.TEXT_NODE) {
            let range = document.createRange()
            range.selectNode(nextSibling)
            range.collapse(true)
            this.lastSelectedRange = range
          }
        } else {
          let prevSibling = el.previousSibling
          if (prevSibling && prevSibling.nodeType === Node.TEXT_NODE) {
            let range = document.createRange()
            range.selectNode(prevSibling)
            range.collapse()
            this.lastSelectedRange = range
          }
        }
        e.stopPropagation()
      }
      this.setLastSelectedRange()
      this.showSuggestionQuery(e)
    },
    formulaEditorFocusEvent (e) {
      this.pageConfig.formluaEditorFocus = true
    },
    formulaEditorBlurEvent (e) {
      this.pageConfig.formluaEditorFocus = false
      setTimeout(
        () => { this.pageConfig.suggestionVisible = false }
      , 500)
    },
    formulaEditorCompositionstart (e) {
      // true改为false
      this.pageConfig.isIME = true
      this.pageConfig.suggestionVisible = false
    },
    formulaEditorCompositionend (e) {
      // false改为true
      this.pageConfig.isIME = false
    },
    showSuggestionQuery (e) {
      // console.info('this.store.suggestResult')
      // 当输入,或者修改内容后, 获取当前文本节点,从光标开始往前退,到头或者到空格,运算符,括号为止, 这部分内容
      // 获取当前光标的位置
      if (!this.pageConfig.formluaEditorFocus) {
        return
      }
      this.pageConfig.suggestionVisible = false
      this.pageConfig.suggestRange = null

      if (!this.lastSelectedRange) return
      let newRange = this.lastSelectedRange.cloneRange()
      if (!newRange.collapsed) return
      if (!(newRange.endContainer.nodeType === 3)) return // 校验当前节点类型是否为文本

      let s = this.lastSelectedRange.endContainer.textContent
      let endOffset = this.lastSelectedRange.endOffset
      let queryStrCharArr = []
      let newStartOffset = 0
      for (let i = endOffset - 1; i >= 0; i--) {
        let c = s.charAt(i)
        if (/\s|,|\+|-|\*|\/|\(|\)/.test(c)) break
        newStartOffset = i
        queryStrCharArr.push(s.charAt(i))
      }

      if (queryStrCharArr.length === 0) {
        return
      }

      newRange.setStart(newRange.endContainer, newStartOffset)
      this.pageConfig.suggestRange = newRange
      this.pageConfig.suggestionQueryStr = queryStrCharArr.reverse().join('')

      let parentElement = this.$refs.editorWrapper
      let parentRange = document.createRange()
      parentRange.selectNode(parentElement)
      let parentRect = parentRange.getBoundingClientRect()
      let rect = newRange.getBoundingClientRect()
      // 10px:formula-editor.margin-top
      this.pageConfig.suggestionTop = rect.bottom - parentRect.top
      this.pageConfig.suggestionLeft = rect.left - parentRect.left
      this.$emit('searchTextChange', this.pageConfig.suggestionQueryStr)
      this.pageConfig.suggestFocusIndex = 0
      this.pageConfig.suggestionVisible = true
    },
    focusPrevSuggest () {
      if (this.store.suggestResult.length > 0 &&
        this.pageConfig.suggestFocusIndex > 0) {
        this.pageConfig.suggestFocusIndex = this.pageConfig.suggestFocusIndex - 1
      }
      //      按↑键滚动条滚动
      this.suggestCountScroll()
    },
    //    按↑↓键与滚动条关联
    suggestCountScroll () {
      let parentElement = this.$refs.editorWrapper
      let parentChildNodeUl = parentElement.getElementsByTagName('ul')  // 获取ul节点
      let ulParentNode = parentChildNodeUl[0].parentNode // 获取ul的父级节点
      let ulParentNodeHeight = ulParentNode.offsetHeight  // 获取ul父级节点的高度(overflow设置的高度)
      let childNodeUlHeight = parentChildNodeUl[0].clientHeight  // 获取ul节点的实际高度
      if (childNodeUlHeight > ulParentNodeHeight) {
        let focusOffsetHeight = parentChildNodeUl[0].childNodes[this.pageConfig.suggestFocusIndex]
        ulParentNode.scrollTop = focusOffsetHeight.offsetTop
      }
    },
    focusNextSuggest () {
      if (this.store.suggestResult.length > 0 &&
        this.pageConfig.suggestFocusIndex < (this.store.suggestResult.length - 1)) {
        this.pageConfig.suggestFocusIndex = this.pageConfig.suggestFocusIndex + 1
      }
      //      按↓键滚动条滚动
      this.suggestCountScroll()
    },
    selectFocusSuggest () {
      if (this.store.suggestResult.length > 0 &&
        this.pageConfig.suggestFocusIndex < this.store.suggestResult.length) {
        this.selectSuggest(this.store.suggestResult[this.pageConfig.suggestFocusIndex])
      }
    },
    selectSuggest (item) {
      let delSuggestRange = true
      this.$emit('selectSuggest', {item, delSuggestRange})
    },
    checkFormulaStrValid () {
      // FIXME 校验方法开发中....
      this.isFormulaStrValid().then(() => {
        this.AppUtils.showSuccess('校验通过！')
      }).catch((error) => {
        console.log(error)
        this.AppUtils.showErrorMsg(error)
      })
    },
    isFormulaStrValid () {
      return new Promise((resolve, reject) => {
        this.buildFormulaInfo()
        if (this.editedFormulaInfo.formulaStr.length === 0) {
          reject({message: '公式内容不能为空.'})
          return
        }
        let checkFromulaItems = this.Utils.cloneDeep(this.editedFormulaInfo.formulaItems)
        // 小陆在校验的时候改了formulaItems, 所以clone一份给他校验
        let ret = FormulaValidateUtils.ananalyticFormula(checkFromulaItems, this.store.initFuncs, this.store.formula.returnType, this.store.formula.label)
        if (ret.retCode === '-1') {
          this.AppUtils.showWarning(ret.retMsg)
          reject()
          return
        }

        let formulaInfo = {
          frmuId: this.initFormulaInfo.formulaId,
          frmuFormulaText: this.editedFormulaInfo.formulaText,
          frmuFormulaContent: this.editedFormulaInfo.formulaStr,
          frmuFormulaStyle: this.editedFormulaInfo.formulaItemsStr
        }
        resolve(formulaInfo)
      })
    },
    clearPPIFormulaInfo () {
    },
    checkPPIFormulaStrValid () {
      // FIXME 校验方法开发中....
      this.isPPIFormulaStrValid().then(() => {
        this.AppUtils.showSuccess('校验通过！')
      }).catch((error) => {
        console.log(error)
        this.AppUtils.showErrorMsg(error)
      })
    },
    isPPIFormulaStrValid () {
      return new Promise((resolve, reject) => {
        this.buildFormulaInfo()
        if (this.editedFormulaInfo.formulaStr.length === 0) {
          reject({message: '公式内容不能为空.'})
          return
        }
        // 加上函数语法校验
        let checkFromulaItems = this.Utils.cloneDeep(this.editedFormulaInfo.formulaItems)
        // PPI公式语法校验只要校验函数的合法性 并不要校验公式返回类型是否与PPI名称的类型是否一致
        console.log(this.store.ppiName)
        let ret = PPIFormulaValidateUtils.ananalyticFormula(checkFromulaItems, this.store.initFuncs, this.store.formula.returnType, this.store.ppiName)
        if (ret.retCode === '-1') {
          this.AppUtils.showWarning(ret.retMsg)
          reject()
          return
        }

        let formulaInfo = {
          frmuId: this.initFormulaInfo.formulaId,
          frmuFormulaText: this.editedFormulaInfo.formulaText,
          frmuFormulaContent: this.editedFormulaInfo.formulaStr,
          frmuFormulaStyle: this.editedFormulaInfo.formulaItemsStr
        }
        resolve(formulaInfo)
      })
    },
    checkObjectPreFormulaStrValid () {
      this.isObjectPreFormulaStrValid().then(() => {
        this.AppUtils.showSuccess('校验通过！')
      }).catch((error) => {
        console.log(error)
        this.AppUtils.showErrorMsg(error)
      })
    },
    isObjectPreFormulaStrValid () {
      return new Promise((resolve, reject) => {
        this.buildFormulaInfo()
        if (this.editedFormulaInfo.formulaStr.length === 0) {
          reject({message: '公式内容不能为空.'})
          return
        }
        // 加上函数语法校验
        let checkFromulaItems = this.Utils.cloneDeep(this.editedFormulaInfo.formulaItems)
        // 对象呈现公式语法校验只要校验函数的合法性 并不要校验公式返回类型是否一致
        let ret = ObjectPreFormulaValidateUtils.ananalyticFormula(checkFromulaItems, this.store.initFuncs, this.store.formula.returnType, this.store.formula.label)
        if (ret.retCode === '-1') {
          this.AppUtils.showWarning(ret.retMsg)
          reject()
          return
        }

        let formulaInfo = {
          frmuId: this.initFormulaInfo.formulaId,
          frmuFormulaText: this.editedFormulaInfo.formulaText,
          frmuFormulaContent: this.editedFormulaInfo.formulaStr,
          frmuFormulaStyle: this.editedFormulaInfo.formulaItemsStr
        }
        resolve(formulaInfo)
      })
    },
    buildFormulaInfo () {
      let editorNode = this.$refs.editor
      let formulaStr = ''
      let formulaText = ''
      let formulaItems = []
      editorNode.childNodes.forEach(
        (node, nodeIndex, arr) => {
          let formulaItem = {}
          // console.info(node)
          if (node.nodeType === Node.TEXT_NODE) {
            let txt = node.nodeValue.trim()
            formulaStr += txt
            formulaText += txt
            formulaItem = {type: 'str', val: txt}
            formulaItems.push(formulaItem)
          }
          if (node.nodeType === Node.ELEMENT_NODE &&
            node.nodeName === 'DIV') {
            if (node.dataset.type === 'func') {
              formulaStr += node.dataset.value
              formulaText += node.dataset.value
              formulaItem = {
                type: 'func',
                val: node.dataset.value,
                label: node.dataset.value,
                returnType: node.dataset.returnType
              }
              formulaItems.push(formulaItem)
            }
            if (node.dataset.type === 'var') {
              // formulaStr += node.dataset.value
              formulaStr += `#var_${node.dataset.value}#`
              formulaText += node.dataset.value
              formulaItem = {
                type: 'var',
                val: node.dataset.value,
                label: node.dataset.value,
                returnType: node.dataset.returnType
              }
              // console.info('add_var')
              // console.info(formulaItem)
              formulaItems.push(formulaItem)
            }
            if (node.dataset.type === 'prop') {
              formulaStr += `#prop_${node.dataset.value}#`
              formulaText += node.dataset.label
              formulaItem = {
                type: 'prop',
                val: node.dataset.value,
                label: node.dataset.label,
                returnType: node.dataset.returnType
              }
              // console.info('add_prop')
              // console.info(formulaItem)
              formulaItems.push(formulaItem)
            }
          }
        }
      )
      formulaStr = formulaStr.trim()
      this.editedFormulaInfo.formulaId = this.store.formula.formulaId
      this.editedFormulaInfo.label = this.store.formula.label
      this.editedFormulaInfo.returnType = this.store.formula.returnType
      this.editedFormulaInfo.formulaStr = formulaStr
      this.editedFormulaInfo.formulaText = formulaText
      this.editedFormulaInfo.formulaItems = formulaItems
      this.store.formula.formulaItems = formulaItems
      this.editedFormulaInfo.formulaItemsStr = JSON.stringify(formulaItems)
      this.editedFormulaInfo.formulaStrHtml = this.buildFromulaStrHtml(formulaItems)
      // console.info(this.editedFormulaInfo)
      // console.info(JSON.stringify(this.editedFormulaInfo))
    },
    showFormulaValidatorDlg () {
      this.isFormulaStrValid().then(() => {
        this.OpenGlobalDialog({
          name: 'formulaValidatorDlg',
          component: FormulaValidator,
          props: {
            formula: this.editedFormulaInfo
          },
          options: {
            title: '校验公式',
            top: '20%',
            customClass: 'subpage-dlg dialog-width-xxl'
          },
          events: {
            close: () => {
              this.CloseGlobalDialog('formulaValidatorDlg')
            }
          }
        })
      }).catch((error) => {
        console.log(error)
        this.AppUtils.showErrorMsg(error)
      })
    },
    showFuncDesc (func) {
      if (func.funDesc) {
        this.pageConfig.funcDescHtml = func.funDesc.replace(/\n/g, '<br>')
      } else {
        this.pageConfig.funcDescHtml = ''
      }
    }
  },
  computed: {
    dynPageConfig () {
      let config = {
        emptyfreqUsedFuncsCnt: 11,
        activeFuncsTab: 'math'
      }
      // 两排共计16的按钮位, 固定符号5个. fuf从数据库取, 剩下的常用函数填充
      let usedCount = this.store.freqUsedFuncs.length
      if (usedCount >= 11) {
        config.emptyfreqUsedFuncsCnt = 0
      } else {
        config.emptyfreqUsedFuncsCnt = 11 - usedCount
      }
      // console.info(config)
      return config
    },
    initFormulaInfo () {
      let info = {
        formulaId: this.store.formula.formulaId,
        returnType: this.store.formula.returnType,
        label: this.store.formula.label,
        formulaStr: this.store.formula.formulaStr,
        formulaItems: this.store.formula.formulaItems,
        formulaStrHtml: this.buildFromulaStrHtml(this.store.formula.formulaItems)
      }
      return info
    }
  },
  watch: {
    'store.allFuncs' () {
      if (this.store &&
          this.store.allFuncs &&
          this.store.allFuncs[0] &&
          this.pageConfig.funcDescHtml === '') {
        this.showFuncDesc(this.store.allFuncs[0].funcs[0])
      }
    },
    'store.suggestResult' () {
      if (this.store.suggestResult.length === 0 || this.pageConfig.isIME) {
        this.pageConfig.suggestionVisible = false
      }
    }
  },
  components: {
    FormulaSuggestion,
    FormulaValidator
  }
}
</script>
<style scoped>
.formula-editor-comp {
  box-sizing: border-box;
}

.formula-editor-comp .el-card {
  position: relative;
  border-style:none;
  overflow: auto;
  background-color:#F1F3FA;
  box-shadow: none;
}

.formula-editor-comp .el-tabs,
.formula-showcase,
.formula-editor {
  background-color: #fff;
}

/** .formula-editor__wrapper begin **/
.formula-editor__wrapper {
  position: relative;
  box-sizing:border-box;
  height: calc(50% - 160px);
  /*border:1px solid red;*/
}

.formula-editor__wrapper .formula-editor {
  box-sizing:border-box;
  height:100%;
}

/** .formula-editor__wrapper end **/

/** .frequently used func buttons begin **/
.fufb-func-btn,
.fufb-blank-btn {
  margin-top: 9px;
/*  margin-left: 8px;
  margin-right: 8px;*/
  width: 100%;
  min-width: 75px;
  height: 30px;
  padding:0;
  color:#000;
  border-color: #D7DAE1;
  text-overflow: ellipsis;
  /*font-size: 9px;*/
/*  white-space: normal;
  word-wrap: break-word;
  word-break: break-all;*/
  overflow: hidden;
}

.min-editor .fufb-func-btn,
.min-editor .fufb-blank-btn {
  min-width: 58px;
}

.fun-btn-col {
  padding-left: 4px;
  padding-right: 4px;
}

.fufb-div {
  height:86px;
  margin-top:7px;
}

.fufb-div .fun-btn-col:nth-child(8n){
  padding-right: 8px;
}
.fufb-div .fun-btn-col:nth-child(8n+1){
  padding-left: 8px;
}


/** .frequently used func buttons end **/


/** .func-tabs begin **/
.func-tabs {
  /*border:1px solid yellow;*/
  height:198px;
}
.func-tabs .el-row {
  width: calc(100% - 20px);
}

.func-tabs .fun-btn-col:nth-child(8n){
  padding-right: 7px;
}
.func-tabs .fun-btn-col:nth-child(8n+1){
  padding-left: 7px;
}

/** .func-tabs end **/

.func-desc-div {
  position: absolute;
  /*border:1px solid blue;*/
  width: 100%;
  top: calc(50% + 150px);
  bottom: 0;
}
.func-desc-div .formula-label {
  /*height: 34px;*/
  margin:0;
  padding-top:16px;
  /*border:1px solid blue;*/
  padding-bottom: 8px;
}
.formula-showcase-scroll {
  position: absolute;
  box-sizing:border-box;
  width: 100%;
  top: 41px;
  bottom: 0;
}
.formula-showcase-scroll-wrapper {
  height: 100%;
}
.formula-showcase {
  height: 100%;
  /*border:1px solid red;*/
}

/*
 * 整个页面的高度:
 *  formula-label-div: 35px
 *  common-vgap: 12px;
 *  formula-editor__wrapper calc(50% - 160px);
 *  fufb-div: 86px;
 *  func-tabs: 150px;
 *  func-desc-div: top: calc(100%-50% + 160-35-12-86-150);
 *
 */


</style>
