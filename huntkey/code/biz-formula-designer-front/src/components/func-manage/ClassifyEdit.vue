<template>
  <div>
    <!--按钮区域-->
    <el-row>
      <el-col :span="24">
        <el-button type="primary" icon="save" @click="saveClassify()" :loading="pageConfig.isSaving">保存</el-button>
        <el-button type="warning" icon="cancel" @click="closeDlg()">取消</el-button>
      </el-col>
    </el-row>

    <!-- 编辑表单区域 -->
    <el-row>
      <el-col :span="24" >
        <el-form :model="classifyInfo" :rules="classifyInfoFormRules" ref="classifyInfoForm" label-width="80px">
          <el-form-item label="分类名称" prop="fnccClassifyName">
            <el-input v-model="classifyInfo.fnccClassifyName" placeholder="分类名称"></el-input>
          </el-form-item>
          <el-form-item label="分类编码" prop="fnccClassifyCode" >
            <el-input v-model="classifyInfo.fnccClassifyCode" placeholder="eg. 商品：Good(字母和数字组成,且首字母大写)"
            title="eg. 商品：Good(字母和数字组成,且首字母大写)" :disabled="!pageConfig.display">
            </el-input>
          </el-form-item>
          <el-form-item label="分类描述" prop="fnccClassifyDesc" >
            <el-input v-model="classifyInfo.fnccClassifyDesc" type="textarea" :rows="3" class="no-resize"
              :autosize="{ minRows: 2, maxRows: 6 }"
              resize="none" placeholder="分类描述">
            </el-input>
          </el-form-item>
          <el-form-item label="分类状态" prop="fnccState" >
            <el-select v-model="classifyInfo.fnccState" placeholder="请选择函数分类状态" :disabled="!pageConfig.display">
              <el-option label="请选择" value=""></el-option>
              <el-option v-for="fnccState in fnccStateDict"
                :label="fnccState.label"
                :value="fnccState.val"
                :key="fnccState.val" >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="维护人：" >
            {{classifyInfo.moduser}}
          </el-form-item>
          <el-form-item label="维护时间：" >
            {{classifyInfo.modtime|datetimeFormat}}
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import { classifyService } from '@/api'
export default {
  name: 'classifyEdit',
  props: ['initData'],
  // 页面数据
  data () {
    return {
      firstName: 'com.huntkey.rx.',
      lastName: 'Function',
      pageConfig: {
        isSaving: false,
        display: this.initData.display
      },
      classifyInfo: {
        fnccId: '',
        fnccClassifyName: '',
        fnccClassifyCode: '',
        fnccClassifyDesc: '',
        fnccState: '',
        moduser: 'admin',
        modtime: new Date()
      },
      // 校验规则
      classifyInfoFormRules: {
        fnccClassifyName: [
          {required: true, message: '请输入分类名称', trigger: 'blur'}
        ],
        fnccClassifyCode: [
          {required: true, message: '请输入分类编码', trigger: 'blur'},
          {
            validator: (rules, value, callback) => {
              if (!this.Utils.validate(value, 'func_class_name')) {
                // 名称(大写字母开头,字母和数字)
                callback(new Error('分类编码格式不正确'))
              }
              callback()
            },
            trigger: 'blur'
          }
        ],
        fnccClassifyDesc: [
          {required: true, message: '请输入分类描述', trigger: 'blur'}
        ],
        fnccState: [
          {required: true, message: '请输入分类状态', trigger: 'blur'}
        ]
      }
    }
  },
  created () { // 页面初始化方法
    console.error(this.initData)
    console.info(this.pageConfig.display)
    if (this.initData.mode === 'edit') { // 编辑页面数据赋值
      let oldFnccClassifyCode = this.initData.data.fnccClassifyCode
      console.log(oldFnccClassifyCode)
      console.log((oldFnccClassifyCode.substring(oldFnccClassifyCode.lastIndexOf('.') + 1)).substring(0, (oldFnccClassifyCode.substring(oldFnccClassifyCode.lastIndexOf('.') + 1)).indexOf('Function')))
      this.classifyInfo.fnccId = this.initData.data.fnccId
      this.classifyInfo.fnccClassifyName = this.initData.data.fnccClassifyName
      this.classifyInfo.fnccClassifyCode = (oldFnccClassifyCode.substring(oldFnccClassifyCode.lastIndexOf('.') + 1)).substring(0, (oldFnccClassifyCode.substring(oldFnccClassifyCode.lastIndexOf('.') + 1)).indexOf('Function'))
      this.classifyInfo.fnccClassifyDesc = this.initData.data.fnccClassifyDesc
      this.classifyInfo.fnccState = this.initData.data.fnccState
    }
  },
  methods: {
    /**
     * 保存按钮触发方法
     */
    saveClassify () {
      console.log('save')
      this.$refs.classifyInfoForm.validate((valid) => {
        if (valid) {
          this.pageConfig.isSaving = true
          if (this.initData.mode === 'add') {
            console.log('add save-------')
            let obj = this.Utils.cloneDeep(this.classifyInfo)
            obj.fnccClassifyCode = this.firstName + obj.fnccClassifyCode.toLocaleLowerCase() + '.' + obj.fnccClassifyCode + this.lastName
            // console.error(obj.fnccClassifyCode)
            classifyService.saveClassify(obj).then((reData) => {
              console.log(reData)
              this.$message.success('保存成功！')
              this.closeDlg()
              this.$emit('reload')
              this.pageConfig.isSaving = false
            }).catch((error) => {
              this.$message.error(error.message)
              this.pageConfig.isSaving = false
            })
          }
          if (this.initData.mode === 'edit') {
            console.log('add edit-------')
            let obj = this.Utils.cloneDeep(this.classifyInfo)
            obj.fnccClassifyCode = this.firstName + obj.fnccClassifyCode.toLocaleLowerCase() + '.' + obj.fnccClassifyCode + this.lastName
            classifyService.updateClassify(obj).then(() => {
              this.$message.success('修改成功！')
              this.closeDlg()
              this.$emit('reload')
              this.pageConfig.isSaving = false
            }).catch(() => {
              this.pageConfig.isSaving = false
            })
          }
        } else {
          return false
        }
      })
    },
    /**
     * 取消按钮触发方法
     */
    closeDlg () {
      this.$emit('close')
    }
  },
  computed: {
    ...mapGetters({
      fnccStateDict: 'getFnccStateDict'
    })
  }
}
</script>
<style scope>

</style>
