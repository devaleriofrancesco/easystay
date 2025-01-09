<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <div>
      <form @submit.prevent="userStore.updateUser(user, password)">
        <div class="mb-3">
          <label for="firstname" class="form-label">Nome</label>
          <input v-model="user.nome" required type="text" class="form-control" id="firstname" />
        </div>
        <div class="mb-3">
          <label for="lastname" class="form-label">Cognome</label>
          <input v-model="user.cognome" required type="text" class="form-control" id="lastname" />
        </div>
        <div class="mb-3">
          <label for="emailAddress" class="form-label">Indirizzo email</label>
          <input
            v-model="user.email"
            required
            type="email"
            class="form-control"
            id="emailAddress"
          />
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input v-model="password" type="password" class="form-control" id="password" />
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
    </div>
  </div>
</template>

<script lang="ts">
import type { Tab } from '@/interfaces/tab.ts'
import type { User } from '@/interfaces/user.ts'
import { useUsers } from '@/stores/user.ts'
import { ref } from 'vue'

export default {
  name: 'ProfileTab',
  props: {
    tab: {
      type: {} as Tab,
      required: true,
    },
    index: {
      type: Number,
      required: true,
    },
  },
  setup() {
    const user = ref<User>(useUsers().userData)
    const password = ref<string>('')
    return { user, password }
  },
  data() {
    return {
      userStore: useUsers(),
    }
  },
}
</script>
