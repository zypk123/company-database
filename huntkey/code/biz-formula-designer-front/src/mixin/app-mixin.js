// import Utils from '../utils/utils'
const mixin = {
  methods: {
    parseEdmpDataValueType (prop) {
      if (prop && prop.edmpValueType) {
        return (prop.edmpValueType === 'normalObj' ? (prop.edmpDataType || 'undefined') : prop.edmpValueType)
      }
      return 'unknow'
    }, // end of parseEdmpDataValueType
    formatDataType: function (value) {
      if (!value) return ''
      let statuses = this.$store.getters.getDataTypeDict
      let val = value.toString().toLowerCase()
      for (let status in statuses) {
        if (statuses[status].val === val) {
          return statuses[status].label
        }
      }
      return val
    }, // end of formatDataType
    // 级联格式化设置属性的标签和字段类型
    recursivlySetPropLabel (props, parent, classId, casEdmpCode) {
      for (let i = 0; i < props.length; i++) {
        props[i].rootClassId = classId
        props[i].edmpValueType = this.parseEdmpDataValueType(props[i])
        if (parent) {
          props[i].parentAlias = parent.propAlias
          props[i].propAlias = props[i].parentAlias + '.' + props[i].edmpName
          props[i].parentCasEdmpCode = parent.casEdmpCode
          props[i].casEdmpCode = props[i].parentCasEdmpCode + '.' + props[i].edmpCode
        }
        if (props[i].children && props[i].children.length > 0) {
          this.recursivlySetPropLabel(props[i].children, props[i], classId, casEdmpCode)
        }
      }
    }, // end of recursivlySetPropLabel
    parseFormulaItems (formulaItemsStr) {
      // 把公式内容字符串解析为数组, 当前这个字段的存储名称为 'frmuFormulaStyle'
      let tempItems = []
      try {
        tempItems = JSON.parse(formulaItemsStr) || [] // 为什么会有null... FIX后台
      } catch (e) {
      }
      return tempItems
    }
  } // end of methods
} // end of mixin
export default mixin
