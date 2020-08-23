package com.lambdaschool.notapotluck.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "potluckService")
public class PotluckServiceImpl implements PotluckService
{
}
