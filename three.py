#!/usr/bin/python
# Three Card Game Paradox
# One of three cards has a prize
# The player selects a card
# Then a wrong card from the other 2 cards is removed randomly
# The player can stick with original selection or change to the other card
# People tend to stick, however changing is better
# This program simulates a number of these games to prove this
#
# by Conor Gilmer

import random
stick = 0
twist = 0
stickpc = 0.0
twistpc = 0.0
games = 1000
cards = ['A', 'B', 'C']

def iteration(limit):
	winning = random.randint(1,limit)
	chosen = random.randint(1,limit)

	remove = random.randint(1,limit)
	while remove == chosen or remove == winning:
		remove = random.randint(1,limit)
	results = "Winning = " + cards[winning-1] + " | Chosen = " + cards[chosen-1] + " | removed = " + cards[remove-1] 
	if winning == chosen:
		print results + " - Verdict : Sticking Wins"
		return 1  
	else:
		print results + " - Verdict : Changing Wins"
		return 0


print "Three Card Game Paradox"
print "Playing %d times" % games

for i in range(1, games+1):
	if iteration(3) == 1:
		stick = stick +1
	else:
		twist = twist +1

stickpc = (float(stick)/float(games)) * 100
twistpc = (float(twist)/float(games)) * 100

print "Results:"
s ="Sticking wins " + str(stick) + " times ("+ str(stickpc) + "% )"
print s
s ="Changing wins " + str(twist) + " times ("+ str(twistpc) + "% )"
print s

if stick > twist:
	print "Sticking is better!"
elif stick == twist:
	print "Sticking and twisting are the same!"
else:
	print "Changing is better!"

print "The End"


