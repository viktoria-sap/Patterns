package ru.netology.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationByCardInfo {
    private final String name;
    private final String phone;
}