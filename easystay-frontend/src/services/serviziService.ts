import axiosInstance from '@/services/axios.ts'
import type { Servizio, ServizioWrapper } from '@/interfaces/roomtype.ts'

export const getAvailableServicesByRoomTypeId = async (roomTypeId: number) => {
  try {
    const response = await axiosInstance.get<ServizioWrapper[]>(
      `/admin/services/roomtype/${roomTypeId}`,
    )
    return response.data
  } catch (error) {
    console.error('Error fetching services:', error)
    throw error
  }
}

// get all services
export const getAllServices = async () => {
  try {
    const response = await axiosInstance.get<Servizio[]>('/admin/services')
    return response.data
  } catch (error) {
    console.error('Error fetching services:', error)
    throw error
  }
}

// update service
export const updateService = async (service: Servizio) => {
  try {
    const response = await axiosInstance.put<Servizio>(`/admin/services/${service.id}`, service)
    return response.data
  } catch (error) {
    console.error('Error updating service:', error)
    throw error
  }
}

// create service
export const createService = async (service: Servizio) => {
  try {
    const response = await axiosInstance.post<Servizio>('/admin/services', service)
    return response.data
  } catch (error) {
    console.error('Error creating service:', error)
    throw error
  }
}

// delete service
export const deleteService = async (serviceId: number) => {
  try {
    const response = await axiosInstance.delete<Servizio>(`/admin/services/${serviceId}`)
    return response.data
  } catch (error) {
    console.error('Error deleting service:', error)
    throw error
  }
}
