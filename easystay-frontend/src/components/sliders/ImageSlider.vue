<template>
  <div :id="sliderId" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators" v-if="showIndicators">
      <button
        v-for="(image, index) in images"
        :key="index"
        type="button"
        :data-bs-target="`#${sliderId}`"
        :data-bs-slide-to="index"
        :class="{ active: index == 0 }"
        aria-current="true"
        :aria-label="`Slide ${index + 1}`" />
    </div>
    <div class="carousel-inner">
      <div
        v-for="(image, index) in images"
        :key="index"
        class="carousel-item"
        :class="{ active: index == 0 }"
      >
        <img :src="image.src" class="d-block w-100 carousel-image" :style="carouselImageStyle" :alt="image.title" />
        <div class="carousel-caption d-none d-md-block">
          <h5>{{ image.title }}</h5>
          <p v-if="image.description">{{ image.description }}</p>
        </div>
      </div>
    </div>
    <button
      v-if="showControls"
      class="carousel-control-prev"
      type="button"
      :data-bs-target="`#${sliderId}`"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
      v-if="showControls"
      class="carousel-control-next"
      type="button"
      :data-bs-target="`#${sliderId}`"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</template>

<script lang="ts">
import type { Image } from '@/interfaces/Image.ts'
import { Carousel } from 'bootstrap'

export default {
  name: 'ImageSlider',
  props: {
    images: {
      type: Array as () => Image[],
      required: true,
    },
    sliderId: {
      type: String,
      default: 'carouselExampleCaptions',
    },
    showIndicators: {
      type: Boolean,
      default: true,
    },
    showControls: {
      type: Boolean,
      default: true,
    },
    carouselImageHeight: {
      type: String,
      default: '800px',
    },
  },
  computed: {
    carouselImageStyle() {
      return {
        height: this.carouselImageHeight,
      }
    },
  },
  mounted() {
    new Carousel(document.querySelector(`#${this.sliderId}`)!, {
      interval: 5000,
    })
  },
}
</script>

<style lang="scss">
.carousel-image {
  object-fit: cover;
}
</style>
