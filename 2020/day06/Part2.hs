module Part2 where

import Data.List.Split

import System.IO ()  
import Control.Monad ()

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . sum . part2 . map (splitOn "\n") . splitOn "\n\n" . filter (/= '\r') $ init contents


part2 :: [[String]] -> [Int]
part2 = map ((\x -> countContainedAll (head x) (drop 1 x)) . map quicksort)

countContainedAll :: String -> [String] -> Int
countContainedAll s [] = length s
countContainedAll s (x:xs) = countContainedAll sNew xs
        where 
        sNew = filter (`elem` x) s

quicksort :: Ord a => [a] -> [a]
quicksort []     = []
quicksort (p:xs) = (quicksort lesser) ++ [p] ++ (quicksort greater)
    where
        lesser  = filter (< p) xs
        greater = filter (>= p) xs