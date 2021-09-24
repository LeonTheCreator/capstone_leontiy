import AuthContext from './AuthContext'
import { useContext, useState } from 'react'
import jwt from 'jsonwebtoken'
import { getToken } from '../service/api-service'

export default function AuthProvider({ children }) {
  const [token, setToken] = useState()
  //claims? Was macht das?
  const claims = jwt.decode(token)

  const user = claims && {
    username: claims.sub,
  }

  const login = credentials => getToken(credentials).then(setToken)

  const logout = () => setToken()

  return (
    <AuthContext.Provider value={{ token, user, login, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext)
