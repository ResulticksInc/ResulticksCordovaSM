var exec = require('cordova/exec');

module.exports.register = function(arg0, success, error) {
	exec(success, error, 'ResulticksPlugin', 'register', [ arg0 ]);
};

module.exports.customEvent = function(arg0, success, error) {
	exec(success, error, 'ResulticksPlugin', 'customEvent', [ arg0 ]);
};

module.exports.screenStart = function(arg0, success, error) {
	exec(success, error, 'ResulticksPlugin', 'screenStart', [ arg0 ]);
};

module.exports.screenEnd = function(arg0, success, error) {
	exec(success, error, 'ResulticksPlugin', 'screenEnd', [ arg0 ]);
};
module.exports.customFieldCapture = function(arg0, success, error) {
	exec(success, error, 'ResulticksPlugin', 'customFieldCapture', [ arg0 ]);
};
