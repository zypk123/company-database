/**
 * 基于easyui的验证规则初始化,说明如下，可以混合使用,以逗号分隔validType:['email','url']
 * required 	非空验证：				data-options="required:true"
 * length 		长度验证：				data-options="validType:'length[0,10]'"
 * maxLength    最大长度：				data-options="validType:'maxLength[10]'"
 * fixedLength	固定长度验证：			data-options="validType:'fixedLength[6]'"
 * videoNbrLength	视频编号长度验证：	data-options="validType:'videoNbrLength[18,20]'"
 * email 		邮箱地址：				data-options="validType:'email'"
 * url 			http连接地址：			data-options="validType:'url'"
 * personId 	身份证号：				data-options="validType:'personId'"
 * phone		座机或手机：			data-options="validType:'phone'"
 * telephone 	座机号码：				data-options="validType:'telephone'"
 * cellphone 	手机号码：				data-options="validType:'cellphone'"
 * notBlank 	只能有中文字母数组下划线组成：data-options="validType:'notBlank'"
 * ip 			ip地址：				data-options="validType:'ip'"
 * date			日期：				data-options="validType:'date'"
 * maxDate		参数为对应结束时间输入框id：	data-options="validType:'maxDate[\'id\']'"
 * minDate		参数为对应开始时间输入框id：	data-options="validType:'minDate[\'id\']'"
 * maxValue		参数为对应结束时间输入框id：	data-options="validType:'maxValue[\'id\']'"
 * minValue		参数为对应开始时间输入框id：	data-options="validType:'minValue[\'id\']'"
 * remote 		通过后台地址服务验证：		data-options="validType:'remote[\''+$rootScope.$restRoot+'/xx/xx\',\'paramName\']'"
 * passwordValidate 密码验证			data-options="validType:'passwordValidate[\'id\']'" 
 * number       只允许数字数字验证：		data-options="validType:'number'"
 * numOrLetter  只允许数字或字母验证		data-options="validType:'numOrLetter'"
 * blank		空格字符串验证			data-options="validType:'blank'"
 * numOrDecimal 小数或整数				data-options="validType:'numOrDecimal'"
 */
