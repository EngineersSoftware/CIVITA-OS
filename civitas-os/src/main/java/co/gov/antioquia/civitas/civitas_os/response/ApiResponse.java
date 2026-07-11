package co.gov.antioquia.civitas.civitas_os.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();

    private final int status;
    private final String message;
    private final T data;

    public static <T> ApiResponse<T> success(String message, T data){
        return ApiResponse.<T>builder()
                            .status(200)
                            .message(message)
                            .data(data)
                            .build();
    }

    public static <T> ApiResponse<T> created(String message,  T data){
        return ApiResponse.<T>builder()
                            .status(201)
                            .message(message)
                            .data(data)
                            .build();
    }

    public static <T> ApiResponse<T> ok(String message){
        return ApiResponse.<T>builder()
                            .status(200)
                            .message(message)
                            .build();
    }

}
