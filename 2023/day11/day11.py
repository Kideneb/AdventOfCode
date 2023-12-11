import math
import sys

def empty_rows(lines):
    rows = []
    i = 0
    while i < len(lines):
        if not '#' in lines[i]:
            rows += [i]
        i += 1
    return rows
        
def empty_cols(lines):
    j = 0
    cols = []
    while j < len(lines[0]):
        i = 0
        has_galaxy = False
        while i < len(lines):
            if lines[i][j] == '#':
                has_galaxy = True
                break
            i += 1
        if not has_galaxy:
            cols += [j]
        j += 1
    return cols
        
def dist(p1, p2, rows, cols, expansion):
    val = abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])
    for row in rows:
        if (row - p1[0]) * (row - p2[0]) < 0:
            val += expansion - 1
    for col in cols:
        if (col - p1[1]) * (col - p2[1]) < 0:
            val += expansion - 1
    return val

with open("in.txt") as input:
    lines = input.read().splitlines()

    rows = empty_rows(lines)
    print(rows)
    cols = empty_cols(lines)
    print(cols)
    galaxies = [[i,j] for i in range(len(lines)) for j in range(len(lines[0])) if lines[i][j] == '#']

    print(sum([dist(galaxies[i], galaxies[j], rows, cols, 2) for i in range(len(galaxies)) for j in range(len(galaxies)) if j > i]))
    print(sum([dist(galaxies[i], galaxies[j], rows, cols, 1000000) for i in range(len(galaxies)) for j in range(len(galaxies)) if j > i]))

