package io.pivotal.hackday.firehose;

import cf.dropsonde.firehose.Firehose;
import cf.dropsonde.firehose.FirehoseBuilder;
import io.pivotal.hackday.ccapi.AppEventService;
import io.pivotal.hackday.ccapi.AppService;
import io.pivotal.hackday.uaa.UAAUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rx.observables.BlockingObservable;

import javax.annotation.PreDestroy;

/**
 * Created by pivotal on 5/6/16.
 */
@Component
public class FirehoseService {
    private static Log log = LogFactory.getLog(FirehoseService.class);

    @Value("${doppler.host}")
    private String dopplerHost;

    @Value("${doppler.port}")
    private Long dopplerPort;

    @Value("${uaa.host}")
    private String uaaHost;

    @Value("${uaa.user}")
    private String uaaUser;

    @Value("${uaa.secret}")
    private String uaaSecret;

    @Autowired
    AppService appService;

    @Autowired
    AppEventService appEventService;

    private Firehose firehose;

    @Async
    public void start() {
        try {
            String url = String.format("wss://%s:%d", dopplerHost, dopplerPort);

            String token = UAAUtil.getToken(uaaHost, uaaUser, uaaSecret);
            firehose = FirehoseBuilder.create(url, token)
                    .skipTlsValidation(true)
                    .build();

            BlockingObservable observable = firehose.open().toBlocking();

            observable.forEach(new MessageAction(appService, appEventService));

        } catch(Exception e) {
            log.error("Exception starting firehose.", e);
        }
    }

    @PreDestroy
    public void stop() {
        log.info("Stopping firehose ingestor.");
        if(!firehose.isClosed()) {
            firehose.close();
        }
    }
}
