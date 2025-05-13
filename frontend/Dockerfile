# Usa a imagem oficial do Node.js
FROM node:20-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos de dependência
COPY package*.json ./

# Instala as dependências
RUN npm install

# Copia o restante da aplicação
COPY . .

# Gera o build da aplicação Next.js
RUN npm run build

# Expõe a porta que o Next.js usará
EXPOSE 3000

# Inicializa o servidor (modo produção)
CMD ["npm", "run", "start"]
