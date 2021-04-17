package spring.mappingexcercise.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.mappingexcercise.utils.ValidatorUtilImpl;
import spring.mappingexcercise.utils.ValidatorUtils;
import spring.mappingexcercise.utils.XmlParser;
import spring.mappingexcercise.utils.XmlParserImpl;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement jsonElement,
                                                     Type type, JsonDeserializationContext jsonDeserializationContext)
                            throws JsonParseException {
                        return LocalDateTime.parse(jsonElement.getAsString(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    }
                })
                .create();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidatorUtils validatorUtils() {
        return new ValidatorUtilImpl(validator());
    }

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }
}
