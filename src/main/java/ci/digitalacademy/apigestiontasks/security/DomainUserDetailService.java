package ci.digitalacademy.apigestiontasks.security;

import ci.digitalacademy.apigestiontasks.models.RoleUser;
import ci.digitalacademy.apigestiontasks.models.User;
import ci.digitalacademy.apigestiontasks.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DomainUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        final List<GrantedAuthority> grantedAuthorities = user.get()
                .getRoleUsers()
                .stream()
                .map(RoleUser::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return user.map(userRecover -> org.springframework.security.core.userdetails.User.builder()
                .username(userRecover.getUsername())
                .password(userRecover.getPassword())
                .authorities(grantedAuthorities)
                //      .roles()
                .build()).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
