package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.ProjectService;
import ci.digitalacademy.apigestiontasks.services.TeamService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamResource {

    private final TeamService teamService;
    private final ProjectService projectService;

    @PostMapping
    public TeamDTO save(@RequestBody TeamDTO teamDTO) {
        log.debug("REST Request to save {}", teamDTO);
        /*Optional<ProjectDTO> projectDTO = projectService.findOne(teamDTO.getProject().getId());
        if (projectDTO.isPresent()){
            teamDTO.setProject(projectDTO.get());
            TeamDTO team = teamService.save(teamDTO);
            return new ResponseEntity<>(team, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }*/
        return teamService.save((teamDTO));
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAll() {
        log.debug("REST Request to getAll");
        List<TeamDTO> entities = teamService.findAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        log.debug("REST request to get one {}", id);
        Optional<TeamDTO> teamDTO = teamService.findOne(id);
        if (teamDTO.isPresent()) {
            return new ResponseEntity<>(teamDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
        }
    }

    /*@GetMapping("/slug/{slug}")
    @Operation(summary = "get team by slug", description = "this endpoint allow to get team by slug")
    public Optional<TeamDTO> getOneBySlug(@PathVariable String slug){
        log.debug("REST Request to get one by slug {}", slug);
        return teamService.findBySlug(slug);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@RequestBody TeamDTO teamDTO, @PathVariable Long id) {
        log.debug("REST request to update Team {} {}", teamDTO, id);
        TeamDTO updatedTeam = teamService.update(teamDTO, id);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.debug("REST Request to delete {}", id);
        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
