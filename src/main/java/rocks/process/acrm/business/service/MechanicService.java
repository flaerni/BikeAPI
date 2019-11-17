package rocks.process.acrm.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import rocks.process.acrm.data.domain.Mechanic;
import rocks.process.acrm.data.repository.MechanicRepository;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.Validator;

@Service
@Validated
public class MechanicService {

    @Autowired
    private MechanicRepository mechanicRepository;
    @Autowired
    Validator validator;

    public void saveMechanic(@Valid Mechanic mechanic) throws Exception {
        if (mechanic.getId() == null) {
            if (mechanicRepository.findByName(mechanic.getName()) != null) {
                throw new Exception("Mechanic with name " + mechanic.getName() + " already created.");
            }
        } else if (mechanicRepository.findByNameAndIdNot(mechanic.getName(), mechanic.getId()) != null) {
            throw new Exception("Email " + mechanic.getEmail() + " already assigned another mechanic.");
        }
        mechanicRepository.save(mechanic);
    }

    public Mechanic getCurrentMechanic() {
        String userName = "Demo";
        return mechanicRepository.findByName(userName);
    }

    @PostConstruct
    private void init() throws Exception {
        Mechanic mechanic = new Mechanic();
        mechanic.setId((long) 0);
        mechanic.setName("Demo");
        mechanic.setEmail("demo@demo.ch");
        mechanic.setPassword("password");
        this.saveMechanic(mechanic);
    }
}
