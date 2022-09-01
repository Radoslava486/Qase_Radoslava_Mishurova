package models.Projects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Runs {
    @Builder.Default
    private int total = 0,active = 0;

}
