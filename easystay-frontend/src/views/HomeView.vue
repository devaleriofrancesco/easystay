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
            src: 'https://placehold.co/600x400',
            title: 'Image 1',
            description: 'Description 1',
          },
          {
            src: 'https://placehold.co/600x400',
            title: 'Image 2',
            description: 'Description 2',
          },
          {
            src: 'https://placehold.co/600x400',
            title: 'Image 3',
            description: 'Description 3',
          },
        ]
      }
    },
  }
</script>
