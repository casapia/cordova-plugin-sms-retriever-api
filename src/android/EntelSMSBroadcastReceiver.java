package cl.entel.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

public class EntelSMSBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "EntelSMSBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        CallbackContext callbackContext = EntelSMSRetrieverCallbackHelper.getCallbackContext();
        if (callbackContext == null) {
            Log.w(TAG, "onReceive: callbackContext is null");
            return;
        }
        final String action = intent.getAction();
        Log.d(TAG, "onReceive: " + action);
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(action)) {
            final Bundle extras = intent.getExtras();
            if (extras == null) {
                Log.w(TAG, "onReceive: extras is null");
                PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "extras is null");
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);
                return;
            }
            final Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
            if (status == null) {
                Log.w(TAG, "onReceive: status is null");
                PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "status is null");
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);
                return;
            }
            final int statusCode = status.getStatusCode();
            Log.d(TAG, "onReceive: status: " + statusCode);
            if (statusCode == CommonStatusCodes.SUCCESS || statusCode == CommonStatusCodes.SUCCESS_CACHE) {
                String message = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE);
                Log.d(TAG, "onReceive: message: " + message);
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, message);
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);
            } else {
                Log.w(TAG, "onReceive: status code: " + statusCode);
                PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "status code: " + statusCode);
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);
            }
        }
    }
}
