<script setup lang="ts">
import { reactive } from 'vue'
import services from '@/services'

const state = reactive({
  cliente: { nome: '', endereco: ``, bairro: ``, telefones: [''] },
  numeroDisponivel: [true],
  nomeInvalido: false,
  telefoneRedundante: false
})
function adicionar() {
  state.cliente.telefones.push('')
  state.numeroDisponivel.push(true)
}
function renmover(telefone: any) {
  if (state.cliente.telefones.length > 1) {
    state.cliente.telefones.splice(state.cliente.telefones.indexOf(telefone), 1)
    state.numeroDisponivel.splice(state.numeroDisponivel.indexOf(telefone), 1)
  }
  state.cliente.telefones.forEach((element, idx) => {
    validarTelefone(element, idx)
  })
}
async function validarTelefone(telefone: String, index: number) {
  state.telefoneRedundante = false
  if (telefone && telefone.length === 13) {
    const { data, errors } = await services.cliente.veridicarTelefone(telefone)
    if (!errors) {
      state.numeroDisponivel[index] = data
    } else console.log('errors =', errors.response?.data)
  }
}
function temDuplicidade(array: String[]) {
  return new Set(array).size !== array.length
}
function validarFormulario() {
  let formValido = true
  if (state.cliente.nome.length < 10) {
    state.nomeInvalido = true
    return false
  }
  if (temDuplicidade(state.cliente.telefones)) {
    state.telefoneRedundante = true
    return false
  }

  state.numeroDisponivel.forEach((numero) => {
    if (!numero) {
      formValido = false
    }
  })
  return formValido
}

async function enviar() {
  if (validarFormulario()) {
    const { data, errors } = await services.cliente.salvarCliente(state.cliente)
    if (!errors) {
      state.cliente = { nome: '', endereco: ``, bairro: ``, telefones: [''] }
      state.numeroDisponivel = [true]
      state.telefoneRedundante = false
      alert('Cliente salvo com sucesso.')
    } else {
      alert('Problema ao salvar')
      console.log('errors =', errors.response?.data)
    }
  }
}
</script>

<template>
  <main>
    <div class="body w-100 p-5 center">
      <div class="row align-items-center teste">
        <div class="col align-self-center text-center">
          <div class="card p-3">
            <form action="">
              <div class="textInput">
                <label for="nome">Nome</label>
                <input
                  placeholder="nome"
                  name="nome"
                  id="nome"
                  type="string"
                  v-model="state.cliente.nome"
                />
                <p v-if="state.nomeInvalido" class="help-message">
                  Nome deve ter pelo menos 10 caracteres
                </p>
              </div>
              <div class="textInput">
                <label for="endereco">Endereço</label>
                <input
                  placeholder="endereço"
                  name="endereco"
                  id="endereco"
                  type="string"
                  v-model="state.cliente.endereco"
                />
              </div>
              <div class="textInput">
                <label for="bairro">Bairro</label>
                <input
                  placeholder="bairro"
                  name="bairro"
                  id="bairro"
                  type="string"
                  v-model="state.cliente.bairro"
                />
              </div>
              <div>
                <div class="textInput">
                  <label for="telefone">Telefones</label>
                  <div class="mt-3" v-for="(item, idx) in state.cliente.telefones" :key="idx">
                    <div class="input-group">
                      <input
                        v-maska
                        data-maska="(##)####-####"
                        class="form-control"
                        name="telefone"
                        id="telefone"
                        type="string"
                        placeholder="(00)0000-0000"
                        v-model="state.cliente.telefones[idx]"
                        @blur="validarTelefone(state.cliente.telefones[idx], idx)"
                      />
                      <button
                        @click="renmover(item)"
                        class="btn btn-danger"
                        type="button"
                        id="button-remove"
                      >
                        Remover
                      </button>
                    </div>
                    <p v-if="!state.numeroDisponivel[idx]" class="help-message">
                      Telefone já cadastrado
                    </p>
                  </div>
                  <p v-if="state.telefoneRedundante" class="help-message">
                    Telefones não podem ser iguais
                  </p>
                  <button
                    @click="adicionar()"
                    class="btn btn-success mt-2"
                    type="button"
                    id="button-add"
                  >
                    Adicionar Telefone
                  </button>
                </div>
              </div>
              <button
                @click="enviar()"
                class="btn btn-primary submit"
                type="button"
                id="button-add"
              >
                Enviar
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>
<style scoped>
input::placeholder {
  font-weight: bold;
  opacity: 0.4;
}
.help-message {
  text-align: left;
  left: 0;
  margin: 0;
  color: hsl(0, 100%, 74%);
}
.submit {
  width: 100%;
  padding: 10px 15px;
}
label {
  font-weight: 700;
  display: block;
  margin-bottom: 4px;
  width: 100%;
}

input {
  font-weight: 700;
  border-radius: 5px;
  border: 2px solid gainsboro;
  padding: 15px 10px;
  outline: none;
  background-color: #e0eee4;
  width: 100%;
  transition:
    border-color 0.3s ease-in-out,
    color 0.3s ease-in-out,
    background-color 0.3s ease-in-out;
}
.textInput {
  position: relative;
  margin-bottom: calc(1em * 1.5);
  width: 100%;
}
.card {
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.35) 5px 7px 3px;
  background-color: var(--background-dark-3);
  color: var(--text-dark-1);
}
.teste {
  width: 150%;
  height: 80%;
}
.body {
  background-image: url('@/assets/images/bg-intro-desktop.png');
  /* Center and scale the image nicely */
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  background-color: var(--Primary-Red);
  height: 100vh;
  width: 100vw;
}
.center {
  display: grid;
  place-items: center;
}
</style>
