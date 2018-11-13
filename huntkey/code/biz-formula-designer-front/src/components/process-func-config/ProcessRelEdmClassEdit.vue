<template>
  <div class="rel-edm-class-edit subpage">
    <el-row>
      <el-col :span="24">
        <el-form :inline="true" class="demo-form-inline"
                 :model="newRelEdmClassInfo" :rules="relClassNameFormRules"
                 ref="relClassNameForm"
        >
          <el-form-item label="相关类" prop="clssClassId"
          >
            <el-input v-model="edmcClassNameFilter" ref="clssClassInput"
                      placeholder="请选择相关类"  icon="search"
                      :disabled="!pageConfig.isSelectingEdmClass"
                      :on-icon-click="handleIconClick"
                      @focus="edmcClassNameFocusEvent($event)"
                      @blur="edmcClassNameBlurEvent($event)"
                      style="width:220px"
            ></el-input>
            <edm-classes-tree
              style="width:220px"
              v-if="pageConfig.showEdmClassesTree"
              :useOuterFilterText="true"
              :outerFilterText="edmcClassNameFilter"
              @selectClass="selectEdmClass"
            >
            </edm-classes-tree>
          </el-form-item>

          <el-form-item label="相关类名称" prop="clssAliasName">
            <el-input v-model.trim="newRelEdmClassInfo.clssAliasName" placeholder="相关类名称"
                      :disabled="!pageConfig.isSelectingEdmClass"
                      :maxlength="32">
            </el-input>
          </el-form-item>

          <el-form-item  v-if="pageConfig.isSelectingEdmClass">
            <el-button type="primary" title="锁定选择的相关类"
                       @click="lockSelectedEdmClass"  :disabled="!pageConfig.isSelectingEdmClass" >
              锁定
            </el-button>
          </el-form-item>

          <!--       <el-form-item v-if="pageConfig.isSelectingEdmClass">
                  <el-button title="解锁选择的相关类" :disabled="!pageConfig.canCancel"
                    @click="cancelLockSelectEdmClass" >
                    取消
                  </el-button>
                </el-form-item> -->

          <el-form-item v-if="!pageConfig.isSelectingEdmClass">
            <el-button type="warning" title="解锁选择的相关类"
                       @click="unLockSelectEdmClass" >
              解锁
            </el-button>
          </el-form-item>

        </el-form>
      </el-col><!-- /.el-col-24 顶部信息-->

      <el-col class="common-vgap" :span="24">
        <span>条件列表:</span>
        <div class="float-right">
          <el-button type="primary" size="small" icon="plus" :disabled="!condEditable"
                     @click="addNewCond">添加</el-button>
        </div>
      </el-col><!-- /.el-col-24 条件列表.标签-->

      <el-col class="common-vgap" :span="24">
        <el-table
          :data="oldConds"
          border
          style="width: 100%"
        >

          <el-table-column label="序号" align='center' width="80">
            <template scope="scope">
              <span class="formula-item-seq block cursor-pointer no-select" @click="insertSeq(scope.$index + 1)"> [{{scope.$index + 1}}]</span>
            </template>
          </el-table-column>

          <el-table-column prop="cndtProp" label="属性" header-align="center" align="center" width="150">
          </el-table-column>

          <el-table-column prop="cndtOperate" label="条件" header-align="center" align="center" width="150">
          </el-table-column>

          <el-table-column prop="cndtValue" label="值" header-align="center" align="center">
          </el-table-column>

          <el-table-column label="操作" header-align="center" align="center" width="120">
            <template scope="scope">
              <el-button size="small"
                         @click="editCond(scope.$index)" :disabled="!condEditable"
                         type="text">编辑
              </el-button>
              <el-button size="small"
                         @click="deleteCond(scope.$index)" :disabled="!condEditable"
                         type="text">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col><!-- /.el-col-24 条件列表-->
    </el-row>

    <cond-formula-editor ref="editor"
                         :editable="condFormulaEditable"
                         :initFormulaName="newRelEdmClassInfo.clssConditionName"
                         :initFormulaText="newRelEdmClassInfo.clssConditionFormula"
                         :condsDescHtmlArr="condsDescHtmlArr"
                         :condsDescTextArr="condsDescTextArr"
    ></cond-formula-editor>

    <el-row>
      <el-col class="common-vgap" :span="24">
        <el-button type="primary" @click="updateRelClassInfo4Final" title="保存相关类" :disabled="!pageEditable">保存</el-button>
        <!-- <el-button type="primary" @click="test" title="保存相关类">test</el-button> -->
      </el-col><!-- /.el-col-24-->
    </el-row>
  </div>

