package by.itechart.carrier.entity;

import by.itechart.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "driver")
@Document(indexName = "warehouse", type = "driver")
public class Driver extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id")
    @Field(type = FieldType.Nested, includeInParent = true)
    private Carrier carrier;

    @Column(name = "info")
    private String info;

    @Column(name = "deleted")
    private LocalDate deleted;
}
