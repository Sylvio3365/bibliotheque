package com.biblio.bibliotheque.service.gestion;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.repository.gestion.AdherentRepository;
import com.biblio.bibliotheque.repository.gestion.StatutAdherentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private StatutAdherentRepository statutAdherentRepository;

    public List<Adherent> getAll() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> getById(Integer id) {
        return adherentRepository.findById(id);
    }

    public Adherent save(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void delete(Integer id) {
        adherentRepository.deleteById(id);
    }

    public String getStatutAdherentOnDate(Integer idAdherent, LocalDate date) {
        boolean actif = statutAdherentRepository.findStatutActifByAdherentIdAndDate(idAdherent, date).isPresent();
        return actif ? "actif" : "inactif";
    }

}
