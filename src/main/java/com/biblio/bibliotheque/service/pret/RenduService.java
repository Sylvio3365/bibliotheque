package com.biblio.bibliotheque.service.pret;

import com.biblio.bibliotheque.model.pret.Rendu;
import com.biblio.bibliotheque.repository.pret.RenduRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RenduService {

    @Autowired
    private RenduRepository renduRepository;

    public List<Rendu> getAllRendus() {
        return renduRepository.findAll();
    }

    public Optional<Rendu> getRenduById(Integer id) {
        return renduRepository.findById(id);
    }

    public Rendu saveRendu(Rendu rendu) {
        return renduRepository.save(rendu);
    }

    public void deleteRendu(Integer id) {
        renduRepository.deleteById(id);
    }
}
