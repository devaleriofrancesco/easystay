// pinia store for user
import { defineStore } from 'pinia'
import type { AuthResponse, User } from '@/interfaces/user.ts'
import axiosInstance from '@/services/axios.ts'
import showToast from '@/services/toaster.ts'
import router from '@/router'
import { AxiosError } from 'axios'

export const useUsers = defineStore('users', {
  state: () => ({
    token: '',
    userData: {} as User,
    isLoggedIn: false,
  }),
  persist: true,
  actions: {
    async authenticate(email: string, password: string) {
      try {
        const { data } = await axiosInstance.post<AuthResponse>('/auth/token', { email, password })
        this.token = data.token
        this.userData = data.user
        this.isLoggedIn = true
        await router.push({ name: 'profilo' })
        showToast('Benvenuto', 'success')
      } catch (error) {
        showToast("Errore durante l'autenticazione", 'error')
        console.log('Error authenticating user:', error)
      }
    },
    async register(user: User) {
      try {
        const { data } = await axiosInstance.post<AuthResponse>(`/auth/register`, user)
        this.token = data.token
        this.userData = data.user
        this.isLoggedIn = true
        await router.push({ name: 'profilo' })
      } catch (error) {
        console.error('Error registering user:', error)
        throw error
      }
    },
    async updateUser(user: User, password: string | null = null) {
      try {
        const { data } = await axiosInstance.put<AuthResponse>(`/user`, { ...user, password })
        this.userData = data.user
        showToast('Profilo aggiornato', 'success')
      } catch (error) {
        if (error instanceof AxiosError) {
          showToast(error.response?.data.detail, 'error')
        }
        console.log('Error updating user:', error)
      }
    },
    async logout() {
      this.token = ''
      this.userData = {} as User
      this.isLoggedIn = false
      await router.push({ name: 'login' })
    },
  },
})
