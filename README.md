自动化后台管理系统基础框架  
===
项目依赖：
-------
* 后台：spring boot + mybatis
* 数据库：mysql 
* 前台UI: HeyUI +Vue.js  

### 相关连接：
HeyUI:https://www.heyui.top/
## 功能说明：
  * 此项目可按表结构自动生成后台代码及前台ui。自动完成功能（列表展示|查询|增加|删除|详情）分页下拉框等功能。
  * 前后端分离：restful api接口。前端自带静态文件运行服务器。也可迁移自nginx运行。
## 项目配置：
 * spring boot 项目相关功能不多做说明。
 * 工具相关配置可通过工具下*.yaml文件进行配置
 * 下载源代码后，从项目resources目录中提取出others文件夹。
 * db : 数据库基础脚本。
 * tools: 代码生成工具，支持mysql,oracle 数据库。
 * ui : 前端UI基础界面。server文件夹下包含静态服务器。windows 可点击 exe运行。linux可./Run直接运行。
 
