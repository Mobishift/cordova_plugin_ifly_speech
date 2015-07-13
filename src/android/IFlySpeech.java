package com.mobishift.cordova.plugins.iflyspeech;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class IFlySpeech extends CordovaPlugin {
    private static final String ACTION_SPEECH = "speech";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_SPEECH)) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }
}
