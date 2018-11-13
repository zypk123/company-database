<template>
  <div class="local-vars-list">
    <!-- 公式设计器 选择系统变量-->
    <el-card class="box-card has-header-a">
      <div slot="header" class="clearfix">
        <span class="a-card-header-title">请选择局部变量</span>
        <div class="top-oper-area">
          <i class="el-icon-plus float-right" @click="addLocalVar"></i>
        </div>
      </div>
       <el-scrollbar
        tag="div"
        class="list-items-scroll"
        wrap-class="list-items-scroll-wrapper"
        view-class="el-select-dropdown__list"
        >
        <div v-for="localVar in store.localVars" :key="localVar.vrntId" class="list-item">
          <div class="list-item-label-area" :title="localVar.varName"
            @click="listItemClickEvent($event,localVar)">
             <i class="el-icon-rx-dot-2x"></i>
              {{localVar.vrntVarName}}
              <span class="item-return-type">{{localVar.vrntVarType}}</span>
          </div>
          <div class="list-item-oper-area">
            <i class="el-icon-rx-edit" @click.stop="editLocalVar(localVar)"></i>
            <i class="el-icon-rx-delete" @click.stop="delLocalVar(localVar)"></i>
          </div>
        </div>
      </el-scrollbar>
    </el-card>
  </div>
</template>
<script>
import { formulaService } from '@/api'
import PropFormulaLocalVarDesigner from '../PropFormulaLocalVarDesigner'
// import TestComp from './TestComp'
export default {
  name: 'localVarsList',
  props: ['store'],
  data () {
    return {
    } // end of data.return
  }, // endo of data()
  methods: {
    listItemClickEvent (e, localVar) {
      this.$emit('selectVar', localVar)
    },
    addLocalVar () {
      formulaService.initLocalVariant({parentFormulaId: this.store.formula.formulaId})
      .then((initResult) => {
        let mode = 'new'
        let localVar = initResult.initLocVar
        let initformula = initResult.initformula
        this.openLocalVarDesignerDlg({mode, localVar, initformula})
      }) // end of then
    },
    editLocalVar (localVar) {
      let mode = 'edit'
      this.openLocalVarDesignerDlg({mode, localVar})
    },
    openLocalVarDesignerDlg ({mode, localVar, initformula}) {
      this.OpenGlobalDialog({
        name: 'local-var-designer-dialog',
        component: PropFormulaLocalVarDesigner,
        props: {
          mode: mode,
          parentStore: this.store,
          localVar: localVar,
          initformula: initformula
        },
        options: {
          title: '公式设计器-局部变量',
          customClass: 'subpage-dlg bg-color-1 min-editor dialog-width-max'
        },
        events: {
          localVarUpdated: this.localVarUpdated
        }
      })
    },
    localVarUpdated () {
      this.$emit('refreshMe')
      this.CloseGlobalDialog('local-var-designer-dialog')
    },
    delLocalVar (localVar) {
      this.$confirm('确定删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return formulaService.deleteVariant({
          varId: localVar.vrntId
        }).then((resData) => {
          // FIXME 刷新相关类列表
          // 判断是否删除成功
          this.$emit('refreshMe')
        }).catch((error) => {
          this.AppUtils.showError(error.message)
        })
      })
    }
  },
  components: {
    // LocalFormulaDesigner
  }
}
</script>
<style>
.local-vars-list .top-oper-area {
  height: 24px;
  float: right;
  margin-top:6px;
}
.local-vars-list .top-oper-area .el-button {
  margin: 0;
  padding: 1px;
}
</style>
