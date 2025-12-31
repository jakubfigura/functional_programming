{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}

module Main where

import Web.Scotty
import Data.Aeson (FromJSON, ToJSON, object, (.=))
import Data.List (transpose)
import Control.Monad.State
import Control.Monad (replicateM)
import GHC.Generics
import Data.Time.Clock.POSIX
import qualified Data.Text.Lazy as T
import Data.IORef

--test
data TestRequest = TestRequest{
    message :: String
}deriving(Show, Generic)

instance FromJSON TestRequest
instance ToJSON TestRequest

--funkcja dla generowania ziarna z czasu
getSystemSeed :: IO Int
getSystemSeed = do
  now <- getPOSIXTime
  return $ round (now * 1000000)

--zadanie 1
type Rand a = State Int a

nextRand :: Rand Int
nextRand = do
    seed <- Control.Monad.State.get
    let a = 1103515245
        c = 12345
        -- m = maxBound --maksymalna wartość INT
        seed' = (a * seed + c) --- gdy damy modulo, to będzie bez ostatniej`mod` m
    Control.Monad.State.put seed'
    return (abs seed') -- w taki sposób uwzględniamy maxBound, więc jest max value INT włącznie!

randList :: Int -> Rand [Int]
randList n = replicateM n nextRand

runRandList :: Int -> Int -> [Int]
runRandList seed n =
    evalState (randList n) seed


main = do
  initialSeed <- getSystemSeed
  state <- newIORef initialSeed

  scotty 3000 $ do
      --endpoint testowy
    post "/test" $ do
      message <- jsonData :: ActionM TestRequest
      json $ object
          [
            "Response" .= message
          ]

    --zadanie 1 endpoint
    post "/randINT" $ do
      seed <- liftIO $ readIORef state
      let (randomNumber, seed') = Control.Monad.State.runState nextRand seed
      liftIO $ writeIORef state seed'
      json $ object
        [
          "Random number" .= randomNumber
        ]
    
    post "/randDouble" $ do
       json $ object
        [
        ]
