FROM node:lts-alpine as build

# instala um servidor http simples para servir conteúdo estático
RUN npm install -g http-server

# faz da pasta 'app' o diretório atual de trabalho
WORKDIR /app

# copia os arquivos 'package.json' e 'package-lock.json' (se disponível)
COPY ./TesteErikFrontend/package*.json ./

# instala dependências do projeto
RUN npm install

# copia arquivos e pastas para o diretório atual de trabalho (pasta 'app')
COPY ./TesteErikFrontend .

# compila a aplicação de produção com minificação
RUN npm run build

EXPOSE 5173
CMD [ "http-server", "--cors", "-p5173", "dist" ]