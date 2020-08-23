package com.lambdaschool.notapotluck.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "guestService")
public class GuestServiceImpl implements GuestService
{
}
