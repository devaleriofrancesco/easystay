<template>
  <main class="main-container">
    <h1 class="text-center">Profilo Admin</h1>
    <div class="container">
      <ProfileTabsWrapper :tabs="tabs" />
    </div>
  </main>
</template>

<script lang="ts">
import ProfileTabsWrapper from '@/components/ProfileTabsWrapper.vue'

export default {
  name: 'HomeView',
  components: { ProfileTabsWrapper },
  data() {
    return {
      tabs: [
        {
          id: 'bookings',
          name: 'Prenotazioni',
          component: 'BookingCalendarTab',
        },
      ],
    }
  },
}
</script>
