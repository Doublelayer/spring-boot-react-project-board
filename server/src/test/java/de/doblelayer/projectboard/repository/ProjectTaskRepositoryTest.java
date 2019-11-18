package de.doblelayer.projectboard.repository;

import de.doblelayer.projectboard.domain.ProjectTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectTaskRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Test
    public void whenFindBySummaryThenReturnProjectTask() {
        ProjectTask projectTask = new ProjectTask();
        projectTask.setSummary("test");
        entityManager.persist(projectTask);
        entityManager.flush();

        ProjectTask found = projectTaskRepository.findBySummary(projectTask.getSummary());

        assertThat(found.getSummary())
                .isEqualTo(projectTask.getSummary());
    }


}