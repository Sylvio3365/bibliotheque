package com.biblio.bibliotheque.service.reservation;

import com.biblio.bibliotheque.model.reservation.StatutReservation;
import com.biblio.bibliotheque.repository.reservation.StatutReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutReservationService {

    @Autowired
    private final StatutReservationRepository statutReservationRepository;

    public StatutReservationService(StatutReservationRepository statutReservationRepository) {
        this.statutReservationRepository = statutReservationRepository;
    }

    public List<StatutReservation> getAllStatuts() {
        return statutReservationRepository.findAll();
    }

    public Optional<StatutReservation> getStatutById(Integer id) {
        return statutReservationRepository.findById(id);
    }

    public StatutReservation saveStatut(StatutReservation statut) {
        return statutReservationRepository.save(statut);
    }

    public void deleteStatut(Integer id) {
        statutReservationRepository.deleteById(id);
    }

    public void create(StatutReservation statutReservation) {
        statutReservationRepository.save(statutReservation);
    }

    // (Optionnel) Récupérer les statuts par réservation
    // public List<StatutReservation> getStatutsByReservationId(Integer
    // idReservation) {
    // return statutReservationRepository.findByReservationId(idReservation);
    // }
}
