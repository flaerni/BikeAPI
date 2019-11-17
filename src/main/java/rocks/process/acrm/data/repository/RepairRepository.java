package rocks.process.acrm.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.process.acrm.data.domain.Mechanic;
import rocks.process.acrm.data.domain.Repair;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    List<Repair> findByIdAndMechanicId(Long repairId, Long mechanicId);

    List<Repair> findByCustomerName(String customerName);

    List<Repair> findByMechanicId(Long id);

    List<Repair> findByDate(String date);

}
