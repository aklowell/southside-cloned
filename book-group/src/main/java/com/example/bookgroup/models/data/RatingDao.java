package com.example.bookgroup.models.data;


import com.example.bookgroup.models.Book;
import com.example.bookgroup.models.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RatingDao extends CrudRepository<Rating, Integer> {

}
