package io.pivotal.hackday.domain;

/**
 * Created by pivotal on 5/6/16.
 */
public class AppCatalogItem {
    private String guid;
    private String name;
    private Integer instanceIndex;
    private Double cpuPercentage;
    private Long memoryBytes;
    private Long diskBytes;

    public AppCatalogItem(String guid, String name, Integer instanceIndex, Double cpuPercentage, Long memoryBytes, Long diskBytes, Long uptime, String state) {
        this.guid = guid;
        this.name = name;
        this.instanceIndex = instanceIndex;
        this.cpuPercentage = cpuPercentage;
        this.memoryBytes = memoryBytes;
        this.diskBytes = diskBytes;
        this.uptime = uptime;
        this.state = state;
    }

    private Long uptime;

    private String state;

    @Override
    public String toString() {
        return "AppCatalogItem{" +
                "guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                ", instanceIndex=" + instanceIndex +
                ", cpuPercentage=" + cpuPercentage +
                ", memoryBytes=" + memoryBytes +
                ", diskBytes=" + diskBytes +
                ", uptime=" + uptime +
                ", state='" + state + '\'' +
                '}';
    }

    public Long getUptime() {
        return uptime;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInstanceIndex() {
        return instanceIndex;
    }

    public void setInstanceIndex(Integer instanceIndex) {
        this.instanceIndex = instanceIndex;
    }

    public Double getCpuPercentage() {
        return cpuPercentage;
    }

    public void setCpuPercentage(Double cpuPercentage) {
        this.cpuPercentage = cpuPercentage;
    }

    public Long getMemoryBytes() {
        return memoryBytes;
    }

    public void setMemoryBytes(Long memoryBytes) {
        this.memoryBytes = memoryBytes;
    }

    public Long getDiskBytes() {
        return diskBytes;
    }

    public void setDiskBytes(Long diskBytes) {
        this.diskBytes = diskBytes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
