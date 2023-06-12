package com.medical_declaire_form.controller;

import com.medical_declaire_form.entity.DeclaireForm;
import com.medical_declaire_form.service.DeclairFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class DeclaireController{
    @Autowired
    DeclairFormService declairFormService;

    @GetMapping
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("medical_declaire_form/list");
        modelAndView.addObject("formList", declairFormService.getAll());
        return modelAndView;
    }

    @GetMapping("new")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("medical_declaire_form/create_form");
        DeclaireForm declaireForm = new DeclaireForm();
        Date currentDate = new Date();
        declaireForm.setDeclaireDate(currentDate);
        modelAndView.addObject("form", declaireForm);
        modelAndView.addObject("genderList", new String[]{"male", "female", "others"});
        modelAndView.addObject("vehicles", new String[]{"Tàu bay", "Tàu thuyền", "Ô tô", "Khác"});
        return modelAndView;
    }

    @PostMapping("**/new")
    public String addForm(@ModelAttribute("form") DeclaireForm form) {
        if (declairFormService.findById(form.getId()) != null) {
            declairFormService.editForm(form);
            return "redirect:/";
        }
        declairFormService.addForm(form);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable int id){
        ModelAndView modelAndView =new ModelAndView("medical_declaire_form/update_form");
        modelAndView.addObject("form",declairFormService.findById(id));
        modelAndView.addObject("genderList", new String[]{"male", "female", "others"});
        modelAndView.addObject("vehicles", new String[]{"Tàu bay", "Tàu thuyền", "Ô tô", "Khác"});
        return modelAndView;
    }



}
