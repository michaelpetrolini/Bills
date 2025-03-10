package it.mrt.bills.services;


import it.mrt.bills.entities.DbEntity;
import it.mrt.bills.repositories.DbEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public abstract class DbEntityService<T extends DbEntity> {

    protected final DbEntityRepository<T> dbEntityRepository;

    @Autowired
    public DbEntityService(DbEntityRepository<T> dbEntityRepository) {
        this.dbEntityRepository = dbEntityRepository;
    }

    public T findById(UUID id) {
        return dbEntityRepository.findByIdAndDeletedAtNull(id);
    }

    public T save(T entity) {
        return dbEntityRepository.save(entity);
    }

    public Collection<T> findAll() {
        return dbEntityRepository.findByDeletedAtNull();
    }

    public T deleteById(UUID id) {
        T entity = findById(id);

        if(entity == null) {
            return null;
        }

        entity.setDeletedAt(LocalDateTime.now());
        return dbEntityRepository.save(entity);
    }
}
