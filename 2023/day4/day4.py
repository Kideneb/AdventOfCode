import re

with open("in.txt") as input:

    lines = input.read().splitlines()

    point_sum = 0
    scratch_cards = [1 for line in lines]
    current_card = 0
    for line in lines:
        [win_nums, my_nums] = line.split(": ")[1].split(" | ")
        intersection_size = len(list(set(win_nums.split())& set(my_nums.split())))
        if (intersection_size != 0):
            point_sum += 2 ** (intersection_size - 1)
            for i in range(intersection_size):
                scratch_cards[current_card + i + 1] += scratch_cards[current_card]
        current_card += 1
    
    print(point_sum)
    print(sum(scratch_cards))