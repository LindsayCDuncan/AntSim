# AntSim
Ant simulator project

Project from my data structures class in 2017. Tee instructor provided the gui and data structure implementations (not # included here) and we needed to link the gui to the logic and use the provided data structures classes.

Gameboard is comprised of tiles (like a grid). Simulation starts with only the center 9 tiles unveiled. Tiles may contain food.
Queen ant: resides in the center tile, eats 1 food a day, and makes a random ant every few days.
Forager ant: looks for food, leaves phermone trail back to food and follows this trail back and forth until food is exhausted. It deposits 
food at center grid tile for queen to eat.
Scout ant: moves around randomly unveiling hidden tiles.
Warrior ant: moves around randomly. If an enemy ant is spotted, attacks the ant. It has a chance to die in battle.
Bala ant: enemy ant. Starts at top left tile and moves randomly. If it enters a tile with another ant, has a chance to kill that ant. May die in battle with warrior ant.

The simulation ends if the queen dies of starvation (no food in her tile) or an enemy ant kills her
