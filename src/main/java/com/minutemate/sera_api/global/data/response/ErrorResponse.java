package com.minutemate.sera_api.global.data.response;

import com.minutemate.sera_api.global.data.type.ErrorCode;

public record ErrorResponse(
        ErrorCode code,
        String message
) {
}
