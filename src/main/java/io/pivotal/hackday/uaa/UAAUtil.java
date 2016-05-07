package io.pivotal.hackday.uaa;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by pivotal on 5/6/16.
 */
public class UAAUtil {

    public static String getToken(String uaahost, String userName, String password) {
        String token = "";

        try{
            SSLUtilities.trustAllHostnames();
            SSLUtilities.trustAllHttpsCertificates();

            URL uaaLoginUrl = new URL("https://"+uaahost+"/oauth/token");
            HttpURLConnection conn = (HttpURLConnection) uaaLoginUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setUseCaches (true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Authorization", "Basic "+ Base64.getUrlEncoder().encodeToString("cf:".getBytes()));

            Map<String, String> data = new HashMap<String, String>();

            data.put("username", userName);
            data.put("password", password);
            data.put("grant_type", "password");
            data.put("scope", "");

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());

            Set<String> keys = data.keySet();
            Iterator<String> keyIter = keys.iterator();
            String content = "";
            for(int i=0; keyIter.hasNext(); i++) {
                Object key = keyIter.next();
                if(i!=0) {
                    content += "&";
                }
                content += key + "=" + URLEncoder.encode(data.get(key), "UTF-8");
            }

            out.writeBytes(content);
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";

            while((line=in.readLine())!=null) {
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(line);
                if(je.isJsonObject()){
                    JsonObject jo =  je.getAsJsonObject();
                    token = jo.get("access_token").getAsString();
                }
            }
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return token;
    }
}
