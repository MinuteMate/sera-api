package com.minutemate.sera_api.domain.account.repository;

import com.minutemate.sera_api.domain.account.data.entity.AuthorizeNumberEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorizeNumberRepository extends CrudRepository<AuthorizeNumberEntity, String> {
}
