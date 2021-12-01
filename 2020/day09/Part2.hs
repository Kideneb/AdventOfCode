module Part2 where

import Data.List.Split
import Data.Char

import System.IO ()  
import Control.Monad ()
main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print
                . part2
                . map read
                . splitOn "\n" 
                . filter (/= '\r') $ init contents

part1 :: [Int] -> Int
part1 xs
        | isSumIn (xs!!25) (take 25 xs) = part1 (drop 1 xs)
        | otherwise = xs!!25

isSumIn :: Int -> [Int] -> Bool
isSumIn x xs = any (\y -> elem (x-y) xs) xs

part2 :: [Int] -> Int
part2 xs = findFirstSum xs (part1 xs) 0 1

findFirstSum :: [Int] -> Int -> Int -> Int -> Int
findFirstSum xs val min max
        | sum sublist == val = maximum sublist + minimum sublist
        | sum sublist > val = findFirstSum xs val (min+1) (min+2)
        | otherwise = findFirstSum xs val min (max+1)
        where sublist = take (max + 1 - min) (drop min xs)