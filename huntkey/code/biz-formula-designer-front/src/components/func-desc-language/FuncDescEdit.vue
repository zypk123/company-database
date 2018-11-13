<template>
  <div class="funcDesc-edit">
    <el-row>
      <el-col :span="24">
        <el-button type="primary" @click="pageConfig.isEditing=!pageConfig.isEditing" v-if="!pageConfig.isEditing">编辑</el-button>
        <el-button type="warning" @click="cancleFunc" v-if="pageConfig.isEditing">取消 </el-button>
        <el-button type="primary" @click="doSubmit" :loading="pageConfig.isSaving" :disabled="!pageConfig.isEditing">保存</el-button>
      </el-col>
    </el-row>
    <el-row class="common-vgap">
      <el-col :span="24" >
        <el-form :model="funcDescInfo"  :rules="formRules" ref="infoForm"  label-width="80px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="变量类型" prop="varType">
                <el-select v-model="funcDescInfo.varType" placeholder="请选择变量类型"
                           :disabled="!pageConfig.isEditing">
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
                <el-input v-model="funcDescInfo.vrntVarName" placeholder="变量名称(字母开头,英文和数字,不能包含空格)"
                          title="字母开头,英文和数字,不能包含空格"
                          :disabled="!pageConfig.isEditing"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="变量状态" prop="vrntState" >
                <el-select v-model="funcDescInfo.vrntState" placeholder="请选择变量状态"
                           :disabled="!pageConfig.isEditing">
                  <el-option v-for="varStatus in varStatusDict"
                             :label="varStatus.label"
                             :value="varStatus.val"
                             :key="varStatus.val" >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="变量说明">
            <el-input v-model="funcDescInfo.vrntVarDesc" :disabled="!pageConfig.isEditing"
                      type="textarea"
                      :rows="3"
                      placeholder="变量说明" >
            </el-input>
          </el-form-item>

          <el-form-item label="变量公式" prop="varFormulaHtml">
            <div class="formula-editor" v-html="funcDescInfo.varFormulaHtml"></div>
          </el-form-item>

          <el-form-item label="变更摘要">
            <el-input v-model="funcDescInfo.vrntModifyRemarks" :disabled="!pageConfig.isEditing"
                      type="textarea"
                      :rows="2"
                      placeholder="变更摘要">
            </el-input>
          </el-form-item>

          <el-row>
            <el-col :span="8">
              <el-form-item label="维护人" >
                {{funcDescInfo.adduser}}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="维护时间" >
                {{funcDescInfo.addtime|datetimeFormat}}
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import editorMixin from '@/mixin/editor-mixin'
  import { mapGetters } from 'vuex'
  import funcDescService from '@/api/services/funcDesc-service'
  export default {
    name: 'funcDescEdit',
    mixins: [editorMixin],
    props: ['initData'],
    data () {
      return {
        pageConfig: {
          isEditing: false,
          isSaving: false,
          isAdd: false
        },
        initInfo: {},
        funcDescInfo: {
          vrntId: '',
          varType: '',
          vrntVarName: '',
          vrntState: '',
          vrntVarDesc: '',
          varFormulaHtml: '',
          vrntFormulaId: '',
          vrntModifyRemarks: '',
          adduser: '',
          addtime: new Date(),
          isenable: '1',
          vrntVarScope: 'system'
        },
        formRules: {
          varType: [
            { required: true, message: '请选择变量类型' }
          ],
          vrntVarName: [
            { required: true, message: '请输入变量名称' },
            {
              validator: (rules, value, callback) => {
                if (!this.Utils.validate(value, 'name_en')) {
                  // 名称(字母开头,英文和数字,不能包含空格)
                  callback(new Error('格式错误,字母开头,英文和数字,无空格'))
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
      }
    },
    created () {
      if (this.initData.data) {
//        funcDescService.getFuncDescLanguage(this.initData.data).then((data) => {
//          // 获取响应数据
//          this.initInfo = this.Utils.cloneDeep(data)
//          this.funcDescInfo = data.tvmVariant
//        }).catch((err) => {
//          this.$message.error(err.message)
//        })
      } else {
//        funcDescService.initFuncDescLanguageId().then((data) => {
//          // 获取响应数据
//          // this.funcDescInfo.vrntId = data.vrntId
//        }).catch((err) => {
//          this.$message.error(err.message)
//        })
        this.pageConfig.isEditing = true
        this.pageConfig.isAdd = true
      }
    },
    methods: {
      doSubmit () {
        this.$refs.infoForm.validate((valid) => {
          if (valid) {
            if (!this.pageConfig.isSaving) {
              this.pageConfig.isSaving = true
              if (this.pageConfig.isAdd === false) {
                funcDescService.updateFuncDescLanguage(this.funcDescInfo).then((data) => {
                  if (data.data.retCode && data.data.retCode !== 1) {
                    this.$message.warning(data.data.errMsg)
                    return
                  }
                  this.$message.success('数据修改成功')
                  this.$emit('saveSuccess')
                }).catch((err) => {
                  this.$message.error(err.message)
                  this.pageConfig.isSaving = false
                })
              } else {
                funcDescService.addFuncDescLanguage(this.funcDescInfo).then((data) => {
                  if (data.data.retCode && data.data.retCode !== 1) {
                    this.$message.warning(data.data.errMsg)
                    return
                  }
                  this.$message.success('数据新增成功')
                  this.$emit('saveSuccess')
                }).catch((err) => {
                  this.$message.error(err.message)
                  this.pageConfig.isSaving = false
                })
              }
              this.pageConfig.isSaving = false
            }
          } else {
            this.$message.warning('数据校验错误')
            return false
          }
        })
      },
      cancleFunc () {
        this.$emit('close')
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
<style scope>
  .formula-editor {
    margin-top: 10px;
    margin-bottom:10px;
    padding-top:10px;
    padding-right: 10px;
    padding-left:10px;
    padding-bottom:10px;
    border: 1px solid gray;
    min-height: 75px;
    border-radius: 4px;
  }
</style>
