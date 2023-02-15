package com.manageBudget.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name can't be blank")
    @Size(min =2, max= 30)
    private String name;
    @Size(min =2, max= 30)
    private String accountNumber;
    @Size(max= 150)
    private String description;
    @Min(1)
    @Max(3)
    private Integer priority;
    private Double currentBalance;

    //@PrePersist annotation to specify a method that will be executed
    // before a new object is persisted to a database.
    @PrePersist
    public void setBalance(){this.currentBalance = new Double(0);}

}
