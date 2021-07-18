package com.vivek.employee101.repository;

import com.vivek.employee101.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//@Repository is a Spring annotation that indicates that the decorated class is a repository.
// A repository is a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects.
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
//    JpaRepository is JPA specific extension of Repository . It contains the full API of CrudRepository and PagingAndSortingRepository .
//    So it contains API for basic CRUD operations and also API for pagination and sorting
}
