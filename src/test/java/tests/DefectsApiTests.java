package tests;

import adapters.DefectAdapter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.AllEntitiesResult;
import models.Defects.Defect;
import models.NegativeResponse;
import models.PositiveResponse;
import models.Projects.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class DefectsApiTests {
    private final Gson gson = new Gson();
    DefectAdapter defectAdapter = new DefectAdapter();
    String testCode = "DEFECTPROJ";

    @Test
    public void getAllDefects(){

        PositiveResponse<AllEntitiesResult<Defect>> response = gson.fromJson((defectAdapter.getAllDefects(200, testCode)),
                new TypeToken<PositiveResponse<AllEntitiesResult<Defect>>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(AllEntitiesResult.builder()
                        .total(1)
                        .filtered(1)
                        .count(1)
                        .entities(Arrays.asList(
                                Defect.builder()
                                        .id(1)
                                        .title("Regression")
                                        .actual_result("No")
                                        .status("open")
                                        .milestone_id(null)
                                        .project_id(239227)
                                        .severity("normal")
                                        .member_id(1)
                                        .attachments(Arrays.asList())
                                        .custom_fields(Arrays.asList())
                                        .external_data("{}")
                                        .resolved_at(null)
                                        .created("2022-08-31 22:49:25")
                                        .updated("2022-08-31 22:49:25")
                                        .created_at("2022-08-31T22:49:25+00:00")
                                        .updated_at("2022-08-31T22:49:25+00:00")
                                        .tags(Arrays.asList())
                                        .build()

                        ))
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void createNewDefect() {

        Defect defect = Defect.builder()
                .title("Close")
                .actual_result("doesn't work")
                .severity("2")
                .build();

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(5)
                        .build())
                .build();

        PositiveResponse<Defect> response = gson.fromJson(
                defectAdapter.createDefect(200, testCode, gson.toJson(defect)),
                new TypeToken<PositiveResponse<Defect>>() {}.getType());
        Assert.assertEquals(response, expectedResponse);
    }




    @Test
    public void getSpecificDefectPositiveTest() {

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(5)
                        .title("Close")
                        .actual_result("doesn't work")
                        .status("open")
                        .milestone_id(null)
                        .project_id(239227)
                        .severity("critical")
                        .member_id(1)
                        .attachments(Arrays.asList())
                        .custom_fields(Arrays.asList())
                        .external_data("{}")
                        .resolved_at(null)
                        .created("2022-08-31 23:14:18")
                        .updated("2022-08-31 23:14:18")
                        .created_at("2022-08-31T23:14:18+00:00")
                        .updated_at("2022-08-31T23:14:18+00:00")
                        .tags(Arrays.asList())
                        .build())
                .build();

        PositiveResponse<Defect> response = gson.fromJson((defectAdapter.getSpecificDefect(200, testCode, 5)),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void getSpecificDefectNegativeTest() {
        NegativeResponse response = gson.fromJson((defectAdapter.getSpecificDefect(404, testCode, 7)), NegativeResponse.class);
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Defect not found")
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteDefectPositiveTest() {
        PositiveResponse<Defect> response = gson.fromJson(((defectAdapter.deleteDefect(200, testCode, 5))),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(5)
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteDefectNegativeTest() {
        NegativeResponse response = gson.fromJson((defectAdapter.deleteDefect(404, testCode, 7)), NegativeResponse.class);
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Defect not found")
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

   @Test
    public void updateDefectPositiveTest() {

        Defect defect = Defect.builder()
                .title("Button")
                .build();

        PositiveResponse<Defect> response = gson.fromJson((defectAdapter.updateDefect(200, testCode, 3, gson.toJson(defect))),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(3)
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

}
