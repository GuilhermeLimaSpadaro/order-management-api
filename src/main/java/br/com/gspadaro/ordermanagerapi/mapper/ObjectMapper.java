package br.com.gspadaro.ordermanagerapi.mapper;

import java.util.List;

public interface ObjectMapper<E, Q, R> {
    E toEntity (Q request);
    R toResponseDTO(E entity);
    List<R> toListResponse(List<E> entityList);
}
