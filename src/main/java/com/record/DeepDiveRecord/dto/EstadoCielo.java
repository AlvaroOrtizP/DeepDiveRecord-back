package com.record.DeepDiveRecord.dto;

public class EstadoCielo {
    private String f1;
    private String descripcion1;
    private String f2;
    private String descripcion2;

    public EstadoCielo(String f1, String descripcion1, String f2, String descripcion2) {
        this.f1 = f1;
        this.descripcion1 = descripcion1;
        this.f2 = f2;
        this.descripcion2 = descripcion2;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getDescripcion1() {
        return descripcion1;
    }

    public void setDescripcion1(String descripcion1) {
        this.descripcion1 = descripcion1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }
}
