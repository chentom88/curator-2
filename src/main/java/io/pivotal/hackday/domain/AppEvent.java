package io.pivotal.hackday.domain;

/**
 * Created by pivotal on 5/6/16.
 */
public class AppEvent {
    private String appId;
    private String description;

    public AppEvent(String appId, String description) {
        this.appId = appId;
        this.description = description;
    }

    @Override
    public String toString() {
        return "AppEvent{" +
                "appId='" + appId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
