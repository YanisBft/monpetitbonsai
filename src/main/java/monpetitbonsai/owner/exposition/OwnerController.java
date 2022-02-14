package monpetitbonsai.owner.exposition;

import monpetitbonsai.authentication.domain.AppUser;
import monpetitbonsai.owner.OwnerMapper;
import monpetitbonsai.owner.domain.Bonsai;
import monpetitbonsai.owner.domain.Owner;
import monpetitbonsai.owner.domain.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<OwnerDto> findAll(
            @RequestParam(required = false, defaultValue = "-1") int has_more
    ) {
        return ownerService.findAll(has_more).stream().map(OwnerMapper::toOwnerDto).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('STAFF')")
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

    @PreAuthorize("hasAuthority('ADMIN')")
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
        AppUser credentials = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isOwner = credentials.getId().equals(owner_id);
        boolean isAdmin = credentials.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));

        if (isOwner || isAdmin) {
            return ownerService.transferBonsai(owner_id, bonsai_id, new_owner).map(b -> ResponseEntity.ok(OwnerMapper.toBonsaiDto(b))).orElse(ResponseEntity.notFound().build());
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/{owner_id}/bonsais")
    public ResponseEntity<List<BonsaiDto>> addBonsai(@PathVariable UUID owner_id, @RequestBody List<Bonsai> bonsais) {
        AppUser credentials = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isOwner = credentials.getId().equals(owner_id);
        boolean isAdmin = credentials.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"));

        if (isOwner || isAdmin) {
            return ResponseEntity.ok(ownerService.addBonsai(owner_id, bonsais).stream().map(OwnerMapper::toBonsaiDto).collect(Collectors.toList()));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
