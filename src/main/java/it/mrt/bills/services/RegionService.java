package it.mrt.bills.services;

import it.mrt.bills.dtos.CommonParameterDTO;
import it.mrt.bills.dtos.RegionDTO;
import it.mrt.bills.entities.CommonParameter;
import it.mrt.bills.entities.Region;
import it.mrt.bills.mappers.RegionMapper;
import it.mrt.bills.repositories.DbEntityRepository;
import it.mrt.bills.repositories.RegionRepository;
import it.mrt.bills.repositories.criterias.CommonParameterCriteria;
import it.mrt.bills.repositories.criterias.RegionCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RegionService extends DbEntityService<Region> {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionMapper mapper;

    public RegionService(RegionRepository regionRepository) {
        super(regionRepository);
    }

    public Region save(RegionDTO dto) {
        Region region = mapper.toEntity(dto);

        Specification<Region> specification = RegionCriteria.filterByName(region.getName());
        Region oldRegion = findOne(specification);

        if (oldRegion != null) {
            region.setId(oldRegion.getId());
        }

        return save(region);
    }

    public Set<Region> findAllByNames(List<String> names) {
        Specification<Region> specification = RegionCriteria.filterByNames(names);

        return new HashSet<>(filter(specification));
    }
}
