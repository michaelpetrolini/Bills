package it.mrt.bills.controllers.energy;

import it.mrt.bills.dtos.energy.EnergyComparisonRequestDTO;
import it.mrt.bills.dtos.energy.EnergyComparisonResultDTO;
import it.mrt.bills.dtos.energy.EnergyOfferDTO;
import it.mrt.bills.dtos.filters.EnergyOfferFilters;
import it.mrt.bills.entities.energy.EnergyOffer;
import it.mrt.bills.mappers.energy.EnergyOfferMapper;
import it.mrt.bills.services.energy.EnergyOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/energy-offers")
public class EnergyOfferController {

    @Autowired
    private EnergyOfferService energyOfferService;

    @Autowired
    private EnergyOfferMapper mapper;

    @PostMapping("save")
    public ResponseEntity<EnergyOfferDTO> save(@RequestBody EnergyOfferDTO dto) {
        EnergyOffer energyOffer = energyOfferService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(energyOffer));
    }

    @GetMapping("{id}")
    public ResponseEntity<EnergyOfferDTO> getOne(@PathVariable("id") UUID id) {
        EnergyOffer energyOffer = energyOfferService.findById(id);

        if (energyOffer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(energyOffer));
    }

    @GetMapping
    public ResponseEntity<Collection<EnergyOfferDTO>> getAll() {
        List<EnergyOfferDTO> energyOffers = energyOfferService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(energyOffers);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EnergyOfferDTO> delete(@PathVariable UUID id) {
        EnergyOffer energyOffer = energyOfferService.deleteById(id);

        if (energyOffer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(energyOffer));
    }

    @PostMapping("filter")
    public ResponseEntity<Collection<EnergyOfferDTO>> filter(@RequestBody EnergyOfferFilters filters) {
        Collection<EnergyOfferDTO> energyOffers = energyOfferService.filter(filters).stream().map(mapper::toDto).toList();

        return ResponseEntity.ok(energyOffers);
    }

    @PostMapping("compare")
    public ResponseEntity<Collection<EnergyComparisonResultDTO>> compare(@RequestBody EnergyComparisonRequestDTO dto) {
        Collection<EnergyComparisonResultDTO> comparisons = energyOfferService.compare(dto);

        return ResponseEntity.ok(comparisons);
    }
}
