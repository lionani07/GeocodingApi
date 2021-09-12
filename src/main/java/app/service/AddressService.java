package app.service;

import app.adapter.GeocodingAPIAdapter;
import app.model.Address;
import app.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private GeocodingAPIAdapter geocodingAPIAdapter;

    public Address save(final Address address) {

        // Passar para um metodo bonitinho...

        if (!isLatitudeAndLongitudeInformed(address)) {
            final var response = this.geocodingAPIAdapter.findLatitudeAndLongitude(address);
            address.setLatitude(response.getLatitude());
            address.setLongitude(response.getLongitude());
        }

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

    public Page<Address> findAll(Pageable pageable) {
        return this.addressRepository.findAll(pageable);
    }

    private boolean isLatitudeAndLongitudeInformed(Address address) {
        return StringUtils.isAllBlank(address.getLatitude(), address.getLongitude());
    }
}
