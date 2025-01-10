<template>
  <!-- slide content will be a card -->

  <div id="roomTypeSlider" class="carousel carousel-dark slide">
    <div class="carousel-inner">
      <div
        v-for="(roomType, index) in roomTypes"
        :key="index"
        class="carousel-item"
        :class="{ active: index == 0 }"
      >
        <RoomTypeSelectionCard :roomType="roomType" />
      </div>
    </div>
    <div class="container">
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#roomTypeSlider"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#roomTypeSlider"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
</template>
<script lang="ts">
import type { RoomType } from '@/interfaces/roomtype.ts'
import RoomTypeSelectionCard from '@/components/cards/RoomTypeSelectionCard.vue'

export default {
  name: 'RoomTypeSlider',
  components: { RoomTypeSelectionCard },
  props: {
    roomTypes: {
      type: Array as () => RoomType[],
      required: true,
    },
  },
}
</script>

<style scoped lang="scss">
.carousel-control-prev {
  justify-content: start;
}

.carousel-control-next {
  justify-content: end;
}
.carousel {
  min-height: 450px;
}
</style>
