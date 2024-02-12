FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=password
ENV MYSQL_DATABASE=coupon

EXPOSE 3306

# docker build -t coupon:latest .
# docker run  -d -p 3306:3306 --name coupon coupon:latest
