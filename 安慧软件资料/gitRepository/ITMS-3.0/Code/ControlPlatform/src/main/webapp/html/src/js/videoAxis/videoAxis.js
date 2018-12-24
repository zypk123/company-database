'use strict';
/**
 * 时间轴控件
 * @param {Object} 参数
 */
var VideoAxis = function(options) {
    //默认参数
    var _options = {
        dom:null,//容器DOM对象
        Items : [],//数据列表
        lineTextFormat:function(item){//紧贴中线上显示的文本

        },
        rightTextFormat:function(item){//右侧显示的文本

        },
        onSelect : function(){//选择时间，回调函数

        },
        onExpand : function(){//打开事件

        },
        clickEnabled:true//状态启用
    };
    //保存参数
    this.options = $.extend(_options,options);
    //初始化DOM
    this.init();
};

VideoAxis.prototype = {
    //参数
    options : null,
    //大类
    data : {},
    //异步调用
    modifyCssTimeout:null,
    //初始化方法
    init : function(){
        var that = this;
        var jDom = $(this.options.dom);
        jDom.html("");

        var root=$("<div style='position: relative'></div>");
        var ulsRoot=$("<div class='videoAxis-uls-root'></div>");
        root.append(ulsRoot);
        root.append("<div class='videoAxis-line static'> </div>")
        root.append("<div class='videoAxis-line mobile'> </div>")
        jDom.append(root);

        for(var index in this.options.Items){
            var level1Item = this.options.Items[index];
            var head = $("<div class='videoAxis-head' id='head-'"+level1Item.id+"></div>");
            head.text(level1Item.text);
            var ul = $("<ul class='videoAxis-item-ul' id='"+level1Item.id+"'></ul>");
            if(index == 0){
                head.addClass("current");
                ul.addClass("current");
                //调用用户自定义展开事件
                this.options.onExpand();
            }
            ulsRoot.append(head).append(ul);
            //保存数据
            var data = (this.data[level1Item.id] = {});
            data.headTarget = head;
            data.target = ul;
            head.click(level1Item.id,function(e){
                that.expand(e.data);
            });
            //填充二级节点
            this.setLevel2Items(ul,level1Item);
            //console.log(this.data[item]);
            //Object{headTarget={...},target={...},children={...}}
        }
        jDom.find(".mobile")[0].style.height=40+"px";
        var height = jDom.height() * 0.95;
        var maxHeight=(parseInt(this.options.Items.length)+1)*(40+10);//一级节点高度，和节点间的间隙
        //console.log(level1Index);
        var level2Length=this.options.Items[index].children.length;
        maxHeight +=65*level2Length;//60 height +5 margin top;
        height=height>maxHeight?height:maxHeight;
        console.log('height:'+height);
        jDom.find(".videoAxis-line.static")[0].style.height=height+"px";
        this.fitSize();
        this.disable();
    },
    //填充刻度
    setLevel2Items : function(ul,level1Item){
        var items = level1Item.children;
        var data = this.data[level1Item.id];
        data.children = {};
        ul.append("<li class='videoAxis-item-ul-top'></li>");//添加一个空的li

        for(var index in items){
            var item = items[index];
            var liHtml="<li class='videoAxis-item-li new' id='"+item.id+"'></li>";
            var li = $(liHtml);
            var value=$(this.options.lineTextFormat(item));
            var point=$("<div class='point'> </div>");
            var text=$("<div class='text'></div>").html(this.options.rightTextFormat(item));
            li.append(value).append(point).append(text);
            ul.append(li);
            if(!ul.is(".current")){
                li.hide();
            }else if(index == 0){
                li.addClass('old');
                li.removeClass('new');
                //调用用户自定义事件
                //this.options.onSelect(item);
            }
            //保存数据
            data.children[item.id] = li;
            li.click([this,item.id],function(e){
                e.data[0].selectItem(e.data[1]);
            });
        }
    },
    //适应页面大小
    fitSize : function(){
        var jDom = $(this.options.dom);
        var ul = jDom.find("ul.current");
        var height = jDom.height() * 0.95;
        var ulHeight = height - 41 * this.options.Items.length;
        //jDom.find(".videoAxis-line.static")[0].style.height=height+"px";

        ul.height(ulHeight);
        //设置li的高度
        ul.find("li").show();
        var uls = jDom.find("ul");
        var tempThis=this;
        uls.each(function(){
            var ulID=$(this).attr('id');
            var totalUlSize=0;
            var ulItem=tempThis.findItem(ulID).data;
            for(var index in ulItem.children){
                totalUlSize+=ulItem.children[index].value;
            }

            var avaliableHeight=ulHeight - ulItem.children.length * 60;
            if(avaliableHeight<0){
                avaliableHeight=0;
            }
            for(var index in ulItem.children){
                var liItem=ulItem.children[index];
                var li=$(this).find("#"+liItem.id).css('margin-bottom',avaliableHeight*liItem.value/totalUlSize);
            }
        });
    },
    //展开
    expand : function(id){
        var jDom = $(this.options.dom);

        var current =jDom.find("#"+id);
        var currentHead =jDom.find("#head-"+id);
        if(!current.is(".current")){
            //收缩已经展开的
            if(!current.is(".current")){
                //收缩已经展开的
                var jDom = $(this.options.dom);
                var ul = jDom.find(".videoAxis-item-ul.current");
                var head = jDom.find(".videoAxis-head.current");
                head.removeClass("current");
                currentHead.addClass("current");
                var height = ul.height();
                ul.children().hide();
                ul.animate({
                    height : 10
                },300).removeClass("current");
                //展开当前的
                current.animate({
                    height:height
                },300).addClass("current");
                current.children().show();
                //调用用户定义事件
                this.options.onExpand(current);
            }


            //改变mobile line起始高度
            var jDom = $(this.options.dom);
            var level1ID=jDom.find(".videoAxis-item-ul.current").attr("id");
            var level1Index=0;
            //一级标题高度40n,当前标题高度n，中间ul:12,li:60n,margintop
            var level2Items=[];
            for(var index in this.options.Items) {
                if(this.options.Items[index].id==level1ID){
                    level1Index=index;
                    level2Items=this.options.Items[index].children;
                    break;
                }
            };
            var height=(parseInt(level1Index)+1)*(40+10);//一级节点高度，和节点间的间隙
//        console.log('expandFirstNodes');
//        console.log(height);
            var mobileLine=jDom.find(".mobile")[0];
            mobileLine.style.transition="height 0s";
            mobileLine.style.height=height;

            //选择第一个子结点
            var jDom = $(this.options.dom);

            var curlis=jDom.find(".videoAxis-item-ul.current").find(".videoAxis-item-ul");
            var that=this;
            curlis.each(function(){
                that.switchStatus(this,'new');
            });

//            var li=jDom.find("#"+this.findItem(id).data.children[0].id);
//            li.removeClass('new');
//            li.addClass("current");
        }
    },
    //选择一个子结点，并触发选中事件
    selectItem : function(itemId){
        //console.log('selectItem'+itemId);
        if(this.options.clickEnabled){
            if(this.modifyCssTimeout){
                //console.log('clear timeout'+this.modifyCssTimeout);
                clearTimeout(this.modifyCssTimeout);
                this.modifyCssTimeout=null;
            };
            this.chooseItem(itemId);
            //执行用户自定义方法，关于联动的处理
            if(this.options.onSelect){
                this.options.onSelect(this.findItem(itemId).data);//onSelect方法里需包含对于chooseItem的处理
            }
        };
    },
    //改变选择状态,进度条高度计算至当前节点，增加下一节点的异步状态刷新方法，这里有一个冗余计算的问题
    chooseItem : function(itemId){
        var jDom = $(this.options.dom);
        var targetItem=this.findItem(itemId);
        if(!targetItem){
            console.log('找不到节点，节点id:'+itemId);
            return;
        }
        var targetIndex=targetItem.index;

        var level1ID=jDom.find(".videoAxis-item-ul.current").attr("id");
        var level1Index=0;
        //一级标题高度40n,当前标题高度n，中间ul:12,li:60n,margintop
        var level2Items=[];
        for(var index in this.options.Items) {
            if(this.options.Items[index].id==level1ID){
                level1Index=index;
                level2Items=this.options.Items[index].children;
                break;
            }
        };
        //console.log(level2Items);
        //根据targetIndex计算startHeight
        var startHeight=0;
        startHeight=(parseInt(level1Index)+1)*(40+10);//一级节点高度，和节点间的间隙
        //console.log(level1Index);
        for(var i=0;i<targetIndex;++i){
            var item=level2Items[i];
            var marginBottom=jDom.find("#"+item.id).css('margin-bottom');
            marginBottom=marginBottom.substr(0,marginBottom.indexOf('px'));
            var maxHeight=parseInt(marginBottom)>5?parseInt(marginBottom):5;//5 margin top
            startHeight+=maxHeight;
            startHeight +=60;//60 height +
            //改变样式
            this.switchStatus(jDom.find("#" + item.id),'old');
        }

        var mobileLine=jDom.find(".mobile")[0];
        startHeight+=1;//从第一节点跳往下一节点过程中，使第一次的结束值和下一节点起始值不同，能促使动画效果立刻结束
        mobileLine.style.transition="height 0s";
        mobileLine.style.height=startHeight+"px";
        //console.log('startheight:'+startHeight+',time:'+targetItem.data.value);

        //改变target节点的样式，但是不计算高度
        this.switchStatus(jDom.find("#" + itemId),'old');

        //其余节点也修改class
        //这里nextItem也要改变，应付在往上跳的过程中对历史节点的处理
        for(var i=targetIndex+1;i<level2Items.length;++i){
            var item=level2Items[i];
            this.switchStatus(jDom.find("#" + item.id),'new');
        }
    },
    /**
     * 动画效果往下延伸绿线的高度，以当前节点的配置时间为准
     * @param itemId
     */
    transitionMobileLineHeight:function(itemId){
        var jDom = $(this.options.dom);
        var targetItem=this.findItem(itemId);
        if(!targetItem){
            console.log('找不到节点，节点id:'+itemId);
            return;
        }
        var targetIndex=targetItem.index;
        var level1ID=jDom.find(".videoAxis-item-ul.current").attr("id");
        var level1Index=0;
        //一级标题高度40n,当前标题高度n，中间ul:12,li:60n,margintop
        var level2Items=[];
        for(var index in this.options.Items) {
            if(this.options.Items[index].id==level1ID){
                level1Index=index;
                level2Items=this.options.Items[index].children;
                break;
            }
        };
        //console.log(level2Items);
        //根据targetIndex计算startHeight
        var startHeight=0;
        startHeight=(parseInt(level1Index)+1)*(40+10);//一级节点高度，和节点间的间隙
        //console.log(level1Index);
        for(var i=0;i<targetIndex+1;++i){
            var item=level2Items[i];
            var marginBottom=jDom.find("#"+item.id).css('margin-bottom');
            marginBottom=marginBottom.substr(0,marginBottom.indexOf('px'));
            var maxHeight=parseInt(marginBottom)>5?parseInt(marginBottom):5;//5 margin top
            startHeight+=maxHeight;
            startHeight +=60;//60 height +
        }

        var mobileLine=jDom.find(".mobile")[0];
        //通过transition实现动画效果
//        setTimeout(function(){
            mobileLine.style.transition="height "+targetItem.data.value+"s linear";
            mobileLine.style.height=startHeight+"px";
            //console.log('transition:height:'+startHeight+',time:'+targetItem.data.value);
//        },100);
        //console.log(startHeight);
        //在最后一个节点修改class
//        var nextItem=level2Items[targetIndex+1];
//        if(nextItem){
//            var that=this;
//            this.modifyCssTimeout=setTimeout(function() {
////                if(!that.modifyCssTimeout){
////                    return;
////                }
//                that.switchStatus(jDom.find("#" + nextItem.id),'old');
//            },targetItem.data.value * 1000);
//            //console.log('set timeout:'+this.modifyCssTimeout);
//        }
    },
    /**
     * 终止动画效果
     * @param itemId
     */
    stopTransitionMobileLineHeight:function(itemId){
        if(this.modifyCssTimeout){
            clearTimeout(this.modifyCssTimeout);
            this.modifyCssTimeout=null;
        };
        this.chooseItem(itemId);
    },
    /**
     * 重新加载:终止异步方法，进度条高度计算至第一个节点，刷新所有节点状态为new，启用节点点击事件
     */
    reload:function(){
        if(this.modifyCssTimeout){
            clearTimeout(this.modifyCssTimeout);
            this.modifyCssTimeout=null;
        };

       var jDom=$(this.options.dom);
        var level1ID=jDom.find(".videoAxis-item-ul.current").attr("id");
        var level1Index=0;
        //一级标题高度40n,当前标题高度n，中间ul:12,li:60n,margintop
        var level2Items=[];
        for(var index in this.options.Items) {
            if(this.options.Items[index].id==level1ID){
                level1Index=index;
                level2Items=this.options.Items[index].children;
                break;
            }
        };
        //console.log(level2Items);
        var item=level2Items[0];
        this.switchStatus(jDom.find("#" + item.id),'old');

        //其余节点也修改class
        //这里nextItem也要改变，然后再等时间到了以后再改变
        for(var i=1;i<level2Items.length;++i){
            var item=level2Items[i];
            this.switchStatus(jDom.find("#" + item.id),'new');
        };
        var height=(parseInt(level1Index)+1)*(40+10);//一级节点高度，和节点间的间隙
        var mobileLine=jDom.find(".mobile")[0];
        mobileLine.style.transition="height 0s";
        mobileLine.style.height=height+"px";

        this.options.clickEnabled=true;
    },
    //启用 取消所有节点样式为disable，启用节点点击事件
    enable:function(){
        this.options.clickEnabled=true;
        var jDom=$(this.options.dom);
        var level1ID=jDom.find(".videoAxis-item-ul.current").attr("id");
        var level2Items=[];
        for(var index in this.options.Items) {
            if(this.options.Items[index].id==level1ID){
                level2Items=this.options.Items[index].children;
                break;
            }
        };

        for(var i=0;i<level2Items.length;++i){
            jDom.find("#" + level2Items[i].id).removeClass("disable");
        };
        jDom.find(".mobile").removeClass('disable');
        jDom.find(".static").removeClass('disable');
    },
    //禁用 终止异步方法，添加所有节点样式为disable，禁用节点点击事件，使用场景：停止巡航
    disable:function(){
        if(this.modifyCssTimeout){
            clearTimeout(this.modifyCssTimeout);
            this.modifyCssTimeout=null;
        };

        this.options.clickEnabled=false;
        var jDom=$(this.options.dom);
        var level1ID=jDom.find(".videoAxis-item-ul.current").attr("id");
        //一级标题高度40n,当前标题高度n，中间ul:12,li:60n,margintop
        var level2Items=[];
        for(var index in this.options.Items) {
            if(this.options.Items[index].id==level1ID){
                level2Items=this.options.Items[index].children;
                break;
            }
        };
        for(var i=0;i<level2Items.length;++i){
            jDom.find("#" + level2Items[i].id).addClass("disable");
        };
        jDom.find(".mobile").addClass('disable');
        jDom.find(".static").addClass('disable');
    },
    //静态显示：终止异步方法，禁用节点点击事件，重置进度条高度为0，使用场景：多屏巡航
    static:function(){
        if(this.modifyCssTimeout){
            clearTimeout(this.modifyCssTimeout);
            this.modifyCssTimeout=null;
        };
        this.options.clickEnabled=false;

        var jDom=$(this.options.dom);
        var level1ID=jDom.find(".videoAxis-item-ul.current").attr("id");
        var level2Items=[];
        for(var index in this.options.Items) {
            if(this.options.Items[index].id==level1ID){
                level2Items=this.options.Items[index].children;
                break;
            }
        };

        for(var i=0;i<level2Items.length;++i){
            this.switchStatus(jDom.find("#" + level2Items[i].id),'new');
        };


        var mobileLine=jDom.find(".mobile")[0];
        mobileLine.style.transition="height 0s";
        mobileLine.style.height="5px";
    },
    //终止动态巡航：终止异步方法，进度条高度计算至当前节点，修改当前节点样式为current，禁用节点点击事件，使用场景：锁定巡航
    pause:function(){
        if(this.modifyCssTimeout){
            clearTimeout(this.modifyCssTimeout);
            console.log('clear modifyCssTimeout:'+this.modifyCssTimeout);
            this.modifyCssTimeout=null;
        };
        var jDom = $(this.options.dom);
        var curID=jDom.find(".videoAxis-item-li.old:last").attr("id");

        var targetItem=this.findItem(curID);
        var targetIndex=targetItem.index;
        //改变样式
        this.switchStatus(jDom.find("#" + curID),'current');
        //改变高度，只计算到当前点
        var level1ID=jDom.find(".videoAxis-item-ul.current").attr("id");
        var level1Index=0;
        //一级标题高度40n,当前标题高度n，中间ul:12,li:60n,margintop
        var level2Items=[];
        for(var index in this.options.Items) {
            if(this.options.Items[index].id==level1ID){
                level1Index=index;
                level2Items=this.options.Items[index].children;
                break;
            }
        };
        //console.log(level2Items);
        //根据targetIndex计算startHeight
        var startHeight=0;
        startHeight=(parseInt(level1Index)+1)*(40+10);//一级节点高度，和节点间的间隙
        for(var i=0;i<targetIndex;++i){
            var item=level2Items[i];
            var marginBottom=jDom.find("#"+item.id).css('margin-bottom');
            marginBottom=marginBottom.substr(0,marginBottom.indexOf('px'));
            var maxHeight=parseInt(marginBottom)>5?parseInt(marginBottom):5;//5 margin top
            startHeight+=maxHeight;
            startHeight +=60;//60 height +5 margin top
        }
        var mobileLine=jDom.find(".mobile")[0];
        mobileLine.style.transition="height 0s";
        mobileLine.style.height=startHeight+"px";

        this.options.clickEnabled=false;
    },
    //启用巡航，启用节点点击事件
    continueNav:function(){
        this.options.clickEnabled=true;
    },
    //修改class,切换point的状态
    switchStatus:function(dom,targetStatus){
        if(dom){
            var statusArr=['old','new','current'];
            for(var index in statusArr){
                if(statusArr[index]==targetStatus){
                    dom.addClass(statusArr[index]);
                }
                else{
                    dom.removeClass(statusArr[index]);
                }
            }
        }
    },
    //一个接一个轮循选择
    //轮循选择在异步处理过程中会产生很多问题。
    chooseOneByOne:function(parentItems,curItemID,targetIndex,order){
//        var curItem=this.findItem(curItemID);
//        var nextItem=null;
//
//        if(order=='asc'){
//            nextItem=parentItems[curItem.index+1];
//        }
//        else{
//            nextItem=parentItems[curItem.index-1]; //考虑到第一个节点再往前是不能点的，不做边界判断
//        }
//
//
//        //改变class
//        var jDom = $(this.options.dom);
//        if(order=='asc'){//顺序
//            jDom.find("#"+curItemID).addClass("old");
//            jDom.find("#"+curItemID).removeClass("current");
//            jDom.find("#"+curItemID).removeClass("new");
//        }
//        else{//倒序
//            jDom.find("#"+curItemID).addClass("new");
//        }
//
//        if(curItem.index==targetIndex) {
//            if(order=='asc'){
//                setTimeout(function () {
//                    jDom.find("#" + nextItem.id).removeClass("new");
//                    jDom.find("#" + nextItem.id).addClass("current");
//                }, curItem.data.value * 1000);
//            }
//        }
//
//        //改变直线颜色（调整高度)
//        //一级标题高度40n,当前标题高度n，中间ul:12,li:60n,margintop
//        var mobileLine=jDom.find(".mobile")[0];
//        var heightPx=$(mobileLine).css('height');
//        var height=parseInt(heightPx.substr(0,heightPx.indexOf("px")));//item height
//        var marginBottom=jDom.find("#"+curItem.data.id).css('margin-bottom');
//        marginBottom=marginBottom.substr(0,marginBottom.indexOf('px'));
//
//        if(curItem.index==targetIndex){
//            console.log(curItem);
//            mobileLine.style.transition="height 0s";
//            if(order=='desc')//倒序在最后一点时先减再加
//            {
//                height -=60;
//                height-=parseInt(marginBottom);
//                console.log(height);
//                mobileLine.style.height=height+"px";
//            }
//            mobileLine.style.transition="height "+curItem.data.value+"s";
//            height +=60;
//            height+=parseInt(marginBottom);
//            console.log(height);
//            mobileLine.style.height=height;
//        }
//        else{
//            mobileLine.style.transition="height 0s";
//            if(order=='asc'){//顺序
//                height +=60;
//                height+=parseInt(marginBottom);
//                mobileLine.style.height=height+"px";
//            }
//            else{//倒序
//                height -=60;
//                height-=parseInt(marginBottom);
//                mobileLine.style.height=height;
//            }
//        }
//        console.log(height);
//        if(curItem.index==targetIndex){
//            return;
//        }
//        else{
//            //循环调用下一个
//            setTimeout(this.chooseOneByOne(parentItems,nextItem.id,targetIndex,order),curItem.data.value*1000);
//        }
    },
    findItem:function(searchID){
        for(var index in this.options.Items) {
            var result=this.findLoopItem(this.options.Items,index,searchID);
            if(result){
                return result;
            }
        }
        return null;
    },
    findLoopItem:function(items,index,searchID){
        if(!items||searchID==items[index].id){
            return {
                index: parseInt(index),
                data: items[index]
            };
        };
        for(var index1 in items[index].children){
            var result=this.findLoopItem(items[index].children,index1,searchID);
            if(result){
                return result;
            }
        }
    }
};