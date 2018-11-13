<template>
  <div class="rel-condition-cond-edit" v-loading="!pageConfig.pageIniting" element-loading-text="正在初始化页面">
    <!--按钮区域-->
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save" :loading="pageConfig.isSaving">保存</el-button>
      <el-button icon="cancel" @click="close">取消</el-button>
    </div>

    <!--表单区域-->
    <div class="form-area">
      <el-form label-width="50px" :model="formData" ref="form" :rules="formRules">
        <!--<el-form-item label="岗位" prop="post">-->
          <!--<el-select v-model="formData.post" class="input-width-full">-->
            <!--<el-option v-for="item in posts"-->
                       <!--:label="item.postName"-->
                       <!--:value="item.postVal"-->
                       <!--:key="item.postVal" >-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-card class="box-card card-style">
              <el-form-item label="岗位">
                {{formData.post}}
              </el-form-item>

              <el-form-item label="条件" prop="cndrOperate1">
                <el-select v-model="formData.cndrOperate1" filterable class="input-width-full">
                  <el-option v-for="compareType in compareTypeDict"
                             :label="compareType.label"
                             :value="compareType.val"
                             :key="compareType.val" >
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="级别" prop="postLevel">
                <el-select v-model="formData.postLevel" class="input-width-full">
                  <el-option v-for="item in levels"
                             :label="item.label"
                             :value="item.val"
                             :key="item.val" >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card class="box-card card-style">
              <el-form-item label="部门">
                {{formData.department}}
              </el-form-item>

              <el-form-item label="条件" prop="cndrOperate2">
                <el-select v-model="formData.cndrOperate2" filterable class="input-width-full">
                  <el-option v-for="compareType in compareTypeDict"
                             :label="compareType.label"
                             :value="compareType.val"
                             :key="compareType.val" >
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="级别" prop="departmentLevel">
                <el-select v-model="formData.departmentLevel" class="input-width-full">
                  <el-option v-for="item in levels"
                             :label="item.label"
                             :value="item.val"
                             :key="item.val" >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-card>
          </el-col>
        </el-row>
        <!--<el-form-item label="部门" prop="department">-->
          <!--<el-select v-model="formData.department" class="input-width-full">-->
            <!--<el-option v-for="item in departments"-->
                       <!--:label="item.departmentName"-->
                       <!--:value="item.departmentVal"-->
                       <!--:key="item.departmentVal" >-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->

      </el-form>
    </div>
  </div>
</template>
<script>
  import _ from 'lodash'
  import { mapGetters } from 'vuex'
  export default {
    name: 'conditionConfigEdit',
    props: ['initData'],
    data () {
      return {
        pageCommon: {
          cndrIndex: 0
        },
        pageConfig: {
          pageIniting: true,
          pageInitText: '正在初始化页面',
          isSaving: false
        },
        formData: {
          post: '岗位级别',
          cndrOperate1: '',
          postLevel: '',
          department: '部门级别',
          cndrOperate2: '',
          departmentLevel: ''
        },
        formRules: {
          cndrOperate1: [
            {required: true, message: '条件不能为空', trigger: 'blur'}
          ],
          postLevel: [
            {required: true, message: '级别不能为空', trigger: 'blur'}
          ],
          cndrOperate2: [
            {required: true, message: '条件不能为空', trigger: 'blur'}
          ],
          departmentLevel: [
            {required: true, message: '级别不能为空', trigger: 'blur'}
          ]
        },
//        posts: [{
//          postName: '岗位级别',
//          postVal: 'post'
//        }],
//        departments: [{
//          departmentName: '部门级别',
//          departmentVal: 'department'
//        }],
        levels: [{
          label: '01',
          val: '01'
        }, {
          label: '02',
          val: '02'
        }, {
          label: '03',
          val: '03'
        }, {
          label: '04',
          val: '04'
        }, {
          label: '05',
          val: '05'
        }, {
          label: '06',
          val: '06'
        }, {
          label: '07',
          val: '07'
        }, {
          label: '08',
          val: '08'
        }, {
          label: '09',
          val: '09'
        }, {
          label: '10',
          val: '10'
        }, {
          label: '11',
          val: '11'
        }, {
          label: '12',
          val: '12'
        }, {
          label: '13',
          val: '13'
        }, {
          label: '14',
          val: '14'
        }],
        cond: {},
        funcs: [],
        variableData: [],
        classes: [],
        relClasses: [],
        classProps: [],
        relClassProps: [],
        classPropInfo: {}
      }
    },
    mounted () {
      this.init()
    },
    methods: {
      init () {
        if (this.initData.cond) {
          console.log(this.initData.cond)
          let arr = _.split(this.initData.cond, ' ')
          this.formData.cndrOperate1 = arr[1]
          this.formData.postLevel = arr[2]
          this.formData.cndrOperate2 = arr[5]
          this.formData.departmentLevel = arr[6]
        }
      },
      /**
       * 保存
       */
      save () {
        this.$refs.form.validate((valid) => {
          if (valid) {
            let condData = this.Utils.cloneDeep(this.formData)
            this.$emit('saveCond', condData)
            this.$emit('close')
          }
        })
      },
      /**
       * 关闭按钮方法
       */
      close () {
        this.$emit('close')
      }
    },
    computed: {
      ...mapGetters({
        compareTypeDict: 'getCndtOperatorDict'
      })
    }
  }
</script>
<style>
 .card-style {
   margin-top: 10px;
 }
</style>
