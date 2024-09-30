package cl.entel.cordova;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.PluginResult;

import java.util.ArrayList;

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

    @CordovaMethod
    public void startSMSListener(CallbackContext callbackContext) {
        Log.d(TAG, "startSMSListener");
        EntelSMSRetrieverCallbackHelper.setCallbackContext(callbackContext);
    }

    @CordovaMethod
    public void stopSMSListener(CallbackContext callbackContext) {
        Log.d(TAG, "stopSMSListener");
        EntelSMSRetrieverCallbackHelper.clearCallbackContext();
    }

    @CordovaMethod
    public void getAppSignatures(CallbackContext callbackContext) {
        Log.d(TAG, "getAppSignatures");
        ArrayList<String> appCodes = new EntelSMSAppSignatureHelper(cordova.getActivity().getApplicationContext()).getAppSignatures();
        if (appCodes.isEmpty() || appCodes.get(0) == null) {
            PluginResult result = new PluginResult(PluginResult.Status.ERROR, "appCodes is empty");
            callbackContext.sendPluginResult(result);
        } else {
            String hash = appCodes.get(0);
            PluginResult result = new PluginResult(PluginResult.Status.OK, hash);
            callbackContext.sendPluginResult(result);
        }
    }
}