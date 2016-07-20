package com.shawn.server.wechat.common;

import javax.servlet.http.HttpServletRequest;

import org.sword.wechat4j.WechatSupport;

public abstract class NativeDispatcher extends WechatSupport {
	public NativeDispatcher(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 文本消息处理Msgtype=text
	 */
	protected abstract void onText();

	/**
	 * 图像消息Msgtype=image
	 */
	protected abstract void onImage();

	/**
	 * 语音消息 Msgtype=voice
	 */
	protected abstract void onVoice();

	/**
	 * 视频 消息Msgtype=video
	 */
	protected abstract void onVideo();

	/**
	 * 小视频 消息Msgtype=shortvideo
	 */
	protected abstract void onShortVideo();

	/**
	 * 地理位置消息Msgtype=location
	 */
	protected abstract void onLocation();

	/**
	 * 链接消息Msgtype=link
	 */
	protected abstract void onLink();

	/**
	 * 未知消息类型的错误处理逻辑，不需要处理则空方法即可
	 */
	protected abstract void onUnknown();

	/**
	 * click点击事件处理event=location
	 */
	protected abstract void click();

	/**
	 * subscribe关注事件处理
	 */
	protected abstract void subscribe();

	/**
	 * unSubscribe取消关注事件处理
	 */
	protected abstract void unSubscribe();

	/**
	 * scan事件处理
	 */
	protected abstract void scan();

	/**
	 * location事件处理event=location
	 */
	protected abstract void location();

	/**
	 * view 事件处理event=location
	 */
	protected abstract void view();

	/**
	 * 模板消息发送回调
	 */
	protected abstract void templateMsgCallback();

	/**
	 * 扫码推事件
	 */
	protected abstract void scanCodePush();

	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件
	 */
	protected abstract void scanCodeWaitMsg();

	/**
	 * 弹出系统拍照发图的事件
	 */
	protected abstract void picSysPhoto();

	/**
	 * 弹出拍照或者相册发图的事件
	 */
	protected abstract void picPhotoOrAlbum();

	/**
	 * 扫码推事件且弹出“消息接收中”提示框的事件
	 */
	protected abstract void picWeixin();

	/**
	 * 弹出地理位置选择器的事件
	 */
	protected abstract void locationSelect();

	/**
	 * 客服人员有接入会话
	 */
	protected abstract void kfCreateSession();

	/**
	 * 客服人员有关闭会话
	 */
	protected abstract void kfCloseSession();

	/**
	 * 客服人员有转接会话
	 */
	protected abstract void kfSwitchSession();
}
