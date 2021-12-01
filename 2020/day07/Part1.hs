module Part1 where

import Data.List.Split
import Data.Char

import System.IO ()  
import Control.Monad ()

data Bag = Bag String [(Int, String)]

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . part1 . map toBag . splitOn "\n" . filter (/= '\r') $ init contents

part1 :: [Bag] -> Int
part1 xs = length (filter (containsGoldBag xs) xs) - 1

containsGoldBag :: [Bag] -> Bag -> Bool
containsGoldBag xs (Bag s ys)
        | null ys = False
        | s == "shiny gold" = True
        | otherwise = or list
        where
                list = map f ys
                f = containsGoldBag xs . getBag xs . snd

getBag :: [Bag] -> String -> Bag
getBag ((Bag s ys):xs) name 
        | name == s = Bag s ys
        | otherwise = getBag xs name 

toBag :: String -> Bag
toBag str = Bag (head splitNameVal) amounts
        where 
                splitNameVal = splitOn " bags contain " (init str)
                splitStr = splitOn ", " (splitNameVal!!1)
                amounts = foldl f ([] :: [(Int, String)]) splitStr
                f xs y
                        | head y == 'n' = []
                        | otherwise = (val, f1 (drop 2 y)) : xs
                                where
                                        f1 = \z -> take ((length z) - (4 + plural)) z
                                        val = digitToInt (head y)
                                        plural
                                                | val == 1 = 0
                                                | val > 1 = 1

showBag :: Bag -> String
showBag  (Bag x y) = "(" ++ show x ++ ", " ++ show y ++ ")"