<template>
  <div class="card" style="width: 18rem">
    <img :src="image" class="card-img-top" :alt="booking.stanza.tipoStanza.nome" />
    <div class="card-body">
      <h5 class="card-title">{{ booking.stanza.tipoStanza.nome }}</h5>
      <p class="card-text">
        Prenotazione #{{ booking.id }} del {{ formatDate(booking.dataCreazione) }}
      </p>
      <div class="d-flex flex-row justify-content-between">
        <a href="#" class="btn btn-primary">Modifica</a>
        <a href="#" class="btn btn-danger">Cancella</a>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import type { Booking } from '@/interfaces/booking.ts'
import type { RoomType } from '@/interfaces/roomtype.ts'
import { getRoomTypeImages } from '@/services/roomTypeService.ts'

export default {
  name: 'BookingCard',
  props: {
    booking: {
      type: Object as () => Booking,
      required: true,
    },
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
  },
}
</script>
