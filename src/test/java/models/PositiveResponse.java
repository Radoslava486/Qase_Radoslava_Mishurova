package models;


import lombok.Builder;
import lombok.Data;
@Data
@Builder

public class PositiveResponse<T> {
    @Builder.Default
    private boolean status = true;
    private T result;
}
