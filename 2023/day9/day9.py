import time
import math

def extrapolate_back(old_list):
    new_list = [old_list[i+1] - old_list[i] for i in range(len(old_list) - 1)]
    if list(filter(lambda x : x != 0, new_list)) != []:
        return extrapolate_back(new_list) + old_list[-1]
    else:
        return old_list[0]
    
def extrapolate_front(old_list):
    new_list = [old_list[i+1] - old_list[i] for i in range(len(old_list) - 1)]
    if list(filter(lambda x : x != 0, new_list)) != []:
        return old_list[0] - extrapolate_front(new_list)
    else:
        return old_list[0]

with open("in.txt") as input:
    lines = input.read().splitlines()
    
    p1_sum = 0
    p2_sum = 0

    for line in lines:
        p1_sum += extrapolate_back(list(map(lambda x: int(x), line.split())))        
        p2_sum += extrapolate_front(list(map(lambda x: int(x), line.split())))


    print(p1_sum)
    print(p2_sum)
    
    