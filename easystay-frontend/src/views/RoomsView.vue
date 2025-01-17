<template>
  <main>
    <ImageSlider
      slider-id="page-slider"
      :images="images"
      :show-controls="false"
      :show-indicators="false"
    />
    <div class="container mt-3 text-center">
      <hr />
      <h3>CAMERE</h3>
      <h4>Un mix armonioso di stile, comfort e praticità.</h4>
      <p>
        Le Camere Classic offrono la soluzione perfetta per un soggiorno breve, sia per lavoro che
        per piacere.
      </p>
      <p>
        Gli ambienti, caratterizzati da tonalità soft e da un’atmosfera accogliente, creano uno
        spazio ideale per rilassarsi.
      </p>
      <p>
        Ogni camera, curata nei dettagli e arredata con eleganza, dispone di bagno privato con
        doccia o vasca e di tutti i servizi più moderni per garantire un’esperienza confortevole.
      </p>
      <hr />
    </div>
    <RoomTypesList />
  </main>
</template>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
<script lang="ts">
import ImageSlider from '@/components/sliders/ImageSlider.vue'
import RoomTypesList from '@/components/RoomTypesList.vue'

export default {
  components: {
    RoomTypesList,
    ImageSlider,
  },
  data() {
    return {
      images: [
        {
          src: 'https://image-tc.galaxy.tf/wijpeg-fi5usdgc3yeyu6ozlbgiq3k3/cosmohotelpalace-7-simonabrunoph-classic-double-room.jpg?width=1920',
          title: '',
          description: '',
        },
      ],
    }
  },
}
</script>
