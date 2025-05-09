package com.basic.myspringboot.repository;


import com.basic.myspringboot.entity.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional //테스트케이스가 메모리상으로 체크만 되어야지 영속적으로 값이 저장되어서는 안되기때문에 상속받는. 롤백이 기본이되어짐
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;


    @Test
    @Rollback(false)
    void deleteCustomer() {
        Customer customer = customerRepository.findById(1L)
                .orElseThrow(()->new RuntimeException("Customer Not Found"));
    }

    @Test
    @Rollback(false)
    void testUpdateCustomer() {
       Customer customer = customerRepository.findById(1L)
                .orElseThrow(()-> new RuntimeException("Customer Not Found"));
        //수정하려면 entity의 setter method를 호출한다
        customer.setCustomerName("홍길동");
        //호출한 값을 반영한다.
//        customerRepository.save(customer);
//        --> 이코드없어도 데이터 값이 업데이트가 된다. transactional을 걸어놨기 떄문임 이걸 dirtread라고한다.
        assertThat(customer.getCustomerName()).isEqualTo("홍길동");
    }


    @Test
    void testByNotFoundException(){ // 조회해서 없으면 에러를 반환하는 코드를 짜보자

      Customer customer = customerRepository.findByCustomerId("A005")
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
//        assertThat(customer.getCustomerId()).isEqualTo("A001");
    }


    @Test
    void testFindBy() { //코드 조회
        Optional<Customer> optionalCustomer = customerRepository.findById(1L);
        if (optionalCustomer.isPresent()) {
            Customer existCustomer = optionalCustomer.get();
            assertThat(existCustomer.getId()).isEqualTo(1L);
        }
        //-- 이렇게 쓰면 if문써서 코드가 길어지니까 아래처럼 씀


        //Optional 의 orElseGet(Supplier<? extends T> supplier)
        //Supplier 의 추상메서드 T get()
        //고객번호가 존재하는 경우
        Optional<Customer> optionalCustomer2 = customerRepository.findByCustomerId("A004");
        optionalCustomer.orElseGet(()->new Customer());
        Customer a001customer = optionalCustomer2.orElseGet(()-> new Customer());
        assertThat(a001customer.getCustomerName()).isEqualTo("스프링");
//        assertThat(a001customer).isNull();


        //해당 고객번호가 존재하지않는 경우
        Customer notFoundCustomer = customerRepository.findByCustomerId("A004")
                .orElseGet(()-> new Customer());
        assertThat(notFoundCustomer.getCustomerName()).isNull();
        }
        // 찾으려는 customerId값을 가진 엔티티가 있으면 값을 들고오고 , 아니면 새로운 객체를 생성해라(근데 값이없는 빈객체가 반환되어짐)
    @Test
    @Rollback(false)
    @Disabled
    void testCreatecustomer(){
        //Given
        Customer customer = new Customer();
        customer.setCustomerId("A003");
        customer.setCustomerName("스프링3");
        //when
        Customer addCustomer = customerRepository.save(customer);
        //Then
        assertThat(addCustomer).isNotNull();
        assertThat(addCustomer.getCustomerName()).isEqualTo("스프링3");
    }


}
