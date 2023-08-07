package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.user.UserAuthDTO;
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

    @PostMapping
    public ResponseEntity auth(@RequestBody UserAuthDTO dto) {
        var user = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(user);

        return ResponseEntity.ok().build();
    }
}
