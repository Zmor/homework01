# 作业一：分布式缓存和消息通信练习

## 技术选型

- 数据库：MySQL
- 缓存中间件：Redis
- 消息中间件：RocketMQ
- 服务发现：Nacos
- 服务调用：OpenFeign


## 目录结构

```
├── common                       # 公共模块
├── service-account              # 账户服务
├── service-order                # 订单服务
```

## 运行环境

```bash
cd ./docker-compose
docker-compose up -d
```
