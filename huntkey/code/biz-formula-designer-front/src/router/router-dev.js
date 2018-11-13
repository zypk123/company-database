// 开发中
import Playground from '@/components/playground/playground'
// import RelEdmClassEdit from '@/components/formula-designer/RelEdmClassEdit'
// import EdmClassesTree from '@/components/formula-designer/EdmClassesTree'
// import VarEdit from '@/components/var-manage/VarEdit'

const routes = [
  {
    path: '/playground',
    component: Playground,
    meta: {
      showMenu: true
    }
  }
  // {
  //   path: '/dev/rel-edm-class-edit',
  //   name: 'relEdmClassEdit',
  //   component: RelEdmClassEdit
  // },
  // {
  //   path: '/dev/edm-class-tree',
  //   name: 'edmClassesTree',
  //   component: EdmClassesTree
  // },
  // {
  //   path: '/dev/var-edit',
  //   component: VarEdit,
  //   meta: {
  //     showMenu: true
  //   }
  // }
]
export default routes
