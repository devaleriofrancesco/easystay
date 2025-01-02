export interface GalleriaImmagine {
  id: number
  posizione: number
  path_immagine: string
}

export interface Servizio {
  id: number
  nome: string
  qty: number
  prezzoAddizionale: number
}

export interface ServizioWrapper {
  id: number
  servizio: Servizio
  qty: number
}

export interface RoomType {
  id: number
  nome: string
  descrizione: string
  prezzo: number
  metriQuadri: number
  numeroAdulti: number
  numeroBambini: number
  galleriaImmagini: GalleriaImmagine[]
  numeroPostiLetto: number
  servizi: ServizioWrapper[]
}
