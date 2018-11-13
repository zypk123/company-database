<template>
  <div class="fun-edit subpage">
    <!--按钮区域-->
    <el-row>
      <el-col :span="24">
        <el-button type="primary" icon="save" @click="beginSaving" :loading="pageConfig.isSaving">
            保存
        </el-button>

      <!--   <el-button type="warning" @click="cancelEditing" v-if="pageConfig.isEditing">
           取消
        </el-button> -->

        <el-button icon="cancel" type="warning" @click="closeDlg" >
          取消
        </el-button>
  
        <el-button type="primary" @click="showFuncValidatorDlg" :disabled="!canValidate">
          函数校验
        </el-button>
      </el-col>
    </el-row>

    <!-- 编辑表单区域 -->
    <el-row class="common-vgap">
      <el-col :span="24" >
        <el-form :model="funInfo"  :rules="funInfoFormRules" ref="funInfoForm"  label-width="100px">
          <el-row>
            <el-col :span="7">
              <el-form-item label="函数分类" prop="fundFunCatagoryId">
                <el-select v-model="funInfo.fundFunClassifyId" placeholder="请选择函数分类">
                  <el-option v-for="funCatagory in classifyData"
                    :label="funCatagory.fnccClassifyName"
                    :value="funCatagory.fnccId"
                    :key="funCatagory.fnccId" >
                  </el-option>
                </el-select>

              </el-form-item>
            </el-col>
            <el-col :span="1">
              <i class="el-icon-plus show-classify-manage-dlg-btn default-color" @click="showClassifyManageDlg"></i>
            </el-col>
            <el-col :span="8">
              <el-form-item label="函数名称" prop="fundFunName" >
                <el-input v-model="funInfo.fundFunName" placeholder="函数名称" title="函数名称必须跟文件名一致" :disabled="!pageConfig.display">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="函数状态" prop="fundState">
                <el-select v-model="funInfo.fundState" placeholder="请选择函数状态">
                  <el-option label="请选择" value=""></el-option>
                  <el-option v-for="funStatus in funStatusDict"
                    :label="funStatus.label"
                    :value="funStatus.val"
                    :key="funStatus.val" >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="函数说明">
            <el-input v-model="funInfo.fundFunDesc"
                type="textarea"
                :rows="3"
                placeholder="函数说明" >
            </el-input>
          </el-form-item>

          <el-row>
            <el-form-item label="文件位置" prop="fileLoacation" >
              <input type="file" @change="fileChange">
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="jar位置" prop="jarFileLocation" >
              <input type="file" @change="jarFileChange">
            </el-form-item>
          </el-row>

          <el-form-item label="变更摘要" >
            <el-input v-model="funInfo.fundModifyRemarks"
                prop="fundModifyRemarks"
                type="textarea"
                :rows="2"
                placeholder="变更摘要" >
            </el-input>
          </el-form-item>

          <el-row>
            <el-col :span="8">
              <el-form-item label="维护人" >
              {{funInfo.moduser}}
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="维护时间" >
              {{funInfo.modtime|datetimeFormat}}
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>
      </el-col>
    </el-row><!-- /.el-row 编辑表单-->

    <!--tab-pane区域-->
    <el-row>
    <el-col :span="24">
      <el-tabs type="border-card">
        <!-- ===============================函数输入参数===============================....-->
        <el-tab-pane label="函数输入参数">
          <el-table :data="paramTypes" height="100" border>
            <el-table-column type="index" label="序号" align="center" ></el-table-column>
            <el-table-column align='center' label="参数类型">
              <template scope="scope">
                {{formatDataType(scope.row.type)}}
              </template>
            </el-table-column>
             <el-table-column align='center' label="是否可选" prop="option" :formatter="optionFormat">
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <!-- ===============================函数返回值===============================....-->
        <el-tab-pane label="函数返回值">
          <el-table :data="returnTypes" height="100" border>
            <el-table-column type="index" label="序号" align="center" ></el-table-column>
            <el-table-column align='center' label="参数类型">
              <template scope="scope">
                {{formatDataType(scope.row.type)}}
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
    </el-tabs>
    </el-col>
    </el-row>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import appMixin from '@/mixin/app-mixin'
import formatMixin from '@/mixin/data-format-mixin'
import { funcService, classifyService } from '@/api' //
// import axios from 'axios'
import ClassifyManage from './ClassifyManage'
import FuncValidator from './func-validator/FuncValidator'

