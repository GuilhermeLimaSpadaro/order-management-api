package br.com.gspadaro.ordermanagerapi.service;

import br.com.gspadaro.ordermanagerapi.dto.product.ProductRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.product.ProductResponseDTO;
import br.com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import br.com.gspadaro.ordermanagerapi.mapper.custom.ProductMapper;
import br.com.gspadaro.ordermanagerapi.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO request) {
        logger.info("Create product");
        var product = productMapper.toEntity(request);
        var savedProduct = productRepository.save(product);
        logger.info("Product successfully created. ID: {}", savedProduct.getId());
        return productMapper.toResponseDTO(savedProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long productId) {
        logger.info("Finding Product.");
        var product = productRepository.findById(productId).orElseThrow(() -> {
            logger.warn("Product not found on findById. ID: {}", productId);
            return new ResourceNotFoundException("Product not found with ID: " + productId);
        });
        logger.info("Product successfully found. ID: {}", productId);
        return productMapper.toResponseDTO(product);
    }

    @Transactional
    public ProductResponseDTO update(Long productId, ProductRequestDTO request) {
        var product = productRepository.findById(productId).orElseThrow(() -> {
            logger.warn("Product not found on update. ID: {}", productId);
            return new ResourceNotFoundException("Product not found with ID: " + productId);});
        var productUpdated = productMapper.toUpdateProduct(request, product);
        var productSaved = productRepository.save(productUpdated);
        logger.info("Product successfully updated. ID: {}", productId);
        return productMapper.toResponseDTO(productSaved);
    }

    @Transactional
    public void delete(Long productId) {
        logger.info("Product Delete.");
        var product = productRepository.findById(productId).orElseThrow(() -> {
            logger.warn("Product not found on delete. ID: {}", productId);
            return new ResourceNotFoundException("Product not found with ID: " + productId);});
        productRepository.delete(product);
        logger.info("Product successfully deleted. ID: {}", productId);
    }
}
