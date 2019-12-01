package tech.ityoung.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import tech.ityoung.search.pojo.Goods;

public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
