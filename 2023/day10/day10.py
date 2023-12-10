import math
import sys

symbol_to_dirs = {
    "J": [[-1, 0], [0, -1]],
    "7": [[0, -1], [1, 0]],
    "|": [[1, 0], [-1, 0]],
    "-": [[0, -1], [0, 1]],
    "L": [[-1, 0], [0, 1]],
    "F": [[1, 0], [0, 1]],
    ".": [],
    "S": [],
}

def has_entry_from(symbol, direction):
    return direction in symbol_to_dirs[symbol]

def angle_between(v1, v2):
    [x1, y1] = v1
    [x2, y2] = v2
    return math.atan2(x1 * y2 - y1 * x2, x1 * x2 + y1 * y2)

def flood_fill(grid, row, col, val):
    if (grid[row][col] == val):
        return 0
    grid[row][col] = val

    filled = 1
    filled += flood_fill(grid, row - 1, col, val) if row > 0 else 0
    filled += flood_fill(grid, row + 1, col, val) if row < len(grid) - 1 else 0
    filled += flood_fill(grid, row, col - 1, val) if col > 0 else 0
    filled += flood_fill(grid, row, col + 1, val) if col < len(grid[0]) - 1 else 0
    
    return filled


with open("in.txt") as input:
    lines = input.read().splitlines()
    [s] = filter(lambda x : x[1] != -1, [[i, lines[i].find('S')] for i in range(len(lines))])
    marked_positions = [[0 for x in lines] for line in lines]
    path = [s]

    row = s[0]
    col = s[1]
    coming_from = []
    if has_entry_from(lines[row][col + 1], [0, -1]):
        coming_from = [0, -1]
        col = col + 1
    elif has_entry_from(lines[row][col - 1], [0, 1]):
        coming_from = [0, 1]
        col = col - 1
    elif has_entry_from(lines[row + 1][col], [-1, 0]):
        row = row + 1
        coming_from = [-1, 0]
    
    steps = 1
    while row != s[0] or col != s[1]:
        directions = symbol_to_dirs[lines[row][col]]
        new_dir = directions[0] if directions[1] == coming_from else directions[1]
        [row, col] = [row + new_dir[0], col + new_dir[1]]
        coming_from = [-x for x in new_dir]
        marked_positions[row][col] = 1
        path += [[row, col]]
        
        steps += 1

    print(steps / 2)

    sys.setrecursionlimit(10000)
    inside_tiles = 0
    for row in range(len(lines)):
        for col in range(len(lines)):
            if marked_positions[row][col] == 0:
                angle = 0
                # Calculate winding number
                for i in range(len(path) - 1):
                    p1 = path[i]
                    p2 = path[i+1]
                    v1 = [p1[0] - row, p1[1] - col]
                    v2 = [p2[0] - row, p2[1] - col]
                    angle += angle_between(v1, v2)
                area_size = flood_fill(marked_positions, row, col, 1)
                if abs(angle) > 1e-6:
                    inside_tiles += area_size
                
    print(inside_tiles)

            

    