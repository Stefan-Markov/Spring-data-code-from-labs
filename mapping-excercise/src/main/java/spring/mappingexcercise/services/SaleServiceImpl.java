package spring.mappingexcercise.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mappingexcercise.domain.dtos.export.lastExport.SaleExportDto;
import spring.mappingexcercise.domain.dtos.export.lastExport.SaleExportRootDto;
import spring.mappingexcercise.domain.entities.Car;
import spring.mappingexcercise.domain.entities.Customer;
import spring.mappingexcercise.domain.entities.Part;
import spring.mappingexcercise.domain.entities.Sale;
import spring.mappingexcercise.domain.repositories.CarRepository;
import spring.mappingexcercise.domain.repositories.CustomerRepository;
import spring.mappingexcercise.domain.repositories.SaleRepository;
import spring.mappingexcercise.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private SaleRepository saleRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSales() {
        Sale sale = new Sale();
        sale.setCar(getRandomCar());
        sale.setCustomer(getRandomCustomer());
        sale.setDiscount(5);

        Sale sale1 = new Sale();
        sale1.setCar(getRandomCar());
        sale1.setCustomer(getRandomCustomer());
        sale1.setDiscount(10);


        Sale sale2 = new Sale();
        sale2.setCar(getRandomCar());
        sale2.setCustomer(getRandomCustomer());
        sale2.setDiscount(30);


        this.saleRepository.saveAndFlush(sale);
        this.saleRepository.saveAndFlush(sale1);
        this.saleRepository.saveAndFlush(sale2);

    }

    @Override
    public void salesDiscount() throws JAXBException {
        String path = "src/main/resources/xml/exported/sales-discount.xml";
        SaleExportRootDto salesRootDtos = new SaleExportRootDto();
        List<SaleExportDto> saleExportDtos = new ArrayList<>();

        for (Sale sale : this.saleRepository.findAll()) {
            SaleExportDto saleExportDto = this.modelMapper.map(sale, SaleExportDto.class);
            saleExportDto.setDiscount(saleExportDto.getDiscount() / 100);

            double sum = sale.getCar().getParts().stream().mapToDouble(p ->
                    Double.parseDouble(p.getPrice().toString())).sum();

            saleExportDto.setPrice(BigDecimal.valueOf(sum));
            double priceWithDiscount = sum - sum * sale.getDiscount() * 1.0 / 100;
            saleExportDto.setPriceWithDiscount(BigDecimal.valueOf(priceWithDiscount));
            saleExportDtos.add(saleExportDto);
        }
        salesRootDtos.setSales(saleExportDtos);
        this.xmlParser.exportXml(salesRootDtos, SaleExportRootDto.class, path);
    }

    private Customer getRandomCustomer() {
        Random random = new Random();

        long id = (long) random.nextInt((int) this.customerRepository.count()) + 1;

        return this.customerRepository.findById(id).get();
    }

    private Car getRandomCar() {
        Random random = new Random();
        long id = (long) random.nextInt((int) this.carRepository.count()) + 1;
        return this.carRepository.findById(id).get();
    }
}
