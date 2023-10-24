package money.manager.service.activity.implementation;

import java.util.List;
import java.util.stream.Collectors;

import money.manager.domain.activity.type.Type.ActivityType;
import money.manager.domain.gateway.ActivityGateway;
import money.manager.service.activity.ActivityService;
import money.manager.service.activity.dto.InsertActivityInputDto;
import money.manager.service.activity.dto.InsertActivityOutputDto;
import money.manager.service.activity.dto.ListActivityOutputDto;
import money.manager.service.activity.dto.mapper.ActivityToInsertActivityOutputMapper;
import money.manager.service.activity.dto.mapper.ActivityToListActivitiesOutputMapper;
import money.manager.service.activity.dto.mapper.InsertActivityInputToActivityMapper;

public class ActivityServiceImpl implements ActivityService {

    private ActivityGateway activityGateway;

    private ActivityServiceImpl(final ActivityGateway aGateway) {
        this.activityGateway = aGateway;
    }

    public static ActivityServiceImpl build(final ActivityGateway aGateway) {
        return new ActivityServiceImpl(aGateway);
    }

    @Override
    public InsertActivityOutputDto insertActivity(InsertActivityInputDto anInput) {
        final var anActivity = InsertActivityInputToActivityMapper.build().apply(anInput);

        this.activityGateway.create(anActivity);

        return ActivityToInsertActivityOutputMapper.build().apply(anActivity);
    }

    @Override
    public void removeActivity(String anId) {
        this.activityGateway.delete(anId);
    }

    @Override
    public List<ListActivityOutputDto> listActivity() {
        final var aList = this.activityGateway.findAll();

        return aList.stream()
                .map(a -> ActivityToListActivitiesOutputMapper.build().apply(a))
                .collect(Collectors.toList());
    }

    @Override
    public float calculateBalance() {
        final var aList = this.activityGateway.findAll();

        if (aList == null || aList.size() == 0) {
            return 0;
        }

        // Isso:
        return (float) aList.stream()
                .mapToDouble(
                        a -> a.getType() == ActivityType.REVENUE
                                ? a.getValue()
                                : -a.getValue())
                .sum();

        // É igual a isso:
        // var balance = 0.0f;

        // for (final var activity : aList) {
        // if (activity.getType() == ActivityType.REVENUE) {
        // balance += activity.getValue();
        // }
        // else {
        // balance -= activity.getValue();
        // }
        // }

        // return balance;
    }

}
