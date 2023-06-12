package com.medical_declaire_form.repo;


import com.medical_declaire_form.entity.DeclaireForm;

import java.util.List;

public interface DeclaireFormRepo {
    List<DeclaireForm> findAll();
    DeclaireForm findById(int id);
    void addForm(DeclaireForm declaireForm);
    void updateForm(DeclaireForm declaireForm);
}
