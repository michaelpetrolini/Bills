package it.mrt.bills.services.gas;

import it.mrt.bills.dtos.filters.GasConsumptionFilters;
import it.mrt.bills.dtos.gas.GasConsumptionDTO;
import it.mrt.bills.entities.gas.GasConsumption;
import it.mrt.bills.mappers.gas.GasConsumptionMapper;
import it.mrt.bills.repositories.criterias.gas.GasConsumptionCriteria;
import it.mrt.bills.repositories.gas.GasConsumptionRepository;
import it.mrt.bills.services.DbEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GasConsumptionService extends DbEntityService<GasConsumption> {

    @Autowired
    private GasConsumptionRepository gasConsumptionRepository;

    @Autowired
    private GasBillService gasBillService;

    @Autowired
    private GasConsumptionMapper mapper;

    public GasConsumptionService(GasConsumptionRepository gasConsumptionRepository) {
        super(gasConsumptionRepository);
    }

    public GasConsumption save(GasConsumptionDTO dto) {
        GasConsumption gasConsumption = mapper.toEntity(dto);
        gasConsumption.setGasBill(gasBillService.findById(dto.getBillId()));

        return save(gasConsumption);
    }

    public Collection<GasConsumption> filter(GasConsumptionFilters filters) {
        Specification<GasConsumption> specification = GasConsumptionCriteria.filter(filters);

        return filter(specification);
    }
}
