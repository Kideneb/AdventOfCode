import copy

with open("in.txt") as input:
    lines = input.read().splitlines()
    
    lines = [list(line) for line in lines]
    def move_north():
        for i in range(len(lines[0])):
            current_balls = 0
            for j in reversed(range(len(lines))):
                if lines[j][i] == '#':
                    for k in range(current_balls):
                        lines[j + k + 1][i] = 'O'
                    current_balls = 0
                    continue
                if lines[j][i] == 'O':
                    lines[j][i] = '.'
                    current_balls += 1
                if j == 0:
                    for k in range(current_balls):
                        lines[k][i] = 'O'
    
    def move_south():
        for i in range(len(lines[0])):
            current_balls = 0
            for j in range(len(lines)):
                if lines[j][i] == '#':
                    for k in range(current_balls):
                        lines[j - k - 1][i] = 'O'
                    current_balls = 0
                    continue
                if lines[j][i] == 'O':
                    lines[j][i] = '.'
                    current_balls += 1
                if j == len(lines) - 1:
                    for k in range(current_balls):
                        lines[len(lines) - 1 - k][i] = 'O'
    
    def move_west():
        for j in range(len(lines)):
            current_balls = 0
            for i in reversed(range(len(lines[0]))):
                if lines[j][i] == '#':
                    for k in range(current_balls):
                        lines[j][i + k + 1] = 'O'
                    current_balls = 0
                    continue
                if lines[j][i] == 'O':
                    lines[j][i] = '.'
                    current_balls += 1
                if i == 0:
                    for k in range(current_balls):
                        lines[j][k] = 'O'
    
    def move_east():
        for j in range(len(lines)):
            current_balls = 0
            for i in range(len(lines[0])):
                if lines[j][i] == '#':
                    for k in range(current_balls):
                        lines[j][i - (k + 1)] = 'O'
                    current_balls = 0
                    continue
                if lines[j][i] == 'O':
                    lines[j][i] = '.'
                    current_balls += 1
                if i == len(lines[0]) - 1:
                    for k in range(current_balls):
                        lines[j][len(lines[0]) - 1 - k] = 'O'
            
        
    boards = []
    while not lines in boards:
        boards += [copy.deepcopy(lines)]
        move_north()
        move_west()
        move_south()
        move_east()
        
    first = boards.index(lines)
    second = len(boards)
    offset = (1000000000 - first) % (second - first)
    
    lines = boards[first + offset]
    sum = 0
    load = len(lines)
    for line in lines:
        sum += load * line.count('O')
        load -= 1
    
    print(sum)
