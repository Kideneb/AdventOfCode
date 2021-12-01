module Part1 where

import System.IO  
import Control.Monad

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . part1 . map readInt . words $ contents

readInt :: String -> Int
readInt = read

part1 :: [Int] -> Int
part1 [] = error "Not possible"
part1 (x:xs) 
    | null list = part1 xs
    | otherwise = x * head list
    where list = inner x xs


inner :: (Eq a, Num a) => a -> [a] -> [a]
inner a = filter (\x -> x + a == 2020)
