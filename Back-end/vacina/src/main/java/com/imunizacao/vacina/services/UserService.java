package com.imunizacao.vacina.services;

import com.imunizacao.vacina.exception.ResourceAlreadyExists;
import com.imunizacao.vacina.exception.ResourceNotFound;
import com.imunizacao.vacina.model.entities.Permission;
import com.imunizacao.vacina.model.entities.User;
import com.imunizacao.vacina.model.dto.UserDTO;
import com.imunizacao.vacina.repositories.PermissionRepository;
import com.imunizacao.vacina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        } else {
            return user;
        }
    }

    public UserDTO create(UserDTO user) {
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new ResourceAlreadyExists("Username " + user.getUserName() + " already exists");
        }

        var entity = new User(user);
        entity.setPassword(encoder.encode(user.getPassword()));
        entity.setPermissions(getPermission(user.getRoles()));

        return new UserDTO(userRepository.save(entity));
    }

    private List<Permission> getPermission(List<String> roles) {

        List<Permission> list = new ArrayList<>();

        for (String r : roles){
            var permission = permissionRepository.findById(Long.parseLong(r)).orElseThrow();
            if (permission == null){
                throw new ResourceNotFound("Permission " + r + " not found");
            } else {
                list.add(permission);
            }
        }

        return list;
    }

    public UserDTO findById(Long id){
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));

        return new UserDTO(entity);
    }

    public Page<UserDTO> findAll(Pageable pageable) {
        var entityList = userRepository.findAll(pageable);
        return entityList.map(this::convertToDTO);
    }

    public UserDTO convertToDTO(User user){
        return new UserDTO(user);
    }

}
