package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.ProjectService;
import ci.digitalacademy.apigestiontasks.services.TeamService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
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
@Slf4j
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamResource {

    private final TeamService teamService;
    private final ProjectService projectService;

    @PostMapping
    @Operation(summary = "save team", description = "this endpoint allow to save team")
    public ResponseEntity<TeamDTO > save(@RequestBody TeamDTO teamDTO) {
        log.debug("REST Request to save {}", teamDTO);
       return new ResponseEntity<>(teamService.saveTeam(teamDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "get all team", description = "this endpoint allow to get all team")
    public ResponseEntity<List<TeamDTO>> getAll() {
        log.debug("REST Request to getAll");
        List<TeamDTO> entities = teamService.findAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get team by id", description = "this endpoint allow to get team by id")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        log.debug("REST request to get one {}", id);
        Optional<TeamDTO> teamDTO = teamService.findOne(id);
        if (teamDTO.isPresent()) {
            return new ResponseEntity<>(teamDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Team not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "get team by slug", description = "this endpoint allow to get team by slug")
    public ResponseEntity<?>  getOneBySlug(@PathVariable String slug){
        log.debug("REST Request to get one by slug {}", slug);
        Optional<TeamDTO> teamDTO = teamService.findBySlug(slug);
        if (teamDTO.isPresent()){
            return new ResponseEntity<>(teamDTO.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Slug team not found",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get team"),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<TeamDTO> update(@RequestBody TeamDTO teamDTO, @PathVariable Long id) {
        log.debug("REST request to update Team {} {}", teamDTO, id);
        TeamDTO updatedTeam = teamService.update(teamDTO, id);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete team", description = "this endpoint allow to delete team")
    public void delete(@PathVariable Long id) {
        log.debug("REST Request to delete {}", id);
        teamService.delete(id);
    }

}
