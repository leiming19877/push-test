package com.lmm.getui;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;

public class AliasFunction1 {
   //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	static String appId = "1PRoXK5wbx8j2cvJYhe277";
   static String appkey = "g6XWFKISYl70AkpDGd3BB2";
   static String mastersecret = "wM6XvozFxZ8NQONLimUIs7";
   static String CID = "302176718d46ca72c3f62fa4b39dc126";
   static String host = "http://sdk.open.api.igexin.com/apiex.htm";

   public static void main(String[] args) {
      List<Target> Lcids = new ArrayList<Target>();
      Target target1 = new Target();
      Target target2 = new Target();
      target1.setClientId("0350dfee5b1380a55f12c9cc422e798c");
      target1.setAlias("leiming");
      target2.setClientId("3830740f714a3d056ae13a12e29d7058");
      target2.setAlias("leiming");
      Lcids.add(target1);
      Lcids.add(target2);
      IGtPush push = new IGtPush(host, appkey, mastersecret);
      IAliasResult bindLCid = push.bindAlias(appId, Lcids);
      System.out.println(bindLCid.getResult());
      System.out.println(bindLCid.getErrorMsg());
   }
}
// 注：只要有一个cid绑定成功，getResult返回结果就为true