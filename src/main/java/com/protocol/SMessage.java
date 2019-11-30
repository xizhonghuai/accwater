package com.protocol;

import com.protocol.dbaccess.DataServer;
import com.protocol.entity.AlarmTel;
import com.protocol.entity.DevSite;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName SMessage
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-15 14:15
 * @Version 1.0
 */
public class SMessage {

    private Logger logger = Logger.getLogger(SMessage.class);

    private String enterpriseId;
    private String loginName;
    private String password;
    private String url;

    public SMessage(String enterpriseId, String loginName, String password, String url) {
        this.enterpriseId = enterpriseId;
        this.loginName = loginName;
        this.password = password;
        this.url = url;
    }

    public SMessage() {
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SMessage{" +
                "enterpriseId='" + enterpriseId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public void sendShortMessage(DevSite devSite, List<AlarmTel> alarmTelList, Integer water, DataServer dataServer) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日HH时mm分");
        String msg = "积水报警！\n" +
                "测站:"+devSite.getName()+"\n" +
                "编号:"+devSite.getCode()+"\n" +
                "水深:"+water+"cm\n" +
                "时间:"+dateFormat.format(new Date());

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0, len = alarmTelList.size(); i < len; i++) {

                    try {

                        AlarmTel alarmTel = new AlarmTel();
                        alarmTel = alarmTelList.get(i);
                        alarmTel.setContent(msg);
                        alarmTel.setCreated(new Date());

                        boolean sendFlag = sendTask(alarmTel.getAlarmcalluser_phone(), alarmTel.getContent());


                        if (sendFlag){
                            alarmTel.setStatus(1);
                        }else {
                            alarmTel.setStatus(0);
                        }

                        dataServer.insertWaringRecord(alarmTel);

                    } catch (Exception e) {
                        logger.info("短信发送异常:");
                        logger.info(e.toString());
                    }
                }
            }
        }).start();
    }


    private boolean sendTask(String tel, String msg)  {

        try
        {

            Map<String, Object> par = new HashMap<>();
            String str = unicodeToUtf8(msg);
            par.put("enterpriseID", this.enterpriseId);
            par.put("loginName", this.loginName);
            par.put("password", encryption(this.password));
            par.put("content", str);
            par.put("mobiles", tel);
            String ret = postCallStr(this.url, null, par);
            logger.info("短信发送响应信息:"+ret);
            if (ret.contains("<Result>0</Result>")) {

                return true;
            }


        }catch (Exception e){

            //
        }


        return false;


    }


    private String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }


    private String postCallStr(String url, String content_type, Map<String, Object> params)
            throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        Set<Map.Entry<String, Object>> sets = params.entrySet();
        Iterator<Map.Entry<String, Object>> it = sets.iterator();

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


        while (it.hasNext()) {

            Map.Entry<String, Object> entry = it.next();
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        //    post.addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
//	        post.addHeader("Cookie", THParams.COOKIE);
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

        String conResult = "";
        HttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            // 读取返回数据
            conResult = (EntityUtils.toString(response.getEntity()));
        }
        return conResult;
    }

    private String unicodeToUtf8(String s) throws Exception {
        return new String(s.getBytes("utf-8"), "utf-8");
    }
}
