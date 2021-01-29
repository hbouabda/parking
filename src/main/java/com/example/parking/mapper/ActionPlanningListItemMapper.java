package com.example.parking.mapper;

import com.example.parking.configuration.MapStructConfig;
import com.example.parking.domain.ActionPlanningListItem;
import com.example.parking.dto.ActionPlanningListItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface ActionPlanningListItemMapper {

    List<ActionPlanningListItemDto> toDtos(Iterable<ActionPlanningListItem> models);

    ActionPlanningListItemDto toDto(ActionPlanningListItem model);

    ActionPlanningListItem toModel(ActionPlanningListItemDto dto);
}
