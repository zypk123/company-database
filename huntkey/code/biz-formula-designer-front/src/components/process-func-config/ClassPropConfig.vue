<template>
  <div class="rel-condition-cond-edit" v-loading="!pageConfig.pageIniting" element-loading-text="正在初始化页面">
    <!--按钮区域-->
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save" :loading="pageConfig.isSaving">保存</el-button>
      <el-button icon="cancel" @click="close">取消</el-button>
    </div>

    <!--表单区域-->
    <div class="form-area-style">
      <el-form label-width="55px" :model="formData" ref="form" :rules="formRules">
        <!--<el-form-item label="类型">-->
          <!--<el-select v-model="formData.classTypeFlag" class="input-width-full">-->
            <!--<el-option v-for="item in classTypeFlagDict"-->
                       <!--:label="item.label"-->
                       <!--:value="item.val"-->
                       <!--:key="item.val">-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->

        <el-form-item label="类" prop="classId" v-if="formData.classTypeFlag===1">
          <el-select v-model="formData.classId" @change="classChange" class="input-width-full">
            <el-option v-for="item in pageCommon.classes"
                       :label="item.edmcName"
                       :value="item.id"
                       :key="item.id" >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="属性：" prop="classPropId" v-if="formData.classTypeFlag===1">
        <el-cascader
        :key="1"
        v-model="formData.classPropId"
        class="input-width-full"
        placeholder="请选择"
        :options="pageCommon.classProps"
        filterable
        :props="filterData"
        ></el-cascader>
        </el-form-item>

        <el-form-item label="相关类" prop="relClassId" v-if="formData.classTypeFlag===2">
          <el-select v-model="formData.relClassId" @change="relClassChange" class="input-width-full">
            <el-option v-for="item in pageCommon.relClasses"
                       :label="item.clssClassRelatedName"
                       :value="item.clssClassId"
                       :key="item.clssClassId">
            </el-option>
          </el-select>
          <i class="el-icon-plus default-color" @click="showRelClassEditDlg"></i>
        </el-form-item>

        <el-form-item label="属性：" prop="relClassPropId" v-if="formData.classTypeFlag===2">
          <el-cascader
            :key="2"
            v-model="formData.relClassPropId"
            class="input-width-full"
            placeholder="请选择"
            :options="pageCommon.relClassProps"
            filterable
            :props="filterData"
          ></el-cascader>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>
