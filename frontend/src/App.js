import { Route, BrowserRouter as Router, Switch } from 'react-router-dom'
import HomePage from '.pages/HomePage'
import Registration from '.pages/Registration'
import Watchlist from '.pages/Watchlist'
export default function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <HomePage />
        </Route>
        <Route path="/registration">
          <Registration />
        </Route>
        <Route path="/watchlist">
          <Watchlist />
        </Route>
        <Route path="/news"></Route>
      </Switch>
    </Router>
  )
}
