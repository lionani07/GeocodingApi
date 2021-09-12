package app.controller;

import app.model.Address;
import app.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody @Valid final Address address) {
        final var addressCreated = this.addressService.save(address);

        final var location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(addressCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(addressCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable final Long id, @RequestBody @Valid final Address address) {
        final var pessoaUpdated = this.addressService.update(id, address);
        return ResponseEntity.ok(pessoaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        this.addressService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> find(@PathVariable final Long id) {
        final var addressFound = this.addressService.find(id);
        return ResponseEntity.ok(addressFound);
    }

    @GetMapping
    public Page<Address> findAll(final Pageable pageable) {
        return this.addressService.findAll(pageable);
    }

}
