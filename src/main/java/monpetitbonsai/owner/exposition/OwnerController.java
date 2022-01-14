package monpetitbonsai.owner.exposition;

import monpetitbonsai.owner.OwnerMapper;
import monpetitbonsai.owner.domain.Bonsai;
import monpetitbonsai.owner.domain.Owner;
import monpetitbonsai.owner.domain.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<OwnerDto> findAll(
            @RequestParam(required = false, defaultValue = "-1") int has_more
    ) {
        return ownerService.findAll(has_more).stream().map(OwnerMapper::toOwnerDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> findById(@PathVariable UUID id) {
        return ownerService.findById(id).map(o -> ResponseEntity.ok(OwnerMapper.toOwnerDto(o))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OwnerDto> create(@RequestBody OwnerDto ownerDto) {
        return new ResponseEntity<>(OwnerMapper.toOwnerDto(ownerService.create(OwnerMapper.toOwner(ownerDto))), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OwnerDto> update(@PathVariable UUID id, @RequestBody OwnerDto updatedOwner) {
        return ownerService.update(id, OwnerMapper.toOwner(updatedOwner))
                .map(o -> ResponseEntity.ok(OwnerMapper.toOwnerDto(o)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        ownerService.delete(id);
    }

    @GetMapping("/{id}/bonsais")
    public List<BonsaiDto> getBonsais(@PathVariable UUID id) {
        return ownerService.getBonsais(id).stream().map(OwnerMapper::toBonsaiDto).collect(Collectors.toList());
    }

    @PostMapping("/{owner_id}/bonsais/{bonsai_id}/transfer")
    public ResponseEntity<BonsaiDto> transferBonsai(@PathVariable UUID owner_id, @PathVariable UUID bonsai_id, @RequestBody Owner new_owner) {
        return ownerService.transferBonsai(owner_id, bonsai_id, new_owner).map(b -> ResponseEntity.ok(OwnerMapper.toBonsaiDto(b))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{owner_id}/bonsais")
    public List<BonsaiDto> addBonsai(@PathVariable UUID owner_id, @RequestBody List<Bonsai> bonsais) {
        return ownerService.addBonsai(owner_id, bonsais).stream().map(OwnerMapper::toBonsaiDto).collect(Collectors.toList());
    }
}
