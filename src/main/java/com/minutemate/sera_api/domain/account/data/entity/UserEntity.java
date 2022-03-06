package com.minutemate.sera_api.domain.account.data.entity;

import com.minutemate.sera_api.domain.account.data.dto.UserDto;
import com.minutemate.sera_api.domain.account.data.type.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String encodedPassword;
    @Enumerated(EnumType.STRING)
    private Permission permission;

    public UserEntity(String nickname, String email, String encodedPassword) {
        this.nickname = nickname;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.permission = Permission.USER;
    }

    public UserDto toDto() {
        return new UserDto(id, nickname, email, encodedPassword);
    }
}
