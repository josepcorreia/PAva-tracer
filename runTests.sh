#!/bin/sh
if [ ! -d ./tests ]; then
    mkdir ./tests;
fi;

echo "## COMPILIIIIING"
ant

javac -cp .:dist/PAva-tracer.jar Test0.java
echo "## RUNNING TEST 0"
java -cp .:dist/PAva-tracer.jar:lib/javassist-3.18.0-GA.jar ist.meic.pa.TraceVM Test0 > tests/test0.out 2>&1
diff tests/test0.out tests/test0.expected

javac -cp .:dist/PAva-tracer.jar Test1.java
echo "## RUNNING TEST 1"
java -cp .:dist/PAva-tracer.jar:lib/javassist-3.18.0-GA.jar ist.meic.pa.TraceVM Test1 > tests/test1.out 2>&1
diff tests/test1.out tests/test1.expected

javac -cp .:dist/PAva-tracer.jar Test2.java
echo "## RUNNING TEST 2"
java -cp .:dist/PAva-tracer.jar:lib/javassist-3.18.0-GA.jar ist.meic.pa.TraceVM Test2 > tests/test2.out 2>&1
diff tests/test2.out tests/test2.expected
