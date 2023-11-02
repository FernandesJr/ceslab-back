package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.TokenDTO;
import br.com.ceslab.ceslab.dto.user.UserUpdateDTO;
import br.com.ceslab.ceslab.entities.User;
import br.com.ceslab.ceslab.repositories.UserRepository;
import br.com.ceslab.ceslab.services.exceptions.DataBaseViolationException;
import br.com.ceslab.ceslab.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional(readOnly = true)
    public UserUpdateDTO findByEmailDto(String email) {
        User entity =  repository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFound("User not found with email " + email));
        return new UserUpdateDTO(entity);
    }

    @Transactional
    public TokenDTO update(Long id, UserUpdateDTO dto) {
        User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found with id " + id));

        if (!passwordEncoder.matches(dto.getPassword(), entity.getPassword()))
            throw new DataBaseViolationException("Password not confirm");

        if (!dto.getNewPassword().equals("") &&
                !dto.getNewPassword().equals(dto.getConfirmPassword()))
            throw new DataBaseViolationException("New password not confirm");

        if (dto.getEmail() != entity.getEmail()) {
            User userByEmail = repository.findByEmail(dto.getEmail()).orElse(new User());
            if (userByEmail.getId() != null && userByEmail.getId() != id)
                throw new DataBaseViolationException("Email already in use for other user");
        }

        if (!dto.getNewPassword().equals("")) entity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());

        //Refresh token
        String token = this.tokenService.createToken(entity);
        return new TokenDTO(token);
    }

    //Used for auth
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new ResourceNotFound("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new ResourceNotFound("User not found " + username));
    }
}
