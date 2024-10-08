package cl.entel.cordova;

import static by.chemerisuk.cordova.support.ExecutionThread.WORKER;

import android.util.Log;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.Task;

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
    public void onSMSReceived(CallbackContext callbackContext) {
        Log.d(TAG, "onSMSReceived");
        EntelSMSRetrieverCallbackHelper.setCallbackContext(callbackContext);
    }

    @CordovaMethod(WORKER)
    public void startSMSListener(CallbackContext callbackContext) {
        Log.d(TAG, "startSMSListener");
        SmsRetrieverClient client = SmsRetriever.getClient(cordova.getActivity().getApplicationContext());
        Task<Void> task = client.startSmsRetriever();
        task.addOnSuccessListener(aVoid -> Log.d(TAG, "startSmsRetriever onSuccess"));
        task.addOnFailureListener(e -> Log.w(TAG, "startSmsRetriever onFailure", e));
        task.addOnCompleteListener(task1 -> Log.d(TAG, "startSmsRetriever onComplete"));
        task.addOnCanceledListener(() -> Log.d(TAG, "startSmsRetriever onCanceled"));
        callbackContext.success();
    }

    @CordovaMethod
    public void stopSMSListener(CallbackContext callbackContext) {
        Log.d(TAG, "stopSMSListener");
        EntelSMSRetrieverCallbackHelper.clearCallbackContext();
        callbackContext.success();
    }
}