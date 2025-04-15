package it.mrt.bills.services.gas;

import it.mrt.bills.dtos.filters.GasBillFilters;
import it.mrt.bills.dtos.gas.GasBillDTO;
import it.mrt.bills.entities.gas.GasBill;
import it.mrt.bills.mappers.gas.GasBillMapper;
import it.mrt.bills.repositories.criterias.gas.GasBillCriteria;
import it.mrt.bills.repositories.gas.GasBillRepository;
import it.mrt.bills.services.DbEntityService;
import it.mrt.bills.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GasBillService extends DbEntityService<GasBill> {

    @Autowired
    private GasBillRepository gasBillRepository;

    @Autowired
    private GasBillMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GasContractService gasContractService;

    @Autowired
    private GasOfferService gasOfferService;

    public GasBillService(GasBillRepository gasBillRepository) {
        super(gasBillRepository);
    }

    public GasBill save(GasBillDTO dto) {
        GasBill gasBill = mapper.toEntity(dto);
        gasBill.setUser(userService.findById(dto.getUserId()));
        gasBill.setGasContract(gasContractService.findById(dto.getContractId()));
        gasBill.setGasOffer(gasOfferService.findById(dto.getOfferId()));

        return save(gasBill);
    }

    public Collection<GasBill> filter(GasBillFilters filters) {
        Specification<GasBill> specification = GasBillCriteria.filter(filters);

        return filter(specification);
    }
}
