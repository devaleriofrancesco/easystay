<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <div v-if="bookings.length" class="booking-cards-wrapper">
      <BookingCard
        @delete-booking="deleteBookingAction"
        :booking="booking"
        v-for="booking in bookings"
        :key="booking.id"
      />
    </div>
    <div v-else>
      <p class="text-center">Nessuna prenotazione presente.</p>
    </div>
  </div>
</template>

<script lang="ts">
import type { Tab } from '@/interfaces/tab.ts'
import type { Booking } from '@/interfaces/booking.ts'
import { onMounted, ref } from 'vue'
import { deleteBooking, getBookings } from '@/services/bookingService.ts'
import showToast from '@/services/toaster.ts'
import BookingCard from '@/components/BookingCard.vue'

export default {
  name: 'BookingListTab',
  components: { BookingCard },
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
    const bookings = ref<Booking[]>([])
    const fetchBookings = async () => {
      try {
        bookings.value = await getBookings()
      } catch (error) {
        showToast('Errore durante il recupero delle prenotazioni', 'error')
        console.error('Failed to fetch bookings:', error)
      }
    }

    const deleteBookingAction = async (booking: Booking) => {
      try {
        await deleteBooking(booking.id.toString())
        bookings.value = bookings.value.filter((b) => b.id !== booking.id)
        showToast('Prenotazione cancellata con successo', 'success')
      } catch (error) {
        showToast('Errore durante la cancellazione della prenotazione', 'error')
        console.error('Failed to delete booking:', error)
      }
    }

    onMounted(fetchBookings)
    return { bookings, fetchBookings, deleteBookingAction }
  },
}
</script>

<style scoped lang="scss">
.booking-cards-wrapper {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 10px;
}
</style>
