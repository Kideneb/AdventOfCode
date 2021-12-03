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
                . (map $ map (\x -> if x == '0'  then 0 else 1))
                . splitOn "\n" 
                . filter (/= '\r') $ init contents


solve xs = (part1 xs, part2 xs)
                

part1 xs = (2^(length $head xs)-(res+1)) * res
        where
                res = getMCBNum $ changeDim xs

part2 xs = oxygen * cotwo
        where
                oxygen = binToNum $ filterByRuleAt (\x -> if x >= 0 then 1 else 0) 0 xs
                cotwo = binToNum $ filterByRuleAt (\x -> if x < 0 then 1 else 0) 0 xs


filterByRuleAt rule pos xs
        | length xs == 1 = head xs
        | otherwise = filterByRuleAt rule (pos+1) filteredList 
                where
                        ys = changeDim xs
                        bit = rule $ foldl (\acc x-> if x==1 then acc+1 else acc-1) 0 (ys!!pos)
                        filteredList = filter (\x -> (x!!pos) == bit) xs

getMCBNum list = binToNum binRes
        where 
                binRes = map ((\x -> if x > 0 then 1 else 0) . foldl (\acc x-> if x==1 then acc+1 else acc-1) 0) list

binToNum bin = fst $ foldr (\x (val,pow) -> (val + x*pow, 2*pow)) (0,1) bin

changeDim xs = listByIndex
        where 
                len = length $ xs!!0 
                listByIndex = [[x!!i | x <- xs] | i <- [0..len-1]]
