package resu.io;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import io.mob.resu.reandroidsdk.AppConstants;
import io.mob.resu.reandroidsdk.AppLifecyclePresenter;
import io.mob.resu.reandroidsdk.MRegisterUser;
import io.mob.resu.reandroidsdk.ReAndroidSDK;
import io.mob.resu.reandroidsdk.error.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class ResulticksPlugin extends CordovaPlugin {

    private static final String TAG = "ResulticksPlugin";

    public static CordovaWebView gWebView;
    CallbackContext callbackContext;
    String OldScreenName = null;
    String newScreenName = null;
    private Calendar oldCalendar = Calendar.getInstance();
    private Calendar sCalendar = Calendar.getInstance();
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("request")) {

            }
        }
    };


    public ResulticksPlugin() {

    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        AppConstants.LogFlag = true;
        AppConstants.isHyBird = true;
        gWebView = webView;
        android.util.Log.d(TAG, "==> ResulticksPlugin initialize");
        LocalBroadcastManager.getInstance(cordova.getActivity()).registerReceiver(mMessageReceiver, new IntentFilter("request"));
    }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        switch (action) {
            case "resRegister":
                this.resRegister(args, callbackContext);
                break;

            case "resEvent":
                this.resEvent(args, callbackContext);
                break;

            case "resNavigation":
                this.resNavigation(args, callbackContext);
                break;

            case "resLocation":
                this.resLocation(args, callbackContext);
                break;

            default:
                break;
        }
        return false;
    }

    private void resLocation(JSONArray message, CallbackContext callbackContext) {

        cordova.getThreadPool().execute(() -> {

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
                        ReAndroidSDK.getInstance(cordova.getActivity()).onTrackEvent(eventName);
                    else
                        ReAndroidSDK.getInstance(cordova.getActivity()).onTrackEvent(eventData, eventName);

                } catch (Exception e) {
                    Log.e("User events Exception: ", String.valueOf(e.getMessage()));
                }
            } else {
                Log.e("User events Exception: ", "Expected one non-empty string argument.");
            }

        });

    }


    private void resNavigation(JSONArray message, CallbackContext callbackContext) {

        cordova.getThreadPool().execute(() -> {
            if (message != null && message.length() > 0) {
                try {
                    JSONObject jsonObject = message.getJSONObject(0);
                    screenTracking(jsonObject.optString("screenName"));
                    OldScreenName = newScreenName;
                    newScreenName = jsonObject.optString("screenName");
                } catch (Exception e) {
                    Log.e("userNavigation Exception: ", String.valueOf(e.getMessage()));
                }

            } else {
                Log.e("userNavigation Exception: ", "Expected one non-empty string argument.");
            }
        });

    }

    private void resRegister(JSONArray message, CallbackContext callbackContext) {

        cordova.getThreadPool().execute(() -> {
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
                    ReAndroidSDK.getInstance(cordova.getActivity()).onDeviceUserRegister(registerUser);
                } catch (Exception e) {
                    Log.e("register Exception: ", String.valueOf(e.getMessage()));
                }

            } else {

                Log.e("register Exception: ", "Expected one non-empty string argument.");
            }
        });
    }

    private void resEvent(JSONArray message, CallbackContext callbackContext) {
        cordova.getThreadPool().execute(() -> {
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
                        ReAndroidSDK.getInstance(cordova.getActivity()).onTrackEvent(eventName);
                    else
                        ReAndroidSDK.getInstance(cordova.getActivity()).onTrackEvent(eventData, eventName);

                } catch (Exception e) {
                    Log.e("User events Exception: ", String.valueOf(e.getMessage()));
                }
            } else {
                Log.e("User events Exception: ", "Expected one non-empty string argument.");
            }

        });
    }

    private void screenTracking(String screenName) {

        try {

            if (sCalendar == null)
                sCalendar = Calendar.getInstance();

            oldCalendar = sCalendar;
            sCalendar = Calendar.getInstance();

            if (OldScreenName != null) {
                AppLifecyclePresenter.getInstance().onSessionStop(cordova.getActivity(), oldCalendar, sCalendar, OldScreenName, null, null);
                AppLifecyclePresenter.getInstance().onSessionStartFragment(cordova.getActivity(), OldScreenName, null);
            }
            if (newScreenName == null)
                newScreenName = screenName;

        } catch (Exception e) {
            Log.e("screenTracking Exception: ", "" + e.getMessage());

        }
    }


    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        screenTracking(newScreenName);
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        oldCalendar = Calendar.getInstance();
        sCalendar = Calendar.getInstance();
    }


}
