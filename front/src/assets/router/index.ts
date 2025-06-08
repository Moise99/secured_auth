import { createRouter, createWebHistory } from 'vue-router'
import Login from '../components/auth/LoginForm.vue'
import Register from '../components/auth/RegisterForm.vue'
import Dashboard from '../pages/Dashboard.vue'
import Home from '../pages/Home.vue'


const routes = [
  { path: '/', component: Home },
  { path: '/login', name: 'login', component: Login },
  { path: '/register', name: 'register', component: Register },
  {
    path: '/dashboard', name: 'dashboard',
    component: Dashboard,
    meta: { requiresAuth: true },
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach(async (to, from, next) => {
  const { useAuthStore } = await import('../store/auth')
  const auth = useAuthStore()

  if (to.meta.requiresAuth && !auth.token) {
    next('/login')
  } else {
    next()
  }
})

export default router
