-- monty.lua
-- by Conor Gilmer (conor.gilmer@gmail.com)
-- Monty Hall Paradox
--


-- Main Program

local theSeed = os.time()
repeat
io.write("Do you want to Play(P) or run Simulation (S)? \n")
playorsimulate = string.upper(io.read())
until playorsimulate == "P" or playorsimulate =="S" 

if playorsimulate =="P" then

-- Play Game
repeat
playMonty(1)
io.write("Do you want to play again (Y/N)?\n");
playagain = string.upper(io.read())
until playagain ~= "Y" 

io.write(" End of Monty Hall - Thank You for Playing \n")
elseif playorsimulate =="S" then
-- Simulate 1000 games
simulate(1000, theSeed)
io.write("End of Monty Hall Simulation.\n")
else
io.write("Exiting\n")
end

-- end of main program

-- Return random card
function getRandom(myTable)

local theSeed = os.time()
math.randomseed(theSeed)
result = myTable[ math.random(1 ,table.getn(myTable)) ] 
io.write("result = ", result, " len ", table.getn(myTable), " .\n")
return result
end

-- Return Remaining Card
function getRemaining(myTable, removed, chosen)
for r = 1, table.getn(myTable) do
if myTable[r] ~= chosen then
if myTable[r] ~= removed then
	remaining = myTable[r]
 end
end
end --end of for

return remaining
end

-- function to play the game
function playMonty(one)
io.write("Welcome to the Monty Hall Paradox Simulation.\n")
-- get user input
-- Select a Card A, B or C
repeat
io.write("Pick a Card from [A], [B] or [C]? ")
chosencard = string.upper(io.read())
until chosencard == "A" or chosencard =="B" or chosencard=="C"
io.write("You picked ", chosencard, ".\n")

-- Randomly chose the Winning Card
local myTable = { 'A', 'B', 'C' }
local theSeed = os.time()
math.randomseed(theSeed)
for i=1, 20 do
winningcard = myTable[ math.random(1 ,3) ] 
end

-- Remove random not winning card from the two not chosen by the player
repeat 
removecard = myTable[ math.random(1 ,3) ] 
until winningcard ~= removecard and removecard ~= chosencard
io.write("Random inncorect card chosen\n");

-- player can stick with their original choice or switch(twist) to the other remaining card
-- Stick or Twist
remaining = getRemaining(myTable, removecard, chosencard)
originalchoice =chosencard
repeat
io.write("Stick(S) with ", chosencard, " or Twist(T) to ", remaining, "\n");
sticktwist = string.upper(io.read())
until sticktwist == "T" or sticktwist =="S" 
if sticktwist =="T" then
sticktwisttext="Twisting"
chosencard = remaining
else
sticktwisttext="Sticking"
end

-- did you win
io.write("Winning Card ", winningcard, " Removed Card ", removecard , "\n" );
if chosencard == winningcard then
io.write(" You win by ", sticktwisttext, "\n" );
else
io.write(" You lost by ", sticktwisttext, "\n")
end

io.write(" End of Game \n")
end
--- end of play the game


-- Simulate function
function simulate (times, seed)
io.write("Run Monty Hall Simulation ", times, " times\n")
twist = 0
stick = 0
for i=1, times do
--io.write("Simulation ", i ,"\n")

local myTable = { 'A', 'B', 'C' }

for _=1,100 do
seed = seed + 1
--print ("seeding with "..seed)
math.randomseed(seed)
chosencardnum = math.random(1,3)
chosencard =myTable[chosencardnum]
winningcardnum = math.random(1 ,3) 
winningcard = myTable[winningcardnum] 
end

repeat 
removecard = myTable[ math.random(1 ,3) ] 
until winningcard ~= removecard and removecard ~= chosencard

remaining = getRemaining(myTable, removecard, chosencard)

if chosencard == winningcard then
wintext = "Sticking Wins"
stick = stick + 1
else
wintext = "Twisting Wins"
twist = twist + 1
end

io.write("Winning Card ", winningcard, " : Chosen Card ", chosencard, " : Removed Card ", removecard , " - ", wintext, "\n" );
end --end of for

io.write("Game played ", times, " times\n")
io.write("Results:\n")
io.write("Sticking wins ", stick, " times (",(stick*100)/times,"%)\n")
io.write("Twisting Wins ", twist , " times (",(twist*100)/times,"%)\n" )
end
-- end of simulation



