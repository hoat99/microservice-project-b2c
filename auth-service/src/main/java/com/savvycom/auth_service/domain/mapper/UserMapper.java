package com.savvycom.auth_service.domain.mapper;

import com.savvycom.auth_service.domain.dto.user.get.UserDTO;
import com.savvycom.auth_service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public List<UserDTO> convertToListDTO(List<User> users) {
        List<UserDTO> userDTOS = users.stream()
                .map(p -> new UserDTO(
                        p.getUserId(),
                        p.getUsername(),
                        p.getName(),
                        p.getPhone(),
                        p.getAddress(),
                        p.getGender(),
                        p.getAvatar(),
                        p.isStatus(),
                        p.getRoles()
                ))
                .collect(Collectors.toList());
        return userDTOS;
    }

    public UserDTO convertToDTO(User p) {
        UserDTO userDTO = new UserDTO(
                p.getUserId(),
                p.getUsername(),
                p.getName(),
                p.getPhone(),
                p.getAddress(),
                p.getGender(),
                p.getAvatar(),
                p.isStatus(),
                p.getRoles()
        );

        return userDTO;
    }
}
