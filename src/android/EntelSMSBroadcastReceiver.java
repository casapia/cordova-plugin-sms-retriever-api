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

import java.util.Objects;

public class EntelSMSBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "EntelSMSBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
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
                return;
            }
            final Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
            if (status == null) {
                Log.w(TAG, "onReceive: status is null");
                return;
            }
            final int statusCode = status.getStatusCode();
            Log.d(TAG, "onReceive: status: " + statusCode);
            switch(statusCode) {
                case CommonStatusCodes.SUCCESS:
                    String message = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE);
                    Log.d(TAG, "onReceive: message: " + message);
                    callbackContext.success(message);
                    break;
                case CommonStatusCodes.TIMEOUT:
                    Log.w(TAG, "onReceive: timeout");
                    break;
            }
        }
    }
}
