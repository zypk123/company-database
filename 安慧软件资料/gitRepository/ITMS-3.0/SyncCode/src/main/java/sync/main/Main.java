package sync.main;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import antlr.StringUtils;
import sync.entity.Org;
import sync.entity.OrgSrc;
import sync.entity.Police;
import sync.entity.PoliceSrc;
import sync.entity.User;
import sync.util.HibernateUtilDist;
import sync.util.HibernateUtilSrc;
import sync.util.MD5Util;

public class Main {
	
	public static void main(String[] args){
		Session sessionSrc = HibernateUtilSrc.getSessionFactory().openSession();
		Session sessionDist = HibernateUtilDist.getSessionFactory().openSession();
		Transaction trans = sessionDist.beginTransaction();
		try {
			System.out.println("开始同步机构信息。。。。。。。。。。。。");
			//同步机构信息
			syncOrgInfo(sessionSrc,sessionDist,trans);
			System.out.println("开始同步用户信息。。。。。。。。。。。。");
			//同步警员、用户信息
			syncUserInfo(sessionSrc,sessionDist,trans);
			trans.commit();
			sessionSrc.close();
			sessionDist.close();
			System.out.println("同步成功。。。。。。。。");
			System.exit(0);
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
			System.out.println("同步出错。。。。。。。。");
			System.exit(1);
		} finally {
			System.out.println("同步程序运行结束。。。。。。。。");
		}
	}

	/**
	 * 同步警员信息
	 * @param trans 
	 * @param sessionDist 
	 * @param sessionSrc 
	 * @throws NoSuchAlgorithmException 
	 */
	private static void syncUserInfo(Session sessionSrc, Session sessionDist, Transaction trans) throws NoSuchAlgorithmException {
		//查找所有警员信息
		String hqlSrc = "from PoliceSrc where jlzt = '1'";
		String hqlDist = "from Police";
		String userHqlDist = "from User";
		Map<String,PoliceSrc> policeSrcMap = (Map<String, PoliceSrc>) sessionSrc.createQuery(hqlSrc).list()
				.stream().collect(Collectors.toMap(PoliceSrc::getJybh,(p) -> p));
		Map<String,Police> policeExistMap = (Map<String, Police>) sessionDist.createQuery(hqlDist).list()
				.stream().collect(Collectors.toMap(Police::getPoliceCode, (p) -> p));
		Map<String,User> userExistMap = (Map<String,User>)sessionDist.createQuery(userHqlDist).list()
				.stream().collect(Collectors.toMap(User::getPoliceId, (p) -> p));
		for(Entry<String,PoliceSrc> entry : policeSrcMap.entrySet()){
			if(policeExistMap.containsKey(entry.getKey())){
				//警员已经存在
				PoliceSrc policeSrc = entry.getValue();
				boolean updateFlag = false;
				Police policeDist = policeExistMap.get(entry.getKey());
				Org org = getOrgInfoByIdOrCode(policeSrc.getBmdm(),sessionDist);
				User userDist = userExistMap.get(policeDist.getPoliceId());
				if(!policeSrc.getCsrq().equals(policeDist.getBirthDate())){
					policeDist.setBirthDate(policeSrc.getCsrq());
					updateFlag = true;
				}
				if(!policeSrc.getSfzmhm().equals(policeDist.getPersonId())){
					policeDist.setPersonId(policeSrc.getSfzmhm());
					updateFlag = true;
				}
				if(!policeSrc.getXm().equals(policeDist.getPoliceName())){
					policeDist.setPoliceName(policeSrc.getXm());
					updateFlag = true;
				}
				if(!policeDist.getOrgId().equals(org.getOrgId())){
					policeDist.setOrgId(org.getOrgId());
					userDist.setOrgId(org.getOrgId());
					updateFlag = true;				
				}
				if(!policeDist.getPoliceName().equals(userDist.getName())){
					userDist.setName(policeDist.getPoliceName());
				}
				//更新警员信息
				if(updateFlag){
					System.out.println("更新警员：" + policeDist.getPoliceName()+"(" + policeDist.getPoliceCode() +")");
					sessionDist.update(policeDist);
					sessionDist.update(userDist);
					updateFlag = true;
				}
			}else{
				//创建新的警员用户
				PoliceSrc policeSrc = entry.getValue();
				Police policeDist = new Police();
				policeDist.setPoliceId(policeSrc.getJybh());
				policeDist.setPoliceCode(policeSrc.getJybh());
				Org org = getOrgInfoByIdOrCode(policeSrc.getBmdm(),sessionDist);
				policeDist.setOrgId(org.getOrgId());
				policeDist.setPoliceType("1");
				policeDist.setPoliticsStatus("1");
				policeDist.setpEnableFlag("1");
				policeDist.setpRecordStatus("1");
				policeDist.setPoliceName(policeSrc.getXm());
				policeDist.setPersonId(policeSrc.getSfzmhm());
				policeDist.setBirthDate(policeSrc.getCsrq());
				String sex = policeSrc.getXb();
				//管控平台编码为0,1   6合1为1,2，做转换
				policeDist.setSex((Integer.parseInt(sex) - 1) + "");
				policeDist.setCreateBy("6合1同步");
				policeDist.setUpdateBy("6合1同步");
				policeDist.setCreateTime(new Date());
				policeDist.setUpdateTime(new Date());
				
				sessionDist.save(policeDist);
				//加入队列
				policeExistMap.put(policeDist.getPoliceCode(), policeDist);
				//创建用户
				User userDist = new User();
				userDist.setUserId(policeSrc.getJybh());
				userDist.setLoginName(policeSrc.getJybh());
				userDist.setOrgId(policeDist.getOrgId());
				userDist.setLoginPassword(MD5Util.md5("123456"));
				userDist.setPoliceId(policeSrc.getJybh());
				userDist.setIsOnline("2");
				userDist.setLatestLoginTime(new Date());
				userDist.setTotalLoginCounts(0);
				userDist.setName(policeSrc.getXm());
				sessionDist.save(userDist);
				userExistMap.put(userDist.getPoliceId(), userDist);
				System.out.println("新增警员：" + policeDist.getPoliceName()+"(" + policeDist.getPoliceCode() +")");
			}
		}
	}

