import { Redirect, Route } from 'react-router-dom'
import { useAuth } from './AuthProvider'

export default function ProtectedRoute({ adminOnly, ...props }) {
  const { user } = useAuth()
  if (!user) {
    return <Redirect to="/login" />
  }
  // hier müsste ich doch den User oder den Admin auch auf verschiedene Routen schicken können?
  if (user.role !== 'standart') {
    return <Redirect to="/" />
  }

  return <Route {...props} />
}
