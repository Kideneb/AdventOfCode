module Part2 where

import System.IO ()  
import Control.Monad ()

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . part2 . map readInt . words $ contents

readInt :: String -> Int
readInt = read

part2 :: [Int] -> Int
part2 [] = error "Not possible"
part2 (x:xs) 
    | null list = part2 xs
    | otherwise = x * fst (head list) * snd (head list)
    where list = middle x xs

middle :: (Eq a, Num a) => a -> [a] -> [(a,a)]
middle a [] = []
middle a (x:xs)
    | null list = middle a xs
    | otherwise = [(x, head list)]
    where list = inner (x+a) xs

inner :: (Eq a, Num a) => a -> [a] -> [a]
inner a = filter (\x -> x + a == 2020)
