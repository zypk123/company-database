<template>
  <div class="rel-edm-class-cond-edit"
       v-loading="pageConfig.pageIniting"
       element-loading-text="正在初始化页面">
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save(false)" :loading="pageConfig.isSaving">保存</el-button>
      <el-button icon="save" @click="save(true)" :loading="pageConfig.isSaving" v-if="initData.mode==='add'">保存并继续</el-button>
      <el-button icon="cancel" @click="close">取消</el-button>
    </div><!-- /.button-area -->
    <div class="form-area">
      <el-form label-width="100px" :model="formData" ref="form" :rules="formRules">

        <el-form-item class="text-only" label="相关类">
          {{initData.relClass.clssAliasName}}
        </el-form-item>

        <el-form-item label="属性" prop="cndtPropOriginCode">
          <el-cascader
            v-model="formData.cndtPropOriginCode"
            class="input-width-full"
            placeholder="请选择"
            :options="emdProps"
            filterable
            :props="filterData"
          ></el-cascader>
        </el-form-item>

        <!--<el-form-item label="属性" prop="cndtPropOriginCode">-->
          <!--<el-select v-model="formData.cndtPropOriginCode" class="input-width-full">-->
            <!--<el-option v-for="edmp in emdProps"-->
                       <!--:label="edmp.edmpName"-->
                       <!--:value="edmp.edmpCode"-->
                       <!--:key="edmp.edmpCode">-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->

        <el-form-item label="条件" prop="cndtOperate">
          <el-select v-model="formData.cndtOperate"  filterable  class="input-width-full">
            <el-option v-for="optType in cndtOperatorDict"
                       :label="optType.label"
                       :value="optType.val"
                       :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类型" prop="cndtValueType">
          <el-select v-model="formData.cndtValueType" @change="cndtValueTypeChange" class="input-width-full">
            <el-option v-for="optType in cndtValueTypeDict"
                       :label="optType.label"
                       :value="optType.val"
                       :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类" prop="cndtValueClassId" v-if="formData.cndtValueType==='class'">
          <el-select v-model="formData.cndtValueClassId" @change="cndtValueClassChange"  class="input-width-full" >
            <el-option v-for="item in availableClasses"
                       :label="item.edmcName"
                       :value="item.id"
                       :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:属性" prop="cndtClassOriginCode" v-if="formData.cndtValueType==='class'">
          <el-cascader
            v-model="formData.cndtClassOriginCode"
            class="input-width-full"
            placeholder="请选择"
            :options="cndtValueProps"
            filterable
            :props="filterData"
          ></el-cascader>
        </el-form-item>

        <!--<el-form-item label="值:属性" prop="cndtClassOriginCode" v-if="formData.cndtValueType==='class'">-->
          <!--<el-select placeholder="请选择属性" v-model="formData.cndtClassOriginCode" class="input-width-full">-->
            <!--<el-option-->
              <!--v-for="aProp in cndtValueProps"-->
              <!--:label="aProp.edmpName"-->
              <!--:value="aProp.edmpCode"-->
              <!--:key="aProp.edmpCode">-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->

        <el-form-item label="值:常量" prop="cndtValueOriginCode" v-if="formData.cndtValueType==='const'">
          <!-- @change="rowCndtValueOriginCodeChange(scope.row)" -->
          <el-input v-model="formData.cndtValueOriginCode" class="input-width-full"></el-input>
        </el-form-item>

        <el-form-item label="值:变量" prop="cndtValueOriginCode" v-if="formData.cndtValueType==='variable'"
                      title="变量为该公式范围内的局部变量">
          <!-- @change="rowCndtValueOriginCodeChange(scope.row)" -->
          <el-select v-model="formData.cndtValueOriginCode" filterable placeholder="请选择" class="input-width-full">
            <el-option
              v-for="item in availableVars"
              :key="item.vrntId"
              :label="item.vrntVarName"
              :value="item.vrntId">
            </el-option>
          </el-select>
        </el-form-item>

      </el-form>
    </div><!-- /.form-area -->
  </div><!--/.rel-edm-class-cond-edit -->
