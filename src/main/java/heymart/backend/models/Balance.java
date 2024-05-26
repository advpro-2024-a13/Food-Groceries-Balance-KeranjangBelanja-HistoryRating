package heymart.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "Balance(" +
                "ownerId=" + ownerId +
                ", balance=" + balance +
                ')';
    }
}
