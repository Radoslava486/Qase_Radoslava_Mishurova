package tests;

import adapters.ProjectAdapter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.AllEntitiesResult;
import models.ErrorField;
import models.NegativeResponse;
import models.PositiveResponse;
import models.Projects.Counts;
import models.Projects.Project;
import models.Projects.Runs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ProjectApiTests {
    private final Gson gson = new Gson();
    ProjectAdapter projectAdapter = new ProjectAdapter();
    String testCode = "CODE15";


    @Test
    public void getAllProjectsTest() {
        PositiveResponse<AllEntitiesResult<Project>> response = gson.fromJson((projectAdapter.getAllProjects(200)),
                new TypeToken<PositiveResponse<AllEntitiesResult<Project>>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(AllEntitiesResult.builder()
                        .total(2)
                        .filtered(2)
                        .count(2)
                        .entities(Arrays.asList(
                                Project.builder()
                                        .title("tms")
                                        .code("TMS")
                                        .counts(Counts.builder()
                                                .runs(Runs.builder()
                                                        .build())
                                                .build())
                                        .build(),
                                Project.builder()
                                        .title("QASE")
                                        .code("QASE")
                                        .counts(Counts.builder()
                                                .runs(Runs.builder()
                                                        .build())
                                                .build())
                                        .build()

                        ))
                        .build())
                .build();
        Assert.assertEquals(response, expectedResponse);
    }



    @Test
    public void createProjectPositiveTest() {

        Project project = Project.builder()
                .title("Project")
                .code(testCode)
                .build();

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Project.builder()
                        .code(testCode)
                        .build())
                .build();

        PositiveResponse<Project> response = gson.fromJson(
                projectAdapter.createProject(200, gson.toJson(project)),
                new TypeToken<PositiveResponse<Project>>() {}.getType());
        Assert.assertEquals(response, expectedResponse);
    }


    @Test
    public void createProjectNegativeTest() {
        Project project = Project.builder()
                .title("Try")
                .build();

        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Data is invalid.")
                .errorFields(Arrays.asList(
                        ErrorField.builder()
                                .field("code")
                                .error("Project code is required.")
                                .build()
                ))
                .build();
        NegativeResponse response = gson.fromJson(projectAdapter.createProject(400, gson.toJson(project)), NegativeResponse.class);
        Assert.assertEquals(response, expectedResponse);
    }


    @Test
    public void getProjectByCodePositiveTest() {
        PositiveResponse<Project> response = gson.fromJson((projectAdapter.getProjectByCode(200, testCode)),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Project.builder()
                        .title("Project")
                        .code(testCode)
                        .counts(Counts.builder()
                                .runs(Runs.builder()
                                        .build())
                                .build())
                        .build())
                .build();


        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void getProjectByCodeNegativeTest() {
        NegativeResponse response = gson.fromJson((projectAdapter.getProjectByCode(404, "qa19")), NegativeResponse.class);
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Project is not found.")
                .build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteProjectByCodePositiveTest() {
        PositiveResponse<Project> response = gson.fromJson((projectAdapter.deleteProjectByCode(200, testCode)),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder().build();
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteProjectByCodeNegativeTest() {
        NegativeResponse response = gson.fromJson((projectAdapter.deleteProjectByCode(404, "QA19")), NegativeResponse.class);
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Project is not found.")
                .build();
        Assert.assertEquals(response, expectedResponse);
    }


}
