package money.manager.service.activity;

import java.util.List;

import money.manager.service.activity.dto.InsertActivityInputDto;
import money.manager.service.activity.dto.InsertActivityOutputDto;
import money.manager.service.activity.dto.ListActivityOutputDto;

public interface ActivityService {
    
    public InsertActivityOutputDto insertActivity(final InsertActivityInputDto anInput);
    public void removeActivity(final String anId);
    public List<ListActivityOutputDto> listActivity();
    public float calculateBalance();
}
