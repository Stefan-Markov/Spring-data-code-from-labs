package course.springdata.mapping.service;

import course.springdata.mapping.entity.Address;
import course.springdata.mapping.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    List<Address> getAllAddresses();

    Address getAddressById(Long id);

    Address addAddress(Address address);

    Address updateAddress(Address address);

    Address deleteAddress(Long id);

    Long getAddressCount();
}
