import type { RoomType } from '@/interfaces/roomtype.ts'
import axiosInstance from '@/services/axios.ts'
import type { Image } from '@/interfaces/Image.ts'

export const getRoomTypes = async () => {
  try {
    const response = await axiosInstance.get<RoomType[]>(`/roomtypes`)
    return response.data
  } catch (error) {
    console.error('Error fetching room types:', error)
    throw error
  }
}

export const getRoomTypeImages = (roomType: RoomType) => {
  return roomType.galleriaImmagini.map(
    (image) =>
      <Image>{
        src: `${import.meta.env.VITE_BACKEND_IMAGES_URL}/roomtypes/${image.path_immagine}`,
        title: '',
        description: '',
        posizione: image.posizione,
      },
  )
}
