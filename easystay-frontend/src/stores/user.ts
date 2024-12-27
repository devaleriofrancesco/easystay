// pinia store for user
import { defineStore } from 'pinia'

export const useUsers = defineStore('users', {
  state: () => ({
    userData: null,
    // ...
  }),

  actions: {
    async login(username: string, password: string) {
      console.log('registering user', username, password)
    },
  },
})
