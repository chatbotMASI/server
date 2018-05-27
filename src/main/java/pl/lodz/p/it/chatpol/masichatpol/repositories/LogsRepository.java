package pl.lodz.p.it.chatpol.masichatpol.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lodz.p.it.chatpol.masichatpol.collections.Log;

import java.util.Optional;

public interface LogsRepository extends MongoRepository<Log, String> {
  Optional<Log> findByConversationId(String conversationId);

  Log findByClientIpAddress(String clientIpAddress);
}
