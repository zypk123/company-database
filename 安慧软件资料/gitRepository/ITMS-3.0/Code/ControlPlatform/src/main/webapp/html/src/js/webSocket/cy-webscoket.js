/**
 * webSocket连接对象
 */
var CyWebSocket = function(_options) {
    var options = {
        addr : "192.168.10.8:8080/AuthenticationCenter/service/websckServer"
    };
    $.extend(options, _options);
    this.options = options;
    this.init();
};

CyWebSocket.prototype = {
	operateType : {
		init : "init",//初始化
		remove : "remove",//删除订阅
		addParam : "addParam",//订阅更新-增加参数
		deleteParam : "deleteParam"//订阅更新-删除参数
	},
    options : null, //参数配置
    isConnected : false, //连接标识
    isConnecting : false, //正在重连标识
    isSending : false,//发送数据标识
    ws : null, //websocket对象
    /**
     * 订阅列表
     */
    subsribeList : {},
    /**
     * 操作缓冲队列
     */
    operateCacheQueue : [],
    /**
     * 操作队列
     */
    operateQuere : [],
    /**
     * 消息执行队列
     */
    messageExcuteQueue : [],
    /**
     * 是否正在执行 
     */
    isExcuting : false,
    
    /**
     *  初始化
     */
    init : function() {
        //初始化订阅列表
        for (var index in SubsribeType) {
            //为每一种订阅创建一个回调函数集合列表
            this.subsribeList[SubsribeType[index].messageKey] = [];
        }
    },
    /**
     * 连接websocket服务
     */
    connect : function() {
    	//debugger;
        var that = this;
        this.isConnected = false;
        this.isConnecting = true;
        this.ws = new WebSocket("ws://" + that.options.addr);
        this.ws.onopen = function() {
            that.onopen();
        };
        this.ws.onmessage = function(evt) {
            that.onmessage(evt);
        }; 
        this.ws.onclose = function(evt) {
            that.onclose(evt);
        };
        this.ws.onerror = function(evt) {
            that.onerror(evt);
        };
    },
    /**
     * 关闭连接
     */
    close : function() {
        this.ws.close();
    },
    /**
     * 发送消息
     */
    sendMessage : function() {
        if (this.isConnected) {
        	this.isSending = true;
        	this.operateQuere = this.operateQuere.concat(this.operateCacheQueue);
        	this.operateCacheQueue = [];
            //当前已连接，按照队列中的顺序，发送消息
            for (var index = 0; index < this.operateQuere.length; index++) {
                console.log("发送消息 :" + this.operateQuere[index]);
                this.ws.send(this.operateQuere[index]);
            }
            this.operateQuere = [];
            this.isSending = false;
            if(this.operateCacheQueue.length > 0){
            	this.sendMessage();
            }
        } else {
            if(!this.isConnecting){
                //重连
                this.connect();
            }
        }
    },
    
    /**
     * 连接后执行操作
     */
    onopen : function() {
        //设置连接成功标识
        this.isConnected = true;
        //设置连接状态标识
        this.isConnecting = false;
        //连接成功后，发送队列中的消息
        this.sendMessage();
    },
    /**
     * 关闭连接操作
     */
    onclose : function(evt) {
        this.isConnected = false;
        console.log(evt);
    },
    /**
     * 连接错误执行操作
     */
    onerror : function(evt) {
        var that = this;
        //连接异常，10秒后重连
        setTimeout(function() {
            that.connect();
        }, 10000);
    },
    /**
     * 收到消息后执行操作
     */
    onmessage : function(evt) {
        //获得消息体
        var message = $.parseJSON(evt.data);
        console.log("接收到消息：" + evt.data);
        var key = message.wbskey;
        var data = message[key];
        var list = this.subsribeList[key];
        if (!list) {
        	//订阅队列不存在，直接返回
            return;
        }
        //执行所有的回调函数
        for(var index in list){
        	if ( typeof list[index] == "function") {
                //按顺序执行回调函数,加入消息操作队列
                this.messageExcuteQueue.push({
                	callback : list[index],
                	data : data
                });
                //触发队列操作
                this.messageExcute();
            }	 
        }
    },
    /**
     * 执行消息操作队列中的内容
     */
    messageExcute : function(){
    	if(!this.isExcuting){
    		this.isExcuting = true;
    		for(var index=0; index<this.messageExcuteQueue.length; index++){
    			try{
    				var item = this.messageExcuteQueue[index];
    				item.callback(item.data);
    			}catch(e){
    				console.log(e);
    			}
    			this.messageExcuteQueue.shift(index--);
    		}
    		this.isExcuting = false;
    	}
    },
    /**
     * 增加订阅信息
     * @param {Object} subsribe 订阅对象
     */
    addSubsribe : function(subsribe) {
        var operate = this.operateType.init;
        //增加订阅信息到订阅列表
        var list = this.subsribeList[subsribe.options.type.messageKey];
        list.push(subsribe.getCallback());
        //增加到操作序列中
        this.addToOperateQuere(this.getMessage(subsribe, operate));
    },
    /**
     * 删除订阅信息
     * @param {Object} subsribe 订阅对象
     */
    removeSubscribe : function(subsribe) {
        var operate = this.operateType.remove;
        //清空回调函数集合
        this.subsribeList[subsribe.options.type.messageKey] = [];
        this.addToOperateQuere(this.getMessage(subsribe, operate));
    },
    /**
     * 增加订阅参数
     */
    addParamToSubscribe : function(subsribe,param){
    	var operate = this.operateType.addParam;
    	this.addToOperateQuere(this.getMessage(subsribe, operate, param));
    },
    /**
     * 删除订阅参数 
     */
    deleteParamToSubscribe : function(subsribe,param){
    	var operate = this.operateType.deleteParam;
    	this.addToOperateQuere(this.getMessage(subsribe, operate, param));
    },
    /**
     * 增加了消息到队列中
     * @param {Object} message 消息体
     */
    addToOperateQuere : function(message) {
    	this.operateCacheQueue.push(message);
        if(!this.isSending){
        	 this.sendMessage();
        }
     },
    /**
     * 获得发送消息
     * @param {Object} subsribe 订阅信息
     * @param {Object} operate 操作类型
     */
    getMessage : function(subsribe, operate) {
    	var parm=subsribe.options.param;
    	if(arguments.length>2)
    	{
    		parm=arguments[2];
    	}
        var meg = {
            wbskey : subsribe.options.type.messageKey,
            serviceMethod : operate,
            token : $rootScope.UserInfo.token,
            param : parm
        };
        return JSON.stringify(meg);
    },
    /**
     * 是否已订阅 
     */
    hasSubsribe : function(subsribeType){
    	if(this.subsribeList[subsribeType.messageKey].length > 0){
    		return true;
    	}else{
    		return false;
    	}
    }
};
