package by.itechart.common.utils;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapperUtils {

    private static ModelMapper modelMapper = new ModelMapper();

    //    do we need this?
    private ObjectMapperUtils() {
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
