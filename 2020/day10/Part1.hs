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
part1 xs = foldRes!!1 * foldRes!!3
        where foldRes = countDifferences ((0 : quicksort xs) ++ [maximum xs + 3]) [0,0,0,0]

countDifferences :: [Int] -> [Int] -> [Int]
countDifferences [] acc = acc
countDifferences [x] acc = acc
countDifferences (x:xs) acc = countDifferences xs (replaceAt acc diff (acc!!diff + 1))
        where diff = head xs - x

replaceAt :: [t] -> Int -> t -> [t]
replaceAt xs i x = take i xs ++ (x : drop (i + 1) xs)



quicksort :: Ord a => [a] -> [a]
quicksort []     = []
quicksort (p:xs) = (quicksort lesser) ++ [p] ++ (quicksort greater)
    where
        lesser  = filter (< p) xs
        greater = filter (>= p) xs