package com.heidatech.beauty_clinic_back.model.dto;

public record LoginResponse(String accessToken, long expiresIn) {
}
