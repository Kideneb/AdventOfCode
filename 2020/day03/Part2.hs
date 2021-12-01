{-# LANGUAGE OverloadedStrings #-}
module Day3_1 where

import System.IO ()  
import Control.Monad ()

data Tuple4 = Tuple4 Int Int Int Int

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . part2 . map lineToBoolArray . lines $ contents


lineToBoolArray :: [Char] -> [Bool]
lineToBoolArray xs = cycle (map parseChar (init xs))

parseChar :: Char -> Bool
parseChar c
    | c == '#' = True
    | c == '.' = False
    | otherwise = error ("Invalid character '" ++ [c] ++ "'")

part2 :: [[Bool]] -> Int
part2 xs = fst (foldl step1 (0,0) xs) * fst (foldl step3 (0,0) xs) * fst (foldl step5 (0,0) xs) * fst (foldl step7 (0,0) xs) * fst (foldl step1 (0,0) (half xs))

step1 :: (Int,Int) -> [Bool] -> (Int,Int)
step1 (x,y) xs
    | xs!!y = (x+1, y+1)
    | otherwise = (x, y+1)

step3 :: (Int,Int) -> [Bool] -> (Int,Int)
step3 (x,y) xs
    | xs!!(3*y) = (x+1, y+1)
    | otherwise = (x, y+1)

step5 :: (Int,Int) -> [Bool] -> (Int,Int)
step5 (x,y) xs
    | xs!!(5*y) = (x+1, y+1)
    | otherwise = (x, y+1)

step7 :: (Int,Int) -> [Bool] -> (Int,Int)
step7 (x,y) xs
    | xs!!(7*y) = (x+1, y+1)
    | otherwise = (x, y+1)

half :: [a] -> [a]
half (x:xs) = x: everySnd xs

everySnd :: [a] -> [a]
everySnd xs = case drop 1 xs of
              (y:ys) -> y : everySnd ys
              [] -> []