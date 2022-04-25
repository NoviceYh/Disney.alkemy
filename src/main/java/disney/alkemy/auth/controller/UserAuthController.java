
package disney.alkemy.auth.controller;

import disney.alkemy.auth.dto.AuthenticationRequest;
import disney.alkemy.auth.dto.AuthenticationResponse;
import disney.alkemy.auth.dto.UserDTO;
import disney.alkemy.auth.service.JwtUtils;
import disney.alkemy.auth.service.UserDetailsCustomService;
import disney.alkemy.auth.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    
    @Autowired
    private UserDetailsCustomService userDetailsService;
    
    @Autowired
    private UserService userService;
//    private AuthenticationManager authenticationManager;
//    private JwtUtils jwtTokenUtils;
    
//    @Autowired
//    public UserAuthController(
//            UserDetailsCustomService userDetailsService, 
//            AuthenticationManager authenticationManager, 
//            JwtUtils jwtTokenUtils) {
//        this.userDetailsService = userDetailsService;
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenUtils = jwtTokenUtils;
//    }
    
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO user) throws Exception{
        this.userDetailsService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authRequest) throws Exception{
        //Pasar todo a un metodo de servicio
        
        String jwt = userService.authenticationUser(authRequest);
        /*
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
                    );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final String jwt = jwtTokenUtils.generateToken(userDetails);
        */
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    
    
    
}
