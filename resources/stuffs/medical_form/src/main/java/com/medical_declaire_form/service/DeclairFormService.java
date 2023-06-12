package com.medical_declaire_form.service;

import com.medical_declaire_form.entity.DeclaireForm;
import com.medical_declaire_form.repo.DeclaireFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclairFormService {
    @Autowired
    DeclaireFormRepo declaireFormRepo;
    public List<DeclaireForm> getAll(){
        return declaireFormRepo.findAll();
    }

    public DeclaireForm findById(int id){
        return declaireFormRepo.findById(id);
    }

    public void addForm(DeclaireForm declaireForm){
        declaireFormRepo.addForm(declaireForm);
    }
    public void editForm(DeclaireForm declaireForm){declaireFormRepo.updateForm(declaireForm);}
}
