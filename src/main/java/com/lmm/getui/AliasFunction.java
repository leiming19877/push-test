package com.lmm.getui;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.http.IGtPush;

public class AliasFunction {
   //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
   static String appId = "AiNHcCiJZT8Qq3GFdWCtU4";
   static String appkey = "fL9bHm48WH6q5gg1oP1S36";
   static String mastersecret = "5vjiwMEaij5VvYf7VhlGM4";
   static String CID = "832669e8c983fe5227c0b6896114a300";
   static String host = "http://sdk.open.api.igexin.com/apiex.htm";
   static String Alias = "leiming";

   public static void main(String[] args) throws Exception {
      IGtPush push = new IGtPush(host, appkey, mastersecret);

      IAliasResult bindSCid = push.bindAlias(appId, Alias, CID);
      System.out.println("绑定结果：" + bindSCid.getResult() + "错误码:" + bindSCid.getErrorMsg());

   }
}