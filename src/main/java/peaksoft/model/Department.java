package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "department_gen")
    @SequenceGenerator(name = "department_gen",
            sequenceName = "department_seq",
            allocationSize = 1

    )
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "departments",
            cascade = {CascadeType.PERSIST,
                    CascadeType.REFRESH,
//                    CascadeType.REMOVE,
                    CascadeType.MERGE,
                    CascadeType.DETACH},
            fetch = FetchType.EAGER
    )
    private List<Doctor> doctors;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,
            CascadeType.MERGE,CascadeType.DETACH})
    private Hospital hospital;// new 2
    @Transient
    private Long hospitalId;
}
