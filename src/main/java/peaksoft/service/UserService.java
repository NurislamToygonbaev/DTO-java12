package peaksoft.service;

import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.request.SignUpRequest;
import peaksoft.dto.response.RegisterResponse;
import peaksoft.dto.response.SignResponse;
import peaksoft.dto.response.SimpleResponse;

public interface UserService {
    RegisterResponse signUp(SignUpRequest signUpRequest);

    SignResponse signIn(SignInRequest signInRequest);
}
