package com.savvycom.orderservice.repository;

import com.savvycom.orderservice.domain.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:45 PM
 * Project Name:  order-service
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     *
     * @param cartId = id of cart
     * @return find a product have a cart
     */
    Optional<Cart> findByCartId(Long cartId);

    /**
     *
     * @param userId = id of customer
     * @return get all products of customer in a cart
     */
    List<Cart> findAllByUserId(Long userId);

    /**
     *
     * @param userId = id of customer
     */
    void deleteAllByUserId(Long userId);

    /**
     * Sort and paging in cart
     * @param userId = id of user
     * @param pageable = paging
     * @return Page<Cart>
     */
    Page<Cart> findAllByUserIdOrderByCreateAtDesc(Long userId, Pageable pageable);
}
