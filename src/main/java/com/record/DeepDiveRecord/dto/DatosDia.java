package com.record.DeepDiveRecord.dto;

public class DatosDia {
    private String fecha;
    private String tAgua;
    private EstadoCielo estadoCielo;

    public DatosDia(String fecha, String tAgua, EstadoCielo estadoCielo) {
        this.fecha = fecha;
        this.tAgua = tAgua;
        this.estadoCielo = estadoCielo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String gettAgua() {
        return tAgua;
    }

    public void settAgua(String tAgua) {
        this.tAgua = tAgua;
    }

    public EstadoCielo getEstadoCielo() {
        return estadoCielo;
    }

    public void setEstadoCielo(EstadoCielo estadoCielo) {
        this.estadoCielo = estadoCielo;
    }
}
