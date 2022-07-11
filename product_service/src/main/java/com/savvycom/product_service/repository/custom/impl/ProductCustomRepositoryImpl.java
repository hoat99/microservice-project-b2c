package com.savvycom.product_service.repository.custom.impl;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.dto.product.get_product.ProductFeign;
import com.savvycom.product_service.domain.dto.product.get_product.ProductResponse;
import com.savvycom.product_service.domain.entity.Product;
import com.savvycom.product_service.domain.mapper.ProductMapper;
import com.savvycom.product_service.repository.custom.ProductCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Optional;
/**
 * Author: Nông Văn Hoạt
 * Date: July 4, 2022
 */
@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final EntityManager entityManager;

    private final ProductMapper productMapper;
    /**
     * Lấy tất cả product (phân trang, search, filter)
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @param color
     * @param size
     * @param min
     * @param max
     * @param nameBrand
     * @param type
     * @param nameCategoryDetail
     * @param sort               sắp xếp theo
     * @param optional           sắp xếp tăng or giảm
     * @return ProductResponse
     */
    @Override
    public ProductResponse getProducts(Optional<Integer> pageNum, Optional<Integer> pageSize, Optional<String> keyword, Optional<String> color, Optional<String> size, Optional<Long> min, Optional<Long> max, Optional<String> nameBrand, Optional<String> type, Optional<String> nameCategoryDetail, Optional<String> sort, Optional<String> optional) {

        String searchQuery = "MATCH(p.name, p.description) AGAINST (\"" + (keyword.isPresent()?keyword.get():null)+ "\") and ";
        String sortQuery = null;
        String maxPriceQuery = " and p.price <= " + (max.isPresent()? max.get():null);

        switch (sort.orElse("")) {
            case "price":
                switch (optional.orElse("high-to-low")) {
                    case "low-to-high":
                        sortQuery = " order by p.price asc ";
                        break;
                    case "high-to-low":
                        sortQuery = " order by p.price desc ";
                        break;
                }
                break;
            case "date":
                sortQuery = " order by p.create_at desc ";
                break;
            case "rate":
                sortQuery = " order by p.rate asc ";
                break;
            default:
                sortQuery = " ";
                break;
        }

        javax.persistence.Query query =
                entityManager.createNativeQuery("select * from product p inner join product_detail pt on p.product_id = pt.product_id " +
                        "inner join brand b on b.brand_id =p.brand_id " +
                        "inner join category c on c.category_id =p.category_id " +
                        "inner join category_detail cd on cd.category_detail_id =p.category_detail_id " +
                        "where " + (keyword.isPresent() ? searchQuery : " ") +
                        "pt.color like :color " +
                        "and pt.size like :size " +
                        "and p.price >= :min  " + (max.isPresent() ? maxPriceQuery : " ") +
                        " and b.name like :nameBrand " +
                        "and c.type like :type " +
                        "and cd.name like :nameCategoryDetail " +
                        "group by p.product_id " +sortQuery, Product.class);
        query.setParameter("color", "%" + color.orElse("") + "%");
        query.setParameter("size","%" + size.orElse("")+ "%");
        query.setParameter("min", min.orElse((long)0));
        query.setParameter("nameBrand","%" + nameBrand.orElse("")+ "%");
        query.setParameter("type","%" + type.orElse("")+ "%");
        query.setParameter("nameCategoryDetail","%" + nameCategoryDetail.orElse("")+ "%");
        Integer numberOfRecord = query.getResultList().size();
        query.setFirstResult((pageNum.orElse(1) -1) * pageSize.orElse(10));
        query.setMaxResults(pageSize.orElse(10));

        ProductResponse productResponse = ProductResponse.builder()
                .products(productMapper.convertToListDTO(query.getResultList()))
                .numberOfRecord(numberOfRecord)
                .pageSize(pageSize.orElse(10))
                .pageNum(pageNum.orElse(1))
                .build();

        return productResponse;
    }



}
