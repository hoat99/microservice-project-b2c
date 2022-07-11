package com.savvycom.orderservice.service;

import com.savvycom.orderservice.domain.input.CartInput;
import com.savvycom.orderservice.domain.output.CartOutput;
import com.savvycom.orderservice.domain.response.CartResponse;

import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:53 PM
 * Project Name:  order-service
 */
public interface CartService {

    /**
     *
     * @return get all product in a cart
     */
    CartOutput getAllCartItem();

    /**
     *
     * @param cartInput = information all products
     * @return create a new cart follow user
     */
    String createCart(CartInput cartInput);

    /**
     *
     * @param cartInput = information all products will update
     * @param cartItemId = id of product will update
     * @return update product in a cart
     */
    String updateCartItem(CartInput cartInput, Long cartItemId);

    /**
     *
     * @param cartItemId = id of product will delete
     * @return delete product in a cart
     */
    String deleteCartItem(Long cartItemId);

    /**
     * Delete all product by user id
     */
    void deleteAllCartItem();

    /**
     *
     * @return List<CartResponse>
     */
    CartResponse getListCartRes(int pageNo, int pageSize, String sortBy, String sortDir);
}
