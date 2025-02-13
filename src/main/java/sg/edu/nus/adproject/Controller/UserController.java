package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.adproject.Model.User;
import sg.edu.nus.adproject.Service.PasswordResetService;
import sg.edu.nus.adproject.Service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
    // get user by userID
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId)
    {
        User user = userService.getUserById(userId);

        if(user == null)
        {
            Map<String, Object> feedbacks = new HashMap<>();
            feedbacks.put("status", 404);
            feedbacks.put("message", "User not found");

            return ResponseEntity.status(404).body(feedbacks);
        }
        return ResponseEntity.status(200).body(user);
    }
    //delete user by userID
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId)
    {
        boolean successDeletion = userService.deleteUserById(userId);

        if(successDeletion)
        {
            Map<String, Object> feedbacks = new HashMap<>();
            feedbacks.put("status", 200);
            feedbacks.put("message", "User deleted successfully");
            return ResponseEntity.status(200).body(feedbacks);
        }
        return ResponseEntity.status(404).body("User not found");
    }

    //This is for single column update of the user
    //Can change according to biz logic

    //.....
    //Receives a PATCH request with userId and new user data.
    //Finds the user in the database.
    //Updates the user's username.
    //Saves the changes and returns a success response.

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable Long userId ,@RequestBody Map<String, Object> userUpdateBody)
            //@PathVariable -> extract userID from request URL
    {
        User user = userService.getUserById(userId);
        //find the user in database,store user in user object
        //check if the user exist
        //if user is null, return 404 not found
        if(user == null)
        {
            return ResponseEntity.status(404).body("User not found for update");
        }

        String newUserName = userUpdateBody.get("username").toString();
        //retrieve new username from userUpdate body and convert to string
        user.setUsername(newUserName);//update username with new value
        userService.saveUser(user);// save updated user into the database

        //Create response object with
        Map<String, Object> feedbacks = new HashMap<>();
        feedbacks.put("status", 200);//indicate success
        feedbacks.put("message", "User updated successfully");//confirmation message

        return ResponseEntity.status(200).body(feedbacks);
        //return http 200 Ok with success message
    }

    @GetMapping("/forgot-password/{userId}")
    public ResponseEntity<String> requestPasswordReset(@PathVariable long userId) {

        User user = userService.getUserById(userId);

        if(user == null)
        {
            return ResponseEntity.status(404).body("User not found");
        }
        //retrieves the email address of user from username field.
        //username is actually the email.
        String email = user.getUsername();
        //send password reset email to user's email
        passwordResetService.sendResetPasswordEmail(email);
        //email contains reset link that allow user to set a new password.
        return ResponseEntity.ok("Password reset link sent to your email.");
    }


//    @GetMapping("/forgot-password/{userId}")
//    public ResponseEntity<String> requestPasswordReset(@PathVariable long userId) {
//
//        User user = userService.getUserById(userId);
//
//        if(user == null)
//        {
//            return ResponseEntity.status(404).body("User not found");
//        }
//
//        String email = user.getUsername();
//        passwordResetService.sendResetPasswordEmail(email);
//        return ResponseEntity.ok("Password reset link sent to your email.");
//    }

    @PutMapping("/UpdatePassword")
    public ResponseEntity<String> updateUserPassword(@RequestParam Long userId, @RequestParam String newPassword) {
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        user.setPassword(newPassword);
        userService.saveUser(user);

        return ResponseEntity.ok("Password updated successfully");
    }
}
