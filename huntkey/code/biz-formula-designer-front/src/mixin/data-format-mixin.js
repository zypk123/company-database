import Utils from '../utils/utils'
const mixin = {
  methods: {
    tableDateFormat (row, column) {
      var date = row[column.property]
      if (date === undefined) {
        return ''
      }
      return Utils.formatDate(date, 'YYYY-MM-DD')
    },
    tableVarStatusFormat (row, column) {
      let statuses = this.$store.getters.getVarStatusDict
      let val = row[column.property]
      if (val === undefined) {
        return ''
      }
      for (let status in statuses) {
        if (statuses[status].val === val) {
          return statuses[status].label
        }
      }
      return ''
    },
    tableDataTypeFormat (row, column) {
      let statuses = this.$store.getters.getDataTypeDict
      let val = row[column.property]
      if (val === undefined) {
        return ''
      }
      for (let status in statuses) {
        if (statuses[status].val === val) {
          return statuses[status].label
        }
      }
      return val
    },
    tableFunStatusFormat (row, column) {
      let statuses = this.$store.getters.getFunStatusDict
      let val = row[column.property]
      if (val === undefined) {
        return ''
      }
      for (let status in statuses) {
        if (statuses[status].val === val) {
          return statuses[status].label
        }
      }
      return ''
    },
    tableFunCatagoryFormat (row, column) {
      let statuses = this.$store.getters.getFunCatagoryDict
      let val = row[column.property]
      if (val === undefined) {
        return ''
      }
      for (let status in statuses) {
        if (statuses[status].val === val) {
          return statuses[status].label
        }
      }
      return ''
    },
    tableFnccStateFormat (row, column) {
      let statuses = this.$store.getters.getFnccStateDict
      let val = row[column.property]
      if (val === undefined) {
        return ''
      }
      for (let status in statuses) {
        if (statuses[status].val === val) {
          return statuses[status].label
        }
      }
      return ''
    },
    optionFormat (row, column) {
      var option = row[column.property]
      if (option === 'Option') {
        return '是'
      } else {
        return '否'
      }
    }
  }
}
export default mixin
