package money.manager.domain.activity;

import java.time.Instant;
import java.util.UUID;
import money.manager.domain.activity.type.Type.ActivityType;
import money.manager.domain.exception.DomainException;
import money.manager.utils.InstantUtils;

public class Activity {

    private String id;
    private Instant date;
    private String description;
    private float value;
    private ActivityType type;
    private Instant createdAt;
    private Instant updatedAt;

    private Activity(final String aId, final Instant aDate, final String aDescription, final float aValeu,
            final ActivityType aType, final Instant aCreatedAt, final Instant aUpdatedAt) {

        this.id = aId;
        this.date = aDate;
        this.description = aDescription;
        this.value = aValeu;
        this.type = aType;
        this.createdAt = aCreatedAt;
        this.updatedAt = aUpdatedAt;

        this.validate();
    }

    public static Activity newActivity(final Instant aDate, final String aDescription, final float aValeu,
            final ActivityType aType) {

                return new Activity(
                    UUID.randomUUID().toString().toLowerCase(),
                    aDate, aDescription, aValeu, aType,
                    InstantUtils.now(),
                    InstantUtils.now()
                );
    }

    public static Activity with(final String aId, final Instant aDate, final String aDescription, final float aValeu,
            final ActivityType aType, final Instant aCreatedAt, final Instant aUpdatedAt) {

                return new Activity(aId, aDate, aDescription, aValeu, aType, aCreatedAt, aUpdatedAt);
    }

    private void validate() {
        if (this.id.isBlank()) {
            throw new DomainException("Activity's ID should not be blank");
        }
        else if(this.id.length() != 36) {
            throw new DomainException("Activity's ID should be a valid UUID");
        }
        else if(this.description.isBlank()) {
            throw new DomainException("Activity's Description should not be blank");
        }
        else if(this.description.length() > 3) {
            throw new DomainException("Activity's Description should have at least 3 characters");
        }
        else if(this.type != ActivityType.REVENUE && this.type != ActivityType.EXPENSE) {
            throw new DomainException("Activity's Type should be either expense or revenue");
        }
        else if(this.value < 0.01) {
            throw new DomainException("Activity's Value should be greater than zero");
        }
        else if(this.createdAt.isAfter(this.updatedAt)) {
            throw new DomainException("Activity's createdAt should be before updatedAt");
        }
    }

    public String getId() {
        return id;
    }

    public Instant getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public float getValue() {
        return value;
    }

    public ActivityType getType() {
        return type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Activity [id=" + id + ", date=" + date + ", description=" + description + ", value=" + value + ", type="
                + type + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

    
}
