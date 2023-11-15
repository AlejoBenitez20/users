package com.global.users.Pojos;

import com.global.users.dto.ErrorDetailDto;
import com.global.users.dto.ErrorDto;
import com.global.users.dto.PhoneDto;
import com.global.users.exception.UserExceptions;
import org.junit.jupiter.api.Test;
import org.meanbean.lang.Factory;
import org.meanbean.test.BeanTester;

import java.sql.Timestamp;
import java.time.LocalDate;

public class BeanTest {

    @Test
    void testPojos() {

        BeanTester beanTester = new BeanTester();
        beanTester.setIterations(1);
        
        beanTester.getFactoryCollection().addFactory(Timestamp.class, new Factory<Timestamp>() {
            @Override
            public Timestamp create() {
                return Timestamp.valueOf(LocalDate.now().atStartOfDay());
            }
        });

        beanTester.testBean(ErrorDetailDto.class);
        beanTester.testBean(ErrorDto.class);
        beanTester.testBean(PhoneDto.class);

    }
}
