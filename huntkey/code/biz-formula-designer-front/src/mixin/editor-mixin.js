// import Utils from '../utils/utils'
const mixin = {
  methods: {
    // 把公式项格式化成html字符串
    buildFromulaStrHtml (formulaItems) {
      let strHtml = '   '
      if (!formulaItems || (formulaItems.length === 0)) {
        return ''
      }
      formulaItems.forEach((item, index, arr) => {
        if (item.type === 'str') {
          strHtml += item.val
        } else {
          strHtml += `<div class="formula-item formula-item-${item.type}" data-type="${item.type}" data-value="${item.val}" data-return-type="${item.returnType}" data-label="${item.label}" contenteditable="false">${item.label}</div> `
        }
      })
      return strHtml
    }, // end of buildFromulaStrHtml
    // 获取可编辑区域
    getSelectedRange (editorNode, defaultRange) {
      if (defaultRange) {
        return defaultRange
      } else {
        let selectedRange = document.createRange()
        if (editorNode.childNodes.length === 0) {
          editorNode.appendChild(document.createTextNode(''))
        }
        selectedRange.selectNode(editorNode.lastChild)
        return selectedRange
      }
    },
    // 判断一个元素节点是否是公式项
    isFormulaItem (node) {
      if (node.nodeType === Node.TEXT_NODE) {
        return false
      }
      if (this.WebUtils.hasClass(node, 'formula-item')) {
        return true
      }
      return false
    },
    // fixEnterEvent
    fixEnterEvent (e) {
      // console.log('Enter')
      let newNode = document.createElement('br')
      let nodes = []
      nodes.push(newNode)
      // 去除空文本节点
      // nodes.push(document.createTextNode(''))
      this.insertNodeEditor(nodes, 1)
      // e.preventDefault()
      // e.stopPropagation()
    },
    insertNodeEditor (nodes, caretIndex = 0) {
      if (!nodes || nodes.length === 0) {
        return
      }
      let editorNode = this.$refs.editor
      let selectedRange = this.getSelectedRange(editorNode, this.lastSelectedRange)
      let newSelectRange = document.createRange()
      for (let i = 0; i < nodes.length; i++) {
        selectedRange.insertNode(nodes[i])
        // 换行 start
        let hasChildNodes = selectedRange.commonAncestorContainer.childNodes
        let childsArray = Object.keys(hasChildNodes).map(key => hasChildNodes[key])
        for (let i = 0; i < childsArray.length; i++) {
          if (childsArray[i].nodeName === '#text' && childsArray[i].nodeValue === '') {
            childsArray.splice(i--, 1)
          }
        }
        if (childsArray[childsArray.length - 1].localName === 'br') {
          if (childsArray[childsArray.length - 2] && ((childsArray[childsArray.length - 2].nodeName === '#text' && childsArray[childsArray.length - 3]) || childsArray[childsArray.length - 2].localName === 'div')) {
            let newNode = document.createElement('br')
            let nodes = []
            nodes.push(newNode)
            selectedRange.insertNode(nodes[0])
          }
        }
        // end
        if (caretIndex === i) {
          newSelectRange.selectNode(nodes[i])
          newSelectRange.collapse()
        }
        selectedRange.collapse()
      }
      // console.info(newSelectRange)
      this.WebUtils.selectRange(newSelectRange)
      this.setLastSelectedRange()
      // FIXME
      if (this.parseFormulaHtml) {
        this.parseFormulaHtml()
      }
    },
    // fixBackSpaceEvent
    fixBackSpaceEvent (e) {
      // console.info('fixBackSpaceEvent')
      // 为了处理光标bug, 在formula-item之后添加了空文本区域, 删除时, 要把这些删除掉.
      let currentSelection = this.WebUtils.getSelectedRange()
      if (!currentSelection.collapsed) return // 如果光标没有collapse, 按默认方式处理
      let startEl = currentSelection.startContainer
      let endEl = currentSelection.endContainer
      let endOffset = currentSelection.endOffset
      let editorNode = this.$refs.editor
      if (editorNode.isSameNode(startEl) && editorNode.isSameNode(endEl)) {
        // console.info('....1')
        if (editorNode.hasChildNodes() && (endOffset > 0)) {
          let orgNodes = editorNode.childNodes
          let index = endOffset - 1
          let targetNodes = []
          let aNode = orgNodes[index]
          if (aNode.nodeType === Node.TEXT_NODE) {
            if (aNode.textContent.length > 0) {
              // 遇到有内容的文字节点, 采用默认处理方式.
              return
            }
          }
          this.greedSearchDeletableNode(orgNodes, index, targetNodes)
          if (targetNodes.length > 0) {
            for (let i = 0; i < targetNodes.length; i++) {
              editorNode.removeChild(targetNodes[i])
            }
          }
        }
        e.preventDefault()
        e.stopPropagation()
      }
    },
    // 为了处理contenteditable的光标bug, 加了好多冗余的空文本节点, 当删除时, 需删除这些节点
    greedSearchDeletableNode (orgNodes, index, targetNodes) {
      if (index >= 0 && index < orgNodes.length) {
        let aNode = orgNodes[index]
        if (aNode.nodeType === Node.TEXT_NODE) {
          if (aNode.textContent.trim() === '') {
            targetNodes.push(aNode)
            this.greedSearchDeletableNode(orgNodes, (index - 1), targetNodes)
          }
        } else if (aNode.nodeType === Node.ELEMENT_NODE) {
          targetNodes.push(aNode)
          // if (aNode.nodeName === 'BR') {
          //   // 在contenteditable的div中删除空文本是, 会自动产生一个BR标签, 该标签的产生机制不明确, 遇到就删了吧.
          //   this.greedSearchDeletableNode(orgNodes, (index - 1), targetNodes)
          // }
        }
      }
    }, // end of greedSearchDeletableNode
    insertNode2Editor (nodes, caretIndex = 0) {
      if (!nodes || nodes.length === 0) {
        return
      }
      let editorNode = this.$refs.editor
      let selectedRange = this.getSelectedRange(editorNode, this.lastSelectedRange)
      let newSelectRange = document.createRange()
      for (let i = 0; i < nodes.length; i++) {
        selectedRange.insertNode(nodes[i])
        if (caretIndex === i) {
          newSelectRange.selectNode(nodes[i])
          newSelectRange.collapse()
        }
        selectedRange.collapse()
      }
      // console.info(newSelectRange)
      this.WebUtils.selectRange(newSelectRange)
      this.setLastSelectedRange()
      // FIXME
      if (this.parseFormulaHtml) {
        this.parseFormulaHtml()
      }
    },
    insertBrackets () {
      let nodes = []
      nodes.push(document.createTextNode('( '))
      nodes.push(document.createTextNode(''))
      nodes.push(document.createTextNode(' )'))
      nodes.push(document.createTextNode(''))
      this.insertNode2Editor(nodes, 1)
    },
    setLastSelectedRange () {
      // 限制this.lastSelectedRange始终在editor之内
      // 1. range.container 为 editorNode
      // 2. range.container 为 textNode
      // 如果range跨node了, 暂不处理.
      let currentSelectedRange = this.WebUtils.getSelectedRange()
      // console.log(currentSelectedRange)
      if (!currentSelectedRange) return
      if (!currentSelectedRange.collapsed) return
      let container = currentSelectedRange.startContainer
      let containerParent = container.parentNode
      let editorNode = this.$refs.editor
      // console.info(container) // editorNode.isSameNode(container) ||
      if (editorNode.isSameNode(containerParent)) {
        this.lastSelectedRange = currentSelectedRange
      }

      if (editorNode.isSameNode(container)) {
        // 处理光标bug, 如果选中公式项, 在公式项后面没有元素, 择加一个空元素
        let startOffset = currentSelectedRange.startOffset
        if (startOffset > 0) {
          let startIndex = startOffset - 1
          if (editorNode.childNodes.length > startIndex) {
            let aNode = editorNode.childNodes[startIndex]
            if (this.isFormulaItem(aNode)) {
              let nextSibling = aNode.nextSibling
              if (!nextSibling ||
                nextSibling.nodeType !== Node.TEXT_NODE) {
                let nodes = []
                // 为了解决光标被div边框阻挡的bug. 此处需加一个空文本节点
                nodes.push(document.createTextNode(''))
                this.insertNode2Editor(nodes)
              }
            }
          }
        }
        // 选中editor中的文本区域前后
        this.lastSelectedRange = currentSelectedRange
      }
      this.WebUtils.selectRange(this.getLastSelectedRange())
      // console.info(this.lastSelectedRange)
    },
    getLastSelectedRange () {
      if (this.lastSelectedRange) {
        return this.lastSelectedRange
      }
       // 没有选中时, 默认选中editor中最后的元素
      let editorNode = this.$refs.editor
      let selectedRange = document.createRange()
      if (!editorNode.hasChildNodes) {
        editorNode.appendChild(document.createTextNode(''))
      }
      selectedRange.selectNode(editorNode.lastChild)
      selectedRange.collapse(false)
      this.lastSelectedRange = selectedRange
      return selectedRange
    }
  } // end of methods
} // end of mixin
export default mixin
