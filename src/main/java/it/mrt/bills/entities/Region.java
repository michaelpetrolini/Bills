package it.mrt.bills.entities;

import it.mrt.bills.entities.gas.GasOffer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Region extends DbEntity {

    @Enumerated(EnumType.STRING)
    private Name name;

    @ManyToMany(mappedBy = "regions", fetch = FetchType.LAZY)
    private Set<GasOffer> gasOffers;

    public enum Name {
        ABRUZZO,
        BASILICATA,
        CALABRIA,
        CAMPANIA,
        EMILIA_ROMAGNA,
        FRIULI_VENEZIA_GIULIA,
        LAZIO,
        LIGURIA,
        LOMBARDIA,
        MARCHE,
        MOLISE,
        PIEMONTE,
        PUGLIA,
        SARDEGNA,
        SICILIA,
        TOSCANA,
        TRENTINO_ALTO_ADIGE,
        UMBRIA,
        VALLE_AOSTA,
        VENETO
    }
}
