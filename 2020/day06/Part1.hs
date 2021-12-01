module Part1 where

import Data.List.Split

import System.IO ()  
import Control.Monad ()

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . part1 . map (filter (/= '\n')) . splitOn "\n\n" . filter (/= '\r') $ init contents


part1 :: [String] -> Int
part1 = sum . map (length . removeDuplicates . quicksort)

removeDuplicates :: (Eq a) => [a] -> [a]
removeDuplicates [] = []
removeDuplicates [x] = [x]
removeDuplicates (x:y:xs)
        | x == y = removeDuplicates (x:xs)
        | x /= y = x : removeDuplicates (y:xs)

quicksort :: Ord a => [a] -> [a]
quicksort []     = []
quicksort (p:xs) = (quicksort lesser) ++ [p] ++ (quicksort greater)
    where
        lesser  = filter (< p) xs
        greater = filter (>= p) xs