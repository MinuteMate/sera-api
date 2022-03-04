package com.minutemate.sera_api.domain.account.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "authorize-number", timeToLive = 300)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeNumberEntity {
    @Id
    private String authorizeNumber;
    private String email;
}
