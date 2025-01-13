<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <div>
      <DatePicker expanded v-model.range="range" mode="date" />
    </div>
    <h3 class="text-center mt-3">
      Lista prenotazioni periodo {{ range.start.toLocaleDateString() }} -
      {{ range.end.toLocaleDateString() }}
    </h3>
    <div class="row g-3 align-items-center justify-content-center">
      <div class="col-auto">
        <label for="nameSearchField" class="col-form-label">Nome e cognome</label>
      </div>
      <div class="col-auto">
        <input
          type="text"
          id="nameSearchField"
          class="form-control"
          aria-describedby="nameSearchFieldHelpInline"
          v-model="nameSearchTerm"
        />
      </div>
      <div class="col-auto">
        <span id="nameSearchFieldHelpInline" class="form-text">
          Ricerca prenotazioni per nominativo.
        </span>
      </div>
    </div>
    <div v-if="bookings.length" class="booking-cards-wrapper">
      <BookingCard
        @delete-booking="deleteBookingAction"
        class="mt-3"
        :booking="booking"
        v-for="booking in bookings"
        :key="booking.id"
      />
    </div>
    <div v-else>
      <p class="text-center">Nessuna prenotazione trovata.</p>
    </div>
  </div>
</template>

<script lang="ts">
import type { Tab } from '@/interfaces/tab.ts'
import { DatePicker } from 'v-calendar'
import 'v-calendar/style.css'
import { onMounted, ref, watch } from 'vue'
import type { Booking } from '@/interfaces/booking.ts'
import {
  deleteBooking,
  getBookingsByDates,
  getBookingsByDatesAndUser,
} from '@/services/bookingService.ts'
import showToast from '@/services/toaster.ts'
import BookingCard from '@/components/BookingCard.vue'
import debounce from 'debounce'
import _ from 'lodash'

export default {
  name: 'BookingCalendarTab',
  components: { BookingCard, DatePicker },
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
    const todayDate = new Date()
    const lastDayOfMonth = new Date(todayDate.getFullYear(), todayDate.getMonth() + 1, 0)
    const range = ref({
      start: todayDate,
      end: lastDayOfMonth,
    })

    const getStartByRange = () => {
      return range.value.start
    }

    const getEndByRange = () => {
      const end = _.clone(range.value.end)
      end.setDate(end.getDate() + 1)
      return end
    }

    const getBookingsByCurrentMonthAndYear = async () => {
      try {
        bookings.value = await getBookingsByDates(getStartByRange(), getEndByRange())
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

    const nameSearchTerm = ref('')

    const performSearchByName = debounce(async () => {
      const searchTerm = nameSearchTerm.value.trim()
      if (searchTerm === '') {
        await getBookingsByCurrentMonthAndYear()
        return
      }

      if (searchTerm.length < 3) {
        showToast('Inserisci almeno 3 caratteri per la ricerca', 'warning')
        return
      }

      try {
        bookings.value = await getBookingsByDatesAndUser(
          getStartByRange(),
          getEndByRange(),
          searchTerm.toLowerCase(),
        )
      } catch (error) {
        showToast('Errore durante il recupero delle prenotazioni', 'error')
        console.error('Failed to fetch bookings:', error)
      }
    }, 300)

    // watch for changes in the range object
    watch(range, async () => {
      try {
        bookings.value = await getBookingsByDates(getStartByRange(), getEndByRange())
      } catch (error) {
        showToast('Errore durante il recupero delle prenotazioni', 'error')
        console.error('Failed to fetch bookings:', error)
      }
    })

    // watch for changes in the nameSearchTerm field
    watch(nameSearchTerm, performSearchByName)

    onMounted(getBookingsByCurrentMonthAndYear)
    return {
      getBookingsByCurrentMonthAndYear,
      bookings,
      range,
      deleteBookingAction,
      nameSearchTerm,
    }
  },
}
</script>

<style scoped lang="scss">
.booking-cards-wrapper {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: space-around;
}
</style>
