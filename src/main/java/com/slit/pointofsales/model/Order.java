package com.slit.pointofsales.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private DAOUser userDAO;
    @Column(name = "order_status")
    private Integer orderStatus;
    @Column(name = "created_at")
    @UpdateTimestamp
    private Timestamp createdAt;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetailsList;

}
