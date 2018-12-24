package cy.its.sysLog.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cy.its.platform.common.utils.SqlHelper;
import cy.its.sysLog.client.UserStatusMapper;
import cy.its.sysLog.domain.UserStatus;
import cy.its.sysLog.service.UserStatusLogI;

/**
* @Title: UserStatusLogImpl.java 
* @Package cy.its.sysLog.service.impl 
* @Description:更新用当月登录次数
* @author lil@cychina.cn
* @date 2016年3月11日 下午4:45:15 
* @version V1.0   
 */
@Service
public class UserStatusLogImpl implements UserStatusLogI{
	
	@Autowired
	private UserStatusMapper userStatusMapper;//注入数据库操作类
	

	@Override
	public int updateUserStatus(UserStatus record) {
		//首先获取用户登录次数，如果登录次数为0则直接新增
		String  info= userStatusMapper.selectById(record.getUserId());
		if(StringUtils.isEmpty(info)){
			//不存在记录新增一条记录
			record.setLoginCount((long) 1);
			record.setRemark("数据录入");
			record.setCurrentTime(new Date());
			return userStatusMapper.insert(record);
		}else{
			int count = Integer.valueOf(info.split(":")[0]).intValue();//次数
		    String ldate = info.split(":")[1];//时间
		    Date lastDate;
			try {
				lastDate = new SimpleDateFormat("yyyy-MM-dd").parse(ldate);
				if(lastDate.getMonth() == new Date().getMonth()){//当月累加
					record.setLoginCount((long) (count+1));
					record.setCurrentTime(new Date());
					return userStatusMapper.updateByUserId(record);
			    }else{
					//当月第一次登录
					record.setLoginCount((long) (1));
					record.setCurrentTime(new Date());
					return userStatusMapper.updateByUserId(record);
			    }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("记录登录次数，日期转换出现问题！");
				e.printStackTrace();
			}
		}
		return 0;
	}
	
}
