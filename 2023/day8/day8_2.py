import time
import math

def node_to_index(node):
    node_nums = list(map(lambda x: ord(x) - ord('A'), node))
    return node_nums[0] * 26**2 + node_nums[1] * 26 + node_nums[2]

def index_to_node(index):
    node_nums = [0,0,0]
    node_nums[0] = math.floor(index / 26**2)
    index -= node_nums[0] * 26**2
    node_nums[1] = math.floor(index / 26)
    index -= node_nums[1] * 26
    node_nums[2] = index
    return list(map(lambda x: chr(x + ord('A')), node_nums))
    
def all_end(list):
    for x in list:
        if not (x % 26) == 25:
            return False
    return True

with open("in.txt") as input:
    lines = input.read().splitlines()
    
    directions = list(map(lambda x: 0 if x == 'L' else 1, lines[0]))
    nodes = [[0,0] for i in range(26**3)]
    start_nodes = []
    
    i = 2
    while i < len(lines):
        line = lines[i]
        curr_node = lines[i][0:3]
        left_node = lines[i][7:10]
        right_node = lines[i][12:15]
        nodes[node_to_index(curr_node)] = [node_to_index(left_node), node_to_index(right_node)]
        if curr_node[2] == 'A':
            start_nodes.append(node_to_index(curr_node))
        i += 1
            
    print(len(start_nodes))
    
    walk_nodes = start_nodes
    
    node_lists = [[] for _ in start_nodes]
    node_lists_at_repeats = [[] for _ in start_nodes]
    first_repeats = [0 for _ in start_nodes]
    
    i = 0
    while i < len(walk_nodes):
        current_direction = 0
        counter = 0
        while True:
            if current_direction == 0:
                if walk_nodes[i] in node_lists_at_repeats[i]:
                    pass
                node_lists_at_repeats[i].append(walk_nodes[i])
            node_lists[i].append(walk_nodes[i])
            walk_nodes[i] = nodes[walk_nodes[i]][directions[current_direction]]
            if (walk_nodes[i] % 26) == 25:
                counter += 1
                if counter == 2:
                    break
            current_direction = (current_direction + 1) % len(directions)
        first_repeats[i] = node_lists_at_repeats[i].index(walk_nodes[i]) * len(directions)
        i += 1
        
    print(math.lcm(*first_repeats))
    