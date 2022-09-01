package models;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NegativeResponse {
    @Builder.Default
    private boolean status = false;
    private String errorMessage;
    private List<ErrorField> errorFields;
}
