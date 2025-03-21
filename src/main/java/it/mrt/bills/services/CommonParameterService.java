package it.mrt.bills.services;

import it.mrt.bills.dtos.CommonParameterDTO;
import it.mrt.bills.entities.CommonParameter;
import it.mrt.bills.mappers.CommonParameterMapper;
import it.mrt.bills.repositories.CommonParameterRepository;
import it.mrt.bills.repositories.criterias.CommonParameterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CommonParameterService extends DbEntityService<CommonParameter> {

    @Autowired
    private CommonParameterRepository commonParameterRepository;

    @Autowired
    private CommonParameterMapper mapper;

    public CommonParameterService(CommonParameterRepository commonParameterRepository) {
        super(commonParameterRepository);
    }

    public CommonParameter save(CommonParameterDTO dto) {
        CommonParameter commonParameter = mapper.toEntity(dto);

        Specification<CommonParameter> specification = CommonParameterCriteria.filter(commonParameter.getName());
        CommonParameter oldParameter = findOne(specification);

        if (oldParameter != null) {
            commonParameter.setId(oldParameter.getId());
        }

        return save(commonParameter);
    }
}
