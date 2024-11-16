package org.api_civa.util;

import org.api_civa.dto.UserDTO;
import org.api_civa.entity.Role;
import org.api_civa.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {


    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole().name()
        );
    }

    public User toUser(UserDTO userDTO) {

        return new User(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                Role.valueOf(userDTO.getRole())
        );
    }

}
