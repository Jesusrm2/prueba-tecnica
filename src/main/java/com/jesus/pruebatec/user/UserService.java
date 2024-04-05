package com.jesus.pruebatec.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {

        User user = User.builder()
                .idUser(userRequest.idUser)
                .sesionStatus(userRequest.getSesionStatus())
                .role(Role.USER)
                .build();

        userRepository.updateUser(user.idUser,user.sesionStatus);

        return new UserResponse("El usuario se actualizo satisfactoriamente");
    }

    public UserDTO getUser(Integer id) {
        User user= userRepository.findById(id).orElse(null);

        if (user!=null)
        {
            UserDTO userDTO = UserDTO.builder()
                    .idUser(user.idUser)
                    .username(user.username)
                    .email(user.email)
                    .sesionStatus(user.sesionStatus)
                    .userStatus(user.userStatus)

                    .build();
            return userDTO;
        }
        return null;
    }
}
