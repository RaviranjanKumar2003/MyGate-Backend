package com.example.demo.SuperAdminFolder;

import java.util.List;

public interface SuperAdminService {

    SuperAdminDto createSuperAdmin(SuperAdminDto dto);

    SuperAdminDto getSuperAdminById(Long id);

    List<SuperAdminDto> getAllSuperAdmins();

    SuperAdminDto updateSuperAdmin(Long id, SuperAdminDto dto);

    void deactivateSuperAdmin(Long id);


    SuperAdminDto updateSuperAdminImage(Long superAdminId, String imageName);


}
