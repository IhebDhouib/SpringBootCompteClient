package tn.iit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import exception.CompteNotFoundException;
import lombok.RequiredArgsConstructor;
import tn.iit.dao.CompteRepository;
import tn.iit.entity.Compte;

@RequiredArgsConstructor
@Transactional
@Service
public class CompteService {
    private final CompteRepository compteRepository;

    public void saveOrUpdate(Compte compte) {
        compteRepository.save(compte);
    }

    public List<Compte> findAll() {
        return compteRepository.findAll();
    }

    public void delete(Integer rib) {
        compteRepository.deleteById(rib);
    }

    public Compte findById(Integer rib) {
        return compteRepository.findById(rib)
                .orElseThrow(() -> new CompteNotFoundException("Compte with rib= " + rib + " is Not Found"));
    }
}
