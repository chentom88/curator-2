package io.pivotal.hackday.ccapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import events.ContainerMetric;
import io.pivotal.hackday.domain.AppCatalogItem;
import io.pivotal.hackday.uaa.UAAUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pivotal on 5/6/16.
 */
@Component
public class AppService {

    @Value("${uaa.host}")
    private String uaaHost;

    @Value("${uaa.user}")
    private String uaaUser;

    @Value("${uaa.secret}")
    private String uaaSecret;

    @Value("https://api.${api.host}")
    private String apiHost;

    private String token;

    private Map<String, AppCatalogItem> catalogItems;

    public AppService() {
        catalogItems = new ConcurrentHashMap<>();
    }

    public void catalogApplication(ContainerMetric containerMetric) throws Exception {
        String appKey = containerMetric.applicationId + "/" + containerMetric.instanceIndex;

        new Thread(() -> {
            String state = "";
            Long uptime = 0L;
            String appName = "";

            try {
                JsonObject instanceData = getAppInstanceState(containerMetric.applicationId, containerMetric.instanceIndex);
                state = instanceData.get("state").getAsString();
                uptime = instanceData.get("uptime").getAsLong();
                appName = getAppName(containerMetric.applicationId);
            } catch (Exception e) {
                token = UAAUtil.getToken(uaaHost, uaaUser, uaaSecret);

                try {
                    JsonObject instanceData = getAppInstanceState(containerMetric.applicationId, containerMetric.instanceIndex);
                    state = instanceData.get("state").getAsString();
                    uptime = instanceData.get("uptime").getAsLong();
                    appName = getAppName(containerMetric.applicationId);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            AppCatalogItem item;

            if (catalogItems.containsKey(appKey)) {
                item = catalogItems.get(appKey);

                item.setDiskBytes(containerMetric.diskBytes);
                item.setCpuPercentage(containerMetric.cpuPercentage);
                item.setMemoryBytes(containerMetric.memoryBytes);
                item.setState(state);
                item.setUptime(uptime);
            } else {
                item = new AppCatalogItem(appKey, appName, containerMetric.instanceIndex,
                        containerMetric.cpuPercentage, containerMetric.memoryBytes, containerMetric.diskBytes, uptime, state);
            }

            catalogItems.put(appKey, item);

        }).start();
    }

    public String getAppName(String appId) throws Exception {
        String token = getToken();
        URL apiUrl = new URL(apiHost + "/v2/apps/" + appId + "/summary");
        HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setUseCaches(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Authorization", "bearer " + token);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;

        String appName = null;
        while ((line = in.readLine()) != null) {
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(line);
            if (je.isJsonObject()) {
                JsonObject appData = je.getAsJsonObject();
                appName = appData.get("name").getAsString();
            }
        }
        in.close();
        return appName;
    }

    private JsonObject getAppInstanceState(String appId, Integer index) throws Exception {
        String token = getToken();
        URL apiUrl = new URL(apiHost + "/v2/apps/" + appId + "/instances");
        HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setUseCaches(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Authorization", "bearer " + token);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;

        JsonObject instanceData = null;
        while ((line = in.readLine()) != null) {
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(line);
            if (je.isJsonObject()) {
                JsonObject appData = je.getAsJsonObject();
                instanceData = appData.get(index.toString()).getAsJsonObject();
            }
        }
        in.close();
        return instanceData;
    }

    public List<AppCatalogItem> getAppCatalog() {
        return new ArrayList<>(catalogItems.values());
    }

    private String getToken() {
        if(token == null || token.length() <= 0) {
            token = UAAUtil.getToken(uaaHost, uaaUser, uaaSecret);
        }

        return token;
    }
}
