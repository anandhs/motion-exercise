package org.motion.model.productType;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "product")
public class Fish extends Product implements Serializable {

    /**
     * Vessel Id of the boat that this fish was caught on.
     */
    @NotNull
    String vesselId;
    /**
     * Fish Caught date in millis
     */
    @NotNull@Min(0)
    long catchDateMillis;


    public Fish() {
        super();
    }

    public Fish(String id, @NotNull String userId, @NotNull String productType, @NotNull String typeDescription, @NotNull Double unitMass, @NotNull Long createTimeMillis, @NotNull Long expiryDateMillis, @NotNull String vesselId, @NotNull @Min(0) long catchDateMillis) {
        super(id, userId, productType, typeDescription, unitMass, createTimeMillis, expiryDateMillis);
        this.vesselId = vesselId;
        this.catchDateMillis = catchDateMillis;
    }

    public String getVesselId() {
        return vesselId;
    }

    public void setVesselId(String vesselId) {
        this.vesselId = vesselId;
    }

    public long getCatchDateMillis() {
        return catchDateMillis;
    }

    public void setCatchDateMillis(long catchDateMillis) {
        this.catchDateMillis = catchDateMillis;
    }
}
