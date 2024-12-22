<template>
  <main>
    <ImageSlider :images="images" />
  </main>
</template>

<script lang="ts">
  import ImageSlider from '@/components/ImageSlider.vue'
  import type { Image } from '@/interfaces/Image.ts'

  export default {
    components: {
      ImageSlider,
    },
    data() {
      return {
        images: <Image[]> [
          {
            src: 'https://image-tc.galaxy.tf/wijpeg-z3e5ko7480vw6p0paqsuh7l7/1-homepage.jpg?width=1920',
            title: 'Image 1',
            description: 'Description 1',
          },
          {
            src: 'https://image-tc.galaxy.tf/wijpeg-2eng663e2pw1raqzcxpdhqym/cosmohotelpalace-16-simonabrunoph-suite.jpg?width=1920',
            title: 'Image 2',
            description: 'Description 2',
          },
          {
            src: 'https://image-tc.galaxy.tf/wijpeg-fi5usdgc3yeyu6ozlbgiq3k3/cosmohotelpalace-7-simonabrunoph-classic-double-room.jpg?width=1920',
            title: 'Image 3',
            description: 'Description 3',
          },
        ]
      }
    },
  }
</script>
