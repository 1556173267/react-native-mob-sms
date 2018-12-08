# react-native-mob-sms

React Native bridging library that integrates Mob-SMS
## install

```
npm install @superhao/react-native-mob-sms --save
```

## Android

### Automatic

```
react-native link @superhao/react-native-mob-sms
```

### Manually

* android/settings.gradle

```
include ':react-native-mob-sms'
project(':react-native-mob-sms').projectDir = new File(rootProject.projectDir, '../node_modules/@superhao/react-native-mob-sms/android')
```

* android/app/build.gradle

```
dependencies {
    compile project(':react-native-mob-sms')
}
```

* register module (in MainActivity.java)

```java
...

import com.superhao.react_native_mob_sms.MobSMSReactPackage; // <--- IMPORT

public class MainActivity extends ReactActivity {

    ...

    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new MobSMSReactPackage() // <--- ADD HERE
        );
    }
    
    ...
    
      @Override
  public void onCreate() {
    super.onCreate();
    ...
    MobSMS.init(this, "your-mob-appkey", "your-mob-appsecret");
    ...
  }
}
```

## iOS

### Automatic

```
react-native link @superhao/react-native-mob-sms
```

### Manually

> Link `AlipayModule` library from your `/node_modules/@superhao/react-native-mob-sms/ios` folder like its [described here](http://facebook.github.io/react-native/docs/linking-libraries-ios.html). Don't forget to add it to "Build Phases" of project.

### Config

* Added the following libraries to your "Link Binary With Libraries":
  * [x] libz
  * [x] libicucore
  * [x] MessageUI.framework
  * [x] JavaScriptCore.framework
  * [x] libstdc++ (libc++ for after xcode10)
  * [ ] SystemConfiguration.framework
  * [ ] CoreTelephony.framework
  * [ ] AdSupport.framework
  * [ ] AddressBook.framework
  * [ ] AddressBookUI.framework

## General Usage

```javascript
import {sendCode} from '@superhao/react-native-mob-sms';


```

```javascript
sendCode('your-phone, 'your-zone', 'your-template').
  then((data)=>{
    if (Platform.OS === 'ios') {
      // show toast
      // this.refs.toast.show('验证码发送成功', 1500);
    }
  }).catch((error)=>{
       if (Platform.OS === 'ios') {
         // show toast
         // this.refs.toast.show('验证码发送失败', 1500);
       }
  });

```
