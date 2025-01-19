package org.system.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class EmployeeMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <E, D> List<D> mapList(List<E> entityList, Class<D> dtoClass) {
        return entityList.stream()
                .map(entity -> objectMapper.convertValue(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public static <E, D> D mapToDto(E entity, Class<D> dtoClass) {
        return objectMapper.convertValue(entity, dtoClass);
    }

    public static <D, E> E mapToEntity(D dto, Class<E> entityClass) {
        return objectMapper.convertValue(dto, entityClass);
    }
}
