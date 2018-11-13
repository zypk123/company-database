<template>
<div>
  <div class="top-button" >
    <div v-if="isMod" style="display: inline-block">
      <el-button type="info" @click="isEdit=!isEdit" v-if="!isEdit"><i class="el-icon-rx-bianji2">&nbsp;编辑</i></el-button>
      <el-button type="info" @click="cancel" v-if="isEdit"><i class="el-icon-rx-chexiao" >&nbsp;取消</i></el-button>
      <el-button type="info" @click="save('formInfo')" :loading="isUpdating" :disabled="!isEdit"><i class="el-icon-rx-baocun2">&nbsp;保存</i></el-button>
    </div>
    <el-button type="info" @click="insert('formInfo')" :disabled="!isEdit" v-if="!isMod" :loading="isUpdating"><i class="el-icon-rx-baocun2">&nbsp;保存</i></el-button>
    <el-button type="info" @click="ChangeStatus" v-if="formData.info.edmmStatus === '0' || formData.info.edmmStatus === null" :disabled="!isEdit"><i class="el-icon-rx-jinyong">&nbsp;启用</i></el-button>
    <el-button type="info" @click="ChangeStatus" v-else :disabled="!isEdit"><i class="el-icon-rx-jinyong">&nbsp;禁用</i></el-button>
    <el-button type="info" @click="deleteMethod" :disabled="!isEdit"><i class="el-icon-delete2" >&nbsp;删除</i></el-button>
    </div>
  <div class="method-form">
    <el-form :model="formData"  ref="form">
      <el-tabs type="border-card">
        <el-tab-pane label="方法定义">
          <el-form :model="formData.info" :inline="true" :rules="rules" ref="formInfo" inline>
          <div class="form-inline">
            <el-form-item label="方法分类" prop="edmmType" label-width="75px">
              <el-input v-model="formData.info.edmmTypeName"  size="small" @focus="openTree" :disabled="!isEdit"
              placeholder="请选择方法分类"  icon="plus" @click="openTree" readonly></el-input>
               <!-- style="width: 190px" -->
            </el-form-item>
              <el-form-item  label="方法名称"  prop="edmmName" label-width="75px">
                <el-input v-model="formData.info.edmmName"  size="small" :disabled="!isEdit"></el-input>
              </el-form-item>
              <el-form-item label="程序类型" prop="edmmProgramType"  label-width="75px">
                  <el-select v-model="formData.info.edmmProgramType"  size="small" :disabled="!isEdit" filterable>
                   <!-- style="width: 190px" -->
                    <el-option v-for="options in allDict.edm_program_type" :key="options.id" :label="options.codeName" :value="options.codeValue"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="可覆盖" label-width="75px">
                <div class="checkClass"><el-checkbox v-model="formData.info.isCover" :disabled="!isEdit"></el-checkbox></div>
                </el-form-item>
              </div>
