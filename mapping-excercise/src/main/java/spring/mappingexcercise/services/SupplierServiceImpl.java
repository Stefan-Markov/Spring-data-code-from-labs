package spring.mappingexcercise.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mappingexcercise.domain.dtos.imports.SupplierImportDto;
import spring.mappingexcercise.domain.repositories.SupplierRepository;
import spring.mappingexcercise.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final static String SUPPLIER_PATH = "src/main/resources/jsons/suppliers.json";

    private final static String SUPPLIER_PATH_XML = "src/main/resources/xml/suppliers.xml";
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSupplier() throws IOException, JAXBException {
        //gson

//        String content = String.join("", Files
//                .readAllLines(Path.of(SUPPLIER_PATH)));
//
//        SupplierSeedDto[] supplierSeedDtos =
//                this.gson.fromJson(content, SupplierSeedDto[].class);
//
//        for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
//            this.supplierRepository
//                    .saveAndFlush(this.modelMapper.map(supplierSeedDto, Supplier.class));
//        }


        //XMl

        SupplierImportDto supplierImportDto = this.xmlParser
                .parseXml(SupplierImportDto.class, SUPPLIER_PATH_XML);

        System.out.println();

    }

}
