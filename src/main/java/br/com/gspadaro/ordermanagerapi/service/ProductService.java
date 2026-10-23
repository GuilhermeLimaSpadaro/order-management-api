package br.com.gspadaro.ordermanagerapi.service;

import br.com.gspadaro.ordermanagerapi.dto.product.ProductRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.product.ProductResponseDTO;
import br.com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import br.com.gspadaro.ordermanagerapi.mapper.custom.ProductMapper;
import br.com.gspadaro.ordermanagerapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO request) {
        var product = productMapper.toEntity(request);
        var productSaved = productRepository.save(product);
        return productMapper.toResponseDTO(productSaved);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        return productMapper.toResponseDTO(product);
    }

    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO request) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        var productUpdated = productMapper.toUpdateProduct(request, product);
        var productSaved = productRepository.save(productUpdated);
        return productMapper.toResponseDTO(productSaved);
    }

    @Transactional
    public void delete(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        productRepository.delete(product);
    }
}
