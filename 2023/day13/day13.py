import copy

def check_horizontal(picture, row):
    offset = 0
    while row - offset >= 0 and row + 1 + offset < len(picture):
        if picture[row - offset] != picture[row + 1 + offset]:
            return False
        offset += 1
    return True

def equal_columns(picture, col1, col2):
    for row in picture:
        if row[col1] != row[col2]:
            return False
    return True

def check_vertical(picture, col):
    offset = 0
    while col - offset >= 0 and col + 1 + offset < len(picture[0]):
        if not equal_columns(picture, col - offset, col + 1 + offset):
            return False
        offset += 1
    return True

def find_reflection(picture):
    output = []
    for row in range(len(picture) - 1):
        if check_horizontal(picture, row):
            output += [(row + 1) * 100]
        
    for col in range(len(picture[0]) - 1):
        if check_vertical(picture, col):
            output += [col + 1]
    return output
        
def try_smudges(picture, forbidden_value):
    for row in range(len(picture)):
        for col in range(len(picture[0])):
            new_picture = copy.deepcopy(picture)
            new_picture[row][col] = '#' if new_picture[row][col] == '.' else '.'
            output_list = find_reflection(new_picture)
            for value in output_list:
                if  value != forbidden_value:
                    return value

with open("in.txt") as input:
    lines = input.read().splitlines()

    picture = []
    
    sum = 0
    smudge_sum = 0
    for line in lines:
        if line != "":
            picture += [list(line)]
            continue
        
        val = find_reflection(picture)[0]
        sum += val
        smudge_sum += try_smudges(picture, val)
        picture = []
    
    print(sum)
    print(smudge_sum)
