package com.example.ReactiveCRUD.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Table("LOC_CONTRACT")
public class Contract {
    @Id
    @Column("ID")
    private Long id;

    @NotEmpty(message = "DATE_BEGIN cannot be null or empty")
    @NonNull
    @Column("DATE_BEGIN")
    private LocalDate dateBegin;

    @Column("DATE_END")
    private LocalDate dateEnd;

    @NotEmpty(message = "NUM_CONTRACT cannot be null or empty")
    @NonNull
    @Column("NUM_CONTRACT")
    private String numContract;

    @NotEmpty(message = "SUM cannot be null or empty")
    @NonNull
    @Column("SUM")
    private double sum;

    @Column("COMMENT")
    private String comment;

}
