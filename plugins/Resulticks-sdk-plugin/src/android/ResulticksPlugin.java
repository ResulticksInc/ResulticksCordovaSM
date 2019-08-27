package resu.io;

import android.text.TextUtils;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.mob.resu.reandroidsdk.MRegisterUser;
import io.mob.resu.reandroidsdk.ReAndroidSDK;
import io.mob.resu.reandroidsdk.error.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class ResulticksPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if ("register".equals(action)) {
            this.register(args, callbackContext);
        } else if ("customEvent".equals(action)) {
            this.customEvent(args, callbackContext);
        } else if ("screenStart".equals(action)) {
            this.screenStart(args, callbackContext);
        } else if ("screenEnd".equals(action)) {
            this.screenEnd(args, callbackContext);
        } else if ("customFieldCapture".equals(action)) {
            this.customFieldCapture(args, callbackContext);
        }
        return false;
    }

    private void register(JSONArray message, final CallbackContext callbackContext) {

        if (message != null && message.length() > 0) {
            try {
                JSONObject jsonObject = message.getJSONObject(0);
                MRegisterUser registerUser = new MRegisterUser();
                registerUser.setUserUniqueId(jsonObject.optString("uniqueId"));
                registerUser.setName(jsonObject.optString("name"));
                registerUser.setEmail(jsonObject.optString("email"));
                registerUser.setPhone(jsonObject.optString("phone"));
                registerUser.setAge(jsonObject.optString("age"));
                registerUser.setGender(jsonObject.optString("gender"));
                registerUser.setDeviceToken(jsonObject.optString("token"));
                registerUser.setProfileUrl(jsonObject.optString("profileUrl"));
                ReAndroidSDK.getInstance(this.cordova.getActivity()).onDeviceUserRegister(registerUser);
            } catch (Exception e) {
                //callbackContext.success("Welcome Buvanesh");
                Log.e("User reg Exception: ",String.valueOf(e.getMessage()));
            }

        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    
     private void customEvent(JSONArray message, CallbackContext callbackContext) {

        if (message != null && message.length() > 0) {
            try {
                JSONObject jsonObject = message.getJSONObject(0);
                String eventName = jsonObject.optString("eventName");
                JSONObject eventData = jsonObject.optJSONObject("data");
                if (TextUtils.isEmpty(eventName)) {
                    Toast.makeText(cordova.getActivity(), "Event name can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(eventData.toString()))
                    ReAndroidSDK.getInstance(this.cordova.getActivity()).onTrackEvent(eventName);
                else
                    ReAndroidSDK.getInstance(this.cordova.getActivity()).onTrackEvent(eventData,eventName);

            } catch (Exception e) {
                Log.e("User events Exception: ",String.valueOf(e.getMessage()));
            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }


    private void screenStart(JSONArray message, CallbackContext callbackContext) {

        if (message != null && message.length() > 0) {
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void screenEnd(JSONArray message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            //Toast.makeText(this.cordova.getActivity(), message, Toast.LENGTH_SHORT).show();
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void customFieldCapture(JSONArray message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            //Toast.makeText(this.cordova.getActivity(), message, Toast.LENGTH_SHORT).show();
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }




}
