package scu.stomatology.knowledgemindmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import scu.stomatology.knowledgemindmap.repository.entity.UserMindMap;

import java.util.List;

@Component
public interface UserMindMapJpaRepository extends JpaRepository<UserMindMap, Long> {

    UserMindMap findByUserIdAndMindmapId(Long userId, Long mindmapId);

    List<UserMindMap> findByUserId(Long userId);
}
