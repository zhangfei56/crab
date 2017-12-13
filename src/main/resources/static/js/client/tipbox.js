
/* 提示框     错误 警告  成功等提示框 */
var msgClickTimes=0;
function openBox(type,title,cont){
    msgClickTimes+=1;
    if (type=="worning"){  //蓝
        var typeCode='<i class="fa fa-exclamation-circle"></i>';
    }
    else if(type=="question"){ //黄
        var typeCode='<i class="fa fa-question-circle"></i>';
    }
    else if(type=="right"){  //绿
        var typeCode='<i class="fa fa-check-circle"></i>';
    }
    else if(type=="wrong"){  //红
        var typeCode='<i class="fa fa-frown-o"></i>';
    }
    else if(type=="speaker"){   //白
        var typeCode='<i class="fa fa-volume-up"></i>';
    }
    else{
        var typeCode='<i class="fa fa-info-circle"></i>';
    }
    var layer=document.createElement("div");
    layer.className="tipBox-"+type;
    layer.innerHTML=('<div class="tipsBox" id="Tips_PsnUpdate_Box'+msgClickTimes+'"><h1>'+typeCode+title+'</h1><p class="coinAdded">'+cont+'</p></div>');
    document.body.appendChild(layer);
    var box=document.getElementById("Tips_PsnUpdate_Box"+msgClickTimes);
    startMove(box,{top:200,opacity:100},5,function closeBox(){
        setTimeout (function(){
            startMove(box,{opacity:0},25,function(){
                document.body.removeChild(layer);
            });
        }, 1000);
    });
//setTimeout (function(){closeBox()}, 2000);
}
//startMove(oDiv, {width: 400, height: 400}执行后的再调用的函数)   --->更新2013.3-21

//运动函数
function startMove(obj, json, speednum, fnEnd){
	clearInterval(obj.timer);
	obj.timer=setInterval(function (){
		var bStop=true;		//假设：所有值都已经到了
		for(var attr in json)
		{
			var cur=0;
			if(attr=='opacity'){
				cur=Math.round(parseFloat(getStyle(obj, attr))*100);
			}
			else{
				cur=parseInt(getStyle(obj, attr));
			}
			var speed=(json[attr]-cur)/speednum;
			speed=speed>0?Math.ceil(speed):Math.floor(speed);
			if(cur!=json[attr])
				bStop=false;
			if(attr=='opacity'){
				obj.style.filter='alpha(opacity:'+(cur+speed)+')';
				obj.style.opacity=(cur+speed)/100;
			}else{
				obj.style[attr]=cur+speed+'px';
			}
		}
		if(bStop){
			clearInterval(obj.timer);
			if(fnEnd)fnEnd();
		}
	}, 30);
}
//获取非行间样式
function getStyle(obj, name){
	if(obj.currentStyle){
		return obj.currentStyle[name];
	}else{
		return getComputedStyle(obj, false)[name];
	}
}
/* 提示框     错误 警告  成功等提示框结束 */