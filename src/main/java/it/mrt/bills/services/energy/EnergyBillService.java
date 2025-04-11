package it.mrt.bills.services.energy;

import it.mrt.bills.dtos.energy.EnergyBillDTO;
import it.mrt.bills.dtos.filters.EnergyBillFilters;
import it.mrt.bills.entities.energy.EnergyBill;
import it.mrt.bills.mappers.energy.EnergyBillMapper;
import it.mrt.bills.repositories.criterias.energy.EnergyBillCriteria;
import it.mrt.bills.repositories.energy.EnergyBillRepository;
import it.mrt.bills.services.DbEntityService;
import it.mrt.bills.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EnergyBillService extends DbEntityService<EnergyBill> {

    @Autowired
    private EnergyBillMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EnergyOfferService energyOfferService;

    @Autowired
    private EnergyContractService energyContractService;

    public EnergyBillService(EnergyBillRepository energyBillRepository) {
        super(energyBillRepository);
    }

    public EnergyBill save(EnergyBillDTO dto) {
        EnergyBill energyBill = mapper.toEntity(dto);
        energyBill.setUser(userService.findById(dto.getUserId()));
        energyBill.setEnergyOffer(energyOfferService.findById(dto.getOfferId()));
        energyBill.setEnergyContract(energyContractService.findById(dto.getContractId()));

        return save(energyBill);
    }

    public Collection<EnergyBill> filter(EnergyBillFilters filters) {
        Specification<EnergyBill> specification = EnergyBillCriteria.filter(filters);
        return filter(specification);
    }
}
