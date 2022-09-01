package models.Projects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Counts {
    @Builder.Default
    private int cases = 0, suites = 0, milestones = 0;
    private Runs runs;

}
