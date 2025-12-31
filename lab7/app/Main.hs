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

--zadanie 2
nextRandDouble :: Rand Double
nextRandDouble = do
    seed <- Control.Monad.State.get
    let a = 1103515245
        c = 12345
        m = 2^31
        seed' = (a * seed + c) `mod` m
    Control.Monad.State.put seed'
    return (fromIntegral seed' / fromIntegral m)

--zadanie 3
randIntDouble :: Rand (Int, Double)
randIntDouble = do
  integer_rand <- nextRand
  double_rand <- nextRandDouble
  return (integer_rand, double_rand)

randDoubleInt :: Rand (Double, Int)
randDoubleInt = do
  double_rand <- nextRandDouble
  integer_rand <- nextRand
  return (double_rand, integer_rand)

rand3Double :: Rand (Double, Double, Double)
rand3Double = do
  double1 <- nextRandDouble
  double2 <- nextRandDouble
  double3 <- nextRandDouble
  return(double1, double2, double3)


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
    
    -- zadanie 2 endpoint
    -- 3.5 zwróci wartość między 0, a 1 (Double) z wyłączeniem 1
    post "/randDouble" $ do
      seed <- liftIO $ readIORef state
      let (randomDouble, seed') = Control.Monad.State.runState nextRandDouble seed
      liftIO $ writeIORef state seed'
      json $ object
        [
          "Random double from [0, 1)" .= randomDouble
        ]

    --zadanie 3 endpoint 
    -- 4.0 zwróci pary (Int, Double), (Double, Int) oraz krotki (Double,Double, Double) wykorzystując funkcje z ocen 3.0 oraz 3.5
    post "/krotki" $ do
      seed <- liftIO $ readIORef state
      let (randomIntDouble, seed1) = Control.Monad.State.runState randIntDouble seed
      let (randomDoubleInt, seed2) = Control.Monad.State.runState randDoubleInt seed1
      let (random3Double, seed3) = Control.Monad.State.runState rand3Double seed2
      liftIO $ writeIORef state seed3
      json $ object
        [
          "Random (Int, Double) -> " .= randomIntDouble
        , "Random (Double, Int) -> ".= randomDoubleInt
        , "Random (Double, Double, Double) -> " .= random3Double
        ]

