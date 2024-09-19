package ci.digitalacademy.apigestiontasks.services.impl;

import ci.digitalacademy.apigestiontasks.models.RoleUser;
import ci.digitalacademy.apigestiontasks.repositories.RoleUserRepository;
import ci.digitalacademy.apigestiontasks.services.RoleUserService;
import ci.digitalacademy.apigestiontasks.services.dto.RoleUserDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.RoleUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class RoleUserImpl implements RoleUserService {

    private final RoleUserRepository roleUserRepository;
    private final RoleUserMapper roleUserMapper;

    @Override
    public RoleUserDTO save(RoleUserDTO roleUserDTO) {
        log.debug("Request to save RoleUser : {}", roleUserDTO);
        RoleUser roleUser = roleUserMapper.toEntity(roleUserDTO);
        roleUser = roleUserRepository.save(roleUser);
        return roleUserMapper.fromEntity(roleUser);
    }

    @Override
    public List<RoleUserDTO> findByRole(String roleUser) {
        log.debug("Request to get RoleUser : {}", roleUser);
        return roleUserRepository.findByRole(roleUser).stream().map(role -> {
            return roleUserMapper.fromEntity(role);
        }).toList();
    }
}
