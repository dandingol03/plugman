var exec = require('cordova/exec');

var plugin001={};

plugin001.alert=function(){
    alert("invoke plugin plugin001");
}

plugin001.exec=function(arg0,success,error){
    exec(success,error,"plugin001","coolMethod",[arg0]);
}

//直接导出为coolMethod方法
exports.plugin001=plugin001;