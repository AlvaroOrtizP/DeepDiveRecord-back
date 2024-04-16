package com.record.DeepDiveRecord.dto.request;

public class  WebWeatherScraperRequest {
    private String idWindwuru;
    private String idAemet;
    private String lugar;

    public WebWeatherScraperRequest(String idWindwuru, String idAemet, String lugar) {
        this.idWindwuru = idWindwuru;
        this.idAemet = idAemet;
        this.lugar = lugar;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getIdWindwuru() {
        return idWindwuru;
    }

    public void setIdWindwuru(String idWindwuru) {
        this.idWindwuru = idWindwuru;
    }

    public String getIdAemet() {
        return idAemet;
    }

    public void setIdAemet(String idAemet) {
        this.idAemet = idAemet;
    }
}
