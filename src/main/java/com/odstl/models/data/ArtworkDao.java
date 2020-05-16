package com.odstl.models.data;

import com.odstl.models.Artwork;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface ArtworkDao extends CrudRepository<Artwork, Integer> {
}
