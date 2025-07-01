package com.biblio.bibliotheque.service.pret;

import com.biblio.bibliotheque.model.pret.Prolongement;
import com.biblio.bibliotheque.repository.pret.ProlongementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProlongementService {

    @Autowired
    private ProlongementRepository prolongementRepository;

    public List<Prolongement> getAllProlongements() {
        return prolongementRepository.findAll();
    }

    public Optional<Prolongement> getProlongementById(Integer id) {
        return prolongementRepository.findById(id);
    }

    public Prolongement saveProlongement(Prolongement prolongement) {
        return prolongementRepository.save(prolongement);
    }

    public void deleteProlongement(Integer id) {
        prolongementRepository.deleteById(id);
    }
}
