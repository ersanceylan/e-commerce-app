package com.kodgemisi.course.ecommerce.api;

import com.kodgemisi.course.ecommerce.product.Product;
import com.kodgemisi.course.ecommerce.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping("/api/products")
public class ProductApiController {

    private final ProductService productService;

    @ResponseBody
    @GetMapping
    Page<Product> getAllProducts(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @ResponseBody
    @GetMapping("/{id}")
    Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

//    @GetMapping("/{id}")
//    ResponseEntity<Product> getProductResponseEntity(@PathVariable Long id) {
//        return ResponseEntity.ok(productService.findById(id));
//    }

    @ResponseBody
    @GetMapping("/get-gist")
    GithubGist getGist(@RequestParam String gistId) {
        RestTemplate restTemplate = new RestTemplate();
        GithubGist githubGist = restTemplate.getForObject(
                "https://api.github.com/gists/" + gistId,
                GithubGist.class);
        return githubGist;
    }

}
