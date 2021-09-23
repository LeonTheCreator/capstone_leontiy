import { Route, BrowserRouter as Router, Switch } from 'react-router-dom'
import Registration from './pages/Registration'
import AuthProvider from './auth/AuthProvider'
import ProtectedRoute from './auth/ProtectedRoute'

export default function App() {
  return (
    <AuthProvider>
      <Router>
        <Switch>
          <Route path="/registration" component={Registration} />
          <ProtectedRoute exact path="/" component={HomePage} />
          <ProtectedRoute path="/change-password" component={ChangePassword} />
        </Switch>
      </Router>
    </AuthProvider>
  )
}
