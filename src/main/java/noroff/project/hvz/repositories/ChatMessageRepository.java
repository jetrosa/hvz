package noroff.project.hvz.repositories;

import noroff.project.hvz.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    Set<ChatMessage> findAllByGameId(final int gameId);
    Set<ChatMessage> findAllBySquadId(final int squadId);
    Set<ChatMessage> findAllByGameIdAndIsHumanGlobalAndIsZombieGlobal(final int gameId, final boolean isHumanGlobal, final boolean isZombieGlobal);
}
