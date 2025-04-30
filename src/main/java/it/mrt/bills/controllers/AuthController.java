package it.mrt.bills.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import it.mrt.bills.components.JwtUtil;
import it.mrt.bills.entities.User;
import it.mrt.bills.services.TokenVerificationService;
import it.mrt.bills.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenVerificationService tokenVerificationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody GoogleLoginRequest request) throws Exception {
        GoogleIdToken.Payload payload = tokenVerificationService.verifyGoogleToken(request.idToken);
        if (payload == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID token");
        }

        String email = payload.getEmail();

        // Create or load user
        User user = userService.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName((String) payload.get("given_name"));
            newUser.setSurname((String) payload.get("family_name"));

            return userService.save(newUser);
        });

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String jwt = jwtUtil.generateToken(
                new org.springframework.security.core.userdetails.User(user.getEmail(), "", new ArrayList<>()));
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    static class GoogleLoginRequest {
        public String idToken;
    }

    static class AuthResponse {
        public String token;
        public AuthResponse(String token) {
            this.token = token;
        }
    }
}

