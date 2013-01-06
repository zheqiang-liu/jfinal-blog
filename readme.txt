在运行项目之前请先配置WebRoot/WEB-INF/conf.properties

dbname 数据库
username 数据库用户名
password 数据库密码
host 数据库主机
port 数据库端口

title 博客的title
pageSize 使用的page size

email_username 发送邮箱的用户名
email_password 邮箱的密码
email_protocol 发送邮箱的协议 默认为smtp
email_host 服务器 如 126的为smtp.126.com qq的为smtp.qq.com

然后再导入数据库数据文件 db/sql.sql

由于刚开始做的时候,后台就设定为只为我使用,所以后台就没有考虑到浏览器的兼容性,所以大家尽量用chrome,firefox浏览器
后台管理URL /admin
用户名:mike
密码:root