package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.MemberService;
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
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberResource {

    private final MemberService memberService;
    private final TeamService teamService;

    @PostMapping
    @Operation(summary = "save member", description = "this endpoint allow to save member")
    public ResponseEntity<?> saveMember(@RequestBody MemberDTO memberDTO){
        log.debug("REST Request to save  {}", memberDTO);
        Optional<TeamDTO> teamDTO = teamService.findOne(memberDTO.getTeam().getId());
        if (teamDTO.isPresent()){
            memberDTO.setTeam(teamDTO.get());
            MemberDTO member = memberService.save(memberDTO);
            return new ResponseEntity<>(member, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("not found", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get member by id", description = "this endpoint allow to get member by id")
    public Optional<MemberDTO> getMemberById(@PathVariable Long id){
        log.debug(" REST Request to get one {}", id);
        return memberService.findOne(id);

    }

    @GetMapping
    @Operation(summary = "get all member", description = "this endpoint allow to get all member")
    public List<MemberDTO> getAllMember(){
        log.debug("REST Request to all member ");
        return memberService.findAll();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete member", description = "this endpoint allow to delete member")
    public void deleteMember(@PathVariable Long id){
        log.debug("REST Request to delete {} ", id);
        memberService.delete(id);
    }

    @PutMapping("{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get member"),
            @ApiResponse(responseCode = "404", description = "Member not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> updateMember(@RequestBody MemberDTO memberDTO, @PathVariable Long id){
        log.debug("REST, Request to update member {} {}", memberDTO, id);
        return new ResponseEntity<>(memberService.update(memberDTO, id), HttpStatus.OK);
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "get member by slug", description = "this endpoint allow to get member by slug")
    public ResponseEntity<?> getOneBySlug(@PathVariable String slug){
        log.debug("REST Request to get one by slug {}", slug);
        Optional<MemberDTO> memberDTO = memberService.findBySlug(slug);
        if (memberDTO.isPresent()){
            return new ResponseEntity<>(memberDTO.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Slug project not found",HttpStatus.NOT_FOUND);
        }
    }


}
