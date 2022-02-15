package com.batiaev.provisioner.model.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberTest {
    @Test
    void should_create_phone_number() {
        //given
        String numberString = "+447555555555";
        //when
        PhoneNumber number = new PhoneNumber(numberString);
        //then
        assertThat(number).hasToString(numberString);
    }
}