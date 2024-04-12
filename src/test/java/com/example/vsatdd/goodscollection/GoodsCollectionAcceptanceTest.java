package com.example.vsatdd.goodscollection;

import com.ktown4u.utils.Neutralizer;
import com.ktown4u.utils.YamlPrinter;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("in-memory")
@AutoConfigureHttpGraphQlTester
@ApplicationModuleTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GoodsCollectionAcceptanceTest {
    @Autowired private HttpGraphQlTester graphQlTester;
    @Autowired private GoodsCollectionRepository repository;

    private GraphQlTester.Path request(String queryString, String requestName) {
        return this.graphQlTester
                .mutate()
                .build()
                .document(queryString)
                .execute()
                .path(requestName);
    }

    @Test
    public void create_goods_collection() throws Exception {
        /**
         * 테스트 데이터는 InMemoryRepository를 사용할 때와 실제 DB를 사용할 때 동일한 값을 사용하도록 준비하면
         * InMemoryRepository를 사용할 때 생성된 검증파일(ApprovalsTest의 XXXapproved.txt)와 실제 DB를 사용할 때의 검증파일이 동일해서
         * 부드럽게 진행되는 효과가 있다
         */
        String queryString = """
                mutation {
                    createGoodsCollection(request: {
                        name: "Collection 1"
                        ids: ["GD00112296", "GD00112297", "9000000112298", "9000000112299"]
                    })
                }
                """;

        Long result = request(queryString, "createGoodsCollection")
                .entity(Long.class)
                .get();
        assertThat(result).isGreaterThan(0L);
        Approvals.verify(
                Neutralizer.localDateTime(
                        YamlPrinter.printWithExclusions(
                                repository.findById(result).get(), "id")
                )
        );
    }

    @Test
    public void listGoodsCollection() throws Exception {
        String queryString = """
             query {
               listGoodsCollection(request: {
                 keyword: "Collection",
                 type: "type",
                 page: 0,
                 size: 10
               }) {
                 content {
                   id
                   name
                   createdBy
                   createdAt
                   goodsCollectionItems {
                     goodsNo
                     goodsId
                     barcode
                   }
                 }
               }
            }
             """;

        List<GetGoodsCollection.GoodsCollectionDto> result = request(queryString, "listGoodsCollection.content")
                .entityList(GetGoodsCollection.GoodsCollectionDto.class)
                .get();
        Approvals.verify(Neutralizer.localDateTime(YamlPrinter.printWithExclusions(result, "updatedBy", "updatedAt")));
    }
}