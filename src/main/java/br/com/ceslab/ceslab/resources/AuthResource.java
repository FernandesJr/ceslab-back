package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.TokenDTO;
import br.com.ceslab.ceslab.dto.user.UserAuthDTO;
import br.com.ceslab.ceslab.entities.User;
import br.com.ceslab.ceslab.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody UserAuthDTO dto) {
        //The Spring valid the user and pass. Case not found throw a 403
        var user = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(user);
        TokenDTO token = new TokenDTO(tokenService.createToken((User) auth.getPrincipal()));
        return ResponseEntity.ok(token);
    }
}
