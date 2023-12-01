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
                . splitOn "\n"
                . filter (/= '\r') $ init contents

solve :: [String] -> (Int,Int)
solve xs = (part1 xs, part2 xs)

part1 xs = sum (map getTwoDigitNumber xs)

getTwoDigitNumber :: String -> Int
getTwoDigitNumber str = last digits * 10 + head digits
        where digits = getDigits str

getDigits :: String -> [Int]
getDigits = foldl appendIfDigit []

appendIfDigit :: [Int] -> Char -> [Int]
appendIfDigit list c = if isDigit c then read [c]:list else list

part2 xs = 0