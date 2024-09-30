var PLUGIN_NAME = "EntelSMSRetrieverAPI";
var exec = require("cordova/exec");

exports.echo = function (text) {
  return new Promise(function (resolve, reject) {
    exec(resolve, reject, PLUGIN_NAME, "echo", [text]);
  });
};

exports.onSMSReceived = function (callback, errorCallback) {
  exec(callback, errorCallback, PLUGIN_NAME, "onSMSReceived", []);
};

exports.startSMSListener = function (text) {
  return new Promise(function (resolve, reject) {
    exec(resolve, reject, PLUGIN_NAME, "startSMSListener", [text]);
  });
};

exports.stopSMSListener = function (text) {
  return new Promise(function (resolve, reject) {
    exec(resolve, reject, PLUGIN_NAME, "stopSMSListener", [text]);
  });
};

exports.getAppSignatures = function (text) {
  return new Promise(function (resolve, reject) {
    exec(resolve, reject, PLUGIN_NAME, "getAppSignatures", [text]);
  });
};
