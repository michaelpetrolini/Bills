package it.mrt.bills.services.energy;

import it.mrt.bills.dtos.energy.EnergyContractDTO;
import it.mrt.bills.dtos.filters.EnergyContractFilters;
import it.mrt.bills.entities.energy.EnergyContract;
import it.mrt.bills.mappers.energy.EnergyContractMapper;
import it.mrt.bills.repositories.criterias.energy.EnergyContractCriteria;
import it.mrt.bills.repositories.energy.EnergyContractRepository;
import it.mrt.bills.services.DbEntityService;
import it.mrt.bills.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EnergyContractService extends DbEntityService<EnergyContract> {

    @Autowired
    private UserService userService;

    @Autowired
    private EnergyOfferService energyOfferService;

    @Autowired
    private EnergyContractMapper mapper;

    public EnergyContractService(EnergyContractRepository energyContractRepository) {
        super(energyContractRepository);
    }

    public EnergyContract save(EnergyContractDTO dto) {
        EnergyContract energyContract = mapper.toEntity(dto);
        energyContract.setUser(userService.findById(dto.getUserId()));
        energyContract.setEnergyOffer(energyOfferService.findById(dto.getOfferId()));

        return save(energyContract);
    }

    public Collection<EnergyContract> filter(EnergyContractFilters filters) {
        Specification<EnergyContract> specification = EnergyContractCriteria.filter(filters);

        return filter(specification);
    }
}
