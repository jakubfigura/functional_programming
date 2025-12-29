{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}

module Main where

import Web.Scotty
import Data.Aeson (FromJSON, ToJSON, object, (.=))
import Data.List (transpose)


import GHC.Generics
import qualified Data.Text.Lazy as T

data TestRequest = TestRequest{
    message :: String
}deriving(Show, Generic)

instance FromJSON TestRequest
instance ToJSON TestRequest

main = scotty 3000 $
  post "/test" $ do
    message <- jsonData :: ActionM TestRequest
    json $ object
        [
        "Response" .= message
        ]