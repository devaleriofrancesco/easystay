import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta: {
        title: 'Home',
      }
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
      path: '/login',
      name: 'login',
      meta: {
        title: 'Login',
      },
      component: () => import('../views/LoginView.vue'),
    }
  ],
})

router.beforeEach((to, from, next) => {
  document.title = `EasyStay - ${to.meta.title}` || 'EasyStay'
  next()
})

export default router
