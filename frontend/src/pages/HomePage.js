import Main from '../components/Main'
import Header from '../components/Header'
import Page from '../components/Page'
import TextField from '../components/TextField'
import { useState } from 'react'
import Button from '../components/Button'
import { addWatchListItem } from '../service/api-service'
import { useAuth } from '../auth/AuthProvider'

export default function HomePage() {
  const { token } = useAuth()
  const handleSubmit = event => {
    event.preventDefault()
    const frontendWatchListDto = { watchlistItemName, wkn, isin }
    addWatchListItem(frontendWatchListDto, token)
    //irgendwas muss ich hier noch machen, keinen Plan wie ich grau in gelb verwandle
    console.log(frontendWatchListDto)
  }

  const [watchlistItemName, setWatchlistItemName] = useState('')

  const handleWatchListItemNameChange = event => {
    const newWatchlistItemName = event.target.value
    setWatchlistItemName(newWatchlistItemName)
  }

  const [wkn, setWkn] = useState('')

  const handleWknChange = event => {
    const newWkn = event.target.value
    setWkn(newWkn)
  }
  const [isin, setIsin] = useState('')

  const handleIsinChange = event => {
    const newIsin = event.target.value
    setIsin(newIsin)
  }

  return (
    <div>
      <Page>
        <Header title="Watchlist" />
        <Main as="form" onSubmit={handleSubmit}>
          <TextField
            title="Name des Titels"
            name="watchlistItemName"
            value={watchlistItemName}
            onChange={handleWatchListItemNameChange}
          />
          <TextField
            title="WKN"
            name="wkn"
            value={wkn}
            onChange={handleWknChange}
          />
          <TextField
            title="ISIN"
            name="isin"
            value={isin}
            onChange={handleIsinChange}
          />
          <Button>Hinzufügen</Button>
        </Main>
      </Page>
    </div>
  )
}
