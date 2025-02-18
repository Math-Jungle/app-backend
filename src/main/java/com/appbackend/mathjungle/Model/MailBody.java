//package com.appbackend.mathjungle.Model;
//
//public class MailBody {
//    private final String to;
//    private final String subject;
//    private final String body;
//
//    // Private constructor that takes the builder
//    private MailBody(Builder builder) {
//        this.to = builder.to;
//        this.subject = builder.subject;
//        this.body = builder.body;
//    }
//
//    // Getters (if you need them)
//    public String getTo() {
//        return to;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public String getBody() {
//        return body;
//    }
//    public static Builder builder() {
//        return new Builder();
//    }
//
//    // Static nested Builder class
//    public static class Builder {
//        private String to;
//        private String subject;
//        private String body;
//
//        public Builder to(String to) {
//            this.to = to;
//            return this; // chain
//        }
//
//        public Builder subject(String subject) {
//            this.subject = subject;
//            return this; // chain
//        }
//
//        public Builder body(String body) {
//            this.body = body;
//            return this; // chain
//        }
//
//        public MailBody build() {
//            return new MailBody(this);
//        }
//    }
//}
