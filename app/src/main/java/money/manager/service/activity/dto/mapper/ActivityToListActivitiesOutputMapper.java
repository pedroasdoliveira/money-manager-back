package money.manager.service.activity.dto.mapper;

import java.util.function.Function;

import money.manager.domain.activity.Activity;
import money.manager.service.activity.dto.ListActivityOutputDto;

public class ActivityToListActivitiesOutputMapper implements Function<Activity, ListActivityOutputDto> {

    public static ActivityToListActivitiesOutputMapper build() {
        return new ActivityToListActivitiesOutputMapper();
    }

    @Override
    public ListActivityOutputDto apply(final Activity input) {
        return new ListActivityOutputDto(
            input.getId(), 
            input.getDate(), 
            input.getDescription(), 
            input.getValue(), 
            input.getType().getValue(), 
            input.getCreatedAt(), 
            input.getUpdatedAt());
    }   
}
