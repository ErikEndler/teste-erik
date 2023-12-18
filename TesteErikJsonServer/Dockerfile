FROM debian:10-slim

ENV tmp_dir /tmp

RUN apt-get update \
  && apt-get install -y curl \
  && curl -sL https://deb.nodesource.com/setup_12.x | bash - \
  && apt-get install -y nodejs

RUN npm install -g json-server

RUN echo '{"Clientes":[],"Telefones": []}' > /tmp/test.json

ENTRYPOINT ["json-server", "--port", "5000", "--host", "0.0.0.0", "--no-cors"]


CMD ["/tmp/test.json"]

# json-server --watch db.json --host 127.0.0.1  --port 5000 --no-cors