package com.record.DeepDiveRecord.dto.responses;
import java.util.List;
public class WebWeatherScraperResponse {
    private List<String> erros;

    public WebWeatherScraperResponse(List<String> erros) {
        this.erros = erros;
    }
    public WebWeatherScraperResponse() {

    }
    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    public boolean isOk(){
        return this.erros.isEmpty();
    }

}
