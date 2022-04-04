package co.com.sofka.biblioteca.models;

import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Function<ResourceDTO, ResourceModel> MapperToDTO(String id) {
        return updatedDTO -> {
            var dto = new ResourceModel();
            dto.setId(id);
            dto.setFechaPrestamo(updatedDTO.getFechaPrestamo());
            dto.setDisponible(updatedDTO.getDisponible());
            dto.setNombre(updatedDTO.getNombre());
            dto.setTipo(updatedDTO.getTipo());
            dto.setTema(updatedDTO.getTema());
            return dto;
        };
    }

    public Function<ResourceModel, ResourceDTO> MapperToModel() {
        return model -> new ResourceDTO(
            model.getId(),
            model.getFechaPrestamo(),
            model.getDisponible(),
            model.getNombre(),
            model.getTipo(),
            model.getTema()
        );
    }
}