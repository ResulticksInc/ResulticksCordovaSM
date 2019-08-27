cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "cordova-plugin-fcm.FCMPlugin",
      "file": "plugins/cordova-plugin-fcm/www/FCMPlugin.js",
      "pluginId": "cordova-plugin-fcm",
      "clobbers": [
        "FCMPlugin"
      ]
    },
    {
      "id": "Resulticks-sdk-plugin.ResulticksPlugin",
      "file": "plugins/Resulticks-sdk-plugin/www/ResulticksPlugin.js",
      "pluginId": "Resulticks-sdk-plugin",
      "clobbers": [
        "ResulticksPlugin"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-whitelist": "1.3.4",
    "cordova-plugin-fcm": "2.1.2",
    "Resulticks-sdk-plugin": "0.1.14"
  };
});