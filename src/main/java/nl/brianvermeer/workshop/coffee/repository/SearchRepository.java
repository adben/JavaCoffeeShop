package nl.brianvermeer.workshop.coffee.repository;

import nl.brianvermeer.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

@Repository
public class
SearchRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;

    public List<Product> searchProduct (String input) {
        var lowerInput = input.toLowerCase(Locale.ROOT);
        String querytext = "Select * from Product where lower(description) like CONCAT('%', ?1, '%') OR lower(product_name) like CONCAT('%', ?2, '%')";
        var query = em.createNativeQuery(querytext, Product.class);
        query.setParameter(1, lowerInput);
        query.setParameter(2, lowerInput);
        var resultList = (List<Product>) query.getResultList();
        return resultList;
    }

}
