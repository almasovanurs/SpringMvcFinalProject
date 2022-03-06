package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Teacher;
import peaksoft.service.ServiceCompanyImpl;
import peaksoft.service.ServiceCourseImpl;
import peaksoft.service.ServiceTeacherImpl;

@Controller
@RequestMapping("/teachers/{id}")
public class TeacherController {

    private final ServiceCourseImpl serviceCourse;
    private final ServiceCompanyImpl serviceCompany;
    private final ServiceTeacherImpl serviceTeacher;

    @Autowired
    public TeacherController(ServiceCourseImpl serviceCourse, ServiceCompanyImpl serviceCompany,
                             ServiceTeacherImpl serviceTeacher) {
        this.serviceCourse = serviceCourse;
        this.serviceCompany = serviceCompany;
        this.serviceTeacher = serviceTeacher;

    }

    @GetMapping("/newTeacher")
    public String newTeacher(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        model.addAttribute("courseName", teacher.getCourseName());
        return "teacher/newTeachers";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher, @ModelAttribute("courseName") String courseName, @PathVariable("id") Long id) {
        teacher.setCompany(serviceCompany.getCompanyById(id));
        teacher.setCourse(serviceCourse.getCourseByName(courseName));
        serviceTeacher.saveTeacher(teacher);
        return "redirect:/courses/{id}";
    }

    @DeleteMapping("/{idTeacher}/deleteTeacher")
    public String deleteTeacher(@PathVariable("idTeacher") Long id) {
        serviceTeacher.deleteTeacher(id);
        return "redirect:/courses/{id}";
    }

    @GetMapping("/{idTeacher}/editTeacher")
    public String editTeacher(Model model, @PathVariable("id") Long id, @PathVariable("idTeacher") Long idTeacher) {
        model.addAttribute("teacher", serviceTeacher.getTeacherById(idTeacher));
        model.addAttribute("courseId", id);
        return "teacher/editTeachers";
    }

    @PatchMapping("/{idTeacher}/updateTeacher")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher, @PathVariable("idTeacher") Long idTeacher) {
        serviceTeacher.updateTeacher(idTeacher, teacher);
        return "redirect:/courses/{id}/";
    }
}
