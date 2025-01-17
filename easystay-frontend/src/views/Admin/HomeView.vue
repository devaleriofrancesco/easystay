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
        {
          id: 'roomTypes',
          name: 'Tipi di stanze',
          component: 'RoomTypesTab',
        },
        {
          id: 'services',
          name: 'Servizi disponibili',
          component: 'ServicesTab',
        },
        {
          id: 'rooms',
          name: 'Stanze',
          component: 'RoomsTab',
        },
      ],
    }
  },
}
</script>
