package sg.edu.nus.adproject.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.adproject.Model.Admin;
import sg.edu.nus.adproject.Model.AdminAuthResponse;
import sg.edu.nus.adproject.Service.AdminService;

import java.util.Map;

@RestController
@RequestMapping("/administrator")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<AdminAuthResponse> registerAdmin(@RequestBody Admin admin) {
//        if (adminService.registerAdmin(admin)) {
//            return ResponseEntity.ok(new AdminAuthResponse(true, "/admin/dashboard", "Admin registered successfully"));
//        } else {
//            return ResponseEntity.status(409).body(new AdminAuthResponse(false, null, "Admin already exists"));
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<AdminAuthResponse> loginAdmin(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Admin admin = adminService.authenticateAdmin(username, password);
        if (admin != null) {
            return ResponseEntity.ok(new AdminAuthResponse(true, "/", "Login successful"));
        } else {
            return ResponseEntity.status(401).body(new AdminAuthResponse(false, null, "Invalid admin credentials"));
        }
    }
}
