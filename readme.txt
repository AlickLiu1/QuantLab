Readme

This project is written in the Java, and used approxiamtely 45 mins including reading question, understanding question, writing code and testing.

The folder includes the .jar file for the running in the terminal, and the way to run is: 

java -jar QuantLabProject.jar <inputFile> <outputFile>       ---> generates the output file including the running result

java -jar QuantLabProject.jar <inputFile> 					 ---> printout the output in the terminal

e.x. if the inputFile name is input.csv and the outputFile name is output.csv, the commands to run are:

java -jar QuantLabProject.jar "input.csv" "output.csv"

java -jar QuantLabProject.jar "input.csv"


My thoughts on improving the program is to use the MapReduce because the potential size of data would be larger than the storage size. 
Also, there are many potential overlapping data, so the MapReduce is a good choice.

My thoughts on monitoring ongoing execution of task is to use log system, and it gives feedback when it runs to certain progress.

The reason to choose Java becasue I am familiar with Java, and Java is easier to maintain and develop compared to the C++. 

