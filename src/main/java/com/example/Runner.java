package com.example;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final AccountInfoRepository accountInfoRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    // begin transaction

    public void run(String... args) throws Exception {
        // transient
        Account account = Account.builder()
                .email("john.doe@site.com")
                .build();

        AccountInfo accountInfo = AccountInfo.builder()
                .account(account)
                .firstName("John")
                .lastName("Doe")
                .build();


        // insert
        // transient -> managed

        // tx open
        accountRepository.save(account);

//        if (true) {
            // rollback
//            throw new RuntimeException();

            // commit
//            throw new Exception();
//        }
        // insert
        accountInfoRepository.save(accountInfo);

        // 1st Layer Cache -> TX Level
        // 2nd Layer Cache
        for (int i = 0; i < 1_000; i++) {
            AccountInfo copyAccountInfo = accountInfoRepository.findById(1L).get();
        }


        // update
        accountInfo.setFirstName("Luibov");
        accountInfoRepository.save(accountInfo);

        // tx close
    }
    // commit transaction


    // begin transaction
    // accountRepository.save(account);
    // DB -> RAM -> account -> id -> 1
    // commit transaction

    // DB -> Hard Disk -> account -> id -> 1

    // Annual Report -> REQUIRES ->
    //   begin tx
    //   .save()
    // for
    //  January REQUIRES_NEW
    //    begin tx
    //    commit tx

    //  February REQUIRES_NEW <- ex <- rollback Feb ONLY

    //  March REQUIRES_NEW

    // REQUIRES_NEW

}

interface A {
    void abc();
    void zyx();
}

@Service
class AImpl implements A {

    @Autowired
    private A a; // -> spring -> impl

    @Transactional
    public void abc() {

        a.zyx();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void zyx() {

    }
}
