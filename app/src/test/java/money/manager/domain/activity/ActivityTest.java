package money.manager.domain.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import money.manager.domain.activity.type.Type.ActivityType;
import money.manager.utils.InstantUtils;
import org.junit.jupiter.api.Test;

public class ActivityTest {

    // Given
    // When
    // Then
    @Test
    public void givenValidParams_whenBuildingNewActivity_thenReturnNewValidActivity() {

        final var aDescription = "A description";
        final var aValue = 3.55f;
        final var aDate = InstantUtils.now();
        final var aType = ActivityType.REVENUE;

        final var anActivity = Activity.newActivity(aDate, aDescription, aValue, aType);

        // final var anotherActivity = Activity.with(null, aDate, aDescription, 0, ActivityType.REVENUE,
        //         InstantUtils.now(), InstantUtils.now());

        assertNotNull(anActivity);
        assertNotNull(anActivity.getId());
        assertTrue(anActivity.getId().length() == 36);
        assertEquals(aDescription, anActivity.getDescription());
        assertEquals(aDate, anActivity.getDate());
        assertEquals(aValue, anActivity.getValue(), 0.001);
        assertEquals(aType, anActivity.getType());
        assertNotNull(anActivity.getCreatedAt());
        assertNotNull(anActivity.getUpdatedAt());
    }
}