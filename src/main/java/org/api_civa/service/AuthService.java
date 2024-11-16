package org.api_civa.service;

import org.api_civa.dto.ReqRes;
import org.api_civa.entity.User;
import org.api_civa.repository.UserRepository;
import org.api_civa.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, JWTUtils jwtUtils, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public ReqRes signIn(ReqRes singInRequest) {
        ReqRes response = new ReqRes();
        System.out.println(singInRequest.getUsername());
        System.out.println(singInRequest.getPassword());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(singInRequest.getUsername(), singInRequest.getPassword())
            );

            var user = userRepository.findByUsername(singInRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            var jwt = jwtUtils.generateToken(user);

            response.setStatusCode(200);
            response.setToken(jwt);
            response.setMessage("Éxito al ingresar");

        } catch (BadCredentialsException e) {
            response.setStatusCode(401);
            response.setError("Credenciales incorrectas");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Error en el servidor");
        }

        return response;
    }


}

