# Life Tracker

一个用于追踪和记录生活的全栈小项目：

- 后端：Spring Boot 3 + MyBatis + MySQL
- 前端：Vue 3 + Vite + Element Plus

## 功能

- 生活记录（Activity）新增/查询/删除
- 前后端分离，通过 REST API 通信

## 环境要求

- JDK 17+
- Maven Wrapper（项目已自带 `mvnw` / `mvnw.cmd`）
- Node.js 18+（建议 LTS）
- MySQL 8+

## 数据库准备

1. 创建数据库 `life-tracker`（字符集推荐 `utf8mb4`）。
2. 创建表 `activity_log`：

```sql
USE `life-tracker`;

CREATE TABLE IF NOT EXISTS `activity_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` TEXT NOT NULL COMMENT '活动内容',
  `category` VARCHAR(50) DEFAULT NULL COMMENT '分类',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动日志表';
```

## 后端启动（Spring Boot）

在项目根目录（`life-tracker`）执行：

```bash
./mvnw spring-boot:run
```

Windows PowerShell：

```powershell
.\mvnw spring-boot:run
```

默认端口：`8081`  
数据库连接配置：见 [application.yaml](file:///e:/Project/life-tracker/src/main/resources/application.yaml)

## 前端启动（Vue 3）

进入前端目录：

```bash
cd frontend
```

安装依赖：

```bash
npm install
```

如果安装较慢可使用 npmmirror：

```bash
npm install --registry=https://registry.npmmirror.com
```

启动开发服务器：

```bash
npm run dev
```

默认访问地址：`http://localhost:5173/`

## 接口说明

基础路径：`http://localhost:8081`

- 查询记录：`GET /api/activities`
- 新增记录：`POST /api/activities`
  - JSON Body 示例：
    ```json
    { "content": "今天完成了第一阶段", "category": "工作" }
    ```
- 删除记录：`DELETE /api/activities/{id}`

## 常见问题

### 1) 浏览器无法访问（ERR_CONNECTION_REFUSED）

- 确认后端已启动并监听 `8081` 端口
- 若端口被占用，修改 [application.yaml](file:///e:/Project/life-tracker/src/main/resources/application.yaml) 中 `server.port`

### 2) 中文乱码

- 数据库/表使用 `utf8mb4`
- API 测试建议使用浏览器、Postman、Apifox 等工具（命令行终端可能出现编码显示问题）

