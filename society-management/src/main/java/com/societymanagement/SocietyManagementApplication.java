package com.societymanagement;

import com.societymanagement.entity.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.societymanagement.repository.MemberRepository;

@SpringBootApplication
public class SocietyManagementApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(SocietyManagementApplication.class, args);

    }

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        //TODO: will delete these once development is done.
        Members member1 = new Members("Omkar Nikam", "8082630153","omkarnik@gmail.com","omnik", "Chairmen");
        memberRepository.save(member1);

        Members member2 = new Members("Aditya Nikam", "7021169037","adityanik@gmail.com","adinik", "secretory");
        memberRepository.save(member2);

        Members member3 = new Members("Suresh Nikam", "9833674725","sureshnik@gmail.com","surnik", "Treasurer");
        memberRepository.save(member3);

        Members member4 = new Members("Sujata Nikam", "9152034336","sujatanik@gmail.com","sujnik", "member");
        memberRepository.save(member4);

        Members member5 = new Members("Nikunj Gaikwad", "9969377447","gaikwadnikunj2003@gmail.com","nikunj_gaikwad", "member");
        memberRepository.save(member5);

        Members member6 = new Members("Suyash Kerkar", "1234567890","SuyeshKerkar@gmail.com","Suyesh_Kerkar", "member");
        memberRepository.save(member6);
    }
}


