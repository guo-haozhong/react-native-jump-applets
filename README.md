
# react-native-applets

## Getting started

`$ npm install react-native-jump-applets --save`

### Mostly automatic installation

`$ react-native link react-native-jump-applets`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-jump-applets` and add `RNApplets.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNApplets.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.hozan.applets.RNAppletsPackage;` to the imports at the top of the file
  - Add `new RNAppletsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-jump-applets'
  	project(':react-native-jump-applets').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-jump-applets/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-jump-applets')
  	```


## Usage
```javascript
import * as RNApplets from 'react-native-jump-applets'

// TODO: What to do with the module?
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { Component } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  Text,
  TouchableOpacity
} from 'react-native';

import * as RNApplets from 'react-native-jump-applets'

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {

    }
  }
  componentDidMount() {
    RNApplets.registerApp('appid', (res) => {
      console.log('register==' + JSON.stringify(res));
    })
  }
  render() {
    return (
      <SafeAreaView>
        <ScrollView
          contentInsetAdjustmentBehavior="automatic"
          style={styles.root}>
          <TouchableOpacity onPress={() => {
            RNApplets.launchMini({
              userName: 'gh_xxx',
              path: '/pages/minipro/minipro?xx=&xx=',
              miniProgramType: 0 //0-正式版 1-开发版 2-体验版 
            }, (res) => {
              console.log('launchMini==' + JSON.stringify(res));
            })
            // RNApplets.openWXApp(() => { })
          }}>
            <Text>跳转微信小程序</Text>
          </TouchableOpacity>
        </ScrollView>
      </SafeAreaView>
    )
  }
}

const styles = StyleSheet.create({
  root: {
    flex: 1,
    backgroundColor: '#fff'
  }
})

  