package com.summar.summar.mapper;

import com.summar.summar.domain.User;
import com.summar.summar.dto.JoinRequestDto;
import com.summar.summar.dto.SleepUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses ={})
public interface SleepUserMapper extends EntityMapper<SleepUserDto, User> {
}
