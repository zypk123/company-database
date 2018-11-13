<template>
  <div class="rel-condition-cond-edit" v-loading="!pageConfig.pageIniting" element-loading-text="正在初始化页面">
    <!--按钮区域-->
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save" :loading="pageConfig.isSaving">保存</el-button>
      <el-button @click="restForm">重置</el-button>
      <el-button icon="cancel" @click="close">取消</el-button>
    </div>

    <!--表单区域-->
    <div class="form-area">
      <el-form label-width="90px" :model="formData" ref="form" :rules="formRules">
        <el-form-item label="方式" prop="methodTypeFlag">
          <el-select v-model="formData.methodTypeFlag" @change="methodTypeFlagChange" style="width:90%" placeholder="请选择">
            <el-option v-for="item in methodTypeFlagDict"
                       :label="item.content"
                       :value="item.id"
                       :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <!--<el-form-item label="条件">-->
          <!--<el-input-->
            <!--icon="plus"-->
            <!--style="width:90%"-->
            <!--v-model="formData.cndrOperate"-->
            <!--@focus="handleIconClick"-->
            <!--:on-icon-click="handleIconClick">-->
          <!--</el-input>-->
        <!--</el-form-item>-->

        <!--数组list变为树形结构-->
        <el-form-item ref="assignPost" label="角色人员" prop="assignPost" v-if="getMarkById(formData.methodTypeFlag)==='1'">
          <el-cascader key="1"
            v-model="formData.assignPost"
            style="width:90%"
            placeholder="请选择"
            :options="assignPosts"
            filterable
            :props="filterData2"
            change-on-select
          ></el-cascader>
        </el-form-item>

        <el-form-item ref="assignDepartment" label="角色人员" prop="assignDepartment" v-if="getMarkById(formData.methodTypeFlag)==='2'">
          <el-cascader key="2"
            v-model="formData.assignDepartment"
            style="width:90%"
            placeholder="请选择"
            :options="assignDepartments"
            filterable
            :props="filterData"
            change-on-select
          ></el-cascader>
        </el-form-item>

        <el-form-item label="岗位" prop="selectBranch" v-if="getMarkById(formData.methodTypeFlag)==='2'">
          <el-select v-model="formData.selectBranch"  style="width:90%">
            <el-option v-for="item in departmentBranchs"
                       :label="item.postName"
                       :value="item.val"
                       :key="item.val" >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="岗位" prop="commonSelecter" v-if="getMarkById(formData.methodTypeFlag)==='3'">
          <el-select v-model="formData.commonSelecter"  style="width:90%">
            <el-option v-for="item in commonLists"
                       :label="item.postName"
                       :value="item.val"
                       :key="item.val" >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="部门" prop="commonSelecter" v-if="getMarkById(formData.methodTypeFlag)==='4'">
          <el-select v-model="formData.commonSelecter"  style="width:90%">
            <el-option v-for="item in departmentLists"
                       :label="item.postName"
                       :value="item.val"
                       :key="item.val" >
            </el-option>
          </el-select>
        </el-form-item>

        <!--指定岗位条件框置灰-->
        <el-form-item label="条件">
          <el-input
            readonly
            :disabled="pageConfig.postFlag"
            style="width:90%"
            v-model="formData.cndrOperate"
            @focus="handleIconClick">
          </el-input>
        </el-form-item>

        <!--<el-form-item label="角色人员" prop="appointedPost">-->
          <!--<el-select v-model="formData.appointedPost"  style="width:90%">-->
            <!--<el-option v-for="item in appointedPosts"-->
                       <!--:label="item.rpos_ppost"-->
                       <!--:value="item.jobId"-->
                       <!--:key="item.jobId">-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->
      </el-form>
    </div>
  </div>
