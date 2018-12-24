### 利用GitLab-CI完成自动构建
使用同步程序完成SVN仓库到Git仓库自动离线同步，每次git push操作均会触发构建工作。目前利用徐泳机器利用计划任务实现间隔10分钟自动同步一次。

### 构建完成之后自动上传到FTP服务器，调用~/syncftp.sh脚本，该脚本会自动上传ControlPlatform AuthenticationCenter两个Web工程WAR包到FTP中，并自动删除7天之前的文件
可通过FTP客户端查看是否完成是最新打包成果，格式：ControlPlatform_XXXXXXXX.war

### gitlab-ci 配置文件在git仓库根目录下.gitlab-ci.yml 文件中
Report项目必须在报表保存目录（report）下面执行“find . -name '*.rptdesign' | xargs sed -i 's/src\/main\/webapp\///g'”，以便完成不正确的目录修正

### gitlab-ci runner所在机器必须完成jdk、maven环境安装，并在settings.xml文件中设置deploy访问用户名和密码

### 服务程序构建
服务程序预备使用ICEGrid承载，需使用Maven打包成zip格式化
