package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.response.SignResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.enums.Role;
import peaksoft.model.User;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @PostConstruct
    private void saveAdmin(){
        userRepo.save(
                User.builder()
                        .name("Admin")
                        .email("admin@gmail.com")
                        .role(Role.ADMIN)
                        .password("admin")
                        .build()
        );
    }

    @Override
    public SimpleResponse signUp(SignUpRequest signUpRequest) {
        boolean exists = userRepo.existsByEmail(signUpRequest.getEmail());
        if (exists) throw new RuntimeException("Email: "+signUpRequest.getEmail()+" already exist");
        if (!signUpRequest.getPassword().equals(signUpRequest.getPasswordConfirm())) throw new RuntimeException("Invalid password");

        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(Role.CLIENT);
        userRepo.save(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("successfully saved")
                .build();
    }

    @Override
    public SignResponse signIn(SignInRequest signInRequest) {
        User user = userRepo.findByEmail(signInRequest.email())
                .orElseThrow(() -> new NoSuchElementException("User with email: "+ signInRequest.email()+" not found"));
        if (!user.getPassword().equals(signInRequest.password())) throw new RuntimeException("Invalid password");
        return SignResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
