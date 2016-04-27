package com.umbrellary.daoimpl;

import com.umbrellary.entry.Articles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleRepository extends CrudRepository<Articles, Long> {
    public Articles findByTitle(String title);

    @Query("select a from Articles a where a.id = :id")
    public Articles findByArticleId(@Param("id") Long ID);
}
