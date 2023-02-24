package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.*;
import peaksoft.service.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentApi {
    private final AppointmentService appointmentService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final HospitalService hospitalService;

    @GetMapping("/appointments")
    public String getAllAppointments(Model model) {
        model.addAttribute("appointments",
                appointmentService.getAllAppointments());
        return "appointPage";
    }
    @GetMapping
    public String getAllApps(Model model,
                       @RequestParam(name = "keyWord",required = false) String keyWord) {
        model.addAttribute("keyWord",keyWord);
        model.addAttribute("appointments",
                appointmentService.getAllApps(keyWord));
        return "appointPage";
    }



    @GetMapping("/{hospitalId}/new5")
    public String create(Model model, @PathVariable("hospitalId") Long hospitalId) {
        model.addAttribute("newApp", new Appointment());
        model.addAttribute("patients", hospitalService.getAllHospitalPatients(hospitalId));
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("doctors", hospitalService.getAllHospitalDoctor(hospitalId));
        return "newApp";
    }

    @PostMapping("/{hospitalId}/save5")
    public String save(@ModelAttribute("newApp") Appointment appointment, @PathVariable("hospitalId") Long id) {
        appointmentService.saveAppointment(appointment, id);
        return "redirect:/appointments";
    }

    @DeleteMapping("/{appointmentId}/delete")
    public String delete(@PathVariable("appointmentId") Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }

    @GetMapping("{appointmentId}/editApp")
    public String edit(Model model, @PathVariable("appointmentId") Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        Long hospitalId = appointment.getPatient().getHospital().getId();
        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        model.addAttribute("patients", hospitalService.getAllHospitalPatients(hospitalId));
        model.addAttribute("departments", departmentService.getDepartmentById(hospitalId));
        model.addAttribute("doctors", hospitalService.getAllHospitalDoctor(hospitalId));
        return "editApp";
    }

    @PutMapping("{appointmentId}/update")
    public String update(@PathVariable("appointmentId") Long id,
                         @ModelAttribute("appointment") Appointment appointment,
                         @ModelAttribute("patientId") Long patientId,
                         @ModelAttribute("departmentId") Long departmentId,
                         @ModelAttribute("doctorId") Long doctorId) {
        appointmentService.updateAppointment(id, appointment, patientId, departmentId, doctorId);
        return "redirect:/appointments";
    }

}
