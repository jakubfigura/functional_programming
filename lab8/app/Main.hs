{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}

module Main where

import Prelude hiding (fmap)
import Web.Scotty
import Data.Aeson (FromJSON, ToJSON, object, (.=))
import GHC.Generics

--test
newtype TestRequest = TestRequest{
    message :: String
}deriving(Show, Generic)

instance FromJSON TestRequest
instance ToJSON TestRequest

class Funktor f where
    fmap :: (a -> b) -> f a -> f b

instance Funktor Maybe where
    fmap _ Nothing = Nothing
    fmap f (Just a) = Just (f a)

dodawanie :: Int -> Int -> Int
dodawanie x y = x + y

--3.0 wykorzysta metodę map fuktora do wykonania funkcji dodawania lub odejmowania na dwóch wartość przyjętych na wejściu
obliczDodawanie :: Maybe Int -> Maybe Int -> Maybe Int
obliczDodawanie mx my = fmap (\x -> case my of
                                 Nothing -> x
                                 Just y -> x + y) mx

data SumRequest = SumRequest {
    a :: Maybe Int,
    b :: Maybe Int
} deriving (Show, Generic)

instance FromJSON SumRequest
instance ToJSON SumRequest

data SumResponse = SumResponse {
    result :: Maybe Int
} deriving (Show, Generic)

instance FromJSON SumResponse
instance ToJSON SumResponse



--test
main :: IO ()
main = scotty 8080 $ do

    -- endpoint testowy
    post "/test" $ do 
        message <- jsonData :: ActionM TestRequest
        json $ object
            [
                "Response" .= message
            ]
    
    --Zadanie 1
    post "/fmapSUM" $ do
        reqData <- jsonData :: ActionM SumRequest
        let res = obliczDodawanie (a reqData) (b reqData)
        json $ SumResponse { result = res }



