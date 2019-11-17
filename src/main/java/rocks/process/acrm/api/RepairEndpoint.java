/*
 * Copyright (c) 2019. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.acrm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rocks.process.acrm.business.service.RepairService;
import rocks.process.acrm.data.domain.Repair;


import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class RepairEndpoint {
    @Autowired
    private RepairService repairService;

    @PostMapping(path = "/repair", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Repair> postRepair(@RequestBody Repair repair) {
        try {
            repair = repairService.editRepair(repair);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{repairId}")
                .buildAndExpand(repair.getId()).toUri();

        return ResponseEntity.created(location).body(repair);
    }

    @GetMapping(path = "/repair", produces = "application/json")
    public List<Repair> getRepairs() {
        return repairService.findAllRepairs();
    }

    @GetMapping(path = "/repair/{repairId}", produces = "application/json")
    public ResponseEntity<Repair> getRepair(@PathVariable(value = "repairId") String repairId) {
        Repair repair = null;
        try {
            repair = repairService.findRepairById(Long.parseLong(repairId));
        } catch (Exception e) {
            return (ResponseEntity<Repair>) ResponseEntity.notFound();
        }
        return ResponseEntity.ok(repair);
    }

    @PutMapping(path = "/repair/{repairId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Repair> putRepair(@RequestBody Repair repair, @PathVariable(value = "repairId") String repairId) {
        try {
            repair.setId(Long.parseLong(repairId));
            repair = repairService.editRepair(repair);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(repair);
    }

    @DeleteMapping(path = "/repair/{repairId}")
    public ResponseEntity<Void> deleteRepair(@PathVariable(value = "repairId") String repairId) {
        try {
            repairService.deleteRepair(Long.parseLong(repairId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }
}