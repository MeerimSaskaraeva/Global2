package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "appointment_gen")
    @SequenceGenerator(name = "appointment_gen",
            sequenceName = "appointment_seq",
            allocationSize = 1,
            initialValue = 10
    )
    private Long id;
    private LocalDate date;
    @ManyToOne
            (cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH,
//            CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.DETACH},
            fetch = FetchType.EAGER)
    private Patient patient;
    @ManyToOne
            (cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH,
//            CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.DETACH}
//            fetch = FetchType.EAGER
            )
    private Doctor doctor;
    @ManyToOne
            (cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
//            CascadeType.MERGE,
            CascadeType.DETACH},
            fetch = FetchType.EAGER)
    private Department department;
    @Transient
    private Long patientId;
    @Transient
    private Long departmentId;
    @Transient
    private Long doctorId;
    @Transient
    private Long hospitalId;

}