export default {
  name: 'funEdit',
  mixins: [formatMixin, appMixin],
  props: ['initData'],
  data () {
    return {
      pageConfig: {
        isEditing: false, // 编辑中
        isSaving: false,
        display: this.initData.display
      },
      initFunInfo: {
        fundId: '',
        // fundFunCatagoryId: '',
        fundFunClassifyId: '',
        fnccClassifyCode: '',
        fundFunName: '',
        fundState: '',
        fundFunDesc: '',
        fileLoacation: '',
        fundModifyRemarks: '',
        file: null, // 文件信息
        fileName: '', // 增加一个字段, 用于触发文件校验
        jarFile: null, // jar文件信息
        jarFileName: ''
      }, // 初始化数据
      funInfo: {
        fundId: '',
        // fundFunCatagoryId: '',
        fundFunClassifyId: '',
        fnccClassifyCode: '',
        fundFunName: '',
        fundState: '',
        fundFunDesc: '',
        fileLoacation: '',
        fundModifyRemarks: '',
        file: null, // 文件信息
        fileName: '', // 增加一个字段, 用于触发文件校验
        jarFile: null, // jar文件信息
        jarFileName: ''
      },
      funcDescInfo: { // 记录返回的函数描述信息, 用于判断是否允许函数校验
      },
      paramTypes: [],
      returnTypes: [],
      funInfoFormRules: { // 校验规则
        fundFunClassifyId: [
          { required: true, message: '请选择函数分类', trigger: 'blur' }
        ],
        fundFunName: [
          { required: true, message: '请输入函数名称', trigger: 'blur' }
        ],
        fundState: [
          { required: true, message: '请选择函数状态', trigger: 'blur' }
        ],
        fileLoacation: [
          { required: true, message: '请选择文件', trigger: 'blur' }
        ]
      },
      fileList: [],
      //  分类函数字典
      classifyData: []
    }
  },
  created () {
    console.info(this.initData)
    // if (this.initData.mode === 'new') {
    //   let fileLoacationRule = {}
    //   this.$set(this.funInfoFormRules, 'fileLoacation', fileLoacationRule)
    // }
    classifyService.queryClassifyNameList().then((data) => {
      this.classifyData = data
      if (this.initData.mode === 'edit') {
        console.log(this.initData.data)
        this.funInfo.fundId = this.initData.data.fundId
        this.funInfo.fundFunName = this.initData.data.fundFunName
        // FIXME 数据格式没确定..
        this.funInfo.fundFunClassifyId = this.initData.data.fundFunClassifyId || this.initData.data.fundFunCatagoryId
        this.funInfo.fundFunDesc = this.initData.data.fundFunDesc
        this.funInfo.fundModifyRemarks = this.initData.data.fundModifyRemarks
        this.funInfo.fundState = this.initData.data.fundState
        this.initFuncDescInfo() // 获取函数描述(包含输入参数,返回值信息)
      } else { // this.initData.mode === 'new'
        this.pageConfig.isEditing = true
      }
    }).catch((error) => {
      this.AppUtils.showErrorMsg(error)
    })
  },
  methods: {
    initFuncDescInfo () {
      this.funcDescInfo = {}
      funcService.getFuncDesc(this.funInfo.fundId).then((resData) => {
        if (!resData) {
          return
        }
        this.funcDescInfo = resData
        // 把参数里的types打平 ( types可能有多个值, 现在仅仅显示第一个)
        let paramTypeTemps = []
        if (resData && resData.paramTypes && resData.paramTypes.length > 0) {
          resData.paramTypes.forEach((ele, index, arr) => {
            let paramType = {
              seq: ele.seq,
              paramOrderType: ele.paramOrderType,
              option: ele.option,
              type: ele.types[0]
            }
            paramTypeTemps.push(paramType)
          })
        }
        this.paramTypes = paramTypeTemps
        let returnType = {
          seq: 1,
          type: resData.funType
        }
        this.returnTypes = [returnType]
      })
    },
    beginEditing () {
      console.log('this.funInfo', this.funInfo)
      this.initFunInfo = this.Utils.cloneDeep(this.funInfo)
      this.pageConfig.isEditing = true
    },
    cancelEditing () {
      this.funInfo = this.Utils.cloneDeep(this.initFunInfo)
      this.pageConfig.isEditing = false
    },
    beginSaving () {
      // 这里的层级有些多了, 回头梳理下. 2017-08-15 FIXME
      this.pageConfig.isSaving = true
      this.checkFormValid().then(() => {
        this.$confirm('是否确认保存?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.saveOper().then((res) => {
            if (res.retCode) {
              if (res.retCode === 4003) {
                this.AppUtils.showError(res.errMsg)
              }
            } else {
              this.funInfo.fundId = res
              this.AppUtils.showSuccess('函数保存成功')
              this.$emit('reload')
              this.initFuncDescInfo()
              this.$emit('close')
            }
            this.pageConfig.isSaving = false
          }).catch((error) => {
            // 取消的时候也是返回错误信息...
            console.log(error)
            this.AppUtils.showError('解析函数文件出错, 请检查函数文件')
            this.pageConfig.isSaving = false
          })
        }).catch(() => {
          // 此处catch到的是确认信息的取消
          this.pageConfig.isSaving = false
        })
      }).catch((error) => {
        this.pageConfig.isSaving = false
        console.log(error)
      })
    },
    // 检查表单信息是否有效
    checkFormValid () {
      console.info('...checkFormValid...')
      return new Promise((resolve, reject) => {
        this.$refs.funInfoForm.validate((valid) => {
          if (valid) {
            resolve()
          } else {
            reject({message: '校验不通过'})
          }
        })
      })
    },
    saveOper () { // 通过结构保存数据
      let formData = new FormData()
      formData.append('fundId', this.funInfo.fundId)
      formData.append('files', this.funInfo.file)
      formData.append('files', this.funInfo.jarFile)
      formData.append('fundFunName', this.funInfo.fundFunName)
      formData.append('fundState', this.funInfo.fundState)
      formData.append('fundFunDesc', this.funInfo.fundFunDesc)
      formData.append('fundModifyRemarks', this.funInfo.fundModifyRemarks)
      formData.append('fundFunCatagoryId', this.funInfo.fundFunClassifyId)
      formData.append('fundFunClassifyId', this.funInfo.fundFunClassifyId)
      formData.append('fundFunClassifyCode', this.funInfo.fundFunClassifyCode || this.getClassifyCode(this.funInfo.fundFunClassifyId))
      if (this.funInfo.fundId) {
        return funcService.updateFuncInfo(formData)
      } else {
        return funcService.saveFuncInfo(formData)
      }
    },
    showClassifyManageDlg () {
      this.OpenGlobalDialog({
        name: 'classifyManageDialog',
        component: ClassifyManage,
        props: {},
        options: {
          title: '函数分类管理',
          top: '20%',
          customClass: 'subpage-dlg  dialog-width-xxl'
        },
        dialogEvents: {
          close: () => {
            this.CloseGlobalDialog('classifyManageDialog')
            classifyService.queryClassify().then((data) => {
              this.classifyData = data
            }).catch((error) => {
              this.AppUtils.showErrorMsg(error)
            })
          }
        },
        events: {
          close: () => {
            this.CloseGlobalDialog('classifyManageDialog')
            classifyService.queryClassify().then((data) => {
              this.classifyData = data
            }).catch((error) => {
              this.AppUtils.showErrorMsg(error)
            })
          }
        }
      })
    },
    showFuncValidatorDlg () {
      if (this.funcDescInfo) {
        this.OpenGlobalDialog({
          name: 'funcValidatorDialog',
          component: FuncValidator,
          props: {
            funcDescInfo: this.funcDescInfo
          },
          options: {
            title: '函数校验',
            top: '20%',
            customClass: 'subpage-dlg dialog-width-xxl'
          },
          events: {
            close: () => {
              this.CloseGlobalDialog('funcValidatorDialog')
            }
          }
        })
      } else {
        this.AppUtils.showMsg('函数尚未编译成功, 还不能校验')
      }
    },
    fileChange (e) {
      let file = e.target.files[0]
      if (file) {
        this.funInfo.file = file
        this.funInfo.fileLoacation = file.name
      }
    },
    jarFileChange (e) {
      let file = e.target.files[0]
      if (file) {
        this.funInfo.jarFile = file
        console.log(file)
      }
    },
    getClassifyCode (value) {
      for (var i = 0; i < this.classifyData.length; i++) {
        if (value === this.classifyData[i].fnccId) {
          return this.classifyData[i].fnccClassifyCode
        }
      }
      return 'unknow'
    },
    closeDlg () {
      this.$emit('close')
    }
  },
  computed: {
    ...mapGetters({
      funStatusDict: 'getFunStatusDict'
    }),
    canValidate () {
      if (this.funcDescInfo && this.funcDescInfo.funName) {
        return true
      }
      return false
    }
  }
}
</script>
<style scope>
.show-classify-manage-dlg-btn {
  margin-top: 11px;
}
</style>
