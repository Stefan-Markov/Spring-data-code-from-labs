package course.springdata.mapping.service;

import course.springdata.mapping.dao.AddressRepository;
import course.springdata.mapping.entity.Address;
import course.springdata.mapping.entity.Employee;
import course.springdata.mapping.exception.NonExistingEntityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;

    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() ->
                new NonExistingEntityException(
                        String.format("Address with id %s does not exist!", id)));
    }

    @Override
    @Transactional
    public Address addAddress(Address address) {
        address.setId(null);

        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address updateAddress(Address address) {
        getAddressById(address.getId());
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address deleteAddress(Long id) {
        Address address = getAddressById(id);
        addressRepository.delete(address);
        return address;
    }

    @Override
    public Long getAddressCount() {
        return addressRepository.count();
    }
}
