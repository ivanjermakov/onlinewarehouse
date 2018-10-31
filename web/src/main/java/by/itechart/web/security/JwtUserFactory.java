package by.itechart.web.security;

import by.itechart.common.entity.user.Authority;
import by.itechart.common.entity.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities((Collection<Authority>) user.getAuthorities()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Collection<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
