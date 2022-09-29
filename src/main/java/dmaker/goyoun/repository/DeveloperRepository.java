package dmaker.goyoun.repository;

import dmaker.goyoun.entity.Developer;
import dmaker.goyoun.status.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
  
  Optional<Developer> findByMemberId(String memberId);
  
  List<Developer> findDevelopersStatusCodeEquals(StatusCode statusCode);
}
