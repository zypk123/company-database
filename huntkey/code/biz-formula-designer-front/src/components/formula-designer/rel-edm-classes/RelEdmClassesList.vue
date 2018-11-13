<template>
  <div class="rel-edm-classes-list">
    <!-- 公式设计器 选择类-->
    <el-card class="box-card has-header-a">
      <div slot="header" class="clearfix">
        请选择相关类
        <i class="el-icon-plus float-right" @click="showRelEdmClassEditDlg()"></i>
      </div>
      <el-scrollbar
        tag="div"
        class="list-items-scroll"
        wrap-class="list-items-scroll-wrapper"
        view-class="el-select-dropdown__list"
        >
        <div v-for="relClass in store.relEdmClasses" :key="relClass.clssId" class="list-item"
          :class="{'is-current': relClass.clssId===store.currentSelectedClass}"
          @click="listItemClickEvent($event,relClass)">
          <div class="list-item-label-area" :title="relClass.clssAliasName">
             <i class="el-icon-caret-right"></i>
              {{relClass.clssClassRelatedName}}
              <span class="sub-label">{{relClass.clssAliasName}}</span>
          </div>
          <div class="list-item-oper-area">
            <i class="el-icon-rx-edit" @click.stop="listItemEditBtnClickEvent($event, relClass)" title="修改相关类"></i>
            <i class="el-icon-rx-delete" @click.stop="listItemDelBtnClickEvent($event, relClass)" title="删除相关类"></i>
          </div>
        </div>
      </el-scrollbar>
    </el-card>
  </div>
</template>
<script>
import { formulaService } from '@/api'
import RelEdmClassEdit from './RelEdmClassEdit'
export default {
  name: 'relEdmClassesList',
  props: ['store'],
  data () {
    return {
      pageConfig: {
        relEdmClassEditDlgTitle: '相关类设置-新增',
        relEdmClassEditDlgVisible: false,
        relClass: ''
      }
    }
  },
  methods: {
    listItemClickEvent (e, relClass) {
      this.$emit('selectClass', relClass)
    },
    listItemEditBtnClickEvent (e, relClass) {
      this.showRelEdmClassEditDlg(relClass)
    },
    listItemDelBtnClickEvent (e, relClass) {
      this.$confirm('确定删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return formulaService.delRelClass({
          clssId: relClass.clssId
        }).then((resData) => {
          // FIXME 刷新相关类列表
          // 判断是否删除成功
          this.$emit('refreshMe')
        }).catch((error) => {
          console.log(error)
          this.AppUtils.showError(error.message)
        })
      })
    },
    relClassUpdated () {
      this.$emit('refreshMe')
    },
    showRelEdmClassEditDlg (relClass) {
      let dlgTitle = '相关类设置-新增'
      let mode = 'new'
      if (relClass) {
        mode = 'edit'
        dlgTitle = '相关类设置-编辑'
        this.showRelEdmClassEditDlgOper({mode, relClass, dlgTitle})
      } else {
        formulaService.getNewRelClassId(
          {formulaId: this.store.formula.formulaId},
          (resData) => {
            console.log('测试相关类', resData)
            relClass = resData
            this.showRelEdmClassEditDlgOper({mode, relClass, dlgTitle})
          },
          (error) => {
            console.log(error)
            this.AppUtils.showError('初始化相关类信息时发生网络错误.')
          }
        )// end of formulaService.getNewRelClassId
      }
    },
    showRelEdmClassEditDlgOper ({mode, relClass, dlgTitle}) {
      this.OpenGlobalDialog({
        name: 'RelEdmClassEditDlg',
        component: RelEdmClassEdit,
        props: {
          mode: mode, // 暂时没用上 2017-08-01
          store: this.store,
          relClass: relClass
        },
        options: {
          title: dlgTitle,
          customClass: 'subpage-dlg dialog-width-xxl'
        },
        events: {
          relClassUpdated: () => {
            this.$emit('refreshMe')
          },
          close: () => {
            this.CloseGlobalDialog('RelEdmClassEditDlg')
          }
        }
      })
    }
  },
  computed: {
  },
  components: {
    RelEdmClassEdit
  }
}
</script>
<style scoped>

.rel-edm-classes-list .top-oper-area {
  height: 24px;
  float: right;
  margin-top:6px;
}
.rel-edm-classes-list .top-oper-area .el-button {
  margin: 0;
  padding: 1px;
}
</style>