<script>
  import _ from 'lodash'
  import { formulaService } from '@/api'
  import { mapGetters } from 'vuex'
  import ProcessRelEdmClassEdit from './ProcessRelEdmClassEdit'
  export default {
    name: 'classPropConfig',
    props: ['initData'],
    data () {
      let checkClassPropId = (rule, value, callback) => {
        if (_.isEmpty(value)) {
          return callback(new Error('属性不能为空'))
        }
        callback()
      }
      let checkRelClassPropId = (rule, value, callback) => {
        if (_.isEmpty(value)) {
          return callback(new Error('属性不能为空'))
        }
        callback()
      }
      return {
        initFlag: 0,
        initFlag2: 0,
        pageCommon: {
          classes: [],
          relClasses: [],
          classProps: [],
          relClassProps: []
        },
        filterData: {
          label: 'edmpName',
          value: 'addProp',
          children: 'children'
        },
        pageConfig: {
          pageIniting: true,
          isSaving: false
        },
        formData: {
          classTypeFlag: 1,
          classId: '',
          classPropId: [],
          relClassId: '',
          relClassPropId: []
        },
        formRules: {
          classId: [
            {required: true, message: '类不能为空', trigger: 'blur'}
          ],
          classPropId: [
            { validator: checkClassPropId, trigger: 'blur' }
          ],
          relClassId: [
            {required: true, message: '相关类不能为空', trigger: 'blur'}
          ],
          relClassPropId: [
            { validator: checkRelClassPropId, trigger: 'blur' }
          ]
        }
      }
    },
    mounted () {
      this.init()
    },
    methods: {
      init () {
        this.pageCommon.classes = this.initData.classes
        // this.relClassNewProp(this.initData.relClasses)
        this.pageCommon.relClasses = this.initData.relClasses
        console.log(this.initData)
        if (this.initData.cond) {
          this.formData.classTypeFlag = this.initData.cond.classTypeFlag
          if (this.initData.cond.classTypeFlag === 1) {
            this.formData.classId = this.initData.cond.classId
            this.formData.classPropId = this.initData.cond.classPropId
          } else {
            this.formData.relClassId = this.initData.cond.classId
            console.log('编辑初始化', this.formData.relClassId)
            this.getPropsbyRelClassId(this.formData.relClassId)
            // this.formData.relClassPropId = this.initData.cond.classPropId
          }
        }
      },
      getPropsbyRelClassId (relClassId) {
        formulaService.queryEdmClassPropsByClassID({
          edmcId: relClassId
        }).then((resData) => {
          // console.log('test', resData)
          this.addObjProp(resData)
          this.pageCommon.relClassProps = resData
          this.formData.relClassPropId = this.initData.cond.classPropId
        }).catch((error) => {
          this.AppUtils.showWarning(error.message)
        })
      },
      getRelKeyIdByRelClassId (relClassId) {
        for (let i = 0; i < this.pageCommon.relClasses.length; i++) {
          if (relClassId === this.pageCommon.relClasses[i].clssClassId) {
            return this.pageCommon.relClasses[i].clssId
          }
        }
        return 'unknow'
      },
      save () {
        this.$refs.form.validate((valid) => {
          if (valid) {
            let condData = {}
            condData.classTypeFlag = this.formData.classTypeFlag
            if (this.formData.classTypeFlag === 1) {
              condData.classId = this.formData.classId
              condData.classPropId = this.formData.classPropId
              condData.cndrPropValue = this.getClassNameByClassId(this.formData.classId)
              condData.cndrClass1NameEn = this.getClassNameEnByClassId(this.formData.classId)
            } else {
              console.log('相关类Id', this.formData.relClassId)
              condData.linkClssId = this.getRelKeyIdByRelClassId(this.formData.relClassId)
              condData.classId = this.formData.relClassId
              condData.classPropId = this.formData.relClassPropId
              condData.cndrPropValue = this.getRelClassNameByRelClassId(this.formData.relClassId)
              condData.cndrClass1NameEn = this.getRelClassNameEnByRelClassId(this.formData.relClassId)
            }
            // 保存数据
            this.$emit('saveCond', condData)
            this.$emit('close')
          } else {
            this.$message({
              message: '校验不通过',
              type: 'warning'
            })
          }
        })
      },
      getClassNameEnByClassId (classId) {
        for (let i = 0; i < this.pageCommon.classes.length; i++) {
          if (classId === this.pageCommon.classes[i].id) {
            return this.pageCommon.classes[i].edmcNameEn
          }
        }
        return 'unknow'
      },
      getClassNameByClassId (classId) {
        for (let i = 0; i < this.pageCommon.classes.length; i++) {
          if (classId === this.pageCommon.classes[i].id) {
            return this.pageCommon.classes[i].edmcName
          }
        }
        return '未知类'
      },
      getRelClassNameEnByRelClassId (relClassId) {
        for (let i = 0; i < this.pageCommon.relClasses.length; i++) {
          if (relClassId === this.pageCommon.relClasses[i].clssClassId) {
            return this.pageCommon.relClasses[i].clssEdmcNameEn
          }
        }
        return 'unknow'
      },
      getRelClassNameByRelClassId (relClassId) {
        for (let i = 0; i < this.pageCommon.relClasses.length; i++) {
          if (relClassId === this.pageCommon.relClasses[i].clssClassId) {
            return this.pageCommon.relClasses[i].clssClassRelatedName
          }
        }
        return '未知类'
      },
      close () {
        this.$emit('close')
      },
      relClassChange (relClassId) {
        console.log(this.initFlag2)
        console.log('相关类改变！')
        let tempClassId = relClassId
        if (this.initFlag2 === 0) {
          this.initFlag2 += 1
          formulaService.queryEdmClassPropsByClassID({
            edmcId: tempClassId
          }).then((resData) => {
            // console.log('test', resData)
            this.addObjProp(resData)
            this.pageCommon.relClassProps = resData
          }).catch((error) => {
            this.AppUtils.showWarning(error.message)
          })
        } else {
          this.formData.relClassPropId = []
          formulaService.queryEdmClassPropsByClassID({
            edmcId: tempClassId
          }).then((resData) => {
            // console.log('test', resData)
            this.addObjProp(resData)
            this.pageCommon.relClassProps = resData
          }).catch((error) => {
            this.AppUtils.showWarning(error.message)
          })
        }
      },
      classChange (classId) {
        if (this.initFlag === 0) {
          this.initFlag += 1
          formulaService.queryEdmClassPropsByClassID({
            edmcId: classId
          }).then((resData) => {
            // console.log('test', resData)
            this.addObjProp(resData)
            this.pageCommon.classProps = resData
          }).catch((error) => {
            this.AppUtils.showWarning(error.message)
          })
        } else {
          this.formData.classPropId = []
          formulaService.queryEdmClassPropsByClassID({
            edmcId: classId
          }).then((resData) => {
            // console.log('test', resData)
            this.addObjProp(resData)
            this.pageCommon.classProps = resData
          }).catch((error) => {
            this.AppUtils.showWarning(error.message)
          })
        }
      },
      addObjProp (nodes) {
        nodes.forEach(
          (item, index, arr) => {
            item.addProp = item.id + '+' + item.casEdmpCode + '+' + item.casEdmpName
            if (item.children && item.children.length > 0) {
              this.addObjProp(item.children)
            }
          }
        )
      },
      showRelClassEditDlg () {
        this.addRelClass()
      },
      addRelClass () {
        let title = '相关类设置'
        let mode = 'add'
        formulaService.getNewRelClass(this.initData.classId).then((resData) => {
          // console.log('response', resData)
          this.openCondEditDialog({title, resData, mode})
        }).catch((error) => {
          console.log(error)
          this.AppUtils.showError('初始化相关类信息时发生网络错误')
        })
      },
      openCondEditDialog ({title, resData, mode}) {
        this.OpenGlobalDialog({
          name: 'processRelEdmClassEdit',
          component: ProcessRelEdmClassEdit,
          title: title,
          props: {
            classId: this.initData.classId,
            mode: mode,
            relClasses: this.pageCommon.relClasses,
            relClass: resData,
            variableData: this.initData.variableData,
            classes: this.initData.classes
          },
          options: {
            title: title,
            customClass: 'subpage-dlg dialog-width-xxl'
          },
          events: {
            relClassUpdated: this.updateRelClass,
            close: () => {
              this.CloseGlobalDialog('processRelEdmClassEdit')
            }
          }
        })
      },
//      relClassNewProp (nodes) {
//        nodes.forEach(
//          (item, index, arr) => {
//            item.newProp = item.clssId + '+' + item.clssClassId
//          }
//        )
//      },
      updateRelClass (newRelEdmClassInfo) {
        console.log(newRelEdmClassInfo)
        if (newRelEdmClassInfo.isenable === 1) {
          // newRelEdmClassInfo.newProp = newRelEdmClassInfo.clssId + '+' + newRelEdmClassInfo.clssClassId
          this.pageCommon.relClasses.push(newRelEdmClassInfo)
          this.$emit('updateRelClass')
        } else {
          console.log('锁定类')
        }
      }
    },
    computed: {
      ...mapGetters({
        classTypeFlagDict: 'getClassTypeFlagDict'
      })
    }
  }
</script>
<style scoped>
.form-area-style{
  margin-top: 10px;
}
</style>
