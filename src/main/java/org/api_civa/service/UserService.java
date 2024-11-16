package org.api_civa.service;

import org.api_civa.dto.UserDTO;
import org.api_civa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService{
    public Page<UserDTO> findAll(Pageable pageable);
    public Optional<User> findById(Long id);
    public Optional<User> findByUsername(String username);
    public User save(UserDTO userDTO, Long id);
    public void deleteById(Long id);
}
