
# react-native-applets

## Getting started

`$ npm install react-native-applets --save`

### Mostly automatic installation

`$ react-native link react-native-applets`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-applets` and add `RNApplets.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNApplets.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.hozan.applets.RNAppletsPackage;` to the imports at the top of the file
  - Add `new RNAppletsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-applets'
  	project(':react-native-applets').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-applets/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-applets')
  	```


## Usage
```javascript
import RNApplets from 'react-native-applets';

// TODO: What to do with the module?
RNApplets;
```
  