package org.api_civa.service;

import org.api_civa.dto.UserDTO;
import org.api_civa.entity.User;
import org.api_civa.repository.UserRepository;
import org.api_civa.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        List<UserDTO> userDTOS = usersPage.getContent().stream()
                .map(userMapper::toUserDTO)
                .toList();
        return new PageImpl<>(userDTOS, pageable, usersPage.getTotalElements());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDTO userDTO, Long id) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);

        Optional<User> optionalUser = userRepository.findByUsername(userDTO.getUsername());
        if (optionalUser.isPresent() && !optionalUser.get().getId().equals(id)) {
            throw new IllegalArgumentException("El nombre de usuario está en uso");
        }

        if (id != null) {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));

            User updatedUser = userMapper.toUser(userDTO);
            updatedUser.setId(existingUser.getId());
            updatedUser.setPassword(encodedPassword);

            return userRepository.save(updatedUser);
        } else {
            User newUser = userMapper.toUser(userDTO);
            newUser.setPassword(encodedPassword);

            return userRepository.save(newUser);
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        userRepository.deleteById(id);
    }
}