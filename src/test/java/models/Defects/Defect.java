package models.Defects;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Defect {
    private int id;
    private String title;
    private String actual_result;
    private String severity;
    private String status;
    private String milestone_id;
    private int project_id;
    private int member_id;
    private String created;
    private String updated;
    private String created_at;
    private String updated_at;
    private String resolved_at;
    private String external_data;
    private List<Attachments> attachments;
    private List<CustomFields> custom_fields;
    private List<Tags> tags;

}