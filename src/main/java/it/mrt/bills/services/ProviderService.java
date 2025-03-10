package it.mrt.bills.services;

import it.mrt.bills.dtos.ProviderDTO;
import it.mrt.bills.entities.Provider;
import it.mrt.bills.mappers.ProviderMapper;
import it.mrt.bills.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService extends DbEntityService<Provider> {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderMapper mapper;

    public ProviderService(ProviderRepository providerRepository) {
        super(providerRepository);
    }

    public Provider save(ProviderDTO dto) {
        Provider provider = mapper.toEntity(dto);
        return save(provider);
    }
}
