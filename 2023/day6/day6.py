import time
import math

def win_range(time, distance):
    t1 = math.ceil(time * 0.5 - math.sqrt(time * time * 0.25 - distance))
    t2 = math.floor(time * 0.5 + math.sqrt(time * time * 0.25 - distance))
    return t2 - t1 + 1

with open("in.txt") as input:
    st = time.time()
    lines = input.read().splitlines()
    times = list(map(lambda x: int(x), lines[0].split()[1:]))
    distances = list(map(lambda x: int(x), lines[1].split()[1:]))
    
    p1_product = 1
    for i in range(len(times)):
        p1_product *= win_range(times[i], distances[i])
    
    print(p1_product)
    
    print(win_range(int(lines[0].split(":")[1].replace(" ", "")), int(lines[1].split(":")[1].replace(" ", ""))))
        
    et = time.time()
    print("Elapsed time: ", et - st)