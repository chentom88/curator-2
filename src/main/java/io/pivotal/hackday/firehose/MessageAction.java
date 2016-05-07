package io.pivotal.hackday.firehose;

import events.Envelope;
import io.pivotal.hackday.ccapi.AppEventService;
import io.pivotal.hackday.ccapi.AppService;
import io.pivotal.hackday.domain.AppCatalogItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import rx.functions.Action1;

import java.util.List;

/**
 * Created by pivotal on 5/6/16.
 */

public class MessageAction implements Action1 {
    private static Log log = LogFactory.getLog(MessageAction.class);

    private AppService appService;
    private AppEventService appEventService;

    public MessageAction(AppService appService, AppEventService appEventService) {
        this.appService = appService;
        this.appEventService = appEventService;
    }

    @Override
    public void call(Object o) {
        Envelope envelope = (Envelope) o;

        switch(envelope.eventType) {
            case ContainerMetric:
                try {
                    appService.catalogApplication(envelope.containerMetric);
                } catch(Exception e) {
                    e.printStackTrace();
                }

                break;
            case ValueMetric:
//                gfEnvelope.setAppId(envelope.origin);
//                gfEnvelope.setValueMetric(new ValueMetric(envelope.valueMetric.name, envelope.valueMetric.value, envelope.valueMetric.unit));
                break;
            case HttpStartStop:
//                gfEnvelope.setAppId(envelope.origin);
//                gfEnvelope.setHttpStartStop(new HttpStartStop(envelope.httpStartStop.startTimestamp, envelope.httpStartStop.stopTimestamp, toString(envelope.httpStartStop.requestId), toString(envelope.httpStartStop.peerType), toString(envelope.httpStartStop.method), envelope.httpStartStop.uri, envelope.httpStartStop.remoteAddress, envelope.httpStartStop.userAgent, envelope.httpStartStop.statusCode, envelope.httpStartStop.contentLength, toString(envelope.httpStartStop.parentRequestId), toString(envelope.httpStartStop.applicationId), envelope.httpStartStop.instanceIndex, envelope.httpStartStop.instanceId));
                break;
            case LogMessage:
                if (isAppEvent(envelope)) {
                    appEventService.addAppEvent(envelope.logMessage);
                }
        }
    }

    private String toString(Object obj){
        if(obj == null){
            return null;
        }else{
            return obj.toString();
        }
    }

    private boolean isAppEvent(Envelope envelope) {
        String source = envelope.logMessage.source_type.toLowerCase();

        return source.equals("api") || (source.equals("app") && envelope.logMessage.message.utf8().contains("SystemExit -"));
    }
}
