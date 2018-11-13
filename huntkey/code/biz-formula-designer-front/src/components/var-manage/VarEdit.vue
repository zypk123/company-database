<template>
  <div class="var-edit subpage">
    <el-row>
      <el-col :span="24">
        <el-button type="primary" @click="pageConfig.isEditing=!pageConfig.isEditing" v-if="!pageConfig.isEditing">编辑</el-button>
        <el-button type="warning" @click="cancleFunc" v-if="pageConfig.isEditing">取消 </el-button>
        <el-button type="primary" @click="doSubmit" :loading="pageConfig.isSaving" :disabled="!pageConfig.isEditing">保存</el-button>
      </el-col>
    </el-row><!-- /.el-row 顶部查询按钮 -->

    <el-row class="common-vgap">
      <el-col :span="24" >
        <el-form :model="varInfo"  :rules="varInfoFormRules" ref="varInfoForm"  label-width="80px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="变量类型" prop="vrntVarType">
                <el-select v-model="varInfo.vrntVarType" placeholder="请选择变量类型"
                  :disabled="!isLock">
                  <el-option label="请选择" value=""></el-option>
                  <el-option v-for="dataType in dataTypeDict"
                    :label="dataType.label"
                    :value="dataType.val"
                    :key="dataType.val" >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="变量名称" prop="vrntVarName" >

                <el-input v-model="varInfo.vrntVarName" placeholder="变量名称(字母开头,英文,数字,下划线,不能包含空格)"
                  title="字母开头,英文,数字,下划线,不能包含空格"
                  :disabled="!isLock"
                  ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="变量状态" prop="vrntState" >
                <el-select v-model="varInfo.vrntState" placeholder="请选择变量状态"
                  :disabled="!pageConfig.isEditing"
                  >
                  <el-option v-for="varStatus in varStatusDict"
                    :label="varStatus.label"
                    :value="varStatus.val"
                    :key="varStatus.val" >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="变量说明" prop="vrntVarDesc" >
            <el-input v-model="varInfo.vrntVarDesc" :disabled="!pageConfig.isEditing"
                type="textarea"
                :rows="3"
                placeholder="变量说明" >
            </el-input>
          </el-form-item>

          <el-form-item label="变量公式" prop="varFormulaHtml">
            <el-button type="primary" @click="showFormulaDesigner" size="mini" :disabled="!pageConfig.isEditing">编辑</el-button>
            <div class="formula-editor" v-html="varInfo.varFormulaHtml"></div><!-- /.formula-editor -->
          </el-form-item>

          <el-form-item label="变更摘要" prop="vrntModifyRemarks" >
            <el-input v-model="varInfo.vrntModifyRemarks" :disabled="!pageConfig.isEditing"
                type="textarea"
                :rows="2"
                placeholder="变更摘要" >
            </el-input>
          </el-form-item>


          <el-row>
            <el-col :span="8">
              <el-form-item label="维护人" >
              {{varInfo.adduser}}
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="维护时间" >
              {{varInfo.addtime|datetimeFormat}}
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>
        </el-col>
    </el-row><!-- /.el-row 编辑表单-->
  </div>

