package it.mrt.bills.services.gas;

import it.mrt.bills.dtos.filters.GasOfferFilters;
import it.mrt.bills.dtos.gas.GasComparisonRequestDTO;
import it.mrt.bills.dtos.gas.GasComparisonResultDTO;
import it.mrt.bills.dtos.gas.GasOfferDTO;
import it.mrt.bills.entities.gas.GasOffer;
import it.mrt.bills.mappers.gas.GasBonusMapper;
import it.mrt.bills.mappers.gas.GasOfferMapper;
import it.mrt.bills.repositories.criterias.gas.GasOfferCriteria;
import it.mrt.bills.repositories.gas.GasOfferRepository;
import it.mrt.bills.services.CommonParameterService;
import it.mrt.bills.services.DbEntityService;
import it.mrt.bills.services.ProviderService;
import it.mrt.bills.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class GasOfferService extends DbEntityService<GasOffer> {

    @Autowired
    private GasOfferRepository gasOfferRepository;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private CommonParameterService commonParameterService;

    @Autowired
    private GasOfferMapper mapper;

    @Autowired
    private GasBonusMapper gasBonusMapper;

    public GasOfferService(GasOfferRepository gasOfferRepository) {
        super(gasOfferRepository);
    }

    public GasOffer save(GasOfferDTO dto) {
        GasOffer gasOffer = mapper.toEntity(dto);
        gasOffer.setProvider(providerService.findById(dto.getProviderId()));
        gasOffer.setRegions(regionService.findAllByNames(dto.getRegions()));
        gasOffer.setCommonParameters(commonParameterService.findAllByNames(dto.getCommonParameters()));

        gasOffer.setGasBonuses(dto.getGasBonuses().stream()
                .map(gasBonusMapper::toEntity)
                .peek(b -> b.setGasOffer(gasOffer))
                .collect(Collectors.toSet()));

        return save(gasOffer);
    }

    public Collection<GasOffer> filter(GasOfferFilters filters) {
        Specification<GasOffer> specification = GasOfferCriteria.filter(filters);
        return filter(specification);
    }

    public Collection<GasComparisonResultDTO> compare(GasComparisonRequestDTO request) {
        return gasOfferRepository.calculateComparison(request);
    }
}
