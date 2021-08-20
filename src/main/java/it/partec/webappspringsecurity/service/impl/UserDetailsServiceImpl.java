package it.partec.webappspringsecurity.service.impl;

import org.springframework.transaction.annotation.Transactional;

import it.partec.webappspringsecurity.model.User;
import it.partec.webappspringsecurity.repository.RoleRepository;
import it.partec.webappspringsecurity.repository.UserRepository;
import it.partec.webappspringsecurity.security.UserDetailsAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(user);
        return userDetailsAdapter;
    }
}
