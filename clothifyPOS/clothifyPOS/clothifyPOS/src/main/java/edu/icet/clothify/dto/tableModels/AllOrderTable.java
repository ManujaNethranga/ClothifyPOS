package edu.icet.clothify.dto.tableModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.swing.text.StyledEditorKit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AllOrderTable {
    private String orderId;
    private String username;
    private String paymentType;
    private String date;
    private Boolean isReturned;
    private String email;
}
