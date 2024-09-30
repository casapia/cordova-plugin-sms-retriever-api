package cl.entel.cordova;

import org.apache.cordova.CallbackContext;

public class EntelSMSRetrieverCallbackHelper {
    private static CallbackContext callbackContext;

    public static void setCallbackContext(CallbackContext context) {
        callbackContext = context;
    }

    public static CallbackContext getCallbackContext() {
        return callbackContext;
    }

    public static void clearCallbackContext() {
        callbackContext = null;
    }
}

