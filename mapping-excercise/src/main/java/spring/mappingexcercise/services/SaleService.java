package spring.mappingexcercise.services;

import javax.xml.bind.JAXBException;

public interface SaleService {
    void seedSales();

    void salesDiscount() throws JAXBException;
}
