package com.example.vsatdd.goodscollection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("prod")
@Repository
public class GoodsCollectionRepositoryImpl extends AbstractGoodsCollectionRepository {
    @PersistenceContext
    private EntityManager em;

    public GoodsCollectionRepositoryImpl(GoodsCollectionJpaRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public List<Goods> findGoodsByIds(List<String> ids) {
        List<Object[]> resultList = em.createNativeQuery("""
                        select g.goods_no goodsno, g.goods_id as goodsid, g.barcode as barcode
                         from goods g
                          where (g.goods_id in :ids)
                          or (g.barcode in :ids)
                          order by g.goods_no
            """).setParameter("ids", ids).getResultList();
        return resultList.stream()
                .map(result -> Goods.of((Object[]) result))
                .toList();
    }
}
