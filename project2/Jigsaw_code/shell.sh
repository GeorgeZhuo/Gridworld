#! /usr/bin/env bash

av = 0
for i in {1..50}; do
    ant part2
    result=`grep "No solution" ASearchDialog.txt`
    if [ ! -z "$result" ]; then
	echo "-1" >> result.txt
    else
	result=`grep -o "Total number of searched nodes:[0-9]\+" ASearchDialog.txt | grep -o '[0-9]\+$'`
	echo $result >> result.txt
	av += $result
    fi
echo $av >> result.txt
done
