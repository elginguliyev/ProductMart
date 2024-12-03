package com.example.services.inter;

import com.example.request.AdminRequest;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Long register(AdminRequest adminRequest);
}
