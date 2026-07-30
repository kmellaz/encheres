package fr.carrefour.kata.controler;


import fr.carrefour.kata.request.LoginRequest;
import fr.carrefour.kata.response.JwtResponse;
import fr.carrefour.kata.security.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest req) {
            var authToken = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
            var auth = authenticationManager.authenticate(authToken);
            String jwt = jwtProvider.generateToken(auth);
            return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
