var exec = require('cordova/exec');
var noop = function(){};

exports.exec = function(action, arg0, success, error) {
    exec(success, error, "IFlySpeech", action, [arg0]);
};

exports.speech = function(message, successCallback, errorCallback){
    message = message || "";
    successCallback = successCallback || noop;
    errorCallback = errorCallback || noop;
    exports.exec('speech', speechString, successCallback, errorCallback);
};
