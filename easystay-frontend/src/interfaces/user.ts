export interface Authority {
  authority: string
}

export interface User {
  id: number
  nome: string
  cognome: string
  email: string
  password: string
  ruolo: string
  dataCreazione: string
  enabled: boolean
  username: string
  authorities: Authority[]
  accountNonLocked: boolean
  accountNonExpired: boolean
  credentialsNonExpired: boolean
}

export interface AuthResponse {
  token: string
  user: User
}
