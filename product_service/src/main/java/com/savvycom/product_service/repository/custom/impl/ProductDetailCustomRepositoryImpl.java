package com.savvycom.product_service.repository.custom.impl;

import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailAdmin;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailAdminResponse;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO;
import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailResponse;
import com.savvycom.product_service.domain.entity.ProductDetail;
import com.savvycom.product_service.domain.mapper.ProductDetailMapper;
import com.savvycom.product_service.repository.custom.ProductDetailCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.IllegalTransactionStateException;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductDetailCustomRepositoryImpl implements ProductDetailCustomRepository {

    private final EntityManager entityManager;

    private final ProductDetailMapper productDetailMapper;


    /**
     * Lấy danh sách các product detail theo product id, color, size
     * @param productId
     * @param color
     * @param size
     * @return List<ProductDetailDTO>
     */
    @Override
    public List<ProductDetailDTO> getProductDetails(Long productId, String color, String size) {
        TypedQuery<ProductDetail> query =
                entityManager.createQuery("select p from ProductDetail p where p.product.productId = :productId " +
                        "and p.color = :color and p.size = :size" , ProductDetail.class);
        query.setParameter("productId",productId);
        query.setParameter("color",color);
        query.setParameter("size",size);

        return productDetailMapper.convertToListDTO(query.getResultList());
    }
    /**
     * DAnh sách product detail theo product id, color, size
     * @param productId
     * @param color
     * @param size
     * @return List<ProductDetail>
     */
    @Override
    public List<ProductDetail> getListProductDetail(Long productId, String color, String size) {
        TypedQuery<ProductDetail> query =
                entityManager.createQuery("select p from ProductDetail p where p.product.productId = :productId " +
                        "and p.color = :color and p.size = :size" , ProductDetail.class);
        query.setParameter("productId",productId);
        query.setParameter("color",color);
        query.setParameter("size",size);

        return query.getResultList();
    }
    /**
     * Lấy danh sách các product detail theo mẫu ProductDetailResponse
     * @param productId
     * @param pageNum
     * @param pageSize
     * @param color
     * @param size
     * @param branchId
     * @return ProductDetailResponse
     */
    @Override
    public ProductDetailResponse getProductDetail(Long productId, Optional<Integer> pageNum, Optional<Integer> pageSize, Optional<String> color, Optional<String> size, Optional<String> branchId) {
        javax.persistence.Query query =
                entityManager.createNativeQuery("select * from product_detail pt " +
                        "inner join product p on p.product_id = pt.product_id " +
                        "inner join branch b on b.branch_id=pt.branch_id " +
                        "where pt.product_id = :productId " +
                        "and pt.color like :color  " +
                        "and pt.size like :size " +
                        "and b.branch_id like :branchId order by pt.quantity asc" , ProductDetail.class);
        query.setParameter("productId", productId);
        query.setParameter("color", "%" + color.orElse("") + "%");
        query.setParameter("size","%" + size.orElse("")+ "%");
        query.setParameter("branchId","%" + branchId.orElse("")+ "%");
        Integer numberOfRecord = query.getResultList().size();
        query.setFirstResult((pageNum.orElse(1) -1) * pageSize.orElse(10));
        query.setMaxResults(pageSize.orElse(10));

        ProductDetailResponse productDetailResponse = ProductDetailResponse.builder()
                .products(productDetailMapper.convertToListDTO(query.getResultList()))
                .numberOfRecord(numberOfRecord)
                .pageSize(pageSize.orElse(10))
                .pageNum(pageNum.orElse(1))
                .build();

        return productDetailResponse;
    }
    /**
     * Trừ số lượng của sản phẩm
     * @param productDetailId
     * @param quantity
     * @return
     */
    @Override
    @Transactional
    public ProductDetailDTO subQuantity(Long productDetailId, Integer quantity) {
        TypedQuery<ProductDetail> query = entityManager.createQuery(
                "  SELECT p FROM ProductDetail p WHERE p.productDetailId = :productDetailId"
                , ProductDetail.class);
        query.setParameter("productDetailId", productDetailId);
        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        ProductDetail productDetail =  query.getSingleResult();
        if (productDetail.getQuantity()<quantity) throw  new IllegalTransactionStateException("So luong trong kho it hon so luong dat hang, giao dich that bai");
        productDetail.setQuantity(productDetail.getQuantity()-quantity);
        entityManager.persist(productDetail);
        return productDetailMapper.convertToDTO(productDetail);
    }
    /**
     * Lấy các sản phẩm để hiển thị trên trang admin
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ProductDetailAdminResponse getProductDetailAdmin(Optional<Integer> pageNum, Optional<Integer> pageSize) {
        TypedQuery<ProductDetail> query =
                entityManager.createQuery("select p from ProductDetail p order by p.product.name" , ProductDetail.class);
        Integer numberOfRecord = query.getResultList().size();
        query.setFirstResult((pageNum.orElse(1) -1) * pageSize.orElse(10));
        query.setMaxResults(pageSize.orElse(10));

        List<ProductDetailAdmin> productDetailResponse = query.getResultList().stream().map(p-> new ProductDetailAdmin(
                p.getProduct().getName(),
                p.getProduct().getCategory().getType() + " - " + p.getProduct().getCategoryDetail().getName(),
                p.getProduct().getImages().get(0).getImage(),
                p.getProduct().getPrice().toString(),
                p.getSize(),
                p.getColor(),
                p.getQuantity().toString(),
                0,
                (p.getQuantity()>0? "Còn hàng": "Hết hàng")
        )).collect(Collectors.toList());

        ProductDetailAdminResponse productDetailAdminResponse = new ProductDetailAdminResponse(productDetailResponse,numberOfRecord,pageNum.orElse(1),pageSize.orElse(10));

        return productDetailAdminResponse;
    }

//    @Override
//    public List<ProductDetailDTO> getProductDetail(Long id) {
//        TypedQuery<ProductDetailDTO> query =
//                entityManager.createQuery("select new com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO"
//                        + "(p.productDetailId, p.size, p.color, p.quantity, p.product.productId, p.branch.name)"
//                        + " from ProductDetail p where p.product.productId = :id", ProductDetailDTO.class);
//        query.setParameter("id",id);
//        return query.getResultList();
//    }

}
