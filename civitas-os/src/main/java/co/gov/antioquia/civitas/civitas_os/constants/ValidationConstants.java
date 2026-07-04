package co.gov.antioquia.civitas.civitas_os.constants;

public final class ValidationConstants {

    private ValidationConstants() {
        throw new UnsupportedOperationException("Esta es una clase utilitaria y no puede ser instanciada");
    }

    // Longitudes de campos de texto
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 64;
    public static final int MAX_USERNAME_LENGTH = 50;

    // Expresión Regular para contraseñas seguras
    // Requiere: al menos un dígito, una minúscula, una mayúscula, un carácter especial y 8-64 caracteres.
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,64}$";
    
    // Mensajes de validación por defecto
    public static final String PASSWORD_INVALID_MSG = "La contraseña debe tener entre 8 y 64 caracteres, incluir al menos una mayúscula, un número y un carácter especial.";
    
    // Formatos
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

}
