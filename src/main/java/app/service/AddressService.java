package app.service;

import app.model.Address;
import app.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address save(final Address address) {
        return this.addressRepository.save(address);
    }

    public Address update(final Long id, final Address address) {
        final var addressFound = find(id);
        BeanUtils.copyProperties(address, addressFound, "id");
        return this.addressRepository.save(addressFound);
    }

    public Address find(final Long id) {
        return this.addressRepository
                .findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public void delete(final Long id) {
        this.addressRepository.deleteById(id);
    }
}
