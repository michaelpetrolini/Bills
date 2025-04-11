package it.mrt.bills.services.gas;

import it.mrt.bills.dtos.gas.GasBonusDTO;
import it.mrt.bills.entities.gas.GasBonus;
import it.mrt.bills.entities.gas.GasOffer;
import it.mrt.bills.mappers.gas.GasBonusMapper;
import it.mrt.bills.repositories.gas.GasBonusRepository;
import it.mrt.bills.services.DbEntityService;
import org.springframework.beans.factory.annotation.Autowired;

public class GasBonusService extends DbEntityService<GasBonus> {

    @Autowired
    private GasBonusRepository gasBonusRepository;

    @Autowired
    private GasBonusMapper mapper;

    public GasBonusService(GasBonusRepository gasBonusRepository) {
        super(gasBonusRepository);
    }

    public GasBonus save(GasBonusDTO dto, GasOffer gasOffer) {
        GasBonus gasBonus = mapper.toEntity(dto);
        gasBonus.setGasOffer(gasOffer);

        return save(gasBonus);
    }
}
