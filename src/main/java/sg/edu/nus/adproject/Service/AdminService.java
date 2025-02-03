package sg.edu.nus.adproject.Service;

import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.Admin;
import sg.edu.nus.adproject.Repository.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean registerAdmin(Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()) != null) return false;
        adminRepository.save(admin);
        return true;
    }

    public Admin authenticateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    public int loginAdmin(String username, String password) {
        return adminRepository.findByUsername(username).getPassword().equals(password) ? 1 : 0;
    }

    public Admin getAdmin(String username) {
        return adminRepository.findByUsername(username);
    }
}
