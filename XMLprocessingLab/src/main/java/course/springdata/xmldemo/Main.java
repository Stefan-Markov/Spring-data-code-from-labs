package course.springdata.xmldemo;

import course.springdata.model.Address;
import course.springdata.model.Person;
import course.springdata.model.Persons;
import course.springdata.model.PhoneNumber;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws JAXBException {
        List<Address> addresses = List.of(
                new Address(1L, "Bulgaria", "Sofia", "bul. Tzar Osvoboditel, 50"),
                new Address(1L, "Bulgaria", "Plovdiv", "ul. Gladston, 17")
        );
        List<Person> persons = List.of(
                new Person(1L, "Petar", "Petrov", addresses.get(0),
                        Set.of(new PhoneNumber("+359 885 123456"), new PhoneNumber("+359 2 9731425"))),
                new Person(2L, "Georgi", "Hristov", addresses.get(1),
                        Set.of(new PhoneNumber("+359 887 456677"), new PhoneNumber("+359 32 654237"))),
                new Person(3L, "Stamat", "Petrov", addresses.get(0),
                        Set.of(new PhoneNumber("+359 889 567895"), new PhoneNumber("+359 2 9731425")))
        );

        // 1. Create JAXBContext
        try {
            JAXBContext ctx = JAXBContext.newInstance(Person.class, Persons.class);
            //2. Create Marshaller
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //3. Marshal POJO to XML
//            marshaller.marshal(persons.get(0), new File("person.xml"));
            marshaller.marshal(new Persons(persons), new File(("persons.xml")));
            //4. Marshal multiple persons of persons.xml

            StringWriter printWriter = new StringWriter();
            marshaller.marshal(new Persons(persons), printWriter);
//            System.out.printf("StringWriter %s", printWriter);

            // 5. UnMarshal multiple persons from POJO

            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            Persons unmarshallStack = (Persons) unmarshaller.unmarshal(new File("persons.xml"));

            unmarshallStack.getPersons().forEach(p -> {
                System.out.printf("%5d | %-15.15s | %-15.15s | %-40.40s | %-40.40s |%n",
                        p.getId(), p.getFirstName(), p.getLastName(), p.getAddress().getCity() + " " + p.getAddress().getCountry()
                                + " " + p.getAddress().getStreet()
                        , p.getPhoneNumbers().stream().map(PhoneNumber::getNumber)
                                .collect(Collectors.joining(", ")));
            });

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
