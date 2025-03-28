package it.mrt.bills.services.energy;

import it.mrt.bills.dtos.energy.EnergyBonusDTO;
import it.mrt.bills.entities.energy.EnergyBonus;
import it.mrt.bills.entities.energy.EnergyOffer;
import it.mrt.bills.mappers.energy.EnergyBonusMapper;
import it.mrt.bills.repositories.DbEntityRepository;
import it.mrt.bills.repositories.energy.EnergyBonusRepository;
import it.mrt.bills.services.DbEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyBonusService extends DbEntityService<EnergyBonus> {

    @Autowired
    private EnergyBonusRepository energyBonusRepository;

    @Autowired
    private EnergyBonusMapper mapper;

    public EnergyBonusService(DbEntityRepository<EnergyBonus> dbEntityRepository) {
        super(dbEntityRepository);
    }

    public EnergyBonus save(EnergyBonusDTO dto, EnergyOffer energyOffer) {
        EnergyBonus energyBonus = mapper.toEntity(dto);
        energyBonus.setEnergyOffer(energyOffer);

        return save(energyBonus);
    }

}
