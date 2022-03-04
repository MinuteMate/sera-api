package com.minutemate.sera_api.domain.account.data.entity;

import com.minutemate.sera_api.domain.account.data.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public UserEntity(String nickname, String email, String encodedPassword) {
        this.nickname = nickname;
        this.email = email;
        this.encodedPassword = encodedPassword;
    }

    public UserDto toDto() {
        return new UserDto(id, nickname, email, encodedPassword);
    }
}
