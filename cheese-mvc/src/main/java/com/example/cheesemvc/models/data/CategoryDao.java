package com.example.cheesemvc.models.data;

import com.example.cheesemvc.models.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
