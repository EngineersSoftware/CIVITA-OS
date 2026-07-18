package co.gov.antioquia.civitas.civitas_os.constants;

public final class SecurityConstants {

    private SecurityConstants() {
        throw new UnsupportedOperationException("No se puede instanciar la clase SecurityConstants");
    }

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKE_PREFIX = "Bearer ";
    public static final int TOKEN_PREFIX_LENGTH = 7;

    public static final String[] PUBLIC_MATCHERS = {
        "/api/v1/auth/**",
        "/api/docs/**",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/actuator/health",
        "/api/v1/users/register"
    };

    public static final String CLAIM_ROLES = "roles";
    public static final String CLAIM_EMAIL = "email";

}
