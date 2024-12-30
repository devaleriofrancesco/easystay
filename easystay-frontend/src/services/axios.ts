// axios service with error handling

import axios from 'axios'

const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_BACKEND_API_URL,
})

axiosInstance.interceptors.response.use(
  response => response,
  error => {
    if (error.response) {
      // Handle known errors
      console.log('Response error', error.response)
    } else {
      // Handle unknown errors
      console.log('Unknown error', error)
    }
    // Prevent error from being logged to the console
    return Promise.reject(error)
  }
)

export default axiosInstance
