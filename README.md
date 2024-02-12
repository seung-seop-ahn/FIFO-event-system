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

## Problem & Solution

- P: Race condition
- S: Redis

## Author

👤 **Kevin Ahn**

* Github: [@seung-seop-ahn](https://github.com/seung-seop-ahn)
