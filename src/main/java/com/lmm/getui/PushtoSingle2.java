package com.lmm.getui;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.APNPayload.DictionaryAlertMsg;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;

public class PushtoSingle2 {
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	private static String appId = "1PRoXK5wbx8j2cvJYhe277";
    private static String appKey = "g6XWFKISYl70AkpDGd3BB2";
    private static String masterSecret = "wM6XvozFxZ8NQONLimUIs7";

    static String CID = "317b6938447767ee761aa41cc42bc1a4";
  //别名推送方式
   // static String Alias = "";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    public static void main(String[] args) throws Exception {
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        NotificationTemplate template = notificationTemplateDemo("测试标题","测试内容");
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(appId);
        //target.setClientId(CID);
        target.setAlias("leiming");
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }
    public static NotificationTemplate notificationTemplateDemo(String title,String content) {
    	NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 设置通知栏标题与内容
        template.setTitle(title);
        template.setText(content);
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标，填写图标URL地址
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        
        APNPayload load = new APNPayload();
        load.setBadge(1);  //将应用icon上显示的数字设为1
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，如10，效果和setBadge一样，
        //应用icon上显示指定数字。不能和setBadge同时使用
        //setAutoBadge("+1");
        load.setContentAvailable(1);
        load.setSound("default");
        load.setCategory("$由客户端定义");
        //AlertMsg msg = new SimpleAlertMsg("hello crm NotificationTemplate！");
        //load.setAlertMsg(msg);
        //load.setContentAvailable(1);
        DictionaryAlertMsg alertMsg = new DictionaryAlertMsg();
        alertMsg.setTitle( title );
        alertMsg.setBody( content );
        load.setAlertMsg( alertMsg );
        template.setAPNInfo( load );
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        //template.setTransmissionContent("请输入您要透传的内容");
        return template;
    }
}