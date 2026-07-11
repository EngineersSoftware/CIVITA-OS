package co.gov.antioquia.civitas.civitas_os.specification;

import org.springframework.data.jpa.domain.Specification;

import co.gov.antioquia.civitas.civitas_os.entity.User;

public class UserSpecification {

    private UserSpecification() {
        // Private constructor to prevent instantiation
    }

    public static Specification<User> containUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            if (username == null || username.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("username")),
                    "%" + username.toLowerCase() + "%");
        };
    }

    public static Specification<User> isEnabled(Boolean enabled) {
        return (root, query, criteriaBuilder) -> {
            if (enabled == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("enabled"), enabled);
        };
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("email"), email);
        };
    }

}
