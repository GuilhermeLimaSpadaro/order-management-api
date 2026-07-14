package com.gspadaro.ordermanagerapi.config;

import com.gspadaro.ordermanagerapi.domain.*;
import com.gspadaro.ordermanagerapi.domain.enums.OrderStatus;
import com.gspadaro.ordermanagerapi.repository.*;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    public TestConfig(CategoryRepository categoryRepository, ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void run(String @NonNull ... args) {
        categoryRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
        orderItemRepository.deleteAll();

        Category c1 = new Category(null, "Electronics");
        Category c2 = new Category(null, "Books");
        Category c3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(List.of(c1, c2, c3));

        productRepository.saveAll(List.of(p1, p2, p3, p4, p5));

        p1.getCategories().add(c2);
        p2.getCategories().add(c3);
        p3.getCategories().add(c3);
        p4.getCategories().add(c3);
        p5.getCategories().add(c2);

        productRepository.saveAll(List.of(p1, p2, p3, p4, p5));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        User u3 = new User(null, "Norma Moreti", "norma@gmail.com", "999999999999", "11111111");

        Order o1 = new Order(null, LocalDateTime.parse("2019-06-20T19:53:07"), OrderStatus.PAID);
        Order o2 = new Order(null, LocalDateTime.parse("2019-07-21T03:42:10"), OrderStatus.DELIVERED);
        Order o3 = new Order(null, LocalDateTime.parse("2019-07-22T15:21:22"), OrderStatus.WAITING_PAYMENT);

        userRepository.saveAll(List.of(u1, u2, u3));

        o1.setClient(u1);
        o2.setClient(u2);
        o3.setClient(u3);

        orderRepository.saveAll(List.of(o1, o2, o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(List.of(oi1, oi2, oi3, oi4));
    }
}
