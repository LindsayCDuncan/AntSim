# AntSim
Ant simulator project
Ant simulator project from my data structures class in 2017. Tee instructor provided the gui and data structure implementations (not # included here) and we needed to link the gui to the logic and use the provided data structures classes.

Gameboard is comprised of tiles (like a grid). Simulation starts with only the center 9 tiles unveiled. Tiles may cohtain food
Queen ant: resides in the center of the grid, eats 1 food a day, makes a random ant every few days
Forager ant: looks for food, leaves phermone trail back to food, follows same trail back and forth until food is exhausted, deposits 
food at center grid tile for queen to eat
Scout ant: Moves around randomly unveiling hidden tiles.
Warrior ant: moves around randomly, if an enemy ant is spotted, attacks the ant; has random chance to die in battle
Bala ant: enemy ant; starts at top left tile, moves randomly, if it enters a tile with another ant, has a chance to kill that ant

The simulation ends if the queen dies of starvation (no food in her tile) or an enemy ant kills her
