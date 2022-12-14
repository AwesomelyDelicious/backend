package com.sungkyul.decemberproject.Awesomely_Delicious.repository;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.Restaurant;
import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {
   private final EntityManager em;

   public void save(Restaurant restaurant){
      em.persist(restaurant);
   }
   public Restaurant findById(Long restaurantId) {
      return em.find(Restaurant.class,restaurantId);
   }
   public void deleteById(Long restaurantId){
      em.remove(em.find(Restaurant.class,restaurantId));
   }

}
