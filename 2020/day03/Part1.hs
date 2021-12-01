module Part1 where

import System.IO ()  
import Control.Monad ()

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . part1 . map lineToBoolArray . lines $ contents


lineToBoolArray :: [Char] -> [Bool]
lineToBoolArray xs = cycle (map parseChar (init xs))

parseChar :: Char -> Bool
parseChar c
    | c == '#' = True
    | c == '.' = False
    | otherwise = error ("Invalid character '" ++ [c] ++ "'")

part1 :: [[Bool]] -> Int
part1 xs = fst (foldl step3 (0,0) xs)
        

step3 :: (Int,Int) -> [Bool] -> (Int,Int)
step3 (x, y) xs
    | xs!!(3*y) = (x+1, y+1)
    | otherwise = (x, y+1)
