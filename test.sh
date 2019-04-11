#!/bin/bash
java -jar target/quartzintro-0.0.1-SNAPSHOT.jar node1 > node1.txt &
java -jar target/quartzintro-0.0.1-SNAPSHOT.jar node2 > node2.txt &
