package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;

import com.record.DeepDiveRecord.domain.model.dto.port.historic.CombinedItemDto;
import com.record.DeepDiveRecord.domain.port.HistoricPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.*;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.*;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.configuration_data.ConfigurationDataRepository;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.dive_day.DiveDayRepository;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.fish.FishRepository;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.fishing.FishingRepository;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.geographical_location.GeographicalLocationRepository;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.tide_table.TideTableRepository;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.wind_conditions.WindConditionsRepository;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Component
public class HistoricRepositoryImpl implements HistoricPort {
    private static final Logger log = LoggerFactory.getLogger(HistoricRepositoryImpl.class);
    @Autowired
    private WindConditionsRepository windWuruRepository;
    @Autowired
    private TideTableRepository tideTableRepository;
    @Autowired
    private GeographicalLocationRepository geographicalLocationRepository;
    @Autowired
    private FishRepository fishRepository;
    @Autowired
    private FishingRepository fishingRepository;
    @Autowired
    private DiveDayRepository diveDayRepository;
    @Autowired
    private ConfigurationDataRepository configurationDataRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private WindConditionsMapper windConditionsMapper;
    @Autowired
    private TideTableMapper tideTableMapper;
    @Autowired
    private GeograficLocationMapper geograficLocationMapper;
    @Autowired
    private FishMapper fishMapper;
    @Autowired
    private FishingMapper fishingMapper;
    @Autowired
    private DiveDayMapper diveDayMapper;

    @Autowired
    private ConfigurationDataMapper configurationDataMapper;

    @Override
    public CombinedItemDto read() {
        log.info("Initializing CombinedItem with data from repositories");
        CombinedItemDto combinedItem = new CombinedItemDto();
        combinedItem.setWindConditionsEntityList(windWuruRepository.findAll());
        combinedItem.setTideTableEntityList(tideTableRepository.findAll());
        combinedItem.setGeographicalLocationEntityList(geographicalLocationRepository.findAll());
        combinedItem.setFishEntityList(fishRepository.findAll());
        combinedItem.setFishingEntityList(fishingRepository.findAll());
        combinedItem.setDiveDayEntityList(diveDayRepository.findAll());
        combinedItem.setConfigurationDataEntityList(configurationDataRepository.findAll());

        return combinedItem;
    }

    @Override
    public int process(CombinedItemDto input) throws IOException {
        log.info("Processing CombinedItem: " + input);
        return exportCombinedItemToExcel(input);
    }

    @Override
    public File getLasthistoric() {
        File outputDirectory = new File("src/main/resources/output");

        // Validar si el directorio existe y es un directorio
        if (!outputDirectory.exists() || !outputDirectory.isDirectory()) {
            log.warn("El directorio no existe o no es un directorio: " + outputDirectory.getAbsolutePath());
            return null;
        }

        // Filtrar los archivos que coincidan con el patrón "combined_dataYYYY_DDD.xlsx"
        File[] archivos = outputDirectory.listFiles((dir, name) -> name.matches("combined_data\\d{4}_\\d{1,3}\\.xlsx"));

        if (archivos == null || archivos.length == 0) {
            log.warn("No se encontraron archivos en el directorio: " + outputDirectory.getAbsolutePath());
            return null;
        }

        // Ordenar los archivos para obtener el más reciente
        File ultimoArchivo = Arrays.stream(archivos)
                .max(Comparator.comparingInt(file -> {
                    String nombre = file.getName();
                    try {
                        // Extraer el día del año (DDD) del nombre del archivo
                        String[] partes = nombre.substring(13, nombre.lastIndexOf('.')).split("_");
                        return Integer.parseInt(partes[1]); // DDD
                    } catch (Exception e) {
                        log.error("Error al procesar el archivo: " + nombre, e);
                        return -1;
                    }
                }))
                .orElse(null);

        if (ultimoArchivo != null) {
            log.info("Último archivo encontrado: " + ultimoArchivo.getAbsolutePath());
        } else {
            log.warn("No se pudo determinar el último archivo.");
        }

        return ultimoArchivo;
    }

