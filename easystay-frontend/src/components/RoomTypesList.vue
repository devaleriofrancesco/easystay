<template>
  <div class="container">
    <div class="row" v-for="(room, index) in roomTypes" :key="room.id">
      <div class="col-5">
        <ImageSlider
          :slider-id="`roomtype-slider-${room.id}`"
          :images="images(room)"
          :show-controls="true"
          :show-indicators="false"
          carousel-image-height="unset"
        />
      </div>
      <div class="col-2"></div>
      <div class="col-5">
        <h3>{{ room.nome }}</h3>
        <p>Metri Quadri: {{ room.metriQuadri }}</p>
        <p>{{ sumPostiLetto(room) }} posti letto</p>
        <p>Servizi:</p>
        <ul class="list-group mb-2">
          <li class="list-group-item" v-for="servizio in room.servizi" :key="servizio.servizio.id">
            {{ servizio.qty }} {{ servizio.servizio.nome }}
          </li>
        </ul>
        <p>{{ room.descrizione }}</p>
      </div>
      <hr v-if="index !== roomTypes.length - 1" />
    </div>
  </div>
</template>

<style lang="scss"></style>

<script lang="ts">
import { onMounted, ref } from 'vue'
import { getRoomTypeImages, getRoomTypes } from '@/services/roomTypeService.ts'
import type { RoomType } from '@/interfaces/roomtype.ts'
import ImageSlider from '@/components/sliders/ImageSlider.vue'

export default {
  name: 'RoomTypesList',
  components: { ImageSlider },
  setup() {
    const roomTypes = ref<RoomType[]>([])
    const fetchRoomTypes = async () => {
      try {
        roomTypes.value = await getRoomTypes()
      } catch (error) {
        console.error('Failed to fetch room types:', error)
      }
    }
    onMounted(fetchRoomTypes)
    return { roomTypes, fetchRoomTypes }
  },
  methods: {
    sumPostiLetto(roomType: RoomType) {
      return roomType.numeroAdulti + roomType.numeroBambini
    },
    images(roomType: RoomType) {
      return getRoomTypeImages(roomType)
    },
  },
}
</script>
