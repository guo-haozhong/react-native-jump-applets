
import { DeviceEventEmitter,NativeModules } from 'react-native';
import { EventEmitter } from 'events';

const { RNApplets } = NativeModules;
const emitter = new EventEmitter();

DeviceEventEmitter.addListener('WeChat_Resp', resp => {
  emitter.emit('wechat', resp);
});

/**
 * 注册appid
 * @method registerApp
 * @param {String} appid
 * @param {Callback} callback
 */
export const registerApp = RNApplets.registerApp

/**
 * 打开小程序
 * @method launchMini
 * @param {userName:"小程序原始id",path:"拉起小程序页面的可带参路径",miniProgramType:0}--miniProgramType 拉起小程序的类型. 0-正式版 1-开发版 2-体验版 
 * @param {Callback} callback
 */
export const launchMini=RNApplets.launchMini
/**
 * 是否注册appid
 * @method isWXAppInstalled
 * @param {Callback} callback
 */
export const isWXAppInstalled=RNApplets.isWXAppInstalled

/**
 * 打开微信
 * @method openWXApp
 * @param {Callback} callback
 */
export const openWXApp = RNApplets.openWXApp

export default RNApplets;
