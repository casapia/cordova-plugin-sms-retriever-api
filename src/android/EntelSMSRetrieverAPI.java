package cl.entel.cordova;

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

//    @CordovaMethod(WORKER)
//    public  void requestHint(CordovaArgs args, CallbackContext callbackContext) {
//        Log.d(TAG, "requestHint");
//        try {
//            HintRequest hintRequest = new HintRequest.Builder()
//                    .setPhoneNumberIdentifierSupported(true)
//                    .build();
//
//            PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(
//                    apiClient, hintRequest);
//            startIntentSenderForResult(intent.getIntentSender(),
//                    RESOLVE_HINT, null, 0, 0, 0);
//        } catch (Exception ex) {
//            Log.e(TAG, ex.toString());
//            callbackContext.error(ex.getMessage());
//        }
//    }
}