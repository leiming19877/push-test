package com.lmm.getui;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
public class MyJuniorPushDemo {
	private static String appId = "AiNHcCiJZT8Qq3GFdWCtU4";
    private static String appKey = "fL9bHm48WH6q5gg1oP1S36";
    private static String masterSecret = "xS60WE75lz5OXapODZ6es7";
    static String devicetoken = "84A392CC0C8FA4058E38A4CA011FA249C0BAE43D599D0CAE2CFE6959EA36FDE8";//iOS设备唯一标识，由苹果那边生成
    static String url = "http://sdk.open.api.igexin.com/apiex.htm";
       public static void apnpush() throws Exception {
              IGtPush push = new IGtPush(url, appKey, masterSecret);  
              APNTemplate t = new APNTemplate();
              APNPayload apnpayload = new APNPayload();
              apnpayload.setSound("");
              //apn高级推送
              APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
              ////通知文本消息标题
              alertMsg.setTitle("aaaaaa");
              //通知文本消息字符串
              alertMsg.setBody("bbbb");
              //对于标题指定执行按钮所使用的Localizable.strings,仅支持IOS8.2以上版本
              alertMsg.setTitleLocKey("ccccc");
              //指定执行按钮所使用的Localizable.strings
              alertMsg.setActionLocKey("ddddd");
              apnpayload.setAlertMsg(alertMsg);

              t.setAPNInfo(apnpayload);
              SingleMessage sm = new SingleMessage();
              sm.setData(t);
              IPushResult ret0 = push.pushAPNMessageToSingle(appId, devicetoken, sm);
              System.out.println(ret0.getResponse());

       }

       public static void main(String[] args) throws Exception {
              apnpush();
       }
}