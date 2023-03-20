package com.pamela.todoapp.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetTokenResponse {

    private final String token;

    private final String email;
}
