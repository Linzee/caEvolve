# CaEvolve

CaEvolve is name of my experiment aiming for generating low-level rules for some high-level outcome. In this case I'm generating rules of cellular automaton to fulfill some rules as whole board. For example [checker pattern](src/main/java/me/ienze/caEvolve/fitness/ChessFitnessCalculator.java), [desired count of white neighbors](src/main/java/me/ienze/caEvolve/fitness/NeighborFitnessCalculator.java), and more.

The "Evolve" part of repo name is there because I'm using evolutionary algorithm to generate fittest cellular automaton. It's quite simple implementation and it can be found in class [CaPool](src/main/java/me/ienze/caEvolve/CaPool.java).

Last part of the project is user interface. I made it so we can see how is the evolution going, if it isn't stuck on some local maximum, and so on..