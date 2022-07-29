package com.grpc.service;

import com.google.protobuf.Descriptors;
import com.grpc.Author;
import com.grpc.Book;
import com.grpc.BookAuthorServiceGrpc;
import com.grpc.TempDB;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import javax.management.Descriptor;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class BookAuthorClientService {
    @GrpcClient("com-grpc-service")
    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub synchronousClient;
    @GrpcClient("com-grpc-service")
    BookAuthorServiceGrpc.BookAuthorServiceStub asynchronousClient;
    public Map<Descriptors.FieldDescriptor,Object> getAuthor(int authorId){
       Author authorReq= Author.newBuilder().setAuthorId(authorId).build();
        Author authorResponse=synchronousClient.getAuthor(authorReq);
        return authorResponse.getAllFields();
    }
    public List<Map<Descriptors.FieldDescriptor,Object>> getBooksByAuthor(int authorId) throws InterruptedException {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        Author authorReq=Author.newBuilder().setAuthorId(authorId).build();
        final List<Map<Descriptors.FieldDescriptor,Object>> response =new ArrayList<>();
        asynchronousClient.getBookByAuthor(authorReq, new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
                response.add(book.getAllFields());
            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });
        boolean await=countDownLatch.await(1, TimeUnit.MINUTES);
        return await?response: Collections.emptyList();
    }
    public Map<String,Map<Descriptors.FieldDescriptor,Object>> getExpensiveBook() throws InterruptedException {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        final Map<String,Map<Descriptors.FieldDescriptor,Object>> response=new HashMap<>();
        StreamObserver<Book> responseObserver=asynchronousClient.getExpensiveBook(new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
            response.put("ExpensiveBook",book.getAllFields());
            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
            countDownLatch.countDown();
            }
        });
        TempDB.getBooksFromTempDb().forEach(responseObserver::onNext);
        responseObserver.onCompleted();
       boolean await= countDownLatch.await(1,TimeUnit.MINUTES);
        return await ? response:Collections.emptyMap();
    }
    public List<Map<Descriptors.FieldDescriptor,Object>> getBooksByAuthorGender(String gender) throws InterruptedException {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        final List<Map<Descriptors.FieldDescriptor,Object>> response=new ArrayList<>();
        StreamObserver<Book> responseObserver= asynchronousClient.getBookByAuthorGender(new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
                response.add(book.getAllFields());
            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });
        TempDB.getAuthorsFromTempDb()
                .stream()
                .filter(author-> author.getGender().equalsIgnoreCase(gender))
                .forEach(author->responseObserver.onNext(Book.newBuilder().setAuthorId(author.getAuthorId()).build()));
       responseObserver.onCompleted();
       boolean await=countDownLatch.await(1,TimeUnit.MINUTES);
       return await ? response:Collections.emptyList();
    }
}
