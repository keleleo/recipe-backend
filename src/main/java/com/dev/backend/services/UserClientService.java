package com.dev.backend.services;

import com.dev.backend.models.UserClientRegister;
import com.dev.backend.enums.PermissionName;
import com.dev.backend.models.Permission;
import com.dev.backend.models.UserAccount;
import com.dev.backend.repositories.PermissionRepository;
import com.dev.backend.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserClientService {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final List<PermissionName> defaultPermissions = Arrays.asList(PermissionName.USER);

    public UserAccount findByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

    public UserAccount register(UserClientRegister userRegister) {
        List<Permission> permissions = permissionRepository.findByNameIn(defaultPermissions);
        String password = passwordEncoder.encode(userRegister.getPassword());

        UserAccount user = new UserAccount(userRegister.getUserName(), userRegister.getEmail(), password, permissions);

        userAccountRepository.save(user);

        return user;
    }

//    public void addPermission(String userId, String permissionName) throws Exception {
//        Optional<UserAccount> userOp = userAccountRepository.findById(UUID.fromString(userId));
//        List<Permission> permission = permissionRepository.findByName(PermissionName.valueOf(permissionName));
//        if (userOp.isEmpty()) {
//            throw new Exception("user not found");
//        }
//        if (permission.isEmpty()) {
//            throw new Exception("user not found");
//        }
//        UserAccount user = userOp.get();
//
//        List<Permission> permissions = user.getPermissions();
//        permissions.add(permission.get(0));
//
//        user.setPermissions(permissions);
//        userAccountRepository.save(user);
//    }
}
