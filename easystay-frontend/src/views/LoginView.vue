<template>
  <main class="main-container">
    <div class="container mb-lg-5">
      <h1>Login</h1>
      <form @submit.prevent="authenticate">
        <div class="form-group">
          <label for="email">E-Mail</label>
          <input
            type="email"
            required
            v-model="email"
            class="form-control"
            id="email"
            placeholder="Indirizzo email"
          />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            type="password"
            required
            v-model="password"
            class="form-control"
            id="password"
            placeholder="Password"
          />
        </div>
        <button type="submit" class="btn-cta mt-2">Entra</button>
        <RouterLink to="/register" class="btn-link mx-2">Registrati</RouterLink>
      </form>
    </div>
  </main>
</template>

<script lang="ts">
import { useUsers } from '@/stores/user.ts'

export default {
  name: 'LoginView',
  data() {
    return {
      email: '',
      password: '',
      userStore: useUsers(),
    }
  },
  methods: {
    authenticate() {
      this.userStore.authenticate(this.email, this.password, this.$route.query.redirect as string)
    },
  },
}
</script>

<style scoped lang="scss"></style>
