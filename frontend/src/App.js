import { Route, BrowserRouter as Router, Switch } from 'react-router-dom'
import Login from './pages/Login'
import AuthProvider from './auth/AuthProvider'
import ProtectedRoute from './auth/ProtectedRoute'
import HomePage from './pages/HomePage'

export default function App() {
  return (
    <AuthProvider>
      <Router>
        <Switch>
          <Route path="/login" component={Login} />
          <ProtectedRoute exact path="/" component={HomePage} />
        </Switch>
      </Router>
    </AuthProvider>
  )
}
