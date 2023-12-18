import axios from 'axios'
import clienteService from './clienteService'

const API_URL = 'http://localhost:8080'

const httpClient = axios.create({
  baseURL: API_URL
})

export default {
  cliente: clienteService(httpClient)
}
