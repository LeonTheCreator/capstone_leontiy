import Main from '../components/Main'
import TextField from '../components/TextField'
import Button from '../components/Button'
import Header from '../components/Header'
import Page from '../components/Page'
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
  return (
    <Page>
      <Header title="Login" />
      <Main as="form">
        <TextField title="Username" name="username" />
        <TextField title="Password" name="password" type="password" />
        <Button>login</Button>
      </Main>
    </Page>
  )
}
