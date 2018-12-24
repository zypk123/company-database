### Maven集成Birt 4.4.2版本无任何问题
但是viewservlets只有4.4.1版本，需特殊指定

### Maven集成Birt 4.5.0版本问题处理方法
原因：官方Maven中央仓库中Birt 4.5.0 版本有问题。org.eclipse.equinox.common和org.eclipse.core.runtime版本过于老旧且jar包中签名文件不一致、org.mozilla.javascript包含两个版本，导致版本冲突。
解决办法：
1、手工上传org.eclipse.equinox.common-3.7.0.v20150402-1709.jar、org.eclipse.osgi-3.10.100.v20150529-1857.jar两个到本地Maven私服。
需使用以下参数上传：
		<dependency>
			<groupId>org.eclipse.birt.runtime</groupId>
			<artifactId>org.eclipse.equinox.common</artifactId>
			<version>3.7.0.v20150402-1709</version>
		</dependency>
		<dependency>
			<groupId>org.eclipse.birt.runtime</groupId>
			<artifactId>org.eclipse.core.runtime</artifactId>
			<version>3.11.0.v20150405-1723</version>
		</dependency>
2、使用以下pom.xml

		<dependency>
			<groupId>org.eclipse.birt.runtime</groupId>
			<artifactId>viewservlets</artifactId>
			<version>${birt_version}</version>
		</dependency>
		<dependency>
			<groupId>javax.xml.rpc</groupId>
			<artifactId>javax.xml.rpc-api</artifactId>
			<version>1.1.1</version>
		</dependency>
		<dependency>
			<groupId>org.eclipse.birt.runtime</groupId>
			<artifactId>org.eclipse.core.runtime</artifactId>
			<version>3.11.0.v20150405-1723</version>
		</dependency>
		
		<dependency>
			<groupId>org.eclipse.birt.runtime</groupId>
			<artifactId>org.eclipse.birt.runtime</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.eclipse.birt.runtime</groupId>
					<artifactId>org.eclipse.equinox.common</artifactId>
				</exclusion>
				<exclusion>
					<artifactId>org.mozilla.javascript</artifactId>
					<groupId>org.eclipse.birt.runtime.3_7_1</groupId>
				</exclusion>
			</exclusions> 
		</dependency>
		<dependency>
			<groupId>org.eclipse.birt.runtime</groupId>
			<artifactId>org.eclipse.equinox.common</artifactId>
			<version>3.7.0.v20150402-1709</version>
		</dependency>
		
### Birt 报表查看器中文化
1、手工上传org.eclipse.birt.report.viewer.nl_zh_4.5.0.v20150804081228.jar到本地Maven私服。
需使用以下参数上传：
		<dependency>
			<groupId>org.eclipse.birt.runtime</groupId>
			<artifactId>org.eclipse.birt.report.viewer.nl_zh</artifactId>
			<version>4.5.0</version>
		</dependency>
2、使用以下pom.xml
		<dependency>
			<groupId>org.eclipse.birt.runtime</groupId>
			<artifactId>org.eclipse.birt.report.viewer.nl_zh</artifactId>
			<version>${birt_version}</version>
		</dependency>