import re

with open("in.txt") as input:

    lines = input.read().splitlines()
    seeds = []
    maps = []
    
    ### Part 1
    current_map = -1
    for line in lines:
        if line == "":
            current_map += 1
            maps = maps + [[]]
            continue
        if line.startswith("seeds: "):
                seeds = list(map(lambda x : int(x), line.split(": ")[1].split()))
                print(seeds)
                continue
        if  line.endswith(":"):
            continue
        triple = list(map(lambda x : int(x), line.split(" ")))
        maps[current_map] = maps[current_map] + [[triple[1], triple[1] + triple[2] - 1, triple[0] - triple[1]]] # Saves begin, end of range and offset
        
    
    for map_ in maps:
        for i in range(len(seeds)):
            seed = seeds[i]
            for entry in map_:
                if entry[0] <= seed and seed <= entry[1]:
                    seeds[i] = seed + entry[2]
                    
    print(min(seeds))
    
    ### Part 2
    line = lines[0]
    seeds = list(map(lambda x : int(x), line.split(": ")[1].split()))
    seed_pairs = []
    i = 0
    while i < len(seeds):
        seed_pairs += [[seeds[i], seeds[i] + seeds[i+1] - 1]]
        i += 2
    seeds = seed_pairs
    print(seeds)
    
    
    for map_ in maps:
        i = 0
        while i < len(seeds):
            [min_seed, max_seed] = seeds[i]
            
            for [min_src, max_src, offset] in map_:
                if min_src > min_seed and min_src <= max_seed:
                    seeds += [[min_seed, min_src - 1]]
                    seeds[i] = [min_src, max_seed]
                    [min_seed, max_seed] = seeds[i]
                if max_src < max_seed and max_src >= min_seed:
                    seeds += [[max_src + 1, max_seed]]
                    seeds[i] = [min_seed, max_src]
                    [min_seed, max_seed] = seeds[i]
                if min_src <= min_seed and max_seed <= max_src:
                    seeds[i] = [min_seed + offset, max_seed + offset]
            
            i += 1
    
    print(min(list(map(lambda x : x[0], seeds))))