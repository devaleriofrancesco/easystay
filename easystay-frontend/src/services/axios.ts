// axios service with error handling

import axios from 'axios'
import { useUsers } from '@/stores/user.ts'
import router from '@/router'

const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_BACKEND_API_URL,
})

// Add a request interceptor to add the token to the request
axiosInstance.interceptors.request.use(
  (config) => {
    const userStore = useUsers()
    if (userStore.token) {
      config.headers.Authorization = `Bearer ${userStore.token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  },
)

// Add a response interceptor to handle errors
axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      // handle expired token
      if (error.response.status === 401) {
        console.log('Token expired, session will be cleared')
        useUsers()
          .logout()
          .then(() => {
            // redirect to login page
            router.push({ name: 'login' })
          })
      }

      // Handle known errors
      console.log('Response error', error.response)
    } else {
      // Handle unknown errors
      console.log('Unknown error', error)
    }
    // Prevent error from being logged to the console
    return Promise.reject(error)
  },
)

export default axiosInstance
