package com.batiaev.provisioner.model.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    @Test
    void should_create_email() {
        //given
        String emailString = "foo@bar.com";
        //when
        Email email = new Email(emailString);
        //then
        assertThat(email).hasToString(emailString);
    }
}