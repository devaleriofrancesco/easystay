<template>
  <main class="main-container">
    <ImageSlider :images="images" />
    <div class="container mt-5">
      <hr />
      <div class="row">
        <div class="col-5">
          <h1>EasyStay - la scelta semplice per un soggiorno indimenticabile.</h1>
          <hr />
          <p>Lo stile italiano dell’ospitalità alle porte di Bari.</p>
        </div>
        <div class="col-2"></div>
        <div class="col-5">
          <p><strong>Benvenuti a EasyStay.</strong></p>
          <p>
            <strong
              >Nel cuore di Bari, il nostro hotel è il luogo perfetto per chi cerca un’esperienza di
              soggiorno unica, dove il comfort si unisce all’eleganza.</strong
            >
          </p>
          <p>
            Circondati da un’atmosfera accogliente e raffinata, potrete rilassarvi nelle nostre
            camere curate nei minimi dettagli, pensate per soddisfare ogni vostra esigenza. Che
            siate in viaggio per lavoro o per piacere, il EasyStay è il punto di partenza ideale per
            scoprire le meraviglie di Bari. Approfittate dei nostri servizi esclusivi e lasciatevi
            coccolare dal nostro staff, sempre pronto a rendere il vostro soggiorno davvero
            indimenticabile.
          </p>
          <p>Prenotate ora e preparatevi a vivere momenti di relax, lusso e autenticità.</p>
        </div>
      </div>
    </div>
  </main>
</template>

<script lang="ts">
import ImageSlider from '@/components/sliders/ImageSlider.vue'
import type { Image } from '@/interfaces/Image.ts'

export default {
  components: {
    ImageSlider,
  },
  data() {
    const viteImagesUrl = import.meta.env.VITE_BACKEND_IMAGES_URL
    return {
      images: <Image[]>[
        {
          src: `${viteImagesUrl}/homepage/ingresso.webp`,
          title: 'Ingresso hotel',
          description: 'L’ingresso dell’hotel EasyStay.',
        },
        {
          src: `${viteImagesUrl}/homepage/single-room.webp`,
          title: 'Single Room',
          description: 'Camera singola con letto matrimoniale',
        },
        {
          src: `${viteImagesUrl}/homepage/family-room.webp`,
          title: 'Family Room',
          description: 'Camera familiare con letto matrimoniale e due letti singoli',
        },
      ],
    }
  },
}
</script>
