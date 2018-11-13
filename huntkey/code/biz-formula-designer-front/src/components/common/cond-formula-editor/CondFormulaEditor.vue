<template>
  <!-- 条件公式编辑器 -->
  <div class="cond-formula-editor">
    <el-row>
      <el-col class="common-vgap" :span="24">
        <span>条件公式:</span>
        <el-button type="primary" @click="insertLogicOper('AND')" :disabled="!formulaEditable">并且 AND</el-button>
        <el-button type="primary" @click="insertLogicOper('OR')" :disabled="!formulaEditable">或 OR</el-button>
        <el-button type="primary" @click="insertBrackets" :disabled="!formulaEditable">括号 ()</el-button>
        <el-button type="primary" @click="clearFormula" :disabled="!formulaEditable">清除公式</el-button>
      </el-col><!-- /.el-col-24-->



      <el-col class="common-vgap" :span="24">
         <el-form label-position="top" ref="form" :model="formulaInfo" :rules="formulaInfoRules">
          <el-form-item prop="formulaText">
            <div class="formula-display-div formula-editor" style=""
               :contenteditable="false" ref="editor"
               @click="conditionEditorClickEvent($event)"
               @input="conditionEditorInputEvent($event)"
               @keydown="conditionEditorKeydownEvent($event)"
               @compositionstart="conditionEditorCompositionstart($event)"
               @compositionend="conditionEditorCompositionend($event)"
               @paste.stop.prevent
               v-html="formulaHtml"
            >
            </div><!-- /.condition-editor -->
          </el-form-item>

          <el-form-item label="条件名称:" prop="formulaName">
           <el-input v-model="formulaInfo.formulaName" :disabled="!formulaEditable"></el-input>
          </el-form-item>

          <el-form-item label="条件描述:">
            <div class="formula-display-div" style="" v-html="formulaDescHtml"></div>
          </el-form-item>
        </el-form>
      </el-col><!-- /.el-col-24-->
    </el-row>
  </div><!-- /.cond-formula-editor-->