$.extend($.fn.validatebox.defaults.rules, {
	//长度
	length : {
		validator : function(value,param){
			maxLengthLimit($(this),value,param[1]);
			if(value.length < param[0]){
				return false;
			}
			return true;
		},
		message : "内容 长度必须介于{0}于{1}之间"
	},
	//最大长度
	maxLength : {
		validator : function(value,param){
			maxLengthLimit($(this),value,param[0]);
			return true;
		},
		message : "内容最大长度：{1}"
	},
	/**
	 * 数字验证
	 */
	number:{
		validator : function(value,param){
			var reg = /^[0-9]*$/;
			return reg.test(value);
		},
		message : "必须填写数字"
	},
	blank:{
		validator : function(value,param){
			var reg = /^\S+$/gi;
			return reg.test(value);
		},
		message : "不允许存在空格"
	},
	passwordValidate : {
		validator : function(value,param){
			var password = $(this).parent().parent().parent().parent().find("#" + param[0]).textbox("getValue");
			if(password == "" || password == value){
				return true;
			}
			return false;
		},
		message : "两次输入的密码不一样"
	},
	fixedLength : {
		validator : function(value,param){
			maxLengthLimit($(this),value,param[0]);
			if(value.length < param[0]){
				return false;
			}
			return true;
		},
		message : "内容长度必须为{0}"
	},
	/**
	 * 视频编号验证
	 */
	videoNbrLength : {
		validator : function(value,param){
			maxLengthLimit($(this),value,param[1]);
			if(value.length != param[0] && value.length != param[1] || value.length > param[1]){
				return false;
			}
			return true;
		},
		message : "内容长度必须为{0}或{1}"
	},
	/**
	 * 身份证号
	 */
	personId : {
		validator : function(value, param) {
			var reg = /^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/;
			return reg.test(value);
		},
		message : '身份证格式不正确(15~18为数字或X结尾)'
	},
	/**
	 * 座机号码
	 */
	telephone : {
		validator : function(value, param) {
			var reg = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
			return reg.test(value);
		},
		message : '座机号码格式不正确(区号-电话-分机号)'
	},
	/**
	 * 手机号码
	 */
	cellphone : {
		validator : function(value, param) {
			var reg = /^(1[3-8][0-9])\d{8}$/i;
			return reg.test(value);
		},
		message : "手机号码格式不正确"
	},
	/**
	 * 电话 
	 */
	phone : {
		validator : function(value, param) {
			var reg1 = /^(1[3-8][0-9])\d{8}$/i;//手机
			var reg2 = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;//座机
			if(reg1.test(value) || reg2.test(value)){
				return true;
			}else{
				return false;
			}
		},
		message : "电话号码格式不正确"
	},
	/**
	 * 不允许有空格和特殊字符
	 */
	notBlank : {
		validator : function(value, param) {
			var reg = /^([\u4E00-\u9FA5]|\w)*$/;
			return reg.test(value);
		},
		message : "不允许有空格和特殊字符"
	},
	/**
	 * 只能有数字或字母
	 */
	numOrLetter : {
		validator : function(value, param) {
			var reg = /^[0-9a-zA-Z]*$/g;
			return reg.test(value);
		},
		message : "只允许有数字或字母"
	},
	/**
	 * 小数或整数
	 */
	numOrDecimal  : {
		validator : function(value, param) {
			var reg = /^[0-9]+([.]{1}[0-9]+){0,1}$/;
			return reg.test(value);
		},
		message : "只允许小数或整数"
	},
	
	/**
	 * ip地址
	 */
	ip : {
		validator : function(value, param) {
			var reg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
			return reg.test(value);
		},
		message : "IP地址输入格式不正确"
	},
	/**
	 * 日期 
	 */
	date : {
		validator: function (mytime, param) {         
    		//标准时间格式  
    		var regStandard = /^[1,2]\d{3}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}:\d{1,2}$/;    //匹配标准日期格式  2014-1-20 12:10:00  
    		//日期快速输入格式    
    		var regC =/^[1,2]\d{3}-\d{1,2}-\d{1,2}$/;    //匹配日期  2014-1-20  
    		var x = "";  
      
    		if(!regStandard.test(mytime) ){  
        		if(regC.test(mytime)){  
            		x = mytime + " 00:00:00";  
            		if(!checkDate("date",x)) {  
                		$.fn.validatebox.defaults.rules.date.message ="日期格式错误";  
                		return false;  
            		}         
        		}else{  
            		$.fn.validatebox.defaults.rules.date.message ="日期格式错误";  
            		return false;  
        		}  
    		}else{  
        		if(!(checkDate("date",mytime) && checkDate("time",mytime))) {  
            		$.fn.validatebox.defaults.rules.date.message ="日期格式错误";  
            		return false;  
        		}  
    		}  
            return true;  
        },
		message : ""
	},
    /**
     * 日期
     */
    dateInFormater : {
        validator: function (mytime, param) {
            var regStr = param[0];
            var Week = ['日','一','二','三','四','五','六'];

            regStr=regStr.replace(/yyyy|YYYY/,'[1,2]\\d{3}')
                .replace(/MM/,'\\d{1,2}')
                .replace(/dd|DD/,'\\d{1,2}')
                .replace(/hh|HH/,'\\d{1,2}')
                .replace(/mm/,'\\d{1,2}')
                .replace(/ss|SS/,'\\d{1,2}');
            var regStandard=eval('/'+regStr+'/');

            if(regStandard.test(mytime) ){
                mytime= param[1]+mytime+param[2];
                if(!(checkDate("date",mytime) && checkDate("time",mytime))) {
                    $.fn.validatebox.defaults.rules.date.message ="日期格式错误";
                    return false;
                }
            }else{
                $.fn.validatebox.defaults.rules.date.message ="日期格式错误";
                return false;
            }

            return true;
        },
        message : ""
    },
    /**
     * 最大日期
     */
    maxEndDate : {
        validator : function(value){
            var maxDate =(new Date()).format('yyyy-MM-dd HH:mm:ss');
            if(!maxDate || maxDate == ""){
                return true;
            }
            if(value > maxDate){
                return false;
            }
            return true;
        },
        message : "结束时间不能大于当前时间"
    },
	/**
	 * 最大日期 
	 */
	maxDate : {
		validator : function(value,param){
			var maxDate = $(this).parent().parent().parent().find("#" + param[0]).textbox("getValue");
			if(!maxDate || maxDate == ""){
				return true;
			}
			if(value > maxDate){
				return false;
			}
			return true;
		},
		message : "开始时间不能大于结束时间"
	},
	/**
	 * 最小日期 
	 */
	minDate : {
		validator : function(value,param){
			var minDate = $(this).parent().parent().parent().find("#" + param[0]).textbox("getValue");
			if(!minDate || minDate == ""){
				return true;
			}
			if(value < minDate){
				return false;
			}
			return true;
		},
		message : "结束时间不能小于开始时间"
	},
	/**
	 * 最大值 
	 */
	maxValue : {
		validator : function(value,param){
			var maxValue = parseFloat($(this).parent().parent().parent().find("#" + param[0]).textbox("getValue"));
			if(!maxValue || maxValue == ""){
				return true;
			}
			if(value > maxValue){
				return false;
			}
			return true;
		},
		message : "最小值不能大于最大值"
	},
	/**
	 * 最小值 
	 */
	minValue : {
		validator : function(value,param){
			var minValue = parseFloat($(this).parent().parent().parent().find("#" + param[0]).textbox("getValue"));
			if(!minValue || minValue == ""){
				return true;
			}
			if(value < minValue){
				return false;
			}
			return true;
		},
		message : "最大值不能小于最小值"
	}
});

/**
 * 检查日期 
 * @param {Object} 日期类型
 * @param {Object} datetime 时间
 * @param {Object} split 分隔符
 */
function checkDate(type,datetime,split){  
    var date = datetime.split(" ")[0];  
    var time = datetime.split(" ")[1];  
    //alert(date + '\n' + time)  
    switch (type){  
       case "time"://检查时分秒的有效性  
           var tempArr = time.split(":");  
           if(parseInt(tempArr[0]) > 23){  
               return false;  
           }  
           if(parseInt(tempArr[1]) > 60 || parseInt(tempArr[2]) > 60){  
               return false;  
           }             
            break;  
        case "date"://检查日期的有效性  
            var tempArr = date.split("-");    
            if(parseInt(tempArr[1]) == 0 || parseInt(tempArr[1]) > 12){//月份  
                return false;  
            }  
            var lastday=new Date(parseInt(tempArr[0]),parseInt(tempArr[1]),0);//获取当月的最后一天日期           
            if(parseInt(tempArr[2])== 0 || parseInt(tempArr[2]) > lastday.getDate()){//当月的日  
                return false;  
            }             
            var myDate = new Date(parseInt(tempArr[0]),parseInt(tempArr[1])-1,parseInt(tempArr[2]));                  
            if(myDate=="Invalid Date") {          
                return false;  
            }  
           break;  
    }  
      
    return true;  
}  

/**
 * 最大长度限制 
 * @param {Object} el
 * @param {Object} length
 */
function maxLengthLimit(el,value,length){
	if(value.length <= length){
		return;
	}
	el.val(value.substring(0,length));
}
