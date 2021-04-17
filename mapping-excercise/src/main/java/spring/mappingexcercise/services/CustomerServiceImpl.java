package spring.mappingexcercise.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mappingexcercise.domain.dtos.CustomerExportDto;
import spring.mappingexcercise.domain.dtos.export.CustomerOrderExportDto;
import spring.mappingexcercise.domain.dtos.export.CustomerRootOrderExportDto;
import spring.mappingexcercise.domain.dtos.imports.CustomerImportRootDto;
import spring.mappingexcercise.domain.entities.Customer;
import spring.mappingexcercise.domain.repositories.CustomerRepository;
import spring.mappingexcercise.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final static String CUSTOMER_PATH = "src/main/resources/jsons/customers.json";
    private final static String CUSTOMER_PATH_XML = "src/main/resources/xml/customers.xml";
    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper modelMapper, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCustomers() throws IOException, JAXBException {
//        String content = String.join("",
//                Files.readAllLines(Path.of(CUSTOMER_PATH)));
//
//        CustomerSeedDto[] customerSeedDtos = this.gson.fromJson(content, CustomerSeedDto[].class);
//
//        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {
//            Customer customer = this.modelMapper.map(customerSeedDto, Customer.class);
//            this.customerRepository.saveAndFlush(customer);
//        }
        //  Xml
        CustomerImportRootDto customerImportRootDto = this.xmlParser.parseXml(CustomerImportRootDto.class, CUSTOMER_PATH_XML);

        for (Object customerDto : customerImportRootDto.getCustomers()) {
            Customer customer = this.modelMapper.map(customerDto, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public String orderCustomers() {

        Set<Customer> customers = this.customerRepository.getAllByOrderByYoungDriverAscBirthDateAsc();

        List<CustomerExportDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerExportDto customerDto = this.modelMapper.map(customer, CustomerExportDto.class);
            customerDtos.add(customerDto);
        }
        return this.gson.toJson(customerDtos);

    }

    @Override
    public void exportOrderDto() throws JAXBException {
        String path = "src/main/resources/xml/exported/ordered-customer.xml";

        List<CustomerOrderExportDto> dtos = this.customerRepository.findAllAndSort().stream().
                map(m -> this.modelMapper.map(m, CustomerOrderExportDto.class))
                .collect(Collectors.toList());

        CustomerRootOrderExportDto exportDtos = new CustomerRootOrderExportDto();

        exportDtos.setCustomers(dtos);

        this.xmlParser.exportXml(exportDtos,CustomerRootOrderExportDto.class,path);
    }
}
