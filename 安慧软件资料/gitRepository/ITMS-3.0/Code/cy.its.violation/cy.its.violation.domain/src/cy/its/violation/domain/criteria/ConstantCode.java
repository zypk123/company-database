package cy.its.violation.domain.criteria;

/**
 * @title:SysCodeDic.java
 * @Package:cy.its.violation.rest.dto
 * @Description:TODO
 * @author:jinhb@cychina.cn
 * @date: 2015年9月24日 下午4:20:21
 * @version v1.0
 */
public class ConstantCode {
	/**
	 * @title:SysCodeDic.java
	 * @Package:cy.its.violation.rest.dto
	 * @Description:违法类型 011
	 * @author:jinhb@cychina.cn
	 * @date: 2015年10月13日 下午8:36:16
	 * @version v1.0
	 */
	public static class ViolationType {

		/**
		 * 大车占道 0
		 */
		public static final String DA_CZD = "0";
		/**
		 * 超高速 1
		 */
		public static final String CHAO_GS = "1";
		/**
		 * 逆行 3
		 */
		public static final String NI_X = "3";
		/**
		 * 闯红灯 4
		 */
		public static final String CHUANG_HD = "4";
		/**
		 * 压黄线 5
		 */
		public static final String YA_HX = "5";
		/**
		 * 违章停车 6
		 */
		public static final String WEI_ZTC = "6";
		/**
		 * 区间超速 7
		 */
		public static final String QU_JCS = "7";
		/**
		 * 禁行 8
		 */
		public static final String JIN_X = "8";
		/**
		 * 其他 9
		 */
		public static final String QI_T = "9";
		/**
		 * 不按导向行驶 c
		 */
		public static final String BU_ADXXS = "c";
		/**
		 * 压线 d
		 */
		public static final String YA_X = "d";
		/**
		 * 违法占道 a
		 */
		public static final String WEI_FZD = "a";
		/**
		 * 遮挡号牌 b
		 */
		public static final String ZHE_DHP = "b";
		/**
		 * 未系安全带 e
		 */
		public static final String WEI_JAQD = "e";
	}

	/**
	 * @title:SysCodeDic.java
	 * @Package:cy.its.violation.rest.dto
	 * @Description:记录状态 301
	 * @author:jinhb@cychina.cn
	 * @date: 2015年9月24日 下午4:04:08
	 * @version v1.0
	 */
	public static class StatusFlag {

		/**
		 * 新记录 0
		 */
		public static final String NEW = "0";

		/**
		 * 已 初筛 1
		 */
		public static final String FILTERED = "1";
		/**
		 * 已录入 2
		 */
		public static final String CONFIRMED = "2";
		/**
		 * 待重录 3
		 */
		public static final String RECONFIRM = "3";
		/**
		 * 已废弃 9
		 */
		public static final String DISCARDED = "9";
	}

	/**
	 * @title:SysCodeDic.java
	 * @Package:cy.its.violation.rest.dto
	 * @Description:上传状态 305
	 * @author:jinhb@cychina.cn
	 * @date: 2015年10月13日 下午8:36:26
	 * @version v1.0
	 */
	public static class UploadFlag {
		/**
		 * 未上传 0
		 */
		public static final String NEW = "0";
		/**
		 * 待上传 1
		 */
		public static final String WAITING = "1";
		/**
		 * 已上传 2
		 */
		public static final String UPLOADED = "2";
		/**
		 * 上传失败 3
		 */
		public static final String FAILED = "3";
		/**
		 * 取消上传 4
		 */
		public static final String CANCEL = "4";
	}

	/**
	 * @title:SysCodeDic.java
	 * @Package:cy.its.violation.rest.dto
	 * @Description:废弃类别 306
	 * @author:jinhb@cychina.cn
	 * @date: 2015年9月24日 下午4:20:23
	 * @version v1.0
	 */
	public static class DiscardType {
		/**
		 * 单张 1
		 */
		public static final String SINGLE = "1";
		/**
		 * 自动 2
		 */
		public static final String AUTO = "2";
		/**
		 * 批量 3
		 */
		public static final String BATCH = "3";
	}

	/**
	 * @title:SysCodeDic.java
	 * @Package:cy.its.violation.rest.dto
	 * @Description:废弃原因 312
	 * @author:jinhb@cychina.cn
	 * @date: 2015年9月24日 下午4:23:08
	 * @version v1.0
	 */
	public static class DiscardReason {
		/**
		 * 异常数据 01
		 */
		public static final String ABNORMAL = "01";
		/**
		 * 重复记录 03
		 */
		public static final String DUPLICATE = "03";
		/**
		 * 无效图像 04
		 */
		public static final String BAD_IMAGE = "04";
		/**
		 * 号牌不全 05
		 */
		public static final String UNCOMPLETE_PLATE = "05";
		/**
		 * 无号牌 06
		 */
		public static final String MISSIONG_PLATE = "06";
		/**
		 * 超时 07
		 */
		public static final String OVER_TIME = "07";
		/**
		 * 其它 08
		 */
		public static final String OTHER = "08";
		/**
		 * 套牌车 21
		 */
		public static final String IMITAT_PLATE = "21";
		/**
		 * 假牌车 22
		 */
		public static final String FAKE_PALTE = "22";
		/**
		 * 白名单车 23
		 */
		public static final String WHITE_VEH = "23";
		/**
		 * 军警车 24
		 */
		public static final String ARM_VEH = "24";
		/**
		 * 农用车 25
		 */
		public static final String FARM_VEH = "25";

	}

	public static class BooleanFlag {
		/**
		 * 新记录 0
		 */
		public static final String NONE = "0";

		/**
		 * 变化 1
		 */
		public static final String TRUE = "1";
	}

}
