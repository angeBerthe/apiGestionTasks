package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.ProjectService;
import ci.digitalacademy.apigestiontasks.services.TasksService;
import ci.digitalacademy.apigestiontasks.services.TeamService;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@Slf4j
@RequiredArgsConstructor
public class TasksResource {

    private final TasksService tasksService;
    private final ProjectService projectService;
    private final TeamService teamService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "REST, request to save a task")
    @Operation(summary = "Save a new task", description = "This endpoint allow to save tasks")
    public ResponseEntity<?> save(@RequestBody TasksDTO tasks) {
        log.debug("Rest request to save Tasks : {}", tasks);
        Optional<ProjectDTO> project = projectService.findOne(tasks.getProject().getId());
        Optional<TeamDTO> team = teamService.findOne(tasks.getTeam().getId());
        if (project.isPresent()) {
            tasks.setProject(project.get());
            if (team.isPresent()) {
                tasks.setTeam(team.get());
                return new ResponseEntity<>(tasksService.saveTasks(tasks), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Id of team not found", HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>("Id of project not found" ,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get tasks by id"),
            @ApiResponse(responseCode = "404", description = "School not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getTasksById(@PathVariable Long id) {
        log.debug("Rest request to get Tasks by id : {}", id);
        Optional<TasksDTO> tasks = tasksService.findOne(id);
        if (tasks.isPresent()) {
            return new ResponseEntity<>(tasks.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Id of task not found" ,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getTasksBySlug(@PathVariable String slug) {
        log.debug("Rest request to get Tasks by slug : {}", slug);
        Optional<TasksDTO> tasks = tasksService.findBySlug(slug);
        if (tasks.isPresent()) {
            return new ResponseEntity<>(tasks.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Slug not found" ,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<TasksDTO> getAllTasks() {
        log.debug("Rest request to get all tasks");
        return tasksService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TasksDTO> updateT(@PathVariable Long id, @RequestBody TasksDTO tasks) {
        log.debug("Rest request to update Tasks : {}", tasks);
        return new ResponseEntity<>(tasksService.update(tasks, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.debug("Rest request to delete Tasks : {}", id);
        tasksService.delete(id);
    }
}
