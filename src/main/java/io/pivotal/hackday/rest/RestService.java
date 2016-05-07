package io.pivotal.hackday.rest;

import io.pivotal.hackday.domain.CloudFoundryInfo;
import io.pivotal.hackday.domain.OpsManagerInfo;
import io.pivotal.hackday.domain.Product;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pivotal on 5/6/16.
 */

@Component
public class RestService {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${opsMan.user}")
    private String opsManUser;

    @Value("${opsMan.password}")
    private String opsManPassword;

    @Value("${api.host}")
    private String host;


    public CloudFoundryInfo getCFInfo(){
        return restTemplate.getForObject("http://api."+host+"/v2/info", CloudFoundryInfo.class);
    }

    public OpsManagerInfo getOpsManagerInfo(){
        try {
            String plainCreds = opsManUser + ":" + opsManPassword;
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<OpsManagerInfo> response = restTemplate.exchange("https://pcf."+host+"/api/installation_settings", HttpMethod.GET, request, OpsManagerInfo.class);
            return response.getBody();
        }catch(Exception e){
            OpsManagerInfo info = new OpsManagerInfo();
            Product product = new Product();
            product.setInstallation_name("Please set opsman configuration in system.properties");
            info.getProducts().add(product);
            return info;
        }
    }


}
