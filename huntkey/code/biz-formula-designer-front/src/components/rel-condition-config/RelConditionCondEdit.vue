<template>
  <div class="rel-condition-cond-edit" v-loading="!pageConfig.pageIniting" element-loading-text="正在初始化页面">
    <!--按钮区域-->
    <div class="button-area">
      <el-button type="primary" icon="save" @click="save" :loading="pageConfig.isSaving">保存</el-button>
      <!--<el-button icon="save" @click="save(true)" :loading="pageConfig.isSaving">保存并继续</el-button>-->
      <el-button icon="cancel" @click="close">取消</el-button>
    </div>

    <!--表单区域-->
    <div class="form-area">
      <el-form label-width="100px" :model="formData" ref="form" :rules="formRules">
        <el-form-item label="类:" prop="clssId1">
          <el-select v-model="formData.clssId1" class="input-width-full" @change="cndrClass1Change">
            <el-option v-for="aClass in this.pageCommon.prevRelClasses"
              :label="aClass.edmpEdmcName"
              :value="aClass.id"
              :key="aClass.id" >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="属性：" prop="propId1">
          <el-cascader
            v-model="formData.propId1"
            class="input-width-full"
            placeholder="请选择"
            :options="formData.preRelProps1"
            filterable
            :props="filterData"
            @change="propTypeChange"
          ></el-cascader>
        </el-form-item>

        <!--<el-form-item label="属性:" prop="propId1">-->
          <!--<el-select v-model="formData.propId1" class="input-width-full">-->
            <!--<el-option v-for="aProp in this.formData.preRelProps1"-->
              <!--:label="aProp.edmpName"-->
              <!--:value="aProp.id"-->
              <!--:key="aProp.id" >-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->

        <el-form-item label="条件" prop="cndrOperate">
          <el-select v-model="formData.cndrOperate" filterable class="input-width-full">
             <el-option v-for="compareType in compareTypeDict"
              :label="compareType.label"
              :value="compareType.val"
              :key="compareType.val" >
             </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类型" prop="cndrValueType">
          <el-select v-model="formData.cndrValueType" @change="cndrTypeChange" class="input-width-full">
            <el-option v-for="optType in cndrValueTypeDict"
               :label="optType.label"
               :value="optType.val"
               :key="optType.val">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="类" prop="clssId2" v-if="formData.cndrValueType==='class'">
          <el-select v-model="formData.clssId2" @change="cndrClass2Change">
            <el-option v-for="aClass in this.pageCommon.prevRelClasses"
              :label="aClass.edmpEdmcName"
              :value="aClass.id"
              :key="aClass.id" >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="属性：" prop="classPropId2" v-if="formData.cndrValueType==='class'">
          <el-cascader
            v-model="formData.classPropId2"
            class="input-width-full"
            placeholder="请选择"
            :options="formData.preRelProps2"
            filterable
            :props="filterData"
          ></el-cascader>
        </el-form-item>

        <!--<el-form-item label="属性" prop="propId2" v-if="formData.cndrValueType==='class'">-->
          <!--<el-select placeholder="请选择属性" v-model="formData.propId2" class="input-width-full">-->
            <!--<el-option v-for="aProp in this.formData.preRelProps2"-->
              <!--:label="aProp.edmpName"-->
              <!--:value="aProp.id"-->
              <!--:key="aProp.id" >-->
            <!--</el-option>-->
          <!--</el-select>-->
        <!--</el-form-item>-->

        <el-form-item label="值:常量" prop="propId2" v-if="formData.cndrValueType==='const' && formData.constType===1">
          <el-input v-model="formData.propId2" class="input-width-full" style='width:215px'></el-input>
        </el-form-item>

        <el-form-item label="值:枚举" prop="propId2" v-if="formData.cndrValueType==='const' && formData.constType===2">
          <el-select v-model="formData.propId2" filterable placeholder="请选择" class="input-width-full">
            <el-option
              v-for="item in constEnums"
              :label="item.word_name"
              :value="item.id"
              :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="值:类" prop="propId2" v-if="formData.cndrValueType==='const' && formData.constType===3" required>
          <el-select v-model="formData.propId2" filterable placeholder="请选择" key="const-class" class="input-width-full">
            <el-option
              v-for="item in constEnums"
              :label="item.word_name"
              :value="item.id"
              :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="对象编号" prop="cndtObjectNumber" v-if="formData.cndrValueType==='const' && formData.constType===3" required>
          <el-input v-model="formData.cndtObjectNumber" class="input-width-full" key="const-code"></el-input>
        </el-form-item>

        <el-form-item label="值:变量" prop="propId2" v-if="formData.cndrValueType==='variable'" title="变量为该公式范围内的局部变量">
          <el-select v-model="formData.propId2" filterable placeholder="请选择" class="input-width-full">
            <el-option v-for="item in vars"
              :key="item.vrntId"
              :label="item.vrntVarName"
              :value="item.vrntId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import _ from 'lodash'