<!--            <el-form-item label="状态：">
              <div v-if="formData.info.edmmStatus == '1'">启用</div>
              <div v-else>禁用</div>
            </el-form-item>-->
            <!-- <div style="display: block">
              <el-form-item label="程序类型" prop="edmmProgramType"  label-width="75px">
                <el-select v-model="formData.info.edmmProgramType"  size="small" style="width: 190px" :disabled="!isEdit" filterable>
                  <el-option v-for="options in allDict.edm_program_type" :key="options.id" :label="options.codeName" :value="options.codeValue"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="可覆盖" label-width="75px">
              <div class="checkClass"><el-checkbox v-model="formData.info.isCover" :disabled="!isEdit"></el-checkbox></div>
              </el-form-item> -->
            <!-- <el-form-item label="平台方法" style="margin-left: 25px" label-width="75px">
              <div class="checkClass"><el-checkbox v-model="formData.info.isPlatformProgram" :disabled="!isEdit"></el-checkbox></div>
            </el-form-item> -->
            <!-- </div> -->
            <el-form-item  label="所属类" size="small" style="margin-left: 2px" label-width="75px" prop="edmcId">
              <!-- 所属类，可多选、 -->
              <el-input v-model="formData.info.edmcName" style="width:690px" :disabled="!isEdit" size="small" icon="plus" @focus="chooseClasses" @click="chooseClasses">
              </el-input>
            </el-form-item>
            <div style="display: block" class="form-inline">
              <el-form-item label="版本号" prop="edmmVer"  label-width="75px">
                <el-input v-model="formData.info.edmmVer" size="small" :disabled="!isEdit"></el-input>
              </el-form-item>
              <el-form-item label="研发部门" prop="edmmDevelopDept" label-width="75px">
                <el-input  v-model="formData.info.edmmDevelopDept"  size="small" :disabled="!isEdit"></el-input>
              </el-form-item>
              <el-form-item label="程序员" prop="edmmProgrammer" label-width="75px">
                <el-input v-model="formData.info.edmmProgrammer" size="small" :disabled="!isEdit" style="width: 250px"></el-input>
              </el-form-item>
            <!-- <el-form-item label="排序" prop="edmmSeq" label-width="75px">
              <el-input v-model.number="formData.info.edmmSeq"  size="small" :disabled="!isEdit"></el-input>
            </el-form-item> -->
            </div>
            <el-form-item  label="存储位置" size="small" style="margin-left: 2px" label-width="75px">
              <el-upload
                :action="CONFIG.baseUrl.upload+'attachments/upload'"
                :file-list="fileList"
                class="upload-demo"
                :multiple="false"
                :on-success="handleSuccess"
                size="small"
                ref="upload">
                <el-input
                  placeholder="存储位置"
                  v-model="formData.info.edmmProgramSourceName"
                  style="width:690px"
                  :disabled="true" size="small"
                >
                </el-input>
                <el-button size="small" type="operate" icon="rx-tianjia1" :disabled="!isEdit"></el-button>
              </el-upload>
            </el-form-item>
            <div style="display: block">
            <el-form-item label="触发条件" prop="edmmTriggerCond" label-width="75px">
              <el-input
                type="textarea"
                :disabled="!isEdit"
                :rows="2"
                placeholder="请输入内容"
                v-model="formData.info.edmmTriggerCond"
                style="width:690px"
              >
              </el-input>
            </el-form-item>
            <el-form-item label="业务描述" prop="edmmDesc" size="small" label-width="75px">
              <el-input
                type="textarea"
                :disabled="!isEdit"
                :rows="2"
                placeholder="请输入内容"
                v-model="formData.info.edmmDesc"
                style="width:690px">
              </el-input>
            </el-form-item>
            </div>
            <div style="display: block">
            <el-form-item label="算法描述" prop='edmmArithmeticDesc' size="small" label-width="75px">
              <el-input
                type="textarea"
                :disabled="!isEdit"
                :rows="2"
                placeholder="请输入内容"
                v-model="formData.info.edmmArithmeticDesc"
                style="width:690px">
              </el-input>
            </el-form-item>
            </div>
            <el-form-item  label="算法附件" size="small" style="margin-left: 2px" label-width="75px">
              <el-upload
                :action="CONFIG.baseUrl.upload+'attachments/upload'"
                :file-list="fileList"
                class="upload-demo"
                :multiple="false"
                :on-success="handleSuccess"
                size="small"
                ref="upload">
                <el-input
                  placeholder="算法附件"
                  v-model="formData.info.edmmArithmeticSourceName"
                  style="width:690px"
                  :disabled="true" size="small">
                </el-input>
                <el-button size="small" type="operate" icon="rx-tianjia1" :disabled="!isEdit"></el-button>
              </el-upload>
            </el-form-item>
            <el-form-item label="更新说明" prop="edmmUpdateDesc" label-width="75px">
              <el-input
                type="textarea"
                :disabled="!isEdit"
                :rows="2"
                placeholder="请输入内容"
                v-model="formData.info.edmmUpdateDesc"
                style="width:690px" size="small"
              >
              </el-input>
            </el-form-item>
            <div style="display: block">
            <el-form-item label="维护人" label-width="75px" style="width: 262px">
              {{formData.info.moduser}}
            </el-form-item>
            <el-form-item label="维护时间" label-width="75px" style="width: 262px">
              {{formData.info.modtimeStr}}
            </el-form-item>
            </div>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="输入参数">
          <div class="tab-button" >
              <el-button type="primary" icon="plus" @click="newInsertArg" :disabled="!isEdit">添加</el-button>
              <el-button type="default" icon="arrow-up" @click="turnUp" :disabled="!isEdit">上移</el-button>
              <el-button type="default" icon="arrow-down" @click="turnDown" :disabled="!isEdit">下移</el-button>
              <el-button type="danger" icon="delete2" @click="deleteInsertArg" :disabled="!isEdit">删除</el-button>
          </div>
          <el-table :data="formData.tableData" highlight-current-row v-loading="isSearching"
                    :height="$store.state.windowStore.windowHeight - 491"   @row-click="checkCurrent"
                    @selection-change="handleSelectionChange"  ref="currentTable">
            <el-table-column  type="selection"  width="55" align='center'  v-if="isEdit"></el-table-column>
            <el-table-column type="index" align="center" width="70" label="序号"></el-table-column>
            <el-table-column prop="edmaDataTypeName"  width="180" label="参数类型" align='center'></el-table-column>
            <el-table-column prop="edmaName" width="180" label="参数名称" align='center'>
              <template scope="scope">
                <el-button size="small" @click="editInputArg(scope.row)" type="text">{{scope.row.edmaName}}</el-button>
              </template>
            </el-table-column>
            <el-table-column prop="edmaDesc"  label="参数描述" align='center'></el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="返回值">
          <div >
          <el-form :model="formData.returnData"  ref="returnForm" >
            <el-form-item label="返回值名称" label-width="80px">
              <el-input   v-model="formData.returnData.edmaName"  size="small" style="width: 215px" :disabled="!isEdit"></el-input>
            </el-form-item>
            <el-form-item label="返回值类型"  label-width="80px">
              <el-select v-model="formData.returnData.edmaDataType" :disabled="!isEdit" filterable>
                <el-option v-for="options in allDict.edm_para_type" :key="options.id" :label="options.codeName" :value="options.codeValue"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="返回值描述"  label-width="80px" >
              <el-input   v-model="formData.returnData.edmaDesc"  size="small" style="width: 215px" :disabled="!isEdit"></el-input>
            </el-form-item>
          </el-form>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-form>
  </div>
