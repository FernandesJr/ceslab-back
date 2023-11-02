package br.com.ceslab.ceslab.resources;

import br.com.ceslab.ceslab.dto.TokenDTO;
import br.com.ceslab.ceslab.dto.user.UserUpdateDTO;
import br.com.ceslab.ceslab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping("/{email}")
    public ResponseEntity<UserUpdateDTO> findByEmail(@PathVariable String email) {
        UserUpdateDTO userUpdateDTO = this.service.findByEmailDto(email);
        return ResponseEntity.ok(userUpdateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TokenDTO> update(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        TokenDTO refreshToken = this.service.update(id, dto);
        return ResponseEntity.ok(refreshToken);
    }
}
