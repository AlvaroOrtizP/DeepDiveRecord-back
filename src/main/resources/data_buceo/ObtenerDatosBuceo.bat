@echo off
REM Cambiar a la unidad C:
C:
REM Navegar al directorio específico del usuario actual
cd "%USERPROFILE%\Documents\GitHub\WebWeatherScraper"
REM Ejecutar el script de Python
python -c "import main; main.main()"
