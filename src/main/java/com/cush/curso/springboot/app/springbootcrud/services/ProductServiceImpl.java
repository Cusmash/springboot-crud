package com.cush.curso.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cush.curso.springboot.app.springbootcrud.entities.Product;
import com.cush.curso.springboot.app.springbootcrud.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id); //se vuelve optional al hacer consulta a DB

        //el ifPresent es void y al no devolver nada, no se puede devolver la validacion que se hizo por eso se cambia por un if normal
        // productOptional.ifPresent(productDb -> {   
        //     productDb.setName(product.getName());   
        //     productDb.setDescription(product.getDescription());
        //     productDb.setPrice(product.getPrice());
        //     return Optional.of(productRepository.save(productDb));
        // });
        if(productOptional.isPresent()){
            Product productDb = productOptional.orElseThrow();
            productDb.setSku(product.getSku());
            productDb.setName(product.getName());   
            productDb.setDescription(product.getDescription());
            productDb.setPrice(product.getPrice());
            return Optional.of(productRepository.save(productDb));
        }

        return productOptional;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        productOptional.ifPresent(productDb -> {
            productRepository.delete(productDb);
        });
        return productOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }

}
