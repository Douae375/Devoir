package com.example.Commandes.Controller;

import com.example.Commandes.Model.Commande;
import com.example.Commandes.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeRepository commandeRepository;

    @Value("${mes-config-ms.commandes-last}")
    private int commandesLastDays;

    public CommandeController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }
    @GetMapping("/config")
    public int getCommandesLastDays() {
        return commandesLastDays;
    }


    @GetMapping
    public List<Commande> getCommandesRecents() {
        LocalDate dateLimite = LocalDate.now().minusDays(commandesLastDays);
        List<Commande> allCommandes = commandeRepository.findAll();

        // Filtrage : ne conserver que les commandes dont la date >= dateLimite
        return allCommandes.stream()
                .filter(c -> c.getDate() != null && !c.getDate().isBefore(dateLimite))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeRepository.save(commande);
    }

    @GetMapping("/{id}")
    public Commande getCommande(@PathVariable Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande updatedCommande) {
        updatedCommande.setId(id);
        return commandeRepository.save(updatedCommande);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeRepository.deleteById(id);
    }
}