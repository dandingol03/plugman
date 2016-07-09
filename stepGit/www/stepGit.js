var exec = require('cordova/exec');


var StepGit = function() {};

StepGit.prototype.exec=function(arg0, success, error) {
    exec(success, error, "stepGit", "coolMethod", [arg0]);
}

//调用本地服务
StepGit.prototype.serviceInvoke=function(arg0, success, error) {
    exec(success, error, "stepGit", "serviceInvoke", [arg0]);
}


var stepGit=new StepGit();
module.exports=stepGit;
