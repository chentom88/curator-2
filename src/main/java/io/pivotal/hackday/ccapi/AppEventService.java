package io.pivotal.hackday.ccapi;

import events.LogMessage;
import io.pivotal.hackday.domain.AppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pivotal on 5/6/16.
 */
@Component
public class AppEventService {
    @Autowired
    private AppService appService;

    private Map<String, AppEvent> items;

    public AppEventService() {
        items = new ConcurrentHashMap<>();
    }

    public void addAppEvent(LogMessage appEvent) {
        new Thread(() -> {
            try {
                String appName = appService.getAppName(appEvent.app_id);
                items.put(appEvent.app_id, new AppEvent(appName, appEvent.message.utf8()));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public List<AppEvent> getAppEvents() {
        return new ArrayList<>(items.values());
    }
}