	/**
	 * 根据机构编码查找机构信息
	 * @param id
	 * @return
	 */
	private static Org getOrgInfoByIdOrCode(String id,Session sessionDist) {
		String hql = "from Org where orgId = :bmdm or orgCode = :bmdm";
		Query query = sessionDist.createQuery(hql);
		query.setString("bmdm", id);
		List<Org> orgs = query.list();
		Org org = null;
		if(orgs.size() > 0){
			org = orgs.get(0);
			//机构所属机构上提到大队
			if(org.getOrgPrivilegeCode() == null || "".equals(org.getOrgPrivilegeCode())){
				return getOrgInfoByIdOrCode(org.getParentOrgId(),sessionDist);
			}
		}
		return org;
	}

	/**
	 * 同步机构信息
	 * @param trans 
	 * @param sessionDist 
	 * @param sessionSrc 
	 */
	private static void syncOrgInfo(Session sessionSrc, Session sessionDist, Transaction trans) {
		//查找所有机构
		String hqlSrc =  "from OrgSrc where jlzt = '1' order by bmjb,glbm";
		String hqlDist =  "from Org";
		//获取元数据和目标已存在数据，转化为map
		List<OrgSrc> list = sessionSrc.createQuery(hqlSrc).list();
		Map<String,OrgSrc> orgSrcMap = listToLinkHashMap(list);
		Map<String,Org> orgExistMap = (Map<String, Org>) sessionDist.createQuery(hqlDist).list()
				.stream().collect(Collectors.toMap(Org::getOrgCode, (p) -> p));
		//同步机构信息
		for(Entry<String,OrgSrc> entry : orgSrcMap.entrySet()){
			if(orgExistMap.containsKey(entry.getKey())){
				OrgSrc orgSrc = entry.getValue();
				Org orgDist = orgExistMap.get(entry.getKey());
				boolean updateFlag = false;
				//机构已经存在,判断机构信息是否有更新
				if(orgSrc.getBmmc() != null && !orgSrc.getBmmc().equals(orgDist.getOrgName())){
					orgDist.setOrgName(orgSrc.getBmmc());
					updateFlag = true;
				}
				if(orgSrc.getFzr() != null && !orgSrc.getFzr().equals(orgDist.getOrgHeaderName())){
					orgDist.setOrgHeaderName(orgSrc.getFzr());
					updateFlag = true;
				}
				if(orgSrc.getLxdh() != null && !orgSrc.getLxdh().equals(orgDist.getOrgPhoneNbr())){
					orgDist.setOrgPhoneNbr(orgSrc.getLxdh());
					updateFlag = true;
				}
				if(orgSrc.getLxdz() != null && !orgSrc.getLxdz().equals(orgDist.getAddressDesc())){
					orgDist.setAddressDesc(orgSrc.getLxdz());
					updateFlag = true;
				}
				int bmjb = Integer.parseInt(orgSrc.getBmjb());
				String newOrgLevel = ""; 
				if(bmjb == 6){
					newOrgLevel = "5";
				}else if(bmjb == 5){
					newOrgLevel = "4";
				}else if(bmjb == 4){
					newOrgLevel = "3";
				}else if(bmjb == 3){
					newOrgLevel = "2";
				}else if(bmjb <= 2){
					newOrgLevel = "1";
				}
				if(orgDist.getOrgLevel().equals(newOrgLevel)){
					orgDist.setOrgLevel(newOrgLevel);
					updateFlag = true;
				}
				sessionDist.update(orgDist);
			}else{
				OrgSrc orgSrc = entry.getValue();
				//机构不存在 ，添加新机构
				Org newOrg = new Org();
				if(orgSrc.getLxdz() == null || "".equals(orgSrc.getLxdz())){
					newOrg.setAddressDesc("无驻地信息");
				}else{
					newOrg.setAddressDesc(orgSrc.getLxdz());
				}
				newOrg.setCreateBy("6合1同步");
				newOrg.setUpdateBy("6合1同步");
				newOrg.setCreateTime(new Date());
				newOrg.setUpdateTime(new Date());
				newOrg.setEnforcementFlag("1");
				newOrg.setOrgCode(orgSrc.getGlbm());
				newOrg.setOrgHeaderName(orgSrc.getFzr());
				newOrg.setOrgId(orgSrc.getGlbm());
				int bmjb = Integer.parseInt(orgSrc.getBmjb());
				if(bmjb == 6){
					newOrg.setOrgLevel("5");
				}else if(bmjb == 5){
					newOrg.setOrgLevel("4");
				}else if(bmjb == 4){
					newOrg.setOrgLevel("3");
				}else if(bmjb == 3){
					newOrg.setOrgLevel("2");
				}else if(bmjb <= 2){
					newOrg.setOrgLevel("1");
				}
				newOrg.setOrgName(orgSrc.getBmmc());
				newOrg.setOrgPhoneNbr(orgSrc.getLxdh());
				newOrg.setParentOrgId(orgExistMap.get(orgSrc.getSjbm()).getOrgId());
				newOrg.setParentInstructOrgId(orgExistMap.get(orgSrc.getSjbm()).getOrgId());
				newOrg.setOrgType("1");
				newOrg.setIsDepartment("0");
				newOrg.setIsHighwayOrg("0");
				newOrg.setDistrictCode("53");
				String orgPrivlegeCode = "";
				if((newOrg.getOrgName().endsWith("总队") ||
					newOrg.getOrgName().endsWith("支队") ||
					newOrg.getOrgName().endsWith("大队")) && newOrg.getOrgCode().endsWith("0000")){
					orgPrivlegeCode = createPrivilegeCode(newOrg.getParentOrgId(),sessionDist);
				}
				newOrg.setOrgPrivilegeCode(orgPrivlegeCode);
				sessionDist.save(newOrg);
				System.out.println("新增机构：" + newOrg.getOrgName()+"(" + newOrg.getOrgCode() +","+newOrg.getOrgPrivilegeCode()+")" );
				//将添加的机构加入到队列中
				orgExistMap.put(newOrg.getOrgCode(), newOrg);
			}
		}
	}
	
