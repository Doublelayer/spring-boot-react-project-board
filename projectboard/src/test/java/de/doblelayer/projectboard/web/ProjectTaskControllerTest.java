package de.doblelayer.projectboard.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.doblelayer.projectboard.domain.ProjectTask;
import de.doblelayer.projectboard.service.ProjectTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectTaskController.class)
public class ProjectTaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProjectTaskService projectTaskService;

    @Test
    public void postedProjectTaskShouldReturnJsonArrayWithCreatedStatus() throws Exception {
        ProjectTask projectTask = new ProjectTask();
        projectTask.setSummary("test");

        mvc.perform(post("/api/board")
                .characterEncoding("utf-8")
                .content(asJsonString(projectTask))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.id", is("1")));
                //.andExpect(jsonPath("$.summary").value("test"));

    }

    @Test
    public void getAllProjectTaskShouldReturnAllStoredProjectTasks() throws Exception {
        ProjectTask projectTask = new ProjectTask();
        projectTask.setSummary("test");

        projectTaskService.saveOrUpdateProjectTask(projectTask);

        mvc.perform(get("/api/board/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findByNegativeIdShouldReturnHttpStatusNotFound() throws Exception {
        mvc.perform(get("/api/board/-1"))
                .andExpect(status().isNotFound());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}