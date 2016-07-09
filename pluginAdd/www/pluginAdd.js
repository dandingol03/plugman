var exec = require('cordova/exec');


var PluginAdd = function() {};

PluginAdd.prototype.alert = function() {
    alert("I am a js plugin");
};

PluginAdd.prototype.exec=function(arg0, success, error) {
    exec(success, error, "pluginAdd", "coolMethod", [arg0]);
}

var pluginAdd=new PluginAdd();
module.exports=pluginAdd;
