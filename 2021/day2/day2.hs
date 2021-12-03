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
                . map toDirection
                . splitOn "\n" 
                . filter (/= '\r') $ init contents

data Direction = Depth Integer | Length Integer


toDirection :: String -> Direction
toDirection str
        | x == "forward" = Length yNum
        | x == "down" = Depth yNum
        | otherwise = Depth (0-yNum)
        where 
                (x:y:xs) = splitOn " " str
                yNum = read y


solve xs = (part1 xs, part2 xs)

part1 xs = l*d
        where (l,d) = foldl sumDirections (0,0) xs

sumDirections (a,b) (Depth x) = (a,b+x)
sumDirections (a,b) (Length x) = (a + x, b)

part2 xs = l*d
        where (l,d,_) = foldl updateState (0,0,0) xs

updateState (a,b,c) (Depth x) = (a,b,c+x)
updateState (a,b,c) (Length x) = (a+x,b+c*x,c)