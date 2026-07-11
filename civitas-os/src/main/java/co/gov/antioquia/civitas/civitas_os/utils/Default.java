package co.gov.antioquia.civitas.civitas_os.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Default {

    private Default() {
        throw new UnsupportedOperationException("Clase utilitariaria, no instanciar");
    }

    public static String toHumanReadableFormat(LocalDateTime dateTime){
        if(dateTime == null){
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }

}
