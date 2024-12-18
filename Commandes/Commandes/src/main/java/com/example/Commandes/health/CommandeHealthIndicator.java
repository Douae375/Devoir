package com.example.Commandes.health;


import com.example.Commandes.Repository.CommandeRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CommandeHealthIndicator implements HealthIndicator {

    private final CommandeRepository commandeRepository;

    public CommandeHealthIndicator(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Health health() {
        long count = commandeRepository.count(); // Compte le nombre de commandes
        if (count > 0) {
            // Si des commandes existent, l'état est UP
            return Health.up()
                    .withDetail("Commande count", count)
                    .build();
        } else {
            // Sinon, l'état est DOWN
            return Health.down()
                    .withDetail("Commande count", count)
                    .build();
        }
    }
}
