import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import { useUsers } from '@/stores/user.ts'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta: {
        title: 'Home',
      },
    },
    {
      path: '/le-nostre-camere',
      name: 'le-nostre-camere',
      meta: {
        title: 'Le nostre camere',
      },
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/RoomsView.vue'),
    },
    {
      path: '/book',
      name: 'book',
      meta: {
        title: 'Prenota',
        restricted: true,
      },
      component: () => import('../views/BookView.vue'),
    },
    {
      path: '/login',
      name: 'login',
      meta: {
        title: 'Login',
      },
      component: () => import('../views/LoginView.vue'),
    },
    {
      path: '/register',
      name: 'register',
      meta: {
        title: 'Registrati',
      },
      component: () => import('../views/RegisterView.vue'),
    },
    {
      path: '/profilo',
      name: 'profilo',
      meta: {
        title: 'Profilo',
        restricted: true,
      },
      component: () => import('../views/ProfileVIew.vue'),
    },
    {
      path: '/admin',
      name: 'admin',
      meta: {
        title: 'Admin',
        restricted: true,
        admin: true,
      },
      component: () => import('../views/Admin/HomeView.vue'),
    },
  ],
})

router.beforeEach((to, from, next) => {
  document.title = `EasyStay - ${to.meta.title}` || 'EasyStay'

  const userData = useUsers()

  // redirect to profile page if logged in and trying to access login page
  if (to.name === 'login' && userData.isLoggedIn) {
    next({ name: 'profilo' })
    return
  }

  // redirect to login page if not logged in and trying to access a restricted page
  if (to.meta.restricted && !userData.isLoggedIn) {
    next({ name: 'login', query: { redirect: to.fullPath } })
    return
  }

  next()
})

export default router
