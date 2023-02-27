package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.service.AppointmentService;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalApi {

    private final HospitalService hospitalService;
    private final AppointmentService appointmentService;

    private final DepartmentService departmentService;
    @GetMapping
    public String getAllHospitals(Model model){
        model.addAttribute("hospitals",hospitalService.getAllHospitals());
        return "mainPage";
    }

    @GetMapping("/new")
    public String createHospital(Model model){
        model.addAttribute("newHospital",new Hospital());
        return "newHospital";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("newHospital") Hospital hospital){
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }
    @DeleteMapping("{hospitalId}/delete")
    public String delete(@PathVariable("hospitalId") Long id){
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }
    @GetMapping("{hospitalId}/edit")
    public String edit(Model model,@PathVariable("hospitalId") Long id){
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        return "edit";
    }
    @PutMapping("{hospitalId}/update")
    public String update(@PathVariable("hospitalId")Long id,@ModelAttribute("hospital") Hospital hospital){
        hospitalService.updateHospital(id,hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("{hospitalId}/departments")
    public String getAllHospitalDepartments(Model model, @PathVariable Long hospitalId) {
        model.addAttribute("departments", hospitalService.
                getAllHospitalDepartments(hospitalId));
        return "departmentsPage";
    }
    @GetMapping("{hospitalId}/doctors")
    public String getAllHospitalDoctor(Model model, @PathVariable Long hospitalId){
        model.addAttribute("doctors",hospitalService.getAllHospitalDoctor(hospitalId));
        return "doctorsPage";
    }
    @GetMapping("{hospitalId}/patients")
    public String getAllHospitalPatients(Model model,@PathVariable Long hospitalId){
        model.addAttribute("patients",hospitalService.getAllHospitalPatients(hospitalId));
        return "patientPage";

    }
    @GetMapping("{hospitalId}/appointments")
    public String getAllHospitalAppointments(Model model, @PathVariable Long hospitalId) {
        model.addAttribute("appointments", hospitalService.
                getAllHospitalAppointments(hospitalId));
        return "appointPage";
    }
    @GetMapping("{hospitalId}/assignApp")
    public String assignApp(Model model,@PathVariable("hospitalId") Long id) {
        model.addAttribute("hospital", hospitalService.getHospitalById(id));
        model.addAttribute("appointment",hospitalService.getAllHospitalAppointments(id));
        return "assignApp";
    }
    @PutMapping("{hospitalId}/addApp")
    public String addApp(@PathVariable("hospitalId") Long hospitalId,
                         @ModelAttribute("appointmentId") Long appointmentId){
        hospitalService.assignHospitalToAppointment(hospitalId, appointmentId);
        return "redirect:/hospitals";
    }


    //Delete Department
    //Hospital Department no connection
    //Update Doctor
    //Update Patient
    //Update Appointment




}

