package by.itechart.homecard;

import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "home_card")
public class HomeCard extends BaseEntity {
    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "src")
    private String src;

    @Column(name = "content")
    private String content;
}
