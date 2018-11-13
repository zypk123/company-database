import UserManage from '@/components/UserManage'
const routes = [
  {
    path: '/',
    redirect: 'user-manage'
  },
  {
    path: '/user-manage',
    component: UserManage
  }
]
export default routes
