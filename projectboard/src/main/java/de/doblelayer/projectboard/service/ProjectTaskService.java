package de.doblelayer.projectboard.service;

import de.doblelayer.projectboard.domain.ProjectTask;
import de.doblelayer.projectboard.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask){
        if(isStatusSet(projectTask)){
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findAll(){
        return projectTaskRepository.findAll();
    }

    public ProjectTask findById(Long id){
        return projectTaskRepository.getById(id);
    }

    public void delete(Long id){
        projectTaskRepository.deleteById(id);
    }

    public boolean exists(Long id){
        return projectTaskRepository.existsById(id);
    }

    private boolean isStatusSet(ProjectTask projectTask) {
        return projectTask.getStatus() != null || projectTask.getStatus() != "";
    }
}
