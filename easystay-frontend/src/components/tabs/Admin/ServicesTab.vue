<template>
  <div
    :class="`tab-pane fade show ${index == 0 ? 'active' : ''}`"
    :id="tab.id"
    role="tabpanel"
    :aria-labelledby="`${tab.id}-list`"
  >
    <div>
      <button @click.prevent="onCreateButtonPressed" type="button" class="btn btn-cta-mini mb-3">
        Aggiungi nuovo servizio
      </button>

      <div class="d-flex services-container">
        <div v-for="service in services" :key="service.id" class="card">
          <div class="card-body">
            <h5 class="card-title">{{ service.nome }}</h5>
            <p class="card-text">Prezzo addizionale: {{ service.prezzoAddizionale }}€</p>
            <div class="d-flex justify-content-between">
              <a
                href="#"
                @click.prevent="onEditServiceButtonPressed(service)"
                class="btn btn-cta-mini"
                >Modifica</a
              >
              <button @click.prevent="onDeleteServiceButtonPressed(service)" class="btn btn-danger">
                Elimina
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Modal -->
      <div
        v-if="selectedService"
        class="modal fade"
        id="serviceModal"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabindex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <form @submit.prevent="saveService">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">
                  Modifica servizio {{ selectedService.nome }}
                </h1>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                <div class="mb-3">
                  <label for="serviceName" class="form-label">Nome</label>
                  <input
                    type="text"
                    class="form-control"
                    id="serviceName"
                    required
                    v-model="selectedService.nome"
                  />
                </div>
                <div class="mb-3">
                  <label for="servicePrice" class="form-label mt-2">Prezzo addizionale</label>
                  <input
                    type="number"
                    class="form-control"
                    id="servicePrice"
                    required
                    v-model="selectedService.prezzoAddizionale"
                  />
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                  Close
                </button>
                <button type="submit" class="btn btn-success">Salva</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import type { Tab } from '@/interfaces/tab.ts'
import { nextTick, onMounted, ref } from 'vue'
import type { Servizio } from '@/interfaces/roomtype.ts'
import {
  createService,
  deleteService,
  getAllServices,
  updateService,
} from '@/services/serviziService.ts'
import { Modal } from 'bootstrap'
import { emitter } from '@/services/mitt.ts'

export default {
  name: 'ServicesTab',
  props: {
    tab: {
      type: Object as () => Tab,
      required: true,
    },
    index: {
      type: Number,
      required: true,
    },
  },
  setup() {
    const services = ref<Servizio[]>([])
    const selectedService = ref<Servizio | null>(null)
    const modal = ref<Modal | null>(null)

    const getServicesAction = async () => {
      services.value = await getAllServices()
    }

    onMounted(getServicesAction)
    return { services, getServicesAction, selectedService, modal }
  },
  methods: {
    async onEditServiceButtonPressed(service: Servizio) {
      this.selectedService = service
      await nextTick()
      this.modal = new Modal('#serviceModal')
      this.modal.show()
    },
    async onDeleteServiceButtonPressed(service: Servizio) {
      await deleteService(service.id)
      await this.getServicesAction()
      emitter.emit('service-deleted')
    },
    async onCreateButtonPressed() {
      this.selectedService = {
        id: 0,
        nome: '',
        prezzoAddizionale: 0,
      }
      await nextTick()
      this.modal = new Modal('#serviceModal')
      this.modal.show()
    },
    async saveService() {
      if (this.selectedService) {
        if (this.selectedService.id) {
          // update
          await updateService(this.selectedService)
        } else {
          // create
          await createService(this.selectedService)
        }
        await this.getServicesAction()
        this.modal?.hide()
        emitter.emit('service-saved')
      }
    },
  },
}
</script>

<style scoped lang="scss">
.services-container {
  flex-wrap: wrap;
  gap: 10px;
}
.services-container > div {
  flex: 40%;
}
</style>
