
#!/bin/bash

nohup /{InstallationDirectory}/kafka_2.12-2.2.0/bin/zookeeper-server-start.sh -daemon /{InstallationDirectory}/kafka_2.12-2.2.0/config/zookeeper.properties &
sleep 10
nohup /{InstallationDirectory}/kafka_2.12-2.2.0/bin/kafka-server-start.sh -daemon /{InstallationDirectory}/kafka_2.12-2.2.0/config/server.properties &
sleep 10

bin\kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic transaction-topic
