package com.nhnacademy.board.user.servlce;

import com.nhnacademy.board.config.CommonPropertiesConfig;
import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.exception.IdAlreadyExistException;
import com.nhnacademy.board.exception.UserNotFoundException;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.user.domain.UserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CommonPropertiesConfig commonPropertiesConfig;

    public User getUser(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if(Objects.isNull(user)){
            throw new UserNotFoundException(id);
        }
        return user;
    }

    public Page<User> getUserList(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public String getProfileImagePath(String id){
        User user = getUser(id);
        if(Objects.nonNull(user) && (StringUtils.hasText(user.getProfileFileName()))){
                return  user.getProfileFileName();

        }
        return "/resources/no-image.png";
    }

    public void register(UserRequest userRequest) {

        MultipartFile file = userRequest.getProfileFile();
        if( !file.isEmpty() ){
            try {
                file.transferTo(Paths.get(commonPropertiesConfig.getUploadPath() + File.separator + file.getOriginalFilename()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(userRepository.existsById(userRequest.getId())){
            throw new IdAlreadyExistException(userRequest.getId());
        }
        log.info("fileName:{}",userRequest.getProfileFile().getOriginalFilename());
        User user = User.createUser(userRequest.getId(),userRequest.getPassword(), userRequest.getName(), userRequest.getProfileFile().getOriginalFilename());
        userRepository.save(user);

    }

    public void update(UserRequest userRequest){
        User user = userRepository.findById(userRequest.getId()).orElseThrow(() -> new UserNotFoundException(userRequest.getId()));
        String uploadPath =commonPropertiesConfig.getUploadPath();
        MultipartFile file = userRequest.getProfileFile();
        if( !file.isEmpty() ){
            try {
                file.transferTo(Paths.get(uploadPath + File.separator + file.getOriginalFilename()));
                user.updateProfileName(file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        user.update(userRequest.getPassword(),userRequest.getName(),userRequest.getRole());
        userRepository.save(user);
    }

    public void delete(String id){
        userRepository.deleteById(id);
    }

}
