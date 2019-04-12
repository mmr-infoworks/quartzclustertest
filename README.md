0.Change your system timezone to UTC
1.Run mvn package -DskipTests
2.run sh test.sh 
3.Wait for 10 - 15 mins
4.Kill process with ps -ef|grep quartz |grep node1
5.wait for 3-4 mins
6.Kill process with ps -ef|grep quartz|grep node2
7.cat node* |grep 'sample job' |sort
8.Verify above output to see that all 4 Sample jobs are getting triggered every minute across node1 and node2.
 After killing node1 process,all jobs get scheduled via node2 
