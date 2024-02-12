# Welcome to fifo-event-system 👋
![Version](https://img.shields.io/badge/version-0.0.0-blue.svg?cacheSeconds=2592000)

> First In First Out Event System (Coupon)

## Install

```sh
$ docker-compose up -d
```

## Requirements


We plan to conduct an event offering discount coupons to the first 100 people on a first-come, first-served basis.

This event must satisfy the following conditions.

- It must be awarded to only the first 100 people.
- No more than 100 coupons should be issued.
- It must be able to withstand surges in traffic.

## Problem & Solution 1

- P: Race condition (coupon count)
- S: Redis (single thread, incr)

## Problem & Solution 2

- P: MySql is under load
  - Assuming that it is possible to insert 100 pieces of data per minute.
    - 10:00: coupon insert 10000 request => 100min
    - 10:01: order request => processed after 100min (timeout)
    - 10:02: sign-up request => processed after 100min (timeout)
  - RDB CPU usage increase.
- S: Kafka

```sh
# create topic
$ docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic test_topic

# execute produce (terminal 1)
$ docker exec -it kafka kafka-console-producer.sh --topic test_topic --broker-list 0.0.0.0:9092

# execute consumer (terminal 2)
$ docker exec -it kafka kafka-console-consumer.sh --topic test_topic --bootstrap-server localhost:9092
```

```sh
# coupon topic
$ docker exec -it kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic

# coupon consumer 
$ docker exec -it kafka kafka-console-consumer.sh --topic coupon_create --bootstrap-server localhost:9092 --key-deserializer "org.apache.kafka.common.serialization.StringDeserializer" --value-deserializer "org.apache.kafka.common.serialization.LongDeserializer"
```

## Problem & Solution 3

- Limit 1 coupon per person (additional requirement)
  - Use Set data structure (Redis)

## Author

👤 **Kevin Ahn**

* Github: [@seung-seop-ahn](https://github.com/seung-seop-ahn)
