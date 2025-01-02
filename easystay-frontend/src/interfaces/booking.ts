import type { User } from '@/interfaces/user.ts'
import type { Room } from '@/interfaces/room.ts'

export interface Booking {
  id: number
  dataCheckIn: string
  dataCheckOut: string
  statoPrenotazione: string
  prezzoTotale: number
  dataCreazione: string
  dataAggiornamento: string
  stanza: Room
  cliente: User
}
