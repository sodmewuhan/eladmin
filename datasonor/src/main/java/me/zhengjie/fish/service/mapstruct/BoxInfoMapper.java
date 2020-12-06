package me.zhengjie.fish.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.fish.domain.BoxInfo;
import me.zhengjie.fish.service.dto.BoxInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoxInfoMapper extends BaseMapper<BoxInfoDto, BoxInfo> {
}
