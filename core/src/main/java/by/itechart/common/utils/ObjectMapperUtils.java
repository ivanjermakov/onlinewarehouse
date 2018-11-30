package by.itechart.common.utils;

import by.itechart.consignmentnote.dto.UpdateConsignmentNoteDto;
import by.itechart.consignmentnote.entity.ConsignmentNote;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapperUtils {

    private static ModelMapper modelMapper = new ModelMapper();

    private ObjectMapperUtils() {
    }

    static {
        TypeMap<UpdateConsignmentNoteDto, ConsignmentNote> typeMap = modelMapper
                .createTypeMap(UpdateConsignmentNoteDto.class, ConsignmentNote.class);
        typeMap.addMappings(mapper -> {
//            mapper.skip(ConsignmentNote::setCarrier);
//            mapper.skip(ConsignmentNote::setCarrier);
//            mapper.skip(ConsignmentNote::setDriver);
            mapper.<Long>skip((d, v) -> d.getCounterparty().setId(v));
            mapper.<Long>skip((d, v) -> d.getCarrier().setId(v));
            mapper.<Long>skip((d, v) -> d.getDriver().setId(v));
        });
    }

    public static <D, T> D map(T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> mapAll(Collection<T> entities, Class<D> outCLass) {
        return entities.stream()
                .map(e -> map(e, outCLass))
                .collect(Collectors.toList());
    }

    public static void map(Object source, Object destination) {
        modelMapper.map(source, destination);
    }
}