</template>
<script>
  import _ from 'lodash'
  import { EDM_DATA_TYPES } from '@/types'
  import { mapGetters, mapActions } from 'vuex'
  import { formulaService } from '@/api'
  export default {
    name: 'processRelEdmClassCondEdit',
    props: ['initData'],
    data () {
      let checkProp1 = (rule, value, callback) => {
        if (_.isEmpty(value)) {
          return callback(new Error('属性不能为空'))
        }
        callback()
      }
      let checkProp2 = (rule, value, callback) => {
        if (_.isEmpty(value)) {
          return callback(new Error('属性不能为空'))
        }
        callback()
      }
      return {
        cndtIndex: 0,
        pageConfig: {
          pageIniting: true,
          pageInitText: '正在初始化页面',
          isSaving: false
        },
        initFormData: {},
        formData: {
          cndtProp: '',
          cndtPropOriginCode: [],
          cndtOperate: '',
          cndtValueType: 'const',
          cndtValue: '',
          cndtValueClassId: '',
          cndtValueOriginCode: '',
          cndtClassOriginCode: []
        },
        formRules: {
          cndtPropOriginCode: [
            { validator: checkProp1, trigger: 'blur' }
          ],
          cndtClassOriginCode: [
            { validator: checkProp2, trigger: 'blur' }
          ],
          cndtOperate: [
            {required: true, message: '条件不能为空', trigger: 'blur'}
          ],
          cndtValueOriginCode: [
            {required: true, message: '值不能为空', trigger: 'blur'}
          ]
        },
        emdProps: [], // 相关类属性下拉列表
        cndtValueProps: [], // 比较值类型为类时, 该类的属性下拉列表
        availableClasses: [],
        filterData: {
          label: 'edmpName',
          value: 'addProp',
          children: 'children'
        }
      }
    }, // end of data
    created () {
      this.init()
    },
    methods: {
      ...mapActions({
        fetchEdmps: EDM_DATA_TYPES.ACTION_FETCH_PROPS
      }),
      init () {
        console.log('initData', this.initData)
        this.cndtIndex = this.initData.index
        this.availableClasses = this.initData.classes
        if (this.initData.cond) {
          // this.Utils.clonePropsWhenExist(this.formData, this.initData.cond)
          this.formData.cndtPropOriginCode = _.split(this.initData.cond.preVarchar, ' ')
          this.formData.cndtOperate = this.initData.cond.cndtOperate
          this.formData.cndtValueType = this.initData.cond.cndtValueType
          if (this.formData.cndtValueType === 'class') {
            this.formData.cndtValueClassId = this.initData.cond.cndtValueClassId
            this.formData.cndtClassOriginCode = _.split(this.initData.cond.cndtValueOriginCode, ' ')
          } else {
            this.formData.cndtValueOriginCode = this.initData.cond.cndtValueOriginCode
          }
        }
        this.initPropes4Left().then(() => {
          this.initPropes4Right()
        })
        .then(() => {
          this.initFormData = this.Utils.cloneDeep(this.formData)
          this.pageConfig.pageIniting = false
        })
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
      // 初始化相关类的属性下拉
      initPropes4Left () {
        return new Promise((resolve, reject) => {
          this.fetchEdmps({
            edmcNameEn: this.initData.relClass.clssEdmcNameEn
          }).then((resData) => {
            // console.log('属性', resData)
            this.addObjProp(resData)
            this.emdProps = resData
            resolve()
          })
        })
      },
      // 初始化选中类的的属性
      initPropes4Right () {
        if (this.formData.cndtValueType === 'class') {
          let edmcId = this.formData.cndtValueClassId
          formulaService.queryEdmClassCascadePropsByClassID({
            edmcId: edmcId
          }).then((data) => {
            // console.log('data', data)
            this.addObjProp(data)
            this.cndtValueProps = data
          }).catch((err) => {
            this.AppUtils.showWarning(err.message)
          })
        }
      },
      cndtValueTypeChange () {
        this.formData.cndtValueClassId = ''
        this.formData.cndtClassOriginCode = []
        this.formData.cndtValueOriginCode = ''
      },
      cndtValueClassChange () {
        this.formData.cndtClassOriginCode = []
        let edmcId = this.formData.cndtValueClassId
        return this.fetchEdmps({
          edmcId: edmcId
        }).then((resData) => {
          this.addObjProp(resData)
          this.cndtValueProps = resData
        }).catch((error) => {
          this.AppUtils.showError(error.message)
        })
      },
//      cndt_id编号
//      cndt_prop_clss_id属性或者相关类编号
//      cndt_seq序号
//      cndt_prop属性
//      cndt_operate操作符
//      cndt_value值
//      cndt_value_type值类型 enum[class,constant, variant]
//      isenable是否可用: 1 可用， 0 不可用
//      cndt_value_class_id如果cndt的类型为class, 此列存该class的id
//      cndt_prop_origin_code属性编码
//      cndt_value_origin_code值编码

      save (addNext) {
        this.$refs.form.validate((valid) => {
          if (valid) {
            // 根据cndtPropOriginCode设置cndtProp的值
            // console.log(this.formData.cndtClassOriginCode)
            let selectPropCode = this.formData.cndtPropOriginCode[this.formData.cndtPropOriginCode.length - 1]
            let selectPropCodeArr = selectPropCode.split('+')
            this.formData.cndtProp = selectPropCodeArr[2]

            // 如果cndtValueType为 const 把cndtValueOriginCode的值复制一份到cndtValue
            if (this.formData.cndtValueType === 'const') {
              this.formData.cndtValue = this.formData.cndtValueOriginCode
            }
            // 如果cndtValueType为 variable 根据cndtValueOriginCode设置cndtValue的值
            if (this.formData.cndtValueType === 'variable') {
              this.formData.cndtValue = this.Utils.findPropByProp(this.availableVars, 'vrntId', this.formData.cndtValueOriginCode, 'vrntVarName', 'defVal')
            }
            // 如果cndtValueType为 class 根据cndtValueOriginCode设置cndtValue的值
            // cndtValue = 类名/相关类别名 . 属性名
            if (this.formData.cndtValueType === 'class') {
              let className = this.getClassNameByClassId(this.formData.cndtValueClassId)
              let selectPropCode2 = this.formData.cndtClassOriginCode[this.formData.cndtClassOriginCode.length - 1]
              let selectPropCodeArr2 = selectPropCode2.split('+')
              this.formData.cndtValue = className + '.' + selectPropCodeArr2[2]
            }
            // let condData = this.Utils.cloneDeep(this.formData)
            let condData = {}
            condData.cndtProp = this.formData.cndtProp
            condData.cndtOperate = this.formData.cndtOperate
            condData.cndtValueType = this.formData.cndtValueType
            condData.cndtValue = this.formData.cndtValue
            condData.cndtValueClassId = this.formData.cndtValueClassId
            condData.preVarchar = _.join(this.formData.cndtPropOriginCode, ' ')
            condData.cndtPropOriginCode = selectPropCodeArr[1]
            if (condData.cndtValueType === 'class') {
              condData.cndtValueOriginCode = _.join(this.formData.cndtClassOriginCode, ' ')
            } else {
              condData.cndtValueOriginCode = this.formData.cndtValueOriginCode
            }
            condData.cndtSeq = this.cndtIndex + 1
            let index = this.cndtIndex
            this.$emit('saveCond', {index, condData})
            if (addNext) {
              this.Utils.clonePropsWhenExist(this.formData, this.initFormData)
              this.formData.cndtSeq += 1
              this.cndtIndex += 1
            } else {
              this.$emit('close')
            }
          }
        })
      },
      getClassNameByClassId (classId) {
        for (let i = 0; i < this.availableClasses.length; i++) {
          if (classId === this.availableClasses[i].id) {
            return this.availableClasses[i].edmcName
          }
        }
        return '未知类'
      },
      close () {
        this.$emit('close')
      },
      test () {
        console.info(this.formData)
      }
    },
    computed: {
      ...mapGetters({
        cndtOperatorDict: 'getCndtOperatorDict',
        cndtValueTypeDict: 'getCndtValueTypeDict4RelClass'
      }),
      availableVars () {
        // FIXME 按说要限制只能选择某些变量, 否则会导致死循环
        // FIXME 测试时先选系统变量, 发布时修改为局部变量
        return this.initData.variableData
      }
    }
  }
</script>
<style scoped>
  .text-only{
    margin-bottom: 5px;
  }
  .form-area{
    margin-top: 10px;
  }
  .input-short{
    width: 80px;
  }
  .form-item-normal{
    width: 310px;
  }
  .form-item-short{
    width: 280px;
  }
  .input-width-full{
    width: 100%;
  }
</style>
