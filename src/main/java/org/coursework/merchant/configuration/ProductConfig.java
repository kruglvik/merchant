package org.coursework.merchant.configuration;

import org.coursework.merchant.MerchantApplication;
import org.coursework.merchant.domain.Product;
import org.coursework.merchant.service.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class ProductConfig {
    private static final Logger log = LoggerFactory.getLogger(MerchantApplication.class);

    @Bean
    public CommandLineRunner productPopulator(ProductRepository repository) {
        return (args) -> {
            Product entity1 = new Product();

            entity1.setName("Йогурт");
            entity1.setCode("AnyBkJv9");
            entity1.setStorageCode("4461081618");
            entity1.setPrice(73.0);
            entity1.setDiscountRatio(0.0);
            entity1.setCreatedDate(dateFromLocalDate(LocalDate.of(2016, 11, 14)));
            entity1.setExpirationDate(dateFromLocalDate(LocalDate.of(2016, 11, 22)));
            repository.save(entity1);
            log.info("{} saved", entity1);

            Product entity2 = new Product();
            entity2.setName("Мороженое");
            entity2.setCode("28eEjjVZ");
            entity2.setStorageCode("8802898678");
            entity2.setPrice(56.5);
            entity2.setDiscountRatio(3.0);
            entity1.setCreatedDate(dateFromLocalDate(LocalDate.of(2016, 11, 13)));
            entity2.setExpirationDate(dateFromLocalDate(LocalDate.of(2016, 11, 18)));

            repository.save(entity2);

            Product entity3 = new Product();
            entity3.setName("Эклер");
            entity3.setCode("sWHwQY5g");
            entity3.setStorageCode("8438827610");
            entity3.setPrice(33);
            entity3.setDiscountRatio(40);
            entity3.setCreatedDate(dateFromLocalDate(LocalDate.of(2016, 9, 20)));
            entity3.setExpirationDate(dateFromLocalDate(LocalDate.of(2016, 10, 16)));
            repository.save(entity3);

            Product entity4 = new Product();
            entity4.setName("Кекс");
            entity4.setCode("H5eC7f4M");
            entity4.setStorageCode("8438827610");
            entity4.setPrice(48);
            entity4.setDiscountRatio(33);
            entity4.setCreatedDate(dateFromLocalDate(LocalDate.of(2015, 12, 11)));
            entity4.setExpirationDate(dateFromLocalDate(LocalDate.of(2016, 1, 12)));
            repository.save(entity4);

            Product entity5 = new Product();
            entity5.setName("Мюсли");
            entity5.setCode("H5eC7f4M");
            entity5.setStorageCode("0231291606");
            entity5.setPrice(67);
            entity5.setDiscountRatio(0);
            entity5.setCreatedDate(dateFromLocalDate(LocalDate.of(2016, 9, 28)));
            entity5.setExpirationDate(dateFromLocalDate(LocalDate.of(2017, 12, 31)));
            repository.save(entity5);

            Product entity6 = new Product();
            entity6.setName("Леденец");
            entity6.setCode("tDeSENL4");
            entity6.setStorageCode("9512687428");
            entity6.setPrice(14);
            entity6.setDiscountRatio(0.2);
            entity6.setCreatedDate(dateFromLocalDate(LocalDate.of(2014, 1, 29)));
            entity6.setExpirationDate(dateFromLocalDate(LocalDate.of(2016, 3, 14)));
            repository.save(entity6);

            Product entity7 = new Product();
            entity7.setName("Сухари");
            entity7.setCode("MRLBGezN");
            entity7.setStorageCode("8006295363");
            entity7.setPrice(47.57);
            entity7.setDiscountRatio(3.2);
            entity7.setCreatedDate(dateFromLocalDate(LocalDate.of(2016, 11, 20)));
            entity7.setExpirationDate(dateFromLocalDate(LocalDate.of(2017, 3, 15)));
            repository.save(entity7);

            Product entity8 = new Product();
            entity8.setName("KitKat");
            entity8.setCode("GXyyYPUb");
            entity8.setStorageCode("1870427594");
            entity8.setPrice(26);
            entity8.setDiscountRatio(26);
            entity8.setCreatedDate(dateFromLocalDate(LocalDate.of(2016, 8, 9)));
            entity8.setExpirationDate(dateFromLocalDate(LocalDate.of(2016, 12, 30)));
            repository.save(entity8);
        };
    }

    private static Date dateFromLocalDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant());
    }
}
