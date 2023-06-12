package com.medical_declaire_form.repo.impl;

import com.medical_declaire_form.entity.DeclaireForm;
import com.medical_declaire_form.repo.DeclaireFormRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DeclaireFormImpl implements DeclaireFormRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DeclaireForm> findAll() {
        return entityManager.createQuery("select d from DeclaireForm d").getResultList();
    }

    @Override
    public DeclaireForm findById(int id) {
        return entityManager.find(DeclaireForm.class,id);
    }

    @Override
    public void addForm(DeclaireForm declaireForm) {
        entityManager.persist(declaireForm);
    }

    @Override
    public void updateForm(DeclaireForm declaireForm) {
        entityManager.merge(declaireForm);
    }
}
