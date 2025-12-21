FROM haskell:9.6-slim

RUN apt-get update && apt-get install -y \
    zlib1g-dev \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

RUN cabal update

RUN cabal install --lib scotty aeson

COPY . .

RUN cabal build API

EXPOSE 3000

CMD ["cabal", "run", "API"]