package it.mrt.bills.services.energy;

import it.mrt.bills.dtos.energy.EnergyComparisonRequestDTO;
import it.mrt.bills.dtos.energy.EnergyComparisonResultDTO;
import it.mrt.bills.dtos.energy.EnergyOfferDTO;
import it.mrt.bills.dtos.filters.EnergyOfferFilters;
import it.mrt.bills.entities.energy.EnergyOffer;
import it.mrt.bills.mappers.energy.EnergyBonusMapper;
import it.mrt.bills.mappers.energy.EnergyOfferMapper;
import it.mrt.bills.repositories.criterias.EnergyOfferCriteria;
import it.mrt.bills.repositories.energy.EnergyOfferRepository;
import it.mrt.bills.services.CommonParameterService;
import it.mrt.bills.services.DbEntityService;
import it.mrt.bills.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class EnergyOfferService extends DbEntityService<EnergyOffer> {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private CommonParameterService commonParameterService;

    @Autowired
    private EnergyOfferMapper mapper;

    @Autowired
    private EnergyBonusMapper energyBonusMapper;

    public EnergyOfferService(EnergyOfferRepository energyOfferRepository) {
        super(energyOfferRepository);
    }


    public EnergyOffer save(EnergyOfferDTO dto) {
        EnergyOffer energyOffer = mapper.toEntity(dto);
        energyOffer.setProvider(providerService.findById(dto.getProviderId()));
        energyOffer.setCommonParameters(commonParameterService.findAllByNames(dto.getCommonParameters()));

        energyOffer.setEnergyBonuses(dto.getEnergyBonuses().stream()
                .map(energyBonusMapper::toEntity)
                .peek(b -> b.setEnergyOffer(energyOffer))
                .collect(Collectors.toSet()));

        return save(energyOffer);
    }

    public Collection<EnergyOffer> filter(EnergyOfferFilters filters) {
        Specification<EnergyOffer> specification = EnergyOfferCriteria.filter(filters);
        return filter(specification);
    }
}
