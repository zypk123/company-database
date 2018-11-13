<template>
  <div class="wapper">
    <el-input 
      :placeholder="placeholder ? placeholder : '请选择对象'" 
      icon="plus" 
      v-model="currentView"
      :disabled="isDisabled"
      readonly
      @focus="openChooseDialog"
      @click="openChooseDialog"></el-input>
  </div>
</template>
<script>
import ObjectDialog from './ObjectDialog'
import { mapGetters } from 'vuex'
import { classTypes } from '@/types'

export default {
  /**
   * 输入参数
   * value 值信息
   * placeholder 提示文字信息
   * rootId 类根节点ID
   * classId 直接定义到类ID 此属性不为空时，rootId配置无效
   * disabled 是否禁用
   */
  props: ['value', 'placeholder', 'dialogTitle', 'rootClass', 'classShortName', 'disabled'],
  name: 'object-selecter',
  created () {
    this.isDisabled = this.disabled !== false && this.disabled !== null && this.disabled !== undefined
    if (this.classShortName) {
      // classShortName不为空时，直接获取classId
      this.classId = findTreeByRootName(this.classShortName, this.allClassTreeData).id
    } else if (this.rootClass) {
      // rootClass不为空时，以此为根节点展示
      this.classTreeData = [findTreeByRootName(this.rootClass, this.allClassTreeData)]
    } else {
      this.classTreeData = this.allClassTreeData
    }

    function findTreeByRootName (shortName, list) {
      for (let index in list) {
        const item = list[index]
        if (item.edmcShortName === shortName) {
          return item
        }
        if (item.children && item.children.length > 0) {
          const result = findTreeByRootName(shortName, item.children)
          if (result) {
            return result
          }
        }
      }
    }
  },
  computed: {
    ...mapGetters({
      allClassTreeData: classTypes.GET_CURRENT_CLASS_TREE_DATA
    })
  },
  data () {
    return {
      // 当前值
      currentValue: null,
      // 选中值
      currentView: null,
      // 树组件的vm对象
      treeVm: null,
      // 是否多选
      isDisabled: false,
      // 树数据
      classTreeData: null,
      // 直接查询的classId
      classId: null
    }
  },
  methods: {
    // 弹框
    openChooseDialog () {
      this.$openDialog({
        name: 'object-selecter',
        component: ObjectDialog,
        options: {
          title: this.dialogTitle ? this.dialogTitle : '选择对象',
          customClass: this.classShortName ? 'dialog-width-m' : 'dialog-width-l'
        },
        events: {
        },
        props: {
          treeData: this.classTreeData,
          classId: this.classId
        },
        buttons: [{
          text: '确认',
          icon: 'circle-check',
          type: 'primary',
          handler: () => {
            const result = this.treeVm.getResult()
            if (result) {
              this.currentValue = result.id
              this.currentView = result.name
              this.$emit('input', this.currentValue)
              this.$closeDialog('object-selecter')
            }
          }
        }, {
          text: '取消',
          icon: 'circle-close ',
          handler: () => {
            // 选择
            this.$closeDialog('object-selecter')
          }
        }, {
          text: '清空',
          icon: 'close',
          handler: () => {
            this.currentValue = ''
            this.currentView = ''
            this.$emit('input', this.currentValue)
            this.$closeDialog('object-selecter')
          }
        }]
      }).then(treeVm => {
        this.treeVm = treeVm
      })
    }
  }
}
</script>
<style>
	
</style>
