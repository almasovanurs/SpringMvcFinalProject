package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.service.ServiceCompanyImpl;
import peaksoft.service.ServiceCourseImpl;
import peaksoft.service.ServiceGroupImpl;
import peaksoft.service.ServiceTeacherImpl;

@Controller
@RequestMapping("/courses/{id}")
public class CourseController {

    private final ServiceCourseImpl serviceCourse;
    private final ServiceCompanyImpl serviceCompany;
    private final ServiceGroupImpl serviceGroup;
    private final ServiceTeacherImpl serviceTeacher;


    @Autowired
    public CourseController(ServiceCourseImpl serviceCourse, ServiceCompanyImpl serviceCompany,
                            ServiceGroupImpl serviceGroup,ServiceTeacherImpl serviceTeacher) {
        this.serviceCourse = serviceCourse;
        this.serviceCompany = serviceCompany;
        this.serviceGroup = serviceGroup;
        this.serviceTeacher=serviceTeacher;

    }

    @GetMapping("/newCourse")
    public String newCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course/newCourse";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course, @PathVariable("id") Long id) {
        course.setCompany(serviceCompany.getCompanyById(id));
        serviceCourse.save(course);
        return "redirect:/courses/{id}";
    }

    @GetMapping
    public String getCourses(Model model, @PathVariable("id") Long id) {
        model.addAttribute("courseLists", serviceCourse.getCourses(id));
        model.addAttribute("companyId", id);
        model.addAttribute("groupLists", serviceGroup.getGroupByCompanyId(id));
        model.addAttribute("teacherLists", serviceTeacher.getTeachers(id));
        return "course/getCourses";
    }


    @GetMapping("/{idCourse}/editCourse")
    public String editCourse(Model model, @PathVariable("id") Long id,@PathVariable("idCourse")Long idCourse) {
        model.addAttribute("course", serviceCourse.getCourseById(idCourse));
        model.addAttribute("companyId", id);
        return "course/editCourses";
    }

    @PatchMapping("/{idCourse}/updateCourse")
    public String updateCourse(@ModelAttribute("course") Course course, @PathVariable("idCourse") Long idCourse) {
        serviceCourse.updateCourse(idCourse, course);
        return "redirect:/courses/{id}";
    }

    @DeleteMapping("/{idCourse}/deleteCourse")
    public String deleteCourse(@PathVariable("idCourse") Long id) {
        serviceCourse.deleteCourse(id);
        return "redirect:/courses/{id}";
    }

}
