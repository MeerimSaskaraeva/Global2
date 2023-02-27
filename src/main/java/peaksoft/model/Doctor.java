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
@Table(name = "doctors")

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "doctor_gen")
    @SequenceGenerator(name = "doctor_gen",
            sequenceName = "doctor_seq",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;
    private String email;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,
            CascadeType.MERGE,CascadeType.DETACH})
    private Hospital hospital;
    @ManyToMany
            (cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH,
//            CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.DETACH}
//            fetch = FetchType.EAGER
            )
    private List<Department> departments;
    @OneToMany(mappedBy = "doctor",cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.DETACH},
            fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    public Doctor(String firstName, String lastName, String position, String email, Hospital hospital, List<Department> departments, List<Appointment> appointments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.hospital = hospital;
        this.departments = departments;
        this.appointments = appointments;
    }

    @Transient
    private Long hospitalId;
    @Transient
    private Long departmentId;

}
