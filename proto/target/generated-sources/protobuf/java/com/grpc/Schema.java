// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: schema.proto

package com.grpc;

public final class Schema {
  private Schema() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_grpc_Book_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_grpc_Book_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_grpc_Author_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_grpc_Author_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014schema.proto\022\010com.grpc\"W\n\004Book\022\017\n\007book" +
      "_id\030\001 \001(\005\022\r\n\005title\030\002 \001(\t\022\r\n\005price\030\003 \001(\002\022" +
      "\r\n\005pages\030\004 \001(\005\022\021\n\tauthor_id\030\005 \001(\005\"c\n\006Aut" +
      "hor\022\021\n\tauthor_id\030\001 \001(\005\022\022\n\nfirst_name\030\002 \001" +
      "(\t\022\021\n\tlast_name\030\003 \001(\t\022\016\n\006gender\030\004 \001(\t\022\017\n" +
      "\007book_id\030\005 \001(\0052F\n\021BookAuthorService\0221\n\tg" +
      "etAuthor\022\020.com.grpc.Author\032\020.com.grpc.Au" +
      "thor\"\000B\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_grpc_Book_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_grpc_Book_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_grpc_Book_descriptor,
        new java.lang.String[] { "BookId", "Title", "Price", "Pages", "AuthorId", });
    internal_static_com_grpc_Author_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_grpc_Author_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_grpc_Author_descriptor,
        new java.lang.String[] { "AuthorId", "FirstName", "LastName", "Gender", "BookId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
