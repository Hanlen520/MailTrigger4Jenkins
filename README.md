# MailTrigger4Jenkins
邮件触发Jenkins构建测试项目
##### 项目意义  
为了保持测试项目的独立性，将自动检查邮件的前置操作改造为另一个Maven项目，通过Jenkins管道的形式构建Job工作流，实现检查是否接收到远程仓库构建后发出的自动化测试的通知，自动拉起自动化测试,自动化测试项目见下文链接：  
[基于Appium的自动化测试项目](https://github.com/hardworking4u/TestInAppium)  
##### 持续优化
* 将该部分合并到测试代码中并不影响原有项目  
* 改造现有业务逻辑  
* 更新Jenkins的配置逻辑  
