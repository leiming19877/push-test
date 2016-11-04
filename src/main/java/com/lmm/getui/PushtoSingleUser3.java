package com.lmm.getui;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class PushtoSingleUser3 {
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	private static String appId = "1PRoXK5wbx8j2cvJYhe277";
    private static String appKey = "g6XWFKISYl70AkpDGd3BB2";
    private static String masterSecret = "wM6XvozFxZ8NQONLimUIs7";

    //static String CID = "302176718d46ca72c3f62fa4b39dc126";
  //别名推送方式
   // static String Alias = "";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    public static void main(String[] args) throws Exception {
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        TransmissionTemplate template = transmissionTemplateDemo("标题","测试内容",new HashMap<String,Object>());
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
   
    public static TransmissionTemplate transmissionTemplateDemo(String title,String content,Map<String,Object> busMap) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
       
       // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        //template.setTransmissionContent("请输入需要透传的内容");

        busMap.put("title", title);
        busMap.put("content",content);
        ObjectMapper mapper = new ObjectMapper();
        String msgContent = "";
        try {
        	msgContent = mapper.writeValueAsString(busMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        template.setTransmissionContent(msgContent);
        APNPayload payload = new APNPayload();
        //payload.setBadge(1);
        payload.setContentAvailable(1);
        payload.setSound("default");
        payload.setCategory("$由客户端定义");
        //简单模式APNPayload.SimpleMsg 
        //payload.setAlertMsg(new APNPayload.SimpleAlertMsg("leiming"));
        //字典模式使用下者
        payload.setAlertMsg(getDictionaryAlertMsg(title,content,busMap));
        template.setAPNInfo(payload);
        return template;
    }
    private static AlertMsg getDictionaryAlertMsg(String title,String content,Map<String,Object> busMap){
    	AlertMsg alertMsg = new AlertMsg();  
        alertMsg.setPayload(busMap);
        alertMsg.setBody(content);
        alertMsg.setActionLocKey("ActionLockey");
        alertMsg.setLocKey("LocKey");
        alertMsg.addLocArg("loc-args");
        alertMsg.setLaunchImage("launch-image");
        // IOS8.2以上版本支持
        alertMsg.setTitle(title);
        alertMsg.setTitleLocKey("TitleLocKey");
        alertMsg.addTitleLocArg("TitleLocArg");

        return alertMsg;
    }
    
   private static  class AlertMsg  extends APNPayload.DictionaryAlertMsg{
    	private Map<String,Object> payload;
    	public void setPayload(Map<String,Object> map){
    		this.payload = map;
    	}

		@Override
		public Object getAlertMsg() {
			Map<String,Object> map = (Map<String, Object>) super.getAlertMsg();
			if(payload != null){
				map.put("payload",payload);
			}
			return map;
		};
    	
    	
    };
}