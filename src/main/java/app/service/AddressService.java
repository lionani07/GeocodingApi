package app.service;

import app.adapter.GeocodingApiAdapter;
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

    private final GeocodingApiAdapter geocodingApiAdapter;

    public Address save(final Address address) {
        setLocation(address);
        return this.addressRepository.save(address);
    }

    public Address update(final Long id, final Address address) {
        final var addressFound = find(id);
        BeanUtils.copyProperties(address, addressFound, "id");
        return this.save(addressFound);
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

    private void setLocation(final Address address) {
        if (latitudeAndLongitudeNotInformed(address)) {
            final var geocodingResponse = this.geocodingApiAdapter.findLatitudeAndLongitude(address);
            address.setLatitude(geocodingResponse.getLatitude());
            address.setLongitude(geocodingResponse.getLongitude());
        }
    }

    private boolean latitudeAndLongitudeNotInformed(Address address) {
        return StringUtils.isAllBlank(address.getLatitude(), address.getLongitude());
    }
}