</template>
<script>
import editorMixin from '@/mixin/editor-mixin'
export default {
  name: 'condFormulaEditor',
  mixins: [editorMixin],
  props: ['editable', 'initFormulaName', 'initFormulaText', 'condsDescHtmlArr', 'condsDescTextArr'],
  data () {
    return {
      formulaInfo: {
        formulaText: '',
        formulaName: ''
      },
      pageConfig: {
        isIME: false
      },
      lastSelectedRange: null,
      formulaInfoRules: {
        // TODO 公式内容的校验规则待定
        formulaText: [
         {required: true, message: '公式内容不能为空', trigger: 'change'}
        ]
        // formulaName: [
        //   {required: true, message: '条件名称不能为空', trigger: 'change'}
        // ]
      },
      formulaHtml: ' ',
      operations: {
        AND: '并且',
        OR: '或者'
      }
    } // end of return
  }, // end or data
  created () {
    // console.info(this.initFormulaName)
    // console.info(this.initFormulaText)
    this.formulaInfo.formulaName = this.initFormulaName || ''
    this.formulaHtml = this.initFormulaText || ''
  },
  mounted () {
    this.parseFormulaHtml()
  },
  methods: {
    conditionEditorInputEvent (e) {
      // console.info('=========>输入事件<<<<-conditionEditorInputEvent->>>>执行')
      this.setLastSelectedRange()
      this.parseFormulaHtml()
    },
    conditionEditorKeydownEvent (e) {
      // console.info('=========>键盘输入事件<<<<-conditionEditorKeydownEvent->>>>执行')
      // if (this.pageConfig.isIME) {
      //   // chrome下 中文输入法导致页面直接崩溃
      //   e.stopPropagation()
      //   e.preventDefault()
      //   return false
      // }
      if (e.key === 'Enter') {
        e.preventDefault()
        e.stopPropagation()
        return
      }
      if (e.key === 'Backspace') {
        // 处理退格导致的光标Bug
        this.fixBackSpaceEvent(e)
      }
      this.setLastSelectedRange()
    },
    conditionEditorClickEvent (e) {
      this.setLastSelectedRange()
    },
    conditionEditorCompositionstart (e) {
      this.pageConfig.isIME = true
    },
    conditionEditorCompositionend (e) {
      this.pageConfig.isIME = false
    },
    clearFormula () {
      this.$refs.editor.innerHTML = ' '
      this.parseFormulaHtml()
    },
    insertSeq (seq) {
      let newNode = document.createElement('div')
      newNode.setAttribute('class', 'formula-item formula-item-seq')
      newNode.setAttribute('contenteditable', false)
      newNode.appendChild(document.createTextNode('[' + seq + ']'))

      let nodes = []
      nodes.push(newNode)
      this.insertNode2Editor(nodes)
    },
    insertLogicOper (operName) {
      let newNode = document.createElement('div')
      newNode.setAttribute('class', 'formula-item formula-item-logic-oper')
      newNode.setAttribute('contenteditable', false)
      newNode.appendChild(document.createTextNode(operName))

      let nodes = []
      nodes.push(newNode)
      nodes.push(document.createTextNode('')) // 为了解决光标被div边框阻挡的bug. 此处需加一个空文本节点
      this.insertNode2Editor(nodes)
    },
    /**
     * 当条件公式发生变化时（插入或者写入，清空），
     * 解析条件公式文本框中的HTML，转化成formulaText字符串
     */
    splitFormulaText (formulaText) {
      let arrReturn = []
      if (formulaText !== '') {
        let arr = formulaText.split('')
        let arrIndex = []
        for (let i = 0; i < arr.length; i++) {
          if (arr[i] === '[' || arr[i] === ']') {
            arrIndex.push(i)
          }
        }
        if (arrIndex.length === 0) {
          arrReturn.push(formulaText)
        } else {
          arrReturn.push(formulaText.substring(0, arrIndex[0] + 1))
          for (let j = 0; j < arrIndex.length; j++) {
            if (j === arrIndex.length - 1) {
              arrReturn.push(formulaText.substring(arrIndex[j]))
            } else {
              if (j % 2 === 0) {
                arrReturn.push(formulaText.substring(arrIndex[j] + 1, arrIndex[j + 1]))
              } else {
                arrReturn.push(formulaText.substring(arrIndex[j], arrIndex[j + 1] + 1))
              }
            }
          }
        }
      }
      return arrReturn
    },
    parseFormulaHtml () {
      // console.info('==parseFormulaHtml begin ==')
      let ftext = ''
      let parentNode = this.$refs.editor
      if (!parentNode.childNodes || parentNode.childNodes.length === 0) {
        this.formulaInfo.formulaText = ''
        return
      }
      for (let i = 0; i < parentNode.childNodes.length; i++) {
        let nodetemp = parentNode.childNodes[i]
        if (nodetemp.textContent.trim() !== '') {
          ftext += (nodetemp.textContent.trim() + ' ')
        }
      }
      this.formulaInfo.formulaText = ftext.trim()
      // console.info(this.formulaInfo.formulaText)
      // console.info('==pparseFormulaHtml end ==p')
    },
    valid () {
      return new Promise((resolve, reject) => {
        // 1. 校验条件名称
        // 2. 校验公式是否合法 TODO
        this.$refs.form.validate((valid) => {
          if (valid) {
            let newCondFormulaInfo = {
              formulaName: this.formulaInfo.formulaName,
              formulaText: this.formulaInfo.formulaText,
              formulaDesc: this.formulaDescText
            }
            resolve(newCondFormulaInfo)
          } else {
            console.info(this.formulaInfo)
            reject('cond formula is not valid')
          }
        })// end of this.$refs.form.validate
      })// end of new Promise
    }
  },
  computed: {
    formulaEditable () {
      return this.editable
    },
    formulaDescHtml () {
      // console.info('formulaDescHtml...')
      // console.info(this.formulaInfo.formulaText)
      let tempHtml = this.formulaInfo.formulaText
      if (tempHtml && tempHtml !== '') {
        let array = this.splitFormulaText(tempHtml)
        tempHtml = array.map((value, index, arr) => {
          if (!isNaN(parseInt(value, 10))) {
            if (parseInt(value, 10) - 1 < this.condsDescHtmlArr.length) {
              return this.condsDescHtmlArr[parseInt(value, 10) - 1]
            }
          }
          return value
        }).join('').trim()
        tempHtml = tempHtml.replace(/\[/g, '')
          .replace(/\]/g, '')
          .replace(/AND/g, `<div class="formula-item formula-item-logic-oper" contenteditable="false">${this.operations.AND}</div>`)
          .replace(/OR/g, `<div class="formula-item formula-item-logic-oper" contenteditable="false">${this.operations.OR}</div>`)
      }
      return tempHtml
    },
    formulaDescText () {
      // console.info('form...')
      // console.info(this.initConditionsTableData)
      let tempHtml = this.formulaInfo.formulaText
      if (tempHtml && tempHtml !== '') {
        let array = this.splitFormulaText(tempHtml)
        tempHtml = array.map((value, index, arr) => {
          if (!isNaN(parseInt(value, 10))) {
            if (parseInt(value, 10) - 1 < this.condsDescTextArr.length) {
              return this.condsDescTextArr[parseInt(value, 10) - 1]
            }
          }
          return value
        }).join('').trim()
        tempHtml = tempHtml.replace(/\[/g, '')
          .replace(/\]/g, '')
          .replace(/AND/g, `${this.operations.AND}`)
          .replace(/OR/g, `${this.operations.OR}`)
      }
      return tempHtml
    }
  }
  // ,
  // watch: {
  //   initFormulaText: () => {
  //     console.info('initFormulaText.watch')
  //     if (this.initFormulaText === '') {
  //       this.$refs.editor.innerHTML = ' '
  //     }
  //   }
  // }
}
</script>
<style>
.cond-formula-editor {
  width: 100%;
}
</style>