</div>
</template>

<script>
import { functionService } from '@/api/services/method'
import methodClass from '@/components/method-manager/MethodClassify'
import InsertArgCreater from './InsertArgCreater.vue'
import chooseClasses from './ChooseClasses'
import { mapGetters } from 'vuex'
import { dictTypes } from '@/types'

export default {
  name: 'methodCreater',
  props: ['dataIn'],
  data () {
    return {
      isEdit: true,
      isMod: false,
      fileList: [],
      multipleSelection: [],
      isSearching: false,
      isUpdating: false,
      vmCho: '',
      tempData: {
        info: {
          id: '',
          edmmTypeName: '',
          edmmType: '',
          edmmName: '',
          edmmStatus: '',
          edmmProgramType: '',
          isCover: '',
          isPlatformProgram: '',
          edmmVer: '',
          edmmDevelopDept: '',
          edmmProgrammer: '',
          edmmSeq: '',
          edmmProgramSourceName: '',
          edmmProgramStorage: '',
          edmmDesc: '',
          edmmTriggerCond: '',
          edmmUpdateDesc: '',
          moduser: '',
          modtimeStr: '',
          edmmArithmeticDesc: '',
          edmmArithmeticSourceName: '',
          edmcName: '',
          edmcId: ''
        },
        tableData: [],
        returnData: {
          edmaName: '',
          edmaDataType: '',
          edmaDesc: ''
        }
      },
      formData: {
        info: {
          id: '',
          edmmTypeName: '',
          edmmType: '',
          edmmName: '',
          edmmStatus: '1',
          edmmProgramType: '',
          isCover: '',
          isPlatformProgram: '',
          edmmVer: '',
          edmmDevelopDept: '',
          edmmProgrammer: '',
          edmmSeq: '',
          edmmProgramSourceName: '',
          edmmProgramStorage: '',
          edmmDesc: '',
          edmmTriggerCond: '',
          edmmUpdateDesc: '',
          edmcName: '',
          edmcId: '',
          moduser: '',
          modtimeStr: ''
        },
        tableData: [],
        returnData: {
          edmaName: '',
          edmaDataType: '',
          edmaDesc: ''
        }
      },
      rules: {
        edmmType: [
          { required: true, message: '请选择方法分类', trigger: 'onchange' }
        ],
        edmmName: [
          { required: true, message: '请输入方法名称', trigger: 'blur' },
          { max: 50, message: '长度不能超过50', trigger: 'blur' },
          {
            validator: (rules, value, callback) => {
              // 与原数据相同
              if (this.dataIn.methodId && value === this.tempData.info.edmmName) {
                callback()
              } else {
                functionService.checkMethodName(value).then(() => {
                  // 检查通过
                  this.checkMethodSuccess = true
                  callback()
                }).catch(err => {
                  callback(err.message)
                })
              }
            },
            trigger: 'blur'
          }
        ],
        edmmProgramType: [
          { required: true, message: '请选择程序类型', trigger: 'change' }
        ],
        edmmDesc: [
          { max: 400, message: '长度不能超过400', trigger: 'blur' }
        ],
        edmmTriggerCond: [
          { max: 4000, message: '长度不能超过4000', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    // 获取数据字典
    this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_program_type', 'edm_para_type'])
    this.getMod()

   // console.log(JSON.stringify(this.formData))
  },
  computed: {
    ...mapGetters({
      allDict: dictTypes.GET_ALL_DICTS
    })
  },
  methods: {
    getMod () {
      if (this.dataIn.methodId) {
        this.isEdit = false
        this.isMod = true
       // console.log(JSON.stringify(this.dataIn.methodId))
        functionService.queryMethod(this.dataIn.methodId).then(data => {
         // console.log(JSON.stringify(data.edmMethod_show))
          if (data) {
            data.edmMethod_show.isCover = !!data.edmMethod_show.isCover
            data.edmMethod_show.isPlatformProgram = !!data.edmMethod_show.isPlatformProgram
            this.formData.info = data.edmMethod_show
            if (data.edmMethodInsertList_show) {
              this.formData.tableData = data.edmMethodInsertList_show
            }
            if (data.edmMethodreturn_show) {
              this.formData.returnData = data.edmMethodreturn_show
            }
            this.UTILS.setDataFromOther(this.tempData.info, this.formData.info)
          //  this.UTILS.setDataFromOther(this.tempData.tableData, this.formData.tableData)
            this.UTILS.setDataFromOther(this.tempData.returnData, this.formData.returnData)
           // console.log('...' + JSON.stringify(this.formData))
          }
        })
       // this.formData = this.dataIn.row
      }
    },
    save (formName) {
      if (!this.isUpdating) {
        this.isUpdating = true
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let edmMethodIn = {
              id: this.formData.info.id,
              edmmName: this.formData.info.edmmName,
              edmmType: this.formData.info.edmmType,
              edmmStatus: this.formData.info.edmmStatus,
              edmmProgramType: this.formData.info.edmmProgramType,
              isCover: this.formData.info.isCover ? 1 : 0,
              isPlatformProgram: this.formData.info.isPlatformProgram ? 1 : 0,
              edmmVer: this.formData.info.edmmVer,
              edmmDevelopDept: this.formData.info.edmmDevelopDept,
              edmmProgrammer: this.formData.info.edmmProgrammer,
              edmmSeq: this.formData.info.edmmSeq,
              edmmProgramSourceName: this.formData.info.edmmProgramSourceName,
              edmmProgramStorage: this.formData.info.edmmProgramStorage,
              edmmDesc: this.formData.info.edmmDesc,
              edmmTriggerCond: this.formData.info.edmmTriggerCond,
              edmmUpdateDesc: this.formData.info.edmmUpdateDesc,
              edmcId: this.formData.info.edmcId,
              edmcName: this.formData.info.edmcName
            }
            // console.log('111' + JSON.stringify(edmMethodIn))
            functionService.save({edmMethod_in: edmMethodIn, edmMethodInsertList_in: this.formData.tableData, edmMethodreturn_in: this.formData.returnData}).then(() => {
              // 保存成功，触发父组件事件
              this.$message.success('保存成功！')
              this.$emit('updateSuccess')
              this.isUpdating = false
              this.isEdit = !this.isEdit
            }).catch(() => {
              this.isUpdating = false
            })
          } else {
            this.isUpdating = false
            return false
          }
        })
      }
    },
    insert (formName) {
      if (!this.isUpdating) {
        this.isUpdating = true
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let edmMethodIn = {
              id: this.formData.info.id,
              edmmName: this.formData.info.edmmName,
              edmmType: this.formData.info.edmmType,
              edmmStatus: this.formData.info.edmmStatus,
              edmmProgramType: this.formData.info.edmmProgramType,
              isCover: this.formData.info.isCover ? 1 : 0,
              isPlatformProgram: this.formData.info.isPlatformProgram ? 1 : 0,
              edmmVer: this.formData.info.edmmVer,
              edmmDevelopDept: this.formData.info.edmmDevelopDept,
              edmmProgrammer: this.formData.info.edmmProgrammer,
              edmmSeq: this.formData.info.edmmSeq,
              edmmProgramSourceName: this.formData.info.edmmProgramSourceName,
              edmmProgramStorage: this.formData.info.edmmProgramStorage,
              edmmDesc: this.formData.info.edmmDesc,
              edmmTriggerCond: this.formData.info.edmmTriggerCond,
              edmmUpdateDesc: this.formData.info.edmmUpdateDesc,
              edmcId: this.formData.info.edmcId,
              edmcName: this.formData.info.edmcName
            }
            // console.log('111' + JSON.stringify(edmMethodIn))
            functionService.insert({edmMethod_in: edmMethodIn, edmMethodInsertList_in: this.formData.tableData, edmMethodreturn_in: this.formData.returnData}).then(() => {
              // 保存成功，触发父组件事件
              this.$message.success('新增成功！')
              this.$emit('createSuccess')
              this.isUpdating = false
            }).catch(() => {
              this.isUpdating = false
            })
          } else {
            this.isUpdating = false
            return false
          }
        })
      }
    },
    deleteMethod () {
      if (!this.isDeleting) {
        this.$confirm('确认删除？', '', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.isDeleting = true
          functionService.deleteMethod(this.formData.info.id).then(() => {
            // 删除更改
            this.$message.success('删除成功！')
            this.$emit('deleteSuccess')
          }).catch(() => {
            this.isDeleting = false
          })
        })
      }
    },
    cancel () {
      this.isEdit = !this.isEdit
      this.getMod()
     // console.log('...' + JSON.stringify(this.tempData.info))
    },
    // 选中行，自动选中复选框
    checkCurrent (row) {
      this.$refs.currentTable.toggleRowSelection(row)
    },
    // 选中高亮样式
    tableCheckedClass (row, index) {
      if (this.multipleSelection.indexOf(row) >= 0) {
        return 'table-checked'
      }
      return ''
    },
    handleSelectionChange (val) {
      val.sort((a, b) => this.formData.tableData.indexOf(a) > this.formData.tableData.indexOf(b))
      this.multipleSelection = val
      // console.log(JSON.stringify(this.multipleSelection))
    },
    ChangeStatus () {
      if (this.formData.info.edmmStatus === '1') {
        this.formData.info.edmmStatus = '0'
      } else {
        this.formData.info.edmmStatus = '1'
      }
    },
    handleSuccess (response, file, fileList) {
     // console.log(JSON.stringify(response))
      this.formData.info.edmmProgramSourceName = response.data.edmaSourceName
      this.formData.info.edmmProgramStorage = response.data.edmaPath
    },
    deleteInsertArg () {
      if (this.multipleSelection.length === 0) {
        this.$message.warning('至少选中一行！')
      } else {
        for (let index = 0; index < this.multipleSelection.length; index++) {
          const item = this.multipleSelection[index]
          // 位置
          const position = this.formData.tableData.indexOf(item)
          this.formData.tableData.splice(position, 1)
        }
      }
    },
    turnUp () {
      if (this.multipleSelection.length === 0) {
        this.$message.warning('至少选中一行！')
      } else {
        for (let index = 0; index < this.multipleSelection.length; index++) {
          const item = this.multipleSelection[index]
          // 位置
          const position = this.formData.tableData.indexOf(item)
          if (position > index) {
            // 与前一位数据交换
            this.formData.tableData.splice(position, 1)
            this.formData.tableData.splice(position - 1, 0, item)
          }
        }
      }
    },
    turnDown () {
      if (this.multipleSelection.length === 0) {
        this.$message.warning('至少选中一行！')
      } else {
        for (let index = 0; index < this.multipleSelection.length; index++) {
          const item = this.multipleSelection[this.multipleSelection.length - index - 1]
          // 位置
          const position = this.formData.tableData.indexOf(item)
          if (position < this.formData.tableData.length - index - 1) {
            // 与后一位数据做交换
            this.formData.tableData.splice(position, 1)
            this.formData.tableData.splice(position + 1, 0, item)
          }
        }
      }
    },
    newInsertArg () {
      this.$openDialog({
        name: 'InsertArg-dialog',
        component: InsertArgCreater,
        options: {
          title: '输入参数',
          customClass: 'dialog-width-m'
        },
        props: {
          data: this.formData.tableData
        },
        buttons: [{
          text: '确定',
          icon: 'check',
          type: 'primary',
          handler: () => {
            this.vmPro.getResult().then((res) => {
             // console.log(JSON.stringify(res))
              this.formData.tableData.push(res)
              this.$closeDialog('InsertArg-dialog')
            })
          }
        }, {
          text: '取消',
          icon: 'close',
          handler: () => {
            this.$closeDialog('InsertArg-dialog')
          }
        }]
      }).then(vm => {
        this.vmPro = vm
      })
    },
    editInputArg (row) {
      this.$openDialog({
        name: 'EditArg-dialog',
        component: InsertArgCreater,
        options: {
          title: '输入参数',
          customClass: 'dialog-width-m'
        },
        props: {
          data: this.formData.tableData,
          row: row
        },
        buttons: [{
          text: '确定',
          icon: 'check',
          type: 'primary',
          handler: () => {
            this.vmPro.getResult().then((res) => {
            //  console.log(JSON.stringify(res))
              row = res
              this.$closeDialog('EditArg-dialog')
            })
          }
        }, {
          text: '取消',
          icon: 'close',
          handler: () => {
            this.$closeDialog('EditArg-dialog')
          }
        }]
      }).then(vm => {
        this.vmPro = vm
      })
    },
    openTree: function () {
      this.$openDialog({
        name: 'tree-dialog',
        component: methodClass,
        options: {
          title: '方法分类',
          customClass: 'dialog-width-s'
        },
        events: {
         // changeType: this.changeType
          confirmNode: this.confirmNode
        },
        buttons: [{
          text: '确定',
          icon: 'check',
          type: 'primary',
          handler: () => {
            const result = this.vmPro.getResult()
            if (result) {
              this.formData.info.edmmTypeName = result.name
              this.formData.info.edmmType = result.id
              this.$closeDialog('tree-dialog')
              // console.log(JSON.stringify(result) + '...')
            }
            // console.log('确定')
          }
        }, {
          text: '分类维护',
          handler: () => {
            this.vmPro.methodClassficationMain()
          }
        }, {
          text: '取消',
          icon: 'close',
          handler: () => {
            this.$closeDialog('tree-dialog')
          }
        }]
      }).then(vm => {
        this.vmPro = vm
      })
    },
    confirmNode (data) {
      this.formData.info.edmmTypeName = data.name
      this.formData.info.edmmType = data.id
      this.$closeDialog('tree-dialog')
    },
    chooseClasses () {
      this.$openDialog({
        name: 'choose-class',
        component: chooseClasses,
        options: {
          title: '选择所属类',
          customer: 'dialog-width-s'
        },
        props: {
          edmcId: this.formData.info.edmcId
        },
        buttons: [{
          text: '确定',
          icon: 'check',
          type: 'primary',
          handler: () => {
            // console.log('this.vmCho', this.vmCho)
            const result = this.vmCho.getResult()
            if (result) {
              // 多选。循环？
              // this.pageData.edmm_class_name = JSON.parse(JSON.stringify(result.names))
              this.formData.info.edmcName = result.names.toString()
              this.formData.info.edmcId = result.ids.toString()
              this.$closeDialog('choose-class')
            }
            // console.log(this.formData.info.edmcName, this.formData.info.edmcId)
          }
        }, {
          text: '取消',
          icon: 'close',
          handler: () => {
            this.$closeDialog('choose-class')
          }
        }]
      }).then(vm => {
        this.vmCho = vm
      })
    }
  }
}
</script>

<style scoped>
.top-button{
  margin: 0 0 20px 0;
}
.tab-button{
  text-align: right;
  height: 38px;
}
.method-form .form-inline .el-input, .el-select{
    width: 133px;
  }
</style>
