package edu.icet.clothify.dto.tableModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderTable {
    private String orderId;
    private String userName;
    private String paymentType;
    private String date;
    private Boolean isReturned;
    private String email;
}
