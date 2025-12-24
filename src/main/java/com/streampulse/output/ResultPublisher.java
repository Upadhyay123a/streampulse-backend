package com.streampulse.output;

import com.streampulse.model.AnalyticsResult;

public interface ResultPublisher {

    void publish(AnalyticsResult result);

}
