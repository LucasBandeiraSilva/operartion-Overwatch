package com.overwatch.agents.application.handler;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(int status, String message, List<ApiFieldError>fieldError, LocalDateTime timeStamp) {
}