	private static Map listToLinkHashMap(List<OrgSrc> list){
		Map<String,OrgSrc> returnMap = new LinkedHashMap<String,OrgSrc>();
		for(OrgSrc org : list){
			returnMap.put(org.getGlbm(), org);
		}
		return returnMap;
	}
	
	/**
	 * 获得机构权限编码
	 * @param parentOrgId
	 * @return
	 */
	private static String createPrivilegeCode(String parentOrgId,Session sessionDist) {
		String hql = "select max(orgPrivilegeCode) from Org where parentOrgId=:parentOrgId and orgPrivilegeCode != '5336'";
		Query query = sessionDist.createQuery(hql);
		query.setParameter("parentOrgId", parentOrgId);
		String code = null;
		String maxCode = (String) query.list().get(0);
		if(maxCode == null || "".equals(maxCode)){
			Org parent = (Org) sessionDist.get(Org.class, parentOrgId);
			code = parent.getOrgPrivilegeCode() + "01";
			if(code.length() > 8){
				code = "";
			}
		}else{
			code =  (Integer.parseInt(maxCode) + 1) + "";
		}
		return code;
	}
	
	/**
	 * 根据机构编码获得机构ID
	 */
	private static String getOrgIdByCode(String code,Session session){
		String hql = "from Org where orgCode = :orgCode";
		Query query = session.createQuery(hql);
		query.setParameter("orgCode", code);
		List<Org> result = query.list();
		if(result != null && result.size() > 0){
			return result.get(0).getOrgId();
		}else{
			return null;
		}
	}
}
