var exec = require('cordova/exec');

var NvFileTransfer = function() {};

NvFileTransfer.prototype.coolMethod=function(arg0, success, error) {
    exec(success, error, "nvFileTransfer", "coolMethod", [arg0]);
}

NvFileTransfer.prototype.copyFile=function(arg0, success, error){
    exec(success, error, "nvFileTransfer", "copyFile", [arg0]);
}

NvFileTransfer.prototype.readFile=function(arg0, success, error){
    exec(success, error, "nvFileTransfer", "readFile", [arg0]);
}

NvFileTransfer.prototype.writeFile=function(arg0, success, error){
    exec(success, error, "nvFileTransfer", "writeFile", [arg0]);
}

NvFileTransfer.prototype.listDir=function(arg0, success, error){
    exec(success, error, "nvFileTransfer", "listDir", [arg0]);
}

var nvFileTransfer=new NvFileTransfer();
module.exports=nvFileTransfer;
