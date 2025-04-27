package com.example.mvcproducts.bootstrap;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.Role;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.repositories.ProductRepository;
import com.example.mvcproducts.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  
  public DataLoader(UserRepository userRepository, ProductRepository productRepository) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }
  
  @Override
  public void run(String... args) throws Exception {
    PasswordEncoder bcrypt = new BCryptPasswordEncoder();
    User user1=new User("user1",bcrypt.encode("user1"));
    user1.getRoles().add(Role.ROLE_USER);
    User user2=new User("user2",bcrypt.encode("user2"));
    user2.getRoles().add(Role.ROLE_ADMIN);
    userRepository.save(user1);
    userRepository.save(user2);
    
    loadProducts();
  }
  
  private void loadProducts() {
    if (productRepository.count() == 0) {
      productRepository.save(new Product(null, "Ice Cream"));
      productRepository.save(new Product(null, "Bike"));
      productRepository.save(new Product(null, "Car"));
      productRepository.save(new Product(null, "Laptop"));
      productRepository.save(new Product(null, "Phone"));
      System.out.println("Products loaded: " + productRepository.count());
    }
  }
}
