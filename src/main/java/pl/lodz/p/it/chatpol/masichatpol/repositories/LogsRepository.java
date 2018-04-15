package pl.lodz.p.it.chatpol.masichatpol.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lodz.p.it.chatpol.masichatpol.collections.Log;

public interface LogsRepository extends MongoRepository<Log, String> {
}
