package com.umbrellary.daoimpl;

import com.umbrellary.entry.Articles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("articleDaoimpl")
@Transactional
public interface ArticleDaoimpl extends CrudRepository<Articles, Long> {
    public Articles findByTitle(String title);
}
