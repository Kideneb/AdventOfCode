def string_to_hash(str):
    val = 0
    while str != "":
        val += ord(str[0])
        val *= 17
        val %= 256
        str = str[1:]
    return val

with open("in.txt") as input:
    lines = input.read().splitlines()
    sequence = lines[0].split(',')
    
    boxes = [[] for _ in range(256)]
    val = 0
    sum = 0
    for instruction in sequence:
        val = string_to_hash(instruction)
        sum += val
        
        if instruction[-1] == '-' :
            label = instruction[:-1]
            for [lens_label, power] in boxes[string_to_hash(label)]:
                if lens_label == label:
                    boxes[string_to_hash(label)].remove([lens_label, power])
        else:
            [label, strength] = instruction.split('=')
            box_index = string_to_hash(label)
            
            found_dup = False
            for i in range(len(boxes[box_index])):
                if boxes[box_index][i][0] == label:
                    boxes[box_index][i][1] = strength
                    found_dup = True
            if not found_dup:
                boxes[box_index] += [[label, strength]]
    
    focus_sum = 0
    
    for box_index in range(len(boxes)):
        for lens_index in range(len(boxes[box_index])):
            val = box_index + 1
            val *= int(boxes[box_index][lens_index][1])
            val *= lens_index + 1
            focus_sum += val
               
    print("")
    print(sum)
    print(focus_sum)