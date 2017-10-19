# Viagogo-Java
Coding assignment for Viagogo written in Java

# Build Instructions
1. Clone or download the repository. 
2. Navigate to the src folder
3. To build type javac Main.java
4. To Run type java Main

# Assumptions
There can be only one event at each coordinate

A coordinate may have no events as there are a random amount of events genereated between 0 and 400

Ticket prices can be at least $10

The range between minimum price and maximum price is no more than $30

There can be between 0 and 300 tickets for each event

# How might you change your program if you needed to support multiple events at the same location?
I would change my do/while loop in EventGrid.cs to remove the check for a coordinate already containing an event

The logic looking for nearest events would need to be changed; If multiple events were found at the same location, the events would be ordered by price

# How would you change your program if you were working with a much larger world size?
An external database may need to be used if the world were so large to make memory an issue

A more efficient search algorithm could be created to decrease the processing time used to access the data

Multi-threading could be used to parallelize the creation and finding of events to greatly speed up the process
