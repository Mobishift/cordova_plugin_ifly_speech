package com.mobishift.cordova.plugins.iflyspeech;

import android.os.Bundle;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;


import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class IFlySpeech extends CordovaPlugin {
    private static final String ACTION_SPEECH = "speech";

    private CallbackContext context;
    private SpeechSynthesizer speechSynthesizer;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        context = callbackContext;
        init();
        if (action.equals(ACTION_SPEECH)) {
            String message = args.getString(0);
            speech(message);
            return true;
        }
        return false;
    }

    private void init(){
        if(speechSynthesizer == null) {
            String appId = webView.getPreferences().getString("iflytekappid", "");
            SpeechUtility.createUtility(this.cordova.getActivity(), SpeechConstant.APPID + "=" + appId);
            speechSynthesizer = SpeechSynthesizer.createSynthesizer(this.cordova.getActivity(), null);
            speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
            speechSynthesizer.setParameter(SpeechConstant.SPEED, "50");
            speechSynthesizer.setParameter(SpeechConstant.VOLUME, "80");
            speechSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        }
    }

    private void speech(String message){
        speechSynthesizer.startSpeaking(message, synthesizerListener);
    }

    private SynthesizerListener synthesizerListener =  new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {

        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onCompleted(SpeechError speechError) {
            if(speechError != null){
                context.error(speechError.getMessage());
            }else{
                context.success();
            }
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };

}
