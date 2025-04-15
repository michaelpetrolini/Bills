package it.mrt.bills.services.gas;

import it.mrt.bills.dtos.filters.GasContractFilters;
import it.mrt.bills.dtos.gas.GasContractDTO;
import it.mrt.bills.entities.gas.GasContract;
import it.mrt.bills.mappers.gas.GasContractMapper;
import it.mrt.bills.repositories.criterias.gas.GasContractCriteria;
import it.mrt.bills.repositories.gas.GasContractRepository;
import it.mrt.bills.services.DbEntityService;
import it.mrt.bills.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GasContractService extends DbEntityService<GasContract> {

    @Autowired
    private GasContractRepository gasContractRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GasOfferService gasOfferService;

    @Autowired
    private GasContractMapper mapper;


    public GasContractService(GasContractRepository gasContractRepository) {
        super(gasContractRepository);
    }

    public GasContract save(GasContractDTO dto) {
        GasContract gasContract = mapper.toEntity(dto);
        gasContract.setUser(userService.findById(dto.getUserId()));
        gasContract.setGasOffer(gasOfferService.findById(dto.getOfferId()));

        return save(gasContract);
    }

    public Collection<GasContract> filter(GasContractFilters filters) {
        Specification<GasContract> specification = GasContractCriteria.filter(filters);

        return filter(specification);
    }
}
