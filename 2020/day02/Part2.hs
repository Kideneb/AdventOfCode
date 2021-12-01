{-# LANGUAGE OverloadedStrings #-}
module Part2 where

import System.IO  
import Control.Monad

import qualified Data.Text as T

data Rule = Rule Int Int Char String


instance Show Rule where
    show (Rule a b c d) = show a ++ show b ++ show c ++ d

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . part1 . map readLine . lines $ contents

readLine :: String -> Rule
readLine s = Rule (read (T.unpack (head numbers))) (read (T.unpack (numbers!!1))) ((T.unpack (phrases!!1))!!0) ((T.unpack (phrases!!2)))
    where numbers = T.splitOn "-" (head phrases)
          phrases = T.splitOn " " (T.pack s)

part1 :: [Rule] -> Int
part1= foldl f 0

f :: Int -> Rule -> Int
f y (Rule m1 m2 c str)
    | ((str!!(m1-1) == c) && (str!!(m2-1) /= c)) || ((str!!(m1-1) /= c) && (str!!(m2-1) == c)) = y + 1
    | otherwise = y
    where cnt = length $ filter (== c) str
