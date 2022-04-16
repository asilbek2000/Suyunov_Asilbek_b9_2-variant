package com.example.suyunov_asilbek_b9_2variant.component;


import com.example.suyunov_asilbek_b9_2variant.entity.Category;
import com.example.suyunov_asilbek_b9_2variant.entity.Product;
import com.example.suyunov_asilbek_b9_2variant.entity.Role;
import com.example.suyunov_asilbek_b9_2variant.entity.User;
import com.example.suyunov_asilbek_b9_2variant.entity.enums.PermissionEnum;
import com.example.suyunov_asilbek_b9_2variant.entity.enums.RoleEnum;
import com.example.suyunov_asilbek_b9_2variant.repository.CategoryRepository;
import com.example.suyunov_asilbek_b9_2variant.repository.ProductRepository;
import com.example.suyunov_asilbek_b9_2variant.repository.RoleRepository;
import com.example.suyunov_asilbek_b9_2variant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Dataloader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String mode;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;
    final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always") && ddl.equals("create")) {
            Role admin1 = new Role();
            admin1.setName(RoleEnum.ADMIN1);
            admin1.setPermissionEnumSet(Arrays.stream(PermissionEnum.values()).collect(Collectors.toSet()));

            Role admin2 = new Role();
            admin2.setName(RoleEnum.ADMIN2);
            admin2.setPermissionEnumSet(new HashSet<>(Arrays.asList(
                    PermissionEnum.ADD_PRODUCT
            )));
            Role user=new Role();
            user.setName(RoleEnum.USER);
            user.setPermissionEnumSet(new HashSet<>(Arrays.asList(
                    PermissionEnum.READ_ALL_PRODUCT
            )));
            roleRepository.save(admin1);
            roleRepository.save(admin2);
            roleRepository.save(user);

            Category category = new Category();
            category.setName("Technics");
            Category save = categoryRepository.save(category);

            Product product=new Product();
            product.setName("Apple");
            product.setPrice(12000d);
            product.setCategory(save);
            productRepository.save(product);

            Set<Role> roles = new HashSet<>();
            roles.add(admin1);
            roles.add(admin2);
            roles.add(user);

            Set<Role> rol=new HashSet<>();
            rol.add(admin2);
            rol.add(user);

            Set<Role> justrole=new HashSet<>();
            justrole.add(user);

            User user1 = new User("Suyunov Asilbek", roles, "Asilbek", passwordEncoder.encode("4444"),true);
            userRepository.save(user1);
            User user2=new User("Nodir Eshmatov",rol,"Nodir",passwordEncoder.encode("2222"),true);
            userRepository.save(user2);
            User user3=new User("Qobil",justrole,"Qobil",passwordEncoder.encode("432423"),true);
            userRepository.save(user3);
        }
    }
}