    @Override
    public int saveHistoric(File file) throws IOException {
        log.info("Procesando archivo: " + file.getAbsolutePath());
        int totalRegistrosInsertados = 0;

        try (FileInputStream fis = new FileInputStream(file)) {
            Workbook workbook = WorkbookFactory.create(fis); // Carga el archivo Excel

            // Procesar cada hoja correspondiente a una tabla
            totalRegistrosInsertados += procesarHoja(workbook.getSheet("Wind Conditions"), "WindConditionsEntity");
            totalRegistrosInsertados += procesarHoja(workbook.getSheet("Tide Table"), "TideTableEntity");
            totalRegistrosInsertados += procesarHoja(workbook.getSheet("Geographical Locations"), "GeographicalLocationEntity");
            totalRegistrosInsertados += procesarHoja(workbook.getSheet("Fish"), "FishEntity");
            totalRegistrosInsertados += procesarHoja(workbook.getSheet("Dive Day"), "DiveDayEntity");
            totalRegistrosInsertados += procesarHoja(workbook.getSheet("Fishing"), "FishingEntity");
            totalRegistrosInsertados += procesarHoja(workbook.getSheet("Configuration Data"), "ConfigurationData");

            log.info("Total de registros insertados: " + totalRegistrosInsertados);

        } catch (IOException e) {
            log.error("Error al leer el archivo Excel: " + file.getAbsolutePath(), e);
        } catch (Exception e) {
            log.error("Error durante la inserción de datos.", e);
        } finally {
            // Eliminar el archivo después de procesarlo
            if (file.exists()) {
                boolean deleted = file.delete();
                if (deleted) {
                    log.info("El archivo " + file.getAbsolutePath() + " se eliminó correctamente.");
                } else {
                    log.error("No se pudo eliminar el archivo " + file.getAbsolutePath());
                }
            }
        }

        return totalRegistrosInsertados;
    }

    private int procesarHoja(Sheet sheet, String entityName) {
        if (sheet == null) {
            log.warn("La hoja para la entidad " + entityName + " no se encontró.");
            return 0;
        }

        log.info("Procesando hoja: " + sheet.getSheetName());
        int registrosInsertados = 0;

        List<Object> datos = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                // Saltar la fila de encabezados si es necesario
                continue;
            }

