package com.savvycom.orderservice.service.impl;

import com.savvycom.orderservice.domain.entity.Cart;
import com.savvycom.orderservice.domain.fegin.Product;
import com.savvycom.orderservice.domain.input.CartInput;
import com.savvycom.orderservice.domain.input.CartItemInput;
import com.savvycom.orderservice.domain.output.CartOutput;
import com.savvycom.orderservice.domain.response.CartResponse;
import com.savvycom.orderservice.repository.CartRepository;
import com.savvycom.orderservice.service.CartService;
import com.savvycom.orderservice.service.feign.ProductFeignClient;
import com.savvycom.orderservice.service.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:55 PM
 * Project Name:  order-service
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final UserFeignClient userFeignClient;

    private final ProductFeignClient productFeignClient;

    @Override
    public CartOutput getAllCartItem() {
        assertNotNull("Bạn chưa đang nhập! Để kiểm tra giỏ hàng của bạn",
                userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString()));
        Long userId = userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal().toString())
                .getUserId();
        List<Cart> cartList = cartRepository.findAllByUserId(userId);
        List<CartItemInput> cartItems = new ArrayList<>();

        for (Cart cart : cartList) {
            Product product = productFeignClient
                    .getProductById(cart.getProductId());
            CartItemInput cartItemInput = CartItemInput
                    .builder()
                    .cartItemId(cart.getCartId())
                    .quantity(cart.getQuantity())
                    .color(cart.getColor())
                    .size(cart.getSize())
                    .product(product)
                    .build();
            cartItems.add(cartItemInput);
        }

        double totalCost = 0;
        for (CartItemInput cartItemInput : cartItems) {
            assertNotNull("Không tồn tại số lượng của sản phẩm", cartItemInput.getQuantity());
            assertNotNull("Không tồn tại giá của sản phẩm", cartItemInput.getProduct().getPrice());
            totalCost += (cartItemInput.getQuantity() * cartItemInput.getProduct().getPrice().doubleValue());
        }

        Integer totalProduct = cartRepository.findAllByUserId(userId).size();

        return new CartOutput(cartItems, totalCost, totalProduct);
    }

    @Override
    public String createCart(CartInput cartInput) {
        assertNotNull("Vui lòng chọn size sản phẩm!", cartInput.getSize());
        assertNotNull("Vui lòng chọn màu sản phẩm", cartInput.getColor());
        assertNotNull("Bạn chưa đang nhập! Vui lòng đăng nhập khi mua hàng",
                userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString()));
        Long userId = userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal().toString())
                .getUserId();
        assert true;
        List<Cart> cartList = cartRepository.findAllByUserId(userId);

        for (Cart carts : cartList) {
            Cart cart;
            if (Objects.equals(carts.getProductId(), cartInput.getProductId()) && carts.getQuantity() != null) {
                cart = cartRepository.findByCartId(carts.getCartId()).orElseThrow(() -> new IllegalArgumentException("Sản phẩm đã bị xóa!"));
                cart.setQuantity(carts.getQuantity() + cartInput.getQuantity());
            } else {
                cart = Cart
                        .builder()
                        .userId(userId)
                        .quantity(cartInput.getQuantity())
                        .productId(cartInput.getProductId())
                        .size(cartInput.getSize())
                        .color(cartInput.getColor())
                        .build();

            }
            cartRepository.save(cart);

        }
        if (cartList.isEmpty()) {
            Cart cart = Cart
                    .builder()
                    .userId(userId)
                    .quantity(cartInput.getQuantity())
                    .productId(cartInput.getProductId())
                    .size(cartInput.getSize())
                    .color(cartInput.getColor())
                    .build();
            cartRepository.save(cart);
        }
        return "Thêm sản phẩm thành công";
    }

    @Override
    public String updateCartItem(CartInput cartInput, Long cartItemId) {
        assertNotNull("Vui lòng nhập kiểm số lượng sản phẩm", cartInput.getQuantity());
        Cart cart = cartRepository.findByCartId(cartItemId).orElseThrow(() -> new IllegalArgumentException("Sản phẩm đã bị xóa!"));
        cart.setUpdateAt(LocalDateTime.now());
        cart.setQuantity(cartInput.getQuantity());
        cartRepository.save(cart);
        // If quantity of product by 0 , we will delete product.
        if (cart.getQuantity() == 0) {
            cartRepository.delete(cart);
        }
        return "Cập nhật thành công";
    }

    @Override
    public String deleteCartItem(Long cartItemId) {
        Cart cart = cartRepository.findByCartId(cartItemId).orElseThrow(() -> new IllegalArgumentException("Sản phẩm đã bị xóa!"));
        cartRepository.delete(cart);
        return "Xóa thành công";
    }

    @Override
    public void deleteAllCartItem() {
        assertNotNull("Bạn chưa đang nhập! Vui lòng đăng nhập khi đặt hàng",
                userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString()));
        Long userId = userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal().toString())
                .getUserId();
        cartRepository.deleteAllByUserId(userId);
    }

    @Override
    public CartResponse getListCartRes(int pageNo, int pageSize, String sortBy, String sortDir) {
        assertNotNull("Bạn chưa đang nhập! Vui lòng đăng nhập khi đặt hàng",
                userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString()));
        Long userId = userFeignClient.getUserByUsername(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal().toString())
                .getUserId();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Cart> carts = cartRepository.findAllByUserIdOrderByCreateAtDesc(userId, pageable);

        List<CartItemInput> cartItemInputList = new ArrayList<>();

        for (Cart cart : carts) {
            Product product = productFeignClient
                    .getProductById(cart.getProductId());
            CartItemInput cartItemInput = CartItemInput
                    .builder()
                    .cartItemId(cart.getCartId())
                    .quantity(cart.getQuantity())
                    .color(cart.getColor())
                    .size(cart.getSize())
                    .product(product)
                    .build();
            cartItemInputList.add(cartItemInput);
        }
        // Get total product and total price
        List<Cart> cartList = cartRepository.findAllByUserId(userId);
        List<CartItemInput> cartItems = new ArrayList<>();

        for (Cart cart : cartList) {
            Product product = productFeignClient
                    .getProductById(cart.getProductId());
            CartItemInput cartItemInput = CartItemInput
                    .builder()
                    .cartItemId(cart.getCartId())
                    .quantity(cart.getQuantity())
                    .color(cart.getColor())
                    .size(cart.getSize())
                    .product(product)
                    .build();
            cartItems.add(cartItemInput);
        }

        double totalCost = 0;
        for (CartItemInput cartItemInput : cartItems) {
            assertNotNull("Không tồn tại số lượng của sản phẩm", cartItemInput.getQuantity());
            assertNotNull("Không tồn tại giá của sản phẩm", cartItemInput.getProduct().getPrice());
            totalCost += (cartItemInput.getQuantity() * cartItemInput.getProduct().getPrice().doubleValue());
        }

        Integer totalProduct = cartRepository.findAllByUserId(userId).size();

        CartResponse cartResponse = new CartResponse();
        cartResponse.setContent(cartItemInputList);
        cartResponse.setTotalProduct(totalProduct);
        cartResponse.setTotalPrice(totalCost);
        cartResponse.setPageNo(carts.getNumber());
        cartResponse.setPageSize(carts.getSize());
        cartResponse.setTotalElements(carts.getTotalElements());
        cartResponse.setTotalPages(carts.getTotalPages());
        cartResponse.setLast(carts.isLast());

        return cartResponse;
    }
}
