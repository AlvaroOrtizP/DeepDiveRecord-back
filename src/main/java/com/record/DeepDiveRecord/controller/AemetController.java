package com.record.DeepDiveRecord.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.record.DeepDiveRecord.dto.DatosDia;
import com.record.DeepDiveRecord.dto.EstadoCielo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/aemet")
public class AemetController {

    @GetMapping("/obtener-datos")
    public List<DatosDia> obtenerDatosAemet() {
        List<DatosDia> datosPorDia = new ArrayList<>();

        try {
            String url = "https://www.aemet.es/xml/playas/play_v2_3900602.xml";
            Document doc = Jsoup.connect(url).get();

            Elements dias = doc.select("dia");
            for (Element dia : dias) {
                String fecha = dia.attr("fecha");
                String tAgua = dia.selectFirst("t_agua").attr("valor1");
                Element estadoCielo = dia.selectFirst("estado_cielo");
                EstadoCielo estadoCieloData = new EstadoCielo(
                        estadoCielo.attr("f1"),
                        estadoCielo.attr("descripcion1"),
                        estadoCielo.attr("f2"),
                        estadoCielo.attr("descripcion2")
                );
                DatosDia datosDia = new DatosDia(fecha, tAgua, estadoCieloData);
                datosPorDia.add(datosDia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datosPorDia;
    }

    @GetMapping("/guardar-json")
    public void guardarJson() {
        List<DatosDia> datos = obtenerDatosAemet();
        String nombreArchivo = "datos_aemet.json";
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            file.write(new ObjectMapper().writeValueAsString(datos));
            file.flush();
            System.out.println("Datos guardados correctamente en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}