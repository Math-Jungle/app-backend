package com.appbackend.mathjungle.Model;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String body) {
}
