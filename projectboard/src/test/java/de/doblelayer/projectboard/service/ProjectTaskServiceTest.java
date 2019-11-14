package de.doblelayer.projectboard.service;

import de.doblelayer.projectboard.domain.ProjectTask;
import de.doblelayer.projectboard.repository.ProjectTaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ProjectTaskServiceTest {

    @TestConfiguration
    static class ProjectTaskServiceTestContextConfiguration{
        @Bean
        public ProjectTaskService projectTaskService(){
            return new ProjectTaskService();
        }
    }

    @Autowired
    private ProjectTaskService projectTaskService;

    @MockBean
    private ProjectTaskRepository projectTaskRepository;

    @Before
    public void setUp(){
        ProjectTask projectTask = new ProjectTask();
        projectTask.setSummary("test");

        Mockito.when(projectTaskRepository.findBySummary(projectTask.getSummary()))
                .thenReturn(projectTask);
    }

    @Test
    public void whenValidSummaryThenProjectTaskShouldBeFound(){
        String summary = "test";
        // ProjectTask found = projectTaskService.
    }

}