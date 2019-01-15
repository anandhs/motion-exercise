package org.motion.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.motion.model.productType.Coffee;
import org.motion.model.productType.Product;
import org.motion.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.ValidationErrors;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return repository.findAll();
    }


    @RequestMapping(value="/types", method=RequestMethod.GET)
    public List<String> getConfiguredProductTypes() {
        return Lists.newArrayList("coffee", "fish");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product createProduct(@Valid @RequestBody Product product) throws Exception {
        repository.save(product);
        return product;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String id) {
        repository.delete(repository.findById(id).get());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable String id) {
        return repository.findById(id).get();
    }

}
