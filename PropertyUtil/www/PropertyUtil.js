var exec = require('cordova/exec');


var PropertyUtil = function() {};

PropertyUtil.prototype.exec=function(arg0, success, error) {
    exec(success, error, "PropertyUtil", "coolMethod", [arg0]);
}

PropertyUtil.prototype.store=function(arg0, success, error) {
    exec(success, error, "PropertyUtil", "store", [arg0]);
}

PropertyUtil.prototype.fetch=function(arg0, success, error) {
    exec(success, error, "PropertyUtil", "fetch", [arg0]);
}

var propertyUtil=new PropertyUtil();
module.exports=propertyUtil;
