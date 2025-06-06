package dev.dharam.bookreviewsystem.services;

import dev.dharam.bookreviewsystem.dtos.LoginRequestDto;
import dev.dharam.bookreviewsystem.dtos.SignupRequestDto;
import dev.dharam.bookreviewsystem.dtos.UserDto;
import dev.dharam.bookreviewsystem.models.User;

public interface UserService {

    UserDto signup(SignupRequestDto requestDto);


    User findById(Long userId);
}
