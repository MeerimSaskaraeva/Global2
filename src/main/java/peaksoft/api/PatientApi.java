package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.Gender;
import peaksoft.model.Patient;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

@Controller
@RequiredArgsConstructor
@RequestMapping("patients")
public class PatientApi {
    private final PatientService patientService;
    private final HospitalService hospitalService;

    @GetMapping()
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patientPage";
    }

    @GetMapping("/new3")
    public String create(Model model) {
        model.addAttribute("newPatient", new Patient());
//        model.addAttribute("gender"),
        model.addAttribute("hospital", hospitalService.getAllHospitals());
        return "newPatient";
    }

    @PostMapping("/save3")
    public String save(@ModelAttribute("newPatient") Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/hospitals";
    }

    @DeleteMapping("{patientId}/delete")
    public String delete(@PathVariable("patientId") Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @GetMapping("{patientId}/editPatient")
    public String edit(Model model, @PathVariable("patientId") Long id) {
        model.addAttribute("patient", patientService.getPatientById(id));
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "editPatient";
    }

    @PutMapping("{patientId}/update")
    public String update(@PathVariable("patientId") Long id, @ModelAttribute("patient") Patient patient){
        patientService.updatePatient(id, patient);
        return "redirect:/patients";
    }


}