</template>
<script>
  import {formulaService} from '@/api'
  import {mapGetters} from 'vuex'
  import EdmClassesTree from '@/components/common/edm-class-tree/EdmClassesTree'
  import CondFormulaEditor from '@/components/common/cond-formula-editor/CondFormulaEditor'
  import ProcessRelEdmClassCondEdit from './ProcessRelEdmClassCondEdit'
  export default {
    name: 'processRelEdmClassEdit',
    props: ['initData'],
    data () {
      return {
        pageConfig: {
          showEdmClassesTree: false,
          isSelectingEdmClass: true, // 是否正在重新选择相关类, 为true时,关联条件和关联公式不允许编辑.
          MSG_PLS_SEL_EDM_CLASS: '请选择关联类',
          isSaving: false,
          canCancel: false, // 是否允许取消
          edmcClassNameFocus: false,
          edmcClassTreeFocus: false
        },
        edmcClassNameFilter: '', // 该字段用于相关类名称的显示及新的关联类类的查找
        lastEdmcId: '',
        oldRelEdmClassInfo: {
          clssId: '', // 条件触发Id
          clssClassId: '', // 选中关联类Id
          clssFormulaId: '', // formulaId 作为一组相关类的公共属性, 校验相关类名唯一时用到, 带过去, 后台少查一次
          clssClassRelatedName: '',
          clssAliasName: '',
          clssEdmcNameEn: '',
          clssConditionName: '',
          clssConditionFormula: '',
          clssConditionDesc: '',
          clssLinkClassId: '',  // 入口参数类Id
          type: 2,
          isenable: 1
        },
        oldConds: [],
        newEdmClassInfo: null,
        newRelEdmClassInfo: {
          clssId: '',
          clssClassId: '',
          clssFormulaId: '',
          clssClassRelatedName: '',
          clssAliasName: '',
          clssEdmcNameEn: '',
          clssConditionName: '',
          clssConditionFormula: '',
          clssConditionDesc: '',
          clssLinkClassId: '',
          type: 2,
          isenable: 1
        },
        relClassNameFormRules: {
          clssClassId: [
            {required: true, message: '请选择关联的类'}
          ],
          clssAliasName: [
            {required: true, message: '相关类名称不能为空'},
            {
              validator: (rules, value, callback) => {
                if (!this.Utils.validate(value, 'name_en')) {
                  // 名称(字母开头,英文和数字,不能包含空格)
                  callback(new Error('相关类名称格式不正确'))
                }
                callback()
              }
            }
          ]
        }
      }
    },
    created () {
      console.info('init', this.initData)
      this.oldRelEdmClassInfo = this.Utils.cloneDeep(this.initData.relClass)
      this.newRelEdmClassInfo = this.Utils.cloneDeep(this.initData.relClass)
      this.oldRelEdmClassInfo.clssLinkClassId = this.initData.classId
      this.newRelEdmClassInfo.clssLinkClassId = this.initData.classId
      this.oldRelEdmClassInfo.type = 2
      this.newRelEdmClassInfo.type = 2
      // console.info(this.newRelEdmClassInfo)
      this.edmcClassNameFilter = this.oldRelEdmClassInfo.clssClassRelatedName
      this.lastEdmcId = this.oldRelEdmClassInfo.clssClassId || ''
      if (this.lastEdmcId) {
        this.pageConfig.isSelectingEdmClass = false
        this.pageConfig.canCancel = true
      } else {
        this.pageConfig.canCancel = false
      }
      // 加载条件
      formulaService.queryEdmClassCndsByClassID({
        clssId: this.initData.relClass.clssId
      }).then((resData) => {
        this.oldConds = resData
      })
    },
    methods: {
      // 触发类选择树组件
      handleIconClick () {
        if (this.pageConfig.isSelectingEdmClass) {
          this.pageConfig.showEdmClassesTree = true
          this.$refs.clssClassInput.$el.querySelector('input').focus()
        } else {
          this.AppUtils.showMsg('请先解锁')
        }
      },
      edmcClassNameFocusEvent (e) {
        this.pageConfig.edmcClassNameFocus = true
      },
      edmcClassNameBlurEvent (e) {
        this.pageConfig.edmcClassNameFocus = false
        // FIXME
        setTimeout(
          () => {
            if (!this.pageConfig.edmcClassNameFocus) {
              this.pageConfig.showEdmClassesTree = false
            }
          }
          , 500)
        setTimeout(
          () => {
            if (this.newEdmClassInfo) {
              this.edmcClassNameFilter = this.newEdmClassInfo.edmcName
            } else {
              this.edmcClassNameFilter = this.oldRelEdmClassInfo.clssClassRelatedName
            }
          }
          , 800)
      },
      selectEdmClass (edmClassInfo) {
        this.newEdmClassInfo = edmClassInfo
        this.newRelEdmClassInfo.clssClassId = edmClassInfo.id
        this.newRelEdmClassInfo.clssClassRelatedName = edmClassInfo.edmcName
        this.newRelEdmClassInfo.clssEdmcNameEn = edmClassInfo.edmcNameEn || 'edmcNameEnNull'
        this.newRelEdmClassInfo.clssAliasName = edmClassInfo.edmcNameEn || 'edmcNameEnNull'
        this.edmcClassNameFilter = edmClassInfo.edmcName
        this.$refs.clssClassInput.$el.querySelector('input').focus() // 重新触发校验
        this.pageConfig.showEdmClassesTree = false
      },
      // 锁定选择的关联类
      lockSelectedEdmClass () {
        // 1. 初始化时无需提示, 直接锁定 oldConds置空
        // 2. 只修改了相关类名称, 没有变更相关类无需提示, 直接锁定 oldConds不变
        // 3. 1,2时 可不动oldConds
        // 4. 关联类有变更,需清空oldConds和关联条件公式
        this.$refs.relClassNameForm.validate((valid) => {
          let needResetCond = false
          if (valid) {
            if (this.lastEdmcId !== '' &&
              (this.newEdmClassInfo && this.lastEdmcId !== this.newEdmClassInfo.id)) {
              needResetCond = true
            }
            if (needResetCond) {
              // 切换类了, 需清空关联条件和关联公式
              this.$confirm('变更关联类会清空关联条件和关联公式,是否确认？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.updateRelClassInfo4Lock(true)
              })
            } else {
              // 没有切换类, 只更新相关类名称
              this.updateRelClassInfo4Lock()
            }
          }// end of if (valid) {
        }) // end of this.$refs.relClassNameForm.validate((valid)
      },
      updateRelClassInfo4Lock (needResetCond) {
        if (needResetCond) {
          this.oldConds = []
          this.newRelEdmClassInfo.clssConditionFormula = ''
          console.info('initFormulaText1')
          console.info(this.newRelEdmClassInfo.clssConditionFormula)
          this.newRelEdmClassInfo.clssConditionDesc = ''
        }
        this.updateRelClassInfo().then(() => {
          this.pageConfig.isSelectingEdmClass = false
          this.pageConfig.canCancel = true
          if (needResetCond) {
            this.$refs.editor.clearFormula()
          }
          this.AppUtils.showSuccess('关联类已锁定, 您可以继续操作')
        }).catch((error) => {
          this.AppUtils.showError(error.message)
        })
      },
      // cancelLockSelectEdmClass () {
      //   // FIXME
      //   if (this.oldRelEdmClassInfo.id || this.lastEdmcId) {
      //     this.newEdmClassInfo = null
      //     this.Utils.clonePropsWhenExist(this.newRelEdmClassInfo, this.oldRelEdmClassInfo)
      //     this.pageConfig.isSelectingEdmClass = false
      //   } else {
      //     this.AppUtils.showError(this.pageConfig.MSG_PLS_SEL_EDM_CLASS)
      //   }
      // },
      unLockSelectEdmClass () {
        this.pageConfig.isSelectingEdmClass = true
      },
      addNewCond () {
        let index = this.oldConds.length
        let title = '相关类设置-关联条件-新增'
        let mode = 'add'
        this.openCondEditDialog({index, title, mode})
      },
      editCond (index) {
        let title = '相关类设置-关联条件-编辑'
        let mode = 'edit'
        let cond = this.oldConds[index]
        this.openCondEditDialog({index, title, cond, mode})
      },
      openCondEditDialog ({index, title, cond, mode}) {
        this.OpenGlobalDialog({
          name: 'processRelEdmClassCondEdit',
          component: ProcessRelEdmClassCondEdit,
          props: {
            relClass: this.oldRelEdmClassInfo,
            index: index,
            mode: mode,
            cond: cond,
            variableData: this.initData.variableData,
            classes: this.initData.classes
          },
          options: {
            title: title,
            top: '20%',
            customClass: 'subpage-dlg dialog-width-s'
          },
          events: {
            saveCond: this.saveCond,
            close: () => {
              this.CloseGlobalDialog('processRelEdmClassCondEdit')
            }
          }
        })
      },
      saveCond (obj) {
        console.log(obj.condData)
        this.$set(this.oldConds, obj.index, obj.condData)
      },
      deleteCond (index) {
        // this.$set()
        if (this.oldConds.length >= index) {
          this.oldConds.splice(index, 1)
        }
      },
      updateRelClassInfo4Final () {
        this.$refs.editor.valid().then(
          (resData) => {
            this.newRelEdmClassInfo.isenable = 1
            this.newRelEdmClassInfo.clssConditionName = resData.formulaName
            this.newRelEdmClassInfo.clssConditionFormula = resData.formulaText
            this.newRelEdmClassInfo.clssConditionDesc = resData.formulaDesc
            return this.updateRelClassInfo()
          }
        ).then(() => {
          this.AppUtils.showSuccess('相关类信息已保存.')
          this.$emit('close')
        }).catch((error) => {
          console.log(error)
        })
      },
      // 更新相关类信息
      updateRelClassInfo () {
        return new Promise((resolve, reject) => {
          this.pageConfig.isSaving = true
          console.log('条件', this.oldConds)
          console.log('公式信息', this.newRelEdmClassInfo)
          formulaService.saveRelatedClass({classRelated: this.newRelEdmClassInfo, tplConditions: this.oldConds}).then((resData) => {
            // console.log('resData', resData)
            this.lastEdmcId = this.newRelEdmClassInfo.id
            this.Utils.clonePropsWhenExist(this.oldRelEdmClassInfo, this.newRelEdmClassInfo)
            this.$emit('relClassUpdated', this.newRelEdmClassInfo)
            this.pageConfig.isSaving = false
            resolve(resData)
          }).catch((error) => {
            this.pageConfig.isSaving = false
            reject(error)
          })
        })
      },
      /**
       * 当条件公式发生变化时（插入或者写入，清空），解析条件公式文本框中的HTML，转化成formularText字符串
       */
      parseCndtOperatorDict (value) {
        for (var i = 0; i < this.cndtOperatorDict.length; i++) {
          if (value === this.cndtOperatorDict[i].val) {
            return this.cndtOperatorDict[i].label
          }
        }
        return '未知'
      },
      insertSeq (seq) {
        this.$refs.editor.insertSeq(seq)
      },
      test () {
        // console.info('this.oldRelEdmClassInfo')
        // console.info(this.oldRelEdmClassInfo)
        // console.info('this.newRelEdmClassInfo')
        // console.info(this.newRelEdmClassInfo)
        this.$refs.editor.valid().then(
          (resData) => {
            console.info('this.$refs.editor.valid')
            console.info(resData)
          }
        )
      }
      // 结束操作公式编辑区域
    },
    computed: {
      ...mapGetters({
        cndtOperatorDict: 'getCndtOperatorDict'
      }),
      pageEditable () {
        if (this.pageConfig.isSaving) {
          return false
        }
        if (this.pageConfig.isSelectingEdmClass) {
          return false
        }
        return true
      },
      condEditable () {
        if (this.pageConfig.isSaving) {
          return false
        }
        if (this.pageConfig.isSelectingEdmClass) {
          return false
        }
        return true
      },
      condFormulaEditable () {
        if (this.pageConfig.isSaving) {
          return false
        }
        if (this.pageConfig.isSelectingEdmClass) {
          return false
        }
        return true
      },
      condsDescHtmlArr () { // 条件的html描述
        let retArr = []
        for (let i = 0; i < this.oldConds.length; i++) {
          let line = `<div class="formula-item formula-item-prop" contenteditable="false">${this.oldConds[i].cndtProp}</div>`
          line += `<div class="formula-item formula-item-logic-oper" contenteditable="false">${this.parseCndtOperatorDict(this.oldConds[i].cndtOperate)}</div>`
          line += `<div class="formula-item" contenteditable="false">${this.oldConds[i].cndtValue}</div>`
          retArr.push(line)
        }
        return retArr
      },
      condsDescTextArr () { // 条件的html描述
        let retArr = []
        for (let i = 0; i < this.oldConds.length; i++) {
          let line = `${this.oldConds[i].cndtProp} ${this.parseCndtOperatorDict(this.oldConds[i].cndtOperate)} ${this.oldConds[i].cndtValue}`
          retArr.push(line)
        }
        return retArr
      }
    },
    components: {
      EdmClassesTree,
      CondFormulaEditor
    }
  }
</script>
<style scoped>
  .el-button--text {
    padding-left: 2px;
    padding-right: 2px;
  }
  .button-area{
    position: absolute;
    left: 25px;
    top: 10px;
  }
  .button-area  .el-button{
    width: 80px;
  }

</style>
