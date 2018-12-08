'use strict';

/*
int RESULT_COMPLETE = -1;
int RESULT_ERROR = 0;
int EVENT_GET_SUPPORTED_COUNTRIES = 1;
int EVENT_GET_VERIFICATION_CODE = 2;
int EVENT_SUBMIT_VERIFICATION_CODE = 3;
int EVENT_GET_CONTACTS = 4;
int EVENT_SUBMIT_USER_INFO = 5;
int EVENT_GET_FRIENDS_IN_APP = 6;
int EVENT_GET_NEW_FRIENDS_COUNT = 7;
int EVENT_GET_VOICE_VERIFICATION_CODE = 8;
*/

import {NativeModules, Platform } from 'react-native';
const ReactNativeMobSMS = NativeModules.ReactNativeMobSMS;

export function sendCode(phone, zone='86', template='', showToast=true) {
  if (Platform.OS === 'android') {
    return new Promise((resolve, reject) => {
      ReactNativeMobSMS.sendCode(phone, zone, template, showToast);
    });
  } else {
    return new Promise((resolve, reject) => {
      ReactNativeMobSMS.sendCode(phone, zone, template).then((data)=>{
        resolve(data);
      }).catch((error)=>{
        reject(error);
      });
    });
  }
}