</template>
<script>
import editorMixin from '@/mixin/editor-mixin'
import { mapGetters } from 'vuex'
import { varService } from '@/api'
import SysVarDesigner from '@/components/formula-designer/SysVarDesigner'
export default {
  name: 'varEdit',
  mixins: [editorMixin],
  props: ['varId'], // openType: add,edit
  data () {
    return {
      pageConfig: {
        isEditing: false, // 编辑中
        isSaving: false,
        isAdd: false // 是否新增
      },
      isLock: false,
      initVarInfo: {},
      varInfo: {
        vrntId: '',
        vrntVarType: '',
        vrntVarName: '',
        vrntState: '',
        vrntVarDesc: '',
        vrntFormulaId: '',
        varFormulaHtml: '',
        vrntModifyRemarks: '',
        adduser: '',
        addtime: new Date(),
        isenable: '1',
        vrntVarScope: 'system'
      },
      varInfoFormRules: {
        vrntVarType: [
          { required: true, message: '请选择变量类型' }
        ],
        vrntVarName: [
          { required: true, message: '请输入变量名称' },
          {
            validator: (rules, value, callback) => {
              if (!this.Utils.validate(value, 'var_name')) {
                // 变量名称(字母开头,英文,数字,下划线,不能包含空格)
                callback(new Error('格式错误,字母开头,英文,数字,下划线,无空格'))
              }
              callback()
            }
          }
        ],
        varFormulaHtml: [
          { required: true, message: '请编辑变量公式' }
        ],
        vrntState: [
          { required: true, message: '请输入变量状态' }
        ]
      }
    } // end of return
  }, // end of data
  created () {
    if (this.varId) { // 编辑
      varService.getSysVar(this.varId).then((data) => {
        this.initVarInfo = this.Utils.cloneDeep(data)
        this.varInfo = data.tvmVariant
        let tempItems = JSON.parse(data.formula.frmuFormulaStyle)
        this.$set(this.varInfo, 'varFormulaHtml', this.buildFromulaStrHtml(tempItems))
      }).catch((err) => {
        console.log('----catch-----' + err)
      })
    } else { // 新增
      varService.initSystVariant().then((data) => {
        this.varInfo.vrntId = data.vrntId
        this.varInfo.vrntFormulaId = data.vrntFormulaId
        this.initVarInfo = data
      }).catch((err) => {
        console.log('----catch-----' + err)
      })
      this.pageConfig.isEditing = true
      this.pageConfig.isAdd = true
    }
  },
  mounted () {
    let oldWinOnMsg = window.onmessage
    window.onmessage = (msg) => {
      if (typeof oldWinOnMsg === 'function') {
        oldWinOnMsg()
      }
      if (msg && msg.data && msg.data.eventType) {
        if (msg.data.eventType === 'sysVarUpdated') {
          let newStr = this.buildFromulaStrHtml(msg.data.eventData.formula.formulaItems)
          this.$set(this.varInfo, 'varFormulaHtml', newStr)
        }
      }
    }
  },
  methods: {
    doSubmit () {
      if (!this.pageConfig.isSaving) {
        this.$refs.varInfoForm.validate((valid) => {
          if (valid) {
            this.pageConfig.isSaving = true
            varService.updateSysVar(this.varInfo).then((data) => {
              console.log(data)
              if (data && data.retCode && data.retCode !== 1) {
                this.$message.warning(data.errMsg)
                return
              }
              if (this.pageConfig.isAdd) {
                this.$message.success('保存成功！')
              } else {
                this.$message.success('更新成功！')
              }
              this.$emit('saveSuccess')
            }).catch((data) => {
              this.pageConfig.isSaving = false
            })
            this.pageConfig.isSaving = false
          } else {
            return false
          }
        })
      }
    },
    showFormulaDesigner () {
      this.OpenGlobalDialog({
        name: 'sys-var-designer-dialog',
        component: SysVarDesigner,
        props: {
          sysVar: this.varInfo
        },
        options: {
          title: '公式设计器-系统变量',
          customClass: 'subpage-dlg bg-color-1 min-editor dialog-width-max'
        },
        events: {
          sysVarUpdated: this.sysVarUpdated
        }
      })
    },
    sysVarUpdated ({formulaInfo}) {
      // do sth here
      this.varInfo.vrntVarType = formulaInfo.returnType
      this.varInfo.vrntVarName = formulaInfo.label
      this.varInfo.vrntVarName = formulaInfo.label
      this.$set(this.varInfo, 'varFormulaHtml', this.buildFromulaStrHtml(formulaInfo.formulaItems))
      this.CloseGlobalDialog('sys-var-designer-dialog')
    },
    cancleFunc () {
      this.pageConfig.isEditing = !this.pageConfig.isEditing
      if (this.pageConfig.isAdd) {
        this.$emit('cancleAddVar')
      } else {
        this.varInfo = this.Utils.cloneDeep(this.initVarInfo.tvmVariant)
        let tempItems = JSON.parse(this.initVarInfo.formula.frmuFormulaStyle)
        this.$set(this.varInfo, 'varFormulaHtml', this.buildFromulaStrHtml(tempItems))
      }
    }
  },
  computed: {
    ...mapGetters({
      dataTypeDict: 'getDataTypeDict',
      varStatusDict: 'getVarStatusDict'
    })
  }
}
</script>
<style>
.formula-editor {
  position: relative;
  margin-top: 10px;
  margin-bottom:10px;
  padding-top:10px;
  padding-right: 10px;
  padding-left:10px;
  padding-bottom:10px;
  border: 1px solid gray;
  min-height: 75px; /* textarea 3行高度一致 */
  border-radius: 4px;
}
</style>
