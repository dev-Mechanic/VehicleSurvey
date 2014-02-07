javac -d ..\\CompiledClass ..\\src\\Utility\DebugFuncs.java
javac -d ..\\CompiledClass ..\\src\\Utility\FileReaderUtility.java
javac -d ..\\CompiledClass ..\\src\\Entity\StatsSet.java
javac -d ..\\CompiledClass ..\\src\\Entity\VehicleRecord.java
javac -d ..\\CompiledClass ..\\src\\Entity\SurveyConstants.java
javac -d ..\\CompiledClass ..\\src\\CoreLayer\SurveyParser.java
javac -d ..\\CompiledClass ..\\src\\CoreLayer\StatsBuilder.java
javac -d ..\\CompiledClass ..\\src\\ConsoleProgram\Program.java

java ConsoleProgram.Program

java ConsoleProgram.Program 1

java ConsoleProgram.Program 1 > ResultsOverAll.txt
java ConsoleProgram.Program 2 > ResultsDaily.txt
java ConsoleProgram.Program 5 > Results30MinSlot.txt

