package spring.mappingexcercise.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CarService {
    void seedCars() throws Exception;
    String findByMaker() throws IOException;

    void carsAndParts() throws JAXBException;
}
