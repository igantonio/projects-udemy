package com.myfinances.entity;

import com.myfinances.entity.enums.StatusExpense;
import com.myfinances.entity.enums.TypeExpense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Integer month;

    @Column
    private Integer year;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    private BigDecimal value;

    @CreationTimestamp
//    @Column
//    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dateRegister;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeExpense typeExpense;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusExpense statusExpense;
}
