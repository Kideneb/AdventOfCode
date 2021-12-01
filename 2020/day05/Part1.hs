module Part1 where

import Data.List.Split

import System.IO ()  
import Control.Monad ()

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . part1 . lines . filter (/= '\r') $ init contents


part1 :: [String] -> Integer
part1 xs =  maxInt (map f xs)
        where f = \x -> toBin (take 7 x) 'F' 'B' * 8 + toBin (drop 7 x) 'L' 'R'

toBin :: String -> Char -> Char -> Integer
toBin str l h = toBinAcc str l h 0

toBinAcc :: String -> Char -> Char -> Integer -> Integer
toBinAcc [] l h acc = acc
toBinAcc (s:ss) l h acc
        | s == l = toBinAcc ss l h (2 * acc)
        | s == h = toBinAcc ss l h (2 * acc + 1)
        | otherwise = error ("Found " ++ [s] ++ " with " ++ [l] ++ " " ++ [h])

maxInt :: [Integer] -> Integer
maxInt = foldr max 0