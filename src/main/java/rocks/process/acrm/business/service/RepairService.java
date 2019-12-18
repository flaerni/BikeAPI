/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.acrm.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import rocks.process.acrm.data.domain.Repair;
import rocks.process.acrm.data.repository.MechanicRepository;
import rocks.process.acrm.data.repository.RepairRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private MechanicService mechanicService;

    public Repair editRepair(@Valid Repair repair) {
        repair.setMechanic(mechanicService.getCurrentMechanic());
        return repairRepository.save(repair);

    }

    public void deleteRepair(Long repairId) {
        repairRepository.deleteById(repairId);
    }

    public Repair findRepairById(Long repairId) throws Exception {
        List<Repair> repairList = repairRepository.findByIdAndMechanicId(repairId, mechanicService.getCurrentMechanic().getId());
        if (repairList.isEmpty()) {
            throw new Exception("No repair with ID " + repairId + " found.");
        }
        return repairList.get(0);
    }

    public List<Repair> findAllRepairs() {
        return repairRepository.findByMechanicId(mechanicService.getCurrentMechanic().getId());
    }

}
