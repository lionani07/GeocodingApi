package app.service;

import app.model.Address;
import app.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address save(final Address address) {
        return this.addressRepository.save(address);
    }
}
