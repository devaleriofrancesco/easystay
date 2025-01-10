<template>
  <div
    @click.prevent="selectRoomType"
    :class="`card mb-3 ${isSelected ? 'selected' : ''}`"
    style="max-width: 540px"
  >
    <div class="row g-0">
      <div class="col-md-12">
        <div class="card-body">
          <h5 class="card-title">{{ roomType.nome }}</h5>
          <p class="card-text">{{ roomType.descrizione }}</p>
          <p class="card-text">
            <strong>Numero posti letto</strong>: {{ numeroPostiLetto }} | <strong>Prezzo</strong>:
            {{ prezzoTotale }}€
          </p>
        </div>
        <ul class="list-group list-group-flush">
          <li
            v-for="servzio in roomType.servizi"
            :key="servzio.servizio.id"
            class="list-group-item"
          >
            {{ servzio.servizio.nome }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import type { RoomType } from '@/interfaces/roomtype.ts'
import { useBooking } from '@/stores/booking.ts'

export default {
  name: 'RoomTypeSelectionCard',
  props: {
    roomType: {
      type: Object as () => RoomType,
      required: true,
    },
  },
  computed: {
    numeroPostiLetto() {
      return this.roomType.numeroPostiLetto
    },
    isSelected(): boolean {
      return useBooking().getSelectedRoomTypeId === this.roomType.id
    },
    prezzoTotale(): number {
      const prezzoBase = this.roomType.prezzo
      const prezzoServizi = this.roomType.servizi.reduce(
        (acc, servizio) => acc + servizio.servizio.prezzoAddizionale,
        0,
      )
      return prezzoBase + prezzoServizi
    },
  },
  methods: {
    selectRoomType() {
      useBooking().selectRoomType(this.roomType)
    },
  },
}
</script>

<style scoped lang="scss">
.card {
  margin: 0 auto;
  cursor: pointer;
}
.card.selected {
  border: 2px solid blue;
}
</style>
