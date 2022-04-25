package disney.alkemy.auth.service;

import disney.alkemy.auth.dto.UserDTO;
import disney.alkemy.auth.entity.UserEntity;
import disney.alkemy.auth.repository.UserRepository;
import disney.alkemy.exception.ErrorEnum;
import disney.alkemy.exception.ParamNotFound;
import disney.alkemy.service.EmailService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(ErrorEnum.USERORPASSWORDNOTFOUND.getMessage());
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(UserDTO userDTO) {
        UserEntity user = userRepository.findByUsername(userDTO.getUsername());
        if (!(user == null)) {
            throw new ParamNotFound(ErrorEnum.USERALREADYEXIST.getMessage());
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUsername(userDTO.getUsername());
        userEntity = userRepository.save(userEntity);
        if (userEntity != null) {
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }    
}
