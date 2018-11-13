<template>
  <div class="warpper">
    <div class="button-area">
      <div v-if="dataIn.mode === getMode('EDIT_MODE_CREATE')">
        <el-button icon="rx-baocun1" type="primary" @click="save" :loading="isSaveing">保存</el-button>
         <el-button icon="circle-close" @click="cancel">取消</el-button>
      </div>
      <div v-if="dataIn.mode === getMode('EDIT_MODE_UPDATE')">
        <el-button icon="rx-baocun1" type="primary" @click="update" :loading="isSaveing">保存</el-button>
        <el-button icon="delete" type="danger" @click="remove" :loading="isRemoving">删除</el-button>
         <el-button icon="circle-close" @click="cancel">取消</el-button>
      </div>
      <div v-if="dataIn.mode === getMode('EDIT_MODE_COPY')">
        <el-button icon="rx-baocun1" type="primary" @click="copy" :loading="isSaveing">保存</el-button>
         <el-button icon="circle-close" @click="cancel">取消</el-button>
      </div>
    </div>
    <div class="form-area">
      <el-form label-width="90px" :inline="false" :model="formData" ref="form" :rules="rules">
        <el-form-item v-if="dataIn.mode === getMode('EDIT_MODE_COPY')" class="form-item-full text-only" label="源类：" label-width="100px">
          {{dataIn.classInfo.viewName}}
        </el-form-item>
        <el-form-item class="form-item" label="父类" prop="edmcParentId">
          <class-selecter 
            :filter="classfilter"
            class="input-width-m"
            v-model="formData.edmcParentId"
            placeholder="请选择"
            ></class-selecter>
        </el-form-item>
        <el-form-item class="form-item" label="类编码" prop="edmcCode">
          <el-input class="input-width-m" v-model.trim="formData.edmcCode" :maxlength="15"/>
        </el-form-item>
        <el-form-item class="form-item" label="类名称" prop="edmcName">
          <el-input class="input-width-m" v-model.trim="formData.edmcName" :maxlength="20"/>
        </el-form-item>
        <el-form-item class="form-item" label="类简称" prop="edmcShortName">
          <el-input class="input-width-m" :maxlength="4" v-model.trim="formData.edmcShortName"/>
        </el-form-item>
        <el-form-item class="form-item" label="类英文名称" prop="edmcNameEn">
          <el-input class="input-width-m" v-model.trim="formData.edmcNameEn" :maxlength="20" @change="toLowerCase"/>
        </el-form-item>
        <el-form-item class="form-item" label="类版本" prop="edmcVer">
          <el-input class="input-width-m" v-model.trim="formData.edmcVer" :maxlength="10"/>
        </el-form-item>
        <el-form-item class="form-item" label="负责人" prop="edmcPrincipal">
          <el-input class="input-width-m" v-model.trim="formData.edmcPrincipal" :maxlength="10"/>
        </el-form-item>
        <el-form-item class="form-item" label="所属行业" prop="edmcIndustryCode">
          <industry-selecter v-model="formData.edmcIndustryCode" class="input-width-m"></industry-selecter>
        </el-form-item>
         <el-form-item class="form-item" label="数据库类型" prop="databaseType" required>
          <el-select class="input-width-m" v-model="formData.databaseType" filterable>
            <el-option v-for="item in allDict['edm_db_type']" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item class="form-item" prop="isEntity" label-width="30px">
          <el-radio v-model="formData.isEntity" :label="1">实类</el-radio>
          <el-radio v-model="formData.isEntity" :label="0">虚类</el-radio>
        </el-form-item>
        <!-- <el-form-item class="form-item" label="标准的呈现">
          <object-selecter class="input-width-m" 
          v-model="formData.normalPresent" 
          dialog-title="选择呈现对象" 
          placeholder="请选择呈现对象"
          root-class="show"></object-selecter>
        </el-form-item>
        <el-form-item class="form-item" label="对象所在云" prop="objectOnCloud">
          <el-input class="input-width-m" :maxlength="15" v-model.trim="formData.objectOnCloud"/>
        </el-form-item> -->
        <el-form-item class="form-item-full" label="使用说明" prop="edmcUseDesc">
          <el-input type="textarea" placeholder="100个字以内" class="textarea" :rows="4" v-model="formData.edmcUseDesc" :maxlength="100"/>
        </el-form-item>
        <el-form-item class="form-item-full text-only" label="维护人：" prop="edmcUseDesc" v-if="dataIn.mode === getMode('EDIT_MODE_UPDATE')">
          {{formData.adduser}}
        </el-form-item>
        <el-form-item class="form-item-full text-only" label="维护时间：" prop="edmcUseDesc" v-if="dataIn.mode === getMode('EDIT_MODE_UPDATE')">
          {{formData.addtimeStr}}
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
  import { classTypes, dictTypes } from '@/types'
  import { mapGetters } from 'vuex'
  import { classService } from '@/api'
  import ClassSelecter from '@/components/commons/class-selecter/ClassSelecter'
  import IndustrySelecter from '@/components/commons/industry-selecter/IndustrySelecter'
  import ObjectSelecter from '@/components/commons/object-selecter/ObjectSelecter'
  import _ from 'lodash'

  export default {
    props: ['dataIn'],
    created () {
      // 获取数据字典
      this.$store.dispatch(dictTypes.GET_DICT_BY_CODES, ['edm_db_type'])
      if (this.dataIn.mode === classTypes.EDIT_MODE_CREATE) {
        // 填入模型id
        this.formData.edmcEdmdId = this.dataIn.edmId
        // 填入父组件id
        if (this.dataIn.mode === classTypes.EDIT_MODE_CREATE) {
          this.formData.edmcParentId = this.dataIn.parentId
        }
      } else if (this.dataIn.mode === classTypes.EDIT_MODE_UPDATE) {
        // 编辑时初始化
        _.assign(this.formData, this.dataIn.classInfo)
        this.filterIds.push(this.formData.id)
      } else if (this.dataIn.mode === classTypes.EDIT_MODE_COPY) {
        // 复制时初始化
        this.formData.id = this.dataIn.classInfo.id
        this.formData.edmcEdmdId = this.dataIn.classInfo.edmcEdmdId
      }
    },
    computed: {
      ...mapGetters({
        allDict: dictTypes.GET_ALL_DICTS
      })
    },
    components: {
      ClassSelecter, IndustrySelecter, ObjectSelecter
    },
    data () {
      return {
        isSaveing: false,
        isRemoving: false,
        formData: {
          // 唯一标识
          id: '',
          // 模型ID
          edmcEdmdId: '',
          // 父类Id
          edmcParentId: '',
          // 类编码
          edmcCode: '',
          // 类版本
          edmcVer: '',
          // 类名称
          edmcName: '',
          // 类英文名称
          edmcNameEn: '',
          // 类简称
          edmcShortName: '',
          // 负责人
          edmcPrincipal: '',
          // 是否是实类
          isEntity: 1,
          // 数据库类型
          databaseType: 'hbase',
          // 标准的呈现
          normalPresent: '',
          // 对象所在云
          objectOnCloud: '',
          // 所属行业
          edmcIndustryCode: 'IC001',
          // 说明
          edmcUseDesc: ''
        },
        rules: {
          edmcCode: [{
            required: true,
            message: '请输入类编码'
          }, {
            validator: (rules, value, callback) => {
              if (!this.UTILS.validate(value, 'account')) {
                callback('编码只能由字母、数字和下划线组成')
              } else {
                // 验证唯一性，编辑模式下，与远数据相同，不做验证
                if (this.dataIn.mode === classTypes.EDIT_MODE_UPDATE &&
                  this.dataIn.classInfo.edmcCode === value) {
                  callback()
                } else {
                  classService.validateEdmcCode(this.formData.edmcEdmdId, value).then(() => {
                    callback()
                  }).catch(() => {
                    callback('类编码重复')
                  })
                }
              }
            },
            trigger: 'blur'
          }],
          edmcName: [{
            required: true,
            message: '请输入类名称'
          }, {
            validator: (rules, value, callback) => {
              // 验证唯一性，编辑模式下，与远数据相同，不做验证
              if (this.dataIn.mode === classTypes.EDIT_MODE_UPDATE &&
                this.dataIn.classInfo.edmcName === value) {
                callback()
              } else {
                classService.validateEdmcName(this.formData.edmcEdmdId, value).then(() => {
                  callback()
                }).catch(() => {
                  callback('类名称重复')
                })
              }
            },
            trigger: 'blur'
          }],
          edmcNameEn: [{
            required: true,
            message: '请输入类英文名称'
          }, {
            validator: (rules, value, callback) => {
              if (!this.UTILS.validate(value, 'en')) {
                callback('只能输入小写英文')
              } else {
                // 验证唯一性，编辑模式下，与源数据相同，不做验证
                if (this.dataIn.mode === classTypes.EDIT_MODE_UPDATE &&
                  this.dataIn.classInfo.edmcNameEn === value) {
                  callback()
                } else {
                  classService.validateEdmcNameEn(this.formData.edmcEdmdId, value).then(() => {
                    callback()
                  }).catch(() => {
                    callback('英文名称重复')
                  })
                }
              }
            },
            trigger: 'blur'
          }],
          edmcShortName: [{
            required: true,
            message: '请输入类简称'
          }, {
            validator: (rules, value, callback) => {
              if (!this.UTILS.validate(value, 'en')) {
                callback('只能输入小写英文')
              } else {
                // 验证唯一性，编辑模式下，与远数据相同，不做验证
                if (this.dataIn.mode === classTypes.EDIT_MODE_UPDATE &&
                  this.dataIn.classInfo.edmcShortName === value) {
                  callback()
                } else {
                  classService.validateEdmcShortName(this.formData.edmcEdmdId, value).then(() => {
                    callback()
                  }).catch(() => {
                    callback('类简称重复')
                  })
                }
              }
            },
            trigger: 'blur'
          }],
          edmcIndustryCode: [{
            required: true,
            message: '请选择行业类型'
          }],
          objectOnCloud: [{
            validator: (rules, value, callback) => {
              if (value) {
                if (!this.UTILS.validate(value, 'ip')) {
                  callback('必须输入正确的IP')
                } else {
                  callback()
                }
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }],
          edmcVer: [{
            validator: (rules, value, callback) => {
              if (value) {
                if (!this.UTILS.validate(value, 'version')) {
                  callback('版本号格式不正确（示例：V1.0）')
                } else {
                  callback()
                }
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }]
        },
        filterIds: []
      }
    },
    methods: {
      toLowerCase (value) {
        this.formData.edmcNameEn = value.toLowerCase()
      },
      getMode (key) {
        return classTypes[key]
      },
      // 筛选树组件的方法
      classfilter (data, node) {
        if (this.dataIn.mode === classTypes.EDIT_MODE_UPDATE) {
          // 编辑模式下，父类不能是自己或者自己的子节点
          if (this.filterIds.indexOf(data.id) >= 0) {
            // 将子节点加入到筛选范围内
            for (let index in data.children) {
              this.filterIds.push(data.children[index].id)
            }
            return false
          }
          // 子节点筛选后清空
          this.filterIds = [this.formData.id]
          return true
        }
        return true
      },
      // 保存
      save () {
        if (this.isSaveing) return
        // 表单验证
        this.$refs.form.validate(result => {
          if (result) {
            this.isSaveing = true
            // 验证通过
            classService.createClass(this.formData).then(data => {
              this.$emit('createSuccess')
              this.isSaveing = false
            }).catch(() => {
              this.isSaveing = false
            })
          } else {
            this.isSaveing = false
          }
        })
      },
      // 更新保存
      update () {
        if (this.isSaveing) return
        // 表单验证
        this.$refs.form.validate(result => {
          if (result) {
            this.isSaveing = true
            // 验证通过
            classService.updateClass(this.formData).then(data => {
              this.$emit('updateSuccess')
              this.isSaveing = false
            }).catch(() => {
              this.isSaveing = false
            })
          } else {
            this.isSaveing = false
          }
        })
      },
      // 复制保存
      copy () {
        if (this.isSaveing) return
        // 表单验证
        this.$refs.form.validate(result => {
          if (result) {
            this.isSaveing = true
            // 验证通过
            classService.copyClass(this.formData).then(data => {
              this.$emit('copySuccess')
              this.isSaveing = false
            }).catch(() => {
              this.isSaveing = false
            })
          } else {
            this.isSaveing = false
          }
        })
      },
      // 删除类
      remove () {
        this.$confirm('确定删除？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.isRemoving = true
          classService.removeClass(this.formData.id).then(() => {
            // 删除更改
            this.$message.success('删除成功！')
            this.$emit('removeSuccess')
            this.isRemoving = false
          }).catch(() => {
            this.isRemoving = false
          })
        })
      },
      // 关闭
      cancel () {
        this.$emit('close')
      }
    }
  }
</script>
<style scoped>
  .warpper{
    padding: 0 20px;
  }
  .form-item{
    width: 300px;
    float: left;
  }
  .button-area{
    height: 40px;
    float: left;
  }
  .form-area{
    float: left;
  }
  .form-item-full{
    width: 100%;
    float: left;
  }
  .textarea{
    width: 500px;
  }
  .text-only{
    margin-bottom: 5px;
  }
</style>
