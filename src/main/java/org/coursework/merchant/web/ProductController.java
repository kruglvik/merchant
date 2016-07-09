package org.coursework.merchant.web;

import org.coursework.merchant.domain.Product;
import org.coursework.merchant.service.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;


    @RequestMapping(value = "/product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Product>> listAllProducts() {
        LOG.debug("Find all products");
        Iterable<Product> products = productRepository.findAll();
        if (!products.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        LOG.debug("Fetching Product with id " + id);
        Product product = productRepository.findOne(id);
        if (product == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @RequestMapping(value = "/product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        LOG.debug("Creating Product " + product.getName());

        if (!productRepository.findByName(product.getName()).isEmpty()) {
            LOG.debug("A Product with name #{} already exists", product.getName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        productRepository.save(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        System.out.println("Updating Product " + id);

        Product currentProduct = productRepository.findOne(id);

        if (currentProduct == null) {
            LOG.debug("Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentProduct.setName(product.getName());
        currentProduct.setDiscountRatio(product.getDiscountRatio());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setExpirationDate(product.getExpirationDate());

        productRepository.save(currentProduct);
        return new ResponseEntity<>(currentProduct, HttpStatus.OK);
    }


    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
        LOG.debug("Fetching & Deleting Product with id " + id);

        Product product = productRepository.findOne(id);
        if (product == null) {
            LOG.debug("Unable to delete. Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/product", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteAllProducts(@RequestParam(value = "q") List<Long> q) {
        LOG.debug("Deleting products: {}", q);

        productRepository.delete(productRepository.findAll(q));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
