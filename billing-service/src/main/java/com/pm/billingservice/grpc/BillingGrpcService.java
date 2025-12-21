package com.pm.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import com.pm.billingservice.model.BillingAccount;
import com.pm.billingservice.repository.BillingRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
    private final BillingRepository repository;

    public BillingGrpcService(BillingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<billing.BillingResponse> responseObserver)
    {
        log.info("createBillingAccount request received {}",billingRequest.toString());
        //Business Logic  - e.g save to database,perform calculates etc
        try {
            BillingAccount account = new BillingAccount();
            account.setPatientId(billingRequest.getPatientId());
            account.setName(billingRequest.getName());
            account.setEmail(billingRequest.getEmail());
            account.setStatus("ACTIVE");

            //Persist to database
            account = repository.save(account);

            //return billingAccountId in Response
            BillingResponse response = BillingResponse.newBuilder()
                    .setAccountId(account.getId())
                    .setStatus(account.getStatus())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            log.info("Billing account created successfully with ID {}",account.getId());
        }
        catch(Exception e)
        {
            log.error("Failed to create billing account",e);
            responseObserver.onError(e);
        }
    }
}
