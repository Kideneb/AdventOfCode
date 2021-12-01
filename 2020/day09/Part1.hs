module Part1 where

import Data.List.Split
import Data.Char

import System.IO ()  
import Control.Monad ()
main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print
                . part1
                . map read
                . splitOn "\n" 
                . filter (/= '\r') $ init contents

part1 :: [Int] -> Int
part1 xs
        | isSumIn (xs!!25) (take 25 xs) = part1 (drop 1 xs)
        | otherwise = xs!!25

isSumIn :: Int -> [Int] -> Bool
isSumIn x xs = any (\y -> elem (x-y) xs) xs