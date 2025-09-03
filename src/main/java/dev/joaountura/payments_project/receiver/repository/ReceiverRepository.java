package dev.joaountura.payments_project.receiver.repository;


import dev.joaountura.payments_project.receiver.models.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
    Receiver findByExternalID(UUID externalID);
}
