module Part1 where

import Data.List.Split
import Data.Char

import System.IO ()  
import Control.Monad ()

data Instruction = Jump {val :: Int}
        | Acc {val :: Int}
        | Nop {val :: Int} deriving (Eq)

instance Show Instruction where
        show (Jump val) = "jmp " ++ show val
        show (Acc val) = "acc " ++ show val
        show (Nop val) = "nop" ++ show val
        

main :: IO ()
main = do  
        contents <- readFile "in.txt"
        print   
                . part1
                . map parseInstruction
                . splitOn "\n" 
                . filter (/= '\r') $ init contents

part1 :: [Instruction] -> Int
part1 xs = tryExchange [] xs (length xs)

tryExchange :: [Instruction] -> [Instruction] -> Int -> Int
tryExchange _ [] _ = -1
tryExchange prevInstr nextInstr len
        | isJump headInstr =
                case () of
                     () | dupReturn /= -1 -> dupReturn
                        | otherwise -> tryExchange (prevInstr ++ [headInstr]) (drop 1 nextInstr) len
                        where dupReturn = findDuplicate (prevInstr ++ ([Nop (val (head nextInstr))] ++ drop 1 nextInstr)) len [] 0 0
        | isNop headInstr =
                case () of
                     () | dupReturn /= -1 -> dupReturn
                        | otherwise -> tryExchange (prevInstr ++ [headInstr]) (drop 1 nextInstr) len
                        where dupReturn = findDuplicate (prevInstr ++ [Jump (val (head nextInstr))] ++ drop 1 nextInstr) len [] 0 0
        | otherwise = tryExchange (prevInstr ++ [headInstr]) (drop 1 nextInstr) len
        where
                headInstr = head nextInstr
                retVal = findDuplicate 

findDuplicate :: [Instruction] -> Int -> [Int] -> Int -> Int -> Int
findDuplicate instr len visited acc pos
        | elem pos visited = -1
        | pos == len = acc
        | isJump instruction = findDuplicate instr len newVisited acc (pos + val instruction)
        | isAcc instruction = findDuplicate instr len newVisited (acc + val instruction) (pos + 1)
        | isNop instruction = findDuplicate instr len newVisited acc (pos + 1)
        where
                instruction = instr!!pos
                newVisited = pos : visited

isJump :: Instruction -> Bool
isJump (Jump _) = True
isJump _ = False

isAcc :: Instruction -> Bool
isAcc (Acc _) = True
isAcc _ = False

isNop :: Instruction -> Bool
isNop (Nop _) = True
isNop _ = False 

parseInstruction :: String -> Instruction
parseInstruction str
        | x == "acc" = Acc (read val)
        | x == "jmp" = Jump (read val)
        | x == "nop" = Nop (read val)
        where 
                [x,y] = splitOn " " str 
                val = filter (/= '+') y