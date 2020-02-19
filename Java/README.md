# Gilded Rose Java Solution

This Java solution implements the Gilded Rose requirements.

It can be started using the GildedRoseRunner::main method from any CLI, providing 2 arguments:
- the location to a file containing the items and their initial state
  - each line should be of the form  
  `<item name>, <sellIn>, <quality>`  
  where `sellIn` is an integer (representing a number of days)  
  and `quality` is an integer.
- the number of days to run the simulation as an integer