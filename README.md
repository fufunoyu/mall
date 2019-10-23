# 简介
民生银行网上商城


# 使用
1. 下载到本地，执行db/schema.sql文件
2. 配置mall-dao-impl中的db.properties为自己数据库参数
3. 下载maven jar包
4. 编译mall-web为war包
5. 放入tomcat中运行 

# 目录结构
* mall
  * mall-core:公共文件模块，存放公共类，pojo，dto，vo等
  * mall-dao:数据访问层接口定义模块
  * mall-dao-impl:数据访问层接口实现模块
  * mall-manager:通用处理模块，主要对dao数据进行处理封装（缓存等）
  * mall-manager-impl:通用处理模块实现
  * mall-service:业务逻辑层接口定义模块
  * mall-service-impl:业务逻辑层接口实现模块
  * mall-web:用户显示模块
  * mall-admin:后台管理模块
  * mall-web-support：显示层扩展模块，对相关显示层通用功能封装
