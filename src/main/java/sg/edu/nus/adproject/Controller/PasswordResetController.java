package sg.edu.nus.adproject.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import sg.edu.nus.adproject.Model.User;
import sg.edu.nus.adproject.Repository.UserRepository;
import sg.edu.nus.adproject.Service.InMemoryTokenService;
import sg.edu.nus.adproject.Service.PasswordResetService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {
    //for password reset requests
    private final PasswordResetService passwordResetService;
    private final InMemoryTokenService tokenService;
    private final UserRepository userRepository;

    public PasswordResetController(PasswordResetService passwordResetService, InMemoryTokenService tokenService, UserRepository userRepository) {
        this.passwordResetService = passwordResetService;//handle password reset logic
        this.tokenService = tokenService; //manage reset tokens
        this.userRepository = userRepository; // retrieve user data from database
    }

    @GetMapping("/reset-password/{token}")
    public RedirectView resetPassword(@PathVariable String token) {
        //check if token is valid,if valid -> return user associated with token.
        //if invalid -> returns null
        String email = tokenService.validateResetToken(token);


        //if invalid-> redirect to error page
        if (email == null) {
            return new RedirectView("/error?message=Invalid or expired token");
        }

        User user = userRepository.findByUsername(email);// find the user in database using email
        //if no user is found for given email, redirect to error page
        if (user == null) {
            return new RedirectView("/error?message=User not found");
        }
        //if token is valid and user exist,redirect to the password reset page
        return new RedirectView("http://localhost:5173");//handle the password reset form
    }


}