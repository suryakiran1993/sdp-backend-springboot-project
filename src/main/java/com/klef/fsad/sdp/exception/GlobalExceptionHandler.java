//package com.klef.fsad.sdp.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler 
//{
//    // 404 - Resource Not Found
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) 
//    {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
//
//    // 500 - Generic Exception
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneral(Exception ex) 
//    {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Internal Server Error: " + ex.getMessage());
//    }
//}