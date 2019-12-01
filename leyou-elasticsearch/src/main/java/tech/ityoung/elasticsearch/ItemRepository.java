package tech.ityoung.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import tech.ityoung.elasticsearch.pojo.Item;

public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
}
