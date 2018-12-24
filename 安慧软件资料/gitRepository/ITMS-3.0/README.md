update
本代码库为SVN仓库自动同步结果。在项目组未达成一致前，不作为开发仓库。

[![build status](http://192.168.10.190:8090/ci/projects/12/status.png?ref=master)](http://192.168.10.190:8090/ci/projects/12?ref=master)
管控平台3.0系统介绍
===
# 系统设计

 管控平台随着业务不断的扩展，同时数据量不断的增加，原有的老系统出现以下问题：
1. 性能问题且架构不支持扩展
2. 外部系统数据交换接口管理
3. 日志审计和安全问题
4. 提供多终端访问能力
5. 代码乱，不集中，模块划分不清楚
<br>新系统设计的目标是解决以上问题,架构采用成熟的spring mvc+spring+mybatis框架,
<br>通信协议采用分布式超媒体系统的体系结构风格REST,系统设计支持集群和分布式部署。
<br>[![系统总体架构图](http://192.168.10.190:8090/GKPT/ITMS3.0/blob/master/systemA.png?ref=master)](http://192.168.10.190:8090/GKPT/ITMS3.0/blob/master/systemA.png?ref=master)<br>

# 系统环境

## 准备

1. 安装tomcat
2. 安装eclipse
3. 安装firefox
4. sublime

## 运行后端开发调试环境

1. 本地安装JDK，安装TOMCAT。
2. 打开eclipse,配置服务器为tomcat
3. 下载所有CODE代码
4. 打开eclipse，选择工作目录，然后导入maven项目
5. 本地GIT代码Code目录下执行mvn instal命令
6. 切换eclipse刷新所有的工程。

## 开发项目部署

8. 完成代码功能开发，选择项目ControlPlatform和AuthenticationCenter发布。
9. 启动eclipse中的服务器
10. 启动firefox访问服务地址：http://ip:端口号/ControlPlatform访问服务。
11. 登录并测试服务

## 编译打包
目前编译打包有两种方式： 

1.开发人员将代码push到gitlab后，触发gitlab持续集成工具gitlab-cirunner执行编译。编译脚本为gitlab-ci.yml，该脚本实现编译打包操作并将包拷贝至205ftp目录中，地址为ftp://192.168.10.205/超远版本/测试版本/管控平台3.0/安装包
具体gitlab-ci.yml文件的语法请参考http://doc.gitlab.com/ce/ci/yaml/README.html 。 也可以在编译历史处，点击页面中某次构建历史行上的Retry按钮进行立即构建。

2.如果提交太频繁会导致gitlab一直处于编译中，故取消push触发打包的操作。使用下述方式进行构建：
搭建的CI环境jenkins（http://192.168.10.190:8071） 可以满足定时编译打包和随时构建打包的要求，对应项目为ITMS3.0_Package。管控平台3.0基于maven，故构建时使用mvn clean build package一系列指令完成打包操作，并编写shell脚本将对应打包文件通过ftp上传至ftp://192.168.10.205/超远版本/测试版本/管控平台3.0/安装包 。 构建失败时会给对应提交导致构建失败的开发人员及指定人发邮件通知。

# 系统部署

## 部署准备

### 机器资源分配

1. 确定系统资源有几台主机（如云南4台主机）
2. 根据实际情况合理分配资源情况
    * 主机1资源分配：管控平台3.0两个服务器端口8080 8081，Nginx代理服务，Kettle ETL调度工具
    * 主机2资源分配：状态服务器，rabbitMq （目前单机后续考虑集群）
    * 主机3资源分配：后台分析程序、redis内存数据库（目前单机后续可考虑集群）、icegrid
    * 主机4资源分配：oracle 数据库
3. 确定软件版本

### 安装基础软件

1. 根据资源分配在各机器上安装软件，可参考部署手册
2. 根据安装情况，测试是否安装成功。

## 部署管控平台服务
### 安装后台分析程序

1. 蓝盾视频参数获取服务VideoRefreshService

	           VideoRefreshService.exe.config修改数据库配置
	           LDVideoParamRefreshService重启
	           

2. 安装程序发布到itms3/iceApp/singleService
3. /itms3/iceApp/singleService目录下修改：

	           mqConfig.properties 修改MQ地址
	           dbconfig.properties 修改数据库
	           redis.properties 修改redis地址
	           
4. icegrid

	           service icegridregistry restart

	           service icegridnode restart
5. redis重启 

	           service redis restart

6. Rabbitmq重启

	           service rabbitmq restart

7. kettle调度启动 

* 在/itms3/kettle目录下修改dbConfig.txt数据库地址
* 修改/home/tmp/deploy/cron目录下 kettle.cron文件为

	           0 1 * * *  cd /usr/data-integration && ./kitchen.sh -file /home/tmp/deploy/kettle/itms.kjb >> /itms3/logs/kettle/itms.log 2>&1
	 
* 生成定时计划：

	           crontab kettle.cron
	            
* 查看当前用户所有的定时计划：

	           crontab -l

### 管控平台3.0部署

1. 采用集群部署，修改itms.conf文件，IP地址为实际地址，配置意思请求被平均分配到这两个IP中。

  	        server 127.0.0.1:8080 fail_timeout=0;
	          server 127.0.0.1:8081 fail_timeout=0;
	           
2. 部署ControlPlatform.war、Report.war和AuthenticationCenter.war到tomcat上

* 修改ControlPlatform.war中dbconfig.properties,redis.properties为相应的服务地址
* 修改ControlPlatform.war中global.properties相关信息配置解释如下：
    * 修改service_ip为本服务地址，端口IP地址和端口号要与tomcat对应
    * 修改validate_ip为状态服务器地址
    * 修改validate_time过期时间，默认即可
    * 修改routeKeys为路由配置，为了适配模块分布式，比如设备模块单独部署时需要增加为：authentication,deviceService
    * 同时需要增加：deviceService=127.0.0.1:8009配置信息
* 修改Report.war相关信息logPath.properties和birtds.xml数据库配置
* AuthenticationCenter\WEB-INF\classes目录下修改以下文件
    * dbconfig.properties 修改数据库地址
    * global.properties将三个127.0.0.1修改成tomcat部署地址ip
    * rabbitmq.properties 修改MQ地址
    * redis.properties	修改redis地址

### 访问测试
1. 启动所有tomcat服务
2. 访问http://ip/ControlPlatform地址即可
3. 登录(admin/123456)

# 程序目录结构

程序目录如图所示：
[![程序目录](http://192.168.10.190:8090/GKPT/ITMS3.0/blob/master/codeTree.png?ref=master)](http://192.168.10.190:8090/GKPT/ITMS3.0/blob/master/codeTree.png?ref=master)

# 模块结构
## 程序部署
  程序部署如图：[![部署图](http://192.168.10.190:8090/GKPT/ITMS3.0/blob/master/deploy.png?ref=master)](http://192.168.10.190:8090/GKPT/ITMS3.0/blob/master/deploy.png?ref=master)
  <br>如图所示，系统部署需要数据服务器、业务系统服务器、分析服务器、状态服务器。
* 数据服务器
    * 部署ORACLE数据库，为程序提供数据库服务
* 业务系统服务器
    * 集群方式部署《管控平台3.0业务系统》，集群采用nginx负责均衡方式，随机访问应用程序
    * 部署Kettle ETL调度程序，ETL一般晚上开始调度，跟正常的程序错峰运行，相互不影响性能
* 分析服务器
    * 部署后台分析程序，包含：数据入库、设备状态分析、道路状态等程序。
    * 部署REDIS服务器，后台分析程序需要大量的跟REDIS通信，为了性能考虑放在同一台主机上
* 状态服务器
    * 部署状态服务程序，状态服务主要解决用户登录、过期、权限验证、双工通信等需要知道用户状态，而REST无状态服。
    * 部署rabbitMq服务程序，由于状态服务器无需消耗太多的性能，因此合理利用服务器资源
    
## 系统模块

  管控平台3.0包含以下模块：
* 业务模块
    * 业务模块主要是管控平台3.0系统功能，如：车辆察控、路况监控、设备管理等
* 状态服务系统
    * 用户状态管理：用户登录、权限管理、用户状过期、用户注销等
    * 双工通信模块，保持用户通道信息，后台接收到消息及时推送到客户端
 * 业务系统通用模块
    * 上传下载模块
    * 事务控制模块
    * 日志处理模块
    * 离线下载模块
 * 设备申报审批独立系统
     * 目标是管理全省的设备申报、备案信息，对全省设备进行监管
 * 数据交换中心独立系统
     * 解决问题1： 屏蔽系统间各种接口（FTP、WEBSERVICE、SOAP、REST）
     * 解决问题2： 接口代码管理，提供统一的注册库，进出程序规范化
     * 解决问题3： 降低开发人员接口开发的难度，提高效率 
 * 后台独立程序
     * MQ消息入库
     * 状态分析服务
     * 5分钟流量统计
     * 预警分析程序
     * 道路状态分析  
     * 布控比对服务
 * 报表独立系统
     * Kettle ETL调度程序
     * BIREPORT展示技术
     
# 特性清单（一、二级功能结构）
1. 车辆查控
    * 过车监控
    * 车辆查询
    * 车辆布控
2. 违法管理
    * 违法查询
    * 违法上传
    * 区间测速网
3. 设备运维
    * 设备监控
    * 备案管理
    * 检定管理
    * 申报审批
    * 维护管理
    * 服务监控管理
4. 视频监控
    * 视频监控
    * 视频回放
    * 视频巡航
    * 巡航方案
    * 巡航日志
5. 电子地图
    * 设备分布
    * 交通态势
    * 轨迹查询
6. 信息发布
    * 信息发布
7. 统计分析
    * 设备考核
    * 交通流量统计
    * 大数据分析
8. 系统管理
    * 机构用户
    * 角色权限
    * 系统代码
    * 操作日志
    * 路网信息
    * 交通基础信息
    * 文件下载
    * 节日设定
9. 路况监控
    * 路况监控
    * 交通事件管理
    * 阀值设置
    * 流量监控段
<br>[界面原型](http://192.168.10.240:1234)