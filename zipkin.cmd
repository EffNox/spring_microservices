@echo off
set RABBIT_ADDRESSES=127.0.0.1:5672
set STORAGE_TYPE=mysql
set MYSQL_USER=root
set MYSQL_PASS=96dg
java -jar ./zipkin-server-2.21.1-exec.jar