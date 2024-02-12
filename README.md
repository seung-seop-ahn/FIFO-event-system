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

## Author

👤 **Kevin Ahn**

* Github: [@seung-seop-ahn](https://github.com/seung-seop-ahn)
