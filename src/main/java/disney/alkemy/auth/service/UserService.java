
package disney.alkemy.auth.service;

import disney.alkemy.auth.dto.AuthenticationRequest;
import disney.alkemy.exception.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtTokenUtils;

    public String authenticationUser(AuthenticationRequest authRequest) throws Exception{
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
                    );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception(ErrorEnum.USERORPASSWORDINCORRECT.getMessage(), e);
        }
        final String jwt = jwtTokenUtils.generateToken(userDetails);
        
        return jwt;
    }
    
}
