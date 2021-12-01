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

part2 :: [Int] -> Int
part2 xs = countPossibilities ((0 : quicksort xs) ++ [maximum xs + 3]) [0,0,1] 1 (maximum xs)

countPossibilities :: [Int] -> [Int] -> Int -> Int -> Int
countPossibilities xs amnts val max
        | val == max = sum amnts
        | val `notElem` xs = countPossibilities xs (drop 1 amnts ++ [0]) (val + 1) max
        | otherwise = countPossibilities xs (drop 1 amnts ++ [sum amnts]) (val + 1) max

replaceAt :: [t] -> Int -> t -> [t]
replaceAt xs i x = take i xs ++ (x : drop (i + 1) xs)

quicksort :: Ord a => [a] -> [a]
quicksort []     = []
quicksort (p:xs) = (quicksort lesser) ++ [p] ++ (quicksort greater)
    where
        lesser  = filter (< p) xs
        greater = filter (>= p) xs