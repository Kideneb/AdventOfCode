module Part1 where

import Data.List.Split

import System.IO ()  
import Control.Monad ()

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . foldl part1 0 . map (map (take 3) . splitOneOf " \n") . splitOn "\n\n" . filter (/= '\r') $ init contents


part1 :: Int -> [String] -> Int
part1 acc xs
    | elem "cid" xs && length xs == 8 = acc + 1
    | notElem "cid" xs && length xs == 7 = acc + 1
    | otherwise = acc