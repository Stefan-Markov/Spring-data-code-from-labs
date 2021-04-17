package spring.mappingexcercise.domain.dtos.imports;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootDto {
    @XmlElement(name = "part")
    private List<PartImportDto> parts;

    public PartRootDto() {
    }

    public List<PartImportDto> getParts() {
        return parts;
    }

    public void setParts(List<PartImportDto> parts) {
        this.parts = parts;
    }
}
