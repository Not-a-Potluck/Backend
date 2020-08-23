package com.lambdaschool.notapotluck.repository;

import com.lambdaschool.notapotluck.models.Potluck;
import org.springframework.data.repository.CrudRepository;

public interface PotluckRespository extends CrudRepository<Potluck, Long>
{
}
