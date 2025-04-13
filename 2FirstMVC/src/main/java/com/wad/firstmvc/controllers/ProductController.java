//productController
package com.wad.firstmvc.controllers;

import com.wad.firstmvc.domain.Product;
import com.wad.firstmvc.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // List all products
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    // Show form to add a product
    @GetMapping("/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addproducts";
    }

    // Handle form submission for new product
    @PostMapping("/new")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    // Show the form for searching products
    @GetMapping("/findProducts")
    public String showFindProducts() {
        return "findProducts";
    }

    // Show the results of the search
    @GetMapping("/results")
    public String searchProducts(@RequestParam(required = false) String category,
                                 @RequestParam(required = false) Double minPrice,
                                 @RequestParam(required = false) Double maxPrice,
                                 Model model) {
        List<Product> results = productService.search(category, minPrice, maxPrice);
        model.addAttribute("products", results);
        return "foundProducts";
    }
}
