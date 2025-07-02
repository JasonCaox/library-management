# Library Management System

一个基于 Spring Boot 的图书馆管理系统，支持用户认证、图书管理和借阅归还功能。

## 功能特性

### 用户管理
- **用户注册**：支持新用户注册，设置用户名、密码和角色
- **用户登录**：基于 session 的用户认证系统
- **角色管理**：支持管理员（admin）和普通用户（user）两种角色
- **用户退出**：安全的登出功能

### 图书管理
- **图书列表**：展示所有图书信息，包括书名、作者、分类、状态
- **添加图书**：管理员可以添加新图书到系统
- **编辑图书**：管理员可以修改图书信息
- **删除图书**：管理员可以删除图书（带确认提示）
- **图书状态**：支持"可借"和"已借"两种状态

### 借阅功能
- **图书借阅**：用户可以对"可借"状态的图书进行借阅
- **图书归还**：借阅人可以归还自己借阅的图书
- **借阅记录**：系统自动记录借阅时间、归还时间和借阅状态
- **状态同步**：借阅/归还后自动更新图书状态
- **权限控制**：只有登录用户才能借阅，只有借阅人才能归还

### 界面特性
- **响应式设计**：使用 Bootstrap 5 构建现代化界面
- **用户友好**：借阅/归还操作后显示成功/失败提示弹窗
- **权限显示**：根据用户角色和图书状态动态显示操作按钮
- **美观界面**：现代化的卡片式布局和表格设计

## 技术栈

### 后端
- **Spring Boot 2.x**：主框架
- **Spring MVC**：Web 层框架
- **Spring JDBC**：数据访问层（JdbcTemplate）
- **Thymeleaf**：模板引擎
- **MySQL**：数据库

### 前端
- **Bootstrap 5**：UI 框架
- **HTML5 + CSS3**：页面结构
- **JavaScript**：交互逻辑
- **Thymeleaf**：服务端模板渲染

## 项目结构

```
library-management/
├── src/main/java/cn/tcu/librarymanagement/
│   ├── controller/          # 控制器层
│   │   ├── BookPageController.java      # 图书页面控制器
│   │   ├── UserController.java          # 用户控制器
│   │   └── BorrowRecordController.java  # 借阅记录控制器
│   ├── service/             # 业务逻辑层
│   │   ├── BookService.java
│   │   ├── UserService.java
│   │   ├── BorrowRecordService.java
│   │   └── impl/            # 服务实现
│   ├── mapper/              # 数据访问层
│   │   ├── BookMapper.java
│   │   ├── UserMapper.java
│   │   └── BorrowRecordMapper.java
│   ├── entity/              # 实体类
│   │   ├── Book.java
│   │   ├── User.java
│   │   └── BorrowRecord.java
│   ├── config/              # 配置类
│   │   └── WebConfig.java
│   └── interceptor/         # 拦截器
│       └── LoginInterceptor.java
├── src/main/resources/
│   ├── templates/           # 页面模板
│   │   ├── login.html       # 登录页面
│   │   ├── register.html    # 注册页面
│   │   ├── book_list.html   # 图书列表页面
│   │   ├── add_book.html    # 添加图书页面
│   │   └── edit_book.html   # 编辑图书页面
│   └── application.properties # 配置文件
└── pom.xml                  # Maven 配置
```

## 数据库设计

### 用户表 (user)
```sql
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);
```

### 图书表 (book)
```sql
CREATE TABLE book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    category VARCHAR(255),
    status VARCHAR(20) DEFAULT '可借'
);
```

### 借阅记录表 (borrow_record)
```sql
CREATE TABLE borrow_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    borrow_time DATETIME NOT NULL,
    return_time DATETIME,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);
```

## 安装部署

### 环境要求
- JDK 8 或更高版本
- Maven 3.6+
- MySQL 5.7 或更高版本

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/your-username/library-management.git
   cd library-management
   ```

2. **配置数据库**
   - 创建 MySQL 数据库
   - 执行上述 SQL 语句创建表结构
   - 修改 `src/main/resources/application.properties` 中的数据库连接信息

3. **运行项目**
   ```bash
   mvn spring-boot:run
   ```

4. **访问系统**
   - 打开浏览器访问 `http://localhost:8080`
   - 默认会跳转到登录页面

### 配置文件示例

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

server.port=8080
```

## 使用说明

### 管理员功能
1. **登录系统**：使用管理员账号登录
2. **管理图书**：
   - 查看所有图书列表
   - 添加新图书
   - 编辑图书信息
   - 删除图书
3. **查看借阅记录**：可以查看所有用户的借阅历史

### 普通用户功能
1. **注册账号**：新用户需要先注册
2. **登录系统**：使用注册的账号登录
3. **借阅图书**：
   - 浏览可借图书
   - 点击"借阅"按钮借阅图书
   - 查看借阅成功提示
4. **归还图书**：
   - 在图书列表中查看自己借阅的图书
   - 点击"归还"按钮归还图书
   - 查看归还成功提示

### 操作流程示例

1. **用户注册**：
   - 访问注册页面
   - 填写用户名、密码、角色
   - 提交注册信息

2. **用户登录**：
   - 输入用户名和密码
   - 系统验证身份
   - 登录成功后跳转到图书列表

3. **借阅图书**：
   - 在图书列表中找到想借阅的图书
   - 确认图书状态为"可借"
   - 点击"借阅"按钮
   - 系统显示借阅成功弹窗

4. **归还图书**：
   - 在图书列表中找到已借阅的图书
   - 点击"归还"按钮
   - 系统显示归还成功弹窗

## 开发说明

### 核心功能实现

1. **借阅逻辑**：
   - 检查图书状态是否为"可借"
   - 创建借阅记录
   - 更新图书状态为"已借"
   - 设置借阅时间

2. **归还逻辑**：
   - 验证当前用户是否为借阅人
   - 更新借阅记录状态为"已归还"
   - 设置归还时间
   - 更新图书状态为"可借"

3. **权限控制**：
   - 使用拦截器检查用户登录状态
   - 根据用户角色显示不同操作按钮
   - 后端验证操作权限

### 扩展建议

1. **功能扩展**：
   - 添加图书搜索功能
   - 实现借阅历史页面
   - 添加图书封面图片上传
   - 实现借阅到期提醒

2. **技术优化**：
   - 添加 Redis 缓存
   - 实现前后端分离架构
   - 添加单元测试
   - 集成 Swagger API 文档

## 贡献指南

1. Fork 本项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 联系方式

如有问题或建议，请通过以下方式联系：
- 提交 Issue
- 发送邮件至：[your-email@example.com]

---

**感谢使用图书馆管理系统！**