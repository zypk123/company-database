/**
 * 订阅对象
 */
var Subsribe = function(_options) {
    var options = {
        socket : $rootScope.$webSocket, //webSocket连接对象
        id : new Date().getTime() + "" + parseInt(Math.random() * 1000), //生成随机ID：当前时间毫秒数+3位随机数
        type : "", //订阅类型，默认过车监控,从枚举js文件subsribeType.js中获得
        onMessage : function(message) {//获得数据后的回调函数
            
        },
        param : null//参数
    };
    $.extend(options, _options);
    this.options = options;
    this.init();
};

/**
 * 设置属性
 */
Subsribe.prototype = {
    //参数
    options : null,
    //初始化方法
    init : function() {
        //开启订阅
        this.options.socket.addSubsribe(this);
    },
    //删除订阅
    remove : function() {
        //删除订阅
        this.options.socket.removeSubscribe(this);
    },
    //增加参数
    addParam : function(param){
    	
    	this.options.socket.addParamToSubscribe(this,param);
    },
    //删除参数
    deleteParam : function(param){
    	this.options.socket.deleteParamToSubscribe(this,param);
    },
    //获得回调函数
    getCallback : function() {
        return this.options.onMessage;
    }
};