            switch (entityName) {
                case "WindConditionsEntity":
                    datos.add(windConditionsMapper.mapRowToWindConditionsEntity(row));
                    break;
                case "TideTableEntity":
                    datos.add(tideTableMapper.mapRowToTideTableEntity(row));
                    break;
                case "GeographicalLocationEntity":
                    datos.add(geograficLocationMapper.mapRowToGeographicalLocationEntity(row));
                    break;
                case "FishEntity":
                    datos.add(fishMapper.mapRowToFishEntity(row));
                    break;
                case "DiveDayEntity":
                    datos.add(diveDayMapper.mapRowToDiveDayEntity(row));
                    break;
                case "FishingEntity":
                    datos.add(fishingMapper.mapRowToFishingEntity(row));
                    break;

                case "ConfigurationData":
                    datos.add(configurationDataMapper.mapRowToConfigurationDataEntity(row));
                    break;
                default:
                    log.warn("Entidad desconocida: " + entityName);
            }
        }

        // Insertar los datos en la base de datos
        for (Object data : datos) {
            registrosInsertados += saveEntities(data); // Suponemos un método genérico para insertar
        }

        log.info("Registros insertados para " + entityName + ": " + registrosInsertados);
        return registrosInsertados;
    }

    public int saveEntities(Object data) {
        // Usar JPA o JDBC para insertar el objeto en la base de datos
        if (data instanceof WindConditionsEntity) {
            windWuruRepository.save((WindConditionsEntity) data);
        } else if (data instanceof TideTableEntity) {
            tideTableRepository.save((TideTableEntity) data);
        } else if (data instanceof GeographicalLocationEntity) {
            geographicalLocationRepository.save((GeographicalLocationEntity) data);
            System.out.println("Entidad guardada GeographicalLocationEntity" + ((GeographicalLocationEntity) data).getId());
        } else if (data instanceof FishEntity) {
            fishRepository.save((FishEntity) data);
            System.out.println("Entidad guardada FishEntity" + ((FishEntity) data).getId());
        } else if (data instanceof DiveDayEntity) {
            diveDayRepository.save((DiveDayEntity) data);
            System.out.println("Entidad guardada DiveDayEntity" + ((DiveDayEntity) data).getDiveDayId());
        } else if (data instanceof FishingEntity) {
            fishingRepository.save((FishingEntity) data);
            System.out.println("Entidad guardada " + ((FishingEntity) data).getId());
        }  else if (data instanceof ConfigurationDataEntity) {
            configurationDataRepository.save((ConfigurationDataEntity) data);
            System.out.println("Entidad guardada " + ((ConfigurationDataEntity) data).getId());
        }

        return 1; // Retorna 1 si la inserción fue exitosa
    }

    private int exportCombinedItemToExcel(CombinedItemDto combinedItem) throws IOException {
        int res = 0;
        // Crear un libro de trabajo de Excel
        try (Workbook workbook = new XSSFWorkbook()) {

            // Crear una hoja para cada entidad
            Sheet windConditionsSheet = workbook.createSheet("Wind Conditions");
            Sheet tideTableSheet = workbook.createSheet("Tide Table");
            Sheet geographicalLocationSheet = workbook.createSheet("Geographical Locations");
            Sheet fishSheet = workbook.createSheet("Fish");
            Sheet fishingSheet = workbook.createSheet("Fishing");
            Sheet diveDaySheet = workbook.createSheet("Dive Day");
            Sheet configurationData = workbook.createSheet("Configuration Data");

            // Poblamos cada hoja con los datos
            res = populateWindConditionsSheet(windConditionsSheet, combinedItem.getWindConditionsEntityList());
            res = res + populateTideTableSheet(tideTableSheet, combinedItem.getTideTableEntityList());
            res = res + populateGeographicalLocationSheet(geographicalLocationSheet, combinedItem.getGeographicalLocationEntityList());
            res = res + populateFishSheet(fishSheet, combinedItem.getFishEntityList());
            res = res + populateFishingSheet(fishingSheet, combinedItem.getFishingEntityList());
            res = res + populateDiveDaySheet(diveDaySheet, combinedItem.getDiveDayEntityList());
            res = res + populateConfigurationDataSheet(configurationData, combinedItem.getConfigurationDataEntityList());

            // Guardar el archivo en el directorio resources/output
            saveExcelFile(workbook);
        }

        return res;
    }

    private int populateConfigurationDataSheet(Sheet sheet, List<ConfigurationDataEntity> configurationDataEntityList) {
        int res = 0;
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(1).setCellValue("active");
        headerRow.createCell(2).setCellValue("id_aemet");
        headerRow.createCell(3).setCellValue("id_windwuru");


        int rowIdx = 1;
        for (ConfigurationDataEntity entity : configurationDataEntityList) {
            res++;
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(entity.getId());
            row.createCell(1).setCellValue(entity.isActive());
            row.createCell(2).setCellValue(entity.getIdAemet());
            row.createCell(3).setCellValue(entity.getIdWindwuru());

        }
        log.info("El total de los campos de ConfigurationDataEntity " + res);
        return res;
    }

    private void saveExcelFile(Workbook workbook) throws IOException {
        // Crear el directorio si no existe
        File outputDirectory = new File("src/main/resources/output");
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();  // Crea la carpeta
        }

        Calendar cal = Calendar.getInstance();
        String filename = "combined_data" + cal.get(Calendar.YEAR) + "_" + cal.get(Calendar.DAY_OF_YEAR) + ".xlsx";
        File excelFile = new File(outputDirectory, filename);

        // Guardar el archivo Excel
        try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
            workbook.write(fileOut);
            log.info("Excel file saved at: " + excelFile.getAbsolutePath());
        }

        // Enviar el archivo por correo después de guardarlo
        enviarCorreoConAdjunto(excelFile);
    }


    private void enviarCorreoConAdjunto(File excelFile) {
        try {
            // Crear el mensaje MIME
            MimeMessage message = mailSender.createMimeMessage();
            // Cargar las variables desde .env
            Dotenv dotenv = Dotenv.load();

            // Configurar el helper para enviar HTML y adjuntos
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Personalizar el correo
            helper.setTo(dotenv.get("TO_MAIL_USERNAME"));  // Destinatario
            helper.setSubject("Procesamiento Exitoso- Archivo Adjunto");

            // Contenido HTML
            String htmlContent = """
                    <html>
                        <body>
                            <h2 style="color: #333;">Reporte Diario</h2>
                            <p>Hola,</p>
                            <p>Adjunto encontrarás el archivo <b>Excel</b> generado automáticamente con los datos del día.</p>
                            <p>Para cualquier consulta, no dudes en contactarnos.</p>
                            <br>
                            <p>Saludos,</p>
                            <p><strong>Tu equipo de soporte</strong></p>
                        </body>
                    </html>
                    """;

            helper.setText(htmlContent, true);  // `true` habilita HTML

            // Adjuntar el archivo Excel
            FileSystemResource fileResource = new FileSystemResource(excelFile);
            helper.addAttachment("Reporte_Diario.xlsx", fileResource);

            // Enviar el mensaje con HTML y adjunto
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    // Métodos para poblar cada hoja de la Excel (los mismos que ya tienes)
    private int populateWindConditionsSheet(Sheet sheet, List<WindConditionsEntity> windConditions) {
        int res = 0;
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("year");
        headerRow.createCell(1).setCellValue("day_of_year");
        headerRow.createCell(2).setCellValue("time_of_day");
        headerRow.createCell(3).setCellValue("site");

        headerRow.createCell(4).setCellValue("month");
        headerRow.createCell(5).setCellValue("day");
        headerRow.createCell(6).setCellValue("wind");
        headerRow.createCell(7).setCellValue("wind_direction");

        headerRow.createCell(8).setCellValue("wind_direction_nm");
        headerRow.createCell(9).setCellValue("gusts_of_wind");
        headerRow.createCell(10).setCellValue("wave_height");
        headerRow.createCell(11).setCellValue("wave_period");

        headerRow.createCell(12).setCellValue("wave_direction");
        headerRow.createCell(13).setCellValue("wave_direction_nm");
        headerRow.createCell(14).setCellValue("earth_temperature");
        headerRow.createCell(15).setCellValue("water_temperature");

        headerRow.createCell(16).setCellValue("condition_code");
        headerRow.createCell(17).setCellValue("condition_description");

        int rowIdx = 1;
        for (WindConditionsEntity entity : windConditions) {
            res++;
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(entity.getId().getYear());
            row.createCell(1).setCellValue(entity.getId().getDayOfYear());
            row.createCell(2).setCellValue(entity.getId().getTime());
            row.createCell(3).setCellValue(entity.getId().getSite());

            row.createCell(4).setCellValue(entity.getMonth());
            row.createCell(5).setCellValue(entity.getDay());
            row.createCell(6).setCellValue(entity.getWind());
            if (entity.getWindDirection() != null) {
                row.createCell(7).setCellValue(entity.getWindDirection().doubleValue());
            }

            row.createCell(8).setCellValue(entity.getWindDirectionNm());
            row.createCell(9).setCellValue(entity.getGustsOfWind());
            row.createCell(10).setCellValue(entity.getWaveHeight());
            row.createCell(11).setCellValue(entity.getWavePeriod());

            if (entity.getWaveDirection() != null)
                row.createCell(12).setCellValue(entity.getWaveDirection());
            row.createCell(13).setCellValue(entity.getWaveDirectionNm());
            row.createCell(14).setCellValue(entity.getEarthTemperature());
            if (entity.getWaterTemperature() != null)
                row.createCell(15).setCellValue(entity.getWaterTemperature());

            if (entity.getCodeCondition() != null)
                row.createCell(16).setCellValue(entity.getCodeCondition());
            row.createCell(17).setCellValue(entity.getConditionDescription());

        }
        log.info("El total de los campos de WindConditionsEntity " + res);
        return res;
    }

    private int populateTideTableSheet(Sheet sheet, List<TideTableEntity> tideTable) {
        int res = 0;
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("day");
        headerRow.createCell(1).setCellValue("month");
        headerRow.createCell(2).setCellValue("year");
        headerRow.createCell(3).setCellValue("site");
        headerRow.createCell(4).setCellValue("moon_phase");
        headerRow.createCell(5).setCellValue("coefficient0H");
        headerRow.createCell(6).setCellValue("coefficient12H");
        headerRow.createCell(7).setCellValue("morning_high_tide_time");
        headerRow.createCell(8).setCellValue("morning_high_tide_height");
        headerRow.createCell(9).setCellValue("afternoon_high_tide_time");
        headerRow.createCell(10).setCellValue("afternoon_high_tide_height");
        headerRow.createCell(11).setCellValue("morning_low_tide_time");
        headerRow.createCell(12).setCellValue("morning_low_tide_height");
        headerRow.createCell(13).setCellValue("afternoon_low_tide_time");
        headerRow.createCell(14).setCellValue("afternoon_low_tide_height");


        int rowIdx = 1;
        for (TideTableEntity entity : tideTable) {
            res++;
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(entity.getId().getDay());
            row.createCell(1).setCellValue(entity.getId().getMonth());
            row.createCell(2).setCellValue(entity.getId().getYear());
            row.createCell(3).setCellValue(entity.getId().getSite());
            row.createCell(4).setCellValue(entity.getMoonPhase());
            row.createCell(5).setCellValue(entity.getCoefficient0H());
            row.createCell(6).setCellValue(entity.getCoefficient12H());
            row.createCell(7).setCellValue(entity.getMorningHighTideTime());
            row.createCell(8).setCellValue(entity.getMorningHighTideHeight());
            row.createCell(9).setCellValue(entity.getAfternoonHighTideTime());
            row.createCell(10).setCellValue(entity.getAfternoonHighTideHeight());
            row.createCell(11).setCellValue(entity.getMorningLowTideTime());
            row.createCell(12).setCellValue(entity.getMorningLowTideHeight());
            row.createCell(13).setCellValue(entity.getAfternoonLowTideTime());
            row.createCell(14).setCellValue(entity.getAfternoonLowTideHeight());
        }
        log.info("El total de los campos de TideTableEntity " + res);
        return res;
    }

    // Otros métodos para las entidades restantes (similar a los anteriores)
    private int populateGeographicalLocationSheet(Sheet sheet, List<GeographicalLocationEntity> locations) {
        int res = 0;
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(1).setCellValue("name");
        headerRow.createCell(2).setCellValue("site");
        headerRow.createCell(3).setCellValue("id_windwuru");
        headerRow.createCell(4).setCellValue("geographicalLocation");
        int rowIdx = 1;
        for (GeographicalLocationEntity entity : locations) {
            res++;
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(entity.getId());
            row.createCell(1).setCellValue(entity.getName());
            row.createCell(2).setCellValue(entity.getSite());
            row.createCell(3).setCellValue(entity.getIdWindwuru());
        }
        log.info("El total de los campos de GeographicalLocationEntity " + res);
        return res;
    }

    private int populateFishSheet(Sheet sheet, List<FishEntity> fishList) {
        int res = 0;
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(1).setCellValue("name");
        headerRow.createCell(2).setCellValue("site");
        headerRow.createCell(3).setCellValue("firstSighting");
        headerRow.createCell(4).setCellValue("firstLast");
        headerRow.createCell(5).setCellValue("startSeason");
        headerRow.createCell(6).setCellValue("endSeason");
        headerRow.createCell(7).setCellValue("firstLifeWarning");
        int rowIdx = 1;
        for (FishEntity entity : fishList) {
            res++;
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(entity.getId());
            row.createCell(1).setCellValue(entity.getName());
            row.createCell(2).setCellValue(entity.getSite());
            row.createCell(3).setCellValue(entity.getFirstSighting());
            row.createCell(4).setCellValue(entity.getFirstLast());
            row.createCell(5).setCellValue(entity.getStartSeason());
            row.createCell(6).setCellValue(entity.getEndSeason());
            row.createCell(7).setCellValue(entity.getFirstLifeWarning());
        }
        log.info("El total de los campos de FishEntity " + res);
        return res;
    }

    private int populateFishingSheet(Sheet sheet, List<FishingEntity> fishingList) {
        int res = 0;
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(2).setCellValue("fish_id");
        headerRow.createCell(2).setCellValue("notes");

        headerRow.createCell(3).setCellValue("caught");
        headerRow.createCell(4).setCellValue("weight");
        headerRow.createCell(5).setCellValue("latG");

        headerRow.createCell(6).setCellValue("longG");
        headerRow.createCell(7).setCellValue("geographicalLocation");
        headerRow.createCell(8).setCellValue("dive_Day_id");

        headerRow.createCell(9).setCellValue("sighting_time");

        int rowIdx = 1;
        for (FishingEntity entity : fishingList) {
            res++;
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(entity.getId());
            row.createCell(1).setCellValue(entity.getFish().getId());
            row.createCell(2).setCellValue(entity.getNotes());


            row.createCell(3).setCellValue(entity.isCaught());
            if (entity.getWeight() != null)
                row.createCell(4).setCellValue(entity.getWeight().doubleValue());
            row.createCell(5).setCellValue(entity.getLatG());

            row.createCell(6).setCellValue(entity.getLongG());
            row.createCell(7).setCellValue(entity.getGeographicalLocation().getId());
            row.createCell(8).setCellValue(entity.getDiveDay().getDiveDayId());

            row.createCell(9).setCellValue(entity.getSightingTime());
        }
        log.info("El total de los campos de FishingEntity " + res);
        return res;
    }

    private int populateDiveDaySheet(Sheet sheet, List<DiveDayEntity> diveDayList) {
        int res = 0;
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("dive_day_id");
        headerRow.createCell(1).setCellValue("day");
        headerRow.createCell(2).setCellValue("beginning");
        headerRow.createCell(3).setCellValue("end");
        headerRow.createCell(4).setCellValue("site");
        headerRow.createCell(5).setCellValue("notes");
        headerRow.createCell(6).setCellValue("year");
        headerRow.createCell(7).setCellValue("month");
        headerRow.createCell(8).setCellValue("assessment");
        headerRow.createCell(9).setCellValue("id_geographic");
        headerRow.createCell(10).setCellValue("presence_of_jellyfish");
        headerRow.createCell(11).setCellValue("water_visibility");
        headerRow.createCell(12).setCellValue("sea_Background");
        headerRow.createCell(13).setCellValue("fish_grass");
        headerRow.createCell(14).setCellValue("presence_plastic");

        int rowIdx = 1;
        for (DiveDayEntity entity : diveDayList) {
            res++;
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(entity.getDiveDayId());
            row.createCell(1).setCellValue(entity.getDay());
            row.createCell(2).setCellValue(entity.getBeginning());
            row.createCell(3).setCellValue(entity.getEnd());
            row.createCell(4).setCellValue(entity.getSite());
            row.createCell(5).setCellValue(entity.getNotes());
            row.createCell(6).setCellValue(entity.getYear());
            row.createCell(7).setCellValue(entity.getMonth());
            row.createCell(8).setCellValue(entity.getAssessment());
            row.createCell(9).setCellValue(entity.getGeographicalLocation().getId());
            row.createCell(10).setCellValue(entity.getJellyfish());
            row.createCell(11).setCellValue(entity.getVisibility());
            row.createCell(12).setCellValue(entity.getSeaBackground());
            row.createCell(13).setCellValue(entity.getFishGrass());
            row.createCell(14).setCellValue(entity.getPresencePlastic());
        }
        log.info("El total de los campos de DiveDayEntity " + res);
        return res;
    }
}
