package cl.entel.cordova;

import static by.chemerisuk.cordova.support.ExecutionThread.WORKER;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;

import by.chemerisuk.cordova.support.CordovaMethod;
import by.chemerisuk.cordova.support.ReflectiveCordovaPlugin;

public class EntelSMSRetrieverAPI extends ReflectiveCordovaPlugin {
    private static final String TAG = "EntelSMSRetrieverAPI";

    @Override
    protected void pluginInitialize() {
        Log.d(TAG, "pluginInitialize");
    }

    @CordovaMethod
    public void echo(CordovaArgs args, CallbackContext callbackContext) {
        Log.d(TAG, "echo");
        try {
            String text = args.getString(0);
            Log.d(TAG, "echo: " + text);
            callbackContext.success(text);
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
            callbackContext.error(ex.getMessage());
        }
    }

    @CordovaMethod(WORKER)
    public void onSMSReceived(CallbackContext callbackContext) {
        Log.d(TAG, "onSMSReceived");
        EntelSMSRetrieverCallbackHelper.setCallbackContext(callbackContext);
    }
}