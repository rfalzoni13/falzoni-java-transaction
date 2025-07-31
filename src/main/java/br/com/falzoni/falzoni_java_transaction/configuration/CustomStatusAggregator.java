package br.com.falzoni.falzoni_java_transaction.configuration;

import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.health.StatusAggregator;

import java.util.Set;

public class CustomStatusAggregator implements StatusAggregator {
    @Override
    public Status getAggregateStatus(Set<Status> statuses) {
        if(statuses.contains(new Status("API Funcionando"))) {
            return new Status("API Funcionando");
        }
        if(statuses.contains(Status.DOWN)) {
            return Status.DOWN;
        }
        if(statuses.contains(Status.UNKNOWN)) {
            return Status.UNKNOWN;
        }
        if(statuses.contains(Status.OUT_OF_SERVICE)) {
            return Status.OUT_OF_SERVICE;
        }
        return Status.UP;
    }
}
