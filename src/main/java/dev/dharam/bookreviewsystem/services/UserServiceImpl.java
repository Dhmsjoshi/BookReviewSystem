package dev.dharam.bookreviewsystem.services;

import dev.dharam.bookreviewsystem.dtos.LoginRequestDto;
import dev.dharam.bookreviewsystem.dtos.SignupRequestDto;
import dev.dharam.bookreviewsystem.dtos.UserDto;
import dev.dharam.bookreviewsystem.exceptions.ResourceAlreadyExistsException;
import dev.dharam.bookreviewsystem.models.User;
import dev.dharam.bookreviewsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto signup(SignupRequestDto requestDto) {
        Optional<User> userOptional = userRepository.findByEmail(requestDto.getEmail());
        if(userOptional.isPresent()){
            throw new ResourceAlreadyExistsException("User with email "+requestDto.getEmail()+" already exists");
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User savedUser = userRepository.save(user);
        return UserDto.fromUserToUserDto(savedUser);
    }



    @Override
    public User findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new RuntimeException("User with id "+userId+" does not exist")
        );
        return user;
    }
}
