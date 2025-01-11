<template>
  <div class="card" style="width: 18rem">
    <img :src="image" class="card-img-top" :alt="booking.stanza.tipoStanza.nome" />
    <div class="card-body">
      <h5 class="card-title">{{ booking.stanza.tipoStanza.nome }}</h5>
      <p class="card-text">
        <strong>Data check-in</strong>: {{ formatDate(booking.dataCheckIn) }}<br />
        <strong>Data check-out</strong>: {{ formatDate(booking.dataCheckOut) }}
      </p>
      <div class="d-flex flex-row justify-content-between">
        <button
          data-bs-toggle="modal"
          :data-bs-target="`#editBookingModal-${booking.id}`"
          class="btn btn-primary"
        >
          Modifica
        </button>
        <a @click.prevent="deleteBooking" class="btn btn-danger">Cancella</a>
      </div>
    </div>
    <!-- Modal -->
    <div
      class="modal fade"
      :id="`editBookingModal-${booking.id}`"
      tabindex="-1"
      :aria-labelledby="`editBookingModal-${booking.id}`"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalLabel">
              Modifica prenotazione #{{ booking.id }}
            </h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <form @submit.prevent="updateBooking" ref="form">
            <div class="modal-body">
              <div class="mb-3">
                <label for="checkIn" class="form-label">Check-in</label>
                <input
                  required
                  type="date"
                  class="form-control"
                  id="checkIn"
                  v-model="checkInDate"
                />
              </div>
              <div class="mb-3">
                <label for="checkOut" class="form-label">Check-out</label>
                <input
                  required
                  type="date"
                  class="form-control"
                  id="checkOut"
                  v-model="checkOutDate"
                />
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                Chiudi
              </button>
              <input type="submit" class="btn btn-primary" value="Modifica" />
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import type { Booking } from '@/interfaces/booking.ts'
import type { RoomType } from '@/interfaces/roomtype.ts'
import { getRoomTypeImages } from '@/services/roomTypeService.ts'
import { onMounted, ref } from 'vue'
import { Modal } from 'bootstrap'

export default {
  name: 'BookingCard',
  props: {
    booking: {
      type: Object as () => Booking,
      required: true,
    },
  },
  setup(props) {
    const checkInDate = ref<string>(new Date(props.booking.dataCheckIn).toISOString().split('T')[0])
    const checkOutDate = ref<string>(
      new Date(props.booking.dataCheckOut).toISOString().split('T')[0],
    )
    const form = ref<HTMLFormElement | null>(null)
    const modal = ref<Modal | null>(null)
    onMounted(() => {
      modal.value = new Modal(`#editBookingModal-${props.booking.id}`)
    })
    return {
      checkInDate,
      checkOutDate,
      form,
      modal,
    }
  },
  computed: {
    image() {
      return getRoomTypeImages(this.booking.stanza.tipoStanza as RoomType).find(
        (image) => image.posizione === 1,
      )?.src
    },
  },
  methods: {
    formatDate(date: string) {
      return new Date(date).toLocaleDateString()
    },
    deleteBooking() {
      if (confirm('Sei sicuro di voler cancellare questa prenotazione?')) {
        this.$emit('delete-booking', this.booking)
      }
    },
    updateBooking() {
      const closeModalCallback = () => this.modal?.hide()
      const resetInitialValues = () => {
        this.checkInDate = new Date(this.booking.dataCheckIn).toISOString().split('T')[0]
        this.checkOutDate = new Date(this.booking.dataCheckOut).toISOString().split('T')[0]
      }
      this.$emit(
        'update-booking',
        this.booking.id,
        this.checkInDate,
        this.checkOutDate,
        closeModalCallback.bind(this),
        resetInitialValues.bind(this),
      )
    },
  },
}
</script>
