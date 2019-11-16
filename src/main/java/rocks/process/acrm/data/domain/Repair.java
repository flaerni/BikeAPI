package rocks.process.acrm.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Repair {
    @Id
    @GeneratedValue
    private Long id;
    private String customerName;
    private String bikeColor;
    private String bikeBrand;
    private String workPerformed; //freetext of what labour was performed
    private String date; //DDMMYY
    @ManyToOne
    @JsonBackReference
    private Mechanic mechanic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNameName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getBikeColor() {
        return bikeColor;
    }

    public void setBikeColor(String color) {
        this.bikeColor = color;
    }

    public String getBikeBrand() {
        return bikeBrand;
    }

    public void setBikeBrand(String brand) {
        this.bikeBrand = brand;
    }

    public String getWorkPerformed() {
        return workPerformed;
    }

    public void setWorkPerformed(String freeTextWorkerformed) {
        this.workPerformed = freeTextWorkerformed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

}
