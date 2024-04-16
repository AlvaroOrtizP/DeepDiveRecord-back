package com.record.DeepDiveRecord.dto.request;
import java.util.List;
public class DailyDivingRequest {
    private String site;
    private String day;
    private String month;
    private String year;
    private String startTime;
    private String endTime;
    private List<FishingRequest> fishingRequestList;
    private String notas;

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notes) {
        this.notas = notes;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<FishingRequest> getFishingRequestList() {
        return fishingRequestList;
    }

    public void setFishingRequestList(List<FishingRequest> fishingRequestList) {
        this.fishingRequestList = fishingRequestList;
    }
}
