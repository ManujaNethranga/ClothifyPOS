package edu.icet.clothify.dto;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String id;
    private String Name;
    private String email;
    private String paymentType;
    private String date;
    private Boolean isReturned;

}
