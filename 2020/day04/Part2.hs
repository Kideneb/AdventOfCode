module Part2 where

import Data.List.Split
import Data.List
import Numeric

import System.IO ()  
import Control.Monad ()
import Text.Regex.Posix

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print . foldl part2 0 . map (map toTuple . splitOneOf " \n") . splitOn "\n\n" . filter (/= '\r') $ init contents

toTuple :: String -> (String, String)
toTuple x = (head y, y!!1)
    where y = splitOn ":" x

part2 :: Int -> [(String, String)] -> Int
part2 acc xs
    | foldl checkCondition 0 xs == 7 = acc + 1
    | otherwise = acc

checkCondition :: Int -> (String, String) -> Int
checkCondition acc (x, y)
    | x == "byr" && length y == 4 && intVal >= 1920 && intVal <= 2002 = acc + 1
    | x == "iyr" && length y == 4 && intVal >= 2010 && intVal <= 2020 = acc + 1
    | x == "eyr" && length y == 4 && intVal >= 2020 && intVal <= 2030 = acc + 1
    | x == "hgt" && isValidHeight y = acc + 1
    | x == "hcl" && length y == 7 && (y =~ colorRegex :: Bool) = acc + 1
    | x == "ecl" && elem y eyeColors = acc + 1
    | x == "pid" && length y == 9 && (y =~ pidRegex :: Bool) = acc + 1
    | otherwise = acc
    where 
        intVal = read y
        colorRegex = "#[0-9a-f]{6}"
        eyeColors = ["amb", "blu", "brn", "gry", "grn", "hzl", "oth"]
        pidRegex = "[0-9]{9}"

isValidHeight :: String -> Bool
isValidHeight y
    | isSuffixOf "in" y && intVal >= 59 && intVal <= 76 = True
    | isSuffixOf "cm" y && intVal >= 150 && intVal <= 193 = True
    | otherwise = False
    where intVal = read (take ((length y) - 2) y) :: Int