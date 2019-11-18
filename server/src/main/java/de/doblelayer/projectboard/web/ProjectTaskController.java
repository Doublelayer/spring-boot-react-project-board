package de.doblelayer.projectboard.web;

import de.doblelayer.projectboard.domain.ProjectTask;
import de.doblelayer.projectboard.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/board")
@CrossOrigin
public class ProjectTaskController {
    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("")
    public ResponseEntity<?> addProjectTaskToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result) {

        if (result.hasErrors()) {
            return responseWithBadRequest(result);
        }
        ProjectTask projectTask1 = projectTaskService.saveOrUpdateProjectTask(projectTask);

        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<ProjectTask> getAllProjectTasks() {
        return projectTaskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        if (!projectTaskService.exists(id))
            return notFound();

        ProjectTask projectTask = projectTaskService.findById(id);
        return new ResponseEntity<ProjectTask>(projectTask, ok());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable Long id) {
        if (!projectTaskService.exists(id))
            return notFound();

        projectTaskService.delete(id);
        return new ResponseEntity<String>("Project Task deleted", ok());
    }

    private HttpStatus ok() {
        return HttpStatus.OK;
    }

    private ResponseEntity<Object> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<?> responseWithBadRequest(BindingResult result) {
        Map<String, String> errorMap = new HashMap<>();

        for (FieldError error : result.getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
    }

}
