import { AxiosError, type AxiosInstance } from 'axios'

async function postCliente(
  httpClient: AxiosInstance,
  cliente: any
): Promise<{
  data: any
  errors: AxiosError | null
}> {
  let errors = null
  let data = null
  await httpClient
    .post('clientes', cliente)
    .then((response) => {
      data = response.data
    })
    .catch((e) => {
      errors = e
    })
  return { data, errors }
}
async function getValidarTelefone(
  httpClient: AxiosInstance,
  telefone: String
): Promise<{
  data: any
  errors: AxiosError | null
}> {
  let errors = null
  let data = null
  await httpClient
    .get('clientes/' + `${telefone}`)
    .then((response) => {
      data = response.data
    })
    .catch((e) => {
      errors = e
    })
  return { data, errors }
}

export default (httpClient: AxiosInstance) => ({
  salvarCliente: async (cliente: any) => {
    const { data, errors } = await postCliente(httpClient, cliente)

    return { data, errors }
  },
  veridicarTelefone: async (telefone: String) => {
    const { data, errors } = await getValidarTelefone(httpClient, telefone)

    return { data, errors }
  }
})
