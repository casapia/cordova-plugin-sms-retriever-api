var PLUGIN_NAME = "EntelSMSRetrieverAPI";
var exec = require("cordova/exec");

exports.echo = function (text) {
  return new Promise(function (resolve, reject) {
    exec(resolve, reject, PLUGIN_NAME, "echo", [text]);
  });
};

exports.startSMSListener = function (callback, errorCallback) {
  exec(callback, errorCallback, PLUGIN_NAME, "startSMSListener", []);
};

exports.stopSMSListener = function (text) {
  return new Promise(function (resolve, reject) {
    exec(resolve, reject, PLUGIN_NAME, "stopSMSListener", [text]);
  });
};
