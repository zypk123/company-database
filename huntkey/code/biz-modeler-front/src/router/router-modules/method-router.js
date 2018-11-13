import MethodManage from '@/components/method-manager/MethodManage.vue'
import MethodModel from '@/components/method-manager/MethodModel.vue'
import ConModel from '@/components/method-manager/ConvolutionModel.vue'
export default [{
  name: 'methodManage-list',
  path: '/methodManage-list',
  component: MethodManage
}, {
  name: 'methodModel',
  path: '/methodModel',
  component: MethodModel
}, {
  name: 'ConModel',
  path: '/ConModel',
  component: ConModel
}]
