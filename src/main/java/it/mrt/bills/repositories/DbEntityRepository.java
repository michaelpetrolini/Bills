package it.mrt.bills.repositories;

import it.mrt.bills.entities.DbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.UUID;

@NoRepositoryBean
public interface DbEntityRepository<T extends DbEntity> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {

    T findByIdAndDeletedAtNull(UUID id);

    Collection<T> findByDeletedAtNull();
}
