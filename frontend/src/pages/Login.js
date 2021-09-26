import Main from '../components/Main'
import TextField from '../components/TextField'
import Button from '../components/Button'
import Header from '../components/Header'
import Page from '../components/Page'
import { useState } from 'react'
/*
const initialState = {
  username: '',
  password: '',
}

const { login, user } = useAuth()
const [credentials, setCredentials] = useState(initialState)
const [error, setError] = useState()
*/

export default function Login() {
  const [username, setUsername] = useState('')
  const handleUsernameChange = event => {
    const newUsername = event.target.value
    setUsername(newUsername)
  }
  const handleSubmit = event => {
    event.preventDefault()
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
        <TextField title="Password" name="password" type="password" />
        <Button>login</Button>
      </Main>
    </Page>
  )
}
