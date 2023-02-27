package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.service.AppointmentService;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorApi {
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;
    private final AppointmentService appointmentService;


    public DoctorApi(DoctorService doctorService, HospitalService hospitalService,
                     DepartmentService departmentService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.hospitalService = hospitalService;

        this.departmentService = departmentService;
        this.appointmentService = appointmentService;
    }

    @GetMapping()
    public String getAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctorsPage";
    }

    @GetMapping("/new2")
    public String createDoctor(Model model) {
        model.addAttribute("newDoctor", new Doctor());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "newDoctor";
    }

    @PostMapping("/save2")
    public String save(@ModelAttribute("newDoctor") Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/hospitals";
    }

    @DeleteMapping("{doctorId}/delete")
    public String delete(@PathVariable("doctorId") Long id) {
        doctorService.deleteDoctor(id);// удаляет когда всязь только с dep.
        //doctorService.deleteDoctor(id);//когда 2 раза зяпись идет удаляет когда есть все связи
        return "redirect:/doctors";
    }

    @GetMapping("{doctorId}/editDoctor")
    public String edit(Model model, @PathVariable("doctorId") Long id) {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
//        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "editDoctor";
    }

    @PutMapping("{doctorId}/update")
    public String update(@PathVariable("doctorId") Long id,
                         @ModelAttribute("doctor") Doctor doctor)
//                         @ModelAttribute("hospitalId") Long hospitalId)
    {
        doctorService.updateDoctor(id, doctor);
        return "redirect:/doctors";
    }

    @GetMapping("{doctorId}/departments")
    public String getAllDoctorDepartments(Model model, @PathVariable Long doctorId) {
        model.addAttribute("departments", doctorService.
                getAllDoctorDepartments(doctorId));
        return "departmentsPage";
    }

    @GetMapping("{doctorId}/appointments")
    public String getAllDoctorAppointments(Model model, @PathVariable Long doctorId) {
        model.addAttribute("appointments", doctorService.
                getAllDoctorAppointments(doctorId));
        return "appointPage";
    }

    @GetMapping("/{hospitalId}/assignDep")
    public String assignDep(Model model, @PathVariable("hospitalId") Long hospitalId) {
        model.addAttribute("hospital", hospitalService.getHospitalById(hospitalId));
        model.addAttribute("doctor", hospitalService.getAllHospitalDoctor(hospitalId));
        model.addAttribute("department", hospitalService.getAllHospitalDepartments(hospitalId));
        return "assignDep";
    }

    @PutMapping("/{hospitalId}/addDep")
    public String addDep(
            @PathVariable("hospitalId") Long hospitalId,
            @ModelAttribute("doctorId") Long doctorId,
            @ModelAttribute("departmentId")Long departmentId){
        doctorService.assignDoctorToDepartment(hospitalId,doctorId,departmentId);
        return "redirect:/doctors";
    }
//    @GetMapping("{doctorId}/assignApp")
//    public String assignApp(Model model,@PathVariable("doctorId") Long id) {
//        model.addAttribute("doctor", doctorService.getDoctorById(id));
//        model.addAttribute("appointments",appointmentService.getAllAppointmentDoctors(id));
//        return "assignApp";
//    }
//    @PutMapping("{doctorId}/addApp")
//    public String addApp(@PathVariable("doctorId") Long doctorId,
//                         @ModelAttribute("appointmentId") Long appointmentId){
//        doctorService.assignDoctorToAppointment(doctorId, appointmentId);
//        return "redirect:/doctors";
//    }

}
