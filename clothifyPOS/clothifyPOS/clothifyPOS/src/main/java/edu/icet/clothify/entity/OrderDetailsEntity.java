package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orderDescriptionDetails")
public class OrderDetailsEntity {
    @Id
    private String id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "orderId")
    OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "productId")
    ProductEntity productEntity;

}
