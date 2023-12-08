import time
import math

def node_to_index(node):
    node_nums = list(map(lambda x: ord(x) - ord('A'), node))
    return node_nums[0] * 26**2 + node_nums[1] * 26 + node_nums[2]
    

with open("in.txt") as input:
    lines = input.read().splitlines()
    
    directions = list(map(lambda x: 0 if x == 'L' else 1, lines[0]))
    nodes = [[0,0] for i in range(26**3)]
    
    i = 2
    while i < len(lines):
        line = lines[i]
        curr_node = lines[i][0:3]
        left_node = lines[i][7:10]
        right_node = lines[i][12:15]
        nodes[node_to_index(curr_node)] = [node_to_index(left_node), node_to_index(right_node)]
        i += 1
    print(nodes)
    
    current_node = 0
    current_direction = 0
    counter = 0
    while not current_node == 26**3 - 1:
        current_node = nodes[current_node][directions[current_direction]]
        current_direction = (current_direction + 1) % len(directions)
        counter += 1
    print(counter)
    
    
    