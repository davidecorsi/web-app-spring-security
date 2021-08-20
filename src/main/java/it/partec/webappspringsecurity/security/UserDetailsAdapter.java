package it.partec.webappspringsecurity.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.partec.webappspringsecurity.model.Role;
import it.partec.webappspringsecurity.model.User;

public class UserDetailsAdapter implements UserDetails {
	
	private User user;
    private String ROLE_PREFIX = "ROLE_";
    
    public UserDetailsAdapter(User user) {
        this.user = user;
    }
    public Integer getId() {
        return user.getId();
    }
    public String getUsername() {
        return user.getUsername();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        for(Role role: user.getRoles()) {
            list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRole()));
        }
        return list;
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
