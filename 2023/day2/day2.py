import re

def p1_too_large(bag):
    return bag[0] > 12 or bag[1] > 13 or bag[2] > 14

color_value_map = { "red" : 0, "green" : 1, "blue" : 2}

def update_bag_min(pair, bag):
    index = color_value_map[pair[1]]
    bag[index] = max(bag[index], pair[0])

with open("in.txt") as input:
    lines = input.read().splitlines()

    id = 0
    id_sum = 0
    power_sum = 0
    for game in lines:
        id += 1
        rounds = re.split('; |: ', game)[1:]
        min_bag = [0,0,0]
        for round in rounds:
            draws = re.split(', ', round)
            for draw in draws:
                pair = draw.split()
                update_bag_min([int(pair[0]), pair[1]], min_bag)
        if p1_too_large(min_bag):
            id_sum += id
        power_sum += min_bag[0] * min_bag[1] * min_bag[2]


    print("Part 1:", id_sum)
    print("Part 2:", power_sum)