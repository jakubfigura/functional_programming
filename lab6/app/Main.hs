{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}

module Main where

-- import frameworku do web api
import Web.Scotty
import Data.Aeson (FromJSON, ToJSON, object, (.=))

-- generowanie typów na podstawie instancji
import GHC.Generics
import qualified Data.Text.Lazy as T

-- JSON będzie mieć strukturę lista : [], operand

data IsSortedRequest = IsSortedRequest
    {
        list :: [Int],
        operand :: String
    } deriving(Show, Generic)

instance FromJSON IsSortedRequest
instance ToJSON IsSortedRequest

-- funkcje sortujące dla dwóch przypadków
sortedAsc :: (Ord a) => [a] -> Bool
sortedAsc [] = True
sortedAsc [_] = True
sortedAsc(x:y:xs) = x <= y && sortedAsc (y:xs)

sortedDesc :: (Ord a) => [a] -> Bool
sortedDesc [] = True
sortedDesc [_] = True
sortedDesc(x:y:xs) = x >= y && sortedDesc (y:xs)

isSorted :: String -> [Int] -> Bool
isSorted "<" xs = sortedAsc xs
isSorted ">" xs = sortedDesc xs
isSorted _ _ = False


main :: IO ()
main = scotty 3000 $ do
   
    --Zadanie 1: Sprawdzanie sortowania z kluczem
    post "/isSorted" $ do
        request <- jsonData :: ActionM IsSortedRequest
        let result = isSorted (operand request) (list request)
        json $ object 
            [ "list" .= list request,
            "operand" .= operand request,
            "sorted" .= result
            ]

    

