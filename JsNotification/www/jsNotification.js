var exec = require('cordova/exec');


var JsNotification = function() {};

JsNotification.prototype.alert = function() {
    alert("I am a js plugin");
};

JsNotification.prototype.exec=function(arg0, success, error) {
    exec(success, error, "jsNotification", "coolMethod", [arg0]);
}

JsNotification.prototype.store=function(key,value, success, error) {
    exec(success, error, "jsNotification", "store", [key,value]);
}

JsNotification.prototype.fetch=function(arg0, success, error) {
    exec(success, error, "jsNotification", "fetch", [arg0]);
}

JsNotification.prototype.download=function(arg0,arg1, success, error) {
    exec(success, error, "jsNotification", "download", [arg0,arg1]);
}

var jsNotification=new JsNotification();
module.exports=jsNotification;
