package com.careercitydashboard.Service;

import com.careercitydashboard.Model.OpenJobReq;

public interface UploadJobReqService {

    void save(OpenJobReq openJobReq);

    void truncate();

}
