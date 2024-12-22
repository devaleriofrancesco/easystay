<template>
  <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide-to="0"
        class="active"
        aria-current="true"
        aria-label="Slide 1"
      ></button>
      <button
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide-to="1"
        aria-label="Slide 2"
      ></button>
      <button
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide-to="2"
        aria-label="Slide 3"
      ></button>
    </div>
    <div class="carousel-inner">
      <div
        v-for="(image, index) in images"
        :key="index"
        class="carousel-item"
        :class="{ active: index == 0 }"
      >
        <img :src="image.src" class="d-block w-100 carousel-image" :alt="image.title" />
        <div class="carousel-caption d-none d-md-block">
          <h5>{{ image.title }}</h5>
          <p v-if="image.description">{{ image.description }}</p>
        </div>
      </div>
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#carouselExampleCaptions"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#carouselExampleCaptions"
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
  },
  mounted() {
    new Carousel(document.querySelector('#carouselExampleCaptions')!, {
      interval: 5000,
    })
  },
}
</script>

<style lang="scss">
.carousel-image {
  height: 800px;
  object-fit: cover;
}
</style>
