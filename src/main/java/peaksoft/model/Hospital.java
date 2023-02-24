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
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "hospital_gen")
    @SequenceGenerator(name = "hospital_gen",
            sequenceName = "hospital_seq",
            allocationSize = 1,
            initialValue = 10
    )
    private Long id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Doctor> doctors;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointments;
//    @Transient
//    private Long hospitalId;

    public Hospital(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    @Transient
    private Long appointmentId;

}
