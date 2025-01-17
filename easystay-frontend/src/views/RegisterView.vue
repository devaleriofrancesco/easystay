<template>
  <main class="main-container">
    <div class="container mb-lg-5">
      <h1>Registrati</h1>
      <RegisterForm :user="user" :onSubmitAction="registerUser" />
    </div>
  </main>
</template>
<script lang="ts">
import RegisterForm from '@/components/forms/RegisterForm.vue'
import type { User } from '@/interfaces/user.ts'
import { useUsers } from '@/stores/user.ts'
export default {
  name: 'RegisterView',
  components: {
    RegisterForm,
  },
  data() {
    return {
      user: {
        nome: '',
        cognome: '',
        email: '',
      } as User,
    }
  },
  methods: {
    async registerUser(user: User, password: string) {
      await useUsers().register(user, password)
    },
  },
}
</script>
