package spring.mappingexcercise.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CustomerService {
    void seedCustomers() throws IOException, JAXBException;

    String orderCustomers();

    void exportOrderDto() throws JAXBException;
}
