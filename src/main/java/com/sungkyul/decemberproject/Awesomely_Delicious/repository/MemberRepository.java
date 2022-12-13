package com.sungkyul.decemberproject.Awesomely_Delicious.repository;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class MemberRepository  {
    private final EntityManager em;
    public void save(User user) {
        em.persist(user);
    }
    public User findById(Long id) {
        return em.find(User.class,id);
    }

    /**
     * 이메일, 비밀번호로 유저 조회
     * @return User_id 반환
     */
    public Long findByUserInfo(String email, String password) {
        TypedQuery<User> query = em.createQuery("select m from User m where m.email = : email AND m.password = : password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        User result = query.getSingleResult();
        return result.getUser_id();
    }
}
