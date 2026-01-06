{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE DeriveGeneric #-}

module Main where

import Web.Scotty
import Data.Aeson (FromJSON, ToJSON, object, (.=))
import Data.List (transpose)
import GHC.Generics
import Data.Time.Clock.POSIX
import qualified Data.Text.Lazy as T

--test
main = do
    print "Hello, world!"


