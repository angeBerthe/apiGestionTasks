package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.RoleUserService;
import ci.digitalacademy.apigestiontasks.services.dto.RoleUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role-users")
@RequiredArgsConstructor
@Slf4j
public class RoleUserResource {

    private final RoleUserService roleUserService;

    @PostMapping
    public ResponseEntity<RoleUserDTO> save(@RequestBody RoleUserDTO roleUser) {
        log.debug("Request to save RoleUser : {}", roleUser);
        return new ResponseEntity<>(roleUserService.save(roleUser), HttpStatus.CREATED);
    }
}
