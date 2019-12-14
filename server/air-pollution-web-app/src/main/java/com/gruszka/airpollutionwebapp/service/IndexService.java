package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Index;
import com.gruszka.airpollutionwebapp.entity.PollutionData;
import com.gruszka.airpollutionwebapp.entity.PollutionDataHistory;

public interface IndexService {

    Index findById(Integer id);
    Index choosePollutionIndexByItsValue(PollutionData pollutionData);
    Index choosePollutionIndexByItsValue(PollutionDataHistory pollutionData);
    Index findByName(String name);



}
