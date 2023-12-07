import time
import math

cards = "AKQJT98765432"

def card_to_val(card):
    if card == "A":
        return 12
    elif card == "K":
        return 11
    elif card == "Q":
        return 10
    elif card == "J":
        return 9
    elif card == "T":
        return 8
    else:
        return int(card) - 2

def key_of_hand(hand):
    val = 13**4 * card_to_val(hand[0]) + 13**3 * card_to_val(hand[1]) + 13**2 * card_to_val(hand[2]) + 13 * card_to_val(hand[3]) + card_to_val(hand[4])
    frequencies = [hand.count(str(card)) for card in cards]
    if(5 in frequencies):
        return 6 * 13**5 + val
    if(4 in frequencies and 1 in frequencies):
        return 5 * 13**5 + val
    if(3 in frequencies and 2 in frequencies):
        return 4 * 13**5 + val
    if(3 in frequencies):
        return 3 * 13**5 + val
    if(frequencies.count(2) == 2):
        return 2 * 13**5 + val
    if(frequencies.count(2) == 1):
        return 1 * 13**5 + val
    return val

def key_of_pair(pair):
    return key_of_hand(pair[0])

with open("in.txt") as input:
    lines = input.read().splitlines()
    
    hand_bid_list = []
    bid_list = []
    for line in lines:
        hand_bid_list.append(line.split())
    
    sorted_hands = sorted(hand_bid_list, key=key_of_pair)
    
    winnings = 0
    i = 1
    while i < len(sorted_hands) + 1:
        winnings += i * int(sorted_hands[i - 1][1])
        i += 1
    
    print(winnings)
    
    