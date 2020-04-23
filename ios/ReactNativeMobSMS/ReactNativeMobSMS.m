//
//  ReactNativeMobSMS.m
//  ReactNativeMobSMS
//
//  Created by superhao on 2018/8/29.
//  Copyright © 2018年 superhao. All rights reserved.
//

#import "ReactNativeMobSMS.h"
#import <SMS_SDK/SMSSDK.h>

@implementation ReactNativeMobSMS

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(sendCode:(NSString *)phone zone: (NSString *)zone template: (NSString *)template
                  resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject){
    //带自定义模版
    [SMSSDK getVerificationCodeByMethod:SMSGetCodeMethodSMS phoneNumber: phone zone: zone template: template result:^(NSError *error) {
        
        if (!error)
        {
            resolve(@"发送成功");
        }
        else
        {
            
            reject(error.userInfo[@"code"], error.description, error);
        }
    }];
}

@end
