package com.biblio.bibliotheque.service.pret;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblio.bibliotheque.model.pret.Rendu;
import com.biblio.bibliotheque.repository.pret.RenduRepository;

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

    public void saveRendu(Rendu rendu) {
        renduRepository.save(rendu);
    }    

    public void deleteRendu(Integer id) {
        renduRepository.deleteById(id);
    }

    public boolean isExistPret(Integer idAdherent) {
        return renduRepository.existsById(idAdherent);
    }

}
