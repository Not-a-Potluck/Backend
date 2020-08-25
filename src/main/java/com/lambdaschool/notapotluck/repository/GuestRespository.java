package com.lambdaschool.notapotluck.repository;

import com.lambdaschool.notapotluck.models.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRespository extends CrudRepository<Guest, Long>
{
}
