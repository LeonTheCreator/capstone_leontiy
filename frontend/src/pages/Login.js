import Main from '../components/Main'
import TextField from '../components/TextField'
import Button from '../components/Button'
import Header from '../components/Header'
import Page from '../components/Page'
import { useState } from 'react'
import { useAuth } from '../auth/AuthProvider'
import { Redirect } from 'react-router-dom'
/*
const initialState = {
  username: '',
  password: '',
}

const [credentials, setCredentials] = useState(initialState)
const [error, setError] = useState()
*/

export default function Login() {
  const { login, user } = useAuth()
  const [username, setUsername] = useState('')
  const handleUsernameChange = event => {
    const newUsername = event.target.value
    setUsername(newUsername)
  }

  const [password, setPassword] = useState('')
  const handlePasswordChange = event => {
    const newPassword = event.target.value
    setPassword(newPassword)
  }

  const handleSubmit = event => {
    event.preventDefault()
    const credentials = { username, password }
    login(credentials)
    console.log(username, password)
  }
  if (user) {
    return <Redirect to="/" />
  }
  return (
    <Page>
      <Header title="Login" />
      <Main as="form" onSubmit={handleSubmit}>
        <TextField
          title="Username"
          name="username"
          value={username}
          onChange={handleUsernameChange}
        />
        <TextField
          title="Password"
          name="password"
          type="password"
          value={password}
          onChange={handlePasswordChange}
        />
        <Button>login</Button>
      </Main>
    </Page>
  )
}
