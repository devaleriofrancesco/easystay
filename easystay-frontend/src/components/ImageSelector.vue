<template>
  <div class="image-selector">
    <div
      v-for="(image, index) in images"
      :key="index"
      class="image-selector__image"
      @click.prevent="deleteImage(image.position)"
      :style="{ backgroundImage: `url(${image.src})` }"
    >
      <i class="bi bi-trash-fill image-selector__icon"></i>
    </div>
  </div>
  <div>
    <label :for="`image-selector__input-${roomType.id}`" class="btn btn-cta-mini">
      Aggiungi immagine
      <input
        type="file"
        @click="resetValue"
        @change="uploadImage"
        accept="image/*"
        :id="`image-selector__input-${roomType.id}`"
        class="d-none"
      />
    </label>
  </div>
</template>

<script lang="ts">
import type { RoomType } from '@/interfaces/roomtype.ts'
import { getSelectorImagesByRoomType } from '@/services/roomTypeService.ts'
import showToast from '@/services/toaster.ts'

export interface SelectorImage {
  src: string
  position: number
}

export default {
  name: 'ImageSelector',
  props: {
    roomType: {
      type: Object as () => RoomType,
      required: true,
    },
  },
  data() {
    return {
      images: getSelectorImagesByRoomType(this.roomType),
    }
  },
  methods: {
    uploadImage(event: Event) {
      const target = event.target as HTMLInputElement
      const file = target.files?.[0]
      if (!file) return
      const reader = new FileReader()
      reader.onload = (e) => {
        this.$props.roomType.galleriaImmagini.push({
          path_immagine: e.target!.result as string,
          posizione: this.images.length + 1,
          id: 0,
        })
        this.images.push({ src: e.target!.result as string, position: this.images.length + 1 })
      }
      reader.readAsDataURL(file)
    },
    resetValue(event: Event) {
      const element = event.target as HTMLInputElement
      element.value = ''
    },
    deleteImage(imagePosition: number) {
      if (this.images.length === 1) {
        showToast("Devi avere almeno un'immagine", 'error')
        return
      }
      this.$props.roomType.galleriaImmagini.find(
        (gallery) => gallery.posizione === imagePosition,
      )!.posizione = -1
      this.images = this.images.filter((image) => image.position !== imagePosition)
    },
  },
}
</script>

<style scoped lang="scss">
.image-selector {
  display: flex;
  gap: 20px;
  flex-flow: row wrap;

  .image-selector__image {
    width: 100px;
    height: 100px;
    background-size: cover;
    background-position: center;
    margin-bottom: 10px;
    cursor: pointer;
    position: relative;
  }

  .image-selector__icon {
    position: absolute;
    bottom: 0;
    right: 0;
    color: white;
    cursor: pointer;
    width: 100%;
    background-color: rgba(0, 0, 0, 0.5);
  }

  .image-selector__icon:before {
    position: relative;
    right: 0;
    left: 80px;
  }
}
</style>
