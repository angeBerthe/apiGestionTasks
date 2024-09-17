package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.MemberService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
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

    @PostMapping
    @Operation(summary = "save member", description = "this endpoint allow to save member")
    public ResponseEntity<MemberDTO> saveMember(@RequestBody MemberDTO memberDTO){
        log.debug("REST Request to save  {}", memberDTO);
        return new ResponseEntity<>(memberService.save(memberDTO), HttpStatus.CREATED);
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
    public ResponseEntity<?> updateMember(@RequestBody MemberDTO memberDTO, @PathVariable Long id){
        log.debug("REST, Request to update member {} {}", memberDTO, id);
        return new ResponseEntity<>(memberService.update(memberDTO, id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "partial update member", description = "this endpoint allow to update member")
    public MemberDTO partialUpdate(@RequestBody MemberDTO memberDTO, @PathVariable Long id){
        log.debug("REST Request to partial update {}", id);
        return memberService.partialUpdate(memberDTO, id);
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "get member by slug", description = "this endpoint allow to get member by slug")
    public Optional<MemberDTO> getOneBySlug(@PathVariable String slug){
        log.debug("REST Request to get one by slug {}", slug);
        return memberService.findBySlug(slug);
    }


}
