<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <div>
      <RegisterForm :password-required="false" :onSubmitAction="updateUser" :user="user" />
    </div>
  </div>
</template>

<script lang="ts">
import type { Tab } from '@/interfaces/tab.ts'
import type { User } from '@/interfaces/user.ts'
import { useUsers } from '@/stores/user.ts'
import { ref } from 'vue'
import RegisterForm from '@/components/RegisterForm.vue'

export default {
  name: 'ProfileTab',
  components: { RegisterForm },
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
  methods: {
    async updateUser(user: User, password: string) {
      await useUsers().updateUser(user, password)
    },
  },
}
</script>
