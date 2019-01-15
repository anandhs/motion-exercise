package org.motion.model.productType;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document(collection = "product")
public class Coffee extends Product implements Serializable {

    /**
     * Cupping Score, between 0 and 100
     */
    @NotNull
    @Min(value = 0L, message = "cupping Score must be positive")
    @Max(value = 100L, message = "cupping score must be less than 100")
    Double cuppingScore;
    /**
     * Variety. E.g are Arabica, Robusta
     */
    @NotNull
    String variety;


    public Coffee() {
        super();
    }

    public Coffee(String id, @NotNull String userId,  String productType, @NotNull String typeDescription, @NotNull Double unitMass, @NotNull Long createTimeMillis, @NotNull Long expiryDateMillis, @NotNull @Min(value = 0L, message = "cupping Score must be positive") Double cuppingScore, @NotNull String variety) {
        super(id, userId, productType, typeDescription, unitMass, createTimeMillis, expiryDateMillis);
        this.cuppingScore = cuppingScore;
        this.variety = variety;
    }

    public Double getCuppingScore() {
        return cuppingScore;
    }

    public void setCuppingScore(Double cuppingScore) {
        this.cuppingScore = cuppingScore;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }
}
