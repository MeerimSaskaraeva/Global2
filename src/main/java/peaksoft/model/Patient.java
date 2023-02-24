package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.Gender;


import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "patient_gen")
    @SequenceGenerator(name = "patient_gen",
            sequenceName = "patient_seq",
            allocationSize = 1,
            initialValue = 10
    )
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private Gender gender;
    private String email;
    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH,
//            CascadeType.REMOVE,
                    CascadeType.MERGE,
            CascadeType.DETACH})
    private Hospital hospital;
    @OneToMany(mappedBy = "patient",
            cascade = {CascadeType.PERSIST,
                    CascadeType.REFRESH,
            CascadeType.REMOVE,
                    CascadeType.MERGE,
                    CascadeType.DETACH},
            fetch = FetchType.EAGER)
    private List<Appointment> appointments;
    @Transient
    private Long hospitalId;

}
