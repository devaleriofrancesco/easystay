<template>
  <main>
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
    return {
      images: <Image[]>[
        {
          src: 'https://image-tc.galaxy.tf/wijpeg-z3e5ko7480vw6p0paqsuh7l7/1-homepage.jpg?width=1920',
          title: 'Ingresso hotel',
          description: 'L’ingresso dell’hotel EasyStay.',
        },
        {
          src: 'https://image-tc.galaxy.tf/wijpeg-2eng663e2pw1raqzcxpdhqym/cosmohotelpalace-16-simonabrunoph-suite.jpg?width=1920',
          title: 'Family Room',
          description: 'Family room con stanze comunicanti',
        },
        {
          src: 'https://image-tc.galaxy.tf/wijpeg-fi5usdgc3yeyu6ozlbgiq3k3/cosmohotelpalace-7-simonabrunoph-classic-double-room.jpg?width=1920',
          title: 'Double Room',
          description: 'Camera doppia con letto matrimoniale',
        },
      ],
    }
  },
}
</script>
