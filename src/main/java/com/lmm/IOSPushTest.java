/**
 * 
 */
package com.lmm;

import javapns.devices.Device;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;

/**
 * @author leimi
 *
 */
public class IOSPushTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// 从客户端获取的deviceToken，在此为了测试简单，写固定的一个测试设备标识。
			String deviceToken = "df779eda 73258894 5882ec78 3ac7b254 6ebc66fe fa295924 440d34ad 6505f8c4";
			System.out.println("Push Start deviceToken:" + deviceToken);
			// 定义消息模式
			PushNotificationPayload payLoad = new PushNotificationPayload();
			payLoad.addAlert("this is test!");
			payLoad.addBadge(1);// 消息推送标记数，小红圈中显示的数字。
			payLoad.addSound("default");
			// 注册deviceToken
			PushNotificationManager pushManager = new PushNotificationManager();

			pushManager.addDevice("iPhone", deviceToken);
			// 连接APNS
			String host = "gateway.sandbox.push.apple.com";
			// String host = "gateway.push.apple.com";
			int port = 2195;
			String certificatePath = "c:/PushTest.p12";// 前面生成的用于JAVA后台连接APNS服务的*.p12文件位置
			String certificatePassword = "123456";// p12文件密码。
			AppleNotificationServer aps = new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false);
			
			pushManager.initializeConnection(aps);
			// 发送推送
			Device client = pushManager.getDevice("iPhone");
			System.out.println("推送消息: " + client.getToken() + "\n" + payLoad.toString() + " ");
			pushManager.sendNotification(client, payLoad);
			// 停止连接APNS
			pushManager.stopConnection();
			// 删除deviceToken
			pushManager.removeDevice("iPhone");
			System.out.println("Push End");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
