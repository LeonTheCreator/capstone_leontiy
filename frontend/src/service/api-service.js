import axios from 'axios'

export const getToken = credentials =>
  axios
    .post('auth/access_token', credentials)
    .then(response => response.data)
    .then(dto => dto.token)

const headers = token => ({
  headers: {
    Authorization: `Bearer ${token}`,
  },
})

export const addWatchListItem = (frontendWatchListDto, token) =>
  axios
    .post('/watchlist', frontendWatchListDto, headers(token))
    .then(response => response.data)

export const deleteWatchListItem = (id, token) =>
  axios.delete(`/watchlist/${id}`, headers(token))

export const updateWatchListItem = (frontendWatchListDto, token) =>
  axios.put('watchlist/${id}', frontendWatchListDto, headers(token))
