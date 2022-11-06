# mybatis-template-zero 

# 使用
1、新建mysql数据 springboot
2、通过 springboot.sql 创建表。
3、在 mp-api/src/main/resources/application.yml 修改相关数据库配置。
```
server:
  port: 8083

spring:
  mvc:
    static-path-pattern: /**
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/springboot?serverTimezone=Asia/Shanghai&characterEncoding=utf8
    username: xxx
    password: xxx
```
4、启动应用
打开 mp-api/src/main/java/com/lucaswangdev/MpApiApplication.java 文件，启动应用。