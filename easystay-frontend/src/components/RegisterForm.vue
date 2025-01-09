<template>
  <form @submit.prevent="onSubmitAction(userRef, password)">
    <div class="mb-3">
      <label for="firstname" class="form-label">Nome</label>
      <input
        v-model="userRef.nome"
        required
        type="text"
        placeholder="Nome"
        class="form-control"
        id="firstname"
      />
    </div>
    <div class="mb-3">
      <label for="lastname" class="form-label">Cognome</label>
      <input
        v-model="userRef.cognome"
        required
        type="text"
        placeholder="Cognome"
        class="form-control"
        id="lastname"
      />
    </div>
    <div class="mb-3">
      <label for="emailAddress" class="form-label">Indirizzo email</label>
      <input
        v-model="userRef.email"
        required
        type="email"
        placeholder="E-Mail"
        class="form-control"
        id="emailAddress"
      />
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">Password</label>
      <div class="input-group">
        <input
          :required="passwordRequired"
          v-model="password"
          placeholder="Password"
          :type="showPassword ? 'text' : 'password'"
          class="form-control"
          id="password"
        />
        <!-- Bootstrap eye show password -->
        <div class="input-group-addon">
          <span class="input-group-text">
            <i
              @click="showPassword = !showPassword"
              :class="showPassword ? 'bi bi-eye-slash' : 'bi bi-eye'"
              aria-hidden="true"
            ></i>
          </span>
        </div>
      </div>
    </div>
    <button type="submit" class="btn btn-primary">Invia</button>
  </form>
</template>

<script lang="ts">
import type { User } from '@/interfaces/user.ts'
import { ref } from 'vue'

export default {
  name: 'RegisterForm',
  props: {
    user: {
      type: Object as () => User,
      required: true,
    },
    onSubmitAction: {
      type: Function,
      required: true,
    },
    passwordRequired: {
      type: Boolean,
      default: true,
    },
  },
  setup(props) {
    const userRef = ref<User>(props.user)
    const password = ref<string>('')
    return { userRef, password }
  },
  data() {
    return {
      showPassword: false,
    }
  },
}
</script>
