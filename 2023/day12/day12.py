import math
import sys

saved_solutions = {}
def compute_arrangements(springs, group_sizes):
    if len(group_sizes) == 0:
        if not '#' in springs:
            return 1
        else:
            return 0

    if (springs, tuple(group_sizes)) in saved_solutions.keys():
        return saved_solutions[(springs,tuple(group_sizes))]
    
    min_length = sum(group_sizes) + len(group_sizes) - 1
    arrangements = 0
    for i in range(len(springs)):
        if i > 0 and springs[i - 1] == '#':
            break
        if i > len(springs) - min_length:
            break
        if springs[i] == '.':
            continue
        if '.' in springs[i:i + group_sizes[0]]:
            continue
        if i + group_sizes[0] < len(springs) and '#' == springs[i + group_sizes[0]]:
            continue

        arrangements += compute_arrangements(springs[i+group_sizes[0] + 1:], group_sizes[1:])
    saved_solutions[(springs, tuple(group_sizes))] = arrangements
    return arrangements
        
    

with open("in.txt") as input:
    lines = input.read().splitlines()

    total_arrangements_p1 = 0
    total_arrangements_p2 = 0
    
    for line in lines:
        [springs, group_sizes] = line.split()
        group_sizes = list(map(lambda x : int(x) , group_sizes.split(',')))
        total_arrangements_p1 += compute_arrangements(springs, group_sizes)
        total_arrangements_p2 += compute_arrangements(((springs + "?") * 5)[:-1], group_sizes * 5)
    
    print("")
    print(total_arrangements_p1)
    print(total_arrangements_p2)