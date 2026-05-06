package com.example.demo.Configuration;

import com.example.demo.Entities.SocietyAdmin;
import com.example.demo.Entities.User;
import com.example.demo.Repositories.SocietyAdminRepository;
import com.example.demo.Repositories.StaffRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.SuperAdminFolder.SuperAdmin;
import com.example.demo.SuperAdminFolder.SuperAdminRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final SuperAdminRepository superAdminRepository;
    private final SocietyAdminRepository societyAdminRepository;
    private final StaffRepository staffRepository;

    public CustomUserDetailsService(
            UserRepository userRepository,
            SuperAdminRepository superAdminRepository,
            SocietyAdminRepository societyAdminRepository,
            StaffRepository staffRepository
    ) {
        this.userRepository = userRepository;
        this.superAdminRepository = superAdminRepository;
        this.societyAdminRepository = societyAdminRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // ================= SUPER ADMIN =================
        Optional<SuperAdmin> superAdmin = superAdminRepository.findByEmail(email);
        if (superAdmin.isPresent()) {
            SuperAdmin admin = superAdmin.get();

            return new CustomUserDetails(
                    admin.getId(),
                    admin.getEmail(),
                    admin.getPassword(),
                    "SUPER_ADMIN",
                    null // no society
            );
        }

        // ================= SOCIETY ADMIN =================
        Optional<SocietyAdmin> societyAdmin = societyAdminRepository.findByAdminEmail(email);
        if (societyAdmin.isPresent()) {
            SocietyAdmin admin = societyAdmin.get();

            if (admin.getSociety() == null) {
                throw new RuntimeException("SocietyAdmin is not linked to any society");
            }

            return new CustomUserDetails(
                    admin.getId(),
                    admin.getAdminEmail(),
                    admin.getAdminPassword(),
                    "SOCIETY_ADMIN",
                    admin.getSociety().getId() // ✅ correct
            );
        }

        // ================= STAFF (Optional) =================
        /*
        Optional<Staff> staff = staffRepository.findByEmail(email);
        if (staff.isPresent()) {
            Staff s = staff.get();

            if (s.getSociety() == null) {
                throw new RuntimeException("Staff is not linked to any society");
            }

            return new CustomUserDetails(
                    s.getId(),
                    s.getEmail(),
                    s.getPassword(),
                    "STAFF",
                    s.getSociety().getId()
            );
        }
        */

        // ================= NORMAL USER =================
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        if (user.getSociety() == null) {
            throw new RuntimeException("User is not linked to any society");
        }

        return new CustomUserDetails(
                user.getId(),                          // ✅ correct userId
                user.getEmail(),
                user.getPassword(),
                user.getUserRole().name(),            // OWNER / TENANT etc.
                user.getSociety().getId()             // ✅ correct societyId
        );
    }
}












//package com.example.demo.Configuration;
//
//import com.example.demo.Entities.SocietyAdmin;
//import com.example.demo.Entities.User;
//import com.example.demo.Repositories.SocietyAdminRepository;
//import com.example.demo.Repositories.StaffRepository;
//import com.example.demo.Repositories.UserRepository;
//import com.example.demo.SuperAdminFolder.SuperAdmin;
//import com.example.demo.SuperAdminFolder.SuperAdminRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final SuperAdminRepository superAdminRepository;
//    private final SocietyAdminRepository societyAdminRepository;
//    private final StaffRepository staffRepository;
//
//    public CustomUserDetailsService(
//            UserRepository userRepository,
//            SuperAdminRepository superAdminRepository,
//            SocietyAdminRepository societyAdminRepository,
//            StaffRepository staffRepository
//    ) {
//        this.userRepository = userRepository;
//        this.superAdminRepository = superAdminRepository;
//        this.societyAdminRepository = societyAdminRepository;
//        this.staffRepository = staffRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//
//        // 1️⃣ SUPER ADMIN
//        Optional<SuperAdmin> superAdmin = superAdminRepository.findByEmail(email);
//        if (superAdmin.isPresent()) {
//            return buildUser(
//                    superAdmin.get().getEmail(),
//                    superAdmin.get().getPassword(),
//                    "SUPER_ADMIN"
//            );
//        }
//
//        // 2️⃣ SOCIETY ADMIN
//        Optional<SocietyAdmin> societyAdmin = societyAdminRepository.findByAdminEmail(email);
//        if (societyAdmin.isPresent()) {
//            return buildUser(
//                    societyAdmin.get().getAdminEmail(),
//                    societyAdmin.get().getAdminPassword(),
//                    "SOCIETY_ADMIN"
//            );
//        }
//
//        // 3️⃣ STAFF
//        //Optional<Staff> staff = staffRepository.findByEmail(email);
//        //if (staff.isPresent()) {
//            //return buildUser(
//                    //staff.get().getEmail(),
//                    //staff.get().getPassword(),
//                    //"STAFF"
//            //);
//        //}
//
//        // 4️⃣ NORMAL USER
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found"));
//
//        return buildUser(
//                user.getEmail(),
//                user.getPassword(),
//                user.getUserRole().name()
//        );
//    }
//
//    // ============================== HELPER ==============================
//
//    private UserDetails buildUser(
//            String email,
//            String password,
//            String role) {
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(email)
//                .password(password)
//                .authorities("ROLE_" + role)
//                .build();
//    }
//}
