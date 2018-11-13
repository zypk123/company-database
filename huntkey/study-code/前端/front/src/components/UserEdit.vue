<template>
  <div>
    <!--按钮区域-->
    <el-row>
      <el-col :span="24">
        <el-button type="primary" icon="save" @click="saveUser()" :loading="pageConfig.isSaving">保存</el-button>
        <el-button type="warning" icon="cancel" @click="closeDlg()" >取消</el-button>
      </el-col>
    </el-row>

    <!-- 编辑表单区域 -->
    <el-row>
      <el-col :span="24" >
        <el-form :model="userInfo" :rules="userInfoFormRules" ref="userInfoForm" label-width="80px">
          <el-form-item label="用户名：" prop="userName">
            <el-input v-model="userInfo.userName" placeholder="用户名"></el-input>
          </el-form-item>
          <el-form-item label="昵称：" prop="nickName" >
            <el-input v-model="userInfo.nickName" placeholder="昵称"></el-input>
          </el-form-item>
          <el-form-item label="邮箱：" prop="email" >
            <el-input v-model="userInfo.email" placeholder="邮箱"></el-input>
          </el-form-item>
          <el-form-item label="密码：" prop="passWord" >
            <el-input v-model="userInfo.passWord" placeholder="密码"></el-input>
          </el-form-item>
          <el-form-item label="维护人：" >
          {{userInfo.moduser}}
          </el-form-item>
          <el-form-item label="维护时间：" >
          {{userInfo.modtime|datetimeFormat}}
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
// import { mapGetters } from 'vuex'
import { userService } from '@/api'
export default {
  name: 'UserEdit', // 这个name属性很重要，以后别的组件引用就用这个名字
  props: ['initData'], // 用来初始化数据的，用父组件传递的数据
  // 页面数据
  data () {
    return {
      pageConfig: {
        isSaving: false
      },
      userInfo: {
        id: '',
        userName: '',
        nickName: '',
        email: '',
        passWord: '',
        moduser: 'admin',
        modtime: new Date()
      },
      // 校验规则
      userInfoFormRules: {
        userName: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        nickName: [
          {required: true, message: '请输入昵称', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'}
        ],
        passWord: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }
    }
  },
  // 在页面构造完成之后自动执行的方法
  created () {
    console.log(this.initData)
    if (this.initData.mode === 'edit') { // 编辑页面数据赋值
      this.userInfo.id = this.initData.data.id
      this.userInfo.userName = this.initData.data.userName
      this.userInfo.nickName = this.initData.data.nickName
      this.userInfo.email = this.initData.data.email
      this.userInfo.passWord = this.initData.data.passWord
    }
  },
  methods: {
    /**
     * 保存按钮触发方法
     */
    saveUser () {
      console.log('save')
      this.$refs.userInfoForm.validate((valid) => {
        if (valid) {
          this.pageConfig.isSaving = true
          if (this.initData.mode === 'add') { // 新增
            let obj = this.userInfo
            userService.saveUser(obj).then((reData) => {
              console.log(reData)
              this.$message.success('保存成功！')
              this.closeDlg()
              this.$emit('reload') // 保存成功后刷新页面
              this.pageConfig.isSaving = false
            }).catch((error) => {
              this.$message.error(error.message)
              this.pageConfig.isSaving = false
            })
          }
          if (this.initData.mode === 'edit') { // 编辑
            let obj = this.userInfo
            userService.updateUser(obj).then(() => {
              this.$message.success('修改成功！')
              this.closeDlg()
              this.$emit('reload') // 保存成功后刷新页面
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
  }
}
</script>
<style scoped>
  
</style>
