module Day1 where

import Data.List.Split
import Data.Char

import System.IO ()  
import Control.Monad ()
main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print
                . solve
                . map read
                . splitOn "\n" 
                . filter (/= '\r') $ init contents

solve :: [Int] -> (Int,Int)
solve xs = (part1 xs, part2 xs)

part1 xs = foldl (\acc (x,y) -> if x > y then acc + 1 else acc) 0 $ zip (tail xs) xs

part2 xs = part1 (zipWith (+) xs (zipWith (+) (tail xs) (drop 2 xs)))