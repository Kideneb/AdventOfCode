import re

with open("in.txt") as input:

    lines = input.read().splitlines()
    gear_adjacencies = [[[] for _ in line] for line in lines]

    def p1_is_part(line_num, index, length, val):
        is_part = False
        i = line_num - 1
        while i < line_num + 2:
            j = index - 1
            while j < index + length + 1:
                if re.search("[\d\.]", lines[i][j]) is None:
                    if lines[i][j] == "*":
                        gear_adjacencies[i][j] += [val]
                    is_part = True
                j += 1 
            i += 1
        return is_part

    part_sum = 0
    line_num = 0
    while line_num <  len(lines):
        line = lines[line_num]
        i = 0
        while i < len(line):
            if line[i].isnumeric():
                number_len = re.search("\D", line[i:]).start()
                number_string = line[i:i+number_len]
                val = int(number_string)
                if p1_is_part(line_num, i, number_len, val):
                    part_sum += val
                i += number_len
            else: i += 1
        line_num += 1
    

    print(part_sum)
                
    gear_ratio_sum = sum(map(lambda list : sum(map(lambda entry : entry[0] * entry[1], filter(lambda entry: len(entry) == 2, list))) , gear_adjacencies))
    print(gear_ratio_sum)