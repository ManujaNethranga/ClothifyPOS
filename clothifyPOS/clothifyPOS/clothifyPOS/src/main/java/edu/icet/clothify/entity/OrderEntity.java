package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.lang.model.element.Name;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orderDetails")
public class OrderEntity {
    @Id
    private String id;
    private String Name;
    private String email;
    private String paymentType;
    private String date;
    private Boolean isReturned;

    @OneToMany(mappedBy = "orderEntity")
    List<OrderDetailsEntity> list;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity userEntity;


}
