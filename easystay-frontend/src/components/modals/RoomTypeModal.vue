<template>
  <!-- Modal -->
  <div
    class="modal fade"
    :id="`editRoomTypeModal-${roomTypeModel.id}`"
    tabindex="-1"
    :aria-labelledby="`editRoomTypeModal-${roomTypeModel.id}`"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">
            Modifica tipo camera {{ roomTypeModel.nome }}
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <form @submit.prevent="saveRoomType">
          <div class="modal-body">
            <div class="mb-3">
              <label for="roomTypeName" class="form-label">Nome</label>
              <input
                type="text"
                class="form-control"
                id="roomTypeName"
                v-model="roomTypeModel.nome"
              />
            </div>
            <div class="mb-3">
              <label for="roomTypeDescription" class="form-label">Descrizione</label>
              <textarea
                class="form-control"
                id="roomTypeDescription"
                v-model="roomTypeModel.descrizione"
              ></textarea>
            </div>
            <div class="mb-3">
              <label for="roomTypePrice" class="form-label"> Prezzo base </label>
              <input
                type="number"
                class="form-control"
                id="roomTypePrice"
                v-model="roomTypeModel.prezzo"
              />
            </div>
            <div class="mb-3">
              <label for="squareMeters" class="form-label"> Metri quadri </label>
              <input
                type="number"
                class="form-control"
                id="squareMeters"
                v-model="roomTypeModel.metriQuadri"
              />
            </div>
            <div class="mb-3">
              <label for="roomTypeBeds" class="form-label"> Numero posti letto </label>
              <input
                type="number"
                class="form-control"
                id="roomTypeBeds"
                v-model="roomTypeModel.numeroPostiLetto"
              />
            </div>
            <div class="mb-3">
              <label for="adults" class="form-label"> Numero adulti </label>
              <input
                type="number"
                class="form-control"
                id="adults"
                v-model="roomTypeModel.numeroAdulti"
              />
            </div>
            <div class="mb-3">
              <label for="children" class="form-label"> Numero bambini </label>
              <input
                type="number"
                class="form-control"
                id="children"
                v-model="roomTypeModel.numeroBambini"
              />
            </div>
            <div class="mb-3">
              <label for="associatedServices" class="form-label"> Servizi associati </label>
              <ul>
                <li v-for="service in checkedServices" :key="service.servizio.id">
                  <input type="checkbox" v-model="service.checked" :value="service.servizio.id" />
                  {{ service.servizio.nome }}
                </li>
              </ul>
            </div>
            <div class="mb-3">
              <label class="mb-3">Immagini</label>
              <ImageSelector :roomType="roomTypeModel" :key="roomTypeModel.id" />
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
            <input type="submit" class="btn btn-success" value="Salva" />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import ImageSelector from '@/components/ImageSelector.vue'
import type { RoomType, ServizioWrapper } from '@/interfaces/roomtype.ts'
import { onMounted, ref } from 'vue'
import { updateRoomType } from '@/services/roomTypeService.ts'
import { Modal } from 'bootstrap'
import showToast from '@/services/toaster.ts'
import { getAvailableServicesByRoomTypeId } from '@/services/serviziService.ts'
import { AxiosError } from 'axios'

export interface SelectedServiceCheckbox extends ServizioWrapper {
  checked: boolean
}

export default {
  name: 'RoomTypeModal',
  props: {
    roomType: {
      type: Object as () => RoomType,
      required: true,
    },
  },
  components: { ImageSelector },
  setup(props) {
    const roomTypeModel = props.roomType
    const checkedServices = ref<SelectedServiceCheckbox[]>([])

    const modalRef = ref<Modal | null>(null)
    onMounted(() => {
      modalRef.value = new Modal(`#editRoomTypeModal-${roomTypeModel.id}`)
    })

    // set checked services at startup
    checkedServices.value = roomTypeModel.servizi.map((service) => {
      return {
        id: service.id,
        servizio: service.servizio,
        checked: true,
        qty: service.qty,
      }
    })

    const getAllAvailableServices = async () => {
      const availableServices = await getAvailableServicesByRoomTypeId(roomTypeModel.id)
      checkedServices.value.push(
        ...availableServices.map((s) => {
          return {
            id: s.id,
            qty: s.qty,
            servizio: s.servizio,
            checked: false,
          } as SelectedServiceCheckbox
        }),
      )
    }

    onMounted(getAllAvailableServices)

    return { roomTypeModel, checkedServices, getAllAvailableServices, modalRef }
  },
  methods: {
    async saveRoomType() {
      if (!this.checkedServices.find((s) => s.checked)) {
        showToast('Inserire almeno un servizio associato', 'warning')
        return
      }
      this.roomTypeModel.servizi = this.checkedServices
      try {
        this.roomTypeModel = await updateRoomType(this.roomTypeModel)
        this.modalRef?.hide()
        showToast('Tipo camera aggiornato con successo', 'success')
      } catch (e) {
        if (e instanceof AxiosError) {
          showToast(e.response?.data.detail, 'error')
        }
      }
    },
  },
}
</script>

<style scoped lang="scss">
.modal-dialog-scrollable .modal-content {
  max-height: 100% !important;
  overflow: scroll !important;
  overflow-x: hidden !important;
}
</style>
