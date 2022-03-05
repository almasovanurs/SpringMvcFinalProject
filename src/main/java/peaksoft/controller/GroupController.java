package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Group;
import peaksoft.service.ServiceCompanyImpl;
import peaksoft.service.ServiceCourseImpl;
import peaksoft.service.ServiceGroupImpl;
@Controller
@RequestMapping("/groups/{id}")
public class GroupController {

    private final ServiceCourseImpl serviceCourse;
    private final ServiceCompanyImpl serviceCompany;
    private final ServiceGroupImpl serviceGroup;


    @Autowired
    public GroupController(ServiceCourseImpl serviceCourse, ServiceCompanyImpl serviceCompany,
                            ServiceGroupImpl serviceGroup) {
        this.serviceCourse = serviceCourse;
        this.serviceCompany = serviceCompany;
        this.serviceGroup = serviceGroup;

    }
    @GetMapping("/newGroup")
    public String newGroup(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        model.addAttribute("courseName", group.getCourseName());
        return "group/newGroups";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group, @ModelAttribute("courseName") String courseName, @PathVariable("id") Long id) {
        group.setCompany(serviceCompany.getCompanyById(id));
        group.setCourses(serviceCourse.getCourseByName(courseName));
        serviceGroup.saveGroup(group);
        return "redirect:/courses/{id}/";
    }


    @DeleteMapping("/{idGroup}/deleteGroup")
    public String deleteGroup(@PathVariable("idGroup") Long id) {
        serviceGroup.deleteGroup(id);
        return "redirect:/courses/{id}/";
    }

    @GetMapping("/{idGroup}/editGroup")
    public String editGroup(Model model, @PathVariable("id") Long id,@PathVariable("idGroup")Long idGroup) {
        model.addAttribute("group", serviceGroup.getGroupById(idGroup));
        model.addAttribute("courseId", id);
        return "group/editGroups";
    }

    @PatchMapping("/{idGroup}/updateGroup")
    public String updateGroup(@ModelAttribute("group") Group group, @PathVariable("idGroup") Long idGroup) {
        serviceGroup.updateGroup(idGroup, group);
        return "redirect:/courses/{id}/";
    }
}
