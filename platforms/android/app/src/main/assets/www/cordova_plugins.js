cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "cordova-plugin-fcm-updated.FCMPlugin",
      "file": "plugins/cordova-plugin-fcm-updated/www/FCMPlugin.js",
      "pluginId": "cordova-plugin-fcm-updated",
      "clobbers": [
        "FCMPlugin"
      ]
    },
    {
      "id": "resulticks-cordova-plugin.ReCordovaPlugin",
      "file": "plugins/resulticks-cordova-plugin/www/ReCordovaPlugin.js",
      "pluginId": "resulticks-cordova-plugin",
      "clobbers": [
        "ReCordovaPlugin"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-whitelist": "1.3.4",
    "cordova-plugin-fcm-updated": "1.0.1",
    "resulticks-cordova-plugin": "1.2"
  };
});