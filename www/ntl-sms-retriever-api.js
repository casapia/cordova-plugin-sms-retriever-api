var PLUGIN_NAME = "EntelSMSRetrieverAPI";
var exec = require("cordova/exec");

exports.echo =
    function (text) {
        return new Promise(function (resolve, reject) {
            exec(resolve, reject, PLUGIN_NAME, "echo", [text]);
        });
    }

exports.onDiscoveryStarted =
    function (callback, errorCallback) {
        exec(callback, errorCallback, PLUGIN_NAME, "onDiscoveryStarted", []);
    };
