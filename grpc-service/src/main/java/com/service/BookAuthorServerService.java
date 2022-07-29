package com.service;
import com.grpc.Author;
import com.grpc.BookAuthorServiceGrpc;
import com.grpc.TempDB;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BookAuthorServerService extends BookAuthorServiceGrpc.BookAuthorServiceImplBase {
    @Override
    public void getAuthor(Author request, StreamObserver<Author> responseObserver) {
        TempDB.getAuthorsFromTempDb().stream()
                .filter(author -> author.getAuthorId()==request.getAuthorId()).findFirst()
                .ifPresent(responseObserver::onNext);

    }
}
