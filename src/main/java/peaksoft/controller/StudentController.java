package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Student;
import peaksoft.service.ServiceGroupImpl;
import peaksoft.service.ServiceStudentImpl;

@Controller
@RequestMapping("students/{id}")
public class StudentController {

    ServiceStudentImpl serviceStudent;
    ServiceGroupImpl serviceGroup;

    @Autowired
    public StudentController(ServiceStudentImpl serviceStudent, ServiceGroupImpl serviceGroup) {
        this.serviceStudent = serviceStudent;
        this.serviceGroup = serviceGroup;
    }

    @GetMapping("/newStudent")
    public String newStudent(Model model, @PathVariable("id") Long id) {
        model.addAttribute("student", new Student());
        return "student/newStudents";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student, @PathVariable("id") Long id) {
        student.setGroups(serviceGroup.getGroupById(id));
        serviceStudent.saveStudent(student);
        return "redirect:/students/{id}";
    }

    @GetMapping
    public String getStudents(Model model, @PathVariable("id") Long id) {
        model.addAttribute("studentLists", serviceStudent.getStudents(id));
        model.addAttribute("groupId", id);
        return "student/getStudents";
    }

    @DeleteMapping("{idStudent}/deleteStudent")
    public String deleteStudent(@PathVariable("idStudent") Long id) {
        serviceStudent.deleteStudent(id);
        return "redirect:/students/{id}";
    }

    @GetMapping("/{idStudent}/editStudent")
    public String editStudent(Model model, @PathVariable("id") Long id,@PathVariable ("idStudent")Long idStudent){
        model.addAttribute("idStudent", serviceStudent.getStudentById(idStudent));
        model.addAttribute("groupId", id);
        return "student/editStudents";
    }

    @PatchMapping("/{idStudent/updateStudent}")
    public String updateStudent(@ModelAttribute("student") Student student, @PathVariable("idStudent") Long idStudent) {
        serviceStudent.updateStudent(idStudent, student);
        return "redirect:/students/{id}";
    }
}