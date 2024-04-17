package heymart.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "balance",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "ownerId")
        })
public class Balance {
    @Id
    private Long ownerId;

    @Column(name = "balance_amount", columnDefinition = "bigint default 0")
    @NotNull
    private Long balance;

    public Balance() {
    }

    public Balance(Long ownerId, Long balance) {
        this.ownerId = ownerId;
        this.balance = balance;
    }
}
