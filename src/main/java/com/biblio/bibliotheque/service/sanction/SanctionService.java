package com.biblio.bibliotheque.service.sanction;

import com.biblio.bibliotheque.model.sanction.Sanction;
import com.biblio.bibliotheque.repository.sanction.SanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SanctionService {

    private final SanctionRepository sanctionRepository;

    @Autowired
    public SanctionService(SanctionRepository sanctionRepository) {
        this.sanctionRepository = sanctionRepository;
    }

    public List<Sanction> getAllSanctions() {
        return sanctionRepository.findAll();
    }

    public Optional<Sanction> getSanctionById(Integer id) {
        return sanctionRepository.findById(id);
    }

    public Sanction saveSanction(Sanction sanction) {
        return sanctionRepository.save(sanction);
    }

    public void deleteSanction(Integer id) {
        sanctionRepository.deleteById(id);
    }

    public boolean isAdherentSanctioned(Integer idAdherent, LocalDateTime checkDate) {
        return sanctionRepository.isAdherentSanctioned(idAdherent, checkDate);
    }
}