import { mapGetters } from 'vuex'
import { relConditionService, formulaService } from '@/api'
export default {
  name: 'relConditionCondEdit',
  props: ['initData'],
  data () {
    let checkProp1 = (rule, value, callback) => {
      if (_.isEmpty(value)) {
        return callback(new Error('属性值不能为空'))
      }
      callback()
    }
    let checkProp2 = (rule, value, callback) => {
      if (_.isEmpty(value)) {
        return callback(new Error('属性值不能为空'))
      }
      callback()
    }
    return {
      flag1: 0,
      flag2: 0,
      initFlag: 0,
      constEnums: [],
      vars: [],
      pageCommon: {
        prevRelClasses: [],
        cndrIndex: 0
      },
      filterData: {
        label: 'edmpName',
        value: 'addProp',
        children: 'children'
      },
      pageConfig: {
        pageIniting: true,
        pageInitText: '正在初始化页面',
        isSaving: false
      },
      formData: {
        cndrId: '',
        cndrProp: '',
        cndrProp2: '',
        preRelProps1: [],
        preRelProps2: [],
        clssId1: '',
        clssName1: '',
        propId1: [],
        propName1: '',
        clssId2: '',
        clssName2: '',
        propId2: '',
        classPropId2: [],
        propName2: '',
        cndrOperate: '=',
        cndrValueType: 'const',
        cndrPropOriginCode: '',
        cndrProp2OriginCode: '',
        constType: 1,
        cndtObjectNumber: ''
      },
      availableVars: [], // 变量数组
      formRules: {
        clssId1: [
          {required: true, message: '类不能为空', trigger: 'blur'}
        ],
        propId1: [
          { validator: checkProp1, trigger: 'blur' }
        ],
        classPropId2: [
          { validator: checkProp2, trigger: 'blur' }
        ],
        cndrOperate: [
          {required: true, message: '条件不能为空', trigger: 'blur'}
        ],
        clssId2: [
          {required: true, message: '类不能为空', trigger: 'blur'}
        ],
        propId2: [
          {required: true, message: '值不能为空', trigger: 'blur'}
        ],
        cndtObjectNumber: [
          {required: true, message: '对象编号不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  created () {
    this.init()
    console.log('initData', this.initData)
  },
  methods: {
    init () {
      this.pageCommon.cndrIndex = this.initData.index
      this.pageCommon.prevRelClasses = this.initData.prevRelClasses
      this.availableVars = this.initData.variableData
      this.vars = this.initData.variableData
      if (this.initData.cond) {
        this.Utils.clonePropsWhenExist(this.formData, this.initData.cond)
        this.formData.clssId1 = this.initData.cond.cndrPropOriginCode.split(' ')[0]
        let prop1 = this.initData.prevRelProp[this.formData.clssId1]
        this.addObjProp(prop1)
        this.formData.preRelProps1 = prop1
        this.flag1 = 1
        /** 修改待定 **/
        let propIds = this.Utils.cloneDeep(this.initData.cond.cndrPropOriginCode.split(' '))
        for (let i = 1; i < propIds.length; i++) {
          this.formData.propId1.push(propIds[i])
        }
        if (this.formData.cndrValueType === 'const') {
          this.formData.propId2 = this.initData.cond.cndrProp2OriginCode
          if (this.formData.constType === 2) {
            this.getEnumOrObjectList()
          }
          if (this.formData.constType === 3) {
            this.getEnumOrObjectList()
          }
        } else if (this.formData.cndrValueType === 'class') {
          this.formData.clssId2 = this.initData.cond.cndrProp2OriginCode.split(' ')[0]
          let prop2 = this.initData.prevRelProp[this.formData.clssId2]
          this.addObjProp(prop2)
          this.formData.preRelProps2 = prop2
          this.flag2 = 1
          let propIds2 = this.Utils.cloneDeep(this.initData.cond.cndrProp2OriginCode.split(' '))
          for (let i = 1; i < propIds2.length; i++) {
            this.formData.classPropId2.push(propIds2[i])
          }
        } else {
          this.formData.propId2 = this.initData.cond.cndrProp2OriginCode
          this.filterVars()
        }
      }
    },
    filterVars () {
      let arr = this.initData.cond.cndrPropOriginCode.split(' ')
      let valueStr = arr[arr.length - 1]
      let valueArr = valueStr.split('+')
      relConditionService.queryBaseInfoOfRelCondition({propId: valueArr[0]}).then((res) => {
        console.log('initProp', res)
        let propType = res.edmpValueType
        if (propType === 'normalObj') {
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === res.edmpDataType })
        } else {
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
        }
      }).catch((error) => {
        this.AppUtils.showWarning(error.message)
      })
    },
    getEnumOrObjectList () {
      let arr = this.initData.cond.cndrPropOriginCode.split(' ')
      let valueStr = arr[arr.length - 1]
      let valueArr = valueStr.split('+')
      relConditionService.queryBaseInfoOfRelCondition({propId: valueArr[0]}).then((res) => {
        let propType = res.edmpValueType
        if (propType === 'enum') {
          this.constEnums = []
          // this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
          formulaService.getConstInfoByEnumId(res.edmpDataType).then((data) => {
            this.constEnums = data.dataset
          }).catch((err) => {
            this.AppUtils.showWarning(err.message)
          })
        } else if (propType === 'object' || propType === 'objectLink') {
          this.constEnums = []
          // this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
          formulaService.getConstInfoByObjectId(res.edmpDataType).then((data) => {
            this.constEnums = [{word_name: data.edmcName, id: data.id}]
          }).catch((err) => {
            this.AppUtils.showWarning(err.message)
          })
        }
      }).catch((error) => {
        this.AppUtils.showWarning(error.message)
      })
    },
    /**
     * 保存
     */
    save () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          let condData = this.Utils.cloneDeep(this.formData)
          for (let i = 0; i < this.pageCommon.prevRelClasses.length; i++) {
            if (condData.clssId1 === this.pageCommon.prevRelClasses[i].id) {
              condData.clssName1 = this.pageCommon.prevRelClasses[i].edmpEdmcName
            }
            if (condData.clssId2 === this.pageCommon.prevRelClasses[i].id) {
              condData.clssName2 = this.pageCommon.prevRelClasses[i].edmpEdmcName
            }
          }
          let lastValueStr = condData.propId1[condData.propId1.length - 1]
          let lastValueArr = lastValueStr.split('+')
          condData.propName1 = lastValueArr[2]
          condData.cndrProp1Code = lastValueArr[1]
          condData.cndrProp = condData.clssName1 + '.' + condData.propName1
          let propStr1 = _.join(this.formData.propId1, ' ')
          condData.cndrPropOriginCode = condData.clssId1 + ' ' + propStr1
//          for (let i = 0; i < this.formData.propId1.length; i++) {
//            let tempStr = this.formData.propId1[i]
//            let tempArr = tempStr.split('+')
//            condData.cndrPropOriginCode += '.' + tempArr[0]
//          }
          if (condData.cndrValueType === 'const') {
            if (this.formData.constType === 1) {
              condData.cndrProp2OriginCode = condData.propId2
              condData.propName2 = condData.propId2
              condData.cndrProp2 = condData.propName2
            }
            if (this.formData.constType === 2) {
              condData.cndrProp2OriginCode = condData.propId2
              for (let i = 0; i < this.constEnums.length; i++) {
                if (condData.propId2 === this.constEnums[i].id) {
                  condData.propName2 = this.constEnums[i].word_name
                  condData.cndrProp2 = condData.propName2
                }
              }
            }
            if (this.formData.constType === 3) {
              condData.cndrProp2OriginCode = condData.propId2
              for (let i = 0; i < this.constEnums.length; i++) {
                if (condData.propId2 === this.constEnums[i].id) {
                  condData.propName2 = this.constEnums[i].word_name
                  condData.cndrProp2 = condData.propName2
                }
              }
            }
          } else if (condData.cndrValueType === 'variable') {
            condData.cndrProp2OriginCode = condData.propId2
            condData.propName2 = this.getVrntById(condData.propId2)
            condData.cndrProp2 = condData.propName2
          } else if (condData.cndrValueType === 'class') {
            let lastValueStr2 = condData.classPropId2[condData.classPropId2.length - 1]
            let lastValueArr2 = lastValueStr2.split('+')
            condData.propName2 = lastValueArr2[2]
            condData.cndrProp2Code = lastValueArr2[1]
            condData.cndrProp2 = condData.clssName2 + '.' + condData.propName2
            let propStr2 = _.join(this.formData.classPropId2, ' ')
            condData.cndrProp2OriginCode = condData.clssId2 + ' ' + propStr2
//            for (let i = 0; i < this.formData.classPropId2.length; i++) {
//              let tempStr = this.formData.classPropId2[i]
//              let tempArr = tempStr.split('+')
//              condData.cndrProp2OriginCode += '.' + tempArr[0]
//            }
            relConditionService.getClassById({clssId: condData.clssId2}, (res2) => {
              condData.cndrClass2NameEn = res2.edmcNameEn
            })
          }
          relConditionService.getClassById({clssId: condData.clssId1}, (res1) => {
            condData.cndrClass1NameEn = res1.edmcNameEn
          })
          condData.cndrSeq = this.pageCommon.cndrIndex + 1
          let index = this.pageCommon.cndrIndex
          this.$emit('saveCond', {index, condData})
          this.$emit('close')
        }
      })
    },
    /**
     * 通过ID获取变量名
     */
    getVrntById (cndrProp2OriginCode) {
      for (let i = 0; i < this.availableVars.length; i++) {
        if (cndrProp2OriginCode === this.availableVars[i].vrntId) {
          return this.availableVars[i].vrntVarName
        }
      }
      return '未知'
    },
    /**
     * 关闭按钮方法
     */
    close () {
      this.$emit('close')
    },
    /**
     * 值类型下拉框发生改变
     * 值类型选择变量 类 编辑时 常量-->变量 类 按道理应该会清空？？？
     */
    // FIXME 修改待定
    cndrTypeChange () {
      console.log('cndrTypeChange')
      this.formData.clssId2 = ''
      this.formData.classPropId2 = []
      this.formData.propId2 = ''
      this.formData.cndtObjectNumber = ''
//      if (this.initFlag === 0) {
//        this.initFlag += 1
//      } else {
//        this.formData.clssId2 = ''
//        this.formData.propId2 = ''
//      }
    },
    /**
     * 类下拉框发生改变
     */
    cndrClass1Change (value) {
      if (this.flag1 === 0) {
        let prop1 = this.initData.prevRelProp[value]
        this.addObjProp(prop1)
        this.formData.preRelProps1 = prop1
        this.flag1 += 1
      } else {
        this.formData.propId1 = []
        let prop1 = this.initData.prevRelProp[value]
        this.addObjProp(prop1)
        this.formData.preRelProps1 = prop1
      }
    },
    cndrClass2Change (value) {
      if (this.flag2 === 0) {
        let prop2 = this.initData.prevRelProp[value]
        this.addObjProp(prop2)
        this.formData.preRelProps2 = prop2
        this.flag2 += 1
      } else {
        this.formData.classPropId2 = []
        let prop2 = this.initData.prevRelProp[value]
        this.addObjProp(prop2)
        this.formData.preRelProps2 = prop2
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
    /**
     *还存在bug(没有判断选择属性的时候值类型的选择)
     */
    propTypeChange (value) {
      let valueStr = value[value.length - 1]
      let valueArr = valueStr.split('+')
      relConditionService.queryBaseInfoOfRelCondition({propId: valueArr[0]}).then((res) => {
        let propType = res.edmpValueType
        if (propType === 'normalObj') {
          this.formData.constType = 1
          this.formData.propId2 = ''
          this.formData.cndtObjectNumber = ''
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === res.edmpDataType })
        } else if (propType === 'enum') {
          this.formData.constType = 2
          this.formData.propId2 = ''
          this.formData.cndtObjectNumber = ''
          this.constEnums = []
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
          if (res.edmpDataType === null) {
            this.constEnums = []
          } else {
            formulaService.getConstInfoByEnumId(res.edmpDataType).then((data) => {
              this.constEnums = data.dataset
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          }
        } else if (propType === 'object' || propType === 'objectLink') {
          this.formData.constType = 3
          this.formData.propId2 = ''
          this.formData.cndtObjectNumber = ''
          this.constEnums = []
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
          if (res.edmpDataType === null) {
            this.constEnums = []
          } else {
            formulaService.getConstInfoByObjectId(res.edmpDataType).then((data) => {
              this.constEnums = [{word_name: data.edmcName, id: data.id}]
              if (this.formData.cndrValueType === 'const') {
                this.formData.propId2 = data.id
              }
            }).catch((err) => {
              this.AppUtils.showWarning(err.message)
            })
          }
        } else {
          this.formData.constType = 1
          this.formData.propId2 = ''
          this.formData.cndtObjectNumber = ''
          this.vars = _.filter(this.availableVars, function (o) { return o.vrntVarType === propType })
        }
      }).catch((error) => {
        this.AppUtils.showWarning(error.message)
      })
    }
  },
  computed: {
    ...mapGetters({
      compareTypeDict: 'getCndtOperatorDict',
      cndrValueTypeDict: 'getCndtValueTypeDict4RelCndt'
    })
  }
}
</script>
<style>

</style>
