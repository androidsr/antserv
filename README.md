自动化后台管理系统基础框架  
===
项目依赖：
-------
* 后台：spring boot + mybatis+jwt认证
* 数据库：mysql 
* 前台UI: HeyUI +Vue.js  

![图片展示](https://github.com/androidsr/images/blob/master/界面1.png "登录")  
![图片展示](https://github.com/androidsr/images/blob/master/界面2.png "主界面")  
![图片展示](https://github.com/androidsr/images/blob/master/界面3.png "增加")  
![图片展示](https://github.com/androidsr/images/blob/master/界面4.png "修改")  
      
### 相关连接：
HeyUI:https://www.heyui.top/
## 功能说明：
  * 此项目可按表结构自动生成后台代码及前台ui。自动完成功能（列表展示|查询|增加|删除|详情）分页下拉框等功能。
  * 前后端分离：restful api接口。前端自带静态文件运行服务器。也可迁移自nginx运行。
  ![展示](https://github.com/androidsr/images/blob/master/111.png "界面展示")  
  
## 项目配置：
 * spring boot 项目相关功能不多做说明。
 * 工具相关配置可通过工具下*.yaml文件进行配置
 * 下载源代码后，从项目resources目录中提取出others文件夹。
 * db : 数据库基础脚本。
 * tools: 代码生成工具，支持mysql,oracle 数据库。
 * ui : 前端UI基础界面。server文件夹下包含静态服务器。windows 可点击 exe运行。linux可./Run直接运行。
 
 ## tools工具使用：
  * 通过tools下工具生成相关代码时需数据库字段带有说明最好。无说明的情况下默认标题以为字段名称。生成后可手工进行修改。
  * 默认执行./autocode 会自动生成所有表相关的前后台代码。 也可执行./autocode 表名 生成单张表相关代码。
  * 生成代码后需要对项目做一些改动。当然正常情况下在不改动下也是可以运行的。如：@RequestMapping("/表名") controller 中这里的名字是以表名进行生成的
  可自行修改，修改此处后需要对前端 ui对应的目录进行同样的修改即可。  
  
## 前端说明： 
 * 前端使用heyui+vue.js。cdn模式引入。相比主流node构建前端，此结构开发速度更快。投产更方便并自带golang服务器。  
 * 在此也重点感谢heyui作者对我的帮助，对前端小白的我来说帮助了不少，提出了不少笨笨的问题。她都依依为我们解答。    
   并在组件支持不够的情况下也积极的为我们提供了新的功能组件。  
   作者人还是相关不错的。问什么问题时都感觉是以一种xx的身份，没有一点架子什么的。时分热情。  
   可大家都知道她不欠我们什么。我都感觉不太好意思问太多问题。 听说还是个######  妹子。  
 * 虽然网上heyui相关资料还比较少，但是可以进群作者都会为你一一解答。希望大家多多支持。  
 * 
 *  
 * 前端以进行了组件化，所以代码看起来都是比较简单的了。
 * 列表只需要修改columns 内容表格显示字段即可。table和按钮会自动加载相关事件等。
 * 多于的不想展示 在form.html，query.html等都可以自行删除即可。
 * ####  生成表后先通过菜单管理增加新菜单，并在权限上设置相应权限后可进行访问。
 * 其实这个东西也没什么好讲的了，跑起来一目了然。有什么问题可联系本人：781555290
