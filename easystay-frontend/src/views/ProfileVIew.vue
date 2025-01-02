<template>
  <main class="main-container">
    <div class="container text-center">
      <h1>Benvenuto {{ user.nome }} {{ user.cognome }}</h1>
    </div>
    <div class="container">
      <ProfileTabsWrapper :tabs="tabs" />
    </div>
  </main>
</template>

<script lang="ts">
import { useUsers } from '@/stores/user.ts'
import type { Tab } from '@/interfaces/tab.ts'
import ProfileTabsWrapper from '@/components/ProfileTabsWrapper.vue'

export default {
  name: 'ProfileView',
  components: { ProfileTabsWrapper },
  data() {
    return {
      user: useUsers().userData,
      tabs: <Tab[]>[
        {
          id: 'list-bookings',
          name: 'Prenotazioni',
          component: 'BookingListTab',
        },
        {
          id: 'list-profile',
          name: 'Profilo',
          component: 'ProfileTab',
        },
      ],
    }
  },
}
</script>
