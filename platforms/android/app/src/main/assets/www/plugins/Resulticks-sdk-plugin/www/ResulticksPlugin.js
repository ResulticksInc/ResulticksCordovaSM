cordova.define("Resulticks-sdk-plugin.ResulticksPlugin", function(require, exports, module) {

var exec = require('cordova/exec');

module.exports.resRegister = function(arg0, success, error) {
	exec(success, error, 'ResulticksPlugin', 'resRegister', [ arg0 ]);
};

module.exports.resEvent = function(arg0, success, error) {
	exec(success, error, 'ResulticksPlugin', 'resEvent', [ arg0 ]);
};

module.exports.resLocation = function(arg0, success, error) {
	exec(success, error, 'ResulticksPlugin', 'resLocation', [ arg0 ]);
};

module.exports.resNavigation = function(arg0, success, error) {
	exec(success, error, 'ResulticksPlugin', 'resNavigation', [ arg0 ]);
};
});
