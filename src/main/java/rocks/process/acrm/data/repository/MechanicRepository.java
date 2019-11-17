package rocks.process.acrm.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.process.acrm.data.domain.Mechanic;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    Mechanic findByName(String name);
    Mechanic findByNameAndIdNot(String name, Long id);

}