</template>
<script>
  import _ from 'lodash'
  // import { mapGetters } from 'vuex'
  import { auditService } from '@/api'
  import ConditionConfigEdit from './ConditionConfigEdit'
  export default {
    name: 'auditConditionConfigEdit',
    props: ['initData'],
    data () {
      let departmentChecker = (rule, value, callback) => {
        if (_.isEmpty(value)) {
          return callback(new Error('值不能为空'))
        }
        callback()
      }
      let postChecker = (rule, value, callback) => {
        if (_.isEmpty(value)) {
          return callback(new Error('值不能为空'))
        }
        callback()
      }
      return {
        initFlag: 0,
        pageCommon: {
          cndrIndex: 0
        },
        pageConfig: {
          pageIniting: true,
          pageInitText: '正在初始化页面',
          isSaving: false,
          postFlag: false
        },
        formData: {
          aditId: '',
          alternateField4: '',
          methodTypeFlag: '',
          postOffice: '',
          assignDepartment: [],
          appointedPost: '',
          appointedDepartment: '',
          assignPost: [],
          cndrOperate: '',
          commonSelecter: '',
          selectBranch: '',
          postOperate: '',
          postValue: '',
          deptOperate: '',
          deptValue: ''
        },
        formRules: {
          methodTypeFlag: [
            {required: true, message: '请选择单据关联的资源属性', trigger: 'blur'}
          ],
          commonSelecter: [
            {required: true, message: '值不能为空', trigger: 'blur'}
          ],
          selectBranch: [
            {required: true, message: '值不能为空', trigger: 'blur'}
          ],
          postOffice: [
            {required: true, message: '值不能为空', trigger: 'blur'}
          ],
          assignDepartment: [
            { type: 'array', required: true, message: '值不能为空', trigger: 'blur' },
            { validator: departmentChecker }
          ],
          appointedPost: [
            {required: true, message: '值不能为空', trigger: 'blur'}
          ],
          appointedDepartment: [
            {required: true, message: '值不能为空', trigger: 'blur'}
          ],
          assignPost: [
            { type: 'array', required: true, message: '值不能为空', trigger: 'blur' },
            { validator: postChecker }
          ]
        },
        departmentBranchs: [{
          postName: '主责岗位',
          val: '02'
        }, {
          postName: '所有岗位',
          val: '01'
        }],
        cond: {},
        departmentLists: [{
          postName: '上上级主责岗位',
          val: '上上级主责岗位'
        }, {
          postName: '上级主责岗位',
          val: '上级主责岗位'
        }, {
          postName: '主责岗位',
          val: '主责岗位'
        }],
        commonLists: [{
          postName: '上上级岗位',
          val: '上上级岗位'
        }, {
          postName: '上级岗位',
          val: '上级岗位'
        }, {
          postName: '当前岗位',
          val: '当前岗位'
        }],
        assignDepartments: [],
        assignPosts: [],
        eqLevelList: [],
        eqLevelList2: [],
        methodTypeFlagDict: [],

        filterData: {
          label: 'moniNodeName',
          value: 'deptId',
          children: 'childDept'
        },
        filterData2: {
          label: 'rposName',
          value: 'jobId',
          children: 'childPosition'
        }
      }
    },
    mounted () {
      this.init()
    },
    methods: {
      init () {
        this.pageCommon.cndrIndex = this.initData.index
        this.methodTypeFlagDict = this.initData.methodTypeFlagDict
        console.log(this.initData.cond)
        if (this.initData.cond) {
          this.formData.postOperate = this.initData.cond.postOperate
          this.formData.postValue = this.initData.cond.postValue
          this.formData.deptOperate = this.initData.cond.deptOperate
          this.formData.deptValue = this.initData.cond.deptValue

          this.formData.alternateField4 = this.initData.cond.alternateField4
          this.formData.aditId = this.initData.cond.aditId
          this.formData.methodTypeFlag = this.initData.cond.alternateField1
          this.formData.cndrOperate = this.initData.cond.auditCondition
          let methodName = this.initData.cond.mark
          if (methodName === '1') {
            this.formData.assignPost = this.initData.cond.alternateField2.split('.')
          } else if (methodName === '2') {
            this.formData.assignDepartment = this.initData.cond.alternateField2.split('.')
            this.formData.selectBranch = this.initData.cond.alternateField3
          } else {
            this.formData.commonSelecter = this.initData.cond.alternateField3
          }
        }
      },
      getAssignDepartmentName (id) {
        for (let i = 0; i < this.eqLevelList.length; i++) {
          if (id === this.eqLevelList[i].deptId) {
            return this.eqLevelList[i].moniNodeName
          }
        }
        return '未知'
      },
      changeTreeToArray (nodes) {
        nodes.forEach(
          (item, index, arr) => {
            this.eqLevelList.push(item)
            if (item.childDept && item.childDept.length > 0) {
              this.changeTreeToArray(item.childDept)
            }
          }
        )
      },
      getPostName (id) {
        for (let i = 0; i < this.eqLevelList2.length; i++) {
          if (id === this.eqLevelList2[i].jobId) {
            return this.eqLevelList2[i].rposName
          }
        }
        return '未知'
      },
      changeTreeToArray2 (nodes) {
        nodes.forEach(
          (item, index, arr) => {
            this.eqLevelList2.push(item)
            if (item.childPosition && item.childPosition.length > 0) {
              this.changeTreeToArray2(item.childPosition)
            }
          }
        )
      },
      /**
       * 保存
       */
      save () {
        this.$refs.form.validate((valid) => {
          if (valid) {
            // 类Id
            let condData = {}
            condData.isenable = 1
            condData.postOperate = this.formData.postOperate
            condData.postValue = this.formData.postValue
            condData.deptOperate = this.formData.deptOperate
            condData.deptValue = this.formData.deptValue
            condData.aditId = this.formData.aditId
            condData.alternateField4 = this.formData.alternateField4
            condData.auditCondition = this.formData.cndrOperate
            condData.alternateField1 = this.formData.methodTypeFlag
            let tempName = this.getMarkById(this.formData.methodTypeFlag)
            condData.mark = tempName
            if (tempName === '1') {
              condData.auditPattern = '指定岗位'
              condData.alternateField2 = _.join(this.formData.assignPost, '.')
              // 调用递归
              this.changeTreeToArray2(this.assignPosts)
              // 循环查出各级名称并拼接
              let casTreeName = ''
              for (let i = 0; i < this.formData.assignPost.length; i++) {
                if (i === 0) {
                  casTreeName = this.getPostName(this.formData.assignPost[0])
                } else {
                  casTreeName = casTreeName + '/' + this.getPostName(this.formData.assignPost[i])
                }
              }
              condData.auditRolesStaff = casTreeName
              condData.beiyong4 = ''
//              let lastId = this.formData.assignPost[this.formData.assignPost.length - 1]
//              condData.auditRolesStaff = this.getPostName(lastId)
            } else if (tempName === '2') {
              condData.auditPattern = '指定部门'
              condData.alternateField2 = _.join(this.formData.assignDepartment, '.')
              // 调用递归
              this.changeTreeToArray(this.assignDepartments)
              condData.alternateField3 = this.formData.selectBranch
              let branchName = ''
              if (this.formData.selectBranch === '01') {
                branchName = '所有岗位'
              } else {
                branchName = '主责岗位'
              }
              // 循环查出各级名称并拼接
              let casTreeName2 = ''
              for (let i = 0; i < this.formData.assignDepartment.length; i++) {
                if (i === 0) {
                  casTreeName2 = this.getAssignDepartmentName(this.formData.assignDepartment[0])
                } else {
                  casTreeName2 = casTreeName2 + '/' + this.getAssignDepartmentName(this.formData.assignDepartment[i])
                }
              }
              condData.auditRolesStaff = casTreeName2 + '/' + branchName
              condData.beiyong4 = ''
//              let lastId = this.formData.assignDepartment[this.formData.assignDepartment.length - 1]
//              condData.auditRolesStaff = this.getAssignDepartmentName(lastId)
            } else {
              // 可以将角色人员Id和名称更改为空字符串而不是null
              condData.chooseCode = this.getCodeById(this.formData.methodTypeFlag)
              condData.auditPattern = this.getNameById(this.formData.methodTypeFlag)
              condData.alternateField3 = this.formData.commonSelecter
              condData.auditRolesStaff = condData.alternateField3
              condData.beiyong4 = this.getClassIdById(this.formData.methodTypeFlag)
            }
            condData.auditSeq = this.pageCommon.cndrIndex + 1
            let index = this.pageCommon.cndrIndex
            this.$emit('saveCond', {index, condData})
            this.$emit('close')
          } else {
            console.log('校验不通过！')
          }
        })
      },
      getClassIdById (id) {
        for (let i = 0; i < this.methodTypeFlagDict.length; i++) {
          if (id === this.methodTypeFlagDict[i].id) {
            return this.methodTypeFlagDict[i].classId
          }
        }
        return ''
      },
      getCodeById (id) {
        for (let i = 0; i < this.methodTypeFlagDict.length; i++) {
          if (id === this.methodTypeFlagDict[i].id) {
            return this.methodTypeFlagDict[i].code
          }
        }
        return ''
      },
      /**
       * 关闭按钮方法
       */
      close () {
        this.$emit('close')
      },
      restForm () {
        this.$refs['form'].resetFields()
        this.formData.postOperate = ''
        this.formData.postValue = ''
        this.formData.deptOperate = ''
        this.formData.deptValue = ''
      },
      /**
       * 值类型下拉框发生改变
       */
      methodTypeFlagChange (value) {
        this.pageConfig.postFlag = false
        // 编辑新增初始化
        if (this.initFlag === 0) {
          console.log('初始化')
          this.initFlag += 1
          let method = this.getMarkById(value)
          if (method === '1') {
            this.pageConfig.postFlag = true
            auditService.getRolePersonList({mark: method}).then((resData) => {
              console.log('rolePersonList', resData)
              this.assignPosts = resData
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          } else if (method === '2') {
            auditService.getRolePersonList({mark: method}).then((resData) => {
              console.log('rolePersonList', resData)
              this.assignDepartments = resData
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          }
        } else {
          console.log('第二次触发')
          this.formData.cndrOperate = ''
          this.formData.assignPost = []
          this.formData.assignDepartment = []
          this.formData.commonSelecter = ''
          this.formData.selectBranch = ''
          let method = this.getMarkById(value)
          if (method === '1') {
            this.pageConfig.postFlag = true
            auditService.getRolePersonList({mark: method}).then((resData) => {
              console.log('rolePersonList', resData)
              this.assignPosts = resData
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          } else if (method === '2') {
            auditService.getRolePersonList({mark: method}).then((resData) => {
              console.log('rolePersonList', resData)
              this.assignDepartments = resData
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          }
        }
        this.$nextTick(() => {
          let method = this.getMarkById(value)
          if (method === '1') {
            this.$refs.assignPost.resetField()
          } else if (method === '2') {
            this.$refs.assignDepartment.resetField()
          }
        })
      },
      handleIconClick () {
        if (this.formData.cndrOperate) {
          this.editProp()
        } else {
          this.addNewProp()
        }
      },
      addNewProp () {
        let title = '条件设置'
        this.openCondEditDialog({title})
      },
      /**
       * 编辑条件
       */
      editProp () {
        let title = '条件设置'
        let cond = this.formData.cndrOperate
        this.openCondEditDialog({title, cond})
      },
      getMarkById (id) {
        for (let i = 0; i < this.methodTypeFlagDict.length; i++) {
          if (id === this.methodTypeFlagDict[i].id) {
            return this.methodTypeFlagDict[i].mark
          }
        }
        return ''
      },
      getNameById (id) {
        for (let i = 0; i < this.methodTypeFlagDict.length; i++) {
          if (id === this.methodTypeFlagDict[i].id) {
            return this.methodTypeFlagDict[i].content
          }
        }
        return ''
      },
      /**
       * 打开新增/编辑条件弹框
       */
      openCondEditDialog ({title, cond}) {
        this.OpenGlobalDialog({
          name: 'conditionConfigEdit',
          component: ConditionConfigEdit,
          title: title,
          props: {
            cond: cond
          },
          options: {
            title: title,
            top: '20%',
            customClass: 'subpage-dlg dialog-width-m'
          },
          events: {
            saveCond: this.saveCond,
            close: () => {
              this.CloseGlobalDialog('conditionConfigEdit')
            }
          }
        })
      },
      /**
       * 保存条件
       */
      saveCond (condData) {
        console.log(condData)
        // this.classPropInfo = condData
        this.formData.postOperate = condData.cndrOperate1
        this.formData.postValue = condData.postLevel
        this.formData.deptOperate = condData.cndrOperate2
        this.formData.deptValue = condData.departmentLevel
        this.formData.cndrOperate = `${condData.post} ${condData.cndrOperate1} ${condData.postLevel} 并且 ${condData.department} ${condData.cndrOperate2} ${condData.departmentLevel}`
      }
    },
    computed: {
    }
  }
</script>
<style>

</style>
