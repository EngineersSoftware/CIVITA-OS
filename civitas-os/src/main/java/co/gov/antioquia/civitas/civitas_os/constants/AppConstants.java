package co.gov.antioquia.civitas.civitas_os.constants;

public final class AppConstants {

    private AppConstants() {
        throw new UnsupportedOperationException("Esta es una clase utilitaria y no puede ser instanciada");
    }

    // Paginación por defecto para listados (Tickets, Activos, Usuarios)
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "20";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    // Prefijos de códigos internos de negocio (Ej: TICK-1045, ASSET-993)
    public static final String TICKET_PREFIX = "TICK-";
    public static final String ASSET_PREFIX = "ASSET-";

}
