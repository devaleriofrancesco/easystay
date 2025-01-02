import type { RoomType } from '@/interfaces/roomtype.ts'

export interface Room {
  numeroStanza: number
  tipoStanza: RoomType
  prenotabile: boolean
}
