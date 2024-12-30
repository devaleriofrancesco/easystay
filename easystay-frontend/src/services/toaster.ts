// display messages to the user with a toast
import { useToast } from 'vue-toast-notification'
const toast = useToast({
  position: 'top-right',
  duration: 3000,
  dismissible: true,
  queue: false,
  pauseOnHover: true,
})

export default function showToast(message: string, type: 'success' | 'error' | 'warning' | 'info' = 'info') {
  toast.open({
    message,
    type,
  })
}
