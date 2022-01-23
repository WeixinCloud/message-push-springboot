# 微信云托管·云调用示例（SpirngBoot）

[![GitHub license](https://img.shields.io/github/license/WeixinCloud/message-push-nodejs)](https://github.com/WeixinCloud/message-push-nodejs)
![GitHub package.json dependency version (prod)](https://img.shields.io/github/package-json/dependency-version/WeixinCloud/message-push-nodejs/express)

微信云托管·云调用示例，基于 SpringBoot 框架搭建，实现小程序、公众号简易的接收、回复消息功能。

![](https://qcloudimg.tencent-cloud.cn/raw/21068367f6757057a9125458be3347d4.png)

## 快速开始

前往 [微信云托管快速开始页面](https://cloud.weixin.qq.com/cloudrun/onekey)，选择相应的模板，根据引导完成部署。

## 项目结构说明

```
.
├── Dockerfile                      容器配置文件
├── README.md                       项目介绍
├── container.config.json           云托管一键部署配置
├── mvnw                            maven构建脚本
├── mvnw.cmd                        maven构建脚本（windows）
├── pom.xml                         maven依赖配置
└── settings.xml                    maven仓库配置

```

## 服务 API 文档

### `POST /`

接收来自微信的消息推送，消息结构可参考 [微信文档](https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/customer-message/receive.html#%E6%96%87%E6%9C%AC%E6%B6%88%E6%81%AF)。

接收消息后，将会调用 [发送客户消息](https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/customer-message/customerServiceMessage.send.html) 接口，返回收到的消息详情。

## License

[MIT](./LICENSE)
