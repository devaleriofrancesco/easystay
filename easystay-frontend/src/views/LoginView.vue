<template>
  <main class="main-container">
    <div class="container mb-lg-5">
      <h1>Login</h1>
      <form @submit.prevent="userStore.login(email, password)">
        <div class="form-group">
          <label for="email">E-Mail</label>
          <input type="email" v-model="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Indirizzo email">
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" v-model="password" class="form-control" id="password" placeholder="Password">
        </div>
        <button type="submit" class="btn-cta mt-2">Entra</button>
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
}
</script>

<style scoped lang="scss"></style>
