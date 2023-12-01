with open("in.txt") as input:
    lines = input.read().splitlines()

    counter = 0

    for line in lines:
        digit_list = []
        while line:
            if line[0].isdigit():
                digit_list.append(int(line[0]))
            if line.startswith("one"):
                digit_list.append(1)
            if line.startswith("two"):
                digit_list.append(2)
            if line.startswith("three"):
                digit_list.append(3)
            if line.startswith("four"):
                digit_list.append(4)
            if line.startswith("five"):
                digit_list.append(5)
            if line.startswith("six"):
                digit_list.append(6)
            if line.startswith("seven"):
                digit_list.append(7)
            if line.startswith("eight"):
                digit_list.append(8)
            if line.startswith("nine"):
                digit_list.append(9)
            line = line[1:]
        counter = counter + 10 * digit_list[0] + digit_list[-1]
    print(counter)