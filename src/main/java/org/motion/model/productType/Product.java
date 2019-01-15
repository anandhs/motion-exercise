package org.motion.model.productType;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document(collection = "product")
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "productType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Coffee.class, name = "coffee"),
        @JsonSubTypes.Type(value = Fish.class, name = "fish")
})
public  class Product implements Serializable {

    /**
     * Primary Key
     */
    @Id
    String id;
    /**
     * UserId of the user that added this Product
     */
    @NotNull
    String userId;
    /**
     * Product Type. Note that this is bounded by the defined types at the top of this class.
     */
    
    String productType;
    /**
     * Product type description
     */
    @NotNull
    String typeDescription;
    /**
     * Unit Mass
     */
    @NotNull
    Double unitMass;
    /**
     * Created Time in Millis. Represents epoch time.
     */
    @NotNull
    Long createTimeMillis;
    /**
     * Product expirty time  in Millis. Represents epoch time.
     */

    @NotNull
    Long expiryDateMillis;


    public Product() {

    }

    public Product(String id, @NotNull String userId,  String productType, @NotNull String typeDescription, @NotNull Double unitMass, @NotNull Long createTimeMillis, @NotNull Long expiryDateMillis) {
        this.id = id;
        this.userId = userId;
        this.productType = productType;
        this.typeDescription = typeDescription;
        this.unitMass = unitMass;
        this.createTimeMillis = createTimeMillis;
        this.expiryDateMillis = expiryDateMillis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public Double getUnitMass() {
        return unitMass;
    }

    public void setUnitMass(Double unitMass) {
        this.unitMass = unitMass;
    }

    public Long getCreateTimeMillis() {
        return createTimeMillis;
    }

    public void setCreateTimeMillis(Long createTimeMillis) {
        this.createTimeMillis = createTimeMillis;
    }

    public Long getExpiryDateMillis() {
        return expiryDateMillis;
    }

    public void setExpiryDateMillis(Long expiryDateMillis) {
        this.expiryDateMillis = expiryDateMillis;
    }
}