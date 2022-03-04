package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.Dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("select u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

//    lo usamos optional para el manejo de los null
    Optional<User> findByNameAndEmail(String name, String email);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdAsc(String name);

    @Query("SELECT new com.fundamentos.springboot.fundamentos.Dto.UserDto(u.id, u.name, u.birthDate)" +
            " FROM User u " +
            " WHERE u.birthDate=:parameterDate" +
            " AND u.email=:parameterEmail")
    Optional<UserDto> getAllByBithDateAndEmail(@Param("parameterDate") LocalDate date,
                                               @Param("parameterEmail") String email);
}
