package com.biblio.bibliotheque.service.pret;

import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

    public List<Pret> getAllPrets() {
        return pretRepository.findAll();
    }

    public Optional<Pret> getPretById(Integer id) {
        return pretRepository.findById(id);
    }

    public Pret savePret(Pret pret) {
        return pretRepository.save(pret);
    }

    public void deletePret(Integer id) {
        pretRepository.deleteById(id);
    }
}
