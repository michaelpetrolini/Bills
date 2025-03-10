package it.mrt.bills.services.energy;

import it.mrt.bills.dtos.energy.EnergyConsumptionDTO;
import it.mrt.bills.entities.energy.EnergyConsumption;
import it.mrt.bills.mappers.energy.EnergyConsumptionMapper;
import it.mrt.bills.repositories.energy.EnergyConsumptionRepository;
import it.mrt.bills.services.DbEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyConsumptionService extends DbEntityService<EnergyConsumption> {

    @Autowired
    private EnergyBillService energyBillService;

    @Autowired
    private EnergyConsumptionMapper mapper;

    public EnergyConsumptionService(EnergyConsumptionRepository energyConsumptionRepository) {
        super(energyConsumptionRepository);
    }

    public EnergyConsumption save(EnergyConsumptionDTO dto) {
        EnergyConsumption energyConsumption = mapper.toEntity(dto);
        energyConsumption.setEnergyBill(energyBillService.findById(dto.getBillId()));

        return save(energyConsumption);
    }
}
