import time
import math

cards = "AKQT98765432"

def card_to_val(card):
    if card == "A":
        return 12
    elif card == "K":
        return 11
    elif card == "Q":
        return 10
    elif card == "J":
        return 0
    elif card == "T":
        return 9
    else:
        return int(card) - 1

def key_of_hand(hand):
    val = 13**4 * card_to_val(hand[0]) + 13**3 * card_to_val(hand[1]) + 13**2 * card_to_val(hand[2]) + 13 * card_to_val(hand[3]) + card_to_val(hand[4])
    frequencies = [hand.count(str(card)) for card in cards]
    joker_freq = hand.count("J")
    print(hand, frequencies, joker_freq)
    
    if joker_freq + max(frequencies) == 5:
        print("royal flush")
        return 6 * 13**5 + val
    if joker_freq + max(frequencies) == 4:
        print("four of a kind")
        return 5 * 13**5 + val
    if (joker_freq == 1 and frequencies.count(1) <= 1) or joker_freq == 0 and frequencies.count(1) == 0:
        print("full house")
        return 4 * 13**5 + val
    if joker_freq + max(frequencies) == 3:
        print("three of a kind")
        return 3 * 13**5 + val
    if frequencies.count(2) == 2 or (joker_freq == 1 and frequencies.count(2) >= 1):
        print("two pair")
        return 2 * 13**5 + val
    if joker_freq + max(frequencies) == 2:
        print("one pair")
        return 1 * 13**5 + val
    print("high card")
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
    
    