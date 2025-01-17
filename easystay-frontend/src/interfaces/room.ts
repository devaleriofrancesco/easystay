import type { RoomType } from '@/interfaces/roomtype.ts'

export interface PagedRoom {
  rooms: Room[]
  totalPages: number
  pageNumber: number
}

export interface Room {
  numeroStanza: number
  tipoStanza: RoomType
  prenotabile: boolean
}
