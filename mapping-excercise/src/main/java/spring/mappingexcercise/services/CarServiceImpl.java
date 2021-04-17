package spring.mappingexcercise.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mappingexcercise.domain.dtos.CarExportDtos;
import spring.mappingexcercise.domain.dtos.export.CarExportDto;
import spring.mappingexcercise.domain.dtos.export.CarExportRootDto;
import spring.mappingexcercise.domain.dtos.export.PartExportDto;
import spring.mappingexcercise.domain.dtos.export.PartExportRootDto;
import spring.mappingexcercise.domain.dtos.imports.CarRootImportDto;
import spring.mappingexcercise.domain.entities.Car;
import spring.mappingexcercise.domain.entities.Part;
import spring.mappingexcercise.domain.repositories.CarRepository;
import spring.mappingexcercise.domain.repositories.PartRepository;
import spring.mappingexcercise.utils.XmlParser;


import javax.xml.bind.JAXBException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {
    private final static String CAR_PATH = "src/main/resources/jsons/cars.json";
    private final static String CAR_PATH_XML = "src/main/resources/xml/cars.xml";
    private final static String CAR_PARTS_EXPORT_XML = "src/main/resources/xml/exported/cars-and-parts.xml";
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    @Transactional
    public void seedCars() throws Exception {
//        String content = String.join("", Files.readAllLines(Path.of(CAR_PATH)));
//
//        CarSeedDto[] carSeedDto = this.gson.fromJson(content, CarSeedDto[].class);
//
//        for (CarSeedDto seedDto : carSeedDto) {
//            Car car = this.modelMapper.map(seedDto, Car.class);
//            car.setParts(getRandomParts());
//            this.carRepository.saveAndFlush(car);
//        }
        // Xml
        CarRootImportDto carRootImportDto = this.xmlParser.parseXml(CarRootImportDto.class, CAR_PATH_XML);
        for (Object cartDto : carRootImportDto.getCars()) {
            Car car = this.modelMapper.map(cartDto, Car.class);
            car.setParts(getRandomParts());
            this.carRepository.saveAndFlush(car);
        }


    }

    @Override
    public String findByMaker() throws IOException {
        //Gson export
        Set<Car> maker = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        String path = "src/main/resources/xml/exported/find-by-maker-car.json";
        List<CarExportDtos> exportCars = new ArrayList<>();
        for (Car car : maker) {
            CarExportDtos carExport = this.modelMapper.map(car, CarExportDtos.class);
            exportCars.add(carExport);
        }
        //Gson export
        Writer writer = new FileWriter(path);
        this.gson.toJson(exportCars,writer);
        writer.flush();
        writer.close();

        return this.gson.toJson(exportCars);
    }

    @Override
    public void carsAndParts() throws JAXBException {
        //query
        List<Car> all = this.carRepository.findAll();

        CarExportRootDto carRootDto = new CarExportRootDto();
        List<CarExportDto> carExportDtos = new ArrayList<>();

        for (Car car : all) {
            CarExportDto carExportDto = this.modelMapper.map(car, CarExportDto.class);
//            PartExportRootDto partExportRootDto = new PartExportRootDto();
//            List<PartExportDto> partExportDtos = new ArrayList<>();
//            for (Part part: car.getParts()) {
//                PartExportDto partDto = this.modelMapper.map(part,PartExportDto.class);
//                partExportDtos.add(partDto);
//            }
//            partExportRootDto.setParts(partExportDtos);
//            carExportDto.setParts(partExportRootDto);
            carExportDtos.add(carExportDto);
        }
        carRootDto.setCars(carExportDtos);
        this.xmlParser.exportXml(carRootDto, CarExportRootDto.class, CAR_PARTS_EXPORT_XML);
    }

    private Set<Part> getRandomParts() throws Exception {
        Set<Part> parts = new LinkedHashSet<>();
        for (int i = 0; i < 3; i++) {
            Part part = this.getRandomPart();
            parts.add(part);
        }
        return parts;
    }

    private Part getRandomPart() throws Exception {
        Random random = new Random();
        long index = (long) random.nextInt((int) this.partRepository.count()) + 1;
        Optional<Part> part = this.partRepository.findById(index);
        if (part.isPresent()) {
            return part.get();
        } else {
            throw new Exception("Invalid part Id!");
        }
    }
}
