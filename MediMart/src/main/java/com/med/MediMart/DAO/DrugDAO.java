package com.med.MediMart.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.med.MediMart.entity.Drug;
import com.med.MediMart.repository.DrugRepository;

@Repository
@Component
public class DrugDAO {

	@Autowired
	DrugRepository repository;

	public Drug saveDrug(Drug drug) {
		return repository.save(drug);
	}

	public Drug deleteDeleteInfo(int id) {
		Optional<Drug> optional = repository.findById(id);
		if (optional.isPresent()) {
			Drug drug = optional.get();
			repository.delete(drug);
			return drug;
		}
		return null;
	}

	public Drug updateDrug(Drug drug) {
		return repository.save(drug);
	}

	public Optional<Drug> fetchById(int id) {
		return repository.findById(id);
	}

	public Drug fetchByName(String name) {

		Optional<Drug> o = repository.findByDrugname(name);

		return o.isPresent() ? o.get() : null;
	}

	public List<Drug> fetchByAll() {
		return repository.findAll();
	}

}
