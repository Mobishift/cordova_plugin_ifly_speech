/********* IFlySpeech.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

#import <AVFoundation/AVFoundation.h>

@interface IFlySpeech : CDVPlugin<AVSpeechSynthesizerDelegate> {
  // Member variables go here.
    NSString* callbackId;
}
@property (nonatomic, strong)AVSpeechSynthesizer* speechSynthesizer;

- (void)speech:(CDVInvokedUrlCommand*)command;
@end

@implementation IFlySpeech

- (void)speech:(CDVInvokedUrlCommand*)command
{
    callbackId = command.callbackId;
    NSString* message = [command.arguments objectAtIndex:0];
    [self initSpeech];
    [self speak:message];
}

- (void)initSpeech{
    if(self.speechSynthesizer == nil){
        self.speechSynthesizer = [[AVSpeechSynthesizer alloc] init];
        self.speechSynthesizer.delegate = self;
    }
}

- (void)speak:(NSString*)text{
//    if(self.speechSynthesizer.isSpeaking){
//        [self.speechSynthesizer stopSpeakingAtBoundary:AVSpeechBoundaryImmediate];
//    }
    AVSpeechUtterance* utterance = [[AVSpeechUtterance alloc] initWithString:text];
    if([[[UIDevice currentDevice] systemVersion] compare:@"9.0" options:NSNumericSearch] != NSOrderedAscending){
        utterance.rate = 0.4;
    }else{
        utterance.rate = 0.1;
    }
    utterance.voice = [AVSpeechSynthesisVoice voiceWithLanguage:@"zh_CN"];
    [self.speechSynthesizer speakUtterance:utterance];
}

- (void)speechSynthesizer:(AVSpeechSynthesizer *)synthesizer didFinishSpeechUtterance:(AVSpeechUtterance *)utterance{
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];

    [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackId];
}

- (void)speechSynthesizer:(AVSpeechSynthesizer *)synthesizer didCancelSpeechUtterance:(AVSpeechUtterance *)utterance{
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];

    [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackId];
}

@end
