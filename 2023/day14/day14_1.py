with open("in.txt") as input:
    lines = input.read().splitlines()
    
    lines = [list(line) for line in lines]
    
    
    while True:
        ball_moved = False
        
        for i in range(len(lines)):
            for j in range(len(lines[i])):
                if i > 0 and lines[i][j] == 'O' and lines[i - 1][j] == '.':
                    lines[i][j] = '.'
                    lines[i-1][j] = 'O'
                    ball_moved = True
                    
        
        if not ball_moved:
            break
        
        
        
    sum = 0
    load = len(lines)
    for line in lines:
        sum += load * line.count('O')
        load -= 1
    
    print(sum)
