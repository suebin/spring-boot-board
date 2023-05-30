package com.nhnacademy.board.entity;

import com.nhnacademy.board.user.domain.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_id")
    private String id;
    private String password;
    private String name;
    @Column(name = "profile_file_name")
    private String profileFileName;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private User(String id, String password, String name, Role role, String profileFileName){
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.profileFileName = profileFileName;
        this.createdAt = LocalDateTime.now();
    }

    public static User createAdmin(String id, String password, String name, String profileFileName){
        return new User(id,password,name, Role.ADMIN, profileFileName);
    }
    public static User createUser(String id, String password, String name, String profileFileName) {
        return new User(id, password, name, Role.USER, profileFileName);
    }

    public void update(String password, String name, Role role){
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public void updateProfileName(String profileFileName){
        this.profileFileName = profileFileName;
    }
}
