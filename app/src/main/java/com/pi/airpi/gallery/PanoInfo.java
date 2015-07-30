package com.pi.airpi.gallery;


/**
 * Created by LC on 2015/7/29.
 */
public class PanoInfo {
    private long id;
    private String panoName;
    private String panoPath;
    private int status;
    private String createdTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPanoName() {
        return panoName;
    }

    public void setPanoName(String panoName) {
        this.panoName = panoName;
    }

    public String getPanoPath() {
        return panoPath;
    }

    public void setPanoPath(String panoPath) {
        this.panoPath = panoPath;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PanoInfo)) return false;

        PanoInfo that = (PanoInfo) o;

        if (id != that.id){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
