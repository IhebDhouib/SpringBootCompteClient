package tn.iit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.iit.entity.Compte;
import tn.iit.entity.User;
import tn.iit.service.CompteService;
import tn.iit.service.UserService;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")  

@RequestMapping("/api/compte")
public class CompteController {

    private final CompteService compteService;
    private final UserService userService;

    // Fetch all accounts
    @GetMapping("/all")
    public ResponseEntity<List<Compte>> findAll() {
        List<Compte> comptes = compteService.findAll();
        return ResponseEntity.ok(comptes);
    }

    // Save a new or updated account
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam("rib") Integer rib,
                                       @RequestParam("solde") float solde,
                                       @RequestParam("userId") Long userId) {
        try {
            User user = userService.findById(userId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            Compte compte = new Compte(rib, user.getUsername(), solde, user);
            compteService.saveOrUpdate(compte);
            return ResponseEntity.ok("Compte saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving compte");
        }
    }

    // Delete an account by RIB
    @DeleteMapping("/delete/{rib}")
    public ResponseEntity<String> delete(@PathVariable Integer rib) {
        try {
            compteService.delete(rib);
            return ResponseEntity.ok("Compte deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the compte");
        }
    }

    // Fetch a specific account by RIB
    @GetMapping("/{rib}")
    public ResponseEntity<Compte> findById(@PathVariable Integer rib) {
        Compte compte = compteService.findById(rib);
        if (compte == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(compte);
    }

    // Update an existing account
    @PutMapping("/update")
    public ResponseEntity<String> updateCompte(@RequestParam Integer rib,
                                               @RequestParam float solde) {
        try {
            Compte compte = compteService.findById(rib);
            if (compte == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compte not found");
            }
            compte.setSolde(solde);
            compteService.saveOrUpdate(compte);
            return ResponseEntity.ok("Compte updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating compte");
        }
    }
}